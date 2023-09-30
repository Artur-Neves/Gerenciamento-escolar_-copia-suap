/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Formula;


/**
 *
 * @author Artur
 */
@Entity (name = "aluno")
public class Aluno_Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAluno;
    private String nome;
    @ColumnDefault("0")
    private int nPresenca;
    @ColumnDefault("Cursando")
    private String situacao;
    @ColumnDefault("0")
    private int nFaltas;
    @Formula(value ="nFaltas-nPresenca")
    private int frequencia;
    @ManyToOne
    private Turma_Model turma;
     @ManyToMany(mappedBy="aluno")
    public List<Aula_Model> aula = new ArrayList<>();
     @ManyToMany(mappedBy="aluno")
    public List<Atividade_Model> atividade = new ArrayList<>();

    public Aluno_Model(String nome, Turma_Model turma) {
        this.nome = nome;
        this.turma = turma;
    }

    public Aluno_Model(int idAluno, String nome, int nPresenca, String situacao, int nFaltas, int frequencia, Turma_Model turma) {
        this.idAluno = idAluno;
        this.nome = nome;
        this.nPresenca = nPresenca;
        this.situacao = situacao;
        this.nFaltas = nFaltas;
        this.frequencia = frequencia;
        this.turma = turma;
    }
    public Aluno_Model( String nome, String situacao, int nFaltas, Turma_Model turma) {
        this.nome = nome;
        this.situacao = situacao;
        this.nFaltas = nFaltas;
        this.turma=turma;
    }
    
    public Aluno_Model(String nome) {
        this.nome = nome;
    }

    

    public Aluno_Model() {
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getnPresenca() {
        return nPresenca;
    }

    public void setnPresenca(int nPresenca) {
        this.nPresenca = nPresenca;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public int getnFaltas() {
        return nFaltas;
    }

    public void setnFaltas(int nFaltas) {
        this.nFaltas = nFaltas;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }

    public Turma_Model getTurma() {
        return turma;
    }

    public void setTurma(Turma_Model turma) {
        this.turma = turma;
    }

    public List<Aula_Model> getAula() {
        return aula;
    }

    public void setAula(List<Aula_Model> aula) {
        this.aula = aula;
    }

    public List<Atividade_Model> getAtividade() {
        return atividade;
    }

    public void setAtividade(List<Atividade_Model> atividade) {
        this.atividade = atividade;
    }
//      public void addAula(Aula_Model aula){
//       this.aula.add(aula);
//       aula.getAluno().remove(this);
//   }
//   public void removeAluno(Aula_Model aula){
//       
//              this.aula.remove(aula);
//              
//       
//       this.aula.remove(aula);
//       aula.getAluno().remove(this);
//   }
//    
    
}
