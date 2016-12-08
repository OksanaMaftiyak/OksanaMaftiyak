package omaftiyak.javacourse.lab2.common;

public class IdGenerator {

    private long nextId = 1;

    public long nextId() {
        return nextId++;
    }

}
