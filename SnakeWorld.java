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
    private int level;
    EnemyHead eHead;
    private String gameState;// = "start"; //this string will either have a value of start(start screen), begin(initializing world), or running(running the game)
    private GreenfootImage title = new GreenfootImage("snakeGameTitle.jpg");
    private GreenfootSound music = new GreenfootSound("jungleGroove.mp3");
    private ArrayList<Integer> scoreList = new ArrayList<Integer>();
    StartButton start;
    InstructionsButton instructions;
    BackButton back;
    /**
     * Constructor for objects of class SnakeWorld.
     * 
     */
    public SnakeWorld()
    {    
        super(30, 30, 20, false); 
        
        title.scale(getWidth()*20, getHeight()*20); //sets the background of the startScreen
        setBackground(title);
        start = new StartButton();//adds the start button
        instructions = new InstructionsButton();
        back = new BackButton();
        
        addObject(start,15,12);
        addObject(instructions,15,17);
    
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
        InstructionsLabel instructions = new InstructionsLabel("Move the snake by using the arrow keys", 25);
        InstructionsLabel instructions1 = new InstructionsLabel("and try to survive as long as possible", 25);
        InstructionsLabel instructions2 = new InstructionsLabel("Death will occur if:", 25);
        InstructionsLabel instructions3 = new InstructionsLabel("You crash into yourself", 25);
        InstructionsLabel instructions4 = new InstructionsLabel("You hit a pylon", 25);
        InstructionsLabel instructions5 = new InstructionsLabel("You bump into a wall", 25);
        
      
        addObject(instructions, 15, 15);
        addObject (instructions1, 15, 16);
        addObject (instructions2, 15, 17);
        addObject (instructions3, 15, 18);
        addObject (instructions4, 15, 19);
        addObject (instructions5, 15, 20);
                
     
    }
    public void changeMusic(String musicSet){
        music.stop();
        GreenfootSound music = new GreenfootSound(musicSet);
        music.play();
        
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
    
    public void addEnemy() {
        eHead = new EnemyHead(); 
        addObject(eHead, 1, 15);  
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
    
    public void startWorld() {//this method is supposed to create the grid world after the startbutton is pressed
        
        GreenfootImage img = new GreenfootImage("grass.png");
        setBackground(img);
        
        removeObjects(getObjects(Button.class));
        addObject(new SnakeHead(), 3, 5);
        addObject(new ScoreLabel(), 26, 1);
        addObject(new Timer(), 16, 1);
        
        while(!addFood());
        
        changeGameState("running");
        music.play();
        music.setVolume(15);
    }
    
    public void started() {
        if (gameTime == 0) {
            SnakeTail.reset(this);
        }
        music.play();
        music.setVolume(15);
    }
    
    public void stopped() {
        music.pause();
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
        return scoreList.get(scoreList.size() - 1);
    }
    
    /**
     * Act - do whatever the ParkingLot wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        /*code for clicking the button to change the gameState*/ 
        MouseInfo mouse = Greenfoot.getMouseInfo();//code to check for interactions with the start button
        
        if (mouse != null && mouse.getClickCount() == 1) {
            if (mouse.getActor() == start) {
                changeGameState("begin");
                startWorld();
            } else if (mouse.getActor() == instructions) {
                placeInstructionsLabel();
                
                removeObject(start);
                removeObject(instructions);
        

                addObject(back, 15, 25);
            } else if (mouse.getActor() == back) {
                
                removeObject(back);
                removeObjects(getObjects(InstructionsLabel.class));
                addObject(start,15,12);
                addObject(instructions,15,17);
            }
        }
        
        

        switch (level) {
            case 0: if (score >= 10) {
                        level = 1;
                    }
                    break;
                    
            case 1: removeObjects(getObjects(Pylon.class));
                    for (int i = 0; i <= 30; i=i+5) {
                        addObject(new Wall(), i, 15);
                    }
                    level = 11;
                    
            case 11:if (score >= 15) {
                        level = 2;
                    }
                    break;
                    
            case 2: removeObjects(getObjects(Wall.class));
                    removeObjects(getObjects(Pylon.class));
                    
                    for (int i = 0; i <= 30; i=i+5) {
                        addObject(new Wall(), i, 0);
                        addObject(new Wall(), i, 10);
                        addObject(new Wall(), i, 20);
                        addObject(new Wall(), i, 30);
                    }
                    level = 12;
                    
            case 12:if (score >= 20) {
                        level = 3;
                    }
                    break;        
                    
            case 3: removeObjects(getObjects(Wall.class));
                    removeObjects(getObjects(Pylon.class));
                    
                    for (int i = 0; i <= 30; i=i+5) {
                        addObject(new Wall(), i, i);
                    }
                    level = 13;
                    
            case 13:if (score >= 25) {
                        level = 4;
                    }
                    break; 
                    
            case 4: removeObjects(getObjects(Wall.class));
                    removeObjects(getObjects(Pylon.class));
                    
                    for (int i = 0; i <= 30; i=i+5) {
                        addObject(new Wall(), i, i);
                        addObject(new Wall(), i, 30-i);
                    }
                    level = 14;
            
            case 14:if (score >= 30) {
                        level = 5;
                    }
                    break;         
                    
            case 5: removeObjects(getObjects(Wall.class));
                    removeObjects(getObjects(Pylon.class));
                    
                    for (int j = 0; j <= 30; j=j+10) {
                        for (int i = 0; i <= 30; i=i+5) {
                            addObject(new Wall(), i, j);
                            addObject(new Wall(), i, 30-i);
                            addObject(new Wall(), i, i);
                            addObject(new Wall(), j, i);
                        }
                    }
                    level = 15;
            
            case 15:if (score >= 35) {
                        level = 6;
                    }
                    break;
                    
            case 6: removeObjects(getObjects(Wall.class));
                    removeObjects(getObjects(Pylon.class));
                    for (int i = 0; i <= 30; i=i+5) {
                        addObject(new Wall(), i, 15);
                        addObject(new Wall(), 15, i);
                        addObject(new Wall(), i, 30-i);
                        addObject(new Wall(), i, i);
                    }
                    level = 16;
            
            case 16:if (score >= 40) {
                        level = 7;
                    }
                    break;        
                    
            case 7: removeObjects(getObjects(Wall.class));
                    removeObjects(getObjects(Pylon.class));
                    
                    for (int j = 0; j <= 30; j=j+5) {
                        for (int i = 0; i <= 30; i++) {
                            addObject(new Wall(), i, j);
                            if ((i == 5) || (i == 10) || (i == 15) || (i == 20) || (i == 25) || (i == 30)) {
                                removeObjects(getObjectsAt(i, j, Wall.class));
                            }
                        }
                    }
                    break;
        }
        

        
        gameTime++;
        //Pylon pops up every 10 seconds
        if (gameTime % 600 == 0) {
            while (!addPylon());
             //Repeat until successfully placed
        }
        if (gameTime % 12000 == 0) {
           addEnemy();
        }
    }
}
