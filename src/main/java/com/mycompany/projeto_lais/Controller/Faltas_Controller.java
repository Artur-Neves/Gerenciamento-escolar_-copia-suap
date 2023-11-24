/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Controller;

import com.mycompany.projeto_lais.Model.Aluno_Model;
import com.mycompany.projeto_lais.Model.Aula_Model;
import com.mycompany.projeto_lais.Model.Dao.Aluno_dao;
import com.mycompany.projeto_lais.Model.Dao.Aula_dao;
import com.mycompany.projeto_lais.Model.Dao.Frequencia_dao;
import com.mycompany.projeto_lais.Model.Frequencia_Model;
import com.mycompany.projeto_lais.Model.Turma_Materia_Model;
import com.mycompany.projeto_lais.View.Aluno;
import com.mycompany.projeto_lais.View.Atividade;
import com.mycompany.projeto_lais.View.Aula;
import com.mycompany.projeto_lais.View.Faltas;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Artur
 */
public class Faltas_Controller {

    private Faltas view;
    private Aula_dao daoAula;
    private Aula_Model modelAula;
    private Turma_Materia_Model modelTm;
    private Aluno_Model modelAluno;
    private Aluno_dao daoAluno;
    private Frequencia_dao daof;
    private ArrayList<Localizacao> antes;
    private ArrayList<Localizacao> depois;
    private DefaultTableModel dm;
    Localizacao localizacao;
  
    Map<Integer, Aula_Model> data;
    Map<Integer, Aluno_Model> nome;
    
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    private Validacao validacao;
    public Faltas_Controller(Faltas view, Turma_Materia_Model modelTm) {
        dm = (DefaultTableModel) view.getjTable1().getModel();
        this.view = view;
        this.modelTm = modelTm;
        daoAula = new Aula_dao();
        daoAluno = new Aluno_dao();
        daof = new Frequencia_dao();
        System.out.println(modelTm.getId());
        antes= new ArrayList<>();
        depois= new ArrayList<>();
        
        data = new HashMap<>();
        nome = new HashMap<>();

    }

    public void iniciar() {
        atualizar();
    }

    public void atualizar() {
nome.clear();
data.clear();
        if (view.getjTable1().getModel().getRowCount() > 0) { 
            for (int i = view.getjTable1().getModel().getRowCount() - 1; i >= 0; i--) {
                dm.removeRow(i);
            }
            dm.setColumnCount(1);
            view.getjTable1().setModel(dm);

        }

        List<Frequencia_Model> lista = new ArrayList<>();
        dm = (DefaultTableModel) view.getjTable1().getModel();
        String[] nomes = new String[11];
        int colunaData=1;
        String[] dados = new String[12];
        if (view.getjDateChooser1().getDate() != null) {
            for (Aula_Model aula : daoAula.findDataByTurmaMateria(modelTm, view.getjDateChooser1().getDate())) {
                data.put(colunaData, aula);
                dm.addColumn(formato.format(aula.getData())+"      "+aula.getQuantidade()+" aulas");
                colunaData++;
            }
        } else {
            for (Aula_Model aula : daoAula.findDataByTurmaMateria(modelTm)) {
                  data.put(colunaData, aula);
                dm.addColumn(formato.format(aula.getData())+"      "+aula.getQuantidade()+" aulas");
                
                colunaData++;
            }
        }
        int linhaNome=0;
        for (Aluno_Model aluno : daoAluno.findByTurma(modelTm.getTurma())) {
            nomes[0] = aluno.getNome();
            nome.put(linhaNome, aluno);
            linhaNome++;
            int i = 1;
                    if (view.getjDateChooser1().getDate() == null) {
                        lista = daof.findByAluno(aluno, modelTm);
            }
                    else{
                        lista = daof.findByAluno(aluno, modelTm, view.getjDateChooser1().getDate());
                    }
            if (lista != null) {
                for (Frequencia_Model f : lista) {
                    if ("" + f.getFaltas() == null) {
                        nomes[i] = "" + 0;
                    } else {
                        nomes[i] = "" + f.getFaltas();
                    }

                    i++;
                }
            }
           
            dm.addRow(nomes);

        };
antes =valorcelulas();
    }

    public void entrarAluno() {
        Aluno aluno = new Aluno(modelTm.getTurma(), modelTm.getMateria());
        aluno.setVisible(true);
        view.hide();
    }

    public void entrarAtividade() {
        Atividade atividade = new Atividade(modelTm);
        atividade.setVisible(true);
        view.hide();
    }

    public void salvar() {
        depois =  valorcelulas();
 
   
        if (antes==depois){
            System.out.println("não mudou");
            
        }
        else{
            boolean validade= true;
            List <Frequencia_Model> frequencia = new ArrayList<>();
            for (int i = 0; i < depois.size(); i++) {
                    Aluno_Model alunoc = nome.get(depois.get(i).linha);
                    Aula_Model aulac = data.get(depois.get(i).coluna);
                if (isInteger(depois.get(i).valor)){
                    int a = Integer.parseInt(depois.get(i).valor);
                    int b = Integer.parseInt(antes.get(i).valor);
                    if (a!=b){
                        Frequencia_Model f =daof.findByAlunoAula(alunoc, aulac);
                        if (a<= aulac.getQuantidade()){
                            f.setFaltas(a);
                            if(daof.update(f)){
                                frequencia.add(f);
                            }
                            else {
                                Toolkit.getDefaultToolkit().beep();
                                view.imprimirNaTela("valor invalido", "Erro 100");
                                validade=false;
                                 
                            }
                        }
                        else{
                            Toolkit.getDefaultToolkit().beep();
                            view.imprimirNaTela("A falta do aluno(a) '"+alunoc.getNome()+"' do dia '"+formato.format(aulac.getData())+"' ultrapassou a quantidade das aulas", "Error Valor inválido");
                        validade = false;
                                                 }
                        
            System.out.print(nome.get(depois.get(i).linha).getIdAluno()+"  ");System.out.println(nome.get(antes.get(i).linha));
            System.out.print(""+data.get(depois.get(i).coluna).getIdAula()+"  ");System.out.println(""+data.get(antes.get(i).coluna));
            
                }
                }
                else{
                    if (depois.get(i).coluna!=0){
                        Toolkit.getDefaultToolkit().beep();
                   view.imprimirNaTela("A falta do aluno(a) '"+alunoc.getNome()+"' do dia '"+formato.format(aulac.getData())+"' não é do tipo inteiro", "Error Valor inválido");
                validade=false;
                 }
                }
                
            }
            if (validade){
                view.imprimirNaTela("Faltas regitradas com sucesso!", "Sucess");
                atualizar();
            }
            
           
           
            
        }
     
    }


    public ArrayList<Localizacao> valorcelulas() {
        dm = (DefaultTableModel) view.getjTable1().getModel();
        ArrayList<Localizacao> lista = new ArrayList<>();

          int numColumn = dm.getColumnCount();
          int numrow = dm.getRowCount();
           for (int linha = 0; linha < numrow; linha++) {
            for (int coluna = 0; coluna < numColumn; coluna++) {
                
               
       
                // ler a linha toda
                localizacao = new Localizacao(coluna, linha, (String) dm.getValueAt( linha, coluna));
                lista.add(localizacao);
             
            }

        } return lista;
    }

    public void voltar() {
        Aula aula = new Aula(modelTm);
       aula.setVisible(true);
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
        return str != null && str.matches("[0-9]*");
    }
}

