/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Enums;

/**
 *
 * @author Artur
 */
public enum Unidade_Enum {
    Unidade1("Unidade 1"),
    Unidade2("Unidade 2"),
    Unidade3("Unidade 3");
    private String unidade;
    private Unidade_Enum (String unidade){
        this.unidade=unidade;
    }
    public String toString(){
        return unidade;
    }
}
