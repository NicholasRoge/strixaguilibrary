/**
 * File:  RPanel.java
 * Date of Creation:  Jul 2, 2012
 */
package com.strixa.gui.panel;

import javax.swing.JPanel;

import com.strixa.gui.StrixaWindow;



/**
 * Panel which should be extended and used in conjunction with the RWindow class.
 * 
 * @author Nicholas Rogé
 */
public abstract class StrixaPanel extends JPanel{
    private StrixaWindow __parent_window;    
    
    
    /*Begin Constructors*/
    /**
     * Constructs the panel.
     * 
     * @param parent Parent window.
     */
    public StrixaPanel(StrixaWindow parent){
        if(parent==null){
            throw new NullPointerException("The 'parent' argument must not be null.");
        }
        
        this.__parent_window=parent;
    }
    /*End Constructors*/
    
    /*Begin Getter/Setter Methods*/
    /**
     * @return Returns an initialized object of type RWindow.
     */
    public StrixaWindow getParentWindow(){
        return this.__parent_window;
    }
    /*End Getter/Setter Methods*/
    
    /*Begin Other Essential Methods*/
    /**
     * This method is called when the panel is being disposed of and should only be called by the parent window.  
     */
    public void onPanelClose(){}
    /*End Other Essential Methods*/
}
