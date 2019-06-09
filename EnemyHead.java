import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyHead here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyHead extends SnakeHead
{
    // set variables 
    private GreenfootImage eHead;
    SnakeWorld currentWorld;
    
    /**
     * Constructor for enemy head
     */
    public EnemyHead() {
        eHead = new GreenfootImage("enemy-head_s01.png");
        eHead.scale(20, 20);
        setImage(eHead);
    }
    
    /**
     * Act - How the enemy snake behaves around the screen
     * 
    **/
    public void act() 
    {
        // set current world
        currentWorld = (SnakeWorld) getWorld();
        
        // slow the snake speed
        if(currentWorld.getGameFrames() % 20 == 0) {
            move(1);
        }
        
        // remove itself when collided with snake
        if (isTouching(SnakeHead.class)) {
            currentWorld.increaseScore(5);
            currentWorld.removeObject(this);
            munchSound();
        } else if (isTouching(SnakeTail.class)) {
            currentWorld.decreaseScore(5);
            currentWorld.removeObject(this);
        }
    }    
}
