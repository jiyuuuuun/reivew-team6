package backendproject;

import day09.BankException;
import lombok.Builder;

import java.util.ArrayList;
import java.util.Scanner;

@Builder
public class BankTask {
    public static void main(String[] args) {
        ArrayList<Customer> customers = new ArrayList<>();
        Bank bank = new Bank(customers);
        Customer customer = new Customer();
        Scanner scanner = new Scanner(System.in);
        Account account = Account.builder()
                .bank(bank)
                .build();

        int i;

        do {
            System.out.println("1. 고객 등록");
            System.out.println("2. 계좌 생성");
            System.out.println("3. 입금");
            System.out.println("4. 출금");
            System.out.println("5. 잔액 조회");
            System.out.println("6. 종료");
            i = scanner.nextInt();

            // 충분히 큰 수
            int customerId = 100000000;

            switch (i) {
                case 1:
                    bank.saveCustomer();
                    break;

                case 2:
                    customer.createAccount(bank);
                    break;

                case 3:
                    account.inMoney();
                    break;

                case 4:
                    System.out.println("출금을 선택하셨습니다.");
                    System.out.print("id를 입력하세요: ");
                    int k = scanner.nextInt();

                    System.out.print("출금하고자 하는 계좌를 입력하세요: ");
                    String bankNumber2 = scanner.next();

                    System.out.print("출금하고자 하는 금액을 입력하세요: ");
                    int money2 = scanner.nextInt();

                    try {
                        if (money2 < 0) {
                            throw new InvalidTransactionException("출금 금액은 0보다 커야 합니다.");
                        }

                        Customer customer3 = bank.findCustomer(k);
                        if (customer3 == null) {
                            System.out.println("없는 고객입니다.");
                            break;
                        }

                        account = null;
                        for (Account acc : customer3.getAccounts()) {
                            if (acc.getBankNumber().equals(bankNumber2)) {
                                account = acc;
                                break;
                            }
                        }

                        if (account == null) {
                            System.out.println("해당 계좌가 존재하지 않습니다.");
                        } else {
                            if (account.getMoney() < money2) {
                                throw new InvalidTransactionException("잔액이 부족합니다. 현재 잔액: " + account.getMoney());
                            }

                            account.setMoney(account.getMoney() - money2);
                            System.out.println("출금이 완료되었습니다. 현재 잔액: " + account.getMoney());
                        }

                    } catch (Exception e) {
                        System.out.println("오류가 발생했습니다: " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;
//
//                case 5:
//                    System.out.println("잔액 조회를 선택하셨습니다.");
//                    // 잔액 조회 로직 추가
//                    break;
//
//                case 6:
//                    System.out.println("시스템을 종료합니다.");
//                    break;
//
//                default:
//                    System.out.println("잘못된 입력입니다. 다시 시도하세요.");
            }
        } while (i != 6); // 6을 입력할 때까지 반복
    }

}
