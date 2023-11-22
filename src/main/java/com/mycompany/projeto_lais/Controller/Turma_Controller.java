/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Controller;

import com.mycompany.projeto_lais.Model.Dao.Turma_dao;
import com.mycompany.projeto_lais.Model.Materia_Model;
import com.mycompany.projeto_lais.Model.Turma_Model;
import com.mycompany.projeto_lais.View.Aluno;
import com.mycompany.projeto_lais.View.Cadastro_Turma;
import com.mycompany.projeto_lais.View.Materia;
import com.mycompany.projeto_lais.View.Turma;
import java.util.List;

/**
 *
 * @author Artur
 */
public class Turma_Controller {
    private final Turma view;
               List<Turma_Model> list;
    private Turma_Model model;
    Materia_Model materia;
    private Turma_dao dao;
    public Turma_Controller(Turma view, Materia_Model materia) {
        this.view = view;
        this.materia=materia;
        dao = new Turma_dao ();
    }
    public void iniciar() {
       view.setExtendedState( Turma.MAXIMIZED_BOTH);
    view.getText1().setText("+");
    view.getBtn2().setVisible(false);  view.getText2().setVisible(false);
    view.getBtn3().setVisible(false);  view.getText3().setVisible(false);
    view.getBtn4().setVisible(false);  view.getText4().setVisible(false);
    view.getBtn5().setVisible(false);  view.getText5().setVisible(false);
    view.getBtn6().setVisible(false);  view.getText6().setVisible(false);
    view.getBtn7().setVisible(false);  view.getText7().setVisible(false);
    view.getBtn8().setVisible(false);  view.getText8().setVisible(false);
    view.getBtn9().setVisible(false);  view.getText9().setVisible(false);
   botoesAtivos();
    }

    private void botoesAtivos() {
        dao = new Turma_dao();
        list=dao.selectByMateria(materia.getIdMatricula());
       if(list.size()!=0)
        for (int index = 0; index<list.size(); index++) {
            
            switch (index) {
                case 0:
                  view.getText1().setText(list.get(index).getNome());
                  view.getBtn2().setVisible(true);
                  view.getText2().setVisible(true);
                  view.getText2().setText("+");
                  break;
                  case 1:
                  view.getText2().setText(list.get(index).getNome());
                  view.getBtn3().setVisible(true);
                  view.getText3().setVisible(true);
                  view.getText3().setText("+");
                  break;
                  case 2:
                  view.getText3().setText(list.get(index).getNome());
                  view.getBtn4().setVisible(true);
                  view.getText4().setVisible(true);
                  view.getText4().setText("+");
                  break;
                  case 3:   
                  view.getText4().setText(list.get(index).getNome());
                  view.getBtn5().setVisible(true);
                  view.getText5().setVisible(true);
                  view.getText5().setText("+");
                  break;
                  case 4:
                  view.getText5().setText(list.get(index).getNome());
                  view.getBtn6().setVisible(true);
                  view.getText6().setVisible(true);
                  view.getText6().setText("+");
                  break;
                  case 5:
                  view.getText6().setText(list.get(index).getNome());
                  view.getBtn7().setVisible(true);
                  view.getText7().setVisible(true);
                  view.getText7().setText("+");
                  break;
                  case 6:
                  view.getText7().setText(list.get(index).getNome());
                  view.getBtn8().setVisible(true);
                  view.getText8().setVisible(true);
                  view.getText8().setText("+");
                  break;
                  case 7:
                  view.getText8().setText(list.get(index).getNome());
                  view.getBtn9().setVisible(true);
                  view.getText9().setVisible(true);
                  view.getText9().setText("+");
                  break;
                  case 8: 
                  view.getText9().setText(list.get(index).getNome());
                  view.getBtn10().setVisible(true);
                  view.getText10().setVisible(true);
                  view.getText10().setText("+");
                  break;
                default:
                  System.out.println("error teste");
          }
        }
    }

    public void cadastroOUEntrar(int b) {
       botoesAtivos();
       if (list.size()<b+1){
 Cadastro_Turma c = new Cadastro_Turma(null, true, materia);
 c.setVisible(true);
       }
       else {
       Aluno t = new Aluno(list.get(b), materia);
            t.setVisible(true);
            view.hide();
       }
       botoesAtivos();}
    public void voltar(){
        Materia m = new Materia();
        m.setVisible(true);
        botoesAtivos();
        view.hide();
    }

    public void editarTurma() {
       Cadastro_Turma t = new Cadastro_Turma(null, true,materia, "");
       t.setVisible(true);
          iniciar();
    }

    public void excluirTurma() {
        Cadastro_Turma t = new Cadastro_Turma(null, true,materia, 0);
       t.setVisible(true);
       iniciar();
       
    }
    
    
}
