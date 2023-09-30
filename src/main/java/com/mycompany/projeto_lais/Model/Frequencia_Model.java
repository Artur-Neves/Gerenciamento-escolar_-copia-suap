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
@Entity(name ="aula_aluno")
@Table (name ="aula_aluno")
public class Frequencia_Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Aluno_Model aluno;
    private Aula_Model aula;

    private float frequencia=0;

    private int faltas=0;
   
    
    public Frequencia_Model() {
         this.frequencia = 0; // Valor padrão para frequencia
        this.faltas = 0;     // Valor padrão para faltas
    }

    public Frequencia_Model(int id, Aluno_Model idAuluno, Aula_Model idAula) {
        this.id = id;
        this.aluno = idAuluno;
        this.aula = idAula;
    }

    public Frequencia_Model(int id, Aluno_Model idAuluno, Aula_Model idAula, float frequencia, int faltas) {
        this.id = id;
        this.aluno = idAuluno;
        this.aula = idAula;
        this.frequencia = frequencia;
        this.faltas = faltas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Aluno_Model getIdAuluno() {
        return aluno;
    }

    public void setIdAuluno(Aluno_Model idAuluno) {
        this.aluno = idAuluno;
    }

    public Aula_Model getIdAula() {
        return aula;
    }

    public void setIdAula(Aula_Model idAula) {
        this.aula = idAula;
    }

    public float getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(float frequencia) {
        this.frequencia = frequencia;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

     

    
}
