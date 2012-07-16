/**
 * File:  About.java
 * Date of Creation:  Jul 2, 2012
 */
package com.strixa.gui.panel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import com.strixa.gui.StrixaWindow;


/**
 * Base for a simple about window panel.
 *
 * @author Nicholas Rogé
 */
public abstract class About extends StrixaPanel{
    /**
     * Action the Close button should take.
     * 
     * @author Nicholas Rogé
     */
    private class __CloseAction implements ActionListener{
        /*Begin Overridden Methods*/
        @Override public void actionPerformed(ActionEvent event){
            About.this.getParentWindow().performAction(StrixaWindow.Actions.CLOSE_WINDOW);
        }
        /*End Overridden Methods*/
    }
    
    
    /*Begin Constructors*/
    /**
     * Constructs the About object.
     * 
     * @param parent Parent Window
     */
    public About(StrixaWindow parent){
        super(parent);
        
        this._applyGUI();
    }
    /*End Constructors*/
    
    /*Begin Other Essential Methods*/
    protected void _applyGUI(){
        JLabel  label = null;
        JButton button = null;
        
        
        this.setBackground(Color.WHITE);
        
        //Add the contents
        this.setBorder(new EmptyBorder(20,20,20,20));
        this.setLayout(new GridLayout(2,1));
        {
            label = new JLabel(this.getContents());
            this.add(label);
        }
        {
            button = new JButton("Close");
            button.addActionListener(new __CloseAction());
            this.add(button);
        }
    }
    /*End Other Essential Methods*/
    
    /*Begin Abstract Methods*/
    /**
     * Gets the contents of the main label of the about panel.
     * 
     * @return The main label's text contents.
     */
    public abstract String getContents();
    /*End Abstract Methods*/
}
