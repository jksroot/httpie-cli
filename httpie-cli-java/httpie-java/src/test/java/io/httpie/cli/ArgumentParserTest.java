package io.httpie.cli;

public class ArgumentParserTest {

    public static void main(String[] args) {
        testParseBasic();
        testParseMethod();
        testParseItems();
        System.out.println("ArgumentParserTest passed!");
    }

    static void testParseBasic() {
        ArgumentParser parser = new ArgumentParser();
        ArgumentParser.ParsedArgs args = parser.parse(new String[]{"https://httpbin.org/get"});
        if (!"GET".equals(args.method)) throw new AssertionError("Expected GET, got " + args.method);
        if (!"https://httpbin.org/get".equals(args.url)) throw new AssertionError("Expected https://httpbin.org/get, got " + args.url);
    }

    static void testParseMethod() {
        ArgumentParser parser = new ArgumentParser();
        ArgumentParser.ParsedArgs args = parser.parse(new String[]{"POST", "https://httpbin.org/post"});
        if (!"POST".equals(args.method)) throw new AssertionError("Expected POST, got " + args.method);
        if (!"https://httpbin.org/post".equals(args.url)) throw new AssertionError("Expected https://httpbin.org/post, got " + args.url);
    }

    static void testParseItems() {
        ArgumentParser parser = new ArgumentParser();
        ArgumentParser.ParsedArgs args = parser.parse(new String[]{"https://httpbin.org/post", "name=value", "Header:Value"});
        if (!"POST".equals(args.method)) throw new AssertionError("Expected POST, got " + args.method);
        if (args.requestItems.headers.get("header").size() != 1) throw new AssertionError("Expected 1 header");
        if (!"Value".equals(args.requestItems.headers.get("header").get(0))) throw new AssertionError("Expected Value");
        if (!"value".equals(((Dicts.RequestJSONDataDict)args.requestItems.data).get("name"))) throw new AssertionError("Expected value");
    }
}
