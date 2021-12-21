/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soru3ga;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

/**
 *
 * @author birir
 */
public class GA {
    
    //şifre uzunluğunu tanımladık
    public int pass_lenght = 19;
    //popülasyon büyüküğünü tanımlayalım...
    public int population_size = 400; //!!!!KROMOZOM POPÜLASYON BÜYÜKLÜĞÜNÜN DEĞİŞTİRİLMESİ İÇİN BU DEĞERİN DEĞİŞTİRİLMESİ YETERLİDİR.
    
    public char[] pass_dict = {'y','a','p','Z','e','k','ö','n','t','m','l','r','i','Y'};
    //elitist kromozom sayısını ve crossover yapılacak kromozom sayısını yazdık...
    public int num_parents = population_size/2;
    public int elitism_size = 2;
    //şifreyi tanımlayalım...
    public String password = "YapayZekaYöntemleri";
    public char[] pass_array = new char[password.length()];
    //Popülasyonun tutulacağı diziyi tanımlayalım...
    public Kromozom[] population = new Kromozom[population_size];
    
    public int[][] fitness_scores;
    
    
    //Şifreyi diziye atayalım..
    public void sifreyiDiziyeAta(){
        
        for(int i = 0;i<pass_lenght;i++){
            
            pass_array[i] = password.charAt(i);
        }
    }
    
    //popülasyonu oluşturan fonksiyon..
    public void populasyonOlustur(){
        
        //pass dict ten seçeceğimiz için onun uzunluğu kadar range imiz olacak.
        int range = pass_dict.length;
        int min = 0;
        
        Random r = new Random();
        for(int i = 0;i<population_size;i++){
            
            Kromozom kromozom = new Kromozom();
            for(int j = 0;j<pass_lenght;j++){
                
                //0 - 18 arasında bir sayı üretmek istiyoruz. 18 ve 0 dahil olacak.
                int sayi = r.nextInt(range) + min;
                
                //Rastgele alınan sayı değeriyle kromozomu oluşturduk...
                kromozom.gen_ekle(pass_dict[sayi],j);
            }
            population[i] = kromozom;
        }
    }
    
    
    public void fitnessScoreBul(){
        
        char[] oankigenarrayi;
        int eslesme_skoru = 0;
        int say = 0;
        fitness_scores = new int[population_size][2];
        
        for(Kromozom kromozom : population){
            
            oankigenarrayi = kromozom.gen_array;
            for(int i= 0;i<pass_lenght;i++){
                
                if(pass_array[i] == oankigenarrayi[i]){
                    eslesme_skoru++;
                }
                
            }
            
            //Böylece eşleşme sayısını ve hangi kromozomun bu eşleşme sayısına sahip oldğunu bulduk...
            fitness_scores[say][0] = eslesme_skoru;
            fitness_scores[say][1] = say;
            eslesme_skoru = 0;
            say++;
        }
    }
    
    
    public Kromozom[] parentlariSec(int nesil_sayisi){
        
        Kromozom[] parentlar = new Kromozom[num_parents];
        //AYnı fitness skora sahip olanlar kopyalanmasın diye bu işlem yapılıyor.
        ArrayList indeksDegerleri = new ArrayList();
        
        int[] sirali_skorlar = new int[population_size];
        
        //fitness skorlaırnın olduğu arrayi sıraladık en iyi sonuca sahip 9 kromozomu alıp çağrazlayıp 
        //childlar üreteceğiz...
        int say = 0;
        for(int[] i : fitness_scores){
            
            sirali_skorlar[say] = i[0];
            say++;
        }
        say = 0;
        //Listeyi sıralayalım...
        Arrays.sort(sirali_skorlar);
        int indeks = 0;
        //Şimdi sondan geriye gelip o değere sahip olan fonksiyonu parentlar array ine ekleyelim...
        for(int i = population_size-1;i>=num_parents;i--){
            
            //Şimdi bu değeri bir de fitness skorlar içinde aratalım ve hangi kromozom olduğunu hbulalım.
            for(int j = 0;j<fitness_scores.length;j++){
                if(sirali_skorlar[i] == fitness_scores[j][0]){
                    if(!indeksDegerleri.contains(fitness_scores[j][1])){
                        indeks = fitness_scores[j][1];
                        indeksDegerleri.add(indeks);
                        break;
                    }
                }
            }
            
            
            Kromozom kromozom = new Kromozom();
            for(int k = 0 ;k<pass_lenght;k++){
               
                kromozom.gen_ekle(population[indeks].gen_array[k], k);
            }
            
            parentlar[say] = kromozom;
            say++;
        }
        
        return parentlar;
    }
    
    
    //Crossover ile ikinci nesli oluşturalım...
    public void nesilOlustur(int nesil_sayisi){
                 
        Kromozom kromozom1;
        Kromozom kromozom2;
        
        //Kromozomu parçalamak için random bir sayi seçelim
        Random r = new Random();
        
        int range = 19;
        int min= 0;
        int belirtec= 0;
        
        Kromozom[] parentlar = parentlariSec(nesil_sayisi);
        population = new Kromozom[population_size];
        //parents listesinden ilk iki kromozomu alalım...
        for( int i = 0;i<population_size/2-2;i++){
            
            //Eğer i == 0 sa en iyi iki değeri aynen geçiriyourz.
            if(i==0){
                population[0] = parentlar[0];
                population[1] = parentlar[1];
                population[2] = parentlar[2];
                population[3] = parentlar[3];
                belirtec+=4;
                
                kromozom1 = parentlar[4];
                kromozom2 = parentlar[5];
                
            }else{
                kromozom1 = parentlar[i];
                kromozom2 = parentlar[i+1];
            }
           

            int rastgele_sayi = r.nextInt(range) + min;
            
            //Yeni kromozomu oluşturmak için iki tane yeni Kromozom nesnesi üretelim...
            Kromozom child1;
            Kromozom child2;

            child1 = crossover(kromozom1, kromozom2, rastgele_sayi);
            child2 = crossover(kromozom2, kromozom1, rastgele_sayi);

            //Yeni nesil oluştuğu için kromozom listesini güncelliyoruz.
            population[belirtec] = child1;
            population[belirtec+1] = child2;
            belirtec+=2;
            
            
        }
        
        
        
        
    }
    
