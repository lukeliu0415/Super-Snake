import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level7 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level7 extends SnakeWorld
{

    /**
     * Constructor for objects of class Level7.
     * 
     */
    public Level7()
    {
        super();
        this.startWorld();
        //set walls
        setWalls();
       
    }
    
    private void setWalls() {
        
        for (int j = 0; j <= 30; j=j+5) {
            // use all previous patterns
            for (int i = 0; i <= 30; i++) {
                addObject(new Wall(), i, j);
                
                if ((i == 5) || (i == 10) || (i == 15) || (i == 20) || (i == 25) || (i == 30)) {
                    removeObjects(getObjectsAt(i, j, Wall.class));
                }
                
            }
        }
    
        
    }
}
