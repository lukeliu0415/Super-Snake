import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.time.*;

/**
 * Timer in the world.
 * 
 * @author Maor Gornik, Luke Liu, Qirong Su, Rahim Somjee 
 * @version June 9, 2019
 */
public class Timer extends Label
{
    LocalTime timer = LocalTime.MIDNIGHT;
    //Create a LocalTime object which stores HH/MM/SS passed
    
    /**
     * Constructor for objects of class Timer.
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
        //Update the timer after every second passed
        if(((SnakeWorld) getWorld()).getGameFrames() % 60 == 0) {
            timer = timer.plusSeconds(1);
            setText("Timer: " + timer);
        }
    }    
}
