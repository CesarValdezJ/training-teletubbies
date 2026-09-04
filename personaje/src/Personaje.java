public class Personaje {
    private Arma arma;

    public Personaje(Arma arma){
        this.arma = arma;
    }
    public void atacar(){
        arma.atacar();
    }
    public void cambiarArma(Arma arma){
        this.arma = arma;
    }
}
