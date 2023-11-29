/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Controller;

import com.mycompany.projeto_lais.Model.Dao.Aluno_Atividade_dao;
import com.mycompany.projeto_lais.Model.Dao.Aluno_Aula_dao;
import com.mycompany.projeto_lais.Model.Dao.Aluno_dao;
import com.mycompany.projeto_lais.Model.Dao.Atividade_dao;
import com.mycompany.projeto_lais.Model.Dao.Aula_dao;
import com.mycompany.projeto_lais.Model.Dao.Materia_dao;
import com.mycompany.projeto_lais.Model.Dao.Turma_Materia_dao;
import com.mycompany.projeto_lais.Model.Dao.Turma_dao;
import com.mycompany.projeto_lais.Model.Materia_Model;
import com.mycompany.projeto_lais.Model.Turma_Model;
import com.mycompany.projeto_lais.View.Cadastro_Materia;
import com.mycompany.projeto_lais.View.Menssagem_De_Confirmacao;
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
import javax.swing.JButton;
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
    private Turma_dao dao_turma;
    private List<Turma_Model> turma ;
    private List<Turma_Model> turma_antes ;
    private List<Turma_Model> excluidos;
    private Validacao validacao;
    private Turma_Materia_dao dao_tm;
    private Aluno_Atividade_dao dao_at;
    private Aluno_Aula_dao dao_al;
    private Aluno_dao dao_a;
    private Aula_dao dao_aula;
    private Atividade_dao dao_t;
    private boolean editar;
    

    public Cadastro_Materia_Controller(Cadastro_Materia view) {
        this.view = view;
        this.dao_al = new Aluno_Aula_dao();
        this.dao_at = new Aluno_Atividade_dao();
        this.dao_aula = new Aula_dao();
        this.dao_t = new Atividade_dao();
        this.dao_a = new Aluno_dao();
        this.dao_tm = new Turma_Materia_dao();
        dao_turma = new Turma_dao();
        turma = new ArrayList<>();
        turma_antes = new ArrayList<>();
        excluidos = new ArrayList<>();
        materia = new Materia_Model();
    }

    

    public void inserir() {
        editar=true;
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
                   Menssagem_De_Confirmacao m = new Menssagem_De_Confirmacao(null, true, "Já existe uma materia com esse nome!", "", "Atenção", 0);
                   m.setVisible(true);
                }
            }
        } else if(view.getjButton1().getText().equals("Editar")) {
            materia.getTurma().clear();
            if (imageBytes != null){ materia.setImagem(imageBytes);}
            if (turma_antes!=turma){
                if ( turma_antes.size()!=0 ){
                            if (turma.size()!=0){
                        for (int i= 0; i<turma_antes.size(); i++){
                            excluidos.add(turma_antes.get(i));
                            for (int a=0; a<turma.size();a++){
                                if(turma_antes.get(i)==turma.get(a)){
                                    excluidos.remove(turma_antes.get(i));
                                }
                            }
                        }}
                        else {
                            excluidos =turma_antes;
                        }}
                 if (excluidos.size()!=0){
                        Menssagem_De_Confirmacao m = new Menssagem_De_Confirmacao(null, true, "Ao desvincular uma turma de uma materia: atividades, notas", ", faltas e aulas serão apagados permanentemente", "Atenção");
                    m.setVisible(true);
                    if (m.retornar()) {
                        m = new Menssagem_De_Confirmacao(null, true, "Tem certeza que deseja desvincular a materia: "+materia.getNome(), " da materia " + excluidos.get(0).getNome() + "?", "Este é o ultimo aviso!");
                        m.setVisible(true);
                        if (m.retornar()) {
                        for (Turma_Model turma : excluidos){
                            System.out.println("entrando aqui");
                            deleteMateriaAndTM(turma,materia);
                        }
                        
                       }
                        else {
                        editar=false;}
                    }
                    else{
                        editar=false;
                    }}}
                if (editar){
                materia.setTurma(turma);
                materia.setNome(view.getjTextField1().getText());
                if (dao.editar(materia)) {
                    view.iprimir_Na_Tela("Edição realizada com sucesso!");
                    view.hide();
                }
                }
                
                else{
                    Menssagem_De_Confirmacao m = new Menssagem_De_Confirmacao(null, true, "Já existe uma materia com esse nome!", "", "Atenção", 0);
                   m.setVisible(true);
                

            }
        }
        else{
         Menssagem_De_Confirmacao m = new Menssagem_De_Confirmacao(null, true, "Ao excluir esta Matéria as atividades Aulas e notas", " serão apagados permanentemente", "Atenção");
                    m.setVisible(true);
                    if (m.retornar()) {
                        m = new Menssagem_De_Confirmacao(null, true, "Tem certeza que quer apagar todos os dados", " da Materia: " + materia.getNome() + "?", "Este é o ultimo aviso!");
                        m.setVisible(true);
                        if (m.retornar()) {
            if (deleteMateria(materia)){
                view.iprimir_Na_Tela("Exclusão realizada com sucesso!");
                    view.hide();
            }}
                    }
            
        }}
        else{
            Menssagem_De_Confirmacao m = new Menssagem_De_Confirmacao(null, true, "O campo do nome não pode ficar vazio", "", "Atenção", 0);
            m.setVisible(true);
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
                    view.getjButton6().setVisible(true);
                } else {
                    File url = new File("C:\\Users\\Artur\\Documents\\NetBeansProjects\\Projeto_Lais\\src\\main\\java\\com\\mycompany\\projeto_lais\\View\\img\\camera-dslr.png");
                    Image imagem = ImageIO.read(url);
                    view.getLabelImagem().setIcon(new ImageIcon(imagem));
                    view.getjButton6().setVisible(false);
                }
            } catch (IOException e) {
                System.out.println("atualizar " + e.getMessage());
            }
            DefaultListModel n = new DefaultListModel();
            turma.clear();
            turma_antes.clear();
            for (Turma_Model t : dao_turma.selectByMateria(materia.getIdMatricula())) {
                turma.add(t);
                n.addElement(t.getNome());
                turma_antes.add(t);
            }
            view.getjList1().removeAll();
            view.getjList1().setModel(n);
        }
        else{
            
            try {
            if  (view.getLabelImagem()!=null){
            File url = new File("C:\\Users\\Artur\\Documents\\NetBeansProjects\\Projeto_Lais\\src\\main\\java\\com\\mycompany\\projeto_lais\\View\\img\\camera-dslr.png");
            Image imagem = ImageIO.read(url);
            view.getjButton6().setVisible(false);
            view.getLabelImagem().setIcon(new ImageIcon(imagem));
                }
                else{
            view.getjButton6().setVisible(true);
                }
            } catch (Exception e) {
            }

 
            
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
                view.getjButton6().setVisible(true);
                view.getLabelImagem().updateUI();
                System.out.println(formato);
                imageBytes = convertImageToBytes(bufferedImage, formato);
            } catch (Exception e) {
                System.err.println("Carregar foto " + e);
                view.getjButton6().setVisible(false);
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
        view.getjTextField1().setDocument( new Validacao(40));
        if (dao_turma.selectAll().size() != 0) {
            for (Materia_Model m : dao.selectAll()) {
            view.getjComboBox2().addItem(m.getNome());
        }
            for (Turma_Model t : dao_turma.selectAll()) {
                view.getjComboBox1().addItem(t.getNome());
            }
            view.getjPanel2().setVisible(true);
        }
        atualizar();

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

    public void retirarImagem() {
        
    materia.setImagem(null);
    imageBytes = null;
    atualizar();
    }
    
    public void deleteMateriaAndTM(Turma_Model model, Materia_Model materia){
     dao_al.deleteforTurmaAndMateria(model, materia);
        dao_at.deleteforTurmaAndMateria(model, materia);
        dao_aula.deleteforTurmaAndMateria(model, materia);
        dao_t.deleteforTurmaAndMateria(model, materia);
        dao_tm.deleteforTurmaAndMteria(model, materia);
        
        
    }
    
    public boolean deleteMateria(Materia_Model materia){
dao_al.deleteforMateria(materia);
    dao_at.deleteforMateria(materia);
    dao_aula.deleteforMateria(materia);
    dao_t.deleteforMateria(materia);
    dao_tm.deleteforMateria(materia);
    return dao.delete(materia);
}
    
}
