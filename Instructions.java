import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Instructions here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Instructions extends Actor
{
    private GreenfootImage instructionsImage = new GreenfootImage("InstructionsPictograph.png");
   
   /**
     * Constructor for instructions button
    */
   public  Instructions(){
       instructionsImage.scale(450,375);
        setImage(instructionsImage);
    }
   /**
     * Act - do whatever the Instructions wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
