import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartButton extends Button
{
    private boolean sliding = false;
    private GreenfootImage title = new GreenfootImage("startButton.png");
    
    /**
     * Method for start button
     */
    public StartButton(){
        title.scale(250,90);
        setImage(title);
    }
    /**
     * Act - do whatever the StartButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
    }    
}
