import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

abstract class Account {
    //region Методы

    //endregion
    protected void addTransaction (String key, Transaction transaction){
        this.transactionsList.get(key).add(transaction);
    }
    protected   int getCurrentAmount(){
        //Суммируем все транзакции расходования средств
        int amountLossTransactions = 0;
        List<Transaction> temp = this.transactionsList.get("LOSS");
        for (Transaction t: temp) {
            amountLossTransactions += t.getTransactionAmount();
        }

        //Суммируем все транзакции поступления средств
        int amountGetTransactions = 0;
        temp = this.transactionsList.get("GET");
        for (Transaction t: temp) {
            amountGetTransactions += t.getTransactionAmount();
        }
        return this.initialAmount + amountGetTransactions - amountLossTransactions + this.creditLimit;
    }
    protected void setInitialAmount(int initialAmount) throws IllegalArgumentException{
        if (this.initialAmount != 0) {throw  new IllegalArgumentException("Начальная сумма была установлена ранее");}
        if (initialAmount < 0) {throw  new IllegalArgumentException("Начальная сумма не может быть отрицательной");}
        this.initialAmount = initialAmount;
    }

    public int getAccountId() {
        return accountId;
    }

    @Override
    public String toString() {
        return accountType + " ID" + accountId + " " + currency +" Current amount: " + getCurrentAmount();
    }

    //region Конструкторы
    Account(String  accountType){
        this("RUB", accountType);
    }
    Account(String currency, String  accountType){
        accountId = ++idCounter;
        this.currency = currency;
        this.accountType = accountType;
        this.transactionsList = new HashMap<>();
        this.transactionsList.put("LOSS", new ArrayList<>());//LOSS - список уменьшаюших транзакции
        this.transactionsList.put("GET", new ArrayList<>());//GET - список увеличивающих транзакций
    }
    //endregion
    //region Поля
    protected static int idCounter = 1000000;
    private int accountId;
    private int initialAmount = 0;
    protected int creditLimit = 0;
    protected String currency;
    private String accountType;
    private HashMap<String, List<Transaction>> transactionsList;
    protected String exceptionMessage = "AccountError";
    //endregion
}
