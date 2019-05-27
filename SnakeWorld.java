import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SnakeWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SnakeWorld extends World
{
    
    /**
     * Constructor for objects of class SnakeWorld.
     * 
     */
    public SnakeWorld()
    {    
        super(35, 30, 20); 
        GreenfootImage img = new GreenfootImage(20, 20);
        img.drawRect(0, 0, 20, 20);
        setBackground(img);
        
        addObject(new SnakeHead(), genCoordinates()[0],
        genCoordinates()[1]);

        addFood();

    }

    public void addFood() {
        addObject(new Food(), genCoordinates()[0],
        genCoordinates()[1]);
    }
    
    public int[] genCoordinates() {
        int[] coordinates = new int[2];
        coordinates[0] = Greenfoot.getRandomNumber(getWidth());
        coordinates[1] = Greenfoot.getRandomNumber(getHeight());
        return coordinates;
    }
}
