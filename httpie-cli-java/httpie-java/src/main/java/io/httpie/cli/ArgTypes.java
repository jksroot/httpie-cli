package io.httpie.cli;

import java.util.*;
import java.util.stream.Collectors;

public class ArgTypes {

    public static class KeyValueArg {
        public final String key;
        public final String value;
        public final String sep;
        public final String orig;

        public KeyValueArg(String key, String value, String sep, String orig) {
            this.key = key;
            this.value = value;
            this.sep = sep;
            this.orig = orig;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            KeyValueArg that = (KeyValueArg) o;
            return Objects.equals(key, that.key) &&
                   Objects.equals(value, that.value) &&
                   Objects.equals(sep, that.sep) &&
                   Objects.equals(orig, that.orig);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value, sep, orig);
        }

        @Override
        public String toString() {
            return "KeyValueArg{" +
                   "key='" + key + '\'' +
                   ", value='" + value + '\'' +
                   ", sep='" + sep + '\'' +
                   ", orig='" + orig + '\'' +
                   '}';
        }
    }

    public static class Escaped {
        public final char value;

        public Escaped(char value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    public static class KeyValueArgType {
        private final Set<String> separators;
        private final Set<Character> specialCharacters;

        public KeyValueArgType(String... separators) {
            this.separators = new HashSet<>(Arrays.asList(separators));
            this.specialCharacters = new HashSet<>();
            for (String separator : separators) {
                for (char c : separator.toCharArray()) {
                    specialCharacters.add(c);
                }
            }
        }

        public KeyValueArg parse(String s) {
            List<Object> tokens = tokenize(s);
            List<String> sortedSeparators = separators.stream()
                    .sorted(Comparator.comparingInt(String::length))
                    .collect(Collectors.toList());

            for (int i = 0; i < tokens.size(); i++) {
                Object token = tokens.get(i);
                if (token instanceof Escaped) {
                    continue;
                }

                String strToken = (String) token;
                Map<Integer, String> found = new TreeMap<>();
                for (String sep : sortedSeparators) {
                    int pos = strToken.indexOf(sep);
                    if (pos != -1) {
                        found.put(pos, sep);
                    }
                }

                if (!found.isEmpty()) {
                    int minPos = found.keySet().iterator().next();
                    String sep = found.get(minPos);

                    String key = strToken.substring(0, minPos);
                    String value = strToken.substring(minPos + sep.length());

                    StringBuilder keyBuilder = new StringBuilder();
                    for (int j = 0; j < i; j++) {
                        keyBuilder.append(tokens.get(j).toString());
                    }
                    keyBuilder.append(key);

                    StringBuilder valueBuilder = new StringBuilder();
                    valueBuilder.append(value);
                    for (int j = i + 1; j < tokens.size(); j++) {
                        valueBuilder.append(tokens.get(j).toString());
                    }

                    return new KeyValueArg(keyBuilder.toString(), valueBuilder.toString(), sep, s);
                }
            }

            throw new IllegalArgumentException("'" + s + "' is not a valid value");
        }

        private List<Object> tokenize(String s) {
            List<Object> tokens = new ArrayList<>();
            tokens.add("");
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '\\') {
                    if (i + 1 < s.length()) {
                        char nextChar = s.charAt(++i);
                        if (!specialCharacters.contains(nextChar)) {
                            tokens.set(tokens.size() - 1, (String)tokens.get(tokens.size() - 1) + "\\" + nextChar);
                        } else {
                            tokens.add(new Escaped(nextChar));
                            tokens.add("");
                        }
                    } else {
                        tokens.set(tokens.size() - 1, (String)tokens.get(tokens.size() - 1) + "\\");
                    }
                } else {
                    tokens.set(tokens.size() - 1, (String)tokens.get(tokens.size() - 1) + c);
                }
            }
            return tokens;
        }
    }

    public static class AuthCredentials extends KeyValueArg {
        public AuthCredentials(String key, String value, String sep, String orig) {
            super(key, value, sep, orig);
        }

        public boolean hasPassword() {
            return value != null;
        }
    }

    public static class AuthCredentialsArgType extends KeyValueArgType {
        public AuthCredentialsArgType(String separator) {
            super(separator);
        }

        @Override
        public AuthCredentials parse(String s) {
            try {
                KeyValueArg arg = super.parse(s);
                return new AuthCredentials(arg.key, arg.value, arg.sep, arg.orig);
            } catch (IllegalArgumentException e) {
                return new AuthCredentials(s, null, Constants.SEPARATOR_CREDENTIALS, s);
            }
        }
    }

    public static final AuthCredentialsArgType AUTH_CREDENTIALS_ARG_TYPE = new AuthCredentialsArgType(Constants.SEPARATOR_CREDENTIALS);
}
