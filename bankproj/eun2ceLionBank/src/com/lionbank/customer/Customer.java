package com.lionbank.customer;

import com.lionbank.account.Account;
import com.lionbank.common.exception.CustomException.AccountNotFoundException;
import com.lionbank.common.exception.CustomException.BankOperationException;
import java.util.ArrayList;
import java.util.List;

public class Customer {

  private final String id;
  private final String name;
  private final String password;
  private final List<Account> accounts;

  public Customer(String id, String name, String password) {
    this.id = id;
    this.name = name;
    this.password = password;
    this.accounts = new ArrayList<>();
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<Account> getAccounts() {
    return accounts;
  }

  public boolean authenticate(String password) {
    return this.password.equals(password);
  }

  public void addAccount(Account account) throws BankOperationException {
    if (accounts.size() >= 5) {
      throw new BankOperationException("고객당 최대 5개의 계좌만 생성할 수 있습니다.");
    }
    accounts.add(account);
  }

  public Account findAccount(String accountNumber) throws AccountNotFoundException {
    return accounts.stream()
        .filter(account -> account.getAccountNumber().equals(accountNumber))
        .findFirst()
        .orElseThrow(() -> new AccountNotFoundException("계좌를 찾을 수 없습니다."));
  }
}