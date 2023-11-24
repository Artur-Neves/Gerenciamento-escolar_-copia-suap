/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Controller;

import com.mycompany.projeto_lais.Enums.Calculos_Enum;
import com.mycompany.projeto_lais.Enums.Unidade_Enum;
import com.mycompany.projeto_lais.Model.Aluno_Model;
import com.mycompany.projeto_lais.Model.Atividade_Model;
import com.mycompany.projeto_lais.Model.Dao.Aluno_Atividade_dao;
import com.mycompany.projeto_lais.Model.Dao.Aluno_dao;
import com.mycompany.projeto_lais.Model.Dao.Atividade_dao;
import com.mycompany.projeto_lais.Model.Dao.Materia_dao;
import com.mycompany.projeto_lais.Model.Dao.Turma_Materia_dao;
import com.mycompany.projeto_lais.Model.Dao.Turma_dao;
import com.mycompany.projeto_lais.Model.Turma_Materia_Model;
import com.mycompany.projeto_lais.Model.Turma_Model;
import com.mycompany.projeto_lais.View.Cadastro_Atividade;
import com.mycompany.projeto_lais.View.Menssagem_De_Confirmacao;
import java.awt.Color;
import java.awt.Toolkit;
import java.util.Date;
import java.util.List;

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
    private List<Atividade_Model> lista;
    private boolean confirmacao = true;
    private String unidade;
    private Double valor;
    private Date data;
    private String formato;
    private String descricao;
    private int divisor;
    private double peso;
    private Aluno_Atividade_dao dao_at;
    private Menssagem_De_Confirmacao m;
    private double valido;
    public Validacao validacao;
    private boolean erro;

    public Cadastro_Atividade_Controller(Cadastro_Atividade view, Turma_Materia_Model turmamateria) {
        this.view = view;
        dao_aluno = new Aluno_dao();
        dao_turma = new Turma_dao();
        dao_materia = new Materia_dao();
        dao_tm = new Turma_Materia_dao();
        dao = new Atividade_dao();
        this.model = new Atividade_Model();
        model.setValor(0.0);
        model.setPeso(0);
        dao_at = new Aluno_Atividade_dao();
        this.turmamateria = dao_tm.findbyturmamateria(turmamateria);
     validacao = new Validacao(20);
    }

    public Cadastro_Atividade_Controller(Cadastro_Atividade view, Atividade_Model model, Turma_Materia_Model turmamateria) {
        this.view = view;
        dao_aluno = new Aluno_dao();
        dao_turma = new Turma_dao();
        dao_materia = new Materia_dao();
        dao_tm = new Turma_Materia_dao();
        dao = new Atividade_dao();
        dao_at = new Aluno_Atividade_dao();
        this.model = model;
        valido = model.getValor();
         validacao = new Validacao(20);

        this.turmamateria = dao_tm.findbyturmamateria(turmamateria);

    }

    public void iniciar() {

        view.getjComboBox3().removeAll();
        for (Calculos_Enum formato : Calculos_Enum.values()) {
            view.getjComboBox3().addItem(formato.toString());
        }
        view.getjComboBox4().removeAll();
        for (Unidade_Enum unidade : Unidade_Enum.values()) {
            view.getjComboBox4().addItem(unidade.toString());
        }

        if (!view.getjButton3().getText().equals("Salvar")) {
            view.getjComboBox3().setSelectedItem(model.getCalculo());
            view.getjTextField2().setText("" + model.getDivisor());
            view.getjComboBox4().setSelectedItem(model.getUnidade());
            view.getjTextField1().setText("" + model.getValor());
            view.getjDateChooser1().setDate(model.getData());
            view.getjTextArea2().setText(model.getDescricao());
            view.getjTextField3().setText("" + model.getPeso());

        } else {
            atualizar();
        }
           view.getjTextField1().setDocument( new Validacao(5));
            view.getjTextField1().setDocument( new Validacao(5));
             view.getjTextField1().setDocument( new Validacao(5));
             view.getjTextArea2().setDocument( new Validacao(1000));

    }

    public void atualizar() {
        lista = dao.findByTurmaMateriaUnidade(turmamateria, "" + view.getjComboBox4().getSelectedItem());
        if (lista.size() > 0) {
            System.out.println("tamanho da lista" + lista.size());
            view.getjComboBox3().setSelectedItem(lista.get(0).getCalculo());
            view.getjComboBox3().setEnabled(false);

        } else {
            view.getjComboBox3().setEnabled(true);

        }

        view.getjLabel5().setVisible(false);
        view.getjTextField2().setVisible(false);
        view.getjTextField1().setEnabled(false);
        view.getjTextField3().setEnabled(false);
        view.getjLabel6().setVisible(false);
        view.getjTextField3().setVisible(false);

        switch (view.getjComboBox3().getSelectedIndex()) {
            // soma simples
            case 0:
                view.getjTextField1().setEnabled(true);
                break;
            //Média Aritimetica
            case 1:
                view.getjTextField1().setText("10");
                break;
            // maedia aritmética Peso
            case 2:
                view.getjTextField1().setText("10");
                view.getjTextField3().setEnabled(true);
                view.getjLabel6().setVisible(true);
                view.getjTextField3().setVisible(true);
                break;
            // soma com divisor informado
            case 3:
                view.getjLabel5().setVisible(true);
                view.getjTextField1().setEnabled(true);
                view.getjTextField2().setVisible(true);
                if (lista.size() > 0) {
                    view.getjTextField2().setText("" + lista.get(0).getDivisor());
                    view.getjTextField2().setEnabled(false);

                } else {
                    view.getjTextField2().setEnabled(true);
                }

                break;
            // maior nota
            case 4:
                view.getjTextField1().setText("10");
                break;

            default:
                throw new AssertionError();
        }
    }

        public void atualizar_Dados() {
        try {
            unidade = "" + view.getjComboBox4().getSelectedItem();
            valor = Double.parseDouble(view.getjTextField1().getText());
            data = view.getjDateChooser1().getDate();
            formato = "" + view.getjComboBox3().getSelectedItem();
            descricao = view.getjTextArea2().getText();

            if (view.getjComboBox3().getSelectedIndex() == 3) {
                divisor = Integer.parseInt(view.getjTextField2().getText());
            } else {
                divisor = 1;
            }
            lista = dao.findByTurmaMateriaUnidade(turmamateria, unidade);
        } catch (Exception e) {
            System.out.println("Problema no atualizar_Dados: " + e.getMessage());
        }
        if (view.getjTextField3().getText().trim().isEmpty() || view.getjComboBox3().getSelectedIndex() != 2) {
            // 
            peso = 0;
        } else {
            peso = Double.parseDouble(view.getjTextField3().getText());

        }
        

    }

    public void inserir() {
        atualizar_Dados();
        confirmacao = true;
        double comparar;
        
            if (view.getjDateChooser1().getDate()!=null){
        if (formato.equals("Média Ponderada")) {
            comparar = peso;
        } else {
            comparar = valor;
        }

        if (view.getjButton3().getText().equals("Salvar")) {
            model = new Atividade_Model(formato, valor, data, descricao, unidade, turmamateria, divisor, peso);
 if (valor_Maximo() >= comparar) {
            if (lista.size() == 0) {
                Menssagem_De_Confirmacao mc = new Menssagem_De_Confirmacao(null, true, "Uma vez que você escolhar um método de avaliação para",
                        "essa unidade, não será mais possível mudar esse método!", "Atenção");
                mc.setVisible(true);
                confirmacao = mc.retornar();
            }
            if (confirmacao) {

               

                    System.out.println(peso);
                    if (dao_aluno.findByTurma(turmamateria.getTurma()) != null) {
                        for (Aluno_Model aluno_model : dao_aluno.findByTurma(turmamateria.getTurma())) {
                            model.addAluno(dao_aluno.findByNome(aluno_model.getNome()));

                        }
                    }
                    if (dao.insert(model)) {
//               for (Aluno_Model aluno_model : dao_aluno.findByTurma(turmamateria.getTurma())){
//                   aa = dao_f.findByAlunoAula(aluno_model, model);
//
//               if( dao_f.update(f)){
//                   
//               }
//            }
                        view.imprimir_Na_Tela("Atividade inserida com sucesso!");

                    }
                    view.hide();
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }

        } else if (view.getjButton3().getText().equals("Editar")) {

            model.setUnidade(unidade);
            model.setData(data);
            model.setCalculo(formato);
            model.setDescricao(descricao);
            
            model.setValor(valor);
            model.setPeso(peso);
            if (formato.equals("Média Ponderada")) {
                comparar = model.getPeso();
            } else {
                comparar = model.getValor();
            }
            if ((valor_Maximo() + valido) >= comparar) {
                if (valido != comparar) {
                    m = new Menssagem_De_Confirmacao(null, true, "Ao editar a nota de uma atividade é apagado o registro do valor ", " obtido pelo aluno, deseja continuar?", "Atenção");
                    m.setVisible(true);
                    confirmacao = m.retornar();
                    if (confirmacao)
                        dao_at.ExcluirNotasAtividade(model);
                }
                if (dao.update(model) && confirmacao) {

                    

                    view.imprimir_Na_Tela("Atividade editada com sucesso!");
                    view.hide();
                } 
              
            } 
        } else {
            if (dao.delete(model)) {
                view.imprimir_Na_Tela("Atividade excluida com sucesso!");
                view.hide();
            }
        }
        }
            else{
                m = new Menssagem_De_Confirmacao(null, true, "Insira uma data válida", "", "Atenção");
                    m.setVisible(true);
            }
        }
        
    

    public double valor_Maximo() {
        atualizar_Dados();
        double valor_total = 0;
        double valor_restante = 10;
       
        
        if ( lista.size()==0 && formato.equals("Soma com Divisor Informado")){
            if ((valor/divisor)>10){
             
            }
            else {
                valor_restante = 100;    
            }
       ;
        }
        
        
        for (Atividade_Model atvd : lista) {
            switch (atvd.getCalculo()) {
                case "Soma simples":
                    valor_total = valor_total + atvd.getValor();
                    valor_restante = 10;

                    break;
                case "Média Aritmética":
                    valor_total = valor_total;
                    valor_restante = 10;
                    break;
                case "Média Ponderada":
                    valor_total = valor_total + atvd.getPeso();
                    valor_restante = 10;

                    break;
                case "Soma com Divisor Informado":
                    divisor = atvd.getDivisor();
                    valor_total = valor_total + atvd.getValor();
                    valor_restante = 10;

                    break;
                case "Maior Nota":
                    valor_total = valor_total;
                    valor_restante = 10;

                    break;
                default:
                    break;
            }
        }
        System.out.println(" valor total " + (valor_total) / divisor);
        valor_restante = valor_restante - (valor_total) / divisor;
        System.out.println("Valor restante" + valor_restante);
        return valor_restante * divisor;

    }
    

    public void mudarcor() {

        if ((valor_Maximo() + model.getValor()) < valor) {
            view.getjTextField1().setForeground(Color.red);
        } else {
            view.getjTextField1().setForeground(Color.green);
        }
        if ((valor_Maximo() + model.getPeso()) < peso) {
            view.getjTextField3().setForeground(Color.red);
        } else {
            view.getjTextField3().setForeground(Color.green);
        }
    }
}
