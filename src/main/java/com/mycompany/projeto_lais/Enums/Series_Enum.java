/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Enums;

/**
 *
 * @author Artur
 */
public enum Series_Enum {
    Ano1("1° Ano"),
    Ano2("2° Ano"),
    Ano3("3° Ano");
    private String serie;
    private Series_Enum (String serie){
        this.serie=serie;
    }
    public String toString(){
        return serie;
    }
    
}
