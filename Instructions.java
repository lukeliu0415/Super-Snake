import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Instructions Pictograph.
 * 
 * @author Maor Gornik, Luke Liu, Qirong Su, Rahim Somjee 
 * @version June 9, 2019
 */
public class Instructions extends Actor
{
    private GreenfootImage instructionsImage = new GreenfootImage("InstructionsPictograph.png");
   
   /**
     * Constructor for objects of class Instructions.
     */
   public  Instructions(){
       instructionsImage.scale(450,375);
       setImage(instructionsImage); //Set the image of instructions pictograph.
   }
}
