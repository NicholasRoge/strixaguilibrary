package roge.gui;

import java.awt.Container;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Wrapper for the JFrame class to make it a little bit more convenient to use.
 * 
 * @author Nicholas Rog�
 */
public abstract class RWindow extends JFrame implements WindowListener{
    private static final long serialVersionUID = 6715060991592690674L;
    /**String to be returned in the event that this window has not had its title set yet.*/
    public static final String NO_TITLE="Untitled Window";
    
    private String __window_title=null;
    
    
    /*Begin Initializer Methods*/
    protected void _initialize(){       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.addWindowListener(this);
        
        SwingUtilities.invokeLater(new Runnable(){
           @Override public void run(){
               RWindow.this._addMenu(RWindow.this);
               RWindow.this._addContent(RWindow.this);
           }
        });
    }
    
    protected void _initialize(String title){
        this.setWindowTitle(title);
        
        this._initialize();
    }
    
    protected void _addContent(final JFrame frame){
        JPanel content=new JPanel();
        
        
        this._addContent(content);
        this.setContentPane(content);
    }
    
    protected abstract void _addContent(final JPanel content);
    
    protected void _addMenu(final JFrame frame){
    }
    /*End Initializer Methods*/
    
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
        this._initialize(title);
    }
    /*End Constructors*/
    
    /*Begin Overridden Methods*/
    @Override public void windowClosing(WindowEvent event) {
    }

    @Override public void windowOpened(WindowEvent event) {
    }

    @Override public void windowClosed(WindowEvent event) {
    }
    
    @Override public void windowIconified(WindowEvent event) {
    }
    
    @Override public void windowDeiconified(WindowEvent event) {
    }
    
    @Override public void windowActivated(WindowEvent event) {
    }
    
    @Override public void windowDeactivated(WindowEvent event) {
    }
    /*End Overridden Methods*/
    
    /*Begin Getter Methods*/
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
    /*End Getter Methods
    
    /*Begin Setter Method*/
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
    /*End Setter Methods*/
    
    /*Begin Other Essential Methods*/
    /**
     * Changes the current content of the window.
     * 
     * @param content Content to change the window to.
     * @param pack_after_change If set to <code>true</code> the window will be packed after it's content is changed out.
     */
    public void changeContentPane(Container content,boolean pack_after_change){
        this.getContentPane().removeAll();
        this.setContentPane(content);
        this.validate();
        this.repaint();
        
        if(pack_after_change){
            this.pack();
        }
    }
    /*End Other Essential Methods*/
}
