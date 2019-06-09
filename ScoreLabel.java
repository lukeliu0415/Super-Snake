import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ScoreLabel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreLabel extends Label
{
    /**
     * Constructor for score label
     */
    public ScoreLabel(){
        super("Score: 0", 30);
    }
    
    /**
     * Act - do whatever the ScoreLabel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // set currentworld 
        SnakeWorld currentWorld = (SnakeWorld) getWorld();
        
        //Update and display the current score
        setText("Score: " + currentWorld.getScore());
    } 
}
