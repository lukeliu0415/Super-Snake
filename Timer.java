import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.time.*;
/**
 * Write a description of class Timer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Timer extends Actor
{
    
    LocalTime timer = LocalTime.MIDNIGHT; // Creating an object of type LocalTime which will store the number of HH/MM/SS Passed since
    GreenfootImage textImage;
    
    public Timer() {
        textImage = new GreenfootImage("Timer: 00:00:00", 30, Color.BLACK, Color.WHITE);
        setImage(textImage);
    }

    /**
     * Act - do whatever the Timer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {       
        if(((SnakeWorld) getWorld()).getGameFrames() % 60 == 0) {
            timer = timer.plusSeconds(1);
            textImage = new GreenfootImage("Timer: " + timer, 30, Color.BLACK, Color.WHITE);
            setImage(textImage);
        }
        
    }    
}
