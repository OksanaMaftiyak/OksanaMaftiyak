package omaftiyak.javacourse.lab2.model;

public class ColumnOrdering {

    private String column;
    private Order order;

    public ColumnOrdering(String column, Order order) {
        this.column = column;
        this.order = order;
    }

    public String getColumn() {
        return column;
    }

    public Order getOrder() {
        return order;
    }

}
