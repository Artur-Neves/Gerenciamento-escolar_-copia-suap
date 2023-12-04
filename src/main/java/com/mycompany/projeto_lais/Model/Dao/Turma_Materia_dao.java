/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Model.Dao;

import com.mycompany.projeto_lais.Model.Materia_Model;
import com.mycompany.projeto_lais.Model.Turma_Materia_Model;
import com.mycompany.projeto_lais.Model.Turma_Model;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author Artur
 */
public class Turma_Materia_dao {

    EntityManager em;

    public Turma_Materia_Model findbyturmamateria(Turma_Materia_Model turmamateria) {
        String query = "select tm from Turma_Materia_Model tm where tm.turma= :turma and tm.materia = :materia";
        Turma_Materia_Model mm = null;
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            mm = (Turma_Materia_Model) em.createQuery(query).setParameter("turma", turmamateria.getTurma()).setParameter("materia", turmamateria.getMateria()).getSingleResult();
            em.getTransaction().commit();
            return mm;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Faça rollback se ocorrer uma exceção
            }
            System.err.println("findByid" + e);
            return mm;
        }
        finally {
            em.close();
        }
    }

    public void deleteforTurmaAndMteria(Turma_Model turma, Materia_Model materia) {
        String query = "delete from Turma_Materia_Model a where a.turma= :turma and a.materia= :materia";
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            turma = em.merge(turma);
            materia = em.merge(materia);
            em.createQuery(query).setParameter("turma", turma).setParameter("materia", materia).executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("deleteforTurma " + e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void deleteforMateria(Materia_Model materia) {
        String query = "delete from Turma_Materia_Model a where a.materia= :materia";
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            materia = em.merge(materia);
            em.createQuery(query).setParameter("materia", materia).executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("deleteforTurma " + e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public Turma_Materia_Model findforMateriaAndTurma(Materia_Model materia, Turma_Model turma) {
        String query = "select a from Turma_Materia_Model a where a.materia= :materia and a.turma= :turma";
        Turma_Materia_Model turmamateria = null;
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            turmamateria = (Turma_Materia_Model) em.createQuery(query).setParameter("materia", materia).setParameter("turma", turma).getSingleResult();
            em.getTransaction().commit();
        } catch (NoResultException e) {
            // Lidar com a situação em que nenhum resultado foi encontrado.
            System.out.println("Nenhum resultado encontrado.");
        } catch (Exception e) {
            System.out.println("Error findMateriaAndTUrm " + e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
            return turmamateria;
        }
    }

}
