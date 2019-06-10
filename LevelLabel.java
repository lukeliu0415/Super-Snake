import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Level label in the world.
 * 
 * @author Maor Gornik, Luke Liu, Qirong Su, Rahim Somjee 
 * @version June 9, 2019
 */
public class LevelLabel extends Label
{
    private int time = 0; //Timer to track the amount of time the label has been in the world
    
    /**
     * Constructor for objects of class LevelLabel.
     * 
     * @param text text on the label
     * @param fontSizeSet font size
     */
    public LevelLabel(String text, int fontSizeSet){
        //Call the parent constructor
        super(text,fontSizeSet);
    }
    
    /**
     * Act - do whatever the LevelLabel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //The label removes itself after 3 seconds
        time++;
        if (time == 180) {
            getWorld().removeObject(this);
        }
    }    
}
