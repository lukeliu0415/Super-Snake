import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The apple in the world.
 * 
 * @author Maor Gornik, Luke Liu, Qirong Su, Rahim Somjee 
 * @version June 9, 2019
 */
public class Food extends Actor
{
    /**
     * Constructor for objects of class Food.
     */
    public Food() {
        GreenfootImage food = getImage();
        food.scale(20, 20);
        setImage(food); //Set the image of apple
    }
}
