public class Main {

    public static void main(String[] args) {
        final Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Stack tras 3 push: " + stack);

        System.out.println("pop(): " + stack.pop());
        System.out.println("Stack esperado [1, 2]: " + stack);

        System.out.println("\nStack ya no es un ArrayList, tiene un ArrayList privado (datos). "
                + "Solo expone push(), pop() y estaVacia(): no hay forma de insertar en medio ni de "
                + "romper el invariante desde afuera. El compilador ni siquiera deja intentarlo:");
        System.out.println("// stack.add(0, 99); <- no compila, add() no existe en Stack");
    }
}