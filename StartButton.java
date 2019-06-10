import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The start button in the world.
 * 
 * @author Maor Gornik, Luke Liu, Qirong Su, Rahim Somjee 
 * @version June 9, 2019
 */
public class StartButton extends Button
{
    private GreenfootImage title = new GreenfootImage("startButton.png");
    
    /**
     * Constructor for objects of class StartButton
     */
    public StartButton(){
        title.scale(300,105);
        setImage(title); //Set the image of the start button
    }  
}
