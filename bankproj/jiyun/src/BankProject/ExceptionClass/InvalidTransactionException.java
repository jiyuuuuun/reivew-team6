package BankProject.ExceptionClass;

//잘못된 입출금 요청 처리
public class InvalidTransactionException extends Exception{

    public InvalidTransactionException(){

    }
    public InvalidTransactionException(String str){
        System.out.println(str);
    }
}
