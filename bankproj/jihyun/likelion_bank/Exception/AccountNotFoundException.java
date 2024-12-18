package likelion_bank.Exception;

public class AccountNotFoundException extends Exception{
    public AccountNotFoundException() {
        super("존재하지 않는 계좌 요청 처리 ");
    }

    public AccountNotFoundException(String message) {
        super(message);
    }
}
