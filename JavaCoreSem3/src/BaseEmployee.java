abstract class BaseEmployee implements Comparable<BaseEmployee> {
    //region Методы
    public abstract int calculateSalary();

    @Override
    public int compareTo(BaseEmployee o) {
        int result = name.compareTo(o.name);
        if (o.calculateSalary() == this.calculateSalary()) {
            return result;
        }
        return Integer.compare(o.calculateSalary(), this.calculateSalary());
    }

    @Override
    public String toString() {
        return this.getClass() + ": " +"ID"+ this.id + " " + this.name + " Salary: " + this.calculateSalary();
    }

    public String getName() {
        return name;
    }
    //endregion

    //region Конструкторы
    BaseEmployee() {
        this("NoName");
    }

    BaseEmployee(String name) {
        counter++;
        this.id = counter;
        this.name = name;
    }
    //endregion

    //region Поля
    protected static int counter;
    protected int id;
    public String name;
    //endregion


}
