package BankProject;

//계좌 번호, 잔액, 소유 고객 ID를 포함하며, 입금 및 출금 기능을 제공

import BankProject.ExceptionClass.InvalidTransactionException;

public class Account {



    //계좌번호
    private long accountNumber;

    //잔액
    private long balance;

    //소유 고객 ID
    private int ownId;

    public Account(long accountNumber, long balance, int ownId) {
        this.accountNumber=accountNumber;
        this.balance=balance;
        this.ownId=ownId;
    }

    //입금 메서드
    public void deposit(long money)throws Exception{
        if(money<=0)
            throw new InvalidTransactionException("입금 금액이 0원 입니다");
        this.balance+=money;

    }

    //출금 메서드
    public void withdraw(long money)throws Exception{
        if(balance<=0||balance<money)
            throw new InvalidTransactionException("잔액 부족");
        this.balance-=money;
        System.out.println("출금 완료 되었습니다");
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getBalance() {
        return balance;
    }

    public  void setBalance(long balance) {
        this.balance = balance;
    }

    public  int getOwnId() {
        return this.ownId;
    }

    public  void setOwnId(int ownId) {
        this.ownId = ownId;
    }


}
