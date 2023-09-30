/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Controller;

import com.mycompany.projeto_lais.Enums.Unidade_Enum;
import com.mycompany.projeto_lais.Model.Aluno_Model;
import com.mycompany.projeto_lais.Model.Dao.Aluno_dao;
import com.mycompany.projeto_lais.Model.Dao.Materia_dao;
import com.mycompany.projeto_lais.Model.Dao.Turma_Materia_dao;
import com.mycompany.projeto_lais.Model.Dao.Turma_dao;
import com.mycompany.projeto_lais.Model.Materia_Model;
import com.mycompany.projeto_lais.Model.Turma_Materia_Model;
import com.mycompany.projeto_lais.Model.Turma_Model;
import com.mycompany.projeto_lais.View.Aluno;
import com.mycompany.projeto_lais.View.Atividade;
import com.mycompany.projeto_lais.View.Aula;
import com.mycompany.projeto_lais.View.Cadastro_Aluno;
import com.mycompany.projeto_lais.View.Materia;
import com.mycompany.projeto_lais.View.Turma;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Artur
 */
public class Aluno_Controller {

    private Aluno view;
    private Aluno_dao dao = new Aluno_dao();
    private Turma_Model turma = null;
    private Aluno_Model model;
    private Turma_dao dao_turma = new Turma_dao();
    private Materia_dao dao_materia = new Materia_dao();
    private Materia_Model materia;
    private Turma_Materia_dao dao_tm = new Turma_Materia_dao();
    private Turma_Materia_Model turmamateria;
    private DefaultTableModel dm;

    public Aluno_Controller(Aluno view, Turma_Model turma, Materia_Model materia) {
        this.view = view;
        this.turma = turma;
        this.materia = materia;
        this.turmamateria = new Turma_Materia_Model(dao_turma.findByName(turma.getNome()), dao_materia.findByName(materia.getNome()));
        this. turmamateria = dao_tm.findbyturmamateria(turmamateria);
         dm = (DefaultTableModel) view.getjTable1().getModel(); 
         System.out.println("vai por favor");
    }

    
    public void iniciar() {
        view.setExtendedState(Materia.MAXIMIZED_BOTH);
        
        atualizar();
        
    }

    public void atualizar() {

        if (view.getjTable1().getModel().getRowCount() > 0) {
            for (int i = view.getjTable1().getModel().getRowCount() - 1; i >= 0; i--) {
                dm.removeRow(i);
            }
         
               
            view.getjTable1().setModel(dm);
        }
        String[] dados = new String[9];
        for (Aluno_Model a : dao.findByTurma(turma)) {
            dados[0] = a.getNome();
            dados[1] = "" + a.getnFaltas();
            dados[2] = "" + a.getFrequencia();
            dados[3] = a.getSituacao();
            dados[4] = "0";
            dados[5] = "0";
            dados[6] = "0";
            dados[7] = "0";
            dados[8] = "0";
            dm.addRow(dados);
        }
        view.getjTable1().setModel(dm);
    }

    public void cadastrar() {
        Cadastro_Aluno m = new Cadastro_Aluno(null, true, turmamateria);
        m.setVisible(true);
        atualizar();
    }

    public void voltar() {
        Turma t = new Turma(materia);
        t.setVisible(true);
        view.hide();
    }

    public void editar(int s) {
        Aluno_Model aluno = dao.findByTurma(turma).get(s);
        Cadastro_Aluno m = new Cadastro_Aluno(null, true, turmamateria, aluno);
        m.setVisible(true);
        atualizar();
    }

    public void excluir(int s) {
        Aluno_Model aluno = dao.findByTurma(turma).get(s);
        Cadastro_Aluno m = new Cadastro_Aluno(null, true, turmamateria, aluno, "");
        m.setVisible(true);
        atualizar();
    }

    public void pesquisar() {
        if (!view.getjTextField1().getText().trim().isEmpty()) {
            String coluna=null;
            switch (view.getjComboBox1().getSelectedIndex()) {
                case 0:
                  coluna="nome";
                    break;
                case 1:
                    coluna="nFaltas";
                    break;
                case 2:
                    coluna="frequencia";
                    break;
                case 3:
                    coluna="situacao";
                    break;
                case 4:
                    coluna="nota1";
                    break;
                case 5:
                    coluna="nota2";
                    break;
                case 6:
                    coluna="nota3";
                    break;
                case 7:
                    coluna="recuperacao";
                    break;
                    case 8:
                        coluna="media";
                    break;
                default:
                    throw new AssertionError();
            }
            if (view.getjTable1().getModel().getRowCount() > 0) {
            for (int i = view.getjTable1().getModel().getRowCount() - 1; i >= 0; i--) {
                dm.removeRow(i);
            }
            view.getjTable1().setModel(dm);
        }
           String texto = view.getjTextField1().getText();
        String[] dados = new String[10];
        for (Aluno_Model a : dao.find(turma, coluna, texto)) {
            dados[0] = a.getNome();
            dados[1] = "" + a.getnPresenca();
            dados[2] = "" + a.getnFaltas();
            dados[3] = "" + a.getFrequencia();
            dados[4] = "" + a.getSituacao();
            dados[5] = "0";
            dados[6] = "0";
            dados[7] = "0";
            dados[8] = "0";
            dados[9] = "0";
            dm.addRow(dados);
        }
        view.getjTable1().setModel(dm);
        }
        else{
            atualizar();
        }
    }

    public void entrarAula() {
       Aula aula = new Aula(turmamateria);
       aula.setVisible(true);
       view.hide();
    }

    public void enrtarAtividade() {
           Atividade atividade = new Atividade(turmamateria);
       atividade.setVisible(true);
       view.hide();   
    }

}
