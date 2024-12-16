package com.lionbank.bank;

import com.lionbank.account.Account;
import com.lionbank.common.exception.CustomException.BankOperationException;
import com.lionbank.customer.Customer;
import java.util.ArrayList;
import java.util.List;

public class Bank {

  private final List<Customer> customers;

  public Bank() {
    customers = new ArrayList<>();
  }

  public void registerCustomer(String id, String name, String password)
      throws BankOperationException {
    if (customers.stream().anyMatch(customer -> customer.getId().equals(id))) {
      throw new BankOperationException("중복된 ID 입니다. 다른 ID를 사용하세요.");
    }
    customers.add(new Customer(id, name, password));
  }

  public Customer findCustomer(String id) throws BankOperationException {
    return customers.stream()
        .filter(customer -> customer.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new BankOperationException("고객을 찾을 수 없습니다."));
  }

  public List<Account> findAccountsByCustomerId(String id) throws BankOperationException {
    Customer customer = findCustomer(id);
    return customer.getAccounts();
  }

  public List<Account> findAccountsByCustomerName(String name) throws BankOperationException {
    return customers.stream()
        .filter(customer -> customer.getName().equals(name))
        .flatMap(customer -> customer.getAccounts().stream())
        .toList();
  }
}