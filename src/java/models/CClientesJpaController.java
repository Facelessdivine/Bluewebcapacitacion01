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
import entities.CCiudad;
import entities.CClientes;
import entities.CDistribuidor;
import entities.SUsuarios;
import entities.HActivacion;
import java.util.ArrayList;
import java.util.Collection;
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
public class CClientesJpaController implements Serializable {

     public CClientesJpaController() {
       this.emf = LocalEntityManagerFactory.getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CClientes CClientes) throws PreexistingEntityException, Exception {
        if (CClientes.getHActivacionCollection() == null) {
            CClientes.setHActivacionCollection(new ArrayList<HActivacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CCiudad idCiudad = CClientes.getIdCiudad();
            if (idCiudad != null) {
                idCiudad = em.getReference(idCiudad.getClass(), idCiudad.getIdCiudad());
                CClientes.setIdCiudad(idCiudad);
            }
            CDistribuidor idDistribuidor = CClientes.getIdDistribuidor();
            if (idDistribuidor != null) {
                idDistribuidor = em.getReference(idDistribuidor.getClass(), idDistribuidor.getIdDistribuidor());
                CClientes.setIdDistribuidor(idDistribuidor);
            }
            SUsuarios idUsuarioModifica = CClientes.getIdUsuarioModifica();
            if (idUsuarioModifica != null) {
                idUsuarioModifica = em.getReference(idUsuarioModifica.getClass(), idUsuarioModifica.getIdUsuario());
                CClientes.setIdUsuarioModifica(idUsuarioModifica);
            }
            Collection<HActivacion> attachedHActivacionCollection = new ArrayList<HActivacion>();
            for (HActivacion HActivacionCollectionHActivacionToAttach : CClientes.getHActivacionCollection()) {
                HActivacionCollectionHActivacionToAttach = em.getReference(HActivacionCollectionHActivacionToAttach.getClass(), HActivacionCollectionHActivacionToAttach.getId());
                attachedHActivacionCollection.add(HActivacionCollectionHActivacionToAttach);
            }
            CClientes.setHActivacionCollection(attachedHActivacionCollection);
            em.persist(CClientes);
            if (idCiudad != null) {
                idCiudad.getCClientesCollection().add(CClientes);
                idCiudad = em.merge(idCiudad);
            }
            if (idDistribuidor != null) {
                idDistribuidor.getCClientesCollection().add(CClientes);
                idDistribuidor = em.merge(idDistribuidor);
            }
            if (idUsuarioModifica != null) {
                idUsuarioModifica.getCClientesCollection().add(CClientes);
                idUsuarioModifica = em.merge(idUsuarioModifica);
            }
            for (HActivacion HActivacionCollectionHActivacion : CClientes.getHActivacionCollection()) {
                CClientes oldIdClienteOfHActivacionCollectionHActivacion = HActivacionCollectionHActivacion.getIdCliente();
                HActivacionCollectionHActivacion.setIdCliente(CClientes);
                HActivacionCollectionHActivacion = em.merge(HActivacionCollectionHActivacion);
                if (oldIdClienteOfHActivacionCollectionHActivacion != null) {
                    oldIdClienteOfHActivacionCollectionHActivacion.getHActivacionCollection().remove(HActivacionCollectionHActivacion);
                    oldIdClienteOfHActivacionCollectionHActivacion = em.merge(oldIdClienteOfHActivacionCollectionHActivacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCClientes(CClientes.getIdCliente()) != null) {
                throw new PreexistingEntityException("CClientes " + CClientes + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CClientes CClientes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CClientes persistentCClientes = em.find(CClientes.class, CClientes.getIdCliente());
            CCiudad idCiudadOld = persistentCClientes.getIdCiudad();
            CCiudad idCiudadNew = CClientes.getIdCiudad();
            CDistribuidor idDistribuidorOld = persistentCClientes.getIdDistribuidor();
            CDistribuidor idDistribuidorNew = CClientes.getIdDistribuidor();
            SUsuarios idUsuarioModificaOld = persistentCClientes.getIdUsuarioModifica();
            SUsuarios idUsuarioModificaNew = CClientes.getIdUsuarioModifica();
            Collection<HActivacion> HActivacionCollectionOld = persistentCClientes.getHActivacionCollection();
            Collection<HActivacion> HActivacionCollectionNew = CClientes.getHActivacionCollection();
            if (idCiudadNew != null) {
                idCiudadNew = em.getReference(idCiudadNew.getClass(), idCiudadNew.getIdCiudad());
                CClientes.setIdCiudad(idCiudadNew);
            }
            if (idDistribuidorNew != null) {
                idDistribuidorNew = em.getReference(idDistribuidorNew.getClass(), idDistribuidorNew.getIdDistribuidor());
                CClientes.setIdDistribuidor(idDistribuidorNew);
            }
            if (idUsuarioModificaNew != null) {
                idUsuarioModificaNew = em.getReference(idUsuarioModificaNew.getClass(), idUsuarioModificaNew.getIdUsuario());
                CClientes.setIdUsuarioModifica(idUsuarioModificaNew);
            }
            Collection<HActivacion> attachedHActivacionCollectionNew = new ArrayList<HActivacion>();
            for (HActivacion HActivacionCollectionNewHActivacionToAttach : HActivacionCollectionNew) {
                HActivacionCollectionNewHActivacionToAttach = em.getReference(HActivacionCollectionNewHActivacionToAttach.getClass(), HActivacionCollectionNewHActivacionToAttach.getId());
                attachedHActivacionCollectionNew.add(HActivacionCollectionNewHActivacionToAttach);
            }
            HActivacionCollectionNew = attachedHActivacionCollectionNew;
            CClientes.setHActivacionCollection(HActivacionCollectionNew);
            CClientes = em.merge(CClientes);
            if (idCiudadOld != null && !idCiudadOld.equals(idCiudadNew)) {
                idCiudadOld.getCClientesCollection().remove(CClientes);
                idCiudadOld = em.merge(idCiudadOld);
            }
            if (idCiudadNew != null && !idCiudadNew.equals(idCiudadOld)) {
                idCiudadNew.getCClientesCollection().add(CClientes);
                idCiudadNew = em.merge(idCiudadNew);
            }
            if (idDistribuidorOld != null && !idDistribuidorOld.equals(idDistribuidorNew)) {
                idDistribuidorOld.getCClientesCollection().remove(CClientes);
                idDistribuidorOld = em.merge(idDistribuidorOld);
            }
            if (idDistribuidorNew != null && !idDistribuidorNew.equals(idDistribuidorOld)) {
                idDistribuidorNew.getCClientesCollection().add(CClientes);
                idDistribuidorNew = em.merge(idDistribuidorNew);
            }
            if (idUsuarioModificaOld != null && !idUsuarioModificaOld.equals(idUsuarioModificaNew)) {
                idUsuarioModificaOld.getCClientesCollection().remove(CClientes);
                idUsuarioModificaOld = em.merge(idUsuarioModificaOld);
            }
            if (idUsuarioModificaNew != null && !idUsuarioModificaNew.equals(idUsuarioModificaOld)) {
                idUsuarioModificaNew.getCClientesCollection().add(CClientes);
                idUsuarioModificaNew = em.merge(idUsuarioModificaNew);
            }
            for (HActivacion HActivacionCollectionOldHActivacion : HActivacionCollectionOld) {
                if (!HActivacionCollectionNew.contains(HActivacionCollectionOldHActivacion)) {
                    HActivacionCollectionOldHActivacion.setIdCliente(null);
                    HActivacionCollectionOldHActivacion = em.merge(HActivacionCollectionOldHActivacion);
                }
            }
            for (HActivacion HActivacionCollectionNewHActivacion : HActivacionCollectionNew) {
                if (!HActivacionCollectionOld.contains(HActivacionCollectionNewHActivacion)) {
                    CClientes oldIdClienteOfHActivacionCollectionNewHActivacion = HActivacionCollectionNewHActivacion.getIdCliente();
                    HActivacionCollectionNewHActivacion.setIdCliente(CClientes);
                    HActivacionCollectionNewHActivacion = em.merge(HActivacionCollectionNewHActivacion);
                    if (oldIdClienteOfHActivacionCollectionNewHActivacion != null && !oldIdClienteOfHActivacionCollectionNewHActivacion.equals(CClientes)) {
                        oldIdClienteOfHActivacionCollectionNewHActivacion.getHActivacionCollection().remove(HActivacionCollectionNewHActivacion);
                        oldIdClienteOfHActivacionCollectionNewHActivacion = em.merge(oldIdClienteOfHActivacionCollectionNewHActivacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = CClientes.getIdCliente();
                if (findCClientes(id) == null) {
                    throw new NonexistentEntityException("The cClientes with id " + id + " no longer exists.");
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
            CClientes CClientes;
            try {
                CClientes = em.getReference(CClientes.class, id);
                CClientes.getIdCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The CClientes with id " + id + " no longer exists.", enfe);
            }
            CCiudad idCiudad = CClientes.getIdCiudad();
            if (idCiudad != null) {
                idCiudad.getCClientesCollection().remove(CClientes);
                idCiudad = em.merge(idCiudad);
            }
            CDistribuidor idDistribuidor = CClientes.getIdDistribuidor();
            if (idDistribuidor != null) {
                idDistribuidor.getCClientesCollection().remove(CClientes);
                idDistribuidor = em.merge(idDistribuidor);
            }
            SUsuarios idUsuarioModifica = CClientes.getIdUsuarioModifica();
            if (idUsuarioModifica != null) {
                idUsuarioModifica.getCClientesCollection().remove(CClientes);
                idUsuarioModifica = em.merge(idUsuarioModifica);
            }
            Collection<HActivacion> HActivacionCollection = CClientes.getHActivacionCollection();
            for (HActivacion HActivacionCollectionHActivacion : HActivacionCollection) {
                HActivacionCollectionHActivacion.setIdCliente(null);
                HActivacionCollectionHActivacion = em.merge(HActivacionCollectionHActivacion);
            }
            em.remove(CClientes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CClientes> findCClientesEntities() {
        return findCClientesEntities(true, -1, -1);
    }

    public List<CClientes> findCClientesEntities(int maxResults, int firstResult) {
        return findCClientesEntities(false, maxResults, firstResult);
    }

    private List<CClientes> findCClientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CClientes.class));
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

    public CClientes findCClientes(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CClientes.class, id);
        } finally {
            em.close();
        }
    }

    public int getCClientesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CClientes> rt = cq.from(CClientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
