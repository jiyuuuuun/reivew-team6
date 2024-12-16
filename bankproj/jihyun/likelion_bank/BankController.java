package likelion_bank;


import static likelion_bank.Account.generateAccountNumber;
import static likelion_bank.OutputMessage.printAddCustomer;
import static likelion_bank.OutputMessage.printCustomerIdMessage;
import static likelion_bank.OutputMessage.printDepositAccount;
import static likelion_bank.OutputMessage.printMessage;
import static likelion_bank.OutputMessage.printMoney;
import static likelion_bank.OutputMessage.printWithdrawAccount;
import static likelion_bank.OutputMessage.viewMoneyAccount;

import java.util.InputMismatchException;
import java.util.Scanner;
import likelion_bank.Exception.AccountNotFoundException;
import likelion_bank.Exception.BankOperationException;

public class BankController {
    private final int BANK_CUSTOMER_SIZE = 10;
    private final int EXIT_NUMBER = 7;

    public void mainBankLogic() {
        Bank bank = new Bank(BANK_CUSTOMER_SIZE);
        Scanner scan = new Scanner(System.in);
        int selectNumber = 0;

        printMessage();

        while (true) {
            try{
                selectNumber = scan.nextInt();
                if(selectNumber==EXIT_NUMBER){
                    System.out.println("시스템을 종료합니다.");
                    break;
                }
            }catch (InputMismatchException e){
                System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
                scan.nextLine();// 입력 버퍼 초기화
                printMessage();
                continue;
            }
           selectMenuOption(bank,selectNumber);

            printMessage();
        }

    }


    private void selectMenuOption(Bank bank, int selectNumber){
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
                if (moneyDeposit != -1) {
                    System.out.println("고객님의 입금 후 잔액은 " + moneyDeposit + "입니다");
                }
                break;
            case 4:
                long moneyWithDraw = moneyWithDraw(bank);
                if (moneyWithDraw != -1) {
                    System.out.println("고객님의 출금 후 잔액은 " + moneyWithDraw + "입니다");
                }
                break;
            case 5:
                long remainMoney = viewRemainingMoney(bank);
                if(remainMoney != -1){
                    System.out.println("고객님의 현재 잔액은 " +  remainMoney + "입니다");
                }
                break;
            case 6:
                customerAccountList(bank);
                break;
            default:
                System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
        }
    }



     private long viewRemainingMoney(Bank bank) {
        long remainMoney =-1;
        try {
            remainMoney= bank.getCustomer(printCustomerIdMessage())
                    .getAccount(viewMoneyAccount())
                    .getRemainMoney();
        }catch (BankOperationException | AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return remainMoney;

    }

    private long moneyDeposit(Bank bank){
    long remainMoney =-1;
    try {
         remainMoney = bank.getCustomer(printCustomerIdMessage())
                 .getAccount(printDepositAccount())
                 .deposit(printMoney());

        } catch (BankOperationException | AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return remainMoney;
    }

    private long moneyWithDraw(Bank bank) {
        long remainMoney =-1;
        try {
            remainMoney = bank.getCustomer(printCustomerIdMessage())
                    .getAccount(printWithdrawAccount())
                    .withDraw(printMoney());
        } catch (BankOperationException | AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return remainMoney;
    }


    private Customer addCustomer(Bank bank) {
        return bank.addCustomer(printAddCustomer(), printCustomerIdMessage());
    }


    private long createAccount(Bank bank) {
        long accountNumber;
        try {
            String customerId = printCustomerIdMessage(); //customerId 두번사용

            Customer customer = bank.getCustomer(customerId); //사용 1번
            customer.validAccountLimit();
            accountNumber = generateAccountNumber();

            customer.addAccount(new Account(accountNumber, customerId)); //고객정보에 계좌 추가 사용 2번

        } catch (BankOperationException e) {
            System.out.println(e.getMessage());
            accountNumber =-1;
        }

        return accountNumber;
    }

    private void customerAccountList(Bank bank){
        try {
            Customer customer = bank.getCustomer(printCustomerIdMessage());
            Account[] list = bank.getCustomerAccounts(customer);

            boolean hasAccounts = false; //이런것도 따로 메서드로 빼야할지
            for (Account account : list) {
                if (account != null) {
                    System.out.println("계좌번호 : " + account.getAccountNumber());
                    hasAccounts = true;  // 계좌가 하나라도 있으면 true로 설정
                }
            }

            if (!hasAccounts) {
                System.out.println("계좌를 먼저 생성해주세요. 계좌가 존재하지 않습니다");
            }
        }catch (BankOperationException e){
            System.out.println(e.getMessage());
        }
    }
}
