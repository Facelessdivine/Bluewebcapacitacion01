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
import entities.CTipoTelefono;
import entities.HActivacion;
import entities.HActivacion_;
import entities.SUsuarios_;
import entities.SUsuarios;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import models.exceptions.NonexistentEntityException;
import models.exceptions.PreexistingEntityException;
import utils.LocalEntityManagerFactory;

/**
 *
 * @author Raúl Herrera Macías
 */
public class HActivacionJpaController implements Serializable {

    public HActivacionJpaController() {
        this.emf = LocalEntityManagerFactory.getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    private List<HActivacion> lista;
//<editor-fold defaultstate="collapsed" desc="functions crud controller">

    public void create(HActivacion HActivacion) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CCiudad idCiudad = HActivacion.getIdCiudad();
            if (idCiudad != null) {
                idCiudad = em.getReference(idCiudad.getClass(), idCiudad.getIdCiudad());
                HActivacion.setIdCiudad(idCiudad);
            }
            CClientes idCliente = HActivacion.getIdCliente();
            if (idCliente != null) {
                idCliente = em.getReference(idCliente.getClass(), idCliente.getIdCliente());
                HActivacion.setIdCliente(idCliente);
            }
            CDistribuidor idDistribuidor = HActivacion.getIdDistribuidor();
            if (idDistribuidor != null) {
                idDistribuidor = em.getReference(idDistribuidor.getClass(), idDistribuidor.getIdDistribuidor());
                HActivacion.setIdDistribuidor(idDistribuidor);
            }
            CTipoTelefono idTipoTelefonia = HActivacion.getIdTipoTelefonia();
            if (idTipoTelefonia != null) {
                idTipoTelefonia = em.getReference(idTipoTelefonia.getClass(), idTipoTelefonia.getId());
                HActivacion.setIdTipoTelefonia(idTipoTelefonia);
            }
            SUsuarios idUsuario = HActivacion.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                HActivacion.setIdUsuario(idUsuario);
            }
            em.persist(HActivacion);
            if (idCiudad != null) {
                idCiudad.getHActivacionCollection().add(HActivacion);
                idCiudad = em.merge(idCiudad);
            }
            if (idCliente != null) {
                idCliente.getHActivacionCollection().add(HActivacion);
                idCliente = em.merge(idCliente);
            }
            if (idDistribuidor != null) {
                idDistribuidor.getHActivacionCollection().add(HActivacion);
                idDistribuidor = em.merge(idDistribuidor);
            }
            if (idTipoTelefonia != null) {
                idTipoTelefonia.getHActivacionCollection().add(HActivacion);
                idTipoTelefonia = em.merge(idTipoTelefonia);
            }
            if (idUsuario != null) {
                idUsuario.getHActivacionCollection().add(HActivacion);
                idUsuario = em.merge(idUsuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHActivacion(HActivacion.getId()) != null) {
                throw new PreexistingEntityException("HActivacion " + HActivacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HActivacion HActivacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HActivacion persistentHActivacion = em.find(HActivacion.class, HActivacion.getId());
            CCiudad idCiudadOld = persistentHActivacion.getIdCiudad();
            CCiudad idCiudadNew = HActivacion.getIdCiudad();
            CClientes idClienteOld = persistentHActivacion.getIdCliente();
            CClientes idClienteNew = HActivacion.getIdCliente();
            CDistribuidor idDistribuidorOld = persistentHActivacion.getIdDistribuidor();
            CDistribuidor idDistribuidorNew = HActivacion.getIdDistribuidor();
            CTipoTelefono idTipoTelefoniaOld = persistentHActivacion.getIdTipoTelefonia();
            CTipoTelefono idTipoTelefoniaNew = HActivacion.getIdTipoTelefonia();
            SUsuarios idUsuarioOld = persistentHActivacion.getIdUsuario();
            SUsuarios idUsuarioNew = HActivacion.getIdUsuario();
            if (idCiudadNew != null) {
                idCiudadNew = em.getReference(idCiudadNew.getClass(), idCiudadNew.getIdCiudad());
                HActivacion.setIdCiudad(idCiudadNew);
            }
            if (idClienteNew != null) {
                idClienteNew = em.getReference(idClienteNew.getClass(), idClienteNew.getIdCliente());
                HActivacion.setIdCliente(idClienteNew);
            }
            if (idDistribuidorNew != null) {
                idDistribuidorNew = em.getReference(idDistribuidorNew.getClass(), idDistribuidorNew.getIdDistribuidor());
                HActivacion.setIdDistribuidor(idDistribuidorNew);
            }
            if (idTipoTelefoniaNew != null) {
                idTipoTelefoniaNew = em.getReference(idTipoTelefoniaNew.getClass(), idTipoTelefoniaNew.getId());
                HActivacion.setIdTipoTelefonia(idTipoTelefoniaNew);
            }
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                HActivacion.setIdUsuario(idUsuarioNew);
            }
            HActivacion = em.merge(HActivacion);
            if (idCiudadOld != null && !idCiudadOld.equals(idCiudadNew)) {
                idCiudadOld.getHActivacionCollection().remove(HActivacion);
                idCiudadOld = em.merge(idCiudadOld);
            }
            if (idCiudadNew != null && !idCiudadNew.equals(idCiudadOld)) {
                idCiudadNew.getHActivacionCollection().add(HActivacion);
                idCiudadNew = em.merge(idCiudadNew);
            }
            if (idClienteOld != null && !idClienteOld.equals(idClienteNew)) {
                idClienteOld.getHActivacionCollection().remove(HActivacion);
                idClienteOld = em.merge(idClienteOld);
            }
            if (idClienteNew != null && !idClienteNew.equals(idClienteOld)) {
                idClienteNew.getHActivacionCollection().add(HActivacion);
                idClienteNew = em.merge(idClienteNew);
            }
            if (idDistribuidorOld != null && !idDistribuidorOld.equals(idDistribuidorNew)) {
                idDistribuidorOld.getHActivacionCollection().remove(HActivacion);
                idDistribuidorOld = em.merge(idDistribuidorOld);
            }
            if (idDistribuidorNew != null && !idDistribuidorNew.equals(idDistribuidorOld)) {
                idDistribuidorNew.getHActivacionCollection().add(HActivacion);
                idDistribuidorNew = em.merge(idDistribuidorNew);
            }
            if (idTipoTelefoniaOld != null && !idTipoTelefoniaOld.equals(idTipoTelefoniaNew)) {
                idTipoTelefoniaOld.getHActivacionCollection().remove(HActivacion);
                idTipoTelefoniaOld = em.merge(idTipoTelefoniaOld);
            }
            if (idTipoTelefoniaNew != null && !idTipoTelefoniaNew.equals(idTipoTelefoniaOld)) {
                idTipoTelefoniaNew.getHActivacionCollection().add(HActivacion);
                idTipoTelefoniaNew = em.merge(idTipoTelefoniaNew);
            }
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getHActivacionCollection().remove(HActivacion);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getHActivacionCollection().add(HActivacion);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = HActivacion.getId();
                if (findHActivacion(id) == null) {
                    throw new NonexistentEntityException("The hActivacion with id " + id + " no longer exists.");
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
            HActivacion HActivacion;
            try {
                HActivacion = em.getReference(HActivacion.class, id);
                HActivacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The HActivacion with id " + id + " no longer exists.", enfe);
            }
            CCiudad idCiudad = HActivacion.getIdCiudad();
            if (idCiudad != null) {
                idCiudad.getHActivacionCollection().remove(HActivacion);
                idCiudad = em.merge(idCiudad);
            }
            CClientes idCliente = HActivacion.getIdCliente();
            if (idCliente != null) {
                idCliente.getHActivacionCollection().remove(HActivacion);
                idCliente = em.merge(idCliente);
            }
            CDistribuidor idDistribuidor = HActivacion.getIdDistribuidor();
            if (idDistribuidor != null) {
                idDistribuidor.getHActivacionCollection().remove(HActivacion);
                idDistribuidor = em.merge(idDistribuidor);
            }
            CTipoTelefono idTipoTelefonia = HActivacion.getIdTipoTelefonia();
            if (idTipoTelefonia != null) {
                idTipoTelefonia.getHActivacionCollection().remove(HActivacion);
                idTipoTelefonia = em.merge(idTipoTelefonia);
            }
            SUsuarios idUsuario = HActivacion.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getHActivacionCollection().remove(HActivacion);
                idUsuario = em.merge(idUsuario);
            }
            em.remove(HActivacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
//</editor-fold>

    public List<HActivacion> findHActivacionEntities() {
        return findHActivacionEntities(true, -1, -1);
    }

    public List<HActivacion> findHActivacionEntities(int maxResults, int firstResult) {
        return findHActivacionEntities(false, maxResults, firstResult);
    }

    private List<HActivacion> findHActivacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HActivacion.class));
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

    public HActivacion findHActivacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HActivacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getHActivacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HActivacion> rt = cq.from(HActivacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<HActivacion> getActivationsByDate(SUsuarios idUsuario, final Date startDate, final Date endDate) {

        lista = new ArrayList<>();
        List<Predicate> restrictions = new ArrayList<>();
        EntityManager em = getEntityManager();
        try {
            em = getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();

            CriteriaQuery<HActivacion> query = cb.createQuery(HActivacion.class);

            Root<HActivacion> hactivacion = query.from(HActivacion.class);

            Join<HActivacion, SUsuarios> user = hactivacion.join(HActivacion_.idUsuario);

            query.select(hactivacion).where(cb.equal(user.get(SUsuarios_.idUsuario), idUsuario));
            if(idUsuario != null){
            restrictions.add(cb.equal(hactivacion.get(HActivacion_.idUsuario), idUsuario));
            }
            restrictions.add(cb.between(hactivacion.<Date>get(HActivacion_.fechaPeticion), startDate, endDate));
            query.where(restrictions.toArray(new Predicate[restrictions.size()]));

            TypedQuery<HActivacion> typedQuery = em.createQuery(query);
            lista = typedQuery.getResultList();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return lista;
    }

    public List<HActivacion> getActivationsById(SUsuarios user) {
        HActivacion activaciones;
        List<HActivacion> result = new ArrayList<HActivacion>();
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction();
            StoredProcedureQuery query = em.createNamedStoredProcedureQuery("r_activacionesbyuser");

            query.setParameter("id", user.getIdUsuario());
            query.execute();
            List<Object[]> resultado = query.getResultList();

            for (Object[] i : resultado) {
                activaciones = new HActivacion();
                activaciones.setDescripcionTipo(i[0].toString());
                activaciones.setRespuestaAplicacion(i[1].toString());
                activaciones.setTelefono(i[2].toString());
                activaciones.setIdUsuario(user);
                result.add(activaciones);
            }

        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return result;
    }

}
