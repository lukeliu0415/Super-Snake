import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LeaderBoardButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LeaderBoardButton extends Button
{
    private GreenfootImage leaderBoardButton = new GreenfootImage("LeaderboardButton.png");

    /**
     * Constructor for LeaderBoardButton
     */
    public LeaderBoardButton() {
        leaderBoardButton.scale(250,90); 
        setImage(leaderBoardButton);
    }

    /**
     * Act - do whatever the LeaderBoardButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
