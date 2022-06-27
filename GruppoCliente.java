public class GruppoCliente extends Thread {

    private Banca banca;
    public int idGruppo;
    public int nrPersone;
    public int metallo;
    //metallo Rules:
    //0     Non ha metallo
    //1     Ha metallo
    public int azione;
    //azione Rules:
    //0     Entrare
    //1     Uscire


    //Costruttore
    public GruppoCliente(Banca b, int id, int nr, int m, int a){
        banca = b;
        idGruppo = id;
        nrPersone = nr;
        metallo = m;
        azione = a;
    }

    public int personaMetallo(int nrPersone, int metallo){
        if(metallo == 1){
            int x = (int)(Math.random()*nrPersone);
            return x;
        }
        return 0;
    }

    public void run(){
            try {
                //Per evitare che il cliente si metta subito in coda appena creato prima si manda in sleep
                Thread.sleep((int) (Math.random() * 5001 ));
                banca.mettiInCodaIngressoVersoBanca(this);
            } catch (Exception e) {
                //TODO: handle exception
            }
    }
}
