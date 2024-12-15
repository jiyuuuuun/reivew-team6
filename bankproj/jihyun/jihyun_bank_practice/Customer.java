package jihyun_bank_practice;

import jihyun_bank_practice.Exception.AccountNotFoundException;
import jihyun_bank_practice.Exception.BankOperationException;

public class Customer {
    private String customerId;
    private Account[] accountList;
    private String customerName;
    private final int ACCOUNT_MAX_SIZE = 5;

    public Customer(String customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.accountList = new Account[ACCOUNT_MAX_SIZE]; //최대 계좌 생성 개수를 설정하낟.
    }

    //계좌가 존재하는지 확인하고, 존재한다면 계좌를 return 해줍니다.
    public Account getAccount(long accountNumber) throws AccountNotFoundException {
        try {
            accountExists();
        } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }
        for (Account account : accountList) {
            if (account != null && account.getAccountNumber() == accountNumber) {
                return account;
            }
        }

        throw new AccountNotFoundException();

    }

    public void accountExists() throws AccountNotFoundException {
        boolean hasAccount = false;
        for (Account account : accountList) {
            if (account != null) {
                hasAccount = true;
                break;
            }
        }
        if (!hasAccount) {
            throw new AccountNotFoundException("계좌가 존재하지 않습니다. 계좌를 먼저 생성해주세요");
        }
    }

    public void addAccount(Account account) {
        try {
            validAccountLimit();

        } catch (BankOperationException e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < accountList.length; i++) {
            if (accountList[i] == null) { // 빈 자리 찾기
                accountList[i] = account; // 계좌 추가
                return;
            }
        }
    }

    public void validAccountLimit() throws BankOperationException {
        int accountNumber = 0;
        for (Account account : accountList) {
            if (account != null) {
                accountNumber += 1;
            }
        }
        if (accountNumber >= ACCOUNT_MAX_SIZE) {
            throw new BankOperationException("최대 계좌를 초과했습니다. 더 이상 계좌를 생성할 수 없습니다.");
        }
    }


    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }
}
