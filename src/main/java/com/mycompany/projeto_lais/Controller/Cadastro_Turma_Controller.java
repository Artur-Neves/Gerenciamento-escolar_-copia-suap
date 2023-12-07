/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Controller;

import com.mycompany.projeto_lais.Enums.Series_Enum;
import com.mycompany.projeto_lais.Model.Materia_Model;
import com.mycompany.projeto_lais.Model.Turma_Model;
import com.mycompany.projeto_lais.View.Cadastro_Turma;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import com.mycompany.projeto_lais.Model.Dao.*;
import com.mycompany.projeto_lais.View.Menssagem_De_Confirmacao;

/**
 *
 * @author Artur
 */
public class Cadastro_Turma_Controller {

    private final Cadastro_Turma view;
    private Turma_Model model;
    List<Materia_Model> materia = new ArrayList<>();
    List<Materia_Model> materia_anterior = new ArrayList<>();
    private Turma_dao dao;
    private Materia_dao dao_materia;
    Materia_Model selecionado;
    private Validacao validacao;
    private Turma_Materia_dao dao_tm;
    private Aluno_Atividade_dao dao_at;
    private Aluno_Aula_dao dao_al;
    private Aluno_dao dao_a;
    private Aula_dao dao_aula;
    private Atividade_dao dao_t;
    private boolean editar;

    public Cadastro_Turma_Controller(Cadastro_Turma view, Materia_Model materia) {
        this.view = view;
        this.dao = new Turma_dao();
        this.dao_materia = new Materia_dao();
        this.selecionado = materia;
        this.dao_al = new Aluno_Aula_dao();
        this.dao_at = new Aluno_Atividade_dao();
        this.dao_aula = new Aula_dao();
        this.dao_t = new Atividade_dao();
        this.dao_a = new Aluno_dao();
        this.dao_tm = new Turma_Materia_dao();
        
    }

    public void atualizar() {
        System.out.println("Atualizou a turma e ta de boa");
        if (!view.getjButton1().getText().equals("Salvar")) {
            try {
                model = dao.findByName("" + view.getjComboBox3().getSelectedItem());
                view.getjTextField1().setText(model.getNome());
                view.getjComboBox1().setSelectedItem(model.getSerie());
                DefaultListModel n = new DefaultListModel();
                materia.clear();
                  for (Materia_Model m : model.getMateria()) {
                    materia_anterior.add(dao_materia.findByName(m.getNome()));
                    materia.add(dao_materia.findByName(m.getNome()));
                  }
               
                 
                for (Materia_Model m : materia){
                    n.addElement(m.getNome());
                }
                view.getjList1().removeAll();
                view.getjList1().setModel(n);
            } catch (Exception e) {
                System.out.println("atualizar Turma " + e);
            }
        }
    }

