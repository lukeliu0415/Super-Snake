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
        toTeleport();
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
     * Checking whether the object is touching one of the edges and moving object to the
     * parallel side
     */
    private void toTeleport() {
        System.out.println(getWorld().getWidth());
        // Checking if the object has reached one of the sides of the screen and moving it to the other side
        if(this.getX() == getWorld().getWidth() - 1 && this.getRotation() == 0) {
            Greenfoot.delay(10);
            collidingFood(10);
            //getWorld().removeObjects(getWorld().getObjects(SnakeTail.class));
            this.setLocation(0, this.getY());
            

            // facing LEFT
        } else if(this.getX() == 0 && this.getRotation() == 180) {
            Greenfoot.delay(10);
            collidingFood(10);
            //getWorld().removeObjects(getWorld().getObjects(SnakeTail.class));
            this.setLocation(getWorld().getWidth(), this.getY());

            // UP
        } else if(this.getY() == 0 && this.getRotation() == 270) {
            Greenfoot.delay(10);
            collidingFood(10);
            //getWorld().removeObjects(getWorld().getObjects(SnakeTail.class));
            this.setLocation(this.getX(), getWorld().getHeight());

            // DOWN
        } else if(this.getY() == getWorld().getHeight() - 1 && this.getRotation() == 90) {
            Greenfoot.delay(10);
            collidingFood(10);
            //getWorld().removeObjects(getWorld().getObjects(SnakeTail.class));
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
