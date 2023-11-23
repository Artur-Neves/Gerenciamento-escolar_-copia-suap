/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Model.Dao;

import com.mycompany.projeto_lais.Model.Atividade_Model;
import com.mycompany.projeto_lais.Model.Turma_Materia_Model;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Artur
 */
public class Atividade_dao {
    EntityManager em;
    
    public boolean insert(Atividade_Model atividade){
        boolean validar= true;
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            em.persist(atividade);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(" insert Atividade"+e);
            validar=false;
        } finally {
            em.close();
            return validar;
        }
    }
    public boolean update(Atividade_Model atividade){
        boolean validar= true;
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            em.merge(atividade);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(" update Atividade"+e);
            validar=false;
        } finally {
            em.close();
            return validar;
        }
    }
    public boolean delete(Atividade_Model atividade){
        boolean validar= true;
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            atividade = em.merge(atividade);
            em.remove(atividade);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(" delete Atividade"+e);
            validar=false;
        } finally {
            em.close();
            return validar;
        }
    }
    public List<Atividade_Model> findByTurmaMateria( Turma_Materia_Model turmamateria, String unidade){
           List<Atividade_Model> atividader= null;
           String recu = "Recuperação";
           String query=null;
           try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
           if (unidade.equals("")){
                query = "select a from atividade a where a.turmamateria= :turma and a.unidade <> :recu  order by a.data desc";
                atividader = em.createQuery(query).setParameter("turma", turmamateria).setParameter("recu", recu).getResultList();          
           }else { 
                query = "select a from atividade a where a.turmamateria= :turma and a.unidade = :unidade order by a.data desc";
                 atividader = em.createQuery(query).setParameter("turma", turmamateria).setParameter("unidade", unidade).getResultList();          
           }
           
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(" findByTurmaMateria Atividade"+e);
            
        } finally {
            em.close();
            return atividader;
        }
    }

    public List<Atividade_Model> findByTurmaMateriaRecovery(Turma_Materia_Model turmamateria, String recuperacao) {
        List<Atividade_Model> atividader= null;
           String query = "select a from atividade a where a.turmamateria= :turma and a.unidade = :recuperacao order by a.data desc";
           try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
           atividader= em.createQuery(query).setParameter("turma", turmamateria).setParameter("recuperacao", recuperacao).getResultList();          
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(" findByTurmaMateriaRecovery Atividade"+e);
            
        } finally {
               
            em.close();
            return atividader;
        }
    }
   
    
   
}
