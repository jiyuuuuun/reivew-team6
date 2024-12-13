package BankProject;

import java.util.Scanner;

public class BankUser {
    public static void main(String[] args) {

        Customer customer = new Customer();
        Bank b=null;


        boolean start=true;

        while (start){
        System.out.println("=== 라이온 은행 시스템 ===");
        System.out.println("1. 고객 등록");
        System.out.println("2. 계좌 생성");
        System.out.println("3. 입금");
        System.out.println("4. 출금");
        System.out.println("5. 잔액 조회");
        System.out.println("6. 종료");

        Scanner scanner=new Scanner(System.in);

        int num= scanner.nextInt();





        if(num==1) {
            System.out.println("사용하실 ID를 입력해 주세요(정수)");
            int id = scanner.nextInt();
            System.out.println("사용자의 이름을 입력해 주세요");
            String name = scanner.next();
            b = new Bank(id, name);
            System.out.println("고객 등록이 완료 되었습니다");
            System.out.println("아이디:" + id + "   이름:" + name);
            System.out.println("");
            System.out.println("");
            System.out.println("");


            // 고객 등록 후 고객 객체 할당
            customer = b.searchCustomer(name);
        } else if (num==2) {

            // 계좌 생성
            if (customer == null) {
                System.out.println("먼저 고객을 등록해주세요.");
                continue;  // 고객이 등록되지 않았으면 계좌를 생성하지 않음
            }
            System.out.println("사용자의 이름을 입력해 주세요");
            String name2 = scanner.next();
            System.out.println("사용자의 아이디를 입력해 주세요");
            int id=scanner.nextInt();
            System.out.println("사용하고 싶은 계좌번호를 입력해 주세요");
            long accountNum= scanner.nextLong();
            if (customer.getName().equals(name2) && customer.getId() == id) {
                try {
                    customer.accountAdd(accountNum, 0, id);
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                System.out.println("고객 정보가 일치하지 않습니다.");
            }
            System.out.println("");
            System.out.println("");
            System.out.println("");


        } else if (num==3) {
            System.out.println("게좌번호를 입력해 주세요");
            long num2=scanner.nextLong();
            System.out.println("입금 할 금액:");
            long money=scanner.nextLong();
            for(Account account:customer.getAccounts())
                if (account.getAccountNumber()==num2){
                    try {
                        account.deposit(money);
                        System.out.println("입금이 완료되었습니다");
                        b.balanceSearch(num2);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            System.out.println("");
            System.out.println("");
            System.out.println("");

        } else if (num==4) {
            System.out.println("게좌번호를 입력해 주세요");
            long num4=scanner.nextLong();
            System.out.println("출금 할 금액:");
            long money2=scanner.nextLong();
            for(Account account:customer.getAccounts())
                if (account.getAccountNumber()==num4){
                    try {
                        account.withdraw(money2);
                        System.out.println("출금이 완료되었습니다");
                        b.balanceSearch(num4);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            System.out.println("");
            System.out.println("");
            System.out.println("");

        } else if (num==5) {
            System.out.println("게좌번호를 입력해 주세요");
            long num3=scanner.nextLong();
            for(Account account:customer.getAccounts())
                if (account.getAccountNumber()==num3){
                    try {
                        b.balanceSearch(num3);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            System.out.println("");
            System.out.println("");
            System.out.println("");

        } else if (num==6) {
            System.out.println("종료합니다.");
            start=false;
        }




        }

    }
}
