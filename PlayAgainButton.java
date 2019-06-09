import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayAgainButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayAgainButton extends Button
{
    private GreenfootImage playAgainButton = new GreenfootImage("restartButton.png");
    
    /**
     * Method for play again button 
     */
    public PlayAgainButton(){
        playAgainButton.scale(200,75);
        setImage(playAgainButton);
    }
    
    /**
     * Act - do whatever the PlayAgainButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
