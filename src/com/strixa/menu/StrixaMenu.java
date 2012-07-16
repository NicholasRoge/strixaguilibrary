/**
 * File:  RMenu.java
 * Date of Creation:  Jul 2, 2012
 */
package com.strixa.menu;

import javax.swing.JMenuBar;

import com.strixa.gui.StrixaWindow;
import com.strixa.gui.panel.StrixaPanel;


/**
 * Menu which should extended and used in conjunction with the RWindow class.
 *
 * @author Nicholas Rogé
 */
public abstract class StrixaMenu extends JMenuBar{
    private StrixaPanel  __associated_panel;
    private StrixaWindow __parent_window;    
    
    
    /*Begin Constructors*/
    /**
     * Constructs the panel.
     * 
     * @param parent Parent window.
     */
    public StrixaMenu(StrixaWindow parent){
        this(parent,null);
    }
    
    /**
     * Constructs the panel.
     * 
     * @param parent Parent window.
     * @param associated_panel The Panel with which this menu is associated.
     */
    public StrixaMenu(StrixaWindow parent,StrixaPanel associated_panel){
        if(parent==null){
            throw new NullPointerException("The 'parent' argument must not be null.");
        }
        
        this.__parent_window=parent;
        this.__associated_panel=associated_panel;
    }
    /*End Constructors*/
    
    /*Begin Getter/Setter Methods*/
    /**
     * @return Returns an initialized object of type RPanel.
     */
    public StrixaPanel getAssociatedPanel(){
        return this.__associated_panel;
    }
    
    /**
     * @return Returns an initialized object of type RWindow.
     */
    public StrixaWindow getParentWindow(){
        return this.__parent_window;
    }
    /*End Getter/Setter Methods*/
}
