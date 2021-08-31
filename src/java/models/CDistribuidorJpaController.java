/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.CClientes;
import entities.CDistribuidor;
import java.util.ArrayList;
import java.util.Collection;
import entities.HActivacion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import models.exceptions.NonexistentEntityException;
import models.exceptions.PreexistingEntityException;
import utils.LocalEntityManagerFactory;

/**
 *
 * @author Blueweb
 */
public class CDistribuidorJpaController implements Serializable {

     public CDistribuidorJpaController() {
       this.emf = LocalEntityManagerFactory.getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CDistribuidor CDistribuidor) throws PreexistingEntityException, Exception {
        if (CDistribuidor.getCClientesCollection() == null) {
            CDistribuidor.setCClientesCollection(new ArrayList<CClientes>());
        }
        if (CDistribuidor.getHActivacionCollection() == null) {
            CDistribuidor.setHActivacionCollection(new ArrayList<HActivacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<CClientes> attachedCClientesCollection = new ArrayList<CClientes>();
            for (CClientes CClientesCollectionCClientesToAttach : CDistribuidor.getCClientesCollection()) {
                CClientesCollectionCClientesToAttach = em.getReference(CClientesCollectionCClientesToAttach.getClass(), CClientesCollectionCClientesToAttach.getIdCliente());
                attachedCClientesCollection.add(CClientesCollectionCClientesToAttach);
            }
            CDistribuidor.setCClientesCollection(attachedCClientesCollection);
            Collection<HActivacion> attachedHActivacionCollection = new ArrayList<HActivacion>();
            for (HActivacion HActivacionCollectionHActivacionToAttach : CDistribuidor.getHActivacionCollection()) {
                HActivacionCollectionHActivacionToAttach = em.getReference(HActivacionCollectionHActivacionToAttach.getClass(), HActivacionCollectionHActivacionToAttach.getId());
                attachedHActivacionCollection.add(HActivacionCollectionHActivacionToAttach);
            }
            CDistribuidor.setHActivacionCollection(attachedHActivacionCollection);
            em.persist(CDistribuidor);
            for (CClientes CClientesCollectionCClientes : CDistribuidor.getCClientesCollection()) {
                CDistribuidor oldIdDistribuidorOfCClientesCollectionCClientes = CClientesCollectionCClientes.getIdDistribuidor();
                CClientesCollectionCClientes.setIdDistribuidor(CDistribuidor);
                CClientesCollectionCClientes = em.merge(CClientesCollectionCClientes);
                if (oldIdDistribuidorOfCClientesCollectionCClientes != null) {
                    oldIdDistribuidorOfCClientesCollectionCClientes.getCClientesCollection().remove(CClientesCollectionCClientes);
                    oldIdDistribuidorOfCClientesCollectionCClientes = em.merge(oldIdDistribuidorOfCClientesCollectionCClientes);
                }
            }
            for (HActivacion HActivacionCollectionHActivacion : CDistribuidor.getHActivacionCollection()) {
                CDistribuidor oldIdDistribuidorOfHActivacionCollectionHActivacion = HActivacionCollectionHActivacion.getIdDistribuidor();
                HActivacionCollectionHActivacion.setIdDistribuidor(CDistribuidor);
                HActivacionCollectionHActivacion = em.merge(HActivacionCollectionHActivacion);
                if (oldIdDistribuidorOfHActivacionCollectionHActivacion != null) {
                    oldIdDistribuidorOfHActivacionCollectionHActivacion.getHActivacionCollection().remove(HActivacionCollectionHActivacion);
                    oldIdDistribuidorOfHActivacionCollectionHActivacion = em.merge(oldIdDistribuidorOfHActivacionCollectionHActivacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCDistribuidor(CDistribuidor.getIdDistribuidor()) != null) {
                throw new PreexistingEntityException("CDistribuidor " + CDistribuidor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CDistribuidor CDistribuidor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CDistribuidor persistentCDistribuidor = em.find(CDistribuidor.class, CDistribuidor.getIdDistribuidor());
            Collection<CClientes> CClientesCollectionOld = persistentCDistribuidor.getCClientesCollection();
            Collection<CClientes> CClientesCollectionNew = CDistribuidor.getCClientesCollection();
            Collection<HActivacion> HActivacionCollectionOld = persistentCDistribuidor.getHActivacionCollection();
            Collection<HActivacion> HActivacionCollectionNew = CDistribuidor.getHActivacionCollection();
            Collection<CClientes> attachedCClientesCollectionNew = new ArrayList<CClientes>();
            for (CClientes CClientesCollectionNewCClientesToAttach : CClientesCollectionNew) {
                CClientesCollectionNewCClientesToAttach = em.getReference(CClientesCollectionNewCClientesToAttach.getClass(), CClientesCollectionNewCClientesToAttach.getIdCliente());
                attachedCClientesCollectionNew.add(CClientesCollectionNewCClientesToAttach);
            }
            CClientesCollectionNew = attachedCClientesCollectionNew;
            CDistribuidor.setCClientesCollection(CClientesCollectionNew);
            Collection<HActivacion> attachedHActivacionCollectionNew = new ArrayList<HActivacion>();
            for (HActivacion HActivacionCollectionNewHActivacionToAttach : HActivacionCollectionNew) {
                HActivacionCollectionNewHActivacionToAttach = em.getReference(HActivacionCollectionNewHActivacionToAttach.getClass(), HActivacionCollectionNewHActivacionToAttach.getId());
                attachedHActivacionCollectionNew.add(HActivacionCollectionNewHActivacionToAttach);
            }
            HActivacionCollectionNew = attachedHActivacionCollectionNew;
            CDistribuidor.setHActivacionCollection(HActivacionCollectionNew);
            CDistribuidor = em.merge(CDistribuidor);
            for (CClientes CClientesCollectionOldCClientes : CClientesCollectionOld) {
                if (!CClientesCollectionNew.contains(CClientesCollectionOldCClientes)) {
                    CClientesCollectionOldCClientes.setIdDistribuidor(null);
                    CClientesCollectionOldCClientes = em.merge(CClientesCollectionOldCClientes);
                }
            }
            for (CClientes CClientesCollectionNewCClientes : CClientesCollectionNew) {
                if (!CClientesCollectionOld.contains(CClientesCollectionNewCClientes)) {
                    CDistribuidor oldIdDistribuidorOfCClientesCollectionNewCClientes = CClientesCollectionNewCClientes.getIdDistribuidor();
                    CClientesCollectionNewCClientes.setIdDistribuidor(CDistribuidor);
                    CClientesCollectionNewCClientes = em.merge(CClientesCollectionNewCClientes);
                    if (oldIdDistribuidorOfCClientesCollectionNewCClientes != null && !oldIdDistribuidorOfCClientesCollectionNewCClientes.equals(CDistribuidor)) {
                        oldIdDistribuidorOfCClientesCollectionNewCClientes.getCClientesCollection().remove(CClientesCollectionNewCClientes);
                        oldIdDistribuidorOfCClientesCollectionNewCClientes = em.merge(oldIdDistribuidorOfCClientesCollectionNewCClientes);
                    }
                }
            }
            for (HActivacion HActivacionCollectionOldHActivacion : HActivacionCollectionOld) {
                if (!HActivacionCollectionNew.contains(HActivacionCollectionOldHActivacion)) {
                    HActivacionCollectionOldHActivacion.setIdDistribuidor(null);
                    HActivacionCollectionOldHActivacion = em.merge(HActivacionCollectionOldHActivacion);
                }
            }
            for (HActivacion HActivacionCollectionNewHActivacion : HActivacionCollectionNew) {
                if (!HActivacionCollectionOld.contains(HActivacionCollectionNewHActivacion)) {
                    CDistribuidor oldIdDistribuidorOfHActivacionCollectionNewHActivacion = HActivacionCollectionNewHActivacion.getIdDistribuidor();
                    HActivacionCollectionNewHActivacion.setIdDistribuidor(CDistribuidor);
                    HActivacionCollectionNewHActivacion = em.merge(HActivacionCollectionNewHActivacion);
                    if (oldIdDistribuidorOfHActivacionCollectionNewHActivacion != null && !oldIdDistribuidorOfHActivacionCollectionNewHActivacion.equals(CDistribuidor)) {
                        oldIdDistribuidorOfHActivacionCollectionNewHActivacion.getHActivacionCollection().remove(HActivacionCollectionNewHActivacion);
                        oldIdDistribuidorOfHActivacionCollectionNewHActivacion = em.merge(oldIdDistribuidorOfHActivacionCollectionNewHActivacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = CDistribuidor.getIdDistribuidor();
                if (findCDistribuidor(id) == null) {
                    throw new NonexistentEntityException("The cDistribuidor with id " + id + " no longer exists.");
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
            CDistribuidor CDistribuidor;
            try {
                CDistribuidor = em.getReference(CDistribuidor.class, id);
                CDistribuidor.getIdDistribuidor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The CDistribuidor with id " + id + " no longer exists.", enfe);
            }
            Collection<CClientes> CClientesCollection = CDistribuidor.getCClientesCollection();
            for (CClientes CClientesCollectionCClientes : CClientesCollection) {
                CClientesCollectionCClientes.setIdDistribuidor(null);
                CClientesCollectionCClientes = em.merge(CClientesCollectionCClientes);
            }
            Collection<HActivacion> HActivacionCollection = CDistribuidor.getHActivacionCollection();
            for (HActivacion HActivacionCollectionHActivacion : HActivacionCollection) {
                HActivacionCollectionHActivacion.setIdDistribuidor(null);
                HActivacionCollectionHActivacion = em.merge(HActivacionCollectionHActivacion);
            }
            em.remove(CDistribuidor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CDistribuidor> findCDistribuidorEntities() {
        return findCDistribuidorEntities(true, -1, -1);
    }

    public List<CDistribuidor> findCDistribuidorEntities(int maxResults, int firstResult) {
        return findCDistribuidorEntities(false, maxResults, firstResult);
    }

    private List<CDistribuidor> findCDistribuidorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CDistribuidor.class));
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

    public CDistribuidor findCDistribuidor(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CDistribuidor.class, id);
        } finally {
            em.close();
        }
    }

    public int getCDistribuidorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CDistribuidor> rt = cq.from(CDistribuidor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
