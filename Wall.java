import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Wall - an obstacle in the world.
 * 
 * @author Maor Gornik, Luke Liu, Qirong Su, Rahim Somjee 
 * @version June 9, 2019
 */
public class Wall extends Actor
{
    /**
     * Constructor for objects of class Wall.
     */
    public Wall() {
        GreenfootImage wall = getImage();
        wall.scale(20, 20);
        setImage(wall); //Set the image of the wall
    }
}