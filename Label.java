import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The parent class of all the labels in the world.
 * 
 * @author Maor Gornik, Luke Liu, Qirong Su, Rahim Somjee 
 * @version June 9, 2019
 */
public class Label extends Actor
{
    private String text;
    private int fontSize;
    private Color textColor;
    private Color bkColor; //background colour
    
    /**
     * Constructor for objects of class Label.
     * 
     * @param setText text on the label
     * @param fontSizeSet font size of the text on the label
     */
    public Label(String setText,int fontSizeSet){
        text = setText;
        fontSize = fontSizeSet;
        textColor = Color.BLACK;
        bkColor = new Color(0,0,0,0); //transparent background
        
        //Set the image of the label according to the specified criteria
        GreenfootImage label = new GreenfootImage(text, fontSize, textColor, bkColor);
        updateImage();
    }
    
    /**
     * Method to update the image
     */
    public void updateImage(){
        GreenfootImage img = new GreenfootImage(text, fontSize, textColor, bkColor);
        setImage(img); //Set the label image
    }
    
    /**
     * Setter method that sets the label text
     * 
     * @param textSet text on the label
     */
    public void setText(String textSet){
        text = textSet;
        updateImage();
    }
    
    /**
     * Setter method for the font of the text
     * 
     * @param fontSizeSet size of the font
     */
    public void setFontSize(int fontSizeSet){
        fontSize = fontSizeSet;
        updateImage();
    }
    
    /**
     * Setter method for the colour of the text
     * 
     * @param colorSet color of the text
     */
    public void setTextColor(Color colorSet){
        textColor = colorSet;
        updateImage();
    } 
}
