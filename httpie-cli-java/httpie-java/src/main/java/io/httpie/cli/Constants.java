package io.httpie.cli;

import java.util.Set;
import java.util.regex.Pattern;

public class Constants {
    public static final Pattern URL_SCHEME_RE = Pattern.compile("^[a-z][a-z0-9.+-]*://", Pattern.CASE_INSENSITIVE);

    public static final String HTTP_POST = "POST";
    public static final String HTTP_GET = "GET";
    public static final String HTTP_OPTIONS = "OPTIONS";

    // Various separators used in args
    public static final String SEPARATOR_HEADER = ":";
    public static final String SEPARATOR_HEADER_EMPTY = ";";
    public static final String SEPARATOR_CREDENTIALS = ":";
    public static final String SEPARATOR_PROXY = ":";
    public static final String SEPARATOR_HEADER_EMBED = ":@";
    public static final String SEPARATOR_DATA_STRING = "=";
    public static final String SEPARATOR_DATA_RAW_JSON = ":=";
    public static final String SEPARATOR_FILE_UPLOAD = "@";
    public static final String SEPARATOR_FILE_UPLOAD_TYPE = ";type=";
    public static final String SEPARATOR_DATA_EMBED_FILE_CONTENTS = "=@";
    public static final String SEPARATOR_DATA_EMBED_RAW_JSON_FILE = ":=@";
    public static final String SEPARATOR_QUERY_PARAM = "==";
    public static final String SEPARATOR_QUERY_EMBED_FILE = "==@";

    // Separators that become request data
    public static final Set<String> SEPARATOR_GROUP_DATA_ITEMS = Set.of(
            SEPARATOR_DATA_STRING,
            SEPARATOR_DATA_RAW_JSON,
            SEPARATOR_FILE_UPLOAD,
            SEPARATOR_DATA_EMBED_FILE_CONTENTS,
            SEPARATOR_DATA_EMBED_RAW_JSON_FILE
    );

    public static final Set<String> SEPARATORS_GROUP_MULTIPART = Set.of(
            SEPARATOR_DATA_STRING,
            SEPARATOR_DATA_EMBED_FILE_CONTENTS,
            SEPARATOR_FILE_UPLOAD
    );

    // Separators for items whose value is a filename to be embedded
    public static final Set<String> SEPARATOR_GROUP_DATA_EMBED_ITEMS = Set.of(
            SEPARATOR_HEADER_EMBED,
            SEPARATOR_QUERY_EMBED_FILE,
            SEPARATOR_DATA_EMBED_FILE_CONTENTS,
            SEPARATOR_DATA_EMBED_RAW_JSON_FILE
    );

    // Separators for nested JSON items
    public static final Set<String> SEPARATOR_GROUP_NESTED_JSON_ITEMS = Set.of(
            SEPARATOR_DATA_STRING,
            SEPARATOR_DATA_RAW_JSON,
            SEPARATOR_DATA_EMBED_FILE_CONTENTS,
            SEPARATOR_DATA_EMBED_RAW_JSON_FILE
    );

    // Separators allowed in ITEM arguments
    public static final Set<String> SEPARATOR_GROUP_ALL_ITEMS = Set.of(
            SEPARATOR_HEADER,
            SEPARATOR_HEADER_EMPTY,
            SEPARATOR_HEADER_EMBED,
            SEPARATOR_QUERY_PARAM,
            SEPARATOR_QUERY_EMBED_FILE,
            SEPARATOR_DATA_STRING,
            SEPARATOR_DATA_RAW_JSON,
            SEPARATOR_FILE_UPLOAD,
            SEPARATOR_DATA_EMBED_FILE_CONTENTS,
            SEPARATOR_DATA_EMBED_RAW_JSON_FILE
    );

    // Output options
    public static final String OUT_REQ_HEAD = "H";
    public static final String OUT_REQ_BODY = "B";
    public static final String OUT_RESP_HEAD = "h";
    public static final String OUT_RESP_BODY = "b";
    public static final String OUT_RESP_META = "m";

    public static final Set<String> BASE_OUTPUT_OPTIONS = Set.of(
            OUT_REQ_HEAD,
            OUT_REQ_BODY,
            OUT_RESP_HEAD,
            OUT_RESP_BODY
    );

    public static final Set<String> OUTPUT_OPTIONS = Set.of(
            OUT_REQ_HEAD,
            OUT_REQ_BODY,
            OUT_RESP_HEAD,
            OUT_RESP_BODY,
            OUT_RESP_META
    );

    // Defaults
    public static final String OUTPUT_OPTIONS_DEFAULT = OUT_RESP_HEAD + OUT_RESP_BODY;
    public static final String OUTPUT_OPTIONS_DEFAULT_STDOUT_REDIRECTED = OUT_RESP_BODY;
    public static final String OUTPUT_OPTIONS_DEFAULT_OFFLINE = OUT_REQ_HEAD + OUT_REQ_BODY;

    public enum PrettyOptions {
        STDOUT_TTY_ONLY
    }

    public enum RequestType {
        FORM,
        MULTIPART,
        JSON
    }
}
