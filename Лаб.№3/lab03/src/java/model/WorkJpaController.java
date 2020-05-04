/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import model.exceptions.NonexistentEntityException;
import model.exceptions.RollbackFailureException;

/**
 *
 * @author Limmerko
 */
public class WorkJpaController implements Serializable {

    public WorkJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Work work) throws RollbackFailureException, Exception {
        if (work.getPersonCollection() == null) {
            work.setPersonCollection(new ArrayList<Person>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Person> attachedPersonCollection = new ArrayList<Person>();
            for (Person personCollectionPersonToAttach : work.getPersonCollection()) {
                personCollectionPersonToAttach = em.getReference(personCollectionPersonToAttach.getClass(), personCollectionPersonToAttach.getId());
                attachedPersonCollection.add(personCollectionPersonToAttach);
            }
            work.setPersonCollection(attachedPersonCollection);
            em.persist(work);
            for (Person personCollectionPerson : work.getPersonCollection()) {
                Work oldPlaceOfWorkOfPersonCollectionPerson = personCollectionPerson.getPlaceOfWork();
                personCollectionPerson.setPlaceOfWork(work);
                personCollectionPerson = em.merge(personCollectionPerson);
                if (oldPlaceOfWorkOfPersonCollectionPerson != null) {
                    oldPlaceOfWorkOfPersonCollectionPerson.getPersonCollection().remove(personCollectionPerson);
                    oldPlaceOfWorkOfPersonCollectionPerson = em.merge(oldPlaceOfWorkOfPersonCollectionPerson);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Work work) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Work persistentWork = em.find(Work.class, work.getId());
            Collection<Person> personCollectionOld = persistentWork.getPersonCollection();
            Collection<Person> personCollectionNew = work.getPersonCollection();
            Collection<Person> attachedPersonCollectionNew = new ArrayList<Person>();
            for (Person personCollectionNewPersonToAttach : personCollectionNew) {
                personCollectionNewPersonToAttach = em.getReference(personCollectionNewPersonToAttach.getClass(), personCollectionNewPersonToAttach.getId());
                attachedPersonCollectionNew.add(personCollectionNewPersonToAttach);
            }
            personCollectionNew = attachedPersonCollectionNew;
            work.setPersonCollection(personCollectionNew);
            work = em.merge(work);
            for (Person personCollectionOldPerson : personCollectionOld) {
                if (!personCollectionNew.contains(personCollectionOldPerson)) {
                    personCollectionOldPerson.setPlaceOfWork(null);
                    personCollectionOldPerson = em.merge(personCollectionOldPerson);
                }
            }
            for (Person personCollectionNewPerson : personCollectionNew) {
                if (!personCollectionOld.contains(personCollectionNewPerson)) {
                    Work oldPlaceOfWorkOfPersonCollectionNewPerson = personCollectionNewPerson.getPlaceOfWork();
                    personCollectionNewPerson.setPlaceOfWork(work);
                    personCollectionNewPerson = em.merge(personCollectionNewPerson);
                    if (oldPlaceOfWorkOfPersonCollectionNewPerson != null && !oldPlaceOfWorkOfPersonCollectionNewPerson.equals(work)) {
                        oldPlaceOfWorkOfPersonCollectionNewPerson.getPersonCollection().remove(personCollectionNewPerson);
                        oldPlaceOfWorkOfPersonCollectionNewPerson = em.merge(oldPlaceOfWorkOfPersonCollectionNewPerson);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = work.getId();
                if (findWork(id) == null) {
                    throw new NonexistentEntityException("The work with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Work work;
            try {
                work = em.getReference(Work.class, id);
                work.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The work with id " + id + " no longer exists.", enfe);
            }
            Collection<Person> personCollection = work.getPersonCollection();
            for (Person personCollectionPerson : personCollection) {
                personCollectionPerson.setPlaceOfWork(null);
                personCollectionPerson = em.merge(personCollectionPerson);
            }
            em.remove(work);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Work> findWorkEntities() {
        return findWorkEntities(true, -1, -1);
    }

    public List<Work> findWorkEntities(int maxResults, int firstResult) {
        return findWorkEntities(false, maxResults, firstResult);
    }

    private List<Work> findWorkEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Work.class));
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

    public Work findWork(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Work.class, id);
        } finally {
            em.close();
        }
    }

    public int getWorkCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Work> rt = cq.from(Work.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
