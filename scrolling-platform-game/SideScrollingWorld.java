import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Template for a side-scrolling platform game.
 * 
 * @author R. Gordon
 * @version May 8, 2019
 */
public class SideScrollingWorld extends World
{
    /**
     * Instance variables
     * 
     * These are available for use in any method below.
     */    
    // Tile size in pixels for world elements (blocks, clouds, etc)
    private static final int TILE_SIZE = 32;
    private static final int HALF_TILE_SIZE = TILE_SIZE/2; 

    // World size constants
    private static final int VISIBLE_WIDTH = 640;
    private static final int VISIBLE_HEIGHT = 480;
    public static final int HALF_VISIBLE_WIDTH = VISIBLE_WIDTH / 2;
    private static final int HALF_VISIBLE_HEIGHT = VISIBLE_HEIGHT / 2;
    public static final int SCROLLABLE_WIDTH = VISIBLE_WIDTH * 3;
    private static final int SCROLLABLE_HEIGHT = VISIBLE_HEIGHT;

    // Hero
    Hero theHero;
    //Fox
    Fox theFox;

    // Track whether game is on
    private boolean isGameOver;

    // Object for background music
    private GreenfootSound backgroundMusic;

    //Track time going up 
    private int frames = 0;
    //Track the score 
    private int Score = 0; 
    //Track if Fox is Touched 
    private int FoxTouched =0; 

    private int CoinTaken = 0;
    /**
     * Constructor for objects of class SideScrollingWorld.
     */
    public SideScrollingWorld()
    {    
        // Create a new world with 640x480 cells with a cell size of 1x1 pixels.
        // Final argument of 'false' means that actors in the world are not restricted to the world boundary.
        // See: https://www.greenfoot.org/files/javadoc/greenfoot/World.html#World-int-int-int-boolean-
        super(VISIBLE_WIDTH, VISIBLE_HEIGHT, 1, false);

        // Set up the starting scene
        setup();

        // Game on
        isGameOver = false;

        // Load the sound file
        backgroundMusic = new GreenfootSound("background.mp3");

        // Play the sound file
        backgroundMusic.playLoop();

        // Set the initial score
        String currentScore = "Score: 0";
        showText(currentScore, 500, 50);
        //dedut score if garfield touches the fox 

    }
    /**
     * Set up the entire world.
     */
    private void setup()
    {
        addLeftGround();
        addTrees();
        addBirds();
        addRightGround();
        addHero();
        addFox();
        //Add metal plates to the world 
        //Add metal plates in upper left corner 
        //Add group 1 
        for(int i = 0;i<=4; i+=1) 
        {
            // Location 
            int x = TILE_SIZE + HALF_TILE_SIZE + i * TILE_SIZE;
            int y = 12 * TILE_SIZE + HALF_TILE_SIZE;

            MetalPlate plate = new MetalPlate (x,y); 
            addObject(plate,x,y); 
        }
        //Add group 2 
        for(int i = 0;i<=3; i+=1) 
        {
            //Location 
            int x = 8*TILE_SIZE + HALF_TILE_SIZE + i * TILE_SIZE;
            int y = 8 * TILE_SIZE + HALF_TILE_SIZE;

            MetalPlate plate = new MetalPlate (x,y); 
            addObject(plate,x,y); 
        }
        //Add group 3 
        for(int i = 0;i<=3; i+=1) 
        {
            //Location 
            int x = 20*TILE_SIZE + HALF_TILE_SIZE + i * TILE_SIZE;
            int y = 8 * TILE_SIZE + HALF_TILE_SIZE;

            MetalPlate plate = new MetalPlate (x,y); 
            addObject(plate,x,y); 
        }
        // //Add group 4  
        for(int i = 0;i<=3; i+=1) 
        {
            //Location 
            int x = 14*TILE_SIZE + HALF_TILE_SIZE + i * TILE_SIZE;
            int y = 10 * TILE_SIZE + HALF_TILE_SIZE;

            MetalPlate plate = new MetalPlate (x,y); 
            addObject(plate,x,y); 
        }
        // Add group 5
        for(int i = 0;i<=5; i+=1) 
        {
            //Location 
            int x = 24 * TILE_SIZE + HALF_TILE_SIZE + i * TILE_SIZE;
            int y = 13 * TILE_SIZE + HALF_TILE_SIZE;

            MetalPlate plate = new MetalPlate (x,y); 
            addObject(plate,x,y); 
        }
        // Add group 6
        for(int i = 0;i<=3; i+=1) 
        {
            //Location 
            int x = 31 * TILE_SIZE + HALF_TILE_SIZE + i * TILE_SIZE;
            int y = 12 * TILE_SIZE + HALF_TILE_SIZE;

            MetalPlate plate = new MetalPlate (x,y); 
            addObject(plate,x,y); 
        }
        //Add group 7 
        for(int i = 0;i<=5; i+=1) 
        {
            //Location 
            int x = 37 * TILE_SIZE + HALF_TILE_SIZE + i * TILE_SIZE;
            int y = 10 * TILE_SIZE + HALF_TILE_SIZE;

            MetalPlate plate = new MetalPlate (x,y); 
            addObject(plate,x,y); 
        }
        //Add group 8 
        for(int i = 0;i<=2; i+=1) 
        {
            //Location 
            int x = 44 * TILE_SIZE + HALF_TILE_SIZE + i * TILE_SIZE;
            int y = 13 * TILE_SIZE + HALF_TILE_SIZE;

            MetalPlate plate = new MetalPlate (x,y); 
            addObject(plate,x,y); 
        }
        //Add group 9 
        for(int i = 0;i<=1; i+=1) 
        {
            //Location 
            int x = 47 * TILE_SIZE + HALF_TILE_SIZE + i * TILE_SIZE;
            int y = 13 * TILE_SIZE + HALF_TILE_SIZE;

            MetalPlate plate = new MetalPlate (x,y); 
            addObject(plate,x,y); 
        }
        //Add group 10 
        for(int i = 0;i<=1; i+=1) 
        {
            //Location 
            int x = 50 * TILE_SIZE + HALF_TILE_SIZE + i * TILE_SIZE;
            int y = 9 * TILE_SIZE + HALF_TILE_SIZE;

            MetalPlate plate = new MetalPlate (x,y); 
            addObject(plate,x,y); 
        }
    }

