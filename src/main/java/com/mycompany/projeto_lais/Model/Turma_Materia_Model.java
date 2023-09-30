/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Model;

import com.mycompany.projeto_lais.View.Materia;
import com.mycompany.projeto_lais.View.Turma;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Artur
 */
@Entity
@Table(name = "Turma_Materia")
public class Turma_Materia_Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   
    private Turma_Model turma;

    
    private Materia_Model materia;

    public Turma_Materia_Model(Turma_Model turma, Materia_Model materia) {
        this.turma = turma;
        this.materia = materia;
    }

    public Turma_Materia_Model() {
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Turma_Model getTurma() {
        return turma;
    }

    public void setTurma(Turma_Model turma) {
        this.turma = turma;
    }

    public Materia_Model getMateria() {
        return materia;
    }

    public void setMateria(Materia_Model materia) {
        this.materia = materia;
    }


}

