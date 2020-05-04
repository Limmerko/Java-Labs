/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Limmerko
 * @param <T>
 */
@Remote
public interface IDAO <T>{
    
    public List<T> getAll();
    
    public T getById(int id);
    
    public void delete(int id);
    
    public void update(T model);
    
    public T create(T model);
}
