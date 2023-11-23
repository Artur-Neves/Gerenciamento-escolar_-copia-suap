/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Controller;

import com.mycompany.projeto_lais.Model.Aluno_Atividade_Model;
import com.mycompany.projeto_lais.Model.Aluno_Model;
import com.mycompany.projeto_lais.Model.Dao.Aluno_Atividade_dao;
import com.mycompany.projeto_lais.Model.Dao.Aluno_dao;
import com.mycompany.projeto_lais.Model.Dao.Frequencia_dao;
import com.mycompany.projeto_lais.Model.Dao.Materia_dao;
import com.mycompany.projeto_lais.Model.Dao.Turma_Materia_dao;
import com.mycompany.projeto_lais.Model.Dao.Turma_dao;
import com.mycompany.projeto_lais.Model.Frequencia_Model;
import com.mycompany.projeto_lais.Model.Materia_Model;
import com.mycompany.projeto_lais.Model.Turma_Materia_Model;
import com.mycompany.projeto_lais.Model.Turma_Model;
import com.mycompany.projeto_lais.View.Aluno;
import com.mycompany.projeto_lais.View.Atividade;
import com.mycompany.projeto_lais.View.Aula;
import com.mycompany.projeto_lais.View.Cadastro_Aluno;
import com.mycompany.projeto_lais.View.Materia;
import com.mycompany.projeto_lais.View.Turma;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Artur
 */
public class Aluno_Controller {

    private Aluno view;
    private Aluno_dao dao ;
    private Turma_Model turma;
    private Aluno_Model model;
    private Turma_dao dao_turma;
    private Materia_dao dao_materia;
    private Aluno_Atividade_dao dao_at;
    private Materia_Model materia;
    private Turma_Materia_dao dao_tm;
    private Turma_Materia_Model turmamateria;
    private DefaultTableModel dm;
    private List<Aluno_Atividade_Model> lista_At;
    private List<Frequencia_Model> lista_f;
    private Frequencia_dao dao_aa;
    private double nota1;
    private double nota2;
    private double nota3;
    private double media;
    private int[] faltas;
    private int frequencia;
   

    
    DecimalFormat df = new DecimalFormat("#,###.0");


    public Aluno_Controller(Aluno view, Turma_Model turma, Materia_Model materia) {
           dao = new Aluno_dao();
        dao_turma = new Turma_dao();
        dao_materia = new Materia_dao();
        dao_at = new Aluno_Atividade_dao();
        dao_tm = new Turma_Materia_dao();
        lista_At = new ArrayList<>();
        dao_aa = new Frequencia_dao ();
        faltas = new int[2];
        this.view = view;
        this.turma = turma;
        this.materia = materia;
        this.turmamateria = new Turma_Materia_Model(turma, dao_materia.findByName(materia.getNome()));
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
            nota1 = soma_Das_Notas(a, "Unidade 1");
            nota2 = soma_Das_Notas(a, "Unidade 2");
            nota3 = soma_Das_Notas(a, "Unidade 3");
            faltas = faltas(a);
            frequencia = 100-((faltas[0]*100)/faltas[1]);
            media = (nota1+nota2+nota3)/3;
            double recuperacao = dao_at.findByAlunoUnidade(a, "Recuperação").get(0).getValor_recebido();
            dados[0] = a.getNome();
            dados[1] = ""+faltas[0];
            dados[2] = frequencia+"%";
            dados[3] = a.getSituacao();
            // nota 1
            dados[4] = ""+df.format(nota1);
            // nota 2
            dados[5] = ""+df.format(nota2);
            // nota 3
            dados[6] = ""+df.format(nota3);
            // recuperação
            dados[7] = ""+recuperacao;
            // media
            dados[8] = " "+df.format(media);
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
    public double soma_Das_Notas(Aluno_Model aluno, String unidade){
       lista_At = dao_at.findByAlunoUnidade(aluno, unidade); 
       double valor =0;
       double quantidade=0;
       double valor_peso= 0;
       double maior = -1;
       if (lista_At!=null){
        for (Aluno_Atividade_Model at: lista_At){
             switch (lista_At.get(0).getAtividade().getCalculo()) {
            case "Soma simples":
                valor = valor+at.getValor_recebido();
                quantidade=1;
                System.out.println("ta caindo aqui");
                break;
                 case "Média Aritmética":
                  quantidade++;
                  valor = valor+ at.getValor_recebido();
                    break;
                    case "Média Ponderada":
                   quantidade=1;
                   valor_peso= at.getAtividade().getPeso()*(at.getValor_recebido()/10);
                   valor = valor+valor_peso;
                    break;
                    case "Soma com Divisor Informado":
                    quantidade=at.getAtividade().getDivisor();
                    valor = valor+at.getValor_recebido();
                    
                    break;
                    case "Maior Nota":
                        quantidade=1;
                   if (at.getValor_recebido()>maior){
                       maior = at.getValor_recebido();
                   }
                   valor = maior;
            default:
                break;
        }
        }}
        return valor/quantidade;
    }
    public int[] faltas(Aluno_Model aluno){
        int[] quantidade_faltas = new int[2];
        quantidade_faltas[0]=0;
       quantidade_faltas[1]=0;

      
        lista_f = dao_aa.findByAluno(aluno, turmamateria);
            for (Frequencia_Model f : lista_f){
            quantidade_faltas[0] = quantidade_faltas[0] + f.getFaltas();
            quantidade_faltas[1] = quantidade_faltas[1] +f.getIdAula().getQuantidade();
              }
            return quantidade_faltas;
    }

}
