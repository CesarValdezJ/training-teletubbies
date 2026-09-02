public class Main {

    public static void main(String[] args) {
        final StackMala stack = new StackMala();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Stack tras 3 push: " + stack);

        System.out.println("pop(): " + stack.pop());
        System.out.println("Stack esperado [1, 2]: " + stack);

        stack.add(0, 99);
        System.out.println("\nSe llamo add(0, 99), un metodo heredado de ArrayList que StackMala nunca quiso exponer.");
        System.out.println("Stack 'roto' (99 se colo hasta el fondo sin pasar por push): " + stack);

        System.out.println("\nEl problema no es un bug de este codigo: es que 'StackMala es un ArrayList' abre la puerta "
                + "a TODOS los metodos de ArrayList (add(index,...), remove(index), set(...), sort(...)), "
                + "y ninguno de ellos respeta la regla de un stack (solo entra y sale por un extremo).");
    }
}