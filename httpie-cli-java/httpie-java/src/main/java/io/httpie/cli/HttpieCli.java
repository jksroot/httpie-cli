package io.httpie.cli;

import io.httpie.client.HttpieClient;
import io.httpie.models.HTTPResponse;
import io.httpie.output.HttpieOutput;

public class HttpieCli {

    public ArgumentParser.ParsedArgs args;

    public static void main(String[] args) throws Exception {
        HttpieCli cli = new HttpieCli();
        if (cli.parse(args)) {
            cli.run();
        }
    }

    public boolean parse(String[] args) {
        try {
            this.args = new ArgumentParser().parse(args);
            return true;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public void run() throws Exception {
        if (args.offline) {
            System.out.println("Offline mode: Skipping request to " + args.url);
            return;
        }

        HttpieClient client = new HttpieClient();
        HTTPResponse response = client.send(this);

        HttpieOutput output = new HttpieOutput();
        output.write(response, this);
    }
}
