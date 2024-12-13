package BankProject.ExceptionClass;

//존재하지 않는 계좌 요청 처리
public class AccountNotFoundException extends Exception{

    public AccountNotFoundException(){

    }
    public AccountNotFoundException(String str){
        System.out.println(str);
    }
}
