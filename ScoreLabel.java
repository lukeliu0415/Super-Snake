import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Score Label in the world.
 * 
 * @author Maor Gornik, Luke Liu, Qirong Su, Rahim Somjee 
 * @version June 9, 2019
 */
public class ScoreLabel extends Label
{
    /**
     * Constructor for objects of class ScoreLabel.
     */
    public ScoreLabel() {
        //Call the parent constructor
        super("Score: 0", 30);
    }
    
    /**
     * Act - do whatever the ScoreLabel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //Get currentworld 
        SnakeWorld currentWorld = (SnakeWorld) getWorld();
        
        //Update and display the current score
        setText("Score: " + currentWorld.getScore());
    } 
}
