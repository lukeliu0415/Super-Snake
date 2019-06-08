import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;

/**
 * Write a description of class SnakeHead here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SnakeHead extends Actor
{
    // Instance Variables
    private int headState;
    private String directory;
    private int framesElapsed = 0;
    private final int FRAMES_TO_LAST = 10;
    private GreenfootImage head;
    private SnakeWorld currentWorld;
    private int lastRotation = 0;
    private boolean gameOver;

    private GreenfootSound munch = new GreenfootSound("Munch.mp3");
    public SnakeHead() {
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
        if(!gameOver) {
            toTeleport();
            moveTongue();
            collidingFood();
            try {
                snakeMove();
            }
            catch(Exception E) {
            }

        } else {
            getWorld().removeObjects(getWorld().getObjects(Wall.class));
            getWorld().removeObjects(getWorld().getObjects(SnakeTail.class));
            getWorld().removeObjects(getWorld().getObjects(Food.class));
            getWorld().removeObjects(getWorld().getObjects(Cherry.class));
            getWorld().removeObjects(getWorld().getObjects(Pylon.class));
            getWorld().removeObjects(getWorld().getObjects(SnakeHead.class));
            
            Greenfoot.stop();
        }
    }

    /**
     * Stops the game when the snake intersecting with another class object 
     */
    private void isColliding(Class classIn) throws IOException{
        currentWorld = (SnakeWorld) getWorld();
        if ((this.isTouching(classIn)) || (currentWorld.getScore() < 0) ){
            GreenfootImage gameOverImage = new GreenfootImage("gameOver.jpg");
            gameOverImage.scale(this.getWorld().getWidth()*20, this.getWorld().getHeight()*20); //sets the background of the startScreen
            this.getWorld().setBackground(gameOverImage);
            currentWorld.inputScore(currentWorld.getScore());
            

            Label text = new Label("Your score is: " + currentWorld.getScore() + "   High Score: " + currentWorld.getHighScore(), 30);
            currentWorld.addObject(text, 15, 8);
            gameOver = true;
        }
        if (this.isTouching(EnemyHead.class)) {
            removeTouching(EnemyHead.class); 
            currentWorld.increaseScore(5);
        }
    }
    
    

    /**
     * Adding more tails to the snake when colliding with food
     */
    private void collidingFood() {
        currentWorld = (SnakeWorld) getWorld();
        if(isTouching(Food.class)) {
            removeTouching(Food.class); 
            // Appending 1 tail to the snake's body (10 frames = 1 tail)
            SnakeTail.setLifeDuration(SnakeTail.getLifeDuration() + 10);
            currentWorld.increaseScore(1);
            munchSound();
            //Fixed bug so that every time food gets added
            while (!currentWorld.addFood());
        } else if (isTouching(Cherry.class)) {
            removeTouching(Cherry.class);
            SnakeTail.setLifeDuration(SnakeTail.getLifeDuration() + 20);
            currentWorld.increaseScore(2);
            munchSound();
            while (!currentWorld.addFood());
        }
    }

    /**
     * Changes the snake's head picture every half a second to create an illusion to make it seem
     * as if the snake's tongue is actually moving
     */
    private void moveTongue() {
        if(((SnakeWorld) getWorld()).getGameFrames() % 30 == 0) {
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
        if (!gameOver){
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
    }

    /**
     * Every 10 executions of the act() method, move once
     */
    private void snakeMove() throws IOException{
        framesElapsed++;

        if(framesElapsed == FRAMES_TO_LAST) {
            SnakeTail temp = new SnakeTail();
            getWorld().addObject(temp, getX(), getY());
            temp.setRotation(lastRotation);
            move(1);
            framesElapsed = 0;
            lastRotation = getRotation();

        }
        isColliding(SnakeTail.class);
        isColliding(Pylon.class);
        isColliding(Wall.class);
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
    private void munchSound(){
        munch.play();
        munch.setVolume(15);
    }
}
