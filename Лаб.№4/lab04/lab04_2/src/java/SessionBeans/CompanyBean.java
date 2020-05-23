/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import javax.ejb.SessionContext;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Company;

/**
 *
 * @author Limmerko
 */
@Stateless
public class CompanyBean implements CompanyLocal{
    
    @PersistenceContext(unitName = "lab04_2PU")
    private EntityManager entityManager;
    
    @Resource
    private SessionContext context;

    @Override
    @TransactionAttribute(NOT_SUPPORTED)
    public void add(Company company) {
        entityManager.persist(company);
        // создаем условаие для отметы транзакции
        if (company.getTitle().length() > 20) {
            context.setRollbackOnly();
        }
    }

    @Override
    public void update(Company company) {
       entityManager.merge(company);
    }

    @Override
    public void remove(Company company) {
        entityManager.remove(entityManager.merge(company));
    }

    @Override
    public List<Company> getAll() {
        return entityManager.createQuery(
                "SELECT company FROM Company company").getResultList();
    }

    @Override
    public Company getById(Long id) {
        return entityManager.find(Company.class, id);
    }
}
