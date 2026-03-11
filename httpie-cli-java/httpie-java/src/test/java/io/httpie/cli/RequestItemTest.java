package io.httpie.cli;

import java.util.List;

public class RequestItemTest {

    public static void main(String[] args) {
        testParseHeader();
        testParseData();
        testParseRawJson();
        testParseQueryParam();
        testEscapedSeparator();
        System.out.println("RequestItemTest passed!");
    }

    static void testParseHeader() {
        ArgTypes.KeyValueArgType type = new ArgTypes.KeyValueArgType(Constants.SEPARATOR_HEADER);
        ArgTypes.KeyValueArg item = type.parse("Content-Type:application/json");
        if (!"Content-Type".equals(item.key)) throw new AssertionError("Expected Content-Type, got " + item.key);
        if (!":".equals(item.sep)) throw new AssertionError("Expected :, got " + item.sep);
        if (!"application/json".equals(item.value)) throw new AssertionError("Expected application/json, got " + item.value);
    }

    static void testParseData() {
        ArgTypes.KeyValueArgType type = new ArgTypes.KeyValueArgType(Constants.SEPARATOR_DATA_STRING);
        ArgTypes.KeyValueArg item = type.parse("name=value");
        if (!"name".equals(item.key)) throw new AssertionError("Expected name, got " + item.key);
        if (!"=".equals(item.sep)) throw new AssertionError("Expected =, got " + item.sep);
        if (!"value".equals(item.value)) throw new AssertionError("Expected value, got " + item.value);
    }

    static void testParseRawJson() {
        ArgTypes.KeyValueArgType type = new ArgTypes.KeyValueArgType(Constants.SEPARATOR_DATA_RAW_JSON);
        ArgTypes.KeyValueArg item = type.parse("data:={\"key\":\"value\"}");
        if (!"data".equals(item.key)) throw new AssertionError("Expected data, got " + item.key);
        if (!":=".equals(item.sep)) throw new AssertionError("Expected :=, got " + item.sep);
        if (!"{\"key\":\"value\"}".equals(item.value)) throw new AssertionError("Expected {\"key\":\"value\"}, got " + item.value);
    }

    static void testParseQueryParam() {
        ArgTypes.KeyValueArgType type = new ArgTypes.KeyValueArgType(Constants.SEPARATOR_QUERY_PARAM);
        ArgTypes.KeyValueArg item = type.parse("q==search");
        if (!"q".equals(item.key)) throw new AssertionError("Expected q, got " + item.key);
        if (!"==".equals(item.sep)) throw new AssertionError("Expected ==, got " + item.sep);
        if (!"search".equals(item.value)) throw new AssertionError("Expected search, got " + item.value);
    }

    static void testEscapedSeparator() {
        ArgTypes.KeyValueArgType type = new ArgTypes.KeyValueArgType("=");
        ArgTypes.KeyValueArg item = type.parse("foo\\=bar=baz");
        if (!"foo=bar".equals(item.key)) throw new AssertionError("Expected foo=bar, got " + item.key);
        if (!"=".equals(item.sep)) throw new AssertionError("Expected =, got " + item.sep);
        if (!"baz".equals(item.value)) throw new AssertionError("Expected baz, got " + item.value);
    }
}
