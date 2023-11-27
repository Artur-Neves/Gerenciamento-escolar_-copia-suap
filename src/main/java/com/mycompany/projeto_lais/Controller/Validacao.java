/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Controller;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author devjava
 */
public class Validacao extends PlainDocument {
    int limite;
    int contador=0;
  

    public Validacao(int limite) {
    super();
        this.limite = limite;
    }
    
    public void somentenumero(java.awt.event.KeyEvent evt) {

            String caracteres = "0123456789.";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
       
        }}
    
      public void insertString(int ofs, String str, AttributeSet a) throws BadLocationException{
          if ((getLength() + str.length())<= limite ) {
              super.insertString(ofs, str, a);
          }
}
}