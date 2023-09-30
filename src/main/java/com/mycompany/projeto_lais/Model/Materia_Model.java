/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Artur
 */
@Entity
@Table(name="Materia")
public class Materia_Model{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMatricula;
    @Column(unique=true)
    private String nome;
    @Lob
    @Column(name = "imagem", columnDefinition = "bytea")
    private byte[] imagem;
    @ManyToMany(mappedBy="materia")
    private List<Turma_Model> turma = new ArrayList<>();

    public Materia_Model(int idMatricula, String nome, byte[] imagem, List<Turma_Model> turma) {
        this.idMatricula = idMatricula;
        this.nome = nome;
        this.imagem = imagem;
        this.turma = turma;
    }
   
     public Materia_Model(int idMatricula, String nome,  List<Turma_Model> turma) {
        this.idMatricula = idMatricula;
        this.nome = nome;
        this.turma=turma;
    }


    public Materia_Model(int idMatricula, String nome, byte[] imagem) {
        this.idMatricula = idMatricula;
        this.nome = nome;
        this.imagem = imagem;
    }

    public Materia_Model(int idMatricula, String nome) {
        this.idMatricula = idMatricula;
        this.nome = nome;
    }
    public Materia_Model( String nome, byte[] imagem) {
        this.nome = nome;
         this.imagem = imagem;
    }
 public Materia_Model( String nome) {
        this.nome = nome;
    }
    public Materia_Model() {
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public List<Turma_Model> getTurma() {
        return turma;
    }
     public void setTurma(List<Turma_Model> list) {
        turma=list;
    }
 
   
    
}
