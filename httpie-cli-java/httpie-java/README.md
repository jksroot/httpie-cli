# HTTPie Java Refactor

This is a refactored version of the HTTPie CLI in Java 21.

## Project Structure

- `src/main/java/io/httpie/cli/`: CLI layer (argument parsing).
- `src/main/java/io/httpie/client/`: Client layer (HTTP request logic using `java.net.http.HttpClient`).
- `src/main/java/io/httpie/models/`: Core models (`HTTPRequest`, `HTTPResponse`, `HTTPMessage`).
- `src/main/java/io/httpie/output/`: Output layer (formatting and writing).
- `src/main/java/io/httpie/utils/`: Core utilities.

## How to Execute Unit Tests

Since external build tools like Maven or Gradle might not be available, you can compile and run the tests manually using `javac` and `java`.

### Prerequisites

- Java 21 (JAVA_HOME is set to `/usr/lib/jvm/java-21-openjdk-amd64`)

### Compilation

From the `httpie-java` directory, run:

```bash
mkdir -p out
javac -d out $(find src/main/java -name "*.java")
javac -d out -cp out $(find src/test/java -name "*.java")
```

### Running Tests

To run all unit tests:

```bash
java -cp out io.httpie.utils.HttpieUtilsTest
java -cp out io.httpie.cli.RequestItemTest
java -cp out io.httpie.cli.ArgumentParserTest
java -cp out io.httpie.cli.DefinitionTest
java -cp out io.httpie.internal.UpdateWarningsTest
java -cp out io.httpie.internal.DaemonsTest
java -cp out io.httpie.legacy.LegacyFormatTest
java -cp out io.httpie.manager.ManagerCliTest
java -cp out io.httpie.manager.ManagerCoreTest
java -cp out io.httpie.manager.tasks.ExportArgsTaskTest
java -cp out io.httpie.manager.tasks.PluginsTaskTest
java -cp out io.httpie.manager.tasks.SessionsTaskTest
java -cp out io.httpie.manager.tasks.CheckUpdatesTaskTest
java -cp out io.httpie.output.ProcessingOptionsTest
java -cp out io.httpie.output.ProcessingTest
java -cp out io.httpie.output.OutputUtilsTest
java -cp out io.httpie.output.OutputWriterTest
java -cp out io.httpie.output.formatters.FormattersTest
java -cp out io.httpie.output.lexers.LexersTest
java -cp out io.httpie.output.ui.UiTest
java -cp out io.httpie.plugins.PluginTest
java -cp out io.httpie.adapters.HTTPieHTTPAdapterTest
java -cp out io.httpie.config.ConfigTest
java -cp out io.httpie.context.EnvironmentTest
java -cp out io.httpie.cookies.HTTPieCookiePolicyTest
java -cp out io.httpie.downloads.DownloadsTest
java -cp out io.httpie.encoding.EncodingUtilsTest
java -cp out io.httpie.sessions.SessionsTest
java -cp out io.httpie.ssl.SslSupportTest
java -cp out io.httpie.status.ExitStatusTest
java -cp out io.httpie.uploads.UploadsTest
java -cp out io.httpie.CoreTest
```

## How to Run the Application

After compiling, you can run the refactored CLI entrypoint:

```bash
java -cp out io.httpie.cli.HttpieCli [METHOD] URL [ITEM [ITEM ...]]
```

Example:

```bash
java -cp out io.httpie.cli.HttpieCli GET https://httpbin.org/get
java -cp out io.httpie.cli.HttpieCli POST https://httpbin.org/post name=value
```

If you want to invoke the core entrypoint directly:

```bash
java -cp out io.httpie.core.Core [ARGS]
```
