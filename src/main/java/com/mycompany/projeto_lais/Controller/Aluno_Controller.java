/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Controller;

import com.mycompany.projeto_lais.Model.Aluno_Atividade_Model;
import com.mycompany.projeto_lais.Model.Aluno_Model;
import com.mycompany.projeto_lais.Model.Atividade_Model;
import static com.mycompany.projeto_lais.Model.Atividade_Model_.aluno;
import static com.mycompany.projeto_lais.Model.Atividade_Model_.unidade;
import com.mycompany.projeto_lais.Model.Dao.Aluno_Atividade_dao;
import com.mycompany.projeto_lais.Model.Dao.Aluno_dao;
import com.mycompany.projeto_lais.Model.Dao.Atividade_dao;
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
    private List<Atividade_Model> lista_t;
    private List<Aluno_Model> lista_a;
    private Frequencia_dao dao_aa;
    private Atividade_dao dao_t;
    private double nota1;
    private double nota2;
    private double nota3;
    private double media;
    private int[] faltas;
    private int frequencia;
    private boolean termino= false;
    public Validacao validacao;

    
    DecimalFormat df = new DecimalFormat("#.##");


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
        this.dao_t = new Atividade_dao();
        this.lista_a = new ArrayList<>();
         dm = (DefaultTableModel) view.getjTable1().getModel(); 
         System.out.println("vai por favor");
        
    }

    
    public void iniciar() {
        view.setExtendedState(Materia.MAXIMIZED_BOTH);
        atualizar();
        view.getjTextField1().setDocument( new Validacao(60));
    }
    public void atualizar_dados(){
    
    }

    public void atualizar() {
       pesquisar();
        termino= false;
        valor_Maximo();
        
        if (view.getjTable1().getModel().getRowCount() > 0) {
            for (int i = view.getjTable1().getModel().getRowCount() - 1; i >= 0; i--) {
                dm.removeRow(i);
            }  
            view.getjTable1().setModel(dm);
        }
        String[] dados = new String[9];
        for (Aluno_Model a : lista_a) {
            a.setSituacao(passarOrNot(a, media, media));
            double recuperacao = 0;
            nota1 = soma_Das_Notas(a, "Unidade 1");
            nota2 = soma_Das_Notas(a, "Unidade 2");
            nota3 = soma_Das_Notas(a, "Unidade 3");
            faltas = faltas(a);
            frequencia = 100-((faltas[0]*100)/faltas[1]);
            media = (nota1+nota2+nota3)/3;
            if (dao_at.findByAlunoUnidade(a, "Recuperação", turmamateria).size()!=0)
            recuperacao = dao_at.findByAlunoUnidade(a, "Recuperação", turmamateria).get(0).getValor_recebido();
             a.setSituacao(passarOrNot(a, media, recuperacao));
             dao.update(a);
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
            String coluna="nome";
//            switch (view.getjComboBox1().getSelectedIndex()) {
//                case 0:
//                  coluna="nome";
//                    break;
//                case 1:
//                    coluna="nFaltas";
//                    break;
//                case 2:
//                    coluna="frequencia";
//                    break;
//                case 3:
//                    coluna="situacao";
//                    break;
//                case 4:
//                    coluna="nota1";
//                    break;
//                case 5:
//                    coluna="nota2";
//                    break;
//                case 6:
//                    coluna="nota3";
//                    break;
//                case 7:
//                    coluna="recuperacao";
//                    break;
//                    case 8:
//                        coluna="media";
//                    break;
//                default:
//                    throw new AssertionError();
//            }
            if (view.getjTable1().getModel().getRowCount() > 0) {
            for (int i = view.getjTable1().getModel().getRowCount() - 1; i >= 0; i--) {
                dm.removeRow(i);
            }
            view.getjTable1().setModel(dm);
        }
           String texto = view.getjTextField1().getText();
                   lista_a = dao.find(turmamateria.getTurma(), coluna, texto);

    }
        else {
             lista_a =dao.findByTurma(turmamateria.getTurma());
        } }

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
       lista_At = dao_at.findByAlunoUnidade(aluno, unidade, turmamateria); 
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
     public void valor_Maximo() {
       double valor_total = 0;
        double valor_restante = 30;
        double divisor = 1;
        
         for (int i=1; i<4; i++){
        lista_t = dao_t.findByTurmaMateriaUnidade(turmamateria, "Unidade "+i);
        if (lista_t.size()!=0 &&  lista_t.get(0).getCalculo().equals("Maior Nota"))
            valor_restante = valor_restante-10;
         valor_total = 0;
        for (Atividade_Model atvd : lista_t) {
            switch (atvd.getCalculo()) {
                case "Soma simples":
                    valor_total = valor_total + atvd.getValor();
                    

                    break;
                case "Média Aritmética":
                    valor_total = valor_total;
                   
                    break;
                case "Média Ponderada":
                    valor_total = valor_total + atvd.getPeso();
               

                    break;
                case "Soma com Divisor Informado":
                    divisor = atvd.getDivisor();
                    valor_total = valor_total + atvd.getValor();
               

                    break;
                case "Maior Nota":
                    valor_total = valor_total;
                    
                    break;
                default:
                    break;
            }
        }
        System.out.println(" valor total " + (valor_total) / divisor);
        valor_restante = valor_restante - (valor_total) / divisor;
        System.out.println("Valor restante" + valor_restante);}
        if (valor_restante==0.0){
            termino=true;
            System.out.println("termino "+termino);
        }

    }
    public String passarOrNot(Aluno_Model aluno, double media, double recuperacao){
        String situacao;
        if (termino){
        if (media>=5){
            situacao = "Aluno aprovado por nota";
        }
        else {
            if (recuperacao>5){
                situacao = "Aluno aprovado por recuperação";
            }
            else{
                situacao = "Aluno reprovado";
            }
        }}
        else {
            situacao = "Cursando";
        }
        return situacao;
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
            if (quantidade_faltas[1]==0.0){
                quantidade_faltas[1]=1;
            }
            return quantidade_faltas;
    }

}
