/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

/**
 *
 * @author Limmerko
 */
@Singleton
public class CountNews {
    
    int count;
    
    @PostConstruct
    public void init() {
        count = 0;
    }

    public int getCount() {
        return count;
    }
    
    public void add() {
        ++count;
    }
}
