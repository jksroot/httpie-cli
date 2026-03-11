package io.httpie.cli;

import java.util.*;

public class ArgumentParser {

    public static class ParsedArgs {
        public String method;
        public String url;
        public RequestItems requestItems;
        public boolean verbose;
        public boolean form;
        public boolean json = true;
        public boolean offline;
        public String print;
        public List<ArgTypes.KeyValueArg> rawItems = new ArrayList<>();
    }

    public ParsedArgs parse(String[] args) {
        ParsedArgs parsed = new ParsedArgs();
        List<String> positional = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (arg.equals("-v") || arg.equals("--verbose")) {
                parsed.verbose = true;
            } else if (arg.equals("-f") || arg.equals("--form")) {
                parsed.form = true;
                parsed.json = false;
            } else if (arg.equals("-j") || arg.equals("--json")) {
                parsed.json = true;
                parsed.form = false;
            } else if (arg.equals("--offline")) {
                parsed.offline = true;
            } else if (arg.startsWith("-p") || arg.startsWith("--print")) {
                if (arg.contains("=")) {
                    parsed.print = arg.substring(arg.indexOf("=") + 1);
                } else if (i + 1 < args.length) {
                    parsed.print = args[++i];
                }
            } else if (arg.startsWith("-")) {
                // Ignore other options for now
            } else {
                positional.add(arg);
            }
        }

        if (positional.isEmpty()) {
            throw new IllegalArgumentException("Error: URL is required");
        }

        String first = positional.get(0);
        if (first.matches("^[a-zA-Z]+$")) {
            parsed.method = first.toUpperCase();
            positional.remove(0);
        }

        if (positional.isEmpty()) {
            throw new IllegalArgumentException("Error: URL is required");
        }

        parsed.url = positional.get(0);
        positional.remove(0);

        ArgTypes.KeyValueArgType itemType = new ArgTypes.KeyValueArgType(
                Constants.SEPARATOR_GROUP_ALL_ITEMS.toArray(new String[0]));

        for (String item : positional) {
            parsed.rawItems.add(itemType.parse(item));
        }

        Constants.RequestType requestType = parsed.form ? Constants.RequestType.FORM : Constants.RequestType.JSON;
        parsed.requestItems = RequestItems.fromArgs(parsed.rawItems, requestType);

        if (parsed.method == null) {
            boolean hasData = parsed.requestItems.hasData() || !parsed.requestItems.files.items().isEmpty();
            parsed.method = hasData ? Constants.HTTP_POST : Constants.HTTP_GET;
        }

        return parsed;
    }
}
