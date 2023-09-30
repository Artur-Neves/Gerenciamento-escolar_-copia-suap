/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.projeto_lais.View;

import com.mycompany.projeto_lais.Controller.Turma_Controller;
import com.mycompany.projeto_lais.Model.Materia_Model;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

/**
 *
 * @author Artur
 */
public class Turma extends javax.swing.JFrame {
    
Turma_Controller c;
    /**
     * Creates new form Turma
     */

    public Turma() {
        initComponents();
        iniciar();
    }
    public Turma(Materia_Model materia) {
        initComponents();
        c = new Turma_Controller(this, materia);
        iniciar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn10 = new javax.swing.JButton();
        btn12 = new javax.swing.JButton();
        btn11 = new javax.swing.JButton();
        text1 = new javax.swing.JLabel();
        text2 = new javax.swing.JLabel();
        text3 = new javax.swing.JLabel();
        text4 = new javax.swing.JLabel();
        text5 = new javax.swing.JLabel();
        text6 = new javax.swing.JLabel();
        text7 = new javax.swing.JLabel();
        text8 = new javax.swing.JLabel();
        text9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btn1 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        text10 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        btn10.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn10.setText("+");
        btn10.setPreferredSize(new java.awt.Dimension(200, 80));

        btn12.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn12.setText("+");
        btn12.setPreferredSize(new java.awt.Dimension(200, 80));
        btn12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn12ActionPerformed(evt);
            }
        });

        btn11.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn11.setText("+");
        btn11.setPreferredSize(new java.awt.Dimension(200, 80));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        text1.setFont(new java.awt.Font("Lato", 1, 24)); // NOI18N
        text1.setForeground(new java.awt.Color(148, 0, 211));
        text1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        text1.setText("+");
        text1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(text1);
        text1.setBounds(124, 182, 398, 174);

        text2.setFont(new java.awt.Font("Lato", 1, 24)); // NOI18N
        text2.setForeground(new java.awt.Color(148, 0, 211));
        text2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        text2.setText("+");
        text2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(text2);
        text2.setBounds(570, 184, 398, 174);

        text3.setFont(new java.awt.Font("Lato", 1, 24)); // NOI18N
        text3.setForeground(new java.awt.Color(148, 0, 211));
        text3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        text3.setText("+");
        text3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(text3);
        text3.setBounds(1010, 182, 398, 174);

        text4.setFont(new java.awt.Font("Lato", 1, 24)); // NOI18N
        text4.setForeground(new java.awt.Color(148, 0, 211));
        text4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        text4.setText("+");
        text4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(text4);
        text4.setBounds(124, 374, 398, 174);

        text5.setFont(new java.awt.Font("Lato", 1, 24)); // NOI18N
        text5.setForeground(new java.awt.Color(148, 0, 211));
        text5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        text5.setText("+");
        text5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(text5);
        text5.setBounds(570, 374, 398, 174);

        text6.setFont(new java.awt.Font("Lato", 1, 24)); // NOI18N
        text6.setForeground(new java.awt.Color(148, 0, 211));
        text6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        text6.setText("+");
        text6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(text6);
        text6.setBounds(1012, 374, 398, 174);

        text7.setFont(new java.awt.Font("Lato", 1, 24)); // NOI18N
        text7.setForeground(new java.awt.Color(148, 0, 211));
        text7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        text7.setText("+");
        text7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(text7);
        text7.setBounds(124, 568, 398, 174);

        text8.setFont(new java.awt.Font("Lato", 1, 24)); // NOI18N
        text8.setForeground(new java.awt.Color(148, 0, 211));
        text8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        text8.setText("+");
        text8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(text8);
        text8.setBounds(570, 570, 398, 174);

        text9.setFont(new java.awt.Font("Lato", 1, 24)); // NOI18N
        text9.setForeground(new java.awt.Color(148, 0, 211));
        text9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        text9.setText("+");
        text9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(text9);
        text9.setBounds(1012, 568, 398, 174);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Turmas");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1524, 134);

        btn1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn1.setPreferredSize(new java.awt.Dimension(200, 80));
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn1);
        btn1.setBounds(123, 182, 400, 175);

        btn4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn4.setPreferredSize(new java.awt.Dimension(200, 80));
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });
        getContentPane().add(btn4);
        btn4.setBounds(123, 375, 400, 175);

        btn2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn2.setPreferredSize(new java.awt.Dimension(200, 80));
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });
        getContentPane().add(btn2);
        btn2.setBounds(569, 182, 400, 175);

        btn9.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn9.setPreferredSize(new java.awt.Dimension(200, 80));
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });
        getContentPane().add(btn9);
        btn9.setBounds(1009, 568, 400, 175);

        btn7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn7.setPreferredSize(new java.awt.Dimension(200, 80));
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });
        getContentPane().add(btn7);
        btn7.setBounds(123, 568, 400, 175);

        btn8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn8.setPreferredSize(new java.awt.Dimension(200, 80));
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });
        getContentPane().add(btn8);
        btn8.setBounds(569, 568, 400, 175);

        btn3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn3.setPreferredSize(new java.awt.Dimension(200, 80));
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });
        getContentPane().add(btn3);
        btn3.setBounds(1009, 182, 400, 175);

        btn6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn6.setPreferredSize(new java.awt.Dimension(200, 80));
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });
        getContentPane().add(btn6);
        btn6.setBounds(1009, 375, 400, 175);

        btn5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn5.setPreferredSize(new java.awt.Dimension(200, 80));
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });
        getContentPane().add(btn5);
        btn5.setBounds(569, 375, 400, 175);

        text10.setFont(new java.awt.Font("Lato", 1, 24)); // NOI18N
        text10.setForeground(new java.awt.Color(148, 0, 211));
        text10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        text10.setText("+");
        text10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(text10);
        text10.setBounds(124, 182, 398, 174);

        jMenu1.setText("Operações");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem2.setText("Editar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem3.setText("Excluir");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setText("Voltar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        setSize(new java.awt.Dimension(1538, 810));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        c.cadastroOUEntrar(text1.getText());
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
          c.cadastroOUEntrar(text4.getText());
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
         c.cadastroOUEntrar(text2.getText());
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
         c.cadastroOUEntrar(text9.getText());
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
         c.cadastroOUEntrar(text7.getText());
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn12ActionPerformed
      
    }//GEN-LAST:event_btn12ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       c.voltar();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
      c.cadastroOUEntrar(text3.getText());
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
 c.cadastroOUEntrar(text8.getText());        
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
         c.cadastroOUEntrar(text6.getText());
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
         c.cadastroOUEntrar(text5.getText());
    }//GEN-LAST:event_btn5ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        c.editarTurma();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        c.excluirTurma();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    public JButton getBtn1() {
        return btn1;
    }

    public void setBtn1(JButton btn1) {
        this.btn1 = btn1;
    }

    public JButton getBtn10() {
        return btn10;
    }

    public void setBtn10(JButton btn10) {
        this.btn10 = btn10;
    }

    public JButton getBtn11() {
        return btn11;
    }

    public void setBtn11(JButton btn11) {
        this.btn11 = btn11;
    }

    public JButton getBtn2() {
        return btn2;
    }

    public void setBtn2(JButton btn2) {
        this.btn2 = btn2;
    }

    public JButton getBtn3() {
        return btn3;
    }

    public void setBtn3(JButton btn3) {
        this.btn3 = btn3;
    }

    public JButton getBtn4() {
        return btn4;
    }

    public void setBtn4(JButton btn4) {
        this.btn4 = btn4;
    }

    public JButton getBtn5() {
        return btn5;
    }

    public void setBtn5(JButton btn5) {
        this.btn5 = btn5;
    }

    public JButton getBtn6() {
        return btn6;
    }

    public void setBtn6(JButton btn6) {
        this.btn6 = btn6;
    }

    public JButton getBtn7() {
        return btn7;
    }

    public void setBtn7(JButton btn7) {
        this.btn7 = btn7;
    }

    public JButton getBtn8() {
        return btn8;
    }

    public void setBtn8(JButton btn8) {
        this.btn8 = btn8;
    }

    public JButton getBtn9() {
        return btn9;
    }

    public void setBtn9(JButton btn9) {
        this.btn9 = btn9;
    }

  

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    public JMenu getjMenu1() {
        return jMenu1;
    }

    public void setjMenu1(JMenu jMenu1) {
        this.jMenu1 = jMenu1;
    }


    public JMenuBar getjMenuBar1() {
        return jMenuBar1;
    }

    public void setjMenuBar1(JMenuBar jMenuBar1) {
        this.jMenuBar1 = jMenuBar1;
    }

    public JLabel getText1() {
        return text1;
    }

    public void setText1(JLabel text1) {
        this.text1 = text1;
    }

    public JLabel getText10() {
        return text10;
    }

    public void setText10(JLabel text10) {
        this.text10 = text10;
    }

    public JLabel getText2() {
        return text2;
    }

    public void setText2(JLabel text2) {
        this.text2 = text2;
    }

    public JLabel getText3() {
        return text3;
    }

    public void setText3(JLabel text3) {
        this.text3 = text3;
    }

    public JLabel getText4() {
        return text4;
    }

    public void setText4(JLabel text4) {
        this.text4 = text4;
    }

    public JLabel getText5() {
        return text5;
    }

    public void setText5(JLabel text5) {
        this.text5 = text5;
    }

    public JLabel getText6() {
        return text6;
    }

    public void setText6(JLabel text6) {
        this.text6 = text6;
    }

    public JLabel getText7() {
        return text7;
    }

    public void setText7(JLabel text7) {
        this.text7 = text7;
    }

    public JLabel getText8() {
        return text8;
    }

    public void setText8(JLabel text8) {
        this.text8 = text8;
    }

    public JLabel getText9() {
        return text9;
    }

    public void setText9(JLabel text9) {
        this.text9 = text9;
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Turma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Turma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Turma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Turma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Turma().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn10;
    private javax.swing.JButton btn11;
    private javax.swing.JButton btn12;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JLabel text1;
    private javax.swing.JLabel text10;
    private javax.swing.JLabel text2;
    private javax.swing.JLabel text3;
    private javax.swing.JLabel text4;
    private javax.swing.JLabel text5;
    private javax.swing.JLabel text6;
    private javax.swing.JLabel text7;
    private javax.swing.JLabel text8;
    private javax.swing.JLabel text9;
    // End of variables declaration//GEN-END:variables
public void iniciar(){
c.iniciar();
} 
 public void imprimir_Na_Tela(String message) {
       JOptionPane.showMessageDialog(null, message);
    }
}
