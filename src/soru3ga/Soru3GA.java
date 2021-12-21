/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soru3ga;

/**
 *
 * @author birir
 */
public class Soru3GA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        GA ga = new GA();
        
        //5 farklı çalıştırmanın her birinde  kaç denemede buldu bunu tutan array ve
        //5 farklı çalıştırmanın her birinde kaç ms süre geçti bunu tuta array tanımlayalımm...
        
        int[] nesil = new int[5];
        double[] islem_suresi = new double[5];
        
        int say = 0;
        
        while(say<5){
            long start = System.nanoTime();
        
            int bulundugu_nesil = ga.GA();
        
            long end = System.nanoTime();
        
            double msCinsinden = (end-start)/1000000;
            System.out.println("Geçen süre: " + msCinsinden + " ms");
            nesil[say] = bulundugu_nesil;
            islem_suresi[say] = msCinsinden;
            
            System.out.println("\n\n");
            say++;
        }
        
        System.out.println("\n***GENEL TABLO***");
        
        int nesil_toplami = 0;
        double gecen_sure = 0;
        
        for(int i = 0;i<5;i++){
            
            System.out.println((i+1) + ". çalıştırmada şifrenin bulunduğu nesil: " + nesil[i]);
            System.out.println("Geçen Süre: " + islem_suresi[i] + " ms"+ "\n");
            
            nesil_toplami+=nesil[i];
            gecen_sure+=islem_suresi[i];
        }
        System.out.println("\n5 Çalıştırma İçin...");
        System.out.println("\n Şifrenin bulunduğu nesillerin ortalaması: " + (nesil_toplami / 5));
        System.out.println("\n Ortalama geçen süre: " + gecen_sure/5 + " ms");
        
        
    }
    
}
