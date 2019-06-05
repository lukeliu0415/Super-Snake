import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level5 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level5 extends SnakeWorld
{

    /**
     * Constructor for objects of class Level5.
     * 
     */
    public Level5()
    {
        super();
        this.startWorld();
        //set walls
        setWalls();
       
    }
    
    private void setWalls() {
        
        for (int j = 0; j <= 30; j=j+10) {
            // use all previous patterns
            for (int i = 0; i <= 30; i=i+5) {
                addObject(new Wall(), i, j);
                addObject(new Wall(), i, 30-i);
                addObject(new Wall(), i, i);
                addObject(new Wall(), j, i);
                
            }
        }
    
    }
}
