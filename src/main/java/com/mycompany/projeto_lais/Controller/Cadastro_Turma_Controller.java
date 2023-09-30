/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Controller;

import com.mycompany.projeto_lais.Enums.Series_Enum;
import com.mycompany.projeto_lais.Model.Dao.Materia_dao;
import com.mycompany.projeto_lais.Model.Dao.Turma_dao;
import com.mycompany.projeto_lais.Model.Materia_Model;
import com.mycompany.projeto_lais.Model.Turma_Model;
import com.mycompany.projeto_lais.View.Cadastro_Turma;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

/**
 *
 * @author Artur
 */
public class Cadastro_Turma_Controller {

    private final Cadastro_Turma view;
    private Turma_Model model;
    List<Materia_Model> materia = new ArrayList<>();
    private Turma_dao dao;
    private Materia_dao dao_materia;
    Materia_Model selecionado;

    public Cadastro_Turma_Controller(Cadastro_Turma view, Materia_Model materia) {
        this.view = view;
        this.dao = new Turma_dao();
        this.dao_materia = new Materia_dao();
        this.selecionado = materia;
    }

    public void atualizar() {

        if (!view.getjButton1().getText().equals("Salvar")) {
            try {
                model = dao.findByName("" + view.getjComboBox3().getSelectedItem());
                view.getjTextField1().setText(model.getNome());
                view.getjComboBox1().setSelectedItem(model.getSerie());
                DefaultListModel n = new DefaultListModel();
                materia.clear();

                for (Materia_Model m : model.getMateria()) {
                    materia.add(dao_materia.findByName(m.getNome()));
                    n.addElement(m.getNome());
                }
                view.getjList1().removeAll();
                view.getjList1().setModel(n);
            } catch (Exception e) {
                System.out.println("atualizar Turma " + e);
            }
        }
    }

    public void inserir() {
        if (!view.getjTextField1().getText().trim().isEmpty()) {
            if (view.getjComboBox1().getSelectedIndex() == -1 || view.getjComboBox1().getSelectedIndex() == -1 || view.getjTextField1().getText().trim().isEmpty()) {
            } else {
                if (view.getjButton1().getText().equals("Salvar")) {
                    String nome = view.getjTextField1().getText();
                    String serie = (String) view.getjComboBox1().getSelectedItem();
                    model = new Turma_Model(nome, serie, materia);
                    if (dao.insert(model)) {
                        view.imprimir_Na_Tela("Turma cadastrada com sucesso!");
                        view.hide();
                    } else {
                        view.imprimir_Na_Tela("Já existe uma Turma com esse Nome!");
                    }
                } else if (view.getjButton1().getText().equals("Editar")) {
                    model.getMateria().clear();

                    if (materia.size() != 0) {
                        for (Materia_Model m : materia) {
                            model.addMateria(m);
                        }
                    }
                    model.setNome(view.getjTextField1().getText());
                    model.setSerie("" + view.getjComboBox1().getSelectedItem());
                    if (dao.editar(model)) {
                        view.imprimir_Na_Tela("Edição realizada com sucesso!");
                        view.hide();
                    } else {
                        view.imprimir_Na_Tela("Já existe uma Turma com esse Nome!");
                    }
                } else {
                    if (dao.delete(model)) {
                        view.imprimir_Na_Tela("Exclusão realizada com sucesso!");
                        view.hide();
                    }
                }
            }
        } else {
            view.imprimir_Na_Tela("O campo do Nome não pode ficar vazio!");

        }

    }

    public void iniciar() {
        view.getjComboBox1().setSelectedIndex(-1);

        for (Series_Enum value : Series_Enum.values()) {
            view.getjComboBox1().addItem(value.toString());

        }
        for (Materia_Model materia : dao_materia.selectAll()) {
            view.getjComboBox2().addItem(materia.getNome());
        }
        for (Turma_Model t : dao.selectAll()) {
            view.getjComboBox3().addItem(t.getNome());
        }
        view.getjComboBox2().setSelectedItem(selecionado.getNome());
        addMateria();

    }

    public void addMateria() {
        boolean tem = true;
        for (Materia_Model m : materia) {

            if (m.getNome().equals(view.getjComboBox2().getSelectedItem())) {
                tem = false;
                break;
            }

        }
        if (tem) {
            materia.add(dao_materia.findByName("" + view.getjComboBox2().getSelectedItem()));
            DefaultListModel n = new DefaultListModel();
            for (Materia_Model m : materia) {
                n.addElement(m.getNome());
            }
            view.getjList1().removeAll();
            view.getjList1().setModel(n);
        }
    }

    public void exMateria() {
        boolean remove = materia.remove(dao_materia.findByName("" + view.getjList1().getSelectedValue()));
        DefaultListModel n = new DefaultListModel();
        for (Materia_Model m : materia) {
            n.addElement(m.getNome());
            System.out.println(remove);
        }
        view.getjList1().removeAll();
        view.getjList1().setModel(n);
    }

}
