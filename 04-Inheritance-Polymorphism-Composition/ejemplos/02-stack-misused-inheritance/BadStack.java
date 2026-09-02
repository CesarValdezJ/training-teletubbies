import java.util.ArrayList;


public class BadStack extends ArrayList<Integer> {

    public void push(final int value) {
        add(value);
    }

    public int pop() {
        return remove(size() - 1);
    }
}