/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.Length;

/**
 *
 * @author Artur
 */
@Entity (name="atividade")
@Table (name = "atividade")
public class Atividade_Model {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
     private int id;
    private String Calculo;
    private Double valor;
    private Date data;
    @Column(length=Length.LONG)
    private String descricao;
    // tem que mudar as laranjadas aqui 
    private int divisor;

    private String unidade;
    
    @ManyToMany(cascade = { CascadeType.MERGE})
    private List<Aluno_Model> aluno = new ArrayList<>();
     private Turma_Materia_Model turmamateria;
    public Atividade_Model() {
    }

    public Atividade_Model(int id, String metodo, Double notaMaxima, Date dataDeAnexo, int divisor) {
        this.id = id;
        this.Calculo = Calculo;
        this.valor = notaMaxima;
        this.data = dataDeAnexo;
        this.divisor = divisor;
    }

    public Atividade_Model(String formato, Double valor, Date data, String Descricao, String unidade, Turma_Materia_Model turmamateria, int divisor) {
        this.Calculo = formato;
        this.valor = valor;
        this.data = data;
        this.descricao = Descricao;
        this.unidade = unidade;
        this.turmamateria = turmamateria;
        this.divisor = divisor;
    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDivisor() {
        return divisor;
    }

    public void setDivisor(int divisor) {
        this.divisor = divisor;
    }
    
    

    public String getCalculo() {
        return Calculo;
    }

    public void setCalculo(String calculo) {
        this.Calculo = calculo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String Descricao) {
        this.descricao = Descricao;
    }

    public List<Aluno_Model> getAluno() {
        return aluno;
    }

    public void setAluno(List<Aluno_Model> aluno) {
        this.aluno = aluno;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Turma_Materia_Model getTurmamateria() {
        return turmamateria;
    }

    public void setTurmamateria(Turma_Materia_Model turmamateria) {
        this.turmamateria = turmamateria;
    }
 public void addAluno(Aluno_Model aluno){
       this.aluno.add(aluno);
       aluno.getAula().remove(this);
   }
   public void removeAluno(Aluno_Model aluno){
       for (Aluno_Model a : this.aluno) {
          if(a.getNome().equals(aluno.getNome())){
              this.aluno.remove(a);
              break;
          }
       }
       this.aluno.remove(aluno);
       aluno.getAula().remove(this);
   }
   
    
}
