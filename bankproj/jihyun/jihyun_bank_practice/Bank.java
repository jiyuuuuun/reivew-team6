package jihyun_bank_practice;

import java.util.HashSet;
import jihyun_bank_practice.Exception.BankOperationException;

public class Bank {
    private Customer[] customersInformation; //고객 정보를 저장한다.
    private HashSet<String> customerIdList; //중복 아이디를 검사한다.

    public Bank(int customerSize) {
        this.customersInformation = new Customer[customerSize]; //최대 저장할 수 있는 고객 정보는 은행을 만들때 초기화한다.
        this. customerIdList = new HashSet<>();
    }


    public Customer addCustomer(String customerName, String customerId){
        try{
            duplicateCustomer(customerId);//중복을 검사한다. 아이디가 중복되었다면 다시 입력할 수 있도록 한다.
            validCustomerSize(); //고객을 추가할 자리가 있는지 확인한다.
        }catch (BankOperationException e) {
            System.out.println(e.getMessage());
        }

       return addCustomerInformation(customerId,customerName);
    }

    public Customer getCustomer(String customerId) throws BankOperationException {
        return validCustomer(customerId);
    }


    private Customer addCustomerInformation(String customerId, String customerName){
        Customer customer = new Customer(customerId,customerName);
        for(int i=0;i<customersInformation.length;i++){
            if (customersInformation[i] == null) { // 빈 자리 찾기
                customersInformation[i] = customer;//사람 추가
                customerIdList.add(customerId);
                return customer;
            }
        }
        return null;
    }




    //고객을 추가할 자리가 있는지 확인한다.
    private void validCustomerSize() throws BankOperationException {

        boolean hasSpace = false;
        for (Customer customer : customersInformation) {
            if (customer == null) {
                hasSpace = true;
                break;
            }
        }
        if (!hasSpace) {
            throw new BankOperationException("고객 저장 공간이 가득 찼습니다. 더 이상 고객을 추가할 수 없습니다.");
        }
    }



    //만약 존재하지 않는 아이디라면 예외가 발생한다.
    private Customer validCustomer(String customerId)throws BankOperationException {
        for (Customer customer : customersInformation) {
            if (customer != null && customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        throw new BankOperationException("존재하지 않는 아이디입니다.");

    }

    //아이디 중복 확인
    private void duplicateCustomer(String customerId) throws BankOperationException {
        //고객 중복을 확인한다.
        if(customerIdList.contains(customerId)){
            throw new BankOperationException("이미 존재하는 아이디 입니다. ");
        }

    }

}
