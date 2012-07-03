/**
 * File:  RWindow.java
 * Date of Creation:  May 5, 2012
 */
package roge.gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.UIManager;
import roge.gui.menu.RMenu;
import roge.gui.panel.RPanel;

/**
 * Wrapper for the JFrame class to make it a little bit more convenient to use.
 * 
 * @author Nicholas Rogé
 */
public abstract class RWindow extends JFrame implements WindowListener{
    /**
     * Interface which should be implemented if a class wishes to create its own WindowActions.
     *
     * @author Nicholas Rogé
     */
    public static interface WindowAction{
        /**
         * Performs an action.
         * 
         * @param data Data passed into this method upon RWindow calling its performAction method.
         * 
         * @return Method should return true if the action could be performed, and false otherwise.
         */
        public boolean performAction(Object data);
    }
    
    /**
     * Action to be taken upon window closure.
     *
     * @author Nicholas Rogé
     */
    private class __CloseWindowAction implements WindowAction{
        /*Begin Implemented Methods*/
        @Override public boolean performAction(Object data){
            final WindowEvent event = (WindowEvent)data;
            
            RWindow.this.onWindowClosing(event);
            if(RWindow.this.__current_panel!=null){
                RWindow.this.__current_panel.onPanelClose();
            }

            RWindow.this.dispose();
            RWindow.this.setVisible(false);
            
            return true;
        }
        /*End Implemented Methods*/
    }
    
    /**
     * List of actions available to this window.
     *
     * @author Nicholas Rogé
     */
    public static class Actions{
        /**Indicates that the window should be closed.*/
        public static final int CLOSE_WINDOW = 0;
    }
    
    /*Begin Final Variables*/
    /**String to be returned in the event that this window has not had its title set yet.*/
    public static final String NO_TITLE="Untitled Window";
    /*End Final Variables*/
    
    /*Begin Member Variables*/
    private RPanel                    __current_panel;
    private String                    __window_title;
    private Map<Integer,WindowAction> __window_actions;
    /*End Member Variables*/
    
    
    /*Begin Constructors*/
    /**
     * Constructs this window with the default data.
     */
    public RWindow(){
        this(RWindow.NO_TITLE);
    }
    
    /**
     * Constructs this window with the given title.
     * 
     * @param title The title this window should have.
     */
    public RWindow(String title){
        this.setWindowTitle(title);
        
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){
            System.out.println("Could not get the System's LookAndFeel classname.");
        }
        
        try{
        	System.setProperty("apple.laf.useScreenMenuBar","true");
        }catch(Exception e){
        	System.out.println("Not using Mac Look and Feel");
        }
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        this.addWindowListener(this);
        
