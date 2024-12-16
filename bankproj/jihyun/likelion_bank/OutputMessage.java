package likelion_bank;

import java.util.Scanner;

public class OutputMessage {

    //private static Scanner scan = new Scanner(System.in);

    public static void printMessage(){
        System.out.println("== 라이온 은행 시스템 ==");
        System.out.println("1. 고객 등록 ");
        System.out.println("2. 계좌 생성 ");
        System.out.println("3. 입금 ");
        System.out.println("4. 출금 ");
        System.out.println("5. 잔액 조회 ");
        System.out.println("6. 계좌 조회 ");
        System.out.println("7. 종료 ");
    }

    public static String printAddCustomer(){
        Scanner scan = new Scanner(System.in);
        System.out.println("고객을 등록하겠습니다. 이름을 입력해주세요");
        return scan.nextLine();
    }
    public static String printCustomerIdMessage(){
        Scanner scan = new Scanner(System.in);
        System.out.println("고객 아이디를 입력해주세요");
        return scan.nextLine();
    }

    public static long printDepositAccount(){
        Scanner scan = new Scanner(System.in);
        System.out.println("입금할 계좌를 입력해주세요");
        return scan.nextLong();
    }

    public static long printWithdrawAccount(){
        Scanner scan = new Scanner(System.in);
        System.out.println("출금할 계좌를 입력해주세요");
        return scan.nextLong();
    }

    public static int printMoney(){
        Scanner scan = new Scanner(System.in);
        System.out.println("금액을 입력해주세요");
        return scan.nextInt();
    }

    public static long viewMoneyAccount(){
        Scanner scan = new Scanner(System.in);
        System.out.println("조회하려는 계좌를 입력해주세요");
        return scan.nextLong();
    }

}
