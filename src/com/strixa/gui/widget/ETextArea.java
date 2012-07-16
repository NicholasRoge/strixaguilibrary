package com.strixa.gui.widget;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JTextArea;

/**
 * Extends the functionality of the JTextArea class.
 * 
 * @author Nicholas Rogé
 */
public class ETextArea extends JTextArea{
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
    
    /*Begin Final Variables*/
    private static final long serialVersionUID = 2674799969639586397L;
    
    /**Used with {@link #setMaximumAllowedCharacters(int)} to allow the user to input any number of characters.  */
    public static final int INFINITE=-1;
    /*End Final Variables*/
    
    private ArrayList<TextUpdateListener> __text_update_listeners=null;
    private int __maximum_characters;
    
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
        
        this.setMaximumAllowedCharacters(ETextArea.INFINITE);
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
        	if(this.getMaximumAllowedCharacters()!=ETextArea.INFINITE){
        		if(this.getText().length()>this.getMaximumAllowedCharacters()){
        			this.setText(this.getText().substring(0,this.getMaximumAllowedCharacters()));
        			
        			return;
        		}
        	}
        	
            this._broadcastTextToListeners(this.getText());
        }
    }
    /*End Overridden Methods*/
    
    
    /*Begin Getter/Setter Methods*/
    /**
     * Gets the maximum number of characters that can be input into this text area.
     * 
     * @return The maximum number of characters that can be input into this text area.
     */
    public int getMaximumAllowedCharacters(){
    	return this.__maximum_characters;
    }
    
    /**
     * Sets the maximum number of characters that can be input into this text area.
     * 
     * @param maximum Maximum number of characters allowed.
     */
    public void setMaximumAllowedCharacters(int maximum){
    	if(maximum<0&&maximum!=ETextArea.INFINITE){
    		throw new IllegalArgumentException("Argument 'maximum' must be >= 0.");
    	}
    	
    	this.__maximum_characters=maximum;
    }
    
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
    /*End Getter/Setter Methods*/
    
    
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
