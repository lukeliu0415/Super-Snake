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
    private final int FRAMES_TO_LAST;
    private int framesElapsed = 0;
    SnakeWorld currentWorld;

    public SnakeHead() {
        FRAMES_TO_LAST = 10;
        head = new GreenfootImage("snake-head_s01.png");
        head.scale(20, 20);
        setImage(head);
    }

    /**
     * Act - do whatever the SnakeHead wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        snakeMove();
        toTeleport();
        moveTongue();
        collidingFood(10);
        
        if(isTouching(Wall.class)) {
            Greenfoot.stop();
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
     * Adding more tails to the snake when colliding with food
     */
    private void collidingFood(int tailsToAdd) {
        if(isTouching(Food.class)) {
            removeTouching(Food.class); 
            // Appending 1 tail to the snake's body (10 frames = 1 tail)
            SnakeTail.setLifeDuration(SnakeTail.getLifeDuration() + tailsToAdd);
            ((SnakeWorld)getWorld()).increaseScore();
            
            currentWorld = (SnakeWorld)getWorld();
            currentWorld.addFood();
            
        }
    }
    
    /**
     * Changes the snake's head picture every half a second to create an illusion to make it seem
     * as if the snake's tongue is actually moving
     */
    private void moveTongue() {
        if(((SnakeWorld) getWorld()).getGameFrames() % 30 == 0) {
            System.out.println(((SnakeWorld) getWorld()).getGameTime());
            headState++;

            if(headState > 2) headState = 0;

            switch(headState) {
                case 0:   
                directory = "snake-head_s01.png";
                break;

                case 1:
                directory = "snake-head_s02.png";
                break;

                case 2:
                directory = "snake-head_s03.png";
                break;
            }

            head = new GreenfootImage(directory);
            head.scale(20, 20);
            setImage(head);
        }
    }
    
    /**
     * Checking whether the object is touching one of the edges and moving object to the
     * parallel side
     */
    private void toTeleport() {
        // Checking if the object has reached one of the sides of the screen and moving it to the other side
        if(this.getX() == getWorld().getWidth()) {
            this.setLocation(0, this.getY());

            // facing LEFT
        } else if(this.getX() == -1) {
            this.setLocation(getWorld().getWidth()-1, this.getY());

            // UP
        } else if(this.getY() == -1) {
            this.setLocation(this.getX(), getWorld().getHeight()-1);

            // DOWN
        } else if(this.getY() == getWorld().getHeight()) {
            this.setLocation(this.getX(), 0);
            
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
        isColliding(Pylon.class);

        if(Greenfoot.isKeyDown("right") && this.getRotation() != 0 && this.getRotation() != 180) {
            setRotation(0);
        } else if(Greenfoot.isKeyDown("down") && this.getRotation() != 90 && this.getRotation() != 270) {
            setRotation(90);
        } else if(Greenfoot.isKeyDown("left") && this.getRotation() != 0 && this.getRotation() != 180) {
            setRotation(180);
        } else if(Greenfoot.isKeyDown("up") && this.getRotation() != 90 && this.getRotation() != 270) {
            setRotation(270);
        }
    }
}