    /**
     * Add blocks to create the ground to walk on at bottom-left of scrollable world.
     */
    private void addLeftGround()
    {
        // How many tiles will cover the bottom of the initial visible area of screen?
        final int tilesToCreate = getWidth() / TILE_SIZE;

        // Loop to create and add the tile objects
        for (int i = 0; i < tilesToCreate; i += 1)
        {
            // Add ground objects at bottom of screen
            // NOTE: Actors are added based on their centrepoint, so the math is a bit trickier.
            int x = i * TILE_SIZE + TILE_SIZE / 2;
            int y = getHeight() - TILE_SIZE / 2;

            // Create a ground tile
            Ground groundTile = new Ground(x, y);

            // Add the objects
            addObject(groundTile, x, y);
        }
    }

    /**
     * Add some fences at left and right side.
     */
    private void addTrees()
    {
        // Three fences on left side of world
        int x = TILE_SIZE / 2 + TILE_SIZE * 5;
        int y = VISIBLE_HEIGHT - TILE_SIZE / 2 - TILE_SIZE;
        Tree tree1 = new Tree(x, y);
        addObject(tree1, x, y);

        x = TILE_SIZE / 2 + TILE_SIZE * 6;
        y = VISIBLE_HEIGHT - TILE_SIZE / 2 - TILE_SIZE;        
        Tree tree2 = new Tree(x, y);
        addObject(tree2, x, y);

        x = TILE_SIZE / 2 + TILE_SIZE * 7;
        y = VISIBLE_HEIGHT - TILE_SIZE / 2 - TILE_SIZE;
        Tree tree3 = new Tree(x, y);
        addObject(tree3, x, y);

        // Two fences on right side of world
        x = SCROLLABLE_WIDTH - TILE_SIZE / 2 - TILE_SIZE * 3;
        y = VISIBLE_HEIGHT / 2;
        Tree tree4 = new Tree(x, y);
        addObject(tree4, x, y);

        x = SCROLLABLE_WIDTH - TILE_SIZE / 2 - TILE_SIZE * 4;
        y = VISIBLE_HEIGHT / 2;
        Tree tree5 = new Tree(x, y);
        addObject(tree5, x, y);
    }

