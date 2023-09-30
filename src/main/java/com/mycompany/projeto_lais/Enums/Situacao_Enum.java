/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Enums;

/**
 *
 * @author Artur
 */
public enum Situacao_Enum{
   APROVADORECUPERACAO("Aprovado(a) por recuperação"),
   APROVADO("Aprovado(a)"),
   RERPOVADO("Reprovado(a)"),
   CURSANDO("Cursando");
   private String situacao;

    private Situacao_Enum(String situacao) {
        this.situacao = situacao;
    }

    @Override
    public String toString() {
        return situacao;
    }
   
}
