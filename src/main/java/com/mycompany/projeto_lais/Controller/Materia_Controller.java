/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Controller;

import com.mycompany.projeto_lais.Model.Aluno_Model;
import com.mycompany.projeto_lais.Model.Dao.Materia_dao;
import com.mycompany.projeto_lais.Model.Materia_Model;
import com.mycompany.projeto_lais.Model.Turma_Model;
import com.mycompany.projeto_lais.View.Cadastro_Materia;
import com.mycompany.projeto_lais.View.Materia;
import com.mycompany.projeto_lais.View.Turma;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Artur
 */
public class Materia_Controller {
    private Materia view;
    private Materia_dao dao = new Materia_dao();
    private Aluno_Model aluno;
    private Validacao validacao;

    public Materia_Controller(Materia view) {
        this.view = view;
    }
    public Image pegarImagem(int b){
        
        Materia_Model mm = dao.findbyId(b);
        Image imagem=null;
        if (mm!=null){
            if(dao.findbyId(b).getImagem()!=null){
       ImageIcon imagemicon = new ImageIcon(dao.findbyId(b).getImagem());
            imagem = imagemicon.getImage().getScaledInstance(view.getBtn1().getWidth(), view.getBtn1().getHeight(), Image.SCALE_REPLICATE);}
        
        }
       return imagem;
    }
    public void botoesAtivos (){
        int i = 1;
        dao = new Materia_dao();
         Turma_Model turma=null;
      for (Materia_Model model : dao.selectAll()){
          for(Turma_Model t : model.getTurma()){
              turma=t;
          }
          
          
          switch (i) {
              case 1:
                  if (pegarImagem(model.getIdMatricula())!=null){
                  view.getBtn1().setIcon( new ImageIcon(pegarImagem(model.getIdMatricula())));}
                  view.getText1().setText(""+model.getNome());
                  view.getBtn2().setVisible(true);
                  view.getText2().setVisible(true);
                  view.getText2().setText("+");
                  view.getBtn2().setIcon(null);
                 
                  
                  break;
                  case 2:
                      if (pegarImagem(model.getIdMatricula())!=null){

                  view.getBtn2().setIcon( new ImageIcon(pegarImagem(model.getIdMatricula())));}
                  view.getText2().setText(model.getNome());
                  view.getBtn3().setVisible(true);
                  view.getText3().setVisible(true);
                  view.getText3().setText("+");
                  view.getBtn3().setIcon(null);
                  break;
                  case 3:
                  if (pegarImagem(model.getIdMatricula())!=null){

                  view.getBtn3().setIcon( new ImageIcon(pegarImagem(model.getIdMatricula())));}
                  view.getText3().setText(model.getNome());
                  view.getBtn4().setVisible(true);
                  view.getText4().setVisible(true);
                  view.getText4().setText("+");
                  view.getBtn4().setIcon(null);
                  break;
                  case 4:
                  if (pegarImagem(model.getIdMatricula())!=null){

                  view.getBtn4().setIcon( new ImageIcon(pegarImagem(model.getIdMatricula())));}
                  view.getText4().setText(model.getNome());
                  view.getBtn5().setVisible(true);
                  view.getText5().setVisible(true);
                  view.getText5().setText("+");
                  view.getBtn5().setIcon(null);
                  break;
                  case 5:
                  if (pegarImagem(model.getIdMatricula())!=null){

                  view.getBtn5().setIcon( new ImageIcon(pegarImagem(model.getIdMatricula())));}
                  view.getText5().setText(model.getNome());
                  view.getBtn6().setVisible(true);
                  view.getText6().setVisible(true);
                  view.getText6().setText("+");
                  view.getBtn6().setIcon(null);
                  break;
                  case 6:
                  if (pegarImagem(model.getIdMatricula())!=null){

                  view.getBtn6().setIcon( new ImageIcon(pegarImagem(model.getIdMatricula())));}
                  view.getText6().setText(model.getNome());
                  view.getBtn7().setVisible(true);
                  view.getText7().setVisible(true);
                  view.getText7().setText("+");
                  view.getBtn7().setIcon(null);
                  break;
                  case 7:
                  if (pegarImagem(model.getIdMatricula())!=null){

                  view.getBtn7().setIcon( new ImageIcon(pegarImagem(model.getIdMatricula())));}
                  view.getText7().setText(model.getNome());
                  view.getBtn8().setVisible(true);
                  view.getText8().setVisible(true);
                  view.getText8().setText("+");
                  view.getBtn8().setIcon(null);
                  break;
                  case 8:
                  if (pegarImagem(model.getIdMatricula())!=null){

                  view.getBtn8().setIcon( new ImageIcon(pegarImagem(model.getIdMatricula())));}
                  view.getText8().setText(model.getNome());
                  view.getBtn9().setVisible(true);
                  view.getText9().setVisible(true);
                  view.getText9().setText("+");
                  view.getBtn9().setIcon(null);
                  break;
                  case 9:
                  if (pegarImagem(model.getIdMatricula())!=null){

                  view.getBtn9().setIcon( new ImageIcon(pegarImagem(model.getIdMatricula())));}
                  view.getText9().setText(model.getNome());
                  view.getBtn10().setVisible(true);
                  view.getText10().setVisible(true);
                  view.getText10().setText("+");
                  view.getBtn10().setIcon(null);
                  break;
                  case 10:
                  if (pegarImagem(model.getIdMatricula())!=null){

                  view.getBtn10().setIcon( new ImageIcon(pegarImagem(model.getIdMatricula())));}
                  view.getText10().setText(model.getNome());
                  view.getBtn11().setVisible(true);
                  view.getText11().setVisible(true);
                  view.getText11().setText("+");
                  view.getBtn11().setIcon(null);
                  break;
                  case 11:
                  if (pegarImagem(model.getIdMatricula())!=null){

                  view.getBtn11().setIcon( new ImageIcon(pegarImagem(model.getIdMatricula())));}
                  view.getText11().setText(model.getNome());
                  view.getBtn12().setVisible(true);
                  view.getText12().setVisible(true);
                  view.getText12().setText("+");
                  view.getBtn12().setIcon(null);
                  break;
                  case 12:
                  if (pegarImagem(model.getIdMatricula())!=null){

                  view.getBtn12().setIcon( new ImageIcon(pegarImagem(model.getIdMatricula())));}
                  view.getText12().setText(model.getNome());
                  view.getBtn13().setVisible(true);
                  view.getText13().setVisible(true);
                  view.getText13().setText("+");
                  view.getBtn13().setIcon(null);
                  break;
                  case 13:
                  if (pegarImagem(model.getIdMatricula())!=null){

                  view.getBtn13().setIcon( new ImageIcon(pegarImagem(model.getIdMatricula())));}
                  view.getText13().setText(model.getNome());
                  view.getBtn14().setVisible(true);
                  view.getText14().setVisible(true);
                  view.getText14().setText("+");
                  view.getBtn14().setIcon(null);
                  break;
                  case 14:
                  if (pegarImagem(model.getIdMatricula())!=null){

                  view.getBtn14().setIcon( new ImageIcon(pegarImagem(model.getIdMatricula())));}
                  view.getText14().setText(model.getNome());
                  view.getBtn15().setVisible(true);
                  view.getText15().setVisible(true);
                  view.getText15().setText("+");
                  view.getBtn15().setIcon(null);
                  break;
                  case 15:
                  if (pegarImagem(model.getIdMatricula())!=null){

                  view.getBtn15().setIcon( new ImageIcon(pegarImagem(model.getIdMatricula())));}
                  view.getText15().setText(model.getNome());
                  view.getBtn16().setVisible(true);
                  view.getText16().setVisible(true);
                  view.getText16().setText("+");
                  view.getBtn16().setIcon(null);
                  break;
                  case 16:
                  if (pegarImagem(model.getIdMatricula())!=null){
                  view.getBtn16().setIcon( new ImageIcon(pegarImagem(model.getIdMatricula())));}
                  view.getText16().setText(model.getNome());
                  view.getText6().setVisible(true);
              default:
                  System.out.println("error teste");
          }i++;
                  
                  }
      }
    
