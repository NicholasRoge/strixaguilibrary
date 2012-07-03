/**
 * File:  RMenu.java
 * Date of Creation:  Jul 2, 2012
 */
package roge.gui.menu;

import javax.swing.JMenuBar;

import roge.gui.RWindow;
import roge.gui.panel.RPanel;

/**
 * Menu which should extended and used in conjunction with the RWindow class.
 *
 * @author Nicholas Rogé
 */
public abstract class RMenu extends JMenuBar{
    private RPanel  __associated_panel;
    private RWindow __parent_window;    
    
    
    /*Begin Constructors*/
    /**
     * Constructs the panel.
     * 
     * @param parent Parent window.
     */
    public RMenu(RWindow parent){
        this(parent,null);
    }
    
    /**
     * Constructs the panel.
     * 
     * @param parent Parent window.
     * @param associated_panel The Panel with which this menu is associated.
     */
    public RMenu(RWindow parent,RPanel associated_panel){
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
    public RPanel getAssociatedPanel(){
        return this.__associated_panel;
    }
    
    /**
     * @return Returns an initialized object of type RWindow.
     */
    public RWindow getParentWindow(){
        return this.__parent_window;
    }
    /*End Getter/Setter Methods*/
}
