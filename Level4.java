import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level4 extends SnakeWorld
{

    /**
     * Constructor for objects of class Level4.
     * 
     */
    public Level4()
    {
        super();
        
        //set walls
        setWalls();
       
    }
    
    private void setWalls() {
        
        // add walls along both diagonals
        for (int i = 0; i <= 30; i=i+5) {
            addObject(new Wall(), i, i);
            addObject(new Wall(), i, 30-i);

            
        }
    
    }
}
