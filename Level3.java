import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level3 extends SnakeWorld
{

    /**
     * Constructor for objects of class Level3.
     * 
     */
    public Level3()
    {
        super();
        this.startWorld();
        //set walls
        setWalls();
       
    }
    
    private void setWalls() {
        
        // add walls diagonally 
        for (int i = 0; i <= 30; i=i+5) {
            addObject(new Wall(), i, i);
            
        }
    
    }
}
