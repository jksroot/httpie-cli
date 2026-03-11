package io.httpie.cli;

import java.util.ArrayList;
import java.util.List;

public class Definition {
    public static class Option {
        public final String name;
        public final String shortName;
        public final String description;
        public final boolean hasValue;

        public Option(String name, String shortName, String description, boolean hasValue) {
            this.name = name;
            this.shortName = shortName;
            this.description = description;
            this.hasValue = hasValue;
        }
    }

    public static final List<Option> OPTIONS = new ArrayList<>();

    static {
        // Content types
        OPTIONS.add(new Option("--json", "-j", "(default) Serialize data items from the command line as a JSON object.", false));
        OPTIONS.add(new Option("--form", "-f", "Serialize data items from the command line as form field data.", false));
        OPTIONS.add(new Option("--multipart", null, "Similar to --form, but always sends a multipart/form-data request.", false));
        OPTIONS.add(new Option("--boundary", null, "Specify a custom boundary string for multipart/form-data requests.", true));
        OPTIONS.add(new Option("--raw", null, "Pass raw request data without extra processing.", true));

        // Content processing
        OPTIONS.add(new Option("--compress", "-x", "Compress the content with Deflate algorithm.", false));

        // Output processing
        OPTIONS.add(new Option("--pretty", null, "Control the processing of console outputs.", true));
        OPTIONS.add(new Option("--style", "-s", "Output coloring style (default is \"auto\").", true));
        OPTIONS.add(new Option("--response-charset", null, "Override the response encoding for terminal display purposes.", true));
        OPTIONS.add(new Option("--response-mime", null, "Override the response mime type for coloring and formatting for the terminal.", true));
        OPTIONS.add(new Option("--format-options", null, "Controls output formatting.", true));

        // Output options
        OPTIONS.add(new Option("--print", "-p", "Options to specify what the console output should contain.", true));
        OPTIONS.add(new Option("--headers", "-h", "Print only the response headers. Shortcut for --print=h.", false));
        OPTIONS.add(new Option("--meta", "-m", "Print only the response metadata. Shortcut for --print=m.", false));
        OPTIONS.add(new Option("--body", "-b", "Print only the response body. Shortcut for --print=b.", false));
        OPTIONS.add(new Option("--verbose", "-v", "Verbose output.", false));
        OPTIONS.add(new Option("--all", null, "Show any intermediary requests/responses.", false));
        OPTIONS.add(new Option("--stream", "-S", "Always stream the response body by line.", false));
        OPTIONS.add(new Option("--output", "-o", "Save output to FILE instead of stdout.", true));
        OPTIONS.add(new Option("--download", "-d", "Download the body to a file instead of printing it to stdout.", false));
        OPTIONS.add(new Option("--continue", "-c", "Resume an interrupted download.", false));
        OPTIONS.add(new Option("--quiet", "-q", "Do not print to stdout or stderr.", false));

        // Sessions
        OPTIONS.add(new Option("--session", null, "Create, or reuse and update a session.", true));
        OPTIONS.add(new Option("--session-read-only", null, "Create or read a session without updating it.", true));

        // Authentication
        OPTIONS.add(new Option("--auth", "-a", "Credentials for the selected authentication method.", true));
        OPTIONS.add(new Option("--auth-type", "-A", "The authentication mechanism to be used.", true));
        OPTIONS.add(new Option("--ignore-netrc", null, "Ignore credentials from .netrc.", false));

        // Network
        OPTIONS.add(new Option("--offline", null, "Build the request and print it but don’t actually send it.", false));
        OPTIONS.add(new Option("--proxy", null, "String mapping of protocol to the URL of the proxy.", true));
        OPTIONS.add(new Option("--follow", "-F", "Follow 30x Location redirects.", false));
        OPTIONS.add(new Option("--max-redirects", null, "The maximum number of redirects that should be followed.", true));
        OPTIONS.add(new Option("--max-headers", null, "The maximum number of response headers to be read.", true));
        OPTIONS.add(new Option("--timeout", null, "The connection timeout of the request in seconds.", true));
        OPTIONS.add(new Option("--check-status", null, "Exit with an error status code if the server replies with an error.", false));
        OPTIONS.add(new Option("--path-as-is", null, "Bypass dot segment (/../ or /./) URL squashing.", false));
        OPTIONS.add(new Option("--chunked", null, "Enable streaming via chunked transfer encoding.", false));

        // SSL
        OPTIONS.add(new Option("--verify", null, "If \"no\", skip SSL verification.", true));
        OPTIONS.add(new Option("--ssl", null, "The desired protocol version to used.", true));
        OPTIONS.add(new Option("--ciphers", null, "A string in the OpenSSL cipher list format.", true));
        OPTIONS.add(new Option("--cert", null, "Specifies a local cert to use as the client-side SSL certificate.", true));
        OPTIONS.add(new Option("--cert-key", null, "The private key to use with SSL.", true));
        OPTIONS.add(new Option("--cert-key-pass", null, "The passphrase to be used to with the given private key.", true));

        // Troubleshooting
        OPTIONS.add(new Option("--ignore-stdin", "-I", "Do not attempt to read stdin.", false));
        OPTIONS.add(new Option("--help", null, "Show this help message and exit.", false));
        OPTIONS.add(new Option("--manual", null, "Show the full manual.", false));
        OPTIONS.add(new Option("--version", null, "Show version and exit.", false));
        OPTIONS.add(new Option("--traceback", null, "Prints the exception traceback should one occur.", false));
        OPTIONS.add(new Option("--default-scheme", null, "The default scheme to use if not specified in the URL.", true));
        OPTIONS.add(new Option("--debug", null, "Print useful diagnostic information for bug reports.", false));
    }
}
