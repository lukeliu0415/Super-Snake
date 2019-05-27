import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SnakeHead here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SnakeHead extends Actor
{
    // Instance Variables
    private final int FRAMES_TO_LAST = 10;
    private int framesElapsed = 0;
    SnakeWorld currentWorld;

    public SnakeHead() {
        GreenfootImage head = new GreenfootImage(20,20);
        head.setColor(Color.GREEN);
        head.fill();
        setImage(head);
    }

    /**
     * Act - do whatever the SnakeHead wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        snakeMove();
        if(isTouching(Food.class)) {
            removeTouching(Food.class); 
            // Appending 1 tail to the snake's body (10 frames = 1 tail)
            SnakeTail.setLifeDuration(SnakeTail.getLifeDuration() + 10);

            currentWorld = (SnakeWorld)getWorld();
            currentWorld.addFood();
        }
    } 

    /**
     * Stops the game when the snake intersecting with another class object 
     */
    private void isColliding(Class classIn) {
        if(this.isTouching(classIn)) {
            Greenfoot.stop();
        }
    }

    /**
     * Every 10 executions of the act() method, move once
     */
    private void snakeMove() {
        framesElapsed++;
        if(framesElapsed == FRAMES_TO_LAST) {
            getWorld().addObject(new SnakeTail(), getX(), getY());
            move(1);
            framesElapsed = 0;
        }

        isColliding(SnakeTail.class); 

        if(Greenfoot.isKeyDown("right")) {
            setRotation(0);
        } else if(Greenfoot.isKeyDown("down")) {
            setRotation(90);
        } else if(Greenfoot.isKeyDown("left")) {
            setRotation(180);
        } else if(Greenfoot.isKeyDown("up")) {
            setRotation(270);
        }
    }
}
