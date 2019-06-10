import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;

/**
 * This class is in charge of adding the snake's head.
 * 
 * @author Maor Gornik, Luke Liu, Qirong Su, Rahim Somjee 
 * @version June 9, 2019
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
    private GreenfootSound munch = new GreenfootSound("Munch.mp3");

    /**
     * Constructor for objects of class SnakeHead.
     */
    public SnakeHead() {
        head = new GreenfootImage("snake/head.png");
        head.scale(20, 20);
        setImage(head);
    }

    /**
     * Ends the game when the snake is intersecting with another object 
     */
    private void isColliding() throws IOException{
        currentWorld = (SnakeWorld) getWorld();

        if ((isTouching(SnakeTail.class)) || isTouching(Pylon.class) ||
        isTouching(Wall.class) || currentWorld.getScore() < 0){
            currentWorld.endWorld();
        }
    }

    /**
     * Adding more tails to the snake when colliding with food
     */
    public void collidingFood() {
        // Getting the world object
        currentWorld = (SnakeWorld) getWorld();

        // If the snake is colliding with apples
        if(isTouching(Food.class)) {

            // Removing the apple from the world
            removeTouching(Food.class);

            // Appending one tail to the snake's body
            SnakeTail.setLifeDuration(SnakeTail.getLifeDuration() + 10);

            // Increasing the player score by one
            currentWorld.increaseScore(1);

            // Playing eating sound
            munchSound();

            // Adding more apples to the world
            while (!currentWorld.addFood());

            // If the snake is colliding with cherrys
        } else if (isTouching(Cherry.class)) {

            // Removing the cherry from the world
            removeTouching(Cherry.class);

            // Appending two tail to the snake's body
            SnakeTail.setLifeDuration(SnakeTail.getLifeDuration() + 20);

            // Increasing the player score by two
            currentWorld.increaseScore(2);

            // Playing eating sound
            munchSound();

            // Adding more cherrys to the world
            while (!currentWorld.addFood());
            
            // If the snake is colliding with an enemy snake
        } else if (isTouching(EnemyHead.class)) {

            // Removing the enemy snake from the world
            removeTouching(EnemyHead.class);

            // Appending five tail to the snake's body
            SnakeTail.setLifeDuration(SnakeTail.getLifeDuration() + 50);

            // Increasing the player score by five
            currentWorld.increaseScore(5);

            // Playing eating sound
            munchSound();
        } 
    }

    /**
     * Changes the snake's head picture every half a second to make it seem
     * as if the snake's tongue is actually moving
     */
    private void moveTongue() {
        if(((SnakeWorld) getWorld()).getGameFrames() % 30 == 0) {

            // Moving to the next state of the snake's head
            headState++;

            // Restarting the counter every 3 iterations through the pictures
            if(headState > 2) headState = 0;

            // Checking what is the current state of the snake's head and choosing
            // to display the appropriate picture
            switch(headState) {
                case 0:   
                directory = "snake/head.png";
                break;

                case 1:
                directory = "snake/head-s2.png";
                break;

                case 2:
                directory = "snake/head-s3.png";
                break;
            }

            // Setting the new image of the snake head and displaying it
            head = new GreenfootImage(directory);
            setImage(head);
        }
    }

    /**
     * Checking whether the snake is touching one of the edges and moving the snake to the
     * parallel side
     */
    private void toTeleport() {

        // Checking if the object has reached one of the sides of the screen and moving it to the other side
        if(this.getX() == getWorld().getWidth()) {
            this.setLocation(0, this.getY());

            // If snake is facing the left corner teleporting it to the opposite direction 
        } else if(this.getX() == -1) {
            this.setLocation(getWorld().getWidth()-1, this.getY());

            // If snake is facing the upper corner teleporting it to the opposite direction 
        } else if(this.getY() == -1) {
            this.setLocation(this.getX(), getWorld().getHeight()-1);

            // If snake is facing the bottom corner teleporting it to the opposite direction 
        } else if(this.getY() == getWorld().getHeight()) {
            this.setLocation(this.getX(), 0);
        }
    }

    /**
     * Method in charge of playing the sound
     */
    public void munchSound(){
        munch.play();
        munch.setVolume(15);
    }

    /**
     * Ensuring the snake is continually moving and is correctly responding to the 
     * player's desired direction of movement
     */
    private void snakeMove(){

        // Incrementing the number of elapsed frames since the head was added to the world
        framesElapsed++;

        // Every 10 executions of the act method moving the snake, adding a tail behind it,
        // rotating it to match the head's rotation and resetting the number of elapsed frames.
        if(framesElapsed == FRAMES_TO_LAST) {

            // Adding a tail behind the snake's head
            SnakeTail temp = new SnakeTail();
            getWorld().addObject(temp, getX(), getY());

            // Rotating the added tail to match the head's rotation
            temp.setRotation(lastRotation);
            lastRotation = getRotation();

            // Keep moving the snake and resetting the number of elapsed frames
            framesElapsed = 0;
            move(1);
        }

        // Ensuring the snake is correctly responding to the player's desired direction of movement
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

    /**
     * Act - do whatever the SnakeHead wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Getting the world's object
        currentWorld = (SnakeWorld) getWorld();

        // Running required methods for the snake animation to take place
        moveTongue();
        snakeMove();

        // Tracking the snake's behaviour
        toTeleport();
        collidingFood();

        // Trying to check whether the snake collides with any obstacles or with itself
        try {isColliding();}
        catch(Exception e) {System.out.println("Game was unable to write or read from file.\n" +
                "Please ensure the file exists and try again.");}
    }
}
