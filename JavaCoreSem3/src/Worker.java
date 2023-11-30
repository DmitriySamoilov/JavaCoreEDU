public class Worker extends BaseEmployee {
    //region Методы
    @Override
    public int calculateSalary(){
        return this.fixSalary;
    }

    //endregion

    //region Конструкторы
    Worker(){
        super();
        this.fixSalary = 50000; // default min salary
    }
    Worker(int salary){
        super();
        this.fixSalary = salary;
    }
    Worker(String name, int salary) {
        super(name);
        this.fixSalary = salary;
    }

    //endregion

    //region Поля
    int fixSalary;
    //endregion

}
