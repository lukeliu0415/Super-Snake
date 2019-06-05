import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ScoreLabel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreLabel extends Label
{
    private boolean afterFirstRun;
    public ScoreLabel(){//constructor for Score labels
        super("Score: 0", 30);
    }
    
    /**
     * Act - do whatever the ScoreLabel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        SnakeWorld currentWorld = (SnakeWorld) getWorld();
        int nowScore = currentWorld.getScore();
        
        //Update and display the current score
        setText("Score: " + currentWorld.getScore());
        
        if (nowScore == 1) {
            afterFirstRun = true;
            Label info = new Label("",0);//used to store information
            info.firstRunOver();
            if (getWorld() instanceof Level1) {
                Greenfoot.setWorld(new Level2());
            } else if (getWorld() instanceof Level2) {
                Greenfoot.setWorld(new Level3());
            } else if (getWorld() instanceof Level3) {
                Greenfoot.setWorld(new Level4());
            } else if (getWorld() instanceof Level4) {
                Greenfoot.setWorld(new Level5());
            } else if (getWorld() instanceof Level5) {
                Greenfoot.setWorld(new Level6());
            } else if (getWorld() instanceof Level6) {
                Greenfoot.setWorld(new Level7());
            } else if (getWorld() instanceof SnakeWorld) {
                Greenfoot.setWorld(new Level1());
            }
        }
        
    } 
}
