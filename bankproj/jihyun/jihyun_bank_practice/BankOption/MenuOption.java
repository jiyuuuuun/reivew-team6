package jihyun_bank_practice.BankOption;

import jihyun_bank_practice.Bank;
import jihyun_bank_practice.BankController;
import jihyun_bank_practice.Customer;
import jihyun_bank_practice.Exception.BankOperationException;

public enum MenuOption {
    ADD_CUSTOMER(1) {
        @Override
        public void executeMenuOption(BankController controller, Bank bank) {
            Customer customer = controller.addCustomer(bank);
            System.out.println("고객님의 이름은 " + customer.getCustomerName() + "입니다");
            System.out.println("고객님의 아이디는 " + customer.getCustomerId() + "입니다");
        }
    },
    CREATE_ACCOUNT(2) {
        @Override
        public void executeMenuOption(BankController controller, Bank bank) {
            long account = controller.createAccount(bank);
            if (account != -1) {
                System.out.println("고객님의 계좌는 " + account + "입니다");
            }
        }
    },
    DEPOSIT(3) {
        @Override
        public void executeMenuOption(BankController controller, Bank bank) {
            long moneyDeposit = controller.moneyDeposit(bank);
            if (moneyDeposit != -1) {
                System.out.println("고객님의 입금 후 잔액은 " + moneyDeposit + "입니다");
            }
        }
    },
    WITHDRAW(4) {
        @Override
        public void executeMenuOption(BankController controller, Bank bank) {
            long moneyWithdraw = controller.moneyWithDraw(bank);
            if (moneyWithdraw != -1) {
                System.out.println("고객님의 출금 후 잔액은 " + moneyWithdraw + "입니다");
            }
        }
    },
    VIEW_BALANCE(5) {
        @Override
        public void executeMenuOption(BankController controller, Bank bank) {
            long remainMoney = controller.viewRemainingMoney(bank);
            if (remainMoney != -1) {
                System.out.println("고객님의 현재 잔액은 " + remainMoney + "입니다");
            }
        }
    },
    EXIT(6) {
        @Override
        public void executeMenuOption(BankController controller, Bank bank) {
            System.out.println("시스템을 종료합니다.");
        }
    };

    private final int option;

    MenuOption(int option) {
        this.option = option;
    }

    public int getOption() {
        return option;
    }

    public static MenuOption fromOption(int option)throws BankOperationException {

        for(MenuOption menuOption : values()){
            if(menuOption.getOption() == option){
                return menuOption;
            }
        }
        throw new BankOperationException("잘못된 번호를 입력하셨습니다. 다시 선택하세요");
    }

    public abstract void executeMenuOption(BankController controller, Bank bank);
    //enum에서 추상 메서드가 없으면 상수별로 서로 다른 동작을 지정할 방법이 없다
}
