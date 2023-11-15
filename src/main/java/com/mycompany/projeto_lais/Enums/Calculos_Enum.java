/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Enums;

/**
 *
 * @author Artur
 */
public enum Calculos_Enum {
    Soma_Simples ("Soma simples"),
    Media_Aritmetica ("Média Aritmética"),
    Media_Ponderada ("Média Ponderada"),
    Soma_com_Divisor_Informado ("Soma com Divisor Informado");
    private String calculo;

    private Calculos_Enum(String calculo) {
    this.calculo = calculo;
    }

    @Override
    public String toString() {
        return calculo;
    }
    
    
}
