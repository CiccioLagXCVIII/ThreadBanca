public class Cabina extends Thread {

    private Banca banca;
    public int apertura;
    //apertura Rules:
    //0     Verso Interno
    //1     Verso Esterno

    //Costruttore
    public Cabina(Banca b, int a){
        banca = b;
        apertura = a;
    }

    public void run(){
        while(true) {
            if(apertura == 1){
                banca.faiEntrareInBanca(this);
            } else if (apertura == 0){
                banca.faiUscireDallaBanca(this);
            }
        }
    }
}
