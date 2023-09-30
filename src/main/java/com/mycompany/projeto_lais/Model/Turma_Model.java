/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Artur
 */
@Entity
@Table(name="Turma")
public class Turma_Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTurma;
    @Column(unique=true)
    private String nome;
    private String serie;
    @ManyToMany(cascade = { CascadeType.MERGE})
    public List<Materia_Model> materia = new ArrayList<>();
    @OneToMany(mappedBy="turma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Aluno_Model> alunos = new ArrayList<>();

    public Turma_Model(int idTurma, String nome, String serie, Materia_Model materia) {
        this.idTurma = idTurma;
        this.nome = nome;
        this.serie = serie;
        this.materia.add(materia);
    }

    public Turma_Model(int idTurma, String nome, String serie) {
        this.idTurma = idTurma;
        this.nome = nome;
        this.serie = serie;
    }
 public Turma_Model(String nome, String serie, List<Materia_Model> materia) {
        this.nome = nome;
        this.serie = serie;
        this.materia= materia;
    }
    public Turma_Model(String nome, String serie, Materia_Model materia) {
        this.nome = nome;
        this.serie = serie;
        this.materia.add(materia);
    }
    public Turma_Model(String nome, String serie) {
        this.nome = nome;
        this.serie = serie;
        
    }


    public Turma_Model() {
    }
    

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public List<Materia_Model> getMateria() {
        return materia;
    }

    public List<Aluno_Model> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno_Model> alunos) {
        this.alunos = alunos;
    }
    
   public void addMateria(Materia_Model materia){
       this.materia.add(materia);
       materia.getTurma().remove(this);
   }
   public void removeMateria(Materia_Model materia){
       for (Materia_Model m : this.materia) {
          if(m.getNome().equals(materia.getNome())){
              this.materia.remove(m);
              break;
          }
       }
       this.materia.remove(materia);
       materia.getTurma().remove(this);
   }
   public void removeAluno(Aluno_Model aluno){
       for (Aluno_Model m : this.alunos) {
          if(m.getNome().equals(aluno.getNome())){
              this.alunos.remove(m);
              break;
          }
       }
       
   }
    
    

   
    
    
}
