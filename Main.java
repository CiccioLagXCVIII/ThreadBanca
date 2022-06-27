//9 Luglio 2020
public class Main{
    public static void main(String[] args){
        final int numClienti = 7;

        Banca banca = new Banca();
        Cabina cabina = new Cabina(banca, 1);
        cabina.start();

        GruppoCliente gruppoCliente[] = new GruppoCliente[numClienti];

        for(int i=0; i<numClienti; i++){
            int x = (int)(Math.random()*8) + 1; //Numero Di Persone Max 8
            gruppoCliente[i] = new GruppoCliente(banca, i, x, x%2, 0);
            gruppoCliente[i].start();
        }

    }
}