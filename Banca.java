import java.util.LinkedList;

public class Banca {
    final static int CAPIENZA_CABINA = 4;
    private LinkedList<GruppoCliente> codaIngresso = new LinkedList<GruppoCliente>();
    private LinkedList<GruppoCliente> codaUscita = new LinkedList<GruppoCliente>();
    GruppoCliente gruppoEntrante;
    GruppoCliente gruppoUscente;

    public synchronized void mettiInCodaIngressoVersoBanca(GruppoCliente gruppoCliente) {
        System.out.println("Il Gruppo " + gruppoCliente.idGruppo + " Formato Da " + gruppoCliente.nrPersone
                + " Persone Si Mette In Coda per Entrare in banca");
        codaIngresso.add(gruppoCliente);
        notifyAll();
    }

    public synchronized void faiEntrareInBanca(Cabina cabina) {
        while (codaIngresso.size() == 0) {
            System.out.println("Nessun Gruppo Di Clienti In Attesa Di Entrare In Banca!");
            try {
                wait();
            } catch (Exception e) {
                // TODO
            }
        }

        gruppoEntrante = codaIngresso.getFirst();

        if (cabina.apertura == 1) { //Cabina Aperta Verso Esterno
            if(gruppoEntrante.metallo == 0){ //Nessuno Ha Metallo
                if ( CAPIENZA_CABINA >= gruppoEntrante.nrPersone) { //Capienza >= nrPersone Gruppo Entrante
                    System.out.println("Il Gruppo " + gruppoEntrante.idGruppo + " É Entrato!");
                    codaIngresso.removeFirst();
                    try{
                        Thread.sleep(2500);
                    }catch (Exception e){
                        //TODO
                    }  
                    System.out.println("Il Gruppo " + gruppoEntrante.idGruppo + " Si Mette In Coda Per Uscire!");
                    cabina.apertura = 0; //Come Se Schiacciassero Il Bottone Per Far Girare la Cabina
                    codaUscita.add(gruppoEntrante);  
                    notifyAll();
                } else {
                    System.out.println("Gruppo Troppo Numeroso Per Entrare!");
                }
            } else {
                System.out.println("Gruppo " + gruppoEntrante.idGruppo + " Entra In Cabina, Ma Qualcuno Ha Metalli E Quindi Deve Uscire Deve Uscire!");
                codaIngresso.removeFirst();
            }

        } else {
            System.out.println("Cabina Rivolta Verso L'Interno! Bisogna Aspettare!");
        }
    }

    public synchronized void faiUscireDallaBanca(Cabina cabina) {
        while (codaUscita.size() == 0) {
            System.out.println("Nessun Gruppo Di Clienti In Attesa Di Uscire Dalla Banca!");
            cabina.apertura = 1;
            try {
                wait();
            } catch (Exception e) {
                // TODO
            }
        }

        gruppoUscente = codaUscita.getFirst();

        if (cabina.apertura == 0) { //Cabina Aperta Verso Esterno
            //Non Bisogna Controllare Metalli Perchè Se Sono Dentro Non Ne Hanno
            if ( CAPIENZA_CABINA >= gruppoEntrante.nrPersone) { //Capienza >= nrPersone Gruppo Entrante
                System.out.println("Il Gruppo " + gruppoEntrante.idGruppo + " É Uscito!");
                cabina.apertura = 1;
                codaUscita.removeFirst();
            } else {
                System.out.println("Gruppo Troppo Numeroso Per Entrare!");
            }
        } else {
            System.out.println("Cabina Rivolta Verso L'Esterno! Bisogna Aspettare!");
        }
    }

}