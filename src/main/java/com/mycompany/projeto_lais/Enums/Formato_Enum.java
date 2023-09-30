/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Enums;

/**
 *
 * @author Artur
 */
public enum Formato_Enum {
    Presencial("Presencial"),
    Online("Online");
    private String unidade;
    private Formato_Enum (String unidade){
        this.unidade=unidade;
    }
    public String toString(){
        return unidade;
    }
}
