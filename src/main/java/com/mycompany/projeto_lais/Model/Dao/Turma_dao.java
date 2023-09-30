/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Model.Dao;

import com.mycompany.projeto_lais.Model.Materia_Model;
import com.mycompany.projeto_lais.Model.Turma_Model;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;

/**
 *
 * @author Artur
 */
public class Turma_dao {
    EntityManager em = new Entity_Manager().ent();
    
    public List<Turma_Model> selectAll(){
       List<Turma_Model> list= null;
       String query = "select t from Turma_Model t";
        try {
            em.getTransaction().begin();
           list = em.createQuery(query).getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            //em.getTransaction().rollback();
        } finally {
            return list;
        }
    }

   public List<Turma_Model> selectByMateria(int id) {
      List<Turma_Model> list= null;
       System.out.println("------------------"+id+"----------");
       String query = "select t from Turma_Model t join t.materia m where m.idMatricula = :id";
        try {
            em.getTransaction().begin();
           list = em.createQuery(query).setParameter("id", id).getResultList();
            em.getTransaction().commit();
            System.out.println("Size"+list.size()+"----------");
        } catch (Exception e) {
            System.out.println("selectByMateria "+ e);
            em.getTransaction().rollback();
        } finally {
            return list;
        }
    }
    public boolean insert(Turma_Model turma){
        try {
            em.getTransaction().begin();
            em.persist(turma);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.err.println("insert "+e);
            
            return false;
        }   
    }

    public Turma_Model findByName(String nome){
        Turma_Model mm = null;
        String query="select t from Turma_Model t where t.nome= :nome";
        try {
            em.getTransaction().begin();
        mm=  (Turma_Model) em.createQuery(query).setParameter("nome", nome).getSingleResult();
        
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("findByname "+e);
        } finally {
        return mm;
        }
}

    public boolean deleteMateria(Turma_Model turma, Materia_Model materia) {
        try {
            turma.getMateria().remove(materia);
            materia.getTurma().remove(turma);
            em.getTransaction().begin();
            em.merge(turma);
            em.merge(materia);
            em.getTransaction().commit();
            
            return true;
        } catch (Exception e) {
            System.err.println("deleteMateria "+e);
            em.getTransaction().rollback();
            return false;
        }   
    }
    public boolean updateMateria(Turma_Model turma, Materia_Model materia) {
        try {
            turma.getMateria().add(materia);
            em.getTransaction().begin();
            em.merge(turma);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.err.println("UpdateMateria "+e);
            em.getTransaction().rollback();
            return false;
        }   
    }

    public boolean editar(Turma_Model t) {
       try {
             em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
        return true;
        } catch (Exception e) {
            System.err.println("insert "+e);
            em.getTransaction().rollback();
           return false;
        }
    }
    public boolean delete(Turma_Model turma) {
       try {
             em.getTransaction().begin();
        em.remove(turma);
        em.getTransaction().commit();
        return true;
        } catch (Exception e) {
            System.err.println("insert "+e);
            em.getTransaction().rollback();
           return false;
        }
    }
public Turma_Model findbyId(int id){
       
        Turma_Model mm=null;
        try {
            em.getTransaction().begin();
            mm = em.find(Turma_Model.class, id);
            em.getTransaction().commit();
            
       } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback(); // Faça rollback se ocorrer uma exceção
        }
        System.err.println("findByid" + e);
    } finally {
        return mm;
    }
    
    }}
