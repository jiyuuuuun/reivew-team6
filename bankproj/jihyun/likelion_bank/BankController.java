package likelion_bank;


import static likelion_bank.Account.generateAccountNumber;
import static likelion_bank.OutputMessage.printCustomerIdMessage;
import static likelion_bank.OutputMessage.printAddCustomer;
import static likelion_bank.OutputMessage.printDepositAccount;
import static likelion_bank.OutputMessage.printMessage;
import static likelion_bank.OutputMessage.printMoney;
import static likelion_bank.OutputMessage.printWithdrawAccount;
import static likelion_bank.OutputMessage.viewMoneyAccount;

import java.util.Scanner;
import likelion_bank.Exception.AccountNotFoundException;
import likelion_bank.Exception.BankOperationException;

public class BankController {
    private final int BANK_CUSTOMER_SIZE =10;

    public void mainBankLogic() {
        Bank bank = new Bank(BANK_CUSTOMER_SIZE);
        Scanner scan = new Scanner(System.in);
        int selectNumber;

        printMessage();

        while ((selectNumber = scan.nextInt()) != 6) {
            switch (selectNumber) {
                case 1:
                    Customer customer = addCustomer(bank);
                    System.out.println("고객님의 이름은 " + customer.getCustomerName() + "입니다");
                    System.out.println("고객님의 아이디는 " + customer.getCustomerId() + "입니다");
                    break;
                case 2:
                    long account = createAccount(bank);
                    if (account != -1) {
                        System.out.println("고객님의 계좌는 " + account + "입니다");
                    }
                    break;
                case 3:
                    long moneyDeposit = moneyDeposit(bank);
                    if (moneyDeposit != -1) { // 계좌 생성 성공 시에만 메시지 출력
                        System.out.println("고객님의 입금 후 잔액은 " + moneyDeposit + "입니다");
                    }
                    break;
                case 4:
                    long moneyWithDraw = moneyWithDraw(bank);
                    if (moneyWithDraw != -1) { // 출금 성공 시에만 메시지 출력
                        System.out.println("고객님의 출금 후 잔액은 " + moneyWithDraw + "입니다");
                    }
                    break;
                case 5:
                    long remainMoney = viewRemainingMoney(bank);
                    if(remainMoney != -1){
                        System.out.println("고객님의 현재 잔액은 " +  remainMoney + "입니다");
                    }
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
            }

            printMessage();
        }

        // 종료 메시지 출력 후 프로그램 종료
        System.out.println("시스템을 종료합니다.");
    }



    public long viewRemainingMoney(Bank bank) {
        long remainMoney =-1;
        try {
            Customer customer = bank.getCustomer(printCustomerIdMessage());
            remainMoney= (long) customer.getAccount(viewMoneyAccount()).getRemainMoney();
        }catch (BankOperationException | AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return remainMoney;
    }

    public long moneyDeposit(Bank bank){
    long remainMoney =-1;
    try {
            Customer customer = bank.getCustomer(printCustomerIdMessage());
            Account account = customer.getAccount(printDepositAccount());
            remainMoney= account.deposit(printMoney());
        } catch (BankOperationException | AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return remainMoney;
    }

    public long moneyWithDraw(Bank bank) {
        long remainMoney =-1;
        try {
            Customer customer = bank.getCustomer(printCustomerIdMessage());
            Account account = customer.getAccount(printWithdrawAccount());
            remainMoney = account.withDraw(printMoney());
        } catch (BankOperationException | AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return remainMoney;
    }


    public Customer addCustomer(Bank bank) {

        return bank.addCustomer(printAddCustomer(), printCustomerIdMessage());
    }

    public long createAccount(Bank bank) {
        long accountNumber;
        try {
            String customerId = printCustomerIdMessage();

            Customer customer = bank.getCustomer(customerId);
            customer.validAccountLimit();
            accountNumber = generateAccountNumber();

            customer.addAccount(new Account(accountNumber, customerId)); //고객정보에 계좌 추가

        } catch (BankOperationException e) {
            System.out.println(e.getMessage());
            accountNumber =-1;
        }

        return accountNumber;
    }

}
