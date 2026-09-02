public class Main {

    public static void main(String[] args) {
        final Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Stack after 3 pushes: " + stack);

        System.out.println("pop(): " + stack.pop());
        System.out.println("Expected stack [1, 2]: " + stack);

        System.out.println("\nStack is no longer an ArrayList, it has a private ArrayList (data). "
                + "It only exposes push(), pop() and isEmpty(): there is no way to insert in the middle or "
                + "break the invariant from outside. The compiler doesn't even let you try:");
        System.out.println("// stack.add(0, 99); <- does not compile, add() does not exist in Stack");
    }
}