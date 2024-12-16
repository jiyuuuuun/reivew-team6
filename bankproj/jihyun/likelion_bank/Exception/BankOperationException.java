package likelion_bank.Exception;

public class BankOperationException extends Exception{
    public BankOperationException() {
        super("일반적인 은행 관련 오류 처리 ");
    }

    public BankOperationException(String message) {
        super(message);
    }
}