    public void cadastrarBotao(String b){
        System.out.println("olhhaaa oooooo b      ->"+b);
        if (dao.findByName(b) == null) {
            Cadastro_Materia m = new Cadastro_Materia(b, null, true);
       m.setVisible(true);
        }
        else {
            Turma t = new Turma(dao.findByName(b));
            t.setVisible(true);
            view.hide(); 
        }
       botoesAtivos();
    }

    public void iniciar() {
       view.setExtendedState(Materia.MAXIMIZED_BOTH);
       view.getText1().setText("+");
    view.getBtn2().setVisible(false);  view.getText2().setVisible(false);
    view.getBtn3().setVisible(false);  view.getText3().setVisible(false);
    view.getBtn4().setVisible(false);  view.getText4().setVisible(false);
    view.getBtn5().setVisible(false);  view.getText5().setVisible(false);
    view.getBtn6().setVisible(false);  view.getText6().setVisible(false);
    view.getBtn7().setVisible(false);  view.getText7().setVisible(false);
    view.getBtn8().setVisible(false);  view.getText8().setVisible(false);
    view.getBtn9().setVisible(false);  view.getText9().setVisible(false);
    view.getBtn10().setVisible(false); view.getText10().setVisible(false);
    view.getBtn11().setVisible(false); view.getText11().setVisible(false);
    view.getBtn12().setVisible(false); view.getText12().setVisible(false);
    view.getBtn13().setVisible(false); view.getText13().setVisible(false);
    view.getBtn14().setVisible(false); view.getText14().setVisible(false);
    view.getBtn15().setVisible(false); view.getText15().setVisible(false);
    view.getBtn16().setVisible(false); view.getText16().setVisible(false);
   botoesAtivos();
    }

    public void editarMateria(){
      
            Cadastro_Materia m = new Cadastro_Materia( null, true);
       m.setVisible(true);
        botoesAtivos();
    }

    public void excluirMateria() {
        Cadastro_Materia m = new Cadastro_Materia( null, true, "");
       m.setVisible(true); 
       iniciar();
       botoesAtivos();
       
        
    }
   

    
}
