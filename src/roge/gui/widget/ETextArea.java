package roge.gui.widget;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JTextArea;

/**
 * Extends the functionality of the JTextArea class.
 * 
 * @author Nicholas Rogé
 */
public class ETextArea extends JTextArea{
    private static final long serialVersionUID = 2674799969639586397L;

    /**
     * Provides an interface for objects which would like to receive updates whenever this object's text is updated.
     * 
     * @author Nicholas Rogé
     */
    public static interface TextUpdateListener{
        /**
         * Called whenever the JTextArea's text is updated.
         * 
         * @param text The JTextArea's text after the update.
         */
        public void recieveTextUpdate(String text);
    }
    
    private ArrayList<TextUpdateListener> __text_update_listeners=null;
    
    
    /*Begin Constructors*/
    /**
     * Constructs the object with no text.
     */
    public ETextArea(){
        this("");
    }
    
    /**
     * Constructs the object with the given default text.
     * 
     * @param default_text Text that will be displayed within this object on the initial display (TODO:  and whenever the JTextArea becomes blank.).
     */
    public ETextArea(String default_text){
        super(default_text);
    }
    /*End Constructors*/
    
    
    /*Begin Overridden Methods*/
    @Override public void setText(String text){
        super.setText(text);
        
        this._broadcastTextToListeners(text);
    }
    
    @Override public void processKeyEvent(KeyEvent event){
        super.processKeyEvent(event);
        
        if(event.getID()==KeyEvent.KEY_LAST){
            this._broadcastTextToListeners(this.getText());
        }
    }
    /*End Overridden Methods*/
    
    
    /*Begin Getter Methods*/
    /**
     * Returns the list of objects that would like to receive updates from this object whenever the text in this object updates.
     * 
     * @return The list of objects that would like to receive updates from this object whenever the text in this object updates.
     */
    protected ArrayList<TextUpdateListener> _getTextUpdateListeners(){
        if(this.__text_update_listeners==null){
            this.__text_update_listeners=new ArrayList<TextUpdateListener>();
        }
        
        return this.__text_update_listeners;
    }
    /*End Getter Methods*/
    
    
    /*Begin Other Essential Methods*/
    /**
     * Adds a listener to be called when the text in this object changes.
     * 
     * @param listener Object to be called when the text in this object changes.
     */
    public void addTextUpdateListener(TextUpdateListener listener){
        this._getTextUpdateListeners().add(listener);
    }
    
    /**
     * Sends the broadcast text to any object who are registered to receive it.
     * 
     * @param broadcast String to be broadcasted to the object registered to receive it.
     */
    protected void _broadcastTextToListeners(String broadcast){
        for(TextUpdateListener listener:this._getTextUpdateListeners()){
            listener.recieveTextUpdate(broadcast);
        }
    }
    /*End Other Essential Methods*/
}
