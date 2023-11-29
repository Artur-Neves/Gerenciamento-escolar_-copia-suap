/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Model.Dao;

import com.mycompany.projeto_lais.Model.Aula_Model;
import com.mycompany.projeto_lais.Model.Materia_Model;
import com.mycompany.projeto_lais.Model.Turma_Materia_Model;
import com.mycompany.projeto_lais.Model.Turma_Model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Artur
 */
public class Aula_dao {

    EntityManager em;

    public boolean inserir(Aula_Model aula) {
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            em.persist(aula);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            System.out.println("inserir Aula " + e);
            em.close();
            return false;
        }
    }

    public boolean editar(Aula_Model aula) {
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            em.merge(aula);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("editar aluno" + e);
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();

        }
    }

    public boolean excluir(Aula_Model aula) {
        System.out.println(aula.getIdAula());
        try {

            em = new Entity_Manager().ent();
            Aula_Model selecionado = em.find(Aula_Model.class, aula.getIdAula());
            em.getTransaction().begin();

            em.remove(selecionado);
            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            System.out.println("excluir aluno" + e);
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();

        }
    }

    public List<Aula_Model> findByTurmaMateria(Turma_Materia_Model turmamateria) {
        String query = "select a from aula a where a.turmamateria= :turma order by a.data desc";
        List<Aula_Model> a = new ArrayList<Aula_Model>();
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            a = em.createQuery(query).setParameter("turma", turmamateria).getResultList();
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("findByTurmaMateria " + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
            return a;
        }
    }

    public List<Aula_Model> findByTurmaMateria(Turma_Materia_Model turmamateria, String unidade) {
        String query = "select a from aula a where a.turmamateria= :turma and a.unidade= :unidade order by a.data desc";
        List<Aula_Model> a = new ArrayList<Aula_Model>();
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            if (unidade.trim().isEmpty()) {
                return findByTurmaMateria(turmamateria);
            } else {
                a = em.createQuery(query).setParameter("turma", turmamateria).setParameter("unidade", unidade).getResultList();
            }
            em.getTransaction().commit();
            return a;
        } catch (Exception e) {
            System.out.println("findByTurmaMateria " + e);
            em.getTransaction().rollback();
            return null;
        }
    }
    public List<Aula_Model> findDataByTurmaMateria(Turma_Materia_Model turmamateria) {
        String query = "SELECT a FROM aula a WHERE a.turmamateria = :turma ORDER BY a.data DESC";
        List<Aula_Model> a = new ArrayList<Aula_Model>();
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            a = em.createQuery(query).setParameter("turma", turmamateria).setMaxResults(10).getResultList();
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("findByTurmaMateria " + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
            return a;
        }
    }
public List<Aula_Model> findDataByTurmaMateria(Turma_Materia_Model turmamateria, Date datadecomeco) {
    datadecomeco.setDate(datadecomeco.getDate()+1);
        String query = "select a from aula a where a.turmamateria= :turma and a.data<= :date order by a.data desc ";
        List<Aula_Model> a = new ArrayList<Aula_Model>();
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            a =  em.createQuery(query).setParameter("turma", turmamateria).setParameter("date", datadecomeco).setMaxResults(10).getResultList();
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("findByTurmaMateria " + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
            return a;
        }
    }

  
    public void deleteforTurma(Turma_Model turma) {
         String query = "delete from aula a where a.turmamateria.turma= :turma";
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

    public void deleteforTurmaAndMateria(Turma_Model turma, Materia_Model materia) {
         String query = "delete from aula a where a.turmamateria.turma= :turma and a.turmamateria.materia= :materia";
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            em.createQuery(query).setParameter("turma", turma).setParameter("materia", materia).executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("deleteforTurma "+e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void deleteforMateria(Materia_Model materia) {
        String query = "delete from aula a where a.turmamateria.materia= :materia";
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            em.createQuery(query).setParameter("materia", materia).executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("deleteforTurma "+e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    

}
