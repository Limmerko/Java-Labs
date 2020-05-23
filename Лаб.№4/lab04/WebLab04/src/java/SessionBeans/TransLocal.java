/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import javax.ejb.Local;
import model.Company;
import model.Person;

/**
 *
 * @author Limmerko
 */
@Local
public interface TransLocal {
    
    
   public void transPersons();
   
   public void transCompanies();
   
   public void putIntoPersonsList(Person person);
   
   public void putIntoCompaniesList(Company company);
}
