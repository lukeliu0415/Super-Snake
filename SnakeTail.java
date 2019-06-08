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
    private static int lifeCycle;
	private boolean canBeSwitched;
    SnakeWorld currentWorld;
	ArrayList<SnakeTail> tailTurning = new ArrayList<>();
    ArrayList<Integer> tailList = new ArrayList<>();
    SnakeTail lastTail;
	
	/**
     * A constructor for the snake's tails
     */
    public SnakeTail() {
        GreenfootImage tail = new GreenfootImage("snake-tail.png");
        tail.scale(21, 21);
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

        for(int i=0;i<getWorld().getObjects(SnakeTail.class).size();i++) {
            if(getWorld().getObjects(SnakeTail.class).get(i).getTailFrames() == tailList.get(tailList.size() - 1)) {
                lastTail = getWorld().getObjects(SnakeTail.class).get(i);

                GreenfootImage tail = new GreenfootImage("snake/tail.png");
                tail.scale(21, 21);
                lastTail.setImage(tail);

                // Determining the tail's direction and flipping it accordingly
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
     * Sorting the snake's tails based on the number of frames they have
     * been alive
     */
    private void addInOrder() {
        tailTurning.addAll(getWorld().getObjects(SnakeTail.class));
        // Sorting tail based on the number of frames they have 'lived'
        for (int i = 1; i < tailTurning.size(); i++) {
            for (int j = i; j > 0 && tailTurning.get(j - 1).getTailFrames() >
            tailTurning.get(j).getTailFrames(); j--) {
                Collections.swap(tailTurning, j, j - 1);
            }
        }
    }
	
	/**
     * Determining whether the snake is turning
     * @return true if the snake is turning, false if not.
     */
    private boolean isSnakeTurning() {
        addInOrder();

        // Finding whether the rotation of the head of the snake and its
        // tails differ
        for(int i = 0; i<tailTurning.size();i++) {
            SnakeHead tempHead = getWorld().getObjects(SnakeHead.class).get(0);

            if(tempHead.getRotation() != tailTurning.get(i).getRotation()) {
                return true;
            }
        }
        return false;
    }
	
	/**
     * Changing the selected snake's body part and changing its pictures
     * to reflect the turn the snake is taking
     * 
     * @twistedPart The snake's turning body part
     * @tempHead The snake's head object
     */
    private void twistImage(SnakeTail twistedPart, SnakeHead tempHead) {
        // Initializing an image to hold the snake's turning body part image
        GreenfootImage tempImg = twistedPart.getImage();

        //System.out.println(tempHead.getRotation() - twistedPart.getRotation());

        // Calculating the difference in rotation between the snake's head and its
        // turning body part
        int rotationDiff = tempHead.getRotation() - twistedPart.getRotation();

        // Determining in what direction the snake is headed
        if(rotationDiff == -90 || rotationDiff == 270) {
            tempImg = new GreenfootImage("snake/body-twist-flipped.png");
        } else {
            tempImg = new GreenfootImage("snake/body-twist.png");
        }

        // Updating the image
        twistedPart.setImage(tempImg);
    }
	
	/**
     * Changing the connecting body part of the snake to reflect the
     * turn it is taking
     */
    private void twistBody() {
        // Retrieving a sorted list containing all the snake's tails based on the
        // amount of frames they have been alive for
        addInOrder();

        // Getting the snake's head object
        SnakeHead tempHead = getWorld().getObjects(SnakeHead.class).get(0);

        // Looping through all the snake tails
        for(int i = 0; i<tailTurning.size(); i++) {
            // Finding whether the first snake's body part doesn't have the same
            // rotation as the head

            // then sing the puzzle pattern, passing
            // the previous tail to the top which creates an illusion of turning

            // Stopping at the first instance
            if(i == 0) {
                // Checking whether its rotation is the same as the tail
                if(tempHead.getRotation() != tailTurning.get(i).getRotation()) {
                    // Storing the first instance in a variable
                    SnakeTail tempTail = tailTurning.get(i);

                    // Checking whether the head and the turning body part are not aligned over the Y axis and are
                    // going either left or right
                    if(tempHead.getRotation() == 0 || tempHead.getRotation() == 180) {
                        if(tempHead.getX() != tempTail.getX()) {
                            // The turning object's image can be changed 
                            canBeSwitched = true;
                        }

                        // Checking whether the head and the turning body part are not aligned over the X axis and are
                        // going either up or down
                    } else {
                        if(tempHead.getY() != tempTail.getY()) {
                            // The turning object's image can be changed 
                            canBeSwitched = true;
                        } 
                    }

                    // If the snake's body part and its head are not aligned vertically or horizonally
                    // changing the connecting body part image to the turning image
                    if(canBeSwitched) {
                        // Changing the turning object's image
                        twistImage(tempTail, tempHead); 
                        canBeSwitched = false;
                    }
                }
            } 
        }
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
        if(isSnakeTurning()) twistBody();

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
