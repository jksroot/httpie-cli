package io.httpie.cli;

import java.util.*;

public class Dicts {

    public static class BaseMultiDict {
        protected final Map<String, List<Object>> data = new LinkedHashMap<>();

        public void add(String key, Object value) {
            data.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
        }

        public void put(String key, Object value) {
            List<Object> values = new ArrayList<>();
            values.add(value);
            data.put(key, values);
        }

        public List<Object> get(String key) {
            return data.getOrDefault(key, Collections.emptyList());
        }

        public Map<String, List<Object>> getData() {
            return data;
        }

        public List<Map.Entry<String, Object>> items() {
            List<Map.Entry<String, Object>> items = new ArrayList<>();
            for (Map.Entry<String, List<Object>> entry : data.entrySet()) {
                for (Object value : entry.getValue()) {
                    items.add(new AbstractMap.SimpleEntry<>(entry.getKey(), value));
                }
            }
            return items;
        }
    }

    public static class HTTPHeadersDict extends BaseMultiDict {
        @Override
        public void add(String key, Object value) {
            String normalizedKey = key.toLowerCase();
            if (value == null) {
                put(normalizedKey, null);
                return;
            }
            List<Object> values = data.get(normalizedKey);
            if (values != null && values.size() == 1 && values.get(0) == null) {
                data.remove(normalizedKey);
            }
            super.add(normalizedKey, value);
        }
    }

    public static class RequestJSONDataDict extends LinkedHashMap<String, Object> {}

    public static class MultiValueOrderedDict extends BaseMultiDict {}

    public static class RequestQueryParamsDict extends MultiValueOrderedDict {}

    public static class RequestDataDict extends MultiValueOrderedDict {}

    public static class MultipartRequestDataDict extends MultiValueOrderedDict {}

    public static class RequestFilesDict extends RequestDataDict {}
}
