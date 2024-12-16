package backendproject;

import day09.BankException;
import lombok.Builder;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

@Builder
public class BankTask {

    public static int insertIdDisplay(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("고객의 id를 입력해주세요 : ");
        int id;

        while (true) {
            try {
                id = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("정수를 입력해야 합니다. 다시 시도하세요.");
                scanner.next();
            }
        }
        return id;
    }

    public static void main(String[] args) throws AccountNotFoundException, BankOperationException, BankException {
        ArrayList<Customer> customers = new ArrayList<>();
        Bank bank = new Bank(customers);
        Customer customer = new Customer();
        Scanner scanner = new Scanner(System.in);
        Account account = new Account();

        // 입력받을 계좌번호
        String bankNumber;

        // 입출금 변수
        int money;

        // customerId를 담을 변수
        int customerId;

        // 현재 Customer 객체를 담을 변수
        Customer nowCustomer;

        int i;


        do {
            System.out.println("1. 고객 등록");
            System.out.println("2. 계좌 생성");
            System.out.println("3. 입금");
            System.out.println("4. 출금");
            System.out.println("5. 잔액 조회");
            System.out.println("6. 종료");

            while (true) {
                try {
                    i = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("정수를 입력해야 합니다. 다시 시도하세요.");
                    scanner.next();
                }
            }


            switch (i) {
                case 1:
                    System.out.println("고객 등록을 선택하셨습니다.");

                    System.out.print("고객의 이름을 입력하세요 : ");
                    String name = scanner.next();

                    customerId = BankTask.insertIdDisplay();

                    // 고객 생성 로직
                    bank.saveCustomer(name, customerId);
                    break;

                case 2:
                    System.out.println("계좌 생성을 선택하셨습니다.");

                    customerId = BankTask.insertIdDisplay();

                    System.out.print("생성하고자 하는 계좌를 입력하세요: ");
                    bankNumber = scanner.next();

                    // 고객 찾기 로직
                    nowCustomer = bank.findCustomer(customerId);

                    // 계좌 생성 로직
                    nowCustomer.createAccount(bankNumber);
                    break;

                case 3:
                    System.out.println("입금을 선택하셨습니다.");

                    customerId = BankTask.insertIdDisplay();

                    // 고객 찾기 로직
                    nowCustomer = bank.findCustomer(customerId);

                    System.out.print("입금하고자 하는 계좌를 입력하세요: ");
                    bankNumber = scanner.next();

                    System.out.print("입금하고자 하는 금액을 입력하세요: ");
                    money = scanner.nextInt();

                    // 계좌 찾기
                    account = nowCustomer.findAccount(bankNumber);

                    // 계좌에 입금하기
                    account.inMoney(nowCustomer,bankNumber,money);
                    break;

                case 4:
                    System.out.println("출금을 선택하셨습니다.");

                    customerId = BankTask.insertIdDisplay();

                    // 고객 찾기 로직
                    nowCustomer = bank.findCustomer(customerId);

                    System.out.print("출금하고자 하는 계좌를 입력하세요: ");
                    bankNumber = scanner.next();

                    System.out.print("출금하고자 하는 금액을 입력하세요: ");
                    money = scanner.nextInt();

                    // 계좌 찾기
                    account = nowCustomer.findAccount(bankNumber);

                    account.outMoney(nowCustomer, bankNumber, money);
                    break;

                case 5:
                    System.out.println("잔액 조회를 선택하셨습니다.");

                    customerId = BankTask.insertIdDisplay();

                    // 고객 찾기 로직
                    nowCustomer = bank.findCustomer(customerId);

                    System.out.print("조회하고자 하는 계좌를 입력하세요: ");
                    bankNumber = scanner.next();

                    // 계좌 찾기
                    account = nowCustomer.findAccount(bankNumber);

                    // 잔액 조회 로직
                    account.serchAccount(nowCustomer, bankNumber);

                    break;

                case 6:
                    System.out.println("시스템을 종료합니다.");
                    break;

                default:
                    System.out.println("잘못된 입력입니다. 다시 시도하세요.");
            }
        } while (i != 6); // 6을 입력할 때까지 반복


    }

}
