import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The instructions button in the world.
 * 
 * @author Maor Gornik, Luke Liu, Qirong Su, Rahim Somjee 
 * @version June 9, 2019
 */
public class InstructionsButton extends Button
{
    private GreenfootImage instructionsButton = new GreenfootImage("InstructionsButton.png");
    
    /**
     * Constructor for objects of class InstructionsButton
     */
    public InstructionsButton(){
        instructionsButton.scale(250,90); 
        setImage(instructionsButton); //Set the image of the instructions button
    }
}
