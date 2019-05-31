import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Score here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Score extends Actor
{
    public Score(){
        //Update and display the current score
        GreenfootImage textImage = new GreenfootImage("Score: 0", 30, Color.BLACK, Color.WHITE);
        setImage(textImage);
    }
    
    /**
     * Act - do whatever the Score wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        SnakeWorld currentWorld = (SnakeWorld) getWorld();
        int nowScore = currentWorld.getScore();
        //Update and display the current score
        GreenfootImage textImage = new GreenfootImage("Score: " + ((SnakeWorld) getWorld()).getScore(), 30, Color.BLACK, Color.WHITE);
        setImage(textImage);
        
        if (nowScore == 10) {
            Greenfoot.setWorld( new Level1());
        }
    }    
}
