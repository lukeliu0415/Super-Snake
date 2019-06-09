import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import javax.swing.*;
import java.io.*;
/**
 * This class is the world where the Super Snake game occurs.
 * 
 * @author Maor Gornik, Luke Liu, Qirong Su, Rahim Somjee 
 * @version June 9, 2019
 */
public class SnakeWorld extends World
{
    private int gameTime;
    private int score;
    private int level;
    private boolean running;
    private int highScore;
    private String bestPlayer;
    public String pName;
    private GreenfootImage title = new GreenfootImage("snakeGameTitle.jpg");
    private GreenfootSound music = new GreenfootSound("jungleGroove.mp3");
    private GreenfootImage instructionsTitle = new GreenfootImage("InstructionsBackground.jpg");
    private ArrayList<Integer> scoreList = new ArrayList<Integer>();
    private ArrayList<String> nameList = new ArrayList<String>();
    StartButton start;
    InstructionsButton instructions;
    BackButton back;
    PlayAgainButton playAgain;
    
    /**
     * Constructor for objects of class SnakeWorld.
     */
    public SnakeWorld() {    
        //Create a 30*30 world with cells that are 20 pixels
        super(30, 30, 20, false);

        //Initialize the buttons
        start = new StartButton();
        instructions = new InstructionsButton();
        back = new BackButton();
        playAgain = new PlayAgainButton();
        
        //Show intro screen
        startScreen();
    }
    
    /**
     * Method for constructing the intro screen.
     */
    public void startScreen() {
        title.scale(getWidth()*20, getHeight()*20);
        setBackground(title); //sets the background of the screen
        addObject(start,15,12);
        addObject(instructions,15,17); //Add buttons onto the screen
    }
    
    /**
     * Method for constructing the playing screen (after start button is pressed).
     */
    public void startWorld() {
        //Set/reset game related variables
        gameTime = 0;
        score = 0;
        level = 0;
        running = true;
        SnakeTail.setLifeDuration(25);
        
        //Set background image and add objects into the world
        GreenfootImage img = new GreenfootImage("grass.png");
        setBackground(img);
        addObject(new SnakeHead(), 3, 5);
        addObject(new ScoreLabel(), 26, 1);
        addObject(new Timer(), 16, 1);
        while(!addFood());
        
        //Play soundtrack
        music.play();
        music.setVolume(15);
    }
    
