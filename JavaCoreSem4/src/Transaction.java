import java.time.LocalDate;


public class Transaction {
    //region Методы
    public static void createTransaction(Account sourceAccount,Account recipientAccount, int transactionAmount) throws InsufficientFundsException, IllegalArgumentException{
        if(sourceAccount == recipientAccount) {throw new IllegalArgumentException("Запрещены переводы внутри одного счета");}
        if (transactionAmount <= 0 ){throw new IllegalArgumentException("Сумма не может быть отрицательной или равной нулю");}
        if (!(sourceAccount.currency.equals(recipientAccount.currency)))
            {throw new IllegalArgumentException("Переводы возможны только между счетами с одинаковой валютой " + sourceAccount.currency + " " + recipientAccount.currency);}
        //Проверяем достаточность средств на счете
        if (sourceAccount.getCurrentAmount() < transactionAmount) {throw new InsufficientFundsException(sourceAccount.exceptionMessage);}
        //Проводим транзакцию
        Transaction transaction = new Transaction(sourceAccount, recipientAccount, transactionAmount);
        sourceAccount.addTransaction("LOSS",transaction);
        recipientAccount.addTransaction("GET",transaction);
    }
    public int getTransactionAmount() {
        return transactionAmount;
    }

    @Override
    public String toString() {
        return "Transaction "  + transactionId +" sourceAccount=" + sourceAccount.getAccountId() +
                " recipientAccount=" + recipientAccount.getAccountId() +
                " transactionAmount=" + transactionAmount +
                " " + dateOfCreation;
    }

    //endregion

    //region Конструкторы
    private Transaction(Account sourceAccount,Account recipientAccount, int transactionAmount) {
        this.transactionId = ++idCounter;
        this.sourceAccount = sourceAccount;
        this.recipientAccount = recipientAccount;
        this.transactionAmount = transactionAmount;
        this.dateOfCreation = LocalDate.now();
    }
    //endregion

    //region Поля
    protected static int idCounter = 1000000;
    private int transactionId;
    private Account sourceAccount;
    private Account recipientAccount;
    private int transactionAmount;
    private LocalDate dateOfCreation;
    //endregion
}
