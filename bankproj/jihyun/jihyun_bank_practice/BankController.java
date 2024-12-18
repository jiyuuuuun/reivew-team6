package jihyun_bank_practice;


import static likelion_bank.Account.generateAccountNumber;
import static likelion_bank.OutputMessage.printAddCustomer;
import static likelion_bank.OutputMessage.printCustomerIdMessage;
import static likelion_bank.OutputMessage.printDepositAccount;
import static likelion_bank.OutputMessage.printMessage;
import static likelion_bank.OutputMessage.printMoney;
import static likelion_bank.OutputMessage.printWithdrawAccount;
import static likelion_bank.OutputMessage.viewMoneyAccount;

import java.util.Scanner;
import jihyun_bank_practice.BankOption.MenuOption;
import jihyun_bank_practice.Exception.AccountNotFoundException;
import jihyun_bank_practice.Exception.BankOperationException;

public class BankController {
    private final int BANK_CUSTOMER_SIZE =10;

    public void mainBankLogic() {
        Bank bank = new Bank(BANK_CUSTOMER_SIZE);
        Scanner scan = new Scanner(System.in);

        printMessage();
        while (true) {
            int selectNumber = scan.nextInt();

            if (selectNumber == MenuOption.EXIT.getOption()) {
                MenuOption.EXIT.executeMenuOption(this, bank);
                break;
            }

            try {
                MenuOption menuOption = MenuOption.fromOption(selectNumber);
                menuOption.executeMenuOption(this, bank);
            } catch (BankOperationException e) {
                System.out.println(e.getMessage());
            }

            printMessage();
        }

    }

    public long viewRemainingMoney(Bank bank) {
        long remainMoney =-1;
        try {
            Customer customer = bank.getCustomer(printCustomerIdMessage());
            remainMoney= customer.getAccount(viewMoneyAccount()).getRemainMoney();
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

            customer.validAccountLimit(); //최대 계좌 생성 확인

            accountNumber = generateAccountNumber();

            customer.addAccount(new Account(accountNumber, customerId)); //고객정보에 계좌 추가

        } catch (BankOperationException e) {
            System.out.println(e.getMessage());
            accountNumber =-1;
        }

        return accountNumber;
    }

}
