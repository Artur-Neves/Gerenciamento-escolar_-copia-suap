/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Model.Dao;

import com.mycompany.projeto_lais.Model.Aluno_Atividade_Model;
import com.mycompany.projeto_lais.Model.Aluno_Model;
import com.mycompany.projeto_lais.Model.Atividade_Model;
import com.mycompany.projeto_lais.Model.Turma_Materia_Model;
import com.mycompany.projeto_lais.Model.Turma_Model;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author g15
 */
public class Aluno_Atividade_dao {
EntityManager em;

public List<Aluno_Atividade_Model> findByTurmaMateria(Turma_Materia_Model turmamateria){
    String query = "select a from atividade_aluno a where a.atividade.turmamateria = :turmamateria and a.atividade.unidade != 'Recuperação' order by a.atividade.data desc";
    List<Aluno_Atividade_Model> aluno_atividade = new ArrayList<>();
    try {
        em = new Entity_Manager().ent();
        em.getTransaction().begin();
        aluno_atividade =  em.createQuery(query).setParameter("turmamateria", turmamateria).getResultList();
        em.getTransaction().commit();
    } catch (Exception e) {
        System.out.println("findByTurmaMateria Aluno_Atividade_dao" + e);
        em.getTransaction().rollback();
        aluno_atividade = null;
    }
    finally{
        em.close();
        return aluno_atividade;
    }
        
    }
public Aluno_Atividade_Model findByAlunoAtividade(Aluno_Model aluno, Atividade_Model atividade){
    String query = "select a from atividade_aluno a where a.aluno = :aluno and a.atividade= :atividade ";
    Aluno_Atividade_Model aluno_atividade = new Aluno_Atividade_Model();
    try {
        em = new Entity_Manager().ent();
        em.getTransaction().begin();
        aluno_atividade =  (Aluno_Atividade_Model) em.createQuery(query).setParameter("aluno", aluno).setParameter("atividade", atividade).getSingleResult();
        em.getTransaction().commit();
    } catch (Exception e) {
        System.out.println("findByAlunoAtividade Aluno_Atividade_dao" + e);
        em.getTransaction().rollback();
        aluno_atividade = null;
    }
    finally{
        em.close();
        return aluno_atividade;
    }
        
    }

    public boolean update(Aluno_Atividade_Model at) {
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            em.merge(at);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
             System.out.println("update Aluno_Atividade_dao" + e);
        em.getTransaction().rollback();
       return false;
        } finally {
            em.close();
        }
    }

    public List<Aluno_Atividade_Model> findByAlunoUnidade(Aluno_Model aluno, String unidade, Turma_Materia_Model turmamateria) {
         String query = "select a from atividade_aluno a where a.aluno = :aluno and a.atividade.unidade = :unidade and a.atividade.turmamateria= :turmamateria order by a.atividade.data desc";
    List<Aluno_Atividade_Model> aluno_atividade = new ArrayList<>();
    try {
        em = new Entity_Manager().ent();
        em.getTransaction().begin();
        aluno_atividade =  em.createQuery(query).setParameter("aluno", aluno).setParameter("unidade", unidade).setParameter("turmamateria", turmamateria).getResultList();
        em.getTransaction().commit();
    } catch (Exception e) {
        System.out.println("findByAlunoAtividade Aluno_Atividade_dao" + e);
        em.getTransaction().rollback();
        aluno_atividade = null;
    }
    finally{
        em.close();
        return aluno_atividade;
    }
    }
    
    public void ExcluirNotasAtividade(Atividade_Model atividade){
        
        String query ="update atividade_aluno a  set a.valor_recebido= 0 where a.atividade= :atividade and a.atividade.unidade != 'Recuperação'";
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
           
            em.createQuery(query).setParameter("atividade", atividade).executeUpdate();
             em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("ExluirNOtasAtividade "+e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Aluno_Atividade_Model> findByTurmaMateriaUnidade(Turma_Materia_Model turmamateria, String unidade) {
        String query = "select a from atividade_aluno a where a.atividade.unidade = :unidade and a.atividade.turmamateria= :turmamateria order by a.atividade.data desc";
    List<Aluno_Atividade_Model> aluno_atividade = new ArrayList<>();
    try {
        em = new Entity_Manager().ent();
        em.getTransaction().begin();
        aluno_atividade =  em.createQuery(query).setParameter("unidade", unidade).setParameter("turmamateria", turmamateria).getResultList();
        em.getTransaction().commit();
    } catch (Exception e) {
        System.out.println("findByTurmaMateriaUnidadeu" + e.getMessage());
        em.getTransaction().rollback();
        aluno_atividade = null;
    }
    finally{
        em.close();
        return aluno_atividade;
    }
    }

     public void deleteforTurma(Turma_Model turma) {
       String query = "delete from aluno_atividade a where a.turmamateria.turma= :turma";
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            em.createQuery(query).setParameter("turma", turma).executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("deleteforTurma "+e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
     
}
