public class CreditAccount extends Account{
    //region Методы
    public void setCreditLimit(int creditLimit) throws IllegalArgumentException {
        if (creditLimit < 0 ){throw  new IllegalArgumentException("Кредитный лимит не может быть отрицательным");}
        this.creditLimit = creditLimit;
    }

    @Override
    public String toString() {
        return super.toString() +" Credit Limit: "+ creditLimit ;
    }

    //endregion
    //region Конструкторы
    CreditAccount() {
        this("RUB");
    }
    CreditAccount(String currency) {
        super(currency, "CREDIT");
        this.exceptionMessage = "Превышение кредитного лимита";
    }
    //endregion
    //region Поля

    //endregion
}
