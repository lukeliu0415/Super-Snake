import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.*;
/**
 * Write a description of class SnakeWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SnakeWorld extends World
{
    private boolean debug;
    private int gameTime;
    private int score;
    private String gameState;// = "start"; //this string will either have a value of start(start screen), begin(initializing world), or running(running the game)
    private GreenfootImage title = new GreenfootImage("snakeGameTitle.jpg");
    private GreenfootSound music = new GreenfootSound("");
    private ArrayList<Integer> scoreList = new ArrayList<Integer>();
    /**
     * Constructor for objects of class SnakeWorld.
     * 
     */
    public SnakeWorld()
    {    
        super(30, 30, 20, false); 
        //System.out.println("1. "+gameState);
        
        title.scale(getWidth()*20, getHeight()*20); //sets the background of the startScreen
        setBackground(title);
        StartButton start = new StartButton();//adds the start button
        addObject(start,15,12);
        InstructionsButton instructions = new InstructionsButton();
        addObject(instructions,15,17);
    
        //System.out.println("2. "+gameState);
    
   
       //System.out.println("this is being run");
    
    
        //System.out.println("3. "+gameState);
        //placeInstructionsLabel();
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
    public void placeInstructionsLabel(){
     InstructionsLabel title = new InstructionsLabel("How to Play", 50);
     addObject(title,15,10);
     InstructionsLabel instructions = new InstructionsLabel("move the snake by using the arrow keys", 25);
     addObject(instructions, 15, 15);
     
    }
    public void changeMusic(String musicSet){
     music.stop();
     GreenfootSound music = new GreenfootSound(musicSet);
     music.play();
        
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
    
    public void increaseScore(int num) {
        score += num;
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

    public boolean addFood() {
        //Get random x and y coordinates to place the food
        int randX = genCoordinates()[0];
        int randY = genCoordinates()[1];

        //Place the food if the desired location does not contain any other objects
        if (getObjectsAt(randX, randY, Actor.class).isEmpty()) {
            int randNum = (int) (Math.random()*5);
            
            if (randNum == 1 || randNum == 2 || randNum == 3 || randNum == 4) {
                Food tempFood  = new Food();
                addObject(tempFood, randX, randY);
            } else if (randNum == 0) {
                Cherry tempCherry = new Cherry();
                addObject(tempCherry, randX, randY);
            }
            //Add the food into the game
            
            return true;
        }

        return false;
    }
    
    public int[] genCoordinates() {
        int[] coordinates = new int[2];
        coordinates[0] = Greenfoot.getRandomNumber(getWidth());
        coordinates[1] = Greenfoot.getRandomNumber(getHeight());
        return coordinates;
    }
    
    public void startWorld(){//this method is supposed to create the grid world after the startbutton is pressed
    
        //GreenfootImage img = new GreenfootImage(20, 20);
        //img.drawRect(0, 0, 20, 20);
        //setBackground(img);
        
        GreenfootImage img = new GreenfootImage("grass.png");
        setBackground(img);
        
        addObject(new SnakeHead(), genCoordinates()[0],
        genCoordinates()[1]);
        List buttonList = (getObjects(Button.class));//list holds all buttons present
        Button tempButton = null;
        for(int i=0;i < buttonList.size(); i++){//removes all buttons
            tempButton = (Button)buttonList.get(i);
            removeObject(tempButton);
        }
        while(!addFood());
        addObject(new ScoreLabel(), 26, 1);
        addObject(new Timer(), 16, 1);
        changeGameState("running");
        //music.play();
    }
    
    public void started() {
        if (gameTime == 0) {
            SnakeTail.reset(this);
        }
    }
    
    public void getScores() throws IOException{
        Scanner k = new Scanner (new File("HighScores.txt"));
        
        while (k.hasNextInt()) {
            scoreList.add(k.nextInt());
        }
        
        k.close(); //Closes the file
    }
    
    public void inputScore(int score) throws IOException{
        PrintWriter o = new PrintWriter(new FileWriter("HighScores.txt", true));
        o.println(score);
        o.close();
    }
    
    public int getHighScore() throws IOException{
        getScores();
        Collections.sort(scoreList);
        System.out.println(scoreList.get(0));
        return scoreList.get(scoreList.size() - 1);
    }
    
    /**
     * Act - do whatever the ParkingLot wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        System.out.println(gameState);
        /*code for clicking the button to change the gameState*/ 
        MouseInfo mouse = Greenfoot.getMouseInfo();//code to check for interactions with the start button
        if (mouse!= null) {//checks if mouse is interacting with anything or something is happening w/ the mouse
            Actor currentActor = mouse.getActor();
            if (currentActor !=null){//checks if an actor is being interacted w/ or not
                if (currentActor.getClass() == StartButton.class){//checks if the actor is a start button
                    StartButton currentButton = (StartButton)currentActor;//converts the actor into a button
                    int mouseButtonPressed = mouse.getButton();
                    int mouseClickCount = mouse.getClickCount();
                    if(debug){System.out.println(mouseButtonPressed +"/"+mouseClickCount);}
                    
                    if (mouseClickCount == 1) {
                     changeGameState("begin");
                     startWorld();
                     if(debug){System.out.println("world should be set up");}
                     //removeObject(getObjectsAt(300,100,Label.class));
                    }
                    
                }
                if(currentActor.getClass() == InstructionsButton.class){
                 InstructionsButton currentButton = (InstructionsButton)currentActor;
                 int mouseButtonPressed = mouse.getButton();
                 int mouseClickCount = mouse.getClickCount();
                 if(debug){System.out.println(mouseButtonPressed +"/"+mouseClickCount);}
                 if (mouseClickCount == 1) {
                     placeInstructionsLabel();
                     List buttonList = (getObjects(Button.class));//list holds all buttons present
                        Button tempButton = null;
                        removeObjects(getObjects(StartButton.class));
                        removeObjects(getObjects(InstructionsButton.class));
                        Button backButton = new BackButton();
                        addObject(backButton, 15, 20);
                        
                     if(debug){System.out.println("instructions being displayed");}
                     
                     
                    }
                }
                if(currentActor.getClass() == BackButton.class){
                    BackButton currentButton = (BackButton)currentActor;
                 int mouseButtonPressed = mouse.getButton();
                 int mouseClickCount = mouse.getClickCount();
                    if (mouseClickCount == 1) {
                        
                        removeObjects(getObjects(BackButton.class));
                         removeObjects(getObjects(InstructionsLabel.class));
                         StartButton start = new StartButton();//adds the start button
                        addObject(start,15,12);
                        InstructionsButton instructions = new InstructionsButton();
                        addObject(instructions,15,17);
                    }
                }
            }
        }
        
        gameTime++;
        
        if (gameTime % 1200 == 0) {
            while (!addPylon());
             //Repeat until successfully placed
        }
    }
}