    /**
     * Method for placing instructions on the instructions screen.
     */
    public void placeInstructionsLabel(){
        
        Label title = new Label("How to Play", 50);
        addObject(title,15,10);
        
        Instructions instructionsImage = new Instructions();
        addObject(instructionsImage, 15, 20);
        instructionsTitle.scale(getWidth()*20, getHeight()*20);
        setBackground(instructionsTitle);
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

    public void changeMusic(String musicSet){
        music.stop();
        GreenfootSound music = new GreenfootSound(musicSet);
        music.play();
    }
    
    /**
     * Getter method for the score
     * 
     * @return the user's score
     */
    public int getScore() {
        return score;
    }
    
    public String getName() {
        return pName;
    }
    
    public void increaseScore(int num) {
        score += num;
    }
    
    public void decreaseScore(int num) {
        score -= num;
    }
    
    public boolean addEnemy() {
        int rand = (int) (Math.random() * 4);
        int eY = genCoordinates()[1];
        int eX = genCoordinates()[0];
        EnemyHead eHead = new EnemyHead();
        
        if (rand == 0) {
            if (getObjectsAt(1, eY, null).isEmpty()) {
                addObject(eHead, 1, eY);
                eHead.setRotation(0);
                return true;
            }
        } else if (rand == 1) {
            if (getObjectsAt(28, eY, null).isEmpty()) {
                addObject(eHead, 28, eY);
                eHead.setRotation(180);
                return true;
            }
        } else if (rand == 2) {
            if (getObjectsAt(eX, 28, null).isEmpty()) {
                addObject(eHead, eX, 28);
                eHead.setRotation(270);
                return true;
            }
        } else if (rand == 3) {
            if (getObjectsAt(eX, 1, null).isEmpty()) {
                addObject(eHead, eX, 1);
                eHead.setRotation(90);
                return true;
            }
        }
        return false;
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

    public void endWorld() throws IOException{
        GreenfootImage gameOverImage = new GreenfootImage("gameOver.jpg");
        gameOverImage.scale(getWidth()*20, getHeight()*20); //sets the background of the startScreen
        setBackground(gameOverImage);
        inputScore(score);
            
        removeObjects(getObjects(null));

        getScores();
        Label text = new Label(pName + "'s Score: " + score + "   High Score: " + highScore + " (" + bestPlayer + ")", 30);
        addObject(text, 15, 8);
        addObject(playAgain, 24, 13);
        running = false;
        music.stop();
    }
    
    public void started() {
        if (gameTime == 0) {
            SnakeTail.reset(this);
        } else {
            music.play();
            music.setVolume(15);
        }
    }
    
    public void stopped() {
        music.pause();
    }
    
    public void getScores() throws IOException{
        Scanner k = new Scanner (new File("HighScores.txt"));
        while (k.hasNextLine()) {
            String temp[] = k.nextLine().split(" ");
            int sc = Integer.parseInt(temp[0]);
            String name = temp[1];
            scoreList.add(sc);
            nameList.add(name);
        }
        
        for (int i = 1; i < scoreList.size(); i++) {
            for (int j = i; j > 0 && scoreList.get(j - 1) <= scoreList.get(j); j--) {
                Collections.swap(scoreList, j, j - 1);
                Collections.swap(nameList, j, j - 1);
            }
        }
        
        bestPlayer = nameList.get(0);
        highScore = scoreList.get(0);
        
        k.close(); //Closes the file
    }
    
    public void inputScore(int score) throws IOException{
        PrintWriter o = new PrintWriter(new FileWriter("HighScores.txt", true));
        o.println(score + " " + pName);
        o.close();
    }
    
    public void displayLevel() {
        LevelLabel showLevel = new LevelLabel("Leveled Up! Level: " + level, 30);
        addObject(showLevel, 8, 4);
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
                pName = Greenfoot.ask("Enter your name: (max 6 characters)");
                
                while (pName.length() > 6) {
                    pName = Greenfoot.ask("Max 6 characters!");
                }
                
                removeObject(start);
                removeObject(instructions);
                startWorld();
            } else if (mouse.getActor() == instructions) {
                placeInstructionsLabel();
                
                removeObject(start);
                removeObject(instructions);
        
                addObject(back, 5, 27);
            } else if (mouse.getActor() == back) {
                removeObject(back);
                removeObjects(getObjects(Label.class));
                removeObjects(getObjects(Instructions.class));
                setBackground(title);
                addObject(start,15,12);
                addObject(instructions,15,17);
            } else if (mouse.getActor() == playAgain) {
                removeObject(playAgain);
                removeObjects(getObjects(Label.class));
                startScreen();
            }
        }
        
        switch (level) {
            case 0: if (score >= 10) {
                        level = 1;
                    }
                    break;
                    
            case 1: removeObjects(getObjects(Pylon.class));
                    removeObjects(getObjects(Food.class));
                    removeObjects(getObjects(Cherry.class));
                    
                    for (int i = 0; i <= 30; i=i+5) {
                        addObject(new Wall(), i, 15);
                    }
                    displayLevel();
                    level = 11;
                    while (!addFood());
                    
            case 11:if (score >= 20) {
                        level = 2;
                    }
                    break;
                    
            case 2: removeObjects(getObjects(Wall.class));
                    removeObjects(getObjects(Pylon.class));
                    removeObjects(getObjects(Food.class));
                    removeObjects(getObjects(Cherry.class));
                    
                    for (int i = 0; i <= 30; i=i+5) {
                        addObject(new Wall(), i, 0);
                        addObject(new Wall(), i, 10);
                        addObject(new Wall(), i, 20);
                        addObject(new Wall(), i, 30);
                    }
                    displayLevel();
                    level = 12;
                    while (!addFood());
                    
            case 12:if (score >= 30) {
                        level = 3;
                    }
                    break;        
                    
            case 3: removeObjects(getObjects(Wall.class));
                    removeObjects(getObjects(Pylon.class));
                    removeObjects(getObjects(Food.class));
                    removeObjects(getObjects(Cherry.class));
                    
                    for (int i = 0; i <= 30; i=i+5) {
                        addObject(new Wall(), i, i);
                    }
                    displayLevel();
                    level = 13;
                    while (!addFood());
                    
            case 13:if (score >= 40) {
                        level = 4;
                    }
                    break; 
                    
            case 4: removeObjects(getObjects(Wall.class));
                    removeObjects(getObjects(Pylon.class));
                    removeObjects(getObjects(Food.class));
                    removeObjects(getObjects(Cherry.class));
                    
                    for (int i = 0; i <= 30; i=i+5) {
                        addObject(new Wall(), i, i);
                        addObject(new Wall(), i, 30-i);
                    }
                    displayLevel();
                    level = 14;
                    while (!addFood());
            
            case 14:if (score >= 50) {
                        level = 5;
                    }
                    break;         
                    
            case 5: removeObjects(getObjects(Wall.class));
                    removeObjects(getObjects(Pylon.class));
                    removeObjects(getObjects(Food.class));
                    removeObjects(getObjects(Cherry.class));
                    
                    for (int j = 0; j <= 30; j=j+10) {
                        for (int i = 0; i <= 30; i=i+5) {
                            addObject(new Wall(), i, j);
                            addObject(new Wall(), i, 30-i);
                            addObject(new Wall(), i, i);
                            addObject(new Wall(), j, i);
                        }
                    }
                    displayLevel();
                    level = 15;
                    while (!addFood());
            
            case 15:if (score >= 60) {
                        level = 6;
                    }
                    break;
                    
            case 6: removeObjects(getObjects(Wall.class));
                    removeObjects(getObjects(Pylon.class));
                    removeObjects(getObjects(Food.class));
                    removeObjects(getObjects(Cherry.class));
                    
                    for (int i = 0; i <= 30; i=i+5) {
                        addObject(new Wall(), i, 15);
                        addObject(new Wall(), 15, i);
                        addObject(new Wall(), i, 30-i);
                        addObject(new Wall(), i, i);
                    }
                    displayLevel();
                    level = 16;
                    while (!addFood());
            
            case 16:if (score >= 70) {
                        level = 7;
                    }
                    break;        
                    
            case 7: removeObjects(getObjects(Wall.class));
                    removeObjects(getObjects(Pylon.class));
                    removeObjects(getObjects(Food.class));
                    removeObjects(getObjects(Cherry.class));
                    
                    for (int j = 0; j <= 30; j=j+5) {
                        for (int i = 0; i <= 30; i++) {
                            addObject(new Wall(), i, j);
                            if ((i == 5) || (i == 10) || (i == 15) || (i == 20) || (i == 25) || (i == 30)) {
                                removeObjects(getObjectsAt(i, j, Wall.class));
                            }
                        }
                    }
                    displayLevel();
                    level = 17;
                    while (!addFood());
            
            case 17: //
        }
        
        if (running) {
            gameTime++;
            //Pylon pops up every 10 seconds
            if (gameTime % 600 == 0) {
                while (!addPylon());
                //Repeat until successfully placed
            }
            if (gameTime % 300 == 0) { // enemy every 30 seconods
                while (!addEnemy());
            }
        }
    }
}
