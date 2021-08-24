
package models;
//<editor-fold defaultstate="collapsed" desc="imports">

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entities.CTelefonia;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import utils.LocalEntityManagerFactory;
//</editor-fold>
/**
 *
 * @author Raúl Herrera Macías
 */
public class CTelefoniaJpaController implements Serializable {

    
    public CTelefoniaJpaController() {
       this.emf = LocalEntityManagerFactory.getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CTelefonia CTelefonia) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(CTelefonia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCTelefonia(CTelefonia.getIdTelefonia()) != null) {
                throw new PreexistingEntityException("CTelefonia " + CTelefonia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CTelefonia CTelefonia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CTelefonia = em.merge(CTelefonia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = CTelefonia.getIdTelefonia();
                if (findCTelefonia(id) == null) {
                    throw new NonexistentEntityException("The cTelefonia with id " + id + " no longer exists.");
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
            CTelefonia CTelefonia;
            try {
                CTelefonia = em.getReference(CTelefonia.class, id);
                CTelefonia.getIdTelefonia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The CTelefonia with id " + id + " no longer exists.", enfe);
            }
            em.remove(CTelefonia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CTelefonia> findCTelefoniaEntities() {
        return findCTelefoniaEntities(true, -1, -1);
    }

    public List<CTelefonia> findCTelefoniaEntities(int maxResults, int firstResult) {
        return findCTelefoniaEntities(false, maxResults, firstResult);
    }

    private List<CTelefonia> findCTelefoniaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CTelefonia.class));
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

    public CTelefonia findCTelefonia(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CTelefonia.class, id);
        } finally {
            em.close();
        }
    }

    public int getCTelefoniaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CTelefonia> rt = cq.from(CTelefonia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
