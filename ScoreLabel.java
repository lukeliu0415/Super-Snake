import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ScoreLabel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreLabel extends Label
{
    private int score;
    
    public ScoreLabel(){//constructor for Score labels
        super("Score: 0", 30);
        score = 0;
    }
    
    
    /**
     * Act - do whatever the ScoreLabel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        SnakeWorld currentWorld = (SnakeWorld) getWorld();
        int nowScore = currentWorld.getScore();
        
        //Update and display the current score
        setText("Score: " + currentWorld.getScore());
        
        if (nowScore == 10) {
            Greenfoot.setWorld( new Level1());
        }
    } 
}
