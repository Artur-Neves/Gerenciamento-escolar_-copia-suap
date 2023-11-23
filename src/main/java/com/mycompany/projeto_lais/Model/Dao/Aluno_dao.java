/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Model.Dao;

import com.mycompany.projeto_lais.Model.Aluno_Model;
import com.mycompany.projeto_lais.Model.Turma_Model;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Artur
 */
public class Aluno_dao {

    EntityManager em;

    public boolean inserir(Aluno_Model aluno) {
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("inserir aluno " + e);
            em.getTransaction().rollback();
            return false;
        }finally{
            em.close();
        }

    }

    public List<Aluno_Model> findByTurma(Turma_Model turma) {
        String query = "select a from aluno a where a.turma= :turma order by a.nome";
        List<Aluno_Model> a = new ArrayList<Aluno_Model>();
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            a = em.createQuery(query).setParameter("turma", turma).getResultList();
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("findByTurma aluno" + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
            return a;
        }
    }

    public Aluno_Model findByNome(String nome) {
        String query = "select a from aluno a where a.nome = :nome";
        Aluno_Model a = new Aluno_Model();
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            a = (Aluno_Model) em.createQuery(query).setParameter("nome", nome).getSingleResult();
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("findByNome aluno" + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
            return a;
        }
    }

    public boolean editar(Aluno_Model aluno) {
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            em.merge(aluno);
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

    public boolean excluir(Aluno_Model aluno) {
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            em.remove(aluno);
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

    public List<Aluno_Model> find(Turma_Model turma, String coluna, String texto) {
        String query = null;
        if (isInteger(texto)) {
            query = "select a FROM aluno a WHERE CAST(a." + coluna + " AS VARCHAR) LIKE :valor";
        } else {
            query = "select a FROM aluno a WHERE a." + coluna + " LIKE :valor";
        }

        List<Aluno_Model> a = new ArrayList<Aluno_Model>();
        try {
            System.out.println(turma.getNome());
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            a = em.createQuery(query).setParameter("valor", "" + texto + "%").getResultList();
            em.getTransaction().commit();
            System.out.println(a.size());

        } catch (Exception e) {
            System.out.println("find aluno" + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
            return a;
        }
    }

    private static boolean isInteger(String str) {
        return str != null && str.matches("[0-9]*");
    }

   public Aluno_Model update (Aluno_Model aluno) {
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            aluno = em.merge(aluno);
            em.getTransaction().commit();
            return aluno;
        } catch (Exception e) {
            System.out.println("editar aluno" + e);
            em.getTransaction().rollback();
            return null;
                    } finally {
            em.close();

        }
    }

    public Aluno_Model findByNameAndTurma(Aluno_Model aluno, Turma_Model turma) {
        String query = "select a from aluno a where a.nome = :nome and a.turma = :turma";
        Aluno_Model a = new Aluno_Model();
        try {
            em = new Entity_Manager().ent();
            em.getTransaction().begin();
            a = (Aluno_Model) em.createQuery(query).setParameter("nome", aluno.getNome()).setParameter("turma", turma).getSingleResult();
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("findByNome aluno" + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
            return a;
        }
    }

   
}
