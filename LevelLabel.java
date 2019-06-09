import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LevelLabel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LevelLabel extends Label
{
    private int time = 0;
    
    /**
     * Constructor for Level label
     * 
     * @param text to set the label with
     * @param font size for the text
     */
    public LevelLabel(String text, int fontSizeSet){
        super(text,fontSizeSet);
    }
    
    /**
     * Act - do whatever the LevelLabel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // time to keep the label alive (about 3 seconds)
        time++;
        if (time == 180) {
            getWorld().removeObject(this);
        }
    }    
}
