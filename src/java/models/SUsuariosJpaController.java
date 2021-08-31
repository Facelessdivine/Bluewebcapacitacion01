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
import entities.SPerfiles;
import entities.CCiudad;
import java.util.ArrayList;
import java.util.Collection;
import entities.CClientes;
import entities.HActivacion;
import entities.SUsuarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import models.exceptions.NonexistentEntityException;
import models.exceptions.PreexistingEntityException;
import utils.LocalEntityManagerFactory;

/**
 *
 * @author Raúl Herrera Macías
 */
public class SUsuariosJpaController implements Serializable {

     public SUsuariosJpaController() {
       this.emf = LocalEntityManagerFactory.getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SUsuarios SUsuarios) throws PreexistingEntityException, Exception {
        if (SUsuarios.getCCiudadCollection() == null) {
            SUsuarios.setCCiudadCollection(new ArrayList<CCiudad>());
        }
        if (SUsuarios.getCClientesCollection() == null) {
            SUsuarios.setCClientesCollection(new ArrayList<CClientes>());
        }
        if (SUsuarios.getHActivacionCollection() == null) {
            SUsuarios.setHActivacionCollection(new ArrayList<HActivacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SPerfiles idPerfil = SUsuarios.getIdPerfil();
            if (idPerfil != null) {
                idPerfil = em.getReference(idPerfil.getClass(), idPerfil.getIdPerfil());
                SUsuarios.setIdPerfil(idPerfil);
            }
            Collection<CCiudad> attachedCCiudadCollection = new ArrayList<CCiudad>();
            for (CCiudad CCiudadCollectionCCiudadToAttach : SUsuarios.getCCiudadCollection()) {
                CCiudadCollectionCCiudadToAttach = em.getReference(CCiudadCollectionCCiudadToAttach.getClass(), CCiudadCollectionCCiudadToAttach.getIdCiudad());
                attachedCCiudadCollection.add(CCiudadCollectionCCiudadToAttach);
            }
            SUsuarios.setCCiudadCollection(attachedCCiudadCollection);
            Collection<CClientes> attachedCClientesCollection = new ArrayList<CClientes>();
            for (CClientes CClientesCollectionCClientesToAttach : SUsuarios.getCClientesCollection()) {
                CClientesCollectionCClientesToAttach = em.getReference(CClientesCollectionCClientesToAttach.getClass(), CClientesCollectionCClientesToAttach.getIdCliente());
                attachedCClientesCollection.add(CClientesCollectionCClientesToAttach);
            }
            SUsuarios.setCClientesCollection(attachedCClientesCollection);
            Collection<HActivacion> attachedHActivacionCollection = new ArrayList<HActivacion>();
            for (HActivacion HActivacionCollectionHActivacionToAttach : SUsuarios.getHActivacionCollection()) {
                HActivacionCollectionHActivacionToAttach = em.getReference(HActivacionCollectionHActivacionToAttach.getClass(), HActivacionCollectionHActivacionToAttach.getId());
                attachedHActivacionCollection.add(HActivacionCollectionHActivacionToAttach);
            }
            SUsuarios.setHActivacionCollection(attachedHActivacionCollection);
            em.persist(SUsuarios);
            if (idPerfil != null) {
                idPerfil.getSUsuariosCollection().add(SUsuarios);
                idPerfil = em.merge(idPerfil);
            }
            for (CCiudad CCiudadCollectionCCiudad : SUsuarios.getCCiudadCollection()) {
                SUsuarios oldIdUsuarioOfCCiudadCollectionCCiudad = CCiudadCollectionCCiudad.getIdUsuario();
                CCiudadCollectionCCiudad.setIdUsuario(SUsuarios);
                CCiudadCollectionCCiudad = em.merge(CCiudadCollectionCCiudad);
                if (oldIdUsuarioOfCCiudadCollectionCCiudad != null) {
                    oldIdUsuarioOfCCiudadCollectionCCiudad.getCCiudadCollection().remove(CCiudadCollectionCCiudad);
                    oldIdUsuarioOfCCiudadCollectionCCiudad = em.merge(oldIdUsuarioOfCCiudadCollectionCCiudad);
                }
            }
            for (CClientes CClientesCollectionCClientes : SUsuarios.getCClientesCollection()) {
                SUsuarios oldIdUsuarioModificaOfCClientesCollectionCClientes = CClientesCollectionCClientes.getIdUsuarioModifica();
                CClientesCollectionCClientes.setIdUsuarioModifica(SUsuarios);
                CClientesCollectionCClientes = em.merge(CClientesCollectionCClientes);
                if (oldIdUsuarioModificaOfCClientesCollectionCClientes != null) {
                    oldIdUsuarioModificaOfCClientesCollectionCClientes.getCClientesCollection().remove(CClientesCollectionCClientes);
                    oldIdUsuarioModificaOfCClientesCollectionCClientes = em.merge(oldIdUsuarioModificaOfCClientesCollectionCClientes);
                }
            }
            for (HActivacion HActivacionCollectionHActivacion : SUsuarios.getHActivacionCollection()) {
                SUsuarios oldIdUsuarioOfHActivacionCollectionHActivacion = HActivacionCollectionHActivacion.getIdUsuario();
                HActivacionCollectionHActivacion.setIdUsuario(SUsuarios);
                HActivacionCollectionHActivacion = em.merge(HActivacionCollectionHActivacion);
                if (oldIdUsuarioOfHActivacionCollectionHActivacion != null) {
                    oldIdUsuarioOfHActivacionCollectionHActivacion.getHActivacionCollection().remove(HActivacionCollectionHActivacion);
                    oldIdUsuarioOfHActivacionCollectionHActivacion = em.merge(oldIdUsuarioOfHActivacionCollectionHActivacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSUsuarios(SUsuarios.getIdUsuario()) != null) {
                throw new PreexistingEntityException("SUsuarios " + SUsuarios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SUsuarios SUsuarios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SUsuarios persistentSUsuarios = em.find(SUsuarios.class, SUsuarios.getIdUsuario());
            SPerfiles idPerfilOld = persistentSUsuarios.getIdPerfil();
            SPerfiles idPerfilNew = SUsuarios.getIdPerfil();
            Collection<CCiudad> CCiudadCollectionOld = persistentSUsuarios.getCCiudadCollection();
            Collection<CCiudad> CCiudadCollectionNew = SUsuarios.getCCiudadCollection();
            Collection<CClientes> CClientesCollectionOld = persistentSUsuarios.getCClientesCollection();
            Collection<CClientes> CClientesCollectionNew = SUsuarios.getCClientesCollection();
            Collection<HActivacion> HActivacionCollectionOld = persistentSUsuarios.getHActivacionCollection();
            Collection<HActivacion> HActivacionCollectionNew = SUsuarios.getHActivacionCollection();
            if (idPerfilNew != null) {
                idPerfilNew = em.getReference(idPerfilNew.getClass(), idPerfilNew.getIdPerfil());
                SUsuarios.setIdPerfil(idPerfilNew);
            }
            Collection<CCiudad> attachedCCiudadCollectionNew = new ArrayList<CCiudad>();
            for (CCiudad CCiudadCollectionNewCCiudadToAttach : CCiudadCollectionNew) {
                CCiudadCollectionNewCCiudadToAttach = em.getReference(CCiudadCollectionNewCCiudadToAttach.getClass(), CCiudadCollectionNewCCiudadToAttach.getIdCiudad());
                attachedCCiudadCollectionNew.add(CCiudadCollectionNewCCiudadToAttach);
            }
            CCiudadCollectionNew = attachedCCiudadCollectionNew;
            SUsuarios.setCCiudadCollection(CCiudadCollectionNew);
            Collection<CClientes> attachedCClientesCollectionNew = new ArrayList<CClientes>();
            for (CClientes CClientesCollectionNewCClientesToAttach : CClientesCollectionNew) {
                CClientesCollectionNewCClientesToAttach = em.getReference(CClientesCollectionNewCClientesToAttach.getClass(), CClientesCollectionNewCClientesToAttach.getIdCliente());
                attachedCClientesCollectionNew.add(CClientesCollectionNewCClientesToAttach);
            }
            CClientesCollectionNew = attachedCClientesCollectionNew;
            SUsuarios.setCClientesCollection(CClientesCollectionNew);
            Collection<HActivacion> attachedHActivacionCollectionNew = new ArrayList<HActivacion>();
            for (HActivacion HActivacionCollectionNewHActivacionToAttach : HActivacionCollectionNew) {
                HActivacionCollectionNewHActivacionToAttach = em.getReference(HActivacionCollectionNewHActivacionToAttach.getClass(), HActivacionCollectionNewHActivacionToAttach.getId());
                attachedHActivacionCollectionNew.add(HActivacionCollectionNewHActivacionToAttach);
            }
            HActivacionCollectionNew = attachedHActivacionCollectionNew;
            SUsuarios.setHActivacionCollection(HActivacionCollectionNew);
            SUsuarios = em.merge(SUsuarios);
            if (idPerfilOld != null && !idPerfilOld.equals(idPerfilNew)) {
                idPerfilOld.getSUsuariosCollection().remove(SUsuarios);
                idPerfilOld = em.merge(idPerfilOld);
            }
            if (idPerfilNew != null && !idPerfilNew.equals(idPerfilOld)) {
                idPerfilNew.getSUsuariosCollection().add(SUsuarios);
                idPerfilNew = em.merge(idPerfilNew);
            }
            for (CCiudad CCiudadCollectionOldCCiudad : CCiudadCollectionOld) {
                if (!CCiudadCollectionNew.contains(CCiudadCollectionOldCCiudad)) {
                    CCiudadCollectionOldCCiudad.setIdUsuario(null);
                    CCiudadCollectionOldCCiudad = em.merge(CCiudadCollectionOldCCiudad);
                }
            }
            for (CCiudad CCiudadCollectionNewCCiudad : CCiudadCollectionNew) {
                if (!CCiudadCollectionOld.contains(CCiudadCollectionNewCCiudad)) {
                    SUsuarios oldIdUsuarioOfCCiudadCollectionNewCCiudad = CCiudadCollectionNewCCiudad.getIdUsuario();
                    CCiudadCollectionNewCCiudad.setIdUsuario(SUsuarios);
                    CCiudadCollectionNewCCiudad = em.merge(CCiudadCollectionNewCCiudad);
                    if (oldIdUsuarioOfCCiudadCollectionNewCCiudad != null && !oldIdUsuarioOfCCiudadCollectionNewCCiudad.equals(SUsuarios)) {
                        oldIdUsuarioOfCCiudadCollectionNewCCiudad.getCCiudadCollection().remove(CCiudadCollectionNewCCiudad);
                        oldIdUsuarioOfCCiudadCollectionNewCCiudad = em.merge(oldIdUsuarioOfCCiudadCollectionNewCCiudad);
                    }
                }
            }
            for (CClientes CClientesCollectionOldCClientes : CClientesCollectionOld) {
                if (!CClientesCollectionNew.contains(CClientesCollectionOldCClientes)) {
                    CClientesCollectionOldCClientes.setIdUsuarioModifica(null);
                    CClientesCollectionOldCClientes = em.merge(CClientesCollectionOldCClientes);
                }
            }
            for (CClientes CClientesCollectionNewCClientes : CClientesCollectionNew) {
                if (!CClientesCollectionOld.contains(CClientesCollectionNewCClientes)) {
                    SUsuarios oldIdUsuarioModificaOfCClientesCollectionNewCClientes = CClientesCollectionNewCClientes.getIdUsuarioModifica();
                    CClientesCollectionNewCClientes.setIdUsuarioModifica(SUsuarios);
                    CClientesCollectionNewCClientes = em.merge(CClientesCollectionNewCClientes);
                    if (oldIdUsuarioModificaOfCClientesCollectionNewCClientes != null && !oldIdUsuarioModificaOfCClientesCollectionNewCClientes.equals(SUsuarios)) {
                        oldIdUsuarioModificaOfCClientesCollectionNewCClientes.getCClientesCollection().remove(CClientesCollectionNewCClientes);
                        oldIdUsuarioModificaOfCClientesCollectionNewCClientes = em.merge(oldIdUsuarioModificaOfCClientesCollectionNewCClientes);
                    }
                }
            }
            for (HActivacion HActivacionCollectionOldHActivacion : HActivacionCollectionOld) {
                if (!HActivacionCollectionNew.contains(HActivacionCollectionOldHActivacion)) {
                    HActivacionCollectionOldHActivacion.setIdUsuario(null);
                    HActivacionCollectionOldHActivacion = em.merge(HActivacionCollectionOldHActivacion);
                }
            }
            for (HActivacion HActivacionCollectionNewHActivacion : HActivacionCollectionNew) {
                if (!HActivacionCollectionOld.contains(HActivacionCollectionNewHActivacion)) {
                    SUsuarios oldIdUsuarioOfHActivacionCollectionNewHActivacion = HActivacionCollectionNewHActivacion.getIdUsuario();
                    HActivacionCollectionNewHActivacion.setIdUsuario(SUsuarios);
                    HActivacionCollectionNewHActivacion = em.merge(HActivacionCollectionNewHActivacion);
                    if (oldIdUsuarioOfHActivacionCollectionNewHActivacion != null && !oldIdUsuarioOfHActivacionCollectionNewHActivacion.equals(SUsuarios)) {
                        oldIdUsuarioOfHActivacionCollectionNewHActivacion.getHActivacionCollection().remove(HActivacionCollectionNewHActivacion);
                        oldIdUsuarioOfHActivacionCollectionNewHActivacion = em.merge(oldIdUsuarioOfHActivacionCollectionNewHActivacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = SUsuarios.getIdUsuario();
                if (findSUsuarios(id) == null) {
                    throw new NonexistentEntityException("The sUsuarios with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SUsuarios SUsuarios;
            try {
                SUsuarios = em.getReference(SUsuarios.class, id);
                SUsuarios.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The SUsuarios with id " + id + " no longer exists.", enfe);
            }
            SPerfiles idPerfil = SUsuarios.getIdPerfil();
            if (idPerfil != null) {
                idPerfil.getSUsuariosCollection().remove(SUsuarios);
                idPerfil = em.merge(idPerfil);
            }
            Collection<CCiudad> CCiudadCollection = SUsuarios.getCCiudadCollection();
            for (CCiudad CCiudadCollectionCCiudad : CCiudadCollection) {
                CCiudadCollectionCCiudad.setIdUsuario(null);
                CCiudadCollectionCCiudad = em.merge(CCiudadCollectionCCiudad);
            }
            Collection<CClientes> CClientesCollection = SUsuarios.getCClientesCollection();
            for (CClientes CClientesCollectionCClientes : CClientesCollection) {
                CClientesCollectionCClientes.setIdUsuarioModifica(null);
                CClientesCollectionCClientes = em.merge(CClientesCollectionCClientes);
            }
            Collection<HActivacion> HActivacionCollection = SUsuarios.getHActivacionCollection();
            for (HActivacion HActivacionCollectionHActivacion : HActivacionCollection) {
                HActivacionCollectionHActivacion.setIdUsuario(null);
                HActivacionCollectionHActivacion = em.merge(HActivacionCollectionHActivacion);
            }
            em.remove(SUsuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SUsuarios> findSUsuariosEntities() {
        return findSUsuariosEntities(true, -1, -1);
    }

    public List<SUsuarios> findSUsuariosEntities(int maxResults, int firstResult) {
        return findSUsuariosEntities(false, maxResults, firstResult);
    }

    private List<SUsuarios> findSUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SUsuarios.class));
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

    public SUsuarios findSUsuarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SUsuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getSUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SUsuarios> rt = cq.from(SUsuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
