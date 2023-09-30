/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Controller;

import com.mycompany.projeto_lais.Model.Dao.Materia_dao;
import com.mycompany.projeto_lais.Model.Dao.Turma_dao;
import com.mycompany.projeto_lais.Model.Materia_Model;
import com.mycompany.projeto_lais.Model.Turma_Model;
import com.mycompany.projeto_lais.View.Cadastro_Materia;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Artur
 */
public class Cadastro_Materia_Controller {

    private final Cadastro_Materia view;
    private Materia_dao dao = new Materia_dao();
    private Materia_Model materia;
    byte[] imageBytes = null;
    // instanciar objeto para o fluxo de bytes
    private FileInputStream fis;
    // váriavel global para armazenar o tamanho das imagens
    private long tamanho;
    private Turma_dao dao_turma = new Turma_dao();
    private List<Turma_Model> turma = new ArrayList<>();
 

    public Cadastro_Materia_Controller(Cadastro_Materia view) {
        this.view = view;
    }

    

    public void inserir() {
        if(!view.getjTextField1().getText().trim().isEmpty()){
        if (view.getjButton1().getText().equals("Salvar")) {
            if (imageBytes != null) {

                materia = new Materia_Model(view.getjTextField1().getText(), imageBytes);
                if (dao.insert(materia)) {
                    for (Turma_Model t : turma) {
                        dao_turma.updateMateria(t, materia);
                    }
                    view.iprimir_Na_Tela("cadastro realizado com sucesso!");
                    view.hide();
                }
            } else {
                materia = new Materia_Model( view.getjTextField1().getText());

                if (dao.insert(materia)) {
                    for (Turma_Model t : turma) {
                        dao_turma.updateMateria(t, materia);
                    }
                    view.iprimir_Na_Tela("cadastro realizado com sucesso!");
                    view.hide();

                }
                else{
                    view.iprimir_Na_Tela("Já existe uma materia com esse Nome!");
                }
            }
        } else if(view.getjButton1().getText().equals("Editar")) {
            if (imageBytes != null) {

                for (Turma_Model t : dao_turma.selectByMateria(materia.getIdMatricula())) {
                    t.removeMateria(materia);
                    dao_turma.editar(t);
                }

                if (turma.size() != 0) {
                    for (Turma_Model t : turma) {
                        t.addMateria(materia);
                        dao_turma.editar(t);
                    }
                }
                materia.setNome(view.getjTextField1().getText());
                materia.setImagem(imageBytes);
                if (dao.editar(materia)) {
                    view.iprimir_Na_Tela("Edição realizada com sucesso!");
                    view.hide();
                }
            } else {
                for (Turma_Model t : dao_turma.selectByMateria(materia.getIdMatricula())) {
                    t.removeMateria(materia);
                    dao_turma.editar(t);
                }

                if (turma.size() != 0) {
                    for (Turma_Model t : turma) {
                        t.addMateria(materia);
                        dao_turma.editar(t);
                    }
                }
                materia.setNome(view.getjTextField1().getText());
                if (dao.editar(materia)) {
                    view.iprimir_Na_Tela("Edição realizada com sucesso!");
                    view.hide();
                }
                else{
                    view.iprimir_Na_Tela("Já existe uma materia com esse Nome!");
                }

            }
        }
        else{
            if (turma.size() !=0) {
            for (Turma_Model t : dao_turma.selectByMateria(materia.getIdMatricula())) {
                    t.removeMateria(materia);
                    dao_turma.editar(t);
                }}
            if (dao.delete(materia)){
                view.iprimir_Na_Tela("Exclusão realizada com sucesso!");
                    view.hide();
            }
            
        }}
        else{
            view.iprimir_Na_Tela("O campo do Nome não pode ficar vazio!");
        }

    }

