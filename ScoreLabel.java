import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ScoreLabel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreLabel extends Label
{
    private int score;
    public ScoreLabel(String setScore,int fontSizeSet){//constructor for Scorelabels
        super("0",30);
        score = 0;
        
    }
    public void addScore(int add){
     score = score + add;
     
    }
    /**
     * Act - do whatever the ScoreLabel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
