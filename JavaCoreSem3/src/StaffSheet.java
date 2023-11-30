import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//Создать класс, содержащий массив или коллекцию сотрудников (как Worker так и Freelancer), и реализовать возможность
// вывода данных с использованием foreach (подсказка: вам потребуется поработать с интерфейсом Iterable).
public class StaffSheet implements Iterable<BaseEmployee> {
    public List<BaseEmployee> sheet;

    StaffSheet() {
        this.sheet = new ArrayList<>();
    }

    public void add(BaseEmployee e) {
        this.sheet.add(e);
    }

    @Override
    public Iterator<BaseEmployee> iterator() {
        return this.sheet.iterator();
    }

}
