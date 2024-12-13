package BankProject;

import BankProject.ExceptionClass.AccountNotFoundException;
import BankProject.ExceptionClass.BankOperationException;

import java.util.ArrayList;

//고객을 등록하고, 고객의 정보를 조회합니다.
public class Bank {

    public Bank(){

    }

    public Bank(int id,String name){
        try {
            addCustomer(id, name);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private static ArrayList<Customer> customers=new ArrayList<>();

    //고객을 추가
    public void addCustomer(int id, String name) throws Exception {
        if (customers.size() > 100000) {
            throw new BankOperationException("고객 수가 초과되었습니다.");
        }
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                throw new Exception("이미 등록 된 아이디 입니다.");
            }
        }
        // 루프가 끝난 후 중복 확인 완료
        customers.add(new Customer(id, name));
        System.out.println("고객이 성공적으로 추가되었습니다.");
    }
    //고객 검색하고 반환
    public Customer searchCustomer(String name){
        for(Customer customer: customers)
            if(customer.getName().equals(name)){
              return customer;
            }
        System.out.println("해당 이름의 고객을 찾을 수 없습니다.");
        return null;  // 고객을 찾지 못한 경우 null 반환
    }

    //고객 검색
    public void searchCustomer2(String name){
        for(Customer customer: customers)
            if(customer.getName().equals(name)){
                System.out.println("고객의 ID는"+customer.getId()+"입니다");
                System.out.println("현재 보유 계좌는"+customer.getAccounts().size()+"개 입니다");

            }
        System.out.println("해당 이름의 고객을 찾을 수 없습니다.");

    }



    // 잔액 조회 메서드
    public void balanceSearch(long accountNum) throws Exception {
        boolean accountFound = false; // 계좌 번호를 찾았는지 여부 확인용 플래그

        for (Customer customer : customers) {
            for (Account account : customer.getAccounts()) {
                if (account.getAccountNumber() == accountNum) {
                    System.out.println("잔액은 " + account.getBalance() + "원 입니다");
                    accountFound = true; // 계좌를 찾았으므로 플래그 설정
                    break; // 계좌를 찾았으니 더 이상 루프를 돌 필요 없음
                }
            }
        }

        // 모든 계좌를 확인했음에도 계좌 번호를 찾지 못한 경우
        if (!accountFound) {
            throw new AccountNotFoundException("등록 되지 않은 계좌 번호 입니다");
        }
    }


    public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    public static void setCustomers(ArrayList<Customer> customers) {
        Bank.customers = customers;
    }


}
