public class Main {
    public static void main(String[] args) {
        Usuario usuario = new Usuario("Ana");
        usuario.agregarNotificador(new EmailNotificador());
        usuario.agregarNotificador(new SmsNotificador());

        usuario.notificar("Tu pedido ha sido confirmado.");

        System.out.println("\nUn mismo Usuario compone varios Notificadores distintos y los usa a todos sin saber cómo funciona cada uno por dentro.");
    }
}
