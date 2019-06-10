import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The leaderboard button in the world.
 * 
 * @author Maor Gornik, Luke Liu, Qirong Su, Rahim Somjee 
 * @version June 9, 2019
 */
public class LeaderBoardButton extends Button
{
    private GreenfootImage leaderBoardButton = new GreenfootImage("LeaderboardButton.png");

    /**
     * Constructor for objects of class LeaderBoardButton
     */
    public LeaderBoardButton() {
        leaderBoardButton.scale(250,90); 
        setImage(leaderBoardButton); //Set the image of the leaderboard button
    }
}