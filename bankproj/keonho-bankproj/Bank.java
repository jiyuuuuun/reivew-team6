package backendproject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bank {
    private final static int MAX_SIZE = 10;
    private ArrayList<Customer> customers = new ArrayList<>();

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) throws BankOperationException {
        if (customers.size() == MAX_SIZE) {
            throw new BankOperationException("최대 고객 제한을 초과했습니다.");
        }

        customers.add(customer);
    }

    public Customer findCustomer(int i) throws BankOperationException {
        Customer returnCustomer = null;

        for (Customer customer : customers) {
            if (customer.getId() == i) {
                returnCustomer = customer;
                return returnCustomer;
            }
        }

        throw new BankOperationException("없는 고객입니다.");
    }

    public boolean isIn(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void saveCustomer(String name, int id){
        try {

            // 중복 체크
            if(isIn(id)){
                throw new BankOperationException("이미 존재하는 ID입니다. (다른 아이디를 입력해주세요.)");
            }

            // 고객 생성
            Customer customer = Customer.builder()
                    .name(name)
                    .id(id)
                    .build();

            // 고객 목록에 추가
            addCustomer(customer);

            // 목록 출력
            showCustomersDiplay();

        }catch (BankOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    // 고객 목록 출력 Display
    private void showCustomersDiplay() {
        System.out.println("-------- 현재 고객 정보 --------");
        for (Customer c : getCustomers()) {
            System.out.println(c.getName());
        }
        System.out.println("------------------------------");
    }



}
