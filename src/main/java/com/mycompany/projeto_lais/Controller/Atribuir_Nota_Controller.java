/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Controller;

import com.mycompany.projeto_lais.Enums.Unidade_Enum;
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
import com.mycompany.projeto_lais.View.Menssagem_De_Confirmacao;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
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
    private List<Aluno_Atividade_Model>listAluno_Atividade;
    private List<Aluno_Model>listAluno;
    private List<Atividade_Model>listAtividade;
    private ArrayList<Atribuir_Nota_Controller.Localizacao> antes;
    private ArrayList<Atribuir_Nota_Controller.Localizacao> depois;
    private Atribuir_Nota_Controller.Localizacao localizacao;

    public Atribuir_Nota_Controller(Atribuir_Nota view, Turma_Materia_Model turmamateria) {
        this.view = view;
        dao = new Aluno_Atividade_dao();
        dao_atvd = new Atividade_dao();
        dao_aluno = new Aluno_dao();
        dao_Turma = new Turma_dao(); 
        dao_materia = new Materia_dao();
        this.turmamateria = turmamateria;
        listAluno_Atividade =  dao.findByTurmaMateria(turmamateria);
        dm = new DefaultTableModel();
    }
    public void iniciar(){
        view.setExtendedState(Atribuir_Nota.MAXIMIZED_BOTH);
        view.getjComboBox2().removeAll();
        for (Unidade_Enum unidade : Unidade_Enum.values()){
           view.getjComboBox2().addItem(unidade.name());
        }
        atualizar();
    }
    public void salvar(){
        depois = valorCelulas();
        if (antes==depois){
            System.out.println("Não mudou");
        }
        else {
            boolean validacao = true;
            for (int i = 0; i < depois.size();i++){
              if (depois.get(i).coluna>1){
                Aluno_Model alunol = listAluno.get(depois.get(i).linha);
                Atividade_Model atividadec = listAtividade.get(depois.get(i).coluna-2);
                if (isInteger(depois.get(i).valor)){
                    float a = Float.parseFloat(depois.get(i).valor);
                    float b;
                    if(antes.get(i).valor!=null){
                        b =  Float.parseFloat(antes.get(i).valor);
                    }
                    else {
                        b=90808090;
                    }
                    
                    if (a!=b){
                        System.out.println("Aluno: "+ alunol.getNome()+ alunol.getIdAluno()+"  atividade "+atividadec.getDescricao()+atividadec.getId());
                        
                        if (a<=atividadec.getValor()){
                            Aluno_Atividade_Model at = dao.findByAlunoAtividade(alunol, atividadec);
                        at.setValor_recebido(a);
                        
                        if (dao.update(at)){
                            System.out.println("linha: "+depois.get(i).linha);
                            System.out.println("coluna: "+depois.get(i).coluna);
                            System.out.println("valor: "+depois.get(i).valor);
                        }   
                        else {
                            Toolkit.getDefaultToolkit().beep();
                                view.imprimirNaTela("valor invalido", "Erro 100");
                                validacao=false;
                    }}
                        else{
                            Menssagem_De_Confirmacao m = new Menssagem_De_Confirmacao(view, validacao, "O valor máximo da atividade "+atividadec.getDescricao(), "é "+atividadec.getValor(), "Atenção");
                            m.setVisible(true);
                            validacao=false;
                            break;
                        }
                
                }
                
                
            }
                else{
                    if (depois.get(i).coluna!=0 && depois.get(i).valor!=null){
                        Toolkit.getDefaultToolkit().beep();
                   view.imprimirNaTela("A nota do aluno(a) '"+alunol.getNome()+"' da atividade '"+atividadec.getDescricao()+"' não é do tipo inteiro", "Error Valor inválido");
                validacao=false;
                 }
                }
              }
            } if (validacao){
                view.imprimirNaTela("Notas regitradas com sucesso!", "Sucess");
                atualizar();
            }
        }
    }
    public void atualizar(){
         dm = new DefaultTableModel();
       
         String unidade = ""+view.getjComboBox2().getSelectedItem();
         listAluno_Atividade =  dao.findByTurmaMateria(turmamateria);
         listAluno = dao_aluno.findByTurma(turmamateria.getTurma());
         listAtividade = dao_atvd.findByTurmaMateria(turmamateria, unidade);
         
         
         String[] dados = new String [10];
         String[] coluna = new String [10];
         
         if (listAluno_Atividade!=null){
             int i = 0;
             int numero=0;
             dm.addColumn("#");
             dm.addColumn("descrição");
            
             for (int a = 0; a <= listAtividade.size()-1; a++){
                 dm.addColumn(listAtividade.get(a).getDescricao());
             }
 
             
             for (Aluno_Model lista_a : listAluno){
                 i++;
                 numero=0;
             dados [0] = ""+i;
             dados [1] = lista_a.getNome();
             
             for (int a = 0; a < listAluno_Atividade.size(); a++){
                // ele ta mostrando a posição errada
                 if(listAluno_Atividade.get(a).getAluno().getIdAluno()== lista_a.getIdAluno()){
                // float media = (list.getNota1()+list.getNota2()+list.getNota3())/3;
             dados [numero+2] = ""+listAluno_Atividade.get(a).getValor_recebido();
             System.out.println("atividade recebida " +listAluno_Atividade.get(a).getAtividade().getDescricao()+"posição "+(numero+2));
                 numero++;}}
             dm.addRow(dados);
                     }
             view.getjTable1().setModel(dm);
         view.getjTable1().getColumnModel().getColumn(0).setMaxWidth(80);
         view.getjTable1().getColumnModel().getColumn(0).setMinWidth(250);
         view.getjTable1().getColumnModel().getColumn(1).setMaxWidth(500);
         view.getjTable1().getColumnModel().getColumn(1).setMinWidth(650);
         }
         antes = valorCelulas();
         
    }
    
     public ArrayList<Atribuir_Nota_Controller.Localizacao> valorCelulas(){
         dm = (DefaultTableModel) view.getjTable1().getModel();
         ArrayList<Atribuir_Nota_Controller.Localizacao> lista =  new ArrayList<>();
         int numColumn = dm.getColumnCount();
         int numRow = dm.getRowCount();
          for (int linha = 0; linha < numRow; linha++) {
            for (int coluna = 2; coluna < numColumn; coluna++) {
                localizacao = new Localizacao(coluna, linha, (String) dm.getValueAt(linha, coluna));
                   lista.add(localizacao);
                   System.out.print(localizacao.valor);
             
            }
         }
          return lista;
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

     private static class Localizacao {
private int coluna;
private int linha;
private String valor;

        public Localizacao(int coluna, int linha, String valor) {
            this.coluna = coluna;
            this.linha = linha;
            this.valor = valor;
        }

       
        
    }
     private static boolean isInteger(String str) {
        return str != null && str.matches("[0-9].*");
    }
    
    
    
    
}
