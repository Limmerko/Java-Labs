/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Limmerko
 */
@Stateful
@SessionScoped
public class StatefulBean implements IStatefulBean, Serializable {
    
    private List<String> titleList = new ArrayList<>();

    @PostConstruct
    private void init() {
        titleList = new ArrayList<>();
    }
    
    @Override
    public void addTitle(String title) {
        titleList.add(title);
    }

    @Override
    public List<String> returnList() {
        return titleList;
    }
}
