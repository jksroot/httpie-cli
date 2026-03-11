package io.httpie.status;

public class ExitStatusTest {
    public static void main(String[] args) {
        assert ExitStatus.fromHttpStatus(404, false) == ExitStatus.ERROR_HTTP_4XX;
        assert ExitStatus.fromHttpStatus(200, false) == ExitStatus.SUCCESS;
        System.out.println("ExitStatusTest passed");
    }
}
