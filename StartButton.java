import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartButton extends Button
{
    private boolean sliding = false;
    private GreenfootImage title = new GreenfootImage("startButton.png");
    public StartButton(){
        title.scale(250,200);
        setImage(title);
    }
    public void removeButton(){
     this.getWorld().removeObject(this);   
    }
    public void startSlideOff(){
        sliding = true;
    }
    /**
     * Act - do whatever the StartButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (sliding){
            this.setLocation(this.getX() + 1, this.getY());
        }
        if (Greenfoot.mouseClicked(this)) {
            //this.getWorld().changeGameState("run");
            this.removeButton();
            
        }
    }    
}
