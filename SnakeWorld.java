import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SnakeWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SnakeWorld extends World
{
    private int gameTime;
    private int score = 0;
    private String gameState = "start";
    private GreenfootImage title = new GreenfootImage("snakeGameTitle.jpg");
    /**
     * Constructor for objects of class SnakeWorld.
     * 
     */
    public SnakeWorld()
    {    
        super(35, 30, 20); 
        /*title.scale(getWidth()*20, getHeight()*20); //sets the background of the startScreen
        setBackground(title);
        StartButton start = new StartButton();//adds the start button
        addObject(start,17,15);*/
        GreenfootImage img = new GreenfootImage(20, 20);
        img.drawRect(0, 0, 20, 20);
        setBackground(img);
        addObject(new SnakeHead(), genCoordinates()[0],
        genCoordinates()[1]);

        addFood();
        addObject(new Score(), 26, 1);

    }
    
    /**
     * Getter method for the game frames
     * 
     * @return the number of frames that have elapsed since the
     * beginning of the game
     */
    public int getGameFrames() {
        return gameTime;
    }
    public String getGameState(){
     return gameState;   
    }
    public void changeGameState(String gameStateSet){
        gameState = gameStateSet;
    }
    
    /**
     * Getter method for the game's seconds 
     * 
     * @return the number of seconds that have elapsed since the
     * beginning of the game
     */
    public int getGameTime() {
        return gameTime / 60;
    }
    
    /**
     * Getter method for the score
     * 
     * @return the user's score (# of coins collected)
     */
    public int getScore() {
        return score;
    }
    
    public void increaseScore() {
        score++;
    }
    
    public boolean addPylon()
    {
        //Get random x and y coordinates to place the pylon
        int randX = genCoordinates()[0];
        int randY = genCoordinates()[1];

        //Place the pylon if the desired location does not contain any other objects
        if (getObjectsAt(randX, randY, Actor.class).isEmpty()) {
            //Add the pylon into the game
            Pylon tempPylon  = new Pylon();
            addObject(tempPylon, randX, randY);
            
            return true;
        }
        return false;
    }

    public void addFood() {
        addObject(new Food(), genCoordinates()[0],
        genCoordinates()[1]);
    }
    
    public int[] genCoordinates() {
        int[] coordinates = new int[2];
        coordinates[0] = Greenfoot.getRandomNumber(getWidth());
        coordinates[1] = Greenfoot.getRandomNumber(getHeight());
        return coordinates;
    }
    
    /**
     * Resets the game is reset
     */
    public void started() {
        SnakeTail.reset((SnakeWorld)this);
    }
    public void startWorld(){//this method is supposed to create the grid world after the startbutton is pressed
        GreenfootImage img = new GreenfootImage(20, 20);
        img.drawRect(0, 0, 20, 20);
        setBackground(img);
        changeGameState("run");
    }
    
    /**
     * Act - do whatever the ParkingLot wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        gameTime++;
        
        if (gameTime % 1200 == 0) {
            while (!addPylon()) {
            } //Repeat until successfully placed
        }
    }
}
