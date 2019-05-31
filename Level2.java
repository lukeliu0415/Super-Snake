import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level2 extends SnakeWorld
{

    /**
     * Constructor for objects of class Level2.
     * 
     */
    public Level2()
    {
        super();
        
        //set walls
        setWalls();
       
    }
    
    private void setWalls() {
        
        // add walls along the mid row incremented hirztontally by 5 and verticalls by 10
        for (int i = 0; i <= 30; i=i+5) {
            addObject(new Wall(), i, 0);
            addObject(new Wall(), i, 10);
            addObject(new Wall(), i, 20);
            addObject(new Wall(), i, 30);
            
        }
    
    }
}