    public void atualizar() {

        if (!view.getjButton1().getText().equals("Salvar")) {
            try {
                materia = dao.findByName("" + view.getjComboBox2().getSelectedItem());
                view.getjTextField1().setText(materia.getNome());
                if (materia.getImagem() != null) {
                    ImageIcon imagemicon = new ImageIcon(materia.getImagem());
                    Image imagem = imagemicon.getImage().getScaledInstance(600, 300, Image.SCALE_REPLICATE);
                    view.getLabelImagem().setIcon(new ImageIcon(imagem));
                } else {
                    File url = new File("C:\\Users\\Artur\\Documents\\NetBeansProjects\\Projeto_Lais\\src\\main\\java\\com\\mycompany\\projeto_lais\\View\\img\\camera-dslr.png");
                    Image imagem = ImageIO.read(url);
                    view.getLabelImagem().setIcon(new ImageIcon(imagem));
                }
            } catch (IOException e) {
                System.out.println("atualizar " + e);
            }
            DefaultListModel n = new DefaultListModel();
            turma.clear();
            for (Turma_Model t : dao_turma.selectByMateria(materia.getIdMatricula())) {
                turma.add(t);
                n.addElement(t.getNome());
            }
            view.getjList1().removeAll();
            view.getjList1().setModel(n);
        }
    }
    

    public void carregarFoto() {
        // Classe que vai gerar o próprio gerador de arquivos do Java
        JFileChooser gerenciador = new JFileChooser();
        // Aqui vai haver uma modificação no nome do gerenciador 
        // para o que está entre aspas 
        gerenciador.setDialogTitle("Selecionar imagem");
        // Vai filtrar os tipos aceitáveis de arquivos que o usuário poderá escolher
        gerenciador.setFileFilter(new FileNameExtensionFilter("Arquivo de imagens (*.PNG, *.JPG, *.JPEG)"
                + "", "png", "jpg", "jpeg"));
        int resultado = gerenciador.showOpenDialog(view);
        // Ou seja, se o usuário escolheu uma imagem
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File selectedFile = gerenciador.getSelectedFile();
            String fileName = selectedFile.getName();
            String[] parts = fileName.split("\\."); // Divide o nome do arquivo pela extensão
            String formato = parts[parts.length - 1]; // Obtém a última parte, que é a extensão
            try {
                fis = new FileInputStream(gerenciador.getSelectedFile());
                tamanho = (int) gerenciador.getSelectedFile().length();
                Image imagem = ImageIO.read(gerenciador.getSelectedFile()).getScaledInstance(view.getLabelImagem().getWidth(), view.getLabelImagem().getHeight(), Image.SCALE_SMOOTH);

                // Converte a imagem para BufferedImage
                BufferedImage bufferedImage = new BufferedImage(imagem.getWidth(null), imagem.getHeight(null), BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = bufferedImage.createGraphics();
                g2d.drawImage(imagem, 0, 0, null);
                g2d.dispose();

                // Exibe a imagem
                view.getLabelImagem().setIcon(new ImageIcon(bufferedImage));
                view.getLabelImagem().updateUI();

                System.out.println(formato);
                imageBytes = convertImageToBytes(bufferedImage, formato);
            } catch (Exception e) {
                System.err.println("Carregar foto " + e);
            }
        }
    }

    public byte[] convertImageToBytes(BufferedImage bufferedImage, String format) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // Escreve a imagem no ByteArrayOutputStream com o formato especificado
            ImageIO.write(bufferedImage, format, baos);

            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removerTodas() {
        // dao_turma.
    }

    public void iniciar() {
        if (dao_turma.selectAll().size() != 0) {
            for (Materia_Model m : dao.selectAll()) {
            view.getjComboBox2().addItem(m.getNome());
        }
            for (Turma_Model t : dao_turma.selectAll()) {
                view.getjComboBox1().addItem(t.getNome());
            }
            view.getjPanel2().setVisible(true);
        }
        

    }

    public void addTurma() {
         boolean tem = true;
        for (Turma_Model m : turma) {
             
           if(m.getNome().equals(""+view.getjComboBox1().getSelectedItem())){
               tem=false;
               break;
           }
          
        }
        if(tem){
        turma.add(dao_turma.findByName("" + view.getjComboBox1().getSelectedItem()));
        DefaultListModel n = new DefaultListModel();
        for (Turma_Model t : turma) {
            n.addElement(t.getNome());
        }
        view.getjList1().removeAll();
        view.getjList1().setModel(n);
    }}

    public void exMateria() {
        turma.remove(dao_turma.findByName("" + view.getjList1().getSelectedValue()));
        DefaultListModel n = new DefaultListModel();
        for (Turma_Model t : turma) {
            n.addElement(t.getNome());
        }
        view.getjList1().removeAll();
        view.getjList1().setModel(n);
    }
}
