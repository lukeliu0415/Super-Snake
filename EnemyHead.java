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
    SnakeWorld currentWorld = (SnakeWorld) getWorld();

    int eX;
    int eY;
    
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
        // Add your action code here.
       this.setRotation(0);
       
       if(((SnakeWorld) getWorld()).getGameFrames() % 20 == 0) {
           this.move(1);
       }

       if (this.isTouching(null)) {
           removeTouching(EnemyHead.class);
       }
    }    
}
