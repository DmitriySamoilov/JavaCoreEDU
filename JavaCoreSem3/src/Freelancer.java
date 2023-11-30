public class Freelancer extends BaseEmployee {
    //region Методы
    @Override
    public int calculateSalary(){
        return (int)20.8 * 8 * this.hourlyWage;
    }

    //endregion

    //region Конструкторы
    Freelancer(){
        super();
        this.hourlyWage = 500; // default min wage rate
    }
    Freelancer(int hourlyWage){
        super();
        this.hourlyWage = hourlyWage;
    }
    Freelancer(String name, int hourlyWage) {
        super(name);
        this.hourlyWage = hourlyWage;
    }

    //endregion

    //region Поля
    int hourlyWage;

    //endregion

}
