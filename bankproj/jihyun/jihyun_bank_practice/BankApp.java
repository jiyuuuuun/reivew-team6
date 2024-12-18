package jihyun_bank_practice;

public class BankApp {
    public static void main(String[] args) {
        //최대 저장할 수 있는 고객 정보는 은행을 만들때 초기화한다.
        BankController bankController = new BankController();
        bankController.mainBankLogic();

    }
}
