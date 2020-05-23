/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import java.util.List;
import javax.ejb.Local;
import model.Company;

/**
 *
 * @author Limmerko
 */
@Local
public interface CompanyLocal {
   
    public void add(Company company);
    
    public void update(Company company);
    
    public void remove(Company companyn);
    
    public List<Company> getAll();
    
    public Company getById(Long id);
}
