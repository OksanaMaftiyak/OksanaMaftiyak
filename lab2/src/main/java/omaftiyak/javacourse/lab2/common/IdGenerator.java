package omaftiyak.javacourse.lab2.common;

public class IdGenerator {

    private int nextId = 1;

    public int nextId() {
        return nextId++;
    }

}
