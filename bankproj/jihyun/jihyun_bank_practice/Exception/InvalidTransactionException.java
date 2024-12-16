package jihyun_bank_practice.Exception;

public class InvalidTransactionException extends Exception{

    public InvalidTransactionException() {
        super("잘못된 입출금 요청 처리");
    }

    public InvalidTransactionException(String message) {
        super(message);
    }
}
