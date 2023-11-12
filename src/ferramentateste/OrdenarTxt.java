/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ferramentateste;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;


/**
 *
 * @author Luan
 */
public class OrdenarTxt  {
    private File file = null;
    private String filePath ;
    private String diretorio;
    private String fileName;
    protected ArrayList<String> txtConteudo;
    private int qtdlinhas;
    
    private int tamPalavra =0 ;
    
   
    public void  AbreOBJFile() throws IOException {
        String pastainicial = System.getProperty("user.dir");
        
        
        String path = "D:\\Arquivos_IC\\Novos_testes\\Metashape\\vaso_claro\\relatorio";
        txtConteudo = new ArrayList<>();
        JFileChooser chooser = new JFileChooser(path);
        try{
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            
            file = chooser.getSelectedFile();
            diretorio = file.getParent();
            fileName = file.getName();
            String[] palavras = fileName.split("\\.");
            
            fileName = palavras[0];
            System.out.println("file name -> "+fileName + "palavras -> "+palavras[0].toString());
            
            this.filePath = file.getAbsolutePath();
                System.out.println("path -> "+filePath);
            
            }
        }catch(NullPointerException error){
            System.out.println("error "+error);
        }
        
        BufferedReader ler=  new BufferedReader(new FileReader(file));
        int cont = 0;
        while(ler.ready()){
            String text = ler.readLine();
            
            txtConteudo.add(text);
            qtdlinhas++;
        }
        System.out.println("feito ");
        System.out.println("linha arq 1 -> "+cont);
        ler.close();
        //qtdLinhasOBJ = cont;
        
    }
    
    
    public void verArray(){
        Iterator<String> iterator = txtConteudo.iterator();
        while(iterator.hasNext()){
            
            String line = iterator.next();
            System.out.println("line -> "+line);
            System.out.println("tamanho da linha -> "+sizeTest(line));
        }
    }
    
    public int sizeTest(String line){
        int size = 0;
        
        String [] textparts = line.split("/|\\s");
        size = textparts.length;
        
        return size;
    }
    
    
    public void Ordenacao(){
        
        
        Comparator<String> coordComparator = new Comparator<String>(){
            
            @Override
            public int compare(String o1, String o2) {
                String[] parte1 = o1.split("[ /]+");
                String [] parte2 = o2.split("[ /]+");
                
                int compararLetra = parte1[0].compareTo(parte2[0]);
                if (compararLetra != 0) {
                    return compararLetra;
                }
                
                for(int i = 1 ; i < parte1.length ; i++){
                    double coordline1 = Double.parseDouble(parte1[i]);
                    double coordline2 = Double.parseDouble(parte2[i]);
                    double diferenca = Double.compare(coordline1, coordline2);

                    // Se os números forem diferentes, retornar a comparação
                    if (diferenca != 0) {
                        return  (int) diferenca;
                    }
                }
                //se iguais o retorno é zero se houver diferença retorna a diferença
                return 0;
            
            }
            
        };
         // Ordenar as coordenadas usando o Comparator personalizado
        Collections.sort(txtConteudo, coordComparator);
        gravarModificacoes(txtConteudo);
        
    }
    
    public void gravarModificacoes(ArrayList<String> textoModificado){
         try {
             BufferedWriter writer = new BufferedWriter( new FileWriter(diretorio+"\\"+fileName+"Ordenado.txt", false));
             for(String linha : textoModificado){
                 writer.write(linha+"\n");
                 
             }
             writer.flush();
             writer.close();
         
         
         } catch (IOException ex) {
             Logger.getLogger(OrdenarTxt.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
    
    
    public boolean ordenacaoMergeSort(ArrayList<String> obj){
        boolean status = false;
        int tamanho = obj.size();
        System.out.println("tamanho "+tamanho);
       
        if(obj.size() <= 1){
           return false;
        }
         int meio= obj.size() /2;
        
        ArrayList<String> esquerda = new ArrayList<>(obj.subList(0, meio));
        ArrayList<String> direita = new ArrayList<>(obj.subList(meio, obj.size()));
        
        ordenacaoMergeSort(esquerda);
        ordenacaoMergeSort(direita);
        
        return fusao(obj, esquerda, direita);
    }
    
    public boolean fusao(ArrayList<String> original, ArrayList<String> esquerda, ArrayList<String> direita){
        boolean status = false;
        int indiceEsquerda = 0 , indiceDireita  = 0 , indiceOriginal = 0;
        
        while(indiceEsquerda < esquerda.size() && indiceDireita < direita.size() ){
            
            String valorEsq = esquerda.get(indiceEsquerda);
            String []textoEsq = valorEsq.split("/|\\s");
            double c1Esq = Double.parseDouble(textoEsq[1]);
            double c2Esq = Double.parseDouble(textoEsq[2]);
           // double c3Esq = Double.parseDouble(textoEsq[3]);
            
            String valorDir = direita.get(indiceDireita);
             
            String []textoDir = valorDir.split("/|\\s");
            double c1Dir = Double.parseDouble(textoDir[1]);
            double c2Dir = Double.parseDouble(textoDir[2]);
         //   double c3Dir = Double.parseDouble(textoDir[3]);
            
            if(c1Esq < c1Dir){
                original.set(indiceOriginal++, esquerda.get(indiceEsquerda++));
                
            }
            if(c1Esq > c1Dir){
                original.set(indiceOriginal++, direita.get(indiceDireita++));
                
            }
            if(c1Esq == c1Dir){
                if(c2Esq <  c2Dir){
                    original.set(indiceOriginal++, esquerda.get(indiceEsquerda++));
                }
                if(c2Esq > c2Dir){
                    original.set(indiceOriginal++, direita.get(indiceDireita++));
                }
               if(c2Esq == c2Dir){
                   original.set(indiceOriginal++, esquerda.get(indiceEsquerda++));
                   original.set(indiceOriginal++, direita.get(indiceDireita++));
                 //    original.set(indiceOriginal++, esquerda.get(indiceEsquerda++));
                  //  if(c3Esq < c3Dir){
                  //      original.set(indiceOriginal++, esquerda.get(indiceEsquerda++));
                 //   }
                //    if(c3Esq >= c3Dir){
                //        original.set(indiceOriginal++, direita.get(indiceDireita++));
                //    }
               }
            } 
            System.out.println("valor esq ->" +valorEsq +"valor dir -> "+valorDir);
           
        }
        while (indiceEsquerda < esquerda.size()) {
            original.set(indiceOriginal++, esquerda.get(indiceEsquerda++));
             System.out.println("rodando");
           
        }
        while (indiceDireita < direita.size()) {
            original.set(indiceOriginal++, direita.get(indiceDireita++));
             System.out.println("rodando");
            
        }
        
        
        return status;
        
    }

    
    
}
