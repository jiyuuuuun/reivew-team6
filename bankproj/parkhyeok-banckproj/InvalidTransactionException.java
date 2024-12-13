package backendproject;

public class InvalidTransactionException extends Exception{
    public InvalidTransactionException() {
        super("통장 잔고보다 많은 금액을 출력합니다.");
    }
    public InvalidTransactionException(String message) {
        super(message);
    }


}
