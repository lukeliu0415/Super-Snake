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
    
    public LevelLabel(String text, int fontSizeSet){
        super(text,fontSizeSet);
    }
    
    /**
     * Act - do whatever the LevelLabel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        time++;
        
        if (time == 180) {
            getWorld().removeObject(this);
        }
    }    
}
