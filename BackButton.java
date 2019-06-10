import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The back button in the world.
 * 
 * @author Maor Gornik, Luke Liu, Qirong Su, Rahim Somjee 
 * @version June 9, 2019
 */
public class BackButton extends Button
{
    private GreenfootImage backButton = new GreenfootImage("backButton.png");
    
    /**
     * Constructor for objects of class BackButton
     */
    public BackButton(){
        backButton.scale(175,75);
        setImage(backButton); //Set the image of the back button
    }   
}
