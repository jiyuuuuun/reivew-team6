package backendproject;

import day09.BankException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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

    public void addCustomer(Customer customer) throws BankException {
        if (customers.size() == MAX_SIZE) {
            throw new BankException("최대 고객 제한을 초과했습니다.");
        }

        customers.add(customer);
    }

    public Customer findCustomer(int i) {
        for (Customer customer : customers) {
            if (customer.getId() == i) {
                return customer;
            }
        }
        return null;
    }

    public boolean isIn(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void saveCustomer(){
        try {
            System.out.println("고객 등록을 선택하셨습니다.");
            System.out.print("고객의 이름을 입력하세요 : ");
            Scanner scanner = new Scanner(System.in);
            String name = scanner.next();
            System.out.print("고객의 id를 입력해주세요 : ");
            int id = scanner.nextInt();
            if(isIn(id)){
                throw new BankException("이미 존재하는 ID입니다. (다른 아이디를 입력해주세요.)");
            }
            Customer customer = Customer.builder()
                    .name(name)
                    .id(id)
                    .build();

            addCustomer(customer);

            System.out.println("-------- 현재 고객 정보 --------");
            for (Customer c : getCustomers()) {
                System.out.println(c.getName());
            }
            System.out.println("------------------------------");
        }catch (BankException e) {
            System.out.println(e.getMessage());
        }
    }



}
