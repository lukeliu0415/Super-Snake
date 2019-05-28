import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SnakeTail here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SnakeTail extends Actor
{
    private int framesElapsed = 0;
    // Each 10 frames represent one more tail (frames)
    private static int lifeCycle = 25;

    public SnakeTail() {
        GreenfootImage tail = new GreenfootImage(20,20);
        tail.fill();
        setImage(tail);
    }

    public static void setLifeDuration(int duration) {
        lifeCycle = duration;
    }

    // need that because we'd be using different fruits that give different
    // bonuses (one fruit may increase the amount of tails the snake has by 1, 2, or even 5)
    public static int getLifeDuration() {
        return lifeCycle;
    }

    /**
     * Resets the game
     */
    public static void reset(World currentWorld) {
        lifeCycle = 25;
        currentWorld.removeObjects(currentWorld.getObjects(SnakeTail.class));
    }
    
    /**
     * Act - do whatever the SnakeTail wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        framesElapsed++;   

        if(framesElapsed == lifeCycle) {
            getWorld().removeObject(this);
        }
    }    
}
