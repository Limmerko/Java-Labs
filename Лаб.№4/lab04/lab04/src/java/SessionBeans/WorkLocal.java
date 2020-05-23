/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import java.util.List;
import model.Work;
import javax.ejb.Local;


/**
 *
 * @author Limmerko
 */
@Local
public interface WorkLocal {
    
    public void add(Work work);
    
    public void update(Work work);
    
    public void remove(Work work);
    
    public List<Work> getAll();
    
    public Work getById(Long id);
}
