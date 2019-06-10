import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class is in charge of adding enemy snakes to the world and it serves as an additional 
 * obstacle for the snake to overcome.
 * 
 * @author Maor Gornik, Luke Liu, Qirong Su, Rahim Somjee 
 * @version June 9, 2019
 */
public class EnemyHead extends SnakeHead
{
    // Instance Variables
    private GreenfootImage eHead;
    SnakeWorld currentWorld;

    /**
     * Constructor for EnemyHead
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
        // Getting the world's object
        currentWorld = (SnakeWorld) getWorld();

        // Slowing the enemy snake's speed
        if(currentWorld.getGameFrames() % 20 == 0) {
            move(1);
        }

        // Removing the enemy snake when collided with snake
        if (isTouching(SnakeHead.class)) {

            // Adding five tails to the snake
            SnakeTail.setLifeDuration(SnakeTail.getLifeDuration() + 50);

            // Increasing the player's score by 5
            currentWorld.increaseScore(5);

            // Playing eating sound
            munchSound();

            // Removing the enemy snake
            currentWorld.removeObject(this);

            // If the enemy snake collides with one of the snake's tails
        } else if (isTouching(SnakeTail.class)) {

            // Taking away five points from the player
            currentWorld.decreaseScore(5);

            // Taking away five tails from the snake
            SnakeTail.setLifeDuration(SnakeTail.getLifeDuration() - 50);

            // Removing the enemy snake
            currentWorld.removeObject(this);
        }
    }    
}
