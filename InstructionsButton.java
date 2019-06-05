import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InstructionsButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InstructionsButton extends Button
{
    private GreenfootImage instructionsButton = new GreenfootImage("InstructionsButton.png");
    public InstructionsButton(){
        instructionsButton.scale(200,75);
        setImage(instructionsButton);
    }
        public void removeButton(){
     this.getWorld().removeObject(this);   
    }
    /**
     * Act - do whatever the InstructionsButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
