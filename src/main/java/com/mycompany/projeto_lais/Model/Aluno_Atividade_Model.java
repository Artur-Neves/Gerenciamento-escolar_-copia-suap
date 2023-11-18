/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.ColumnDefault;

/**
 *
 * @author Artur
 */
@Entity (name="atividade_aluno")
@Table(name="atividade_aluno")
public class Aluno_Atividade_Model {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
     private int id;
    @ColumnDefault("0.0")
    private float valor_recebido;
    private Aluno_Model aluno;
    private Atividade_Model atividade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValor_recebido() {
        return valor_recebido;
    }

    public void setValor_recebido(float valor_recebido) {
        this.valor_recebido = valor_recebido;
    }

    public Aluno_Model getAluno() {
        return aluno;
    }

    public void setAluno(Aluno_Model aluno) {
        this.aluno = aluno;
    }

    public Atividade_Model getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade_Model atividade) {
        this.atividade = atividade;
    }

    
    
    
   
}
