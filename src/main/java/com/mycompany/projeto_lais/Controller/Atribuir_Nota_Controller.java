/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Controller;

import com.mycompany.projeto_lais.Model.Aluno_Atividade_Model;
import com.mycompany.projeto_lais.Model.Aluno_Model;
import com.mycompany.projeto_lais.Model.Atividade_Model;
import com.mycompany.projeto_lais.Model.Dao.Aluno_Atividade_dao;
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
import com.mycompany.projeto_lais.View.Turma;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author g15
 */
public class Atribuir_Nota_Controller {
    private final Atribuir_Nota view;
    private Aluno_Atividade_dao dao;
    private Atividade_dao dao_atvd;
    private Atividade_Model atividade;
    private Aluno_dao dao_aluno;
    private Turma_dao dao_Turma;
    private Materia_dao dao_materia;
    private Aluno_Model aluno;
    private Turma_Model turma;
    private Turma_Materia_Model turmamateria;
    private DefaultTableModel dm;
    private ArrayList<Atividade_Model> atividades;
    private Aluno_Atividade_Model model;

    public Atribuir_Nota_Controller(Atribuir_Nota view, Turma_Materia_Model turmamateria) {
        this.view = view;
        dao = new Aluno_Atividade_dao();
        dao_atvd = new Atividade_dao();
        dao_aluno = new Aluno_dao();
        dao_Turma = new Turma_dao(); 
        dao_materia = new Materia_dao();
        this.turmamateria = turmamateria;
    }
    public void iniciar(){
        view.setExtendedState(Atribuir_Nota.MAXIMIZED_BOTH);
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
        Atividade a = new Atividade(turmamateria);
        a.setVisible(true);
        view.hide();
    } 

    
    
    
    
    
}
