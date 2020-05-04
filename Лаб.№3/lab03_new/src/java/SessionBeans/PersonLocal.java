/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import java.util.List;
import javax.ejb.Local;
import model.Person;
/**
 *
 * @author Limmerko
 */
@Local
public interface PersonLocal {
    
    public void add(Person person);
    
    public void update(Person person);
    
    public void remove(Person person);
    
    public List<Person> getAll();
    
    public Person getById(Long id);
} 