    //Kromozomlar arasında çaprazlama yapan fonksiyon...
    public Kromozom crossover(Kromozom kromozom1,Kromozom kromozom2,int random_sayi){
        
        Kromozom child = new Kromozom();
        for(int i = 0;i<=random_sayi;i++){
            child.gen_ekle(kromozom1.gen_array[i], i);
        }
        
        for(int j = random_sayi+1;j<pass_lenght;j++){
            child.gen_ekle(kromozom2.gen_array[j], j);
        }
        
        
        return child;
    }
    
    //popülasyon içinde 2 farklı yerde mutasyon yapıcaz...
    public void mutasyon(){
        
        Random r = new Random();
        
        int random1 = r.nextInt(19);
        int random2 = r.nextInt(19);
        int random3 = r.nextInt(19);
        int random4 = r.nextInt(19);
        int random5 = r.nextInt(19);
        int random6 = r.nextInt(19);
        int random7 = r.nextInt(14);
        int random8 = r.nextInt(14);
        int random9 = r.nextInt(14);
        
        population[random1].gen_array[random4] = pass_dict[random7];
        population[random2].gen_array[random5] = pass_dict[random8];
        population[random3].gen_array[random6] = pass_dict[random9];
    }
    
    //Tüm kodu birleştiren yapı...
    public int GA(){
        
        int nesil_sayisi=1;
        sifreyiDiziyeAta();
        populasyonOlustur();
        while (true){
            
            mutasyon();
            fitnessScoreBul();
            nesilOlustur(nesil_sayisi);
            
            
            //Her nesilde şifreyee en yakın olan değeri buluyor. 
            System.out.print((nesil_sayisi)+". nesil için şifreye en yakın değer: ");
            
            for(int j = 0;j<pass_lenght;j++){
                    System.out.print(population[0].gen_array[j] + " ");
            }
            
            System.out.println("");
            
            int sorgu = sifreKarsilastir();
            
            if(sorgu==1){
                break;
            }else{
                nesil_sayisi++;
            }
            
        }
        
        System.out.print("Şifre Bulundu : ");
        for(int j = 0;j<pass_lenght;j++){
            System.out.print(population[0].gen_array[j] + " ");
        }
        System.out.println(nesil_sayisi + ". Nesilde");
        
        return nesil_sayisi;
    }
    
    private int sifreKarsilastir(){
        
        char[] nesildeki_en_iyi_kromozom = population[0].gen_array;
        
        int eslesme = 0;
        
        for(int i = 0;i<pass_lenght;i++){
            if(pass_array[i] == nesildeki_en_iyi_kromozom[i]){
                eslesme++;
            }
        }
        
        if(eslesme == 19){
            return 1;
        }
        
        return 0;
    }
}
