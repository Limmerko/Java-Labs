/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.interceptor.InvocationContext;

/**
 *
 * @author Limmerko
 */
public class Interceptor implements Serializable {
    
    @EJB
    CountNews countNews;
    
    public Object addInterceptor(InvocationContext context) throws Exception {
        countNews.add();
        return context.proceed();
    }
}
