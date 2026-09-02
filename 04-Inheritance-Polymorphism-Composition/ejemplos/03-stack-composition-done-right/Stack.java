import java.util.ArrayList;
import java.util.List;

public class Stack {

    private final List<Integer> data = new ArrayList<>();

    public void push(final int value) {
        data.add(value);
    }

    public int pop() {
        if (data.isEmpty()) {
            throw new IllegalStateException("El stack esta vacio.");
        }
        return data.remove(data.size() - 1);
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public String toString() {
        return data.toString();
    }
}