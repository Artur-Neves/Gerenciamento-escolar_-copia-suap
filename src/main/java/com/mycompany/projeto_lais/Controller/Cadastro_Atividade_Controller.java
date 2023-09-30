/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Controller;

import com.mycompany.projeto_lais.Enums.Formato_Enum;
import com.mycompany.projeto_lais.Enums.Unidade_Enum;
import com.mycompany.projeto_lais.Model.Aluno_Atividade_Model;
import com.mycompany.projeto_lais.Model.Aluno_Model;
import com.mycompany.projeto_lais.Model.Atividade_Model;
import static com.mycompany.projeto_lais.Model.Aula_Model_.conteudo;
import static com.mycompany.projeto_lais.Model.Aula_Model_.obs;
import static com.mycompany.projeto_lais.Model.Aula_Model_.quantidade;
import com.mycompany.projeto_lais.Model.Dao.Aluno_dao;
import com.mycompany.projeto_lais.Model.Dao.Atividade_dao;
import com.mycompany.projeto_lais.Model.Dao.Materia_dao;
import com.mycompany.projeto_lais.Model.Dao.Turma_Materia_dao;
import com.mycompany.projeto_lais.Model.Dao.Turma_dao;
import com.mycompany.projeto_lais.Model.Turma_Materia_Model;
import com.mycompany.projeto_lais.Model.Turma_Model;
import com.mycompany.projeto_lais.View.Cadastro_Atividade;
import java.util.Date;

/**
 *
 * @author Artur
 */
public class Cadastro_Atividade_Controller {


    private final Cadastro_Atividade view;
    private Atividade_dao dao;
    private Atividade_Model model;
    private Aluno_dao dao_aluno;
    private Turma_dao dao_turma;
    private Materia_dao dao_materia;
    private Aluno_Model aluno;
    private Turma_Model turma;
    private Turma_Materia_dao dao_tm;
    private Turma_Materia_Model turmamateria;

    public Cadastro_Atividade_Controller(Cadastro_Atividade view, Turma_Materia_Model turmamateria) {
        this.view = view;
         dao_aluno = new Aluno_dao();
                dao_turma = new Turma_dao();
                dao_materia = new Materia_dao();
                dao_tm= new Turma_Materia_dao();
                dao = new Atividade_dao();
        this.turmamateria= dao_tm.findbyturmamateria(turmamateria);
    }
    public Cadastro_Atividade_Controller(Cadastro_Atividade view, Atividade_Model model, Turma_Materia_Model turmamateria) {
        this.view = view;
         dao_aluno = new Aluno_dao();
                dao_turma = new Turma_dao();
                dao_materia = new Materia_dao();
                dao_tm= new Turma_Materia_dao();
                dao = new Atividade_dao();
                this.model= model;
                
        this.turmamateria= dao_tm.findbyturmamateria(turmamateria);
    }

    public void iniciar() {
        view.getjComboBox3().removeAll();
     for(Formato_Enum formato :Formato_Enum.values()){
         view.getjComboBox3().addItem(formato.toString());
     }
     view.getjComboBox4().removeAll();
     for (Unidade_Enum unidade : Unidade_Enum.values()){
         view.getjComboBox4().addItem(unidade.toString());    }
      if (!view.getjButton3().getText().equals("Salvar")){
        view.getjComboBox3().setSelectedItem(model.getFormato());
        view.getjComboBox4().setSelectedItem(model.getUnidade());
        view.getjTextField1().setText(""+model.getValor());
        view.getjDateChooser1().setDate(model.getData());
        view.getjTextArea2().setText(model.getDescricao());
        }
        
    }
    public void inserir(){
        String unidade= ""+view.getjComboBox4().getSelectedItem();
            Double valor=  Double.parseDouble(view.getjTextField1().getText());
            Date data = view.getjDateChooser1().getDate();
            String formato = ""+view.getjComboBox3().getSelectedItem();
            String descricao = view.getjTextArea2().getText();
        if(view.getjButton3().getText().equals("Salvar")){
           model = new Atividade_Model(formato, valor, data, descricao,  unidade ,turmamateria);
           if (dao_aluno.findByTurma(turmamateria.getTurma())!=null){
           for (Aluno_Model aluno_model : dao_aluno.findByTurma(turmamateria.getTurma())) {
                model.addAluno(dao_aluno.findByNome(aluno_model.getNome()));
                
            }
           }
                   Aluno_Atividade_Model aa = new Aluno_Atividade_Model();

           if (dao.insert(model)){
//               for (Aluno_Model aluno_model : dao_aluno.findByTurma(turmamateria.getTurma())){
//                   aa = dao_f.findByAlunoAula(aluno_model, model);
//
//               if( dao_f.update(f)){
//                   
//               }
//            }
               view.imprimir_Na_Tela("Atividade inserida com sucesso!");
               view.hide();
           }
        }
        else if (view.getjButton3().getText().equals("Editar")){
            model.setUnidade(unidade);
            model.setData(data);
            model.setFormato(formato);
            model.setDescricao(descricao);
            model.setValor(valor);
            if (dao.update(model)){
               view.imprimir_Na_Tela("Atividade editada com sucesso!");
               view.hide();
           }
        }
        else{
             if (dao.delete(model)){
               view.imprimir_Na_Tela("Atividade excluida com sucesso!");
               view.hide();
           }
        }
    }
    }
    

