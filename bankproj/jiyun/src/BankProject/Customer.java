package BankProject;

import BankProject.ExceptionClass.AccountNotFoundException;
import BankProject.ExceptionClass.BankOperationException;

import java.util.ArrayList;

//고객 ID와 이름을 포함하며, 고객이 소유한 통장(Account) 목록을 관리
public class Customer {


    public Customer(){

    }
    public Customer(int id,String name){
        this.id=id;
        this.name=name;
        accounts.add(new Account(id,0,id));
    }

    //고객 ID
    private int id;

    //고객 이름
    private String name;

    private ArrayList <Account> accounts = new ArrayList<>();

    //계좌를 추가하는 메서드
    public void accountAdd(long accountNum,long balance,int id)throws Exception{
        if(accounts.size()>5){
            throw new BankOperationException("계좌는 10개 이하만 등록 가능합니다");
        }
        accounts.add(new Account(accountNum, balance, id));
        System.out.println("계좌가 추가 되었습니다");
    }

    //계좌를 검색하는 메서드
    public void searchAccount(int accountNum)throws Exception{
        for(Account account:accounts)
            if(account.getAccountNumber()==accountNum){
                System.out.println("계좌의 잔액은:"+account.getBalance()+"원 입니다");
            }else{
                throw new AccountNotFoundException("존재하지 않는 계좌 번호 입니다");
            }

    }

    public  int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }
}
