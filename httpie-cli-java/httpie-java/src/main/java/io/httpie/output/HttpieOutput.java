package io.httpie.output;

import io.httpie.cli.HttpieCli;
import io.httpie.models.HTTPMessage;

import java.nio.charset.StandardCharsets;

public class HttpieOutput {

    public void write(HTTPMessage message, HttpieCli cli) {
        String printOptions = cli.args.print != null ? cli.args.print : "hb";
        
        if (printOptions.contains("H") || printOptions.contains("h")) {
            System.out.println(message.getHeaders());
            System.out.println();
        }

        if (printOptions.contains("B") || printOptions.contains("b")) {
            byte[] body = message.getBody();
            if (body != null && body.length > 0) {
                // Simplified output without JSON formatting for now
                System.out.println(new String(body, StandardCharsets.UTF_8));
            }
        }
    }
}
