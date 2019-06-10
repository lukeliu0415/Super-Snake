import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Pylon - an obstacle in the world.
 * 
 * @author Maor Gornik, Luke Liu, Qirong Su, Rahim Somjee 
 * @version June 9, 2019
 */
public class Pylon extends Actor
{
    /**
     * Constructor for objects of class Pylon.
     */
    public Pylon() {
        GreenfootImage pylon = getImage();
        pylon.scale(20, 20);
        setImage(pylon); //Set the image of the pylon
    }
}