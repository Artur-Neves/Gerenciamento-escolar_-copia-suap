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
    private float nota1;
    private float nota2;
    private float nota3;
    private float recu;
    private Aluno_Model aluno;
    private Atividade_Model atividade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getNota1() {
        return nota1;
    }

    public void setNota1(float nota1) {
        this.nota1 = nota1;
    }

    public float getNota2() {
        return nota2;
    }

    public void setNota2(float nota2) {
        this.nota2 = nota2;
    }

    public float getNota3() {
        return nota3;
    }

    public void setNota3(float nota3) {
        this.nota3 = nota3;
    }

    public float getRecu() {
        return recu;
    }

    public void setRecu(float recu) {
        this.recu = recu;
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
