/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Controller;

import com.mycompany.projeto_lais.View.Gerar_PDF;
import com.mycompany.projeto_lais.View.Menssagem_De_Confirmacao;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Artur
 */
public class Gerar_PDF_Controller {
    private final Gerar_PDF view;
    private String path;
    private String professor;
    private String nomeFile;
    private String titulo;
    private boolean valido;
    private String cidade;
    private String estado;
    public Gerar_PDF_Controller(Gerar_PDF view) {
        this.view = view;
    }

    public String getPath() {
        return path;
    }

    public String getProfessor() {
        return professor;
    }

    public String getNomeFile() {
        return nomeFile;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isValido() {
        return valido;
    }

    public Gerar_PDF getView() {
        return view;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }
    

    public void gerar() {
       path = view.getjTextField1().getText();
       nomeFile = view.getjTextField2().getText()+".pdf";
       professor = view.getjTextField3().getText();
       titulo = view.getjTextField4().getText();
       cidade = view.getjTextField5().getText();
       estado = ""+view.getjComboBox1().getSelectedItem();
       File file = new File(path+"\\"+nomeFile);
        if (path.trim().isEmpty() || nomeFile.trim().isEmpty() || professor.trim().isEmpty() || titulo.trim().isEmpty() || cidade.trim().isEmpty()){
            Menssagem_De_Confirmacao m = new Menssagem_De_Confirmacao(null, true, "Nenhum campo pode ficar vazio!", "", "Atenção", 0);
            m.setVisible(true);
            valido = false;
        }
        else if ( file.isFile()){
            Menssagem_De_Confirmacao m = new Menssagem_De_Confirmacao(null, true, "O arquivo: ' "+nomeFile+" ' ja existe!", "", "Atenção", 0);
            m.setVisible(true);
            valido = false;
        }
        else {
            view.hide();
            valido = true;
        }
    }

    public void escolerPast() {
        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
       int resultado = j.showDialog(view, "Selecionar");
        if (resultado == JFileChooser.APPROVE_OPTION){
            path = j.getSelectedFile().getPath();
        }
        else{
            path= null;
        }
        view.getjTextField1().setText(path);
    }

   
    
    
}
