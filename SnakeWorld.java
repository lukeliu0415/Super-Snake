import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
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
    private GreenfootImage title = new GreenfootImage("snakeGameTitle.jpg");
    private GreenfootSound music = new GreenfootSound("jungleGroove.mp3");
    private GreenfootImage instructionsTitle = new GreenfootImage("InstructionsBackground.jpg");
    private ArrayList<Integer> scoreList = new ArrayList<Integer>();
    StartButton start;
    InstructionsButton instructions;
    BackButton back;
    PlayAgainButton playAgain;
    
    /**
     * Constructor for objects of class SnakeWorld.
     */
    public SnakeWorld()
    {    
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
     * Method that constructs the intro screen.
     */
    public void startScreen() {
        title.scale(getWidth()*20, getHeight()*20);
        setBackground(title); //sets the background of the screen
        addObject(start,15,12);
        addObject(instructions,15,17); //Add buttons onto the screen
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

    public void placeInstructionsLabel(){
        InstructionsLabel title = new InstructionsLabel("How to Play", 50);
        addObject(title,15,10);
        InstructionsLabel instructions = new InstructionsLabel("Move the snake by using the arrow keys", 25);
        InstructionsLabel instructions1 = new InstructionsLabel("and try to survive as long as possible", 25);
        InstructionsLabel instructions2 = new InstructionsLabel("Death will occur if:", 25);
        InstructionsLabel instructions3 = new InstructionsLabel("You crash into yourself", 25);
        InstructionsLabel instructions4 = new InstructionsLabel("You hit a pylon", 25);
        InstructionsLabel instructions5 = new InstructionsLabel("You bump into a wall", 25);
        InstructionsLabel instructions6 = new InstructionsLabel("Eating Snakes, cherries, and apples will reward you points", 25);
        InstructionsLabel instructions7 = new InstructionsLabel("Bumping into the enemy snake will cost you points", 25);
        InstructionsLabel instructions8 = new InstructionsLabel("If your score goes below 0, then you will lose", 25);
      
        /*addObject (instructions, 15, 15);
        addObject (instructions1, 15, 16);
        addObject (instructions2, 15, 17);
        addObject (instructions3, 15, 18);
        addObject (instructions4, 15, 19);
        addObject (instructions5, 15, 20);
        addObject (instructions6, 15, 21);
        addObject (instructions7, 15, 22);
        addObject (instructions8, 15, 23);*/
        Instructions instructionsImage = new Instructions();
        addObject(instructionsImage, 15, 20);
        instructionsTitle.scale(getWidth()*20, getHeight()*20); //sets the background of the startScreen
        setBackground(instructionsTitle);
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
    
    public void increaseScore(int num) {
        score += num;
    }
    
    public void decreaseScore(int num) {
        score -= num;
    }
    
    public boolean addEnemy() {
        int eY = genCoordinates()[1];
        EnemyHead eHead = new EnemyHead(); 
        if (getObjectsAt(1, eY, null).isEmpty()) {
            addObject(eHead, 1, eY);
            return true;
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
    
    public void startWorld() {//this method is supposed to create the grid world after the startbutton is pressed
        
        gameTime = 0;
        score = 0;
        level = 0;
        running = true;
        SnakeTail.setLifeDuration(25);
        
        GreenfootImage img = new GreenfootImage("grass.png");
        setBackground(img);
        
        addObject(new SnakeHead(), 3, 5);
        addObject(new ScoreLabel(), 26, 1);
        addObject(new Timer(), 16, 1);
        
        while(!addFood());
        
        music.play();
        music.setVolume(15);
    }
    
    public void endWorld() throws IOException{
        GreenfootImage gameOverImage = new GreenfootImage("gameOver.jpg");
        gameOverImage.scale(getWidth()*20, getHeight()*20); //sets the background of the startScreen
        setBackground(gameOverImage);
        inputScore(score);
            
        removeObjects(getObjects(Wall.class));
        removeObjects(getObjects(SnakeTail.class));
        removeObjects(getObjects(Food.class));
        removeObjects(getObjects(Cherry.class));
        removeObjects(getObjects(Pylon.class));
        removeObjects(getObjects(LevelLabel.class));
        removeObjects(getObjects(Timer.class));
        removeObjects(getObjects(ScoreLabel.class));
        removeObjects(getObjects(SnakeHead.class));

        Label text = new Label("Your score is: " + score + "   High Score: " + getHighScore(), 30);
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
                removeObjects(getObjects(InstructionsLabel.class));
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
            if (gameTime % 1800 == 0) { // enemy every 30 seconods
                while (!addEnemy());
            }
        }
    }
}
