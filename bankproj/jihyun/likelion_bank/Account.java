package likelion_bank;


import java.util.HashSet;
import java.util.Random;
import likelion_bank.Exception.InvalidTransactionException;

public class Account {
    private final long accountNumber; //계좌번호 변경은 안되기 때문에 final 으로
    private String customerId; //계좌 주인을 알기 위한 고객 아이디
    private long remainMoney; //남은 돈

    //계좌 중복을 확인하기 위해서 Set으로 설정했다.
    private static HashSet<Long> duplicateAccountNumber = new HashSet<>();


    public Account(long accountNumber, String customerId) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.remainMoney = 0;
    }

    public long deposit(long money) { //입금
        try {
            validMoney(money);
        } catch (InvalidTransactionException e) {
            System.out.println(e.getMessage());
        }
        remainMoney += money;
        return remainMoney;
    }

    public long withDraw(long money) { //출금
        try {
            validMoney(money);
            validWithDrawMoney(money);
        } catch (InvalidTransactionException e) {
            System.out.println(e.getMessage());
        }
        return remainMoney-=money;
    }


    private void validMoney(long money) throws InvalidTransactionException {
        if (money <=0) {
            throw new InvalidTransactionException("0이하의 값은 입금할 수 없습니다.");
        }
    }

    private void validWithDrawMoney(long money) throws InvalidTransactionException {
        if(remainMoney - money < 0 ){
            throw new InvalidTransactionException("잔액보다 큰 금액은 출력할 수 없습니다.");
        }
    }

    public static long generateAccountNumber() { //계좌생성
        long accountNumber;
        do {
            accountNumber = generateRandomNumber();
        } while (duplicateAccountNumber.contains(accountNumber)); // 중복 체크

        duplicateAccountNumber.add(accountNumber);
        return accountNumber;
    }

    public static long generateRandomNumber() {// 13자리 난수 생성
        Random random = new Random();
        return (long) (Math.pow(10, 12) + Math.abs(random.nextLong()) % (long) Math.pow(10, 12));
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public long getRemainMoney() {
        return remainMoney;
    }






}
