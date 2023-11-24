/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Controller;

import com.mycompany.projeto_lais.Enums.Unidade_Enum;
import com.mycompany.projeto_lais.Model.Aluno_Model;
import com.mycompany.projeto_lais.Model.Aula_Model;
import com.mycompany.projeto_lais.Model.Dao.Aluno_dao;
import com.mycompany.projeto_lais.Model.Dao.Aula_dao;
import com.mycompany.projeto_lais.Model.Dao.Materia_dao;
import com.mycompany.projeto_lais.Model.Dao.Turma_dao;
import com.mycompany.projeto_lais.Model.Turma_Materia_Model;
import com.mycompany.projeto_lais.Model.Turma_Model;
import com.mycompany.projeto_lais.View.Aluno;
import com.mycompany.projeto_lais.View.Atividade;
import com.mycompany.projeto_lais.View.Aula;
import com.mycompany.projeto_lais.View.Cadastro_Aula;
import com.mycompany.projeto_lais.View.Faltas;
import com.mycompany.projeto_lais.View.Turma;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Artur
 */
public class Aula_Controller {


    private final Aula view;
    private Aula_dao dao;
    private Aula_Model model;
    private Aluno_dao dao_aluno;
    private Turma_dao dao_turma;
    private Materia_dao dao_materia;
    private Aluno_Model aluno;
    private Turma_Model turma;
    private Turma_Materia_Model turmamateria;
    private DefaultTableModel dm;
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
    private Validacao validacao;


    public Aula_Controller(Aula view, Turma_Materia_Model turmamateria) {
        this.view = view;
        this.turmamateria= turmamateria;
                dm = (DefaultTableModel) view.getjTable1().getModel();
                dao = new Aula_dao();
                dao_aluno = new Aluno_dao();
                dao_turma = new Turma_dao();                
    }

    public void iniciar() {
     view.setExtendedState(Atividade.MAXIMIZED_BOTH);
     for (Unidade_Enum value : Unidade_Enum.values()) {
            view.getjComboBox1().addItem(value.toString());
        }
     atualizar();
    }
    boolean isAlreadyOneClick;

/*public void duploClick() {
    if (isAlreadyOneClick) {
        System.out.println("double click");
        isAlreadyOneClick = false;
    } else {
        isAlreadyOneClick = true;
        Timer t = new Timer("doubleclickTimer", false);
        t.schedule(new TimerTask() {

            @Override
            public void run() {
                isAlreadyOneClick = false;
            }
        }, 500);
    }
}*/
    
    public void adcionar() {
        Cadastro_Aula cadastro = new Cadastro_Aula(null, true, turmamateria);
        cadastro.setVisible(true);
        atualizar();
    }
    public void editar(int n) {
        Aula_Model aula = dao.findByTurmaMateria(turmamateria).get(n);
        Cadastro_Aula cadastro = new Cadastro_Aula(null, true,turmamateria, "", aula);
        cadastro.setVisible(true);
        atualizar();
    }

    public void excluir(int n) {
        Aula_Model aula = dao.findByTurmaMateria(turmamateria).get(n);
        Cadastro_Aula cadastro = new Cadastro_Aula(null, true,turmamateria, 0, aula);
        cadastro.setVisible(true);
        atualizar();
        
    }

  

    public void entrarAluno() {
        Aluno aluno = new Aluno(turmamateria.getTurma(), turmamateria.getMateria());
        aluno.setVisible(true);
        view.hide();
    }

    public void entrarAtividade() {
        Atividade atividade = new Atividade(turmamateria);
        atividade.setVisible(true);
        view.hide();
    }
    public void voltar(){
        Turma turma = new Turma(turmamateria.getMateria());
        turma.setVisible(true);
        view.hide();
    } 

    public void atualizar() {
    if (view.getjTable1().getModel().getRowCount() > 0) {
            for (int i = view.getjTable1().getModel().getRowCount() - 1; i >= 0; i--) {
                dm.removeRow(i);
            }
            view.getjTable1().setModel(dm);
    }
 String[] dados = new String [6];
 String unidade = ""+view.getjComboBox1().getSelectedItem();
 List<Aula_Model> lista = dao.findByTurmaMateria(turmamateria, unidade);
 if(lista!=null){
        for (Aula_Model  aula : lista) {
            dados[0] = aula.getUnidade();
            dados[1] = ""+aula.getQuantidade();
            dados[2] = ""+formato.format(aula.getData());
            dados[3] = aula.getFormato();
            dados[4] = aula.getConteudo();
            dados[5] = aula.getObs();
            dm.addRow(dados);     
        }
view.getjTable1().setModel(dm);}
    }

    public void abrirFaltas() {
       Faltas falta = new Faltas(turmamateria);
        falta.setVisible(true);
        view.hide();
    }
}
