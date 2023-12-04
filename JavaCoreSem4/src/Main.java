import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<Account> accountList = new ArrayList<>();
        Random rnd = new Random();
    //создаем дебетовые счета
        accountList.add(new DebitAccount());
        accountList.add(new DebitAccount());
        accountList.add(new DebitAccount());
        accountList.add(new DebitAccount());
        accountList.add(new DebitAccount("USD"));
        accountList.add(new DebitAccount("USD"));

    //добавляем начальные остатки дебетовым счетам
        for (Account a : accountList) {
            int initialAmount = rnd.nextInt(50000);

            if (rnd.nextInt(3) == 1) {
                initialAmount = -100 * rnd.nextInt(3);//имитируем ошибку
            }
            try {
                a.setInitialAmount(initialAmount);
                System.out.println(a);

            } catch (IllegalArgumentException e) {

                System.out.println(a + "\t\tInitial Amount: " + initialAmount + " " +e.getMessage());
            }
        }

        //создаем кредитовые счета
        accountList.add(new CreditAccount());
        accountList.add(new CreditAccount());
        accountList.add(new CreditAccount());
        accountList.add(new CreditAccount());
        accountList.add(new CreditAccount("USD"));
        accountList.add(new CreditAccount("USD"));

        //устанавливаем кредитным счетам лимиты
        for (Account a : accountList) {
            if (a instanceof DebitAccount){continue;}//только для кредитных счетов
            int creditLimit = rnd.nextInt(50000);
            if (rnd.nextInt(4) == 1) {
                creditLimit = -100 * rnd.nextInt(3);//имитируем ошибку
            }
            CreditAccount b = (CreditAccount)a;
            try {
                b.setCreditLimit(creditLimit);
                System.out.println(a);

            } catch (IllegalArgumentException e) {

                System.out.println(a + "\t\tCredit limit: " + creditLimit + " " +e.getMessage());
            }
        }
        // создаем 20 транзакций
        for (int i = 0; i < 20; i++){
            Account source = null;
            Account recipient = null;
            int amount = 0;
            try {
                source = accountList.get(rnd.nextInt(12));
                recipient = accountList.get(rnd.nextInt(12));
                amount = rnd.nextInt(5000);
                System.out.println();
                System.out.println("source\t  " + source);
                System.out.println("recipient " + recipient);
                System.out.println("transaction amount " + amount);
                Transaction.createTransaction(source,recipient,amount);
                System.out.println("Транзакция проведена");
            }catch(IllegalArgumentException | InsufficientFundsException e){
                System.out.println(e.getMessage());
            }
        }
    }
}