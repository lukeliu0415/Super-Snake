import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class Label here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Label extends Actor
{
    private String text;
    private int fontSize;
    private Color textColor;
    private Color bkColor;
    /**
     * Act - do whatever the Label wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Label(String setText,int fontSizeSet){//label constructor
        super();
        text = setText;
        fontSize = fontSizeSet;
        textColor = Color.BLACK;
        bkColor = new Color(0,0,0,0);//transparent background
        
        GreenfootImage label = new GreenfootImage(text, fontSize, textColor, bkColor);
        
        updateImage();
    }
    /**
     * Method to update the image
     */
    public void updateImage(){
        GreenfootImage img = new GreenfootImage(text, fontSize, textColor, bkColor);
        setImage(img);
        
    }
    /**
     * Setter method that sets the label text
     * 
     * @param text to set the label with
     */
    public void setText(String textSet){
        text = textSet;
        updateImage();
    }
    /**
     * Setter method for the font of the text
     * 
     * @param size to set the font
     */
    public void setFontSize(int fontSizeSet){
        fontSize = fontSizeSet;
        updateImage();
    }
    /**
     * Setter method for the colour of the text
     * 
     * @param color to set the text
     */
    public void setTextColor(Color colorSet){
        textColor = colorSet;
        updateImage();
    }
    public void act() 
    {
        // Add your action code here.
    }    
}
