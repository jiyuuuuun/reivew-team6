package backendproject;

import day09.BankException;
import lombok.*;

import java.util.ArrayList;
import java.util.Scanner;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private int id;
    private String bankNumber;
    private int money;
    private Customer customer = new Customer();
    private Bank bank;


    public void outMoney(int money) throws BankException {
        if (this.money < money) {
            throw new BankException("잔고 부족: 현재 잔고는 " + this.money + "원입니다.");
        }
        this.money -= money;
        System.out.println("출금 완료: 현재 잔고는 " + this.money + "원입니다.");
    }

    public void inMoney() {
        System.out.println("입금을 선택하셨습니다.");
        System.out.print("id를 입력하세요: ");
        Scanner scanner = new Scanner(System.in);
        int j = scanner.nextInt();

        System.out.print("입금하고자 하는 계좌를 입력하세요: ");
        String bankNumber = scanner.next();

        System.out.print("입금하고자 하는 금액을 입력하세요: ");
        int money = scanner.nextInt();

        try {
            if (money < 0) {
                throw new InvalidTransactionException("입금 금액은 0보다 커야 합니다.");
            }

            Customer customer3 = bank.findCustomer(j);
            if (customer3 == null) {
                throw new AccountNotFoundException("없는 고객입니다.");
            }

            Account account = null;
            for (Account acc : customer3.getAccounts()) {
                if (acc.getBankNumber().equals(bankNumber)) {
                    account = acc;
                    break;
                }
            }

            if (account == null) {
                throw new AccountNotFoundException("없는 계좌 입니다.");
            } else {
                account.setMoney(account.getMoney() + money);
                System.out.println("입금이 완료되었습니다. 현재 잔액: " + account.getMoney());
            }
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }

    public int serchMoney(String id) {
        return this.money;
    }



}
