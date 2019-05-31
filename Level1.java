import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level1 extends SnakeWorld
{

    /**
     * Constructor for objects of class Level1.
     * 
     */
    public Level1()
    {
        super();
        
        //set walls
        setWalls();
       
    }
    
    private void setWalls() {
        
        // add walls along the mid row incremented hirztontally by 5
        for (int i = 0; i <= 30; i=i+5) {
            addObject(new Wall(), i, 15);
        }
    
    }
}
