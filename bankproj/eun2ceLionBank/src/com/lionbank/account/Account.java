package com.lionbank.account;

import com.lionbank.common.exception.CustomException.InvalidTransactionException;

public class Account {

  private final String accountNumber;
  private final String ownerId;
  private double balance;

  public Account(String accountNumber, String ownerId) {
    this.accountNumber = accountNumber;
    this.ownerId = ownerId;
    this.balance = 0.0;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public double getBalance() {
    return balance;
  }

  public void deposit(double amount) throws InvalidTransactionException {
    if (amount <= 0) {
      throw new InvalidTransactionException("입금 금액은 0보다 커야 합니다.");
    }
    balance += amount;
  }

  public void withdraw(double amount) throws InvalidTransactionException {
    if (amount <= 0) {
      throw new InvalidTransactionException("출금 금액은 0보다 커야 합니다.");
    }
    if (amount > balance) {
      throw new InvalidTransactionException("잔액이 부족합니다.");
    }
    balance -= amount;
  }
}
