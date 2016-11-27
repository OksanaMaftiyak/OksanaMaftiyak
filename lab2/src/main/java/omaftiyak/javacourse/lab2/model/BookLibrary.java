package omaftiyak.javacourse.lab2.model;


public class BookLibrary {

    private Long id;

    private String name;

    public BookLibrary() {
    }

    public BookLibrary(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("id: ").append(id);
        sb.append(", name: ").append(name);
        sb.append("]");
        return sb.toString();
    }

}
