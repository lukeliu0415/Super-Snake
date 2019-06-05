import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level6 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level6 extends SnakeWorld
{

    /**
     * Constructor for objects of class Level6.
     * 
     */
    public Level6()
    {
        super();
        this.startWorld();  
        //set walls
        setWalls();
       
    }
    
    private void setWalls() {
        
        // use all previous patterns
        for (int i = 0; i <= 30; i=i+5) {
            addObject(new Wall(), i, 15);
            addObject(new Wall(), 15, i);
            addObject(new Wall(), i, 30-i);
            addObject(new Wall(), i, i);
               
        }
    
    }
}
