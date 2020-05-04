/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Work;

/**
 *
 * @author Limmerko
 */
@Stateless
public class WorkBean implements WorkLocal{
    
    @PersistenceContext(unitName = "lab03_newPU")
    private EntityManager entityManager;
    
    @Override
    public void add(Work work) {
        entityManager.persist(work);
    }

    @Override
    public void update(Work work) {
       entityManager.merge(work);
    }

    @Override
    public void remove(Work work) {
        entityManager.remove(entityManager.merge(work));
    }

    @Override
    public List<Work> getAll() {
        return entityManager.createQuery(
                "SELECT w FROM Work w").getResultList();
    }

    @Override
    public Work getById(Long id) {
        return entityManager.find(Work.class, id);
    }
    
}
