import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The cherry in the world.
 * 
 * @author Maor Gornik, Luke Liu, Qirong Su, Rahim Somjee 
 * @version June 9, 2019
 */
public class Cherry extends Actor
{
    /**
     * Constructor for objects of class cherry.
     */
    public Cherry() {
        GreenfootImage cherry = getImage();
        cherry.scale(20, 20);
        setImage(cherry); //Set the image of cherry
    } 
}