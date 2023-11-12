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
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author Luan
 */
public class OpenOBJFile {
    
    private File file = null;
    private String filePath ;
    private String diretorio;
    private String fileName;
    protected ArrayList<String> conteudoFile;
    
    private ArrayList<String> vertices;
    private ArrayList<String> vertText;
    private ArrayList<String> vertNormais;
    private ArrayList<String> faces;
    private int qtdLinhasOBJ=0;
    private int qtdLinhasVert=0;
    private int qtdLinhasVertText=0;
    private int qtdLinhasVertNormal=0;
    private int qtdLinhasFaces=0;
    
    
    private int qtdlinhas;
    
    
    public OpenOBJFile(){
        try {
            AbreOBJFile();
        } catch (IOException ex) {
            Logger.getLogger(OpenOBJFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void  AbreOBJFile() throws IOException {
        String pastainicial = System.getProperty("user.dir");
        
        
        String path = "D:\\Arquivos_IC\\Novos_testes\\Metashape\\vaso_claro\\relatorio";
        conteudoFile = new ArrayList<>();
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
            
            conteudoFile.add(text);
            qtdLinhasOBJ++;
        }
        System.out.println("feito ");
        System.out.println("linha arq 1 -> "+cont);
        ler.close();
        //qtdLinhasOBJ = cont;
        
    }
    
    public void exportElements() throws IOException{
       // vertices = new ArrayList<>();
        vertText = new ArrayList<>();
        vertNormais= new ArrayList<>();
        faces = new ArrayList<>();
        Iterator<String> iterator = conteudoFile.iterator();
        
        BufferedWriter writerVert = null;
        BufferedWriter writerVertText = null;
        BufferedWriter writerVertNormal = null;
        BufferedWriter writerFaces = null;
        try{
            writerVert = new BufferedWriter(new FileWriter(diretorio+"\\vertExported.txt", false));
            writerVertText = new BufferedWriter(new FileWriter(diretorio+"\\verticeTexturizadosExported.txt", false));
            writerVertNormal = new BufferedWriter(new FileWriter(diretorio+"\\verticeNormaisExported.txt", false));
            writerFaces = new BufferedWriter(new FileWriter(diretorio+"\\FacesExported.txt", false));
            
        }catch(IOException ex){
            Logger.getLogger(OpenOBJFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while(iterator.hasNext()){
            String linha = iterator.next();
            String [] text = linha.split(" ");
            if(text[0].equals("v")){
                writerVert.write(linha+"\n");
                qtdLinhasVert++;
                
            }
            if(text[0].equals("vt")){
                writerVertText.write(linha+"\n");
                qtdLinhasVertText++;
            }
            if(text[0].equals("vn")){
                writerVertNormal.write(linha+"\n");
                qtdLinhasVertNormal++;
            }
            if(text[0].equals("f")){
                writerFaces.write(linha+"\n");
                qtdLinhasFaces++;
            }
        }
        
        writerVert.flush();
        writerVert.close();
        writerVertText.flush();
        writerVertText.close();
        writerVertNormal.flush();
        writerVertNormal.close();
        writerFaces.flush();
        writerFaces.close();
        System.out.println("qtd linhas obj -> "+qtdLinhasOBJ);
        System.out.println("qtd linhas vertices -> "+qtdLinhasVert);
        System.out.println("qtd linhas vertices texturizados -> "+qtdLinhasVertText);
        System.out.println("qtd linhas vertices normais -> "+qtdLinhasVertNormal);
        System.out.println("qtd linhas faces -> "+qtdLinhasFaces);
        
    }
    
}
