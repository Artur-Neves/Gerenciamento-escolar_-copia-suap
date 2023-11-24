/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Controller;

import com.mycompany.projeto_lais.Enums.Unidade_Enum;
import com.mycompany.projeto_lais.Model.Aluno_Model;
import com.mycompany.projeto_lais.Model.Atividade_Model;
import com.mycompany.projeto_lais.Model.Dao.Aluno_dao;
import com.mycompany.projeto_lais.Model.Dao.Atividade_dao;
import com.mycompany.projeto_lais.Model.Dao.Materia_dao;
import com.mycompany.projeto_lais.Model.Dao.Turma_dao;
import com.mycompany.projeto_lais.Model.Turma_Materia_Model;
import com.mycompany.projeto_lais.Model.Turma_Model;
import com.mycompany.projeto_lais.View.Aluno;
import com.mycompany.projeto_lais.View.Atividade;
import com.mycompany.projeto_lais.View.Atribuir_Nota;
import com.mycompany.projeto_lais.View.Aula;
import com.mycompany.projeto_lais.View.Cadastro_Atividade;
import com.mycompany.projeto_lais.View.Turma;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Artur
 */
public class Atividade_Controller {

    private final Atividade view;
    private Atividade_dao dao;
    private Atividade_Model model;
    private Aluno_dao dao_aluno;
    private Turma_dao dao_Turma;
    private Materia_dao dao_materia;
    private Aluno_Model aluno;
    private Turma_Model turma;
    private Turma_Materia_Model turmamateria;
    private DefaultTableModel dm;
    private ArrayList<Atividade_Model> atividades;
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
    public Validacao validacao;

    public Atividade_Controller(Atividade view, Turma_Materia_Model turmamateria) {
        this.view = view;
        this.turmamateria=turmamateria;
        dm = (DefaultTableModel) view.getjTable1().getModel();
        atividades = new ArrayList<>();
        dao = new Atividade_dao();
        dao_aluno = new Aluno_dao();
        dao_Turma = new Turma_dao(); 
        dao_materia = new Materia_dao();
    }

    public void iniciar() {
     view.setExtendedState(Atividade.MAXIMIZED_BOTH);
     view.getjComboBox1().removeAllItems();
     view.getjComboBox1().addItem("");
     for (Unidade_Enum b :Unidade_Enum.values()){
         view.getjComboBox1().addItem(b.toString());
     }
     atualizar();
    }
 public void entrarAluno() {
        Aluno aluno = new Aluno(turmamateria.getTurma(), turmamateria.getMateria());
        aluno.setVisible(true);
        view.hide();
    }

    public void entrarAula() {
        Aula aula = new Aula(turmamateria);
        aula.setVisible(true);
        view.hide();
    }
    public void voltar(){
        Turma turma = new Turma(turmamateria.getMateria());
        turma.setVisible(true);
        view.hide();
    } 

    public void adcionar() {
        Cadastro_Atividade atividade = new Cadastro_Atividade(null, true, turmamateria);
        atividade.setVisible(true);
        atualizar();
    }

    public void editar() {
        if(view.getjTable1().getSelectedRow()!=-1){
        Atividade_Model atividade1 = atividades.get(view.getjTable1().getSelectedRow());
        
            
         Cadastro_Atividade atividade = new Cadastro_Atividade(null, true, atividade1 ,  turmamateria, "");
        atividade.setVisible(true);
        atualizar();}
    }

    public void excluir() {
         if(view.getjTable1().getSelectedRow()!=-1){
        Atividade_Model atividade1 = atividades.get(view.getjTable1().getSelectedRow());
         Cadastro_Atividade atividade = new Cadastro_Atividade(null, true,atividade1 ,  turmamateria, 0);
        atividade.setVisible(true);
        atualizar();}
    }

    public void atualizar() {
         if (view.getjTable1().getModel().getRowCount() > 0) {
            for (int i = view.getjTable1().getModel().getRowCount() - 1; i >= 0; i--) {
                dm.removeRow(i);
            }
            view.getjTable1().setModel(dm);
    }
 String[] dados = new String [5];
 String unidade = ""+view.getjComboBox1().getSelectedItem();
 List<Atividade_Model> lista= dao.findByTurmaMateriaUnidade(turmamateria, unidade);
 atividades.clear();
 if (lista!=null){
        for (Atividade_Model  atividade : lista) {
            dados[0] = atividade.getDescricao();
            dados[1] = ""+atividade.getCalculo();
            dados[2] = ""+atividade.getValor();
            dados[3] = atividade.getUnidade();
            dados[4] = formato.format(atividade.getData());
            dm.addRow(dados);
            atividades.add(atividade);
        }
view.getjTable1().setModel(dm);
    }}

    public void atribuirNota() {
        Atribuir_Nota n = new Atribuir_Nota(turmamateria);
        n.setVisible(true);
        view.hide();
    }

    public void entrarRecuperacao() {
        Atribuir_Nota n = new Atribuir_Nota(turmamateria, "");
        n.setVisible(true);
        view.hide();
    }

    
}
