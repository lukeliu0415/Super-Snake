import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.time.*;
/**
 * Write a description of class Timer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Timer extends Label
{
    
    LocalTime timer = LocalTime.MIDNIGHT;
    // Creating an object of type LocalTime which will store the number of HH/MM/SS Passed since
    
    /**
     * Constructor for timer
     */
    public Timer() {
        super("Timer: 00:00:00", 30);
    }

    /**
     * Act - do whatever the Timer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {       
        if(((SnakeWorld) getWorld()).getGameFrames() % 60 == 0) {
            timer = timer.plusSeconds(1);
            setText("Timer: " + timer);
        }
    }    
}
