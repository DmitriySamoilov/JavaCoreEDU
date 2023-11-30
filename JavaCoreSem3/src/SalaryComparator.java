import java.util.Comparator;

public class SalaryComparator implements Comparator<BaseEmployee> {

    @Override
    public int compare(BaseEmployee o1, BaseEmployee o2) {
        return Integer.compare(o1.calculateSalary(),o2.calculateSalary());
    }
}
