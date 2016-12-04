package omaftiyak.javacourse.lab2.model;

import java.util.List;

public class OrderingInfo {

    private List<ColumnOrdering> columnOrderings;

    public OrderingInfo(List<ColumnOrdering> columnOrderings) {
        this.columnOrderings = columnOrderings;
    }

    public List<ColumnOrdering> getColumnOrderings() {
        return columnOrderings;
    }

}