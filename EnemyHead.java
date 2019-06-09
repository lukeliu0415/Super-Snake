import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyHead here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyHead extends SnakeHead
{
    private GreenfootImage eHead;
    SnakeWorld currentWorld;
    
    public EnemyHead() {
        eHead = new GreenfootImage("enemy-head_s01.png");
        eHead.scale(20, 20);
        setImage(eHead);
    }
    
    /**
     * Act - do whatever the EnemyHead wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        currentWorld = (SnakeWorld) getWorld();
        
        if(currentWorld.getGameFrames() % 20 == 0) {
            move(1);
        }
        
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
