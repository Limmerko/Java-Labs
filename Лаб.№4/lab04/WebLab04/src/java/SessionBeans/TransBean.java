/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import model.Company;
import model.Person;

/**
 *
 * @author Limmerko
 */
@Singleton
@TransactionManagement(TransactionManagementType.BEAN)
public class TransBean implements TransLocal{
    
    List<Person> persons = new ArrayList<>();
    
    List<Company> companies = new ArrayList<>();
    
    @EJB
    private PersonLocal personEjb;
    
    @EJB
    private CompanyLocal companyEjb;
    
    @Resource javax.transaction.UserTransaction userTransaction;

    @Override
    public void transPersons() {
        try {
            userTransaction.begin(); // начало транзакции
            for (Person person : persons) {
                personEjb.add(person); // добавление записей бд
            }
            userTransaction.commit(); // отправить
        } catch (NotSupportedException | SystemException | 
                RollbackException | HeuristicMixedException | 
                HeuristicRollbackException | SecurityException | 
                IllegalStateException e) {
            Logger.getLogger(TransBean.class.getName()).log(Level.SEVERE, null, e);
        }
        persons = new ArrayList<>();
    }

    @Override
    public void transCompanies() {
        try {
            userTransaction.begin();
            for (Company company: companies) {
                companyEjb.add(company);
            }
            userTransaction.commit(); // отправить
        } catch (NotSupportedException | SystemException | 
                RollbackException | HeuristicMixedException | 
                HeuristicRollbackException | SecurityException | 
                IllegalStateException e) {
            Logger.getLogger(TransBean.class.getName()).log(Level.SEVERE, null, e);
        }
        companies = new ArrayList<>();
    }

    @Override
    public void putIntoPersonsList(Person person) {
        persons.add(person);
    }

    @Override
    public void putIntoCompaniesList(Company company) {
        companies.add(company);
    }  
}
