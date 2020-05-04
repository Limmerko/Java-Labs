/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Person;

/**
 *
 * @author Limmerko
 */
public class PersonBean  implements PersonLocal{
    
    @PersistenceContext(unitName = "lab03_newPU")
    private EntityManager entityManager;

    @Override
    public void add(Person person) {
        entityManager.persist(person);
    }

    @Override
    public void update(Person person) {
       entityManager.merge(person);
    }

    @Override
    public void remove(Person person) {
        entityManager.remove(entityManager.merge(person));
    }

    @Override
    public List<Person> getAll() {
        return entityManager.createQuery(
                "SELECT p FROM Person p").getResultList();
    }

    @Override
    public Person getById(Long id) {
        return entityManager.find(Person.class, id);
    }
    
}