    public void inserir() {
     
        editar=true;
        if (!view.getjTextField1().getText().trim().isEmpty()) {
            if (view.getjComboBox1().getSelectedIndex() == -1 || view.getjComboBox1().getSelectedIndex() == -1 || view.getjTextField1().getText().trim().isEmpty()) {
            } else {
                if (view.getjButton1().getText().equals("Salvar")) {
                    String nome = view.getjTextField1().getText();
                    String serie = (String) view.getjComboBox1().getSelectedItem();
                    model = new Turma_Model(nome, serie, materia);
                    if (dao.insert(model)) {
                        view.imprimir_Na_Tela("Turma cadastrada com sucesso!");
                        view.hide();
                    } else {
                       Menssagem_De_Confirmacao m = new Menssagem_De_Confirmacao(null, true, "Já existe uma turma com este nome", "", "Atenção", 0);
                        m.setVisible(true);
                    }
                } else if (view.getjButton1().getText().equals("Editar")) {
                   
                    List<Materia_Model> excluir = new ArrayList<>();
                   
                    
                    if (materia_anterior != materia) {
                        System.out.println("Diferente");
                        if ( materia_anterior.size()!=0 ){
                            if (materia.size()!=0){
                        for (int i= 0; i<materia_anterior.size(); i++){
                            excluir.add(materia_anterior.get(i));
                            for (int a=0; a<materia.size();a++){
                                if(materia_anterior.get(i).getIdMatricula()==materia.get(a).getIdMatricula()){
                                   
                                    excluir.remove(materia_anterior.get(i));
                                }
                            }
                        }}
                        else {
                            excluir =materia_anterior;
                        }}
                        if (excluir.size()!=0){
                        Menssagem_De_Confirmacao m = new Menssagem_De_Confirmacao(null, true, "Ao desvincular uma turma de uma materia: ", "atividades, notas, faltas e aulas serão apagados permanentemente", "Atenção");
                    m.setVisible(true);
                    if (m.retornar()) {
                        m = new Menssagem_De_Confirmacao(null, true, "Tem certeza que deseja desvincular a turma: "+model.getNome(), " da materia " + excluir.get(0).getNome() + "?", "Este é o ultimo aviso!");
                        m.setVisible(true);
                        if (m.retornar()) {
                        for (Materia_Model materia : excluir){
                            System.out.println("entrando aqui");
                            deleteMateria(model,materia);
                        }
                        
                       }
                        else {
                        editar=false;}
                    }
                    else{
                        editar=false;
                    }}} 
                    if(editar){
                       model.setMateria( new ArrayList<>());
                        if (materia.size() != 0) {
                            for (Materia_Model ma : materia) {
                                System.out.println("adcionado e mesmo assim essa caceta dentro do cu n vai");
                                model.addMateria(ma);
                            }
                        }
                    model.setNome(view.getjTextField1().getText());
                    model.setSerie("" + view.getjComboBox1().getSelectedItem());
                    if (dao.editar(model)) {
                        view.imprimir_Na_Tela("Edição realizada com sucesso!");
                        view.hide();
                    } else {
                        Menssagem_De_Confirmacao m = new Menssagem_De_Confirmacao(null, true, "Já existe uma turma com este nome", "", "Atenção", 0);
                        m.setVisible(true);

                    }}
                } else {
                    Menssagem_De_Confirmacao m = new Menssagem_De_Confirmacao(null, true, "Ao excluir a turma os alunos, atividades", "Aulas e notas serão apagados permanentemente", "Atenção");
                    m.setVisible(true);
                    if (m.retornar()) {
                        m = new Menssagem_De_Confirmacao(null, true, "Tem certeza que quer apagar todos os dados", " da turma: " + model.getNome() + "?", "Este é o ultimo aviso!");
                        m.setVisible(true);
                        if (m.retornar()) {
                            if (deleteAll(model)) {
                                view.imprimir_Na_Tela("Exclusão realizada com sucesso!");
                                view.hide();
                            }
                        }
                    }
                }
            }
        } else {
            Menssagem_De_Confirmacao m = new Menssagem_De_Confirmacao(null, true, "O campo do nome não pode ficar vazio!", "", "Atenção", 0);
            m.setVisible(true);

        }

    }

    public void iniciar() {
        view.getjTextField1().setDocument(new Validacao(40));
        view.getjComboBox1().setSelectedIndex(-1);

        for (Series_Enum value : Series_Enum.values()) {
            view.getjComboBox1().addItem(value.toString());

        }
        for (Materia_Model materia : dao_materia.selectAll()) {
            view.getjComboBox2().addItem(materia.getNome());
        }
        for (Turma_Model t : dao.selectAll()) {
            view.getjComboBox3().addItem(t.getNome());
        }
        view.getjComboBox2().setSelectedItem(selecionado.getNome());
        if(view.getjButton1().getText().equals("Salvar"))
        addMateria();

    }

    public void addMateria() {
        boolean tem = true;
        for (Materia_Model m : materia) {

            if (m.getNome().equals(view.getjComboBox2().getSelectedItem())) {
                tem = false;
                break;
            }

        }
        if (tem) {
            materia.add(dao_materia.selectAll().get(view.getjComboBox2().getSelectedIndex()));
            DefaultListModel n = new DefaultListModel();
            for (Materia_Model m : materia) {
                n.addElement(m.getNome());
            }
            view.getjList1().removeAll();
            view.getjList1().setModel(n);
        }
    }

    public void exMateria() {
       materia.remove(view.getjList1().getSelectedIndex());
        DefaultListModel n = new DefaultListModel();
        for (Materia_Model m : materia) {
            n.addElement(m.getNome());

        }
        view.getjList1().removeAll();
        view.getjList1().setModel(n);
    }

    public boolean deleteAll(Turma_Model model) {
    model = dao.findbyId(model.getIdTurma());
            dao_al.deleteforTurma(model);
        dao_at.deleteforTurma(model);
        dao_a.deleteforTurma(model);
        dao_t.deleteforTurma(model);
       dao_aula.deleteforTurma(model);
        return dao.delete(model);
    }

    private void deleteMateria(Turma_Model model, Materia_Model materia) {
        dao_al.deleteforTurmaAndMateria(model, materia);
        dao_at.deleteforTurmaAndMateria(model, materia);
        dao_aula.deleteforTurmaAndMateria(model, materia);
        dao_t.deleteforTurmaAndMateria(model, materia);
        dao_tm.deleteforTurmaAndMteria(model, materia);
        model.removeMateria(materia);   
    }
    
}
