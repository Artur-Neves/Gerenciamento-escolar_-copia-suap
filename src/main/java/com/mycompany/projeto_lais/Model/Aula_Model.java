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
@Entity (name = "aula") 
@Table (name="aula")
public class Aula_Model {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idAula;
    private String unidade;
    private Date data;
    private int quantidade;
    private String formato;
     @Column(length=Length.LONG)
    private String conteudo;
    @Column(length=Length.LONG)
    private String obs;
          @ManyToMany(cascade = { CascadeType.MERGE})
    private List<Aluno_Model> aluno = new ArrayList<>();
    private Turma_Materia_Model turmamateria;

    public Aula_Model( String unidade,Date data, int quantidade, String conteudo, String obs, String formato ,Turma_Materia_Model turmamateria) {
        this.unidade=unidade;
        this.data = data;
        this.quantidade = quantidade;
        this.conteudo = conteudo;
        this.obs = obs;
        this.formato=formato;
        this.turmamateria = turmamateria;
    }
    

    public Aula_Model() {
    }


    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }
    

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
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
