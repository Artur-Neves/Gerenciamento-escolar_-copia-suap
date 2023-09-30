/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Model.Dao;

import com.mycompany.projeto_lais.Model.Materia_Model;
import com.mycompany.projeto_lais.Model.Turma_Materia_Model;
import com.mycompany.projeto_lais.Model.Turma_Model;
import javax.persistence.EntityManager;

/**
 *
 * @author Artur
 */
public class Turma_Materia_dao {
    EntityManager em;
     public Turma_Materia_Model findbyturmamateria(Turma_Materia_Model turmamateria){
       String query ="select tm from Turma_Materia_Model tm where tm.turma= :turma and tm.materia = :materia";
        Turma_Materia_Model mm=null;
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
     }
}
