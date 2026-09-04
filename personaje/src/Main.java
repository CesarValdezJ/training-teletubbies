

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    Espada espada = new Espada();
    Arco arco = new Arco();

    Personaje personaje = new Personaje(espada);

    personaje.atacar();

    personaje.cambiarArma(arco);

    personaje.atacar();
}