        //Add the action listeners
        this.__registerWindowAction(Actions.CLOSE_WINDOW,new __CloseWindowAction());
    }
    /*End Constructors*/
    
    /*Begin Overridden Methods*/
    @Override public void windowClosing(WindowEvent event){
        this.performAction(RWindow.Actions.CLOSE_WINDOW,event);
    }

    @Override public void windowActivated(WindowEvent arg0){
    }

    @Override public void windowClosed(WindowEvent arg0){
    }

    @Override public void windowDeactivated(WindowEvent arg0){
    }

    @Override public void windowDeiconified(WindowEvent arg0){
    }

    @Override public void windowIconified(WindowEvent arg0){
    }

    @Override public void windowOpened(WindowEvent arg0){
    }
    /*End Overridden Methods*/
    
    /*Begin Getter/Setter Methods*/
    /**
     * Gets the panel most recently set with the {@link #setActivePanel(RPanel)} method.
     * 
     * @return An RPanel value.
     */
    public RPanel getCurrentPanel(){
        return this.__current_panel;
    }
    
    /**
     * @return Returns an initialized object of type List<WindowAction>.
     */
    public Map<Integer,WindowAction> getWindowActions(){
        if(this.__window_actions==null){
            this.__window_actions=new HashMap<Integer,WindowAction>();
        }
        
        return this.__window_actions;
    }
    
    /**
     * Gets the <code>__window_title</code> member variable.
     * 
     * @return Returns the window's title, if it has been set, or {@link RWindow#NO_TITLE} if it has not.
     */
    public String getWindowTitle(){
        if(this.__window_title==null){
            return RWindow.NO_TITLE;
        }else{
            return this.__window_title;
        }
    }
    
    /**
     * Sets the window's title to the specified string.
     * 
     * @param title String to set the window's title to.
     * 
     * @return The <code>title</code> parameter.
     */
    public String setWindowTitle(String title){
        this.__window_title=title;
        this.setTitle(title);
        
        return title;
    }
    /*End Getter/Setter Methods*/
    
    /*Begin Other Essential Methods*/
    private boolean __doRegisterWindowAction(int action_id,WindowAction action,boolean check_for_reserved){
        final Map<Integer,WindowAction> action_list = this.getWindowActions();
        
        
        if(action==null){
            throw new NullPointerException("Argument 'action' must not be null.");
        }
        
        if(check_for_reserved&&(action_id>=1&&action_id<=100)){
            return false;  //These are reserved for RWindow
        }
        
        action_list.put(action_id,action);
        
        return true;
    }
    
    protected void onWindowClosing(WindowEvent event){}
    
    /**
     * Performs the action at the given action_id.
     * 
     * @param action_id Action id of the WindowAction to perform.
     * 
     * @return Returns true if the action could be performed, and false otherwise.  Will also return false if there is no action for the given action_id.
     */
    public boolean performAction(int action_id){
        return this.performAction(action_id,null);
    }
    
    /**
     * Performs the action for the given action_id.
     * 
     * @param action_id Action id of WindowAction to perform.
     * @param data Data to be passed to the WindowAction.
     * 
     * @return Returns true if the action could be performed, and false otherwise.  Will also return false if there is no action for the given action_id.
     */
    public boolean performAction(int action_id,Object data){
        final Map<Integer,WindowAction> window_actions = this.getWindowActions(); 
        
        
        if(!window_actions.containsKey(action_id)){
            return false;
        }
        
        
        return window_actions.get(action_id).performAction(data);
    }
    
    private void __registerWindowAction(int action_id,WindowAction action){
        this.__doRegisterWindowAction(action_id, action,false);
    }
    
    /**
     * Adds the requested action to the action list and allows it to be called at a later point in the window's life. 
     * 
     * @param action_id ID that the action can be called at.
     * @param action Action to be performed.
     * 
     * @return Returns true if the action could be added, and false otherwise.
     */
    public boolean registerWindowAction(int action_id,WindowAction action){
        return this.registerWindowAction(action_id,action,false);
    }
    
    /**
     * Adds the requested action to the action list and allows it to be called at a later point in the window's life. 
     * 
     * @param action_id ID that the action can be called at.
     * @param action Action to be performed.
     * @param overwrite_if_exists If an action already exists at the given ID and this is true, it will overwrite that action in favour of this one.
     * 
     * @return Returns true if the action could be added, and false otherwise.
     */
    public boolean registerWindowAction(int action_id,WindowAction action,boolean overwrite_if_exists){
        final Map<Integer,WindowAction> action_list = this.getWindowActions();
        
        
        if(action_list.containsKey(action_id)&&!overwrite_if_exists){
            return false;
        }else{
            return this.__doRegisterWindowAction(action_id,action,true);
        }
    }
    
    /**
     * Sets the active panel for this window.
     * 
     * @param panel Panel to set as the active panel.
     */
    public void setActiveContent(RMenu menu,RPanel panel){
        this.setActiveMenu(menu);
        this.setActivePanel(panel);
    }
    
    /**
     * Sets the currently active menu.
     * 
     * @param menu Menu to set as active.
     */
    public void setActiveMenu(RMenu menu){
        this.setJMenuBar(menu);
    }
    
    /**
     * Sets the currently active panel.
     * 
     * @param panel Panel to set as active.
     */
    public void setActivePanel(RPanel panel){
        if(this.__current_panel!=null){
            this.__current_panel.onPanelClose();
        }
        this.__current_panel=panel;
        
        this.getContentPane().removeAll();
        this.setContentPane(panel);
        this.validate();
        this.repaint();
    }
    /*End Other Essential Methods*/
}
