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
    private boolean afterFirstRun = false;
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
    public void updateImage(){
        GreenfootImage img = new GreenfootImage(text, fontSize, textColor, bkColor);
        setImage(img);
        
    }
    public void setText(String textSet){
        text = textSet;
        updateImage();
    }
    public void setFontSize(int fontSizeSet){
        fontSize = fontSizeSet;
        updateImage();
    }
    public void setTextColor(Color colorSet){
        textColor = colorSet;
        updateImage();
    }
    public void firstRunOver(){
        afterFirstRun = true;
    }
    public boolean checkFirstRun(){
        return afterFirstRun;
    }
    public void act() 
    {
        // Add your action code here.
    }    
}
