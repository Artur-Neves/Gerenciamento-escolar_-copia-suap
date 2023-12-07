/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Controller;
import com.mycompany.projeto_lais.Model.Aluno_Model;
import com.mycompany.projeto_lais.Model.Atividade_Model;
import com.mycompany.projeto_lais.Model.Aula_Model;
import com.mycompany.projeto_lais.Model.Dao.Aluno_Atividade_dao;
import com.mycompany.projeto_lais.Model.Dao.Aluno_Aula_dao;
import com.mycompany.projeto_lais.Model.Dao.Aluno_dao;
import com.mycompany.projeto_lais.Model.Dao.Atividade_dao;
import com.mycompany.projeto_lais.Model.Dao.Aula_dao;
import com.mycompany.projeto_lais.Model.Dao.Materia_dao;
import com.mycompany.projeto_lais.Model.Dao.Turma_Materia_dao;
import com.mycompany.projeto_lais.Model.Dao.Turma_dao;
import com.mycompany.projeto_lais.Model.Materia_Model;
import com.mycompany.projeto_lais.Model.Turma_Materia_Model;
import com.mycompany.projeto_lais.Model.Turma_Model;
import com.mycompany.projeto_lais.View.Cadastro_Aluno;
import com.mycompany.projeto_lais.View.Menssagem_De_Confirmacao;

/**
 *
 * @author Artur
 */
public class Cadastro_Aluno_Controller {

    private Cadastro_Aluno view;
    private Aluno_dao dao = new Aluno_dao();
    private Aluno_Model model;
    private Turma_Model turma;
    private Aluno_Model aluno;
    private Turma_dao dao_turma = new Turma_dao();
    private Aula_dao daof;
    private Atividade_dao dao_atvd;
    private Turma_Materia_Model turmamateria;
    private Turma_Materia_dao dao_tm;
    private Materia_dao dao_materia;
    private Materia_Model materia;
    private Aluno_Atividade_dao dao_aluno_atividade;
    private Aluno_Aula_dao dao_al;
    private Validacao validacao;

    public Cadastro_Aluno_Controller(Cadastro_Aluno view, Turma_Materia_Model turmamateria, Aluno_Model aluno) {
        this.view = view;
        this.turma = turmamateria.getTurma();
        this.aluno = aluno;
        daof = new Aula_dao();
        dao_tm = new Turma_Materia_dao();
        dao_materia = new Materia_dao();
        dao_turma= new Turma_dao();
        dao_atvd = new Atividade_dao();
        dao_aluno_atividade = new Aluno_Atividade_dao();
        dao_al = new Aluno_Aula_dao();
        
        this. turmamateria = dao_tm.findbyturmamateria(turmamateria);

    }

    public Cadastro_Aluno_Controller(Cadastro_Aluno view, Turma_Materia_Model turmamateria) {
        this.view = view;
        this.turma = turmamateria.getTurma();

        daof = new Aula_dao();
        dao_tm = new Turma_Materia_dao();
        dao_materia = new Materia_dao();
        dao_turma= new Turma_dao();
        dao_atvd = new Atividade_dao();
        this. turmamateria = dao_tm.findbyturmamateria(turmamateria);
    }

    public void inserir() {
      
        if(!view.getjTextField1().getText().trim().isEmpty()){
        if (view.getjButton3().getText().equals("Salvar")) {
            if (!view.getjTextField1().getText().trim().isEmpty()) {
                String nome = view.getjTextField1().getText();
                turma = dao_turma.findByName("" + view.getjComboBox1().getSelectedItem());
                turmamateria= dao_tm.findbyturmamateria(turmamateria);
                model = new Aluno_Model(nome, turma);
                turma.getAlunos().add(model);
                
                if (dao.inserir(model)) {
                   model = dao.findByNameAndTurma(model, turma);
                    System.out.println(model.getIdAluno());
                    if (daof.findByTurmaMateria(turmamateria)!=null){
                    for(Aula_Model a : daof.findByTurmaMateria(turmamateria)){
                    a.addAluno(model);
                    daof.editar(a);
                        System.out.println("aula na "+ turmamateria.getTurma().getNome());
                }}
                    if (dao_atvd.findByTurmaMateriaUnidade(turmamateria, "")!=null){
                        for(Atividade_Model ativd : dao_atvd.findByTurmaMateria(turmamateria)){
                            ativd.addAluno(model);
                            dao_atvd.update(ativd);
                        }} 
                    view.imprimir_Na_Tela("Aluno cadastrado com sucesso!");
                    view.hide();
                }
            }
        } else if (view.getjButton3().getText().equals("Editar")) {
            aluno.setNome(view.getjTextField1().getText());
            turma = dao_turma.findByName("" + view.getjComboBox1().getSelectedItem());
            aluno.setTurma(turma);
            if (dao.editar(aluno)){
                 view.imprimir_Na_Tela("Aluno editado com sucesso!");
                    view.hide();
            }
        }
        else { 
            Menssagem_De_Confirmacao m = new Menssagem_De_Confirmacao(null, true, "Ao excluir um aluno, suas notas e frequência serão ", "apagados permanentemente", "Anteção");
            m.setVisible(true);
            if (m.retornar()){
            turma = dao_turma.findByName("" + view.getjComboBox1().getSelectedItem());
                   turma.removeAluno(aluno);
                   dao_turma.editar(turma);
                   System.out.println("Nome do aluno e id que será excluido"+aluno.getNome()+" id"+aluno.getIdAluno());
                   System.out.println("Nome da turma"+turma.getNome());
            if (deleteforAluno(aluno)){
                 view.imprimir_Na_Tela("Aluno excluido com sucesso!");
                    view.hide();
            }}
        }}
        else{
                    Menssagem_De_Confirmacao m = new Menssagem_De_Confirmacao(null, true, "O nome do aluno não pode ficar vazio", "", "Atenção", 0);
                    m.setVisible(true);
                }

    }

    public void iniciar() {
   view.getjTextField1().setDocument( new Validacao(60));
        for (Turma_Model t : dao_turma.selectByMateria(turmamateria.getMateria().getIdMatricula())) {
            view.getjComboBox1().addItem(t.getNome());
        }
      
        view.getjComboBox1().setSelectedItem(turma.getNome());
        if (!view.getjButton3().getText().equals("Salvar")) {
            view.getjTextField1().setText(aluno.getNome());
            view.getjComboBox1().setSelectedItem(dao_turma.findbyId(aluno.getTurma().getIdTurma()));

        }
   
 
 }
    public boolean deleteforAluno(Aluno_Model aluno){
        aluno = dao.findById(aluno.getIdAluno());
        dao_aluno_atividade.deleteforAluno(aluno);
        dao_al.deleteforAluno(aluno);
        return dao.excluir(aluno);
    }

    public void atualizarTurma() {
        turmamateria.setTurma(dao_turma.findByName(""+view.getjComboBox1().getSelectedItem()));
        System.out.println("atualizou o combobox: "+turmamateria.getTurma().getNome());
    }
}
