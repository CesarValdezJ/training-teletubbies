public class Main {

    public static void main(String[] args) {
        final BadStack stack = new BadStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Stack after 3 pushes: " + stack);

        System.out.println("pop(): " + stack.pop());
        System.out.println("Expected stack [1, 2]: " + stack);

        stack.add(0, 99);
        System.out.println("\nCalled add(0, 99), a method inherited from ArrayList that BadStack never meant to expose.");
        System.out.println("'Broken' stack (99 snuck in at the bottom without going through push()): " + stack);

        System.out.println("\nThe problem isn't a bug in this code: it's that 'BadStack is an ArrayList' opens the door "
                + "to ALL of ArrayList's methods (add(index,...), remove(index), set(...), sort(...)), "
                + "none of which respect the rule of a stack (only enter and exit from one end).");
    }
}