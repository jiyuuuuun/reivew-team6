package com.lionbank.common.exception;

public class CustomException {

  public static class InvalidTransactionException extends Exception {

    public InvalidTransactionException(String message) {
      super(message);
    }
  }

  public static class AccountNotFoundException extends Exception {

    public AccountNotFoundException(String message) {
      super(message);
    }
  }

  public static class BankOperationException extends Exception {

    public BankOperationException(String message) {
      super(message);
    }
  }

}