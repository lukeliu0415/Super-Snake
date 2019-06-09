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
    LeaderBoardButton leaderBoard;
    
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
        leaderBoard = new LeaderBoardButton();
        
        //Show intro screen
        startScreen();
    }
    
    /**
     * Method for constructing the intro screen.
     */
    public void startScreen() {
        title.scale(getWidth()*20, getHeight()*20);
        setBackground(title); //sets the background of the screen
        addObject(start, 15, 12);
        addObject(instructions, 15, 17);
        addObject(leaderBoard, 25, 17); //Add buttons onto the screen
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
     * Method for constructing the game over screen after the game ends.
     */
    public void endWorld() throws IOException{
        GreenfootImage gameOverImage = new GreenfootImage("gameOver.jpg");
        gameOverImage.scale(getWidth()*20, getHeight()*20);
        setBackground(gameOverImage); //sets the background of the screen
        removeObjects(getObjects(null)); //Remove all objects from the world
        inputScore(score); //Input this play's score into the document
        getScores(); //Get all scores from the document
        
        //Display this play's score, top score and top player
        Label text = new Label(pName + "'s Score: " + score + "   High Score: "
        + scoreList.get(0) + " (" + nameList.get(0) + ")", 30);
        addObject(text, 15, 8);
        
        addObject(playAgain, 24, 13); //Add play again button
        running = false;
        music.stop(); //Stop the music
    }
    
    /**
     * Method for placing objects on the instructions screen.
     */
    public void placeInstructionsLabel(){
        //Set the background and place the image that shows instructions
        Instructions instructionsImage = new Instructions();
        addObject(instructionsImage, 15, 20);
        instructionsTitle.scale(getWidth()*20, getHeight()*20);
        setBackground(instructionsTitle);
    }
    
    /**
     * Method for placing objects on the leader board screen.
     */
    public void placeLeaderBoardLabel() throws IOException{
        Label title = new Label("Leader Board", 50);
        addObject(title, 15, 10); //Add the title
        
        getScores(); //Get scores and corresponding players from the file
        
        //Display empty message if no plays have occured
        if (nameList.size() == 0) {
            Label line = new Label("Currently empty!", 28);
            addObject(line, 15, 13);
        }
        
        //Display top players and their scores
        for (int i = 0; i <= 4 || i > nameList.size(); i++) {
            Label line = new Label("Player #" + (i+1) + ": " + nameList.get(i) + 
            " (Score: " + scoreList.get(i) + ")", 28);
            addObject(line, 15, 13+2*i);
        }
    }
    
    /**
     * Method for displaying the current level during the game.
     */
    public void displayLevel() {
        //Display the level (last 3 seconds)
        LevelLabel showLevel = new LevelLabel("Leveled Up! Level: " + level, 30);
        addObject(showLevel, 8, 4);
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

    /**
     * Getter method for the score
     * 
     * @return the user's score
     */
    public int getScore() {
        return score;
    }
    
    /**
     * Setter method that increases the score
     * 
     * @param score that is increased
     */
    public void increaseScore(int num) {
        score += num;
    }
    
    /**
     * Setter method that decreases the score
     * 
     * @param score that is decreased
     */
    public void decreaseScore(int num) {
        score -= num;
    }
    
    /**
     * Method for placing the enemy snake
     * 
     * @return If the enemy snake is successfully placed
     */
    public boolean addEnemy() {
        int rand = (int) (Math.random() * 4); //This value determines which direction the snake moves
        
        //Get random x and y coordinates to place the snake
        int eY = genCoordinates()[1];
        int eX = genCoordinates()[0];
        
        EnemyHead eHead = new EnemyHead(); //Construct an enemy head
        
        //Place the enemy snake in location and set direction according to the random value
        //Only place it when the desired location does not contain any other objects
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
    
    /**
     * Method for placing the pylon
     * 
     * @return If the pylon is successfully placed
     */
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

    /**
     * Method for placing the food
     * 
     * @return If the food is successfully placed
     */
    public boolean addFood() {
        //Get random x and y coordinates to place the food
        int randX = genCoordinates()[0];
        int randY = genCoordinates()[1];

        //Place the food if the desired location does not contain any other objects
        if (getObjectsAt(randX, randY, Actor.class).isEmpty()) {
            
            //20% probability the cherry will be placed, 80% probability the apple will be placed
            int randNum = (int) (Math.random()*5);

            if (randNum == 1 || randNum == 2 || randNum == 3 || randNum == 4) {
                Food tempFood  = new Food();
                addObject(tempFood, randX, randY);
            } else if (randNum == 0) {
                Cherry tempCherry = new Cherry();
                addObject(tempCherry, randX, randY);
            }
            return true;
        }
        return false;
    }
    
    /**
     * Method for generating coordinates
     * 
     * @return [0] - random X value, [1] - random Y value
     */
    public int[] genCoordinates() {
        int[] coordinates = new int[2];
        coordinates[0] = Greenfoot.getRandomNumber(getWidth());
        coordinates[1] = Greenfoot.getRandomNumber(getHeight());
        return coordinates;
    }
    
    /**
     * Method that controls what happens after run is clicked
     */
    public void started() {
        //Play music if the game is in progress
        if (running) {
            music.play();
            music.setVolume(15);
        }
    }
    
    /**
     * Method that controls what happens after pause is clicked
     */
    public void stopped() {
        //Pause the music
        music.pause();
    }
    
    /**
     * Method for getting all past scores from the document
     */
    public void getScores() throws IOException{
        //Create a new scanner that scans the file
        Scanner k = new Scanner (new File("HighScores.txt"));
        
        scoreList.clear();
        nameList.clear();
        
        //Scan each line from the file
        while (k.hasNextLine()) {
            //Split each line into the score and the name
            String temp[] = k.nextLine().split(" ");
            int sc = Integer.parseInt(temp[0]);
            String name = temp[1];
            
            //Add the score and the name onto their respective list
            scoreList.add(sc);
            nameList.add(name);
        }
        
        //Use bubble sort to sort through all the scores with their respective player names
        for (int i = 1; i < scoreList.size(); i++) {
            for (int j = i; j > 0 && scoreList.get(j - 1) <= scoreList.get(j); j--) {
                Collections.swap(scoreList, j, j - 1);
                Collections.swap(nameList, j, j - 1);
            }
        }
        
        k.close(); //Close the file
    }
    
    /**
     * Method for inputting the score after a game ends
     */
    public void inputScore(int score) throws IOException{
        //Append a line (score and the name) into the file
        PrintWriter o = new PrintWriter(new FileWriter("HighScores.txt", true));
        o.println(score + " " + pName);
        
        o.close(); //Close the file
    }
    
    /**
     * Act - do whatever the SnakeWorld wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        //Detect which button is clicked
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null && mouse.getClickCount() == 1) {
            if (mouse.getActor() == start) {
                //If the start button is clicked, prompt the user to enter the name
                pName = Greenfoot.ask("Enter your name: (max 6 characters)");
                
                //Make sure the name is no longer than 6 characters
                while (pName.length() > 6) {
                    pName = Greenfoot.ask("Max 6 characters!");
                }
                
                removeObjects(getObjects(Button.class)); //Remove buttons
                startWorld(); //Initialize the world
            } else if (mouse.getActor() == instructions) {
                //If the instructions button is clicked
                placeInstructionsLabel(); //Place the instructions
                
                removeObjects(getObjects(Button.class)); //Remove buttons
                addObject(back, 5, 27); //Add the back button
            } else if (mouse.getActor() == leaderBoard) {
                //If the leader board button is clicked
                try {
                    placeLeaderBoardLabel(); //Place the leaderboard labels
                } catch(Exception E) {
                }
                
                removeObjects(getObjects(Button.class)); //Remove buttons
                addObject(back, 5, 27); //Add the back button
            } else if (mouse.getActor() == back) {
                //If the back button is clicked, remove all objects
                removeObject(back);
                removeObjects(getObjects(Label.class));
                removeObjects(getObjects(Instructions.class));
                startScreen(); //Initialize the start screen
            } else if (mouse.getActor() == playAgain) {
                //If the play again button is clicked, remove all objects
                removeObject(playAgain);
                removeObjects(getObjects(Label.class));
                startScreen(); //Initialize the start screen
            }
        }
        
        /* The following code initializes different levels. Every time the player
         * gains 10 points in the score, he/she moves onto the next level.
         * Each level contains walls that are set up differently.
         * Only the first two cases are commented due to repetitiveness.
         * 
         * Case 1-7 show the initialization of different levels.
         * Case 0, 11-17 track whether the player is eligible to move onto the next level.
         */
        switch (level) {
            case 0: if (score >= 10) {
                        level = 1;
                    } //Move onto the next level when the score reaches 10
                    break;
                    
            case 1: //remove all obstacles and food
                    removeObjects(getObjects(Pylon.class));
                    removeObjects(getObjects(Food.class));
                    removeObjects(getObjects(Cherry.class));
                    
                    //Add walls
                    for (int i = 0; i <= 30; i=i+5) {
                        addObject(new Wall(), i, 15);
                    }
                    displayLevel(); //Display the current level
                    level = 11; //Move on to the tracking phase
                    while (!addFood()); //Add the food into the game
                    
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
        
        //If the game is running
        if (running) {
            gameTime++;
            
            //A pylon pops up every 10 seconds
            if (gameTime % 600 == 0) {
                while (!addPylon());
                //Repeat until successfully placed
            }
            
            //An enemy snake pops up every 30 seconds
            if (gameTime % 1800 == 0) {
                while (!addEnemy());
                //Repeat until successfully placed
            }
        }
    }
}
