package backendproject;

import lombok.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    private String name;
    private int id;
    private ArrayList<Account> accounts = new ArrayList<>();
    private static final int MAX_SIZE = 5; // 최대 계좌 수
    private Scanner scanner = new Scanner(System.in); // Scanner 필드로 선언

    public Customer(String name) {
        this.name = name;
    }

    public void addAccount(Account a) {
        accounts.add(a);
    }

    public Boolean isIn(String bankNumber) {
        for (Account account : accounts) {
            if (bankNumber.equals(account.getBankNumber())) {
                return true;
            }
        }
        return false;
    }

    public void createAccount(Bank bank) {
        System.out.println("계좌 생성을 선택하셨습니다.");
        System.out.print("ID를 입력해주세요: ");

        int customerId = 0;
        while (true) {
            try {
                customerId = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("정수를 입력해야 합니다. 다시 시도하세요.");
                scanner.next();
            }
        }

        Customer customer;
        try {
            customer = bank.findCustomer(customerId);
            if (customer == null) {
                throw new AccountNotFoundException("없는 고객입니다.");
            }

            System.out.print("추가하고 싶은 계좌 번호를 입력하세요: ");
            String bankNumber = scanner.next();

            if(customer.getAccounts() != null) {
                if (customer.getAccounts().size() >= MAX_SIZE) {
                    throw new AccountNotFoundException("이미 5개의 계좌가 있습니다.");
                }

                if (isIn(bankNumber)) {
                    throw new AccountNotFoundException("이미 있는 계좌입니다.");
                }

                Account account = Account.builder()
                        .money(0)
                        .bankNumber(bankNumber)
                        .id(customerId)
                        .build();
                customer.addAccount(account);
            }else {
                Account account = Account.builder()
                        .money(0)
                        .bankNumber(bankNumber)
                        .id(customerId)
                        .build();
                ArrayList<Account> accounts = new ArrayList<>();
                accounts.add(account);
                customer.setAccounts(accounts);
            }
            
            displayAccounts(customer);

        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }

    private void displayAccounts(Customer customer) {
        System.out.println("-------- 현재 계좌 목록 --------");
        for (Account account : customer.getAccounts()) {
            System.out.println(account.getBankNumber());
        }
        System.out.println("------------------------------");
    }
}
