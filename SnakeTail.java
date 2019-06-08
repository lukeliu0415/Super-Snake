import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Collections;

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
    SnakeWorld currentWorld;
    ArrayList<Integer> tailList = new ArrayList<>();

    public SnakeTail() {
        GreenfootImage tail = new GreenfootImage("snake-tail.png");
        tail.scale(20, 20);
        setImage(tail);
    }
    
    /**
     * Finding the snake's last tail and changing its image 
     */
    private void changeLastTail() {
        
        
        
        for(SnakeTail tail : getWorld().getObjects(SnakeTail.class)) {
            tailList.add(tail.getTailFrames());
        }

        Collections.sort(tailList);

        //for(int i : tailList) {
        //    System.out.print(i + "\t"); 
        //}
        //System.out.println("");

        for(int i=0;i<getWorld().getObjects(SnakeTail.class).size();i++) {
            if(getWorld().getObjects(SnakeTail.class).get(i).getTailFrames() == tailList.get(tailList.size() - 1)) {
                SnakeTail lastTail = getWorld().getObjects(SnakeTail.class).get(i);

                GreenfootImage tail = new GreenfootImage("snake-tail-end.png");
                tail.scale(20, 20);
                lastTail.setImage(tail);

                // determining the tail's direction and flipping it accordingly
                SnakeTail tempHead = null;

                for(int j=0;j<getWorld().getObjects(SnakeTail.class).size();j++) {
                    if(tailList.size() > 1 && getWorld().getObjects(SnakeTail.class).get(j).getTailFrames() == tailList.get(tailList.size() - 2)) {
                        tempHead = getWorld().getObjects(SnakeTail.class).get(j);
                    }
                }
                        
                if(tempHead != null && tempHead.getRotation() == 0) {
                    lastTail.setRotation(0);
                } else if(tempHead != null && tempHead.getRotation() == 90) {
                    lastTail.setRotation(90);
                } else if(tempHead != null && tempHead.getRotation() == 180) {
                    lastTail.setRotation(180);
                } else if(tempHead != null && tempHead.getRotation() == 270) {
                    lastTail.setRotation(270);
                }
            }
        }

    }

    public static void setLifeDuration(int duration) {
        lifeCycle = duration;
    }

    // need that because we'd be using different fruits that give different
    // bonuses (one fruit may increase the amount of tails the snake has by 1, 2, or even 5)
    public static int getLifeDuration() {
        return lifeCycle;
    }
    
    public int getTailFrames() {
        return framesElapsed;
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
        currentWorld = (SnakeWorld) getWorld();
        // decrease player score by 5
        if (this.isTouching(EnemyHead.class)) {
            currentWorld.decreaseScore(5); 
            removeTouching(EnemyHead.class);
        } 
        framesElapsed++;   
        changeLastTail();

        if(framesElapsed == lifeCycle) {
            getWorld().removeObject(this);
            
            // Removing the tail from the list
            if(currentWorld.getObjects(SnakeTail.class).size() > 0) {
                for(int i=0;i<currentWorld.getObjects(SnakeTail.class).size();i++) {
                    if(currentWorld.getObjects(SnakeTail.class).get(i).equals(this)) {
                        currentWorld.removeObject(this);

                    }
                }

            }
        }
        
        
    }    
}
