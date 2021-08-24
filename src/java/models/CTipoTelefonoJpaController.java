/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entities.CTipoTelefono;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import utils.LocalEntityManagerFactory;

/**
 *
 * @author Blueweb
 */
public class CTipoTelefonoJpaController implements Serializable {

    public CTipoTelefonoJpaController() {
       this.emf = LocalEntityManagerFactory.getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CTipoTelefono CTipoTelefono) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(CTipoTelefono);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCTipoTelefono(CTipoTelefono.getId()) != null) {
                throw new PreexistingEntityException("CTipoTelefono " + CTipoTelefono + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CTipoTelefono CTipoTelefono) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CTipoTelefono = em.merge(CTipoTelefono);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = CTipoTelefono.getId();
                if (findCTipoTelefono(id) == null) {
                    throw new NonexistentEntityException("The cTipoTelefono with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CTipoTelefono CTipoTelefono;
            try {
                CTipoTelefono = em.getReference(CTipoTelefono.class, id);
                CTipoTelefono.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The CTipoTelefono with id " + id + " no longer exists.", enfe);
            }
            em.remove(CTipoTelefono);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CTipoTelefono> findCTipoTelefonoEntities() {
        return findCTipoTelefonoEntities(true, -1, -1);
    }

    public List<CTipoTelefono> findCTipoTelefonoEntities(int maxResults, int firstResult) {
        return findCTipoTelefonoEntities(false, maxResults, firstResult);
    }

    private List<CTipoTelefono> findCTipoTelefonoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CTipoTelefono.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CTipoTelefono findCTipoTelefono(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CTipoTelefono.class, id);
        } finally {
            em.close();
        }
    }

    public int getCTipoTelefonoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CTipoTelefono> rt = cq.from(CTipoTelefono.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
