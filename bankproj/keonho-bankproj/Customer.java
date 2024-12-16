package backendproject;

import lombok.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    private String name;
    private int id;
    private ArrayList<Account> accounts = new ArrayList<>();
    private static final int MAX_SIZE = 5; // 최대 계좌 수
    private Scanner scanner = new Scanner(System.in);

    public void addAccount(Account a) {
        accounts.add(a);
    }

    public Boolean isIn(String bankNumber) throws AccountNotFoundException {
        boolean found = false;

        for (Account account : accounts) {
            System.out.println(account.getBankNumber());
            if (bankNumber.equals(account.getBankNumber())) {
                found = true;
                return found;
            }
        }

        return found;
    }

    public void createAccount(String bankNumber) {
        try {

            // 빈 계좌인지 체크
            if(getAccounts() != null) {

                // 게좌가 5개 이상인지 체크
                if (getAccounts().size() >= MAX_SIZE) {
                    throw new AccountNotFoundException("이미 5개의 계좌가 있습니다.");
                }

                // 중복된 계좌인지 체크
                if (isIn(bankNumber)) {
                    throw new AccountNotFoundException("이미 있는 계좌입니다.");
                }

                // 계좌 생성
                Account account = Account.builder()
                        .balance(0)
                        .bankNumber(bankNumber)
                        .id(getId())
                        .build();

                // 기존 계좌들에 추가
                addAccount(account);
            }else {

                // 계좌 생성
                Account account = Account.builder()
                        .balance(0)
                        .bankNumber(bankNumber)
                        .id(getId())
                        .build();

                // 새로운 계좌 목록 생성
                ArrayList<Account> accounts = new ArrayList<>();
                accounts.add(account);

                // 새로운 계좌 목록 추가
                setAccounts(accounts);
            }

            // 계좌 목록 출력
            displayAccounts();
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }

    public Account findAccount(String banknumber) throws AccountNotFoundException {
        Account result = null;

        for (Account account : accounts) {
            if (banknumber.equals(account.getBankNumber())) {
                result = account;
                return result;
            }
        }

        throw new AccountNotFoundException("해당 계좌가 존재하지 않습니다.");

    }

    // 계좌 목록 출력을 위한 display
    private void displayAccounts() {
        System.out.println("-------- 현재 계좌 목록 --------");
        for (Account account : getAccounts()) {
            System.out.println(account.getBankNumber());
        }
        System.out.println("------------------------------");
    }
}
