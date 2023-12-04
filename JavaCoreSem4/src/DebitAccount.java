public class DebitAccount extends Account{
    //region Методы

    //endregion

    //region Конструкторы
    DebitAccount() {
        this("RUB");
    }
    DebitAccount(String currency) {
        super(currency, "DEBIT");
        this.exceptionMessage = "Недостаточно средств на счете";
    }
    //endregion
    //region Поля
    //endregion
}