    /**
     * Add a few clouds for the opening scene.
     */
    private void addBirds()
    {
        Bird bird1 = new Bird (170, 125);
        addObject(bird1, 170, 125);
        Bird bird2 = new Bird(450, 175);
        addObject(bird2, 450, 175);
        Bird bird3 = new Bird(775, 50);
        addObject(bird3, 775, 50);
    }
    private void addCoin()
    {
        if ((frames % 240) == 0)
        {
            int x = Greenfoot.getRandomNumber(500) - 250;
            int y = Greenfoot.getRandomNumber(100);
            System.out.println("Adding a coin at x and y of " + x + " and " + y);
            Coin myCoin = new Coin(theHero.currentScrollableWorldPosition() + x, y);
            addObject(myCoin, theHero.getX() + x, y);
        } 
    }
    /**
     * Act
     * 
     * This method is called approximately 60 times per second.
     */
    public void act()
    {
        if ((frames % 60) == 0)
        {
            String timeElapsed = "Time: " + Integer.toString(frames / 60);
            showText(timeElapsed, 100, 50);
        }
        // Increment frame (roughly 60 frames per second)
        frames = frames + 1;

        // See if it's time to add a coin
        addCoin();
        
        // After 60 seconds, end the game 
        if (frames == 3600 )
        {          
            Greenfoot.stop();  
            String GameEnd = "GameOver";
            showText(GameEnd, 300, 200);
        }

    }

    /**
     * Add the hero to the world.
     */
    private void addHero()
    {
        // Initial horizontal position
        int initialX = getWidth() - 10 * getWidth() / 11;

        // Instantiate the hero object
        theHero = new Hero(initialX);

        // Add hero in bottom left corner of screen
        addObject(theHero, initialX, getHeight() - 4 * TILE_SIZE);
    }

    /**
     * Add the fox to the world.
     */
    private void addFox()
    {
        // Initial horizontal position
        int initialX = getWidth() - 10 * getWidth() / 11;

        // Instantiate the fox object
        theFox = new Fox(initialX, getHeight() - 50);

        // Add hero in bottom left corner of screen
        addObject(theFox, initialX, getHeight() - 1 * TILE_SIZE);
    }

    /**
     * Add blocks to create the ground to walk on at top-right of scrollable world.
     */
    private void addRightGround()
    {
        // Constants to control dimensions of the ground at end of world
        final int COUNT_OF_GROUND = 8;
        final int GROUND_BELOW_COLUMNS = COUNT_OF_GROUND;
        final int GROUND_BELOW_ROWS = 6;
        final int COUNT_OF_GROUND_BELOW = GROUND_BELOW_COLUMNS * GROUND_BELOW_ROWS;

        // 1. Make ground at end of level (top layer)
        for (int i = 0; i < COUNT_OF_GROUND; i += 1)
        {
            // Position in wider scrollable world
            int x = SCROLLABLE_WIDTH - TILE_SIZE / 2 - i * TILE_SIZE;
            int y = VISIBLE_HEIGHT / 2 + TILE_SIZE;

            // Create object and add it
            Ground ground = new Ground(x, y);
            addObject(ground, x, y);
        }

        // 2. Make sub-ground at end of level (below top layer)
        for (int i = 0; i < GROUND_BELOW_COLUMNS; i += 1)
        {
            for (int j = 0; j < GROUND_BELOW_ROWS; j += 1)
            {
                // Position in wider scrollable world
                int x = SCROLLABLE_WIDTH - TILE_SIZE / 2 - i * TILE_SIZE;
                int y = VISIBLE_HEIGHT / 2 + TILE_SIZE + TILE_SIZE * (j + 1);

                // Create object and add it
                GroundBelow groundBelow = new GroundBelow(x, y);
                addObject(groundBelow, x, y);
            }
        }
    }

    /**
     * Return an object reference to the hero.
     */
    public Hero getHero()
    {
        return theHero;
    }

    /**
     * Set game over
     */
    public void setGameOver()
    {
        isGameOver = true;
    }

    public void FoxTouched()
    {
        Score = Score - 5;
        //Update the score 
        String currentScore = "Score: " + Integer.toString(Score);
        showText(currentScore, 500, 50); 
    }

    //Add to coin taken  
    public void CoinTaken()
    {
        Score = Score + 100;
        //Update the score 
        String currentScore = "Score: " + Integer.toString(Score);
        showText(currentScore, 500, 50); 
    }
}

