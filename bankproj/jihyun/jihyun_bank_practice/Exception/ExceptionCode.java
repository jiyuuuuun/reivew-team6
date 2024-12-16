package jihyun_bank_practice.Exception;

public enum ExceptionCode {

    ACCOUNT_NOT_FOUND_EXCEPTION("존재하지 않는 계좌 요청 처리 "),
    BANK_OPERATION_EXCEPTION("일반적인 은행 관련 오류 처리 "),
    INVALID_TRANSACTION_EXCEPTION("잘못된 입출금 요청 처리");



    private static final String DEFAULT_ERROR_MESSAGE = "[ERROR] ";

    private final String message;

    ExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return DEFAULT_ERROR_MESSAGE + this.message;
    }


}
