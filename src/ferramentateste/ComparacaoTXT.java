/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ferramentateste;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFileChooser;

/**
 *
 * @author Luan
 */
public class ComparacaoTXT {
    
    private File fileOne = null;
    private String filePathOne ;
    private String diretorioOne;
    private String fileNameOne;
    protected ArrayList<String> txtContentOne;
    int qtdLinesOne=0;
    private File fileTwo = null;
    private String filePathTwo ;
    private String diretorioTwo;
    private String fileNameTwo;
    protected ArrayList<String> txtContentTwo;
    int qtdLinesTwo = 0;
    
    int compFirstTest ;
    
    
    public void selectFileOne() throws IOException{
        String pastainicial = System.getProperty("user.dir");
        
        
        String path = "D:\\Arquivos_IC\\Novos_testes\\exports_obj_recap";
        txtContentOne = new ArrayList<>();
        JFileChooser chooser = new JFileChooser(path);
        try{
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            
            fileOne = chooser.getSelectedFile();
            diretorioOne = fileOne.getParent();
            fileNameOne = fileOne.getName();
            String[] palavras = fileNameOne.split("\\.");
            
            fileNameOne = palavras[0];
            System.out.println("file name -> "+fileNameOne + "palavras -> "+palavras[0].toString());
            
            this.filePathOne = fileOne.getAbsolutePath();
                System.out.println("path -> "+filePathOne);
            
            }
        }catch(NullPointerException error){
            System.out.println("error "+error);
        }
        
        BufferedReader ler=  new BufferedReader(new FileReader(fileOne));
        int cont = 0;
        while(ler.ready()){
            String text = ler.readLine();
            
            txtContentOne.add(text);
            qtdLinesOne++;
        }
        System.out.println("feito ");
        System.out.println("qtd Lines one -> "+qtdLinesOne);
        ler.close();
        //qtdLinhasOBJ = cont;
    }
    
    public void selectFileTwo() throws IOException{
        String pastainicial = System.getProperty("user.dir");
        
        
        String path = "D:\\Arquivos_IC\\Novos_testes\\exports_obj_recap";
        txtContentTwo = new ArrayList<>();
        JFileChooser chooser = new JFileChooser(path);
        try{
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            
            fileTwo = chooser.getSelectedFile();
            diretorioTwo = fileTwo.getParent();
            fileNameTwo = fileTwo.getName();
            String[] palavras = fileNameTwo.split("\\.");
            
            fileNameTwo = palavras[0];
            System.out.println("file name -> "+fileNameTwo + "palavras -> "+palavras[0].toString());
            
            this.filePathTwo = fileTwo.getAbsolutePath();
                System.out.println("path -> "+filePathTwo);
            
            }
        }catch(NullPointerException error){
            System.out.println("error "+error);
        }
        
        BufferedReader ler=  new BufferedReader(new FileReader(fileOne));
        int cont = 0;
        while(ler.ready()){
            String text = ler.readLine();
            
            txtContentTwo.add(text);
            qtdLinesTwo++;
        }
        System.out.println("feito ");
        System.out.println("qtd lines two -> "+qtdLinesTwo);
        ler.close();
        
    }
    
    
    public int firstTest(){
        
        Iterator<String> iterator1 = txtContentOne.iterator();
        Iterator<String> iterator2 = txtContentTwo.iterator();
        
        String line1 = iterator1.next();
        
        String line2 = iterator2.next();
        String []textline1 = line1.split("/|\\s");
        String []textline2 = line2.split("/|\\s");
        int comp=0;
        for (int i = 1; i < textline1.length; i++) {
            double numero1 = Double.parseDouble(textline1[i]);
            double numero2 = Double.parseDouble(textline2[i]);
            compFirstTest = Double.compare(numero1, numero2);

            // Se os números forem diferentes, retornar a comparação
            if (compFirstTest != 0) {
                break;
                
            }
        }
        return compFirstTest;
        
    }
    
    public int testEquals(String textOne , String textTwo){
        String []textline1 = textOne.split("/|\\s");
        String []textline2 = textTwo.split("/|\\s");
        int comp=0;
        for (int i = 1; i < textline1.length; i++) {
            double numero1 = Double.parseDouble(textline1[i]);
            double numero2 = Double.parseDouble(textline2[i]);
            System.out.println("numero 1 -> "+numero1);
            System.out.println("numero 2 -> "+numero2);
            
            comp = Double.compare(numero1, numero2);
            System.out.println("comprando string "+comp);
            // Se os números forem diferentes, retornar a comparação
            if (comp != 0) {
                break;
                
            }
        }
        return comp;
        
    }
    
    
    public ArrayList<String> equalsLine(){
        ArrayList<String> listEquals = new ArrayList<>();
        int countEquals =0 ;
        int countLinesOne = 0, countLinesTwo =0 ;
        double semelhanca =0;
        
        System.out.println("first teste "+firstTest());
        
            Iterator<String> iteratorOne = txtContentOne.iterator();
            while(iteratorOne.hasNext()){
                String textOne = iteratorOne.next();
                countLinesOne++;
                Iterator<String> iteratorTwo = txtContentTwo.iterator();
                System.out.println("arq 1 "+countLinesOne);
                while(iteratorTwo.hasNext()){
                    String textTwo = iteratorTwo.next();
                    countLinesTwo++;
                    System.out.println("arq 2 "+countLinesTwo);        
                    int comp = testEquals(textOne, textTwo);
                    if(comp < 0){
                        iteratorOne.remove();
                        break;
                    }
                    if(comp == 0){
                        iteratorOne.remove();
                        iteratorTwo.remove();
                        listEquals.add(textOne);
                        countEquals++;
                        break;
                    }
                }
            }
            
            semelhanca = (countEquals / countLinesTwo ) *100;
            System.out.println("semelhanca -> "+semelhanca);
        /*
        else{
            
            Iterator<String> iteratorOne = txtContentTwo.iterator();
            while(iteratorOne.hasNext()){
                String textOne = iteratorOne.next();
                countLinesOne++;
                System.out.println("arq 1 "+countLinesOne);
                Iterator<String> iteratorTwo = txtContentOne.iterator();
                while(iteratorTwo.hasNext()){
                    String textTwo = iteratorTwo.next();
                    countLinesTwo++;
                    System.out.println("arq 2 "+countLinesTwo);
                    int comp = testEquals(textOne, textTwo);
                    if(comp < 0){
                        break;
                    }
                    if(comp == 0){
                        listEquals.add(textOne);
                        countEquals++;
                    }
                }
            }
        } */
        return listEquals;
        
    }
    
}
