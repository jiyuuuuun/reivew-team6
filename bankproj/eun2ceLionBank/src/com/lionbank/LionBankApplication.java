package com.lionbank;

import com.lionbank.account.Account;
import com.lionbank.bank.Bank;
import com.lionbank.customer.Customer;
import java.util.List;
import java.util.Scanner;

public class LionBankApplication {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Bank bank = new Bank();

    while (true) {
      System.out.println("=== 라이온 은행 시스템 ===");
      System.out.println("1. 고객 등록");
      System.out.println("2. 계좌 생성");
      System.out.println("3. 입금");
      System.out.println("4. 출금");
      System.out.println("5. 잔액 조회");
      System.out.println("6. 계좌 검색");
      System.out.println("7. 종료");
      System.out.print("선택: ");

      int choice = scanner.nextInt();
      scanner.nextLine(); // 버퍼 비우기

      try {
        switch (choice) {
          case 1: // 고객 등록
            System.out.print("고객 ID: ");
            String id = scanner.nextLine();
            System.out.print("고객 이름: ");
            String name = scanner.nextLine();
            System.out.print("비밀번호: ");
            String password = scanner.nextLine();
            bank.registerCustomer(id, name, password);
            System.out.println("고객 등록이 완료되었습니다.");
            break;
          case 2: // 계좌 생성
            System.out.print("고객 ID: ");
            String customerId = scanner.nextLine();
            System.out.print("비밀번호: ");
            String inputPassword = scanner.nextLine();
            Customer customer = bank.findCustomer(customerId);
            if (!customer.authenticate(inputPassword)) {
              System.out.println("비밀번호가 틀렸습니다.");
              break;
            }
            System.out.print("계좌 번호: ");
            String accountNumber = scanner.nextLine();
            customer.addAccount(new Account(accountNumber, customerId));
            System.out.println("계좌가 생성되었습니다.");
            break;
          case 3: // 입금
            System.out.print("고객 ID: ");
            customerId = scanner.nextLine();
            System.out.print("비밀번호: ");
            inputPassword = scanner.nextLine();
            customer = bank.findCustomer(customerId);
            if (!customer.authenticate(inputPassword)) {
              System.out.println("비밀번호가 틀렸습니다.");
              break;
            }
            System.out.print("계좌 번호: ");
            accountNumber = scanner.nextLine();
            Account account = customer.findAccount(accountNumber);
            System.out.print("입금 금액: ");
            double depositAmount = scanner.nextDouble();
            account.deposit(depositAmount);
            System.out.println("입금이 완료되었습니다.");
            break;
          case 4: // 출금
            System.out.print("고객 ID: ");
            customerId = scanner.nextLine();
            System.out.print("비밀번호: ");
            inputPassword = scanner.nextLine();
            customer = bank.findCustomer(customerId);
            if (!customer.authenticate(inputPassword)) {
              System.out.println("비밀번호가 틀렸습니다.");
              break;
            }
            System.out.print("계좌 번호: ");
            accountNumber = scanner.nextLine();
            account = customer.findAccount(accountNumber);
            System.out.print("출금 금액: ");
            double withdrawAmount = scanner.nextDouble();
            account.withdraw(withdrawAmount);
            System.out.println("출금이 완료되었습니다.");
            break;
          case 5: // 잔액 조회
            System.out.print("고객 ID: ");
            customerId = scanner.nextLine();
            System.out.print("비밀번호: ");
            inputPassword = scanner.nextLine();
            customer = bank.findCustomer(customerId);
            if (!customer.authenticate(inputPassword)) {
              System.out.println("비밀번호가 틀렸습니다.");
              break;
            }
            System.out.print("계좌 번호: ");
            accountNumber = scanner.nextLine();
            account = customer.findAccount(accountNumber);
            System.out.println("잔액: " + account.getBalance());
            break;
          case 6: // 계좌 검색
            System.out.println("1. 고객 ID로 검색");
            System.out.println("2. 고객 이름으로 검색");
            System.out.print("선택: ");
            int searchChoice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            if (searchChoice == 1) {
              System.out.print("고객 ID: ");
              customerId = scanner.nextLine();
              List<Account> accounts = bank.findAccountsByCustomerId(customerId);
              System.out.println("계좌 목록:");
              accounts.forEach(acc -> System.out.println(acc.getAccountNumber()));
            } else if (searchChoice == 2) {
              System.out.print("고객 이름: ");
              name = scanner.nextLine();
              List<Account> accounts = bank.findAccountsByCustomerName(name);
              System.out.println("계좌 목록:");
              accounts.forEach(acc -> System.out.println(acc.getAccountNumber()));
            } else {
              System.out.println("올바른 선택이 아닙니다.");
            }
            break;
          case 7: // 종료
            System.out.println("프로그램을 종료합니다.");
            scanner.close();
            return;
          default:
            System.out.println("올바른 번호를 입력하세요.");
        }
      } catch (Exception e) {
        System.out.println("오류: " + e.getMessage());
      }
    }
  }
}
