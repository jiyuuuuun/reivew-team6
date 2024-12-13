package BankProject.ExceptionClass;

//일반적인 은행 관련 오류 처리
public class BankOperationException extends Exception{

    public BankOperationException(){

    }
    public BankOperationException(String str){
        System.out.println("str");
    }
}
