package io.httpie.status;

public enum ExitStatus {
    SUCCESS(0),
    ERROR(1),
    ERROR_TIMEOUT(2),
    ERROR_HTTP_3XX(3),
    ERROR_HTTP_4XX(4),
    ERROR_HTTP_5XX(5),
    ERROR_TOO_MANY_REDIRECTS(6),
    PLUGIN_ERROR(7),
    ERROR_CTRL_C(130);

    private final int code;

    ExitStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static ExitStatus fromHttpStatus(int status, boolean follow) {
        if (status >= 300 && status <= 399 && !follow) {
            return ERROR_HTTP_3XX;
        } else if (status >= 400 && status <= 499) {
            return ERROR_HTTP_4XX;
        } else if (status >= 500 && status <= 599) {
            return ERROR_HTTP_5XX;
        }
        return SUCCESS;
    }
}
