package com.lionbank;

import com.lionbank.core.Bank;
import com.lionbank.db.DatabaseManager;
import java.util.Scanner;

public class Eun2ceLionBankApplication {
  public static void main(String[] args) {
    try {
      DatabaseManager dbManager = new DatabaseManager("jdbc:mysql://localhost:3306/lionbank_db?useSSL=false&serverTimezone=UTC",
          "root", "root");
      Bank bank = new Bank(dbManager);

      Scanner scanner = new Scanner(System.in);
      while (true) {
        System.out.println("=== 라이온 은행 시스템 ===");
        System.out.println("1. 고객 등록");
        System.out.println("2. 계좌 생성");
        System.out.println("3. 입금");
        System.out.println("4. 출금");
        System.out.println("5. 잔액 조회");
        System.out.println("6. 종료");
        System.out.print("선택: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
          case 1:
            System.out.print("고객 ID: ");
            String customerId = scanner.nextLine();
            System.out.print("고객 이름: ");
            String name = scanner.nextLine();
            bank.registerCustomer(customerId, name);
            System.out.println("고객이 등록되었습니다.");
            break;
          case 2:
            System.out.print("고객 ID: ");
            customerId = scanner.nextLine();
            System.out.print("계좌 번호: ");
            String accountId = scanner.nextLine();
            bank.createAccount(customerId, accountId);
            System.out.println("계좌가 생성되었습니다.");
            break;
          case 3:
            System.out.print("계좌 번호: ");
            accountId = scanner.nextLine();
            System.out.print("입금 금액: ");
            double depositAmount = scanner.nextDouble();
            bank.deposit(accountId, depositAmount);
            System.out.println("입금이 완료되었습니다.");
            break;
          case 4:
            System.out.print("계좌 번호: ");
            accountId = scanner.nextLine();
            System.out.print("출금 금액: ");
            double withdrawAmount = scanner.nextDouble();
            bank.withdraw(accountId, withdrawAmount);
            System.out.println("출금이 완료되었습니다.");
            break;
          case 5:
            System.out.print("계좌 번호: ");
            accountId = scanner.nextLine();
            double balance = bank.checkBalance(accountId);
            System.out.printf("현재 잔액: %.2f\n", balance);
            break;
          case 6:
            System.out.println("프로그램을 종료합니다.");
            return;
          default:
            System.out.println("잘못된 입력입니다.");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}