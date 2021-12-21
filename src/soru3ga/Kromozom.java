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
public class Kromozom {
    
    public char[] gen_array;
    
    //dizinin doldurulacağı constructor
    public void gen_ekle(char gen,int indeks){
        
        gen_array[indeks] = gen;
    }
    
    public Kromozom(){
        gen_array = new char[19];
    }
}
