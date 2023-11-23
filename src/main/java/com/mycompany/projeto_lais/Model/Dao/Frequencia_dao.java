/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Model.Dao;

import com.mycompany.projeto_lais.Model.Aluno_Model;
import static com.mycompany.projeto_lais.Model.Atividade_Model_.aluno;
import com.mycompany.projeto_lais.Model.Aula_Model;
import com.mycompany.projeto_lais.Model.Frequencia_Model;
import com.mycompany.projeto_lais.Model.Turma_Materia_Model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Artur
 */
public class Frequencia_dao {

    EntityManager em;

    public boolean insert(Frequencia_Model frequencia) {
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            em.persist(frequencia);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Faça rollback se ocorrer uma exceção
                System.err.println("insert frequencia" + e);
            }
            return false;
        }
    }

    public Frequencia_Model findByAlunoAula(Aluno_Model aluno, Aula_Model aula) {
        String query = "select a from aula_aluno a where a.aluno= :aluno and a.aula= :aula";
        Frequencia_Model b;
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            b = (Frequencia_Model) em.createQuery(query).setParameter("aluno", aluno).setParameter("aula", aula).getSingleResult();
            em.getTransaction().commit();
            ;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Faça rollback se ocorrer uma exceção
                System.err.println("insert frequencia" + e);
            }
            b = null;
        }
        return b;
    }

    public boolean update(Frequencia_Model frequencia) {
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            em.merge(frequencia);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Faça rollback se ocorrer uma exceção
                System.err.println("insert frequencia" + e);
            }
            return false;
        }
    }

    public List<Frequencia_Model> findByAluno(Aluno_Model aluno, Turma_Materia_Model modelTm) {
        String query = "select a from aula_aluno a, aula al where a.aluno = :aluno and a.aula = al and al.turmamateria = :turma order by al.data desc";
        List<Frequencia_Model> b;
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            b = em.createQuery(query).setParameter("aluno", aluno).setParameter("turma", modelTm).setMaxResults(10).getResultList();
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Faça rollback se ocorrer uma exceção
                System.err.println("insert frequencia" + e);
            }
            b = null;
        }
        return (List<Frequencia_Model>) b;
    }

    public List<Frequencia_Model> findByAluno(Aluno_Model aluno, Turma_Materia_Model modelTm, Date data) {
        data.setDate(data.getDate() + 1);
        String query = "select a from aula_aluno a, aula al where a.aluno = :aluno and a.aula = al and al.turmamateria = :turma and al.data<= :data order by al.data desc";
        List<Frequencia_Model> b;
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            b = em.createQuery(query).setParameter("aluno", aluno).setParameter("turma", modelTm).setParameter("data", data).setMaxResults(10).getResultList();
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Faça rollback se ocorrer uma exceção
                System.err.println("insert frequencia" + e);
            }
            b = null;
        }
        return (List<Frequencia_Model>) b;
    }

    public List<Frequencia_Model> findByAula(Aula_Model aula) {
        String query = "select a from aula_aluno a where a.aula = :aula  order by a.aula.data desc";
        List<Frequencia_Model> b = new ArrayList<>();
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            b = em.createQuery(query).setParameter("aula", aula).getResultList();
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback(); // Faça rollback se ocorrer uma exceção
            System.out.println("insert frequencia" + e.getMessage());
            b = null;
        } finally {
            em.close();
            return  b;
        }

    }

    public void cancelFouls(Aula_Model aula) {
       String query = "update aula_aluno a set a.faltas = 0 where a.aula = :aula";
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            em.createQuery(query).setParameter("aula", aula).executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("cancelFouls "+e.getMessage());
        } finally {
            em.close();
        }
    }
}
