import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fox extends Actor
{
    // Track current theoretical position in wider "scrollable" world
    private int currentScrollableWorldXPosition;

    // Constants to track horizontal direction
    private static final String FACING_RIGHT = "right";
    private static final String FACING_LEFT = "left";
    private String horizontalDirection;
    // For walking animation
    private GreenfootImage walkingRightImages[];
    private GreenfootImage walkingLeftImages[];
    private static final int WALK_ANIMATION_DELAY = 8;
    private static final int COUNT_OF_WALKING_IMAGES = 2;
    private int walkingFrames;
    private int direction; 

    // Horizontal speed (change in horizontal position, or delta X)
    private int deltaX = 4;
    // Vertical speed (change in vertical position, or delta Y)
    private int deltaY = 4;
    private int jeda= 10; 
    private int num= 0; 
    /**
     * Constructor
     * 
     * This runs once when the fox object is created.
     */
    Fox(int startingX)
    {
        // Set where fox begins horizontally
        currentScrollableWorldXPosition = startingX;

        // Facing right to start
        horizontalDirection = FACING_RIGHT;
        // Initialize the 'walking' arrays
        walkingRightImages = new GreenfootImage[COUNT_OF_WALKING_IMAGES];
        walkingLeftImages = new GreenfootImage[COUNT_OF_WALKING_IMAGES];

        // Load walking images from disk
        for (int i = 0; i < walkingRightImages.length; i++)
        {
            walkingRightImages[i] = new GreenfootImage("fox-walk-right-" + i + ".png");

            // Create left-facing images by mirroring horizontally
            walkingLeftImages[i] = new GreenfootImage(walkingRightImages[i]);
            walkingLeftImages[i].mirrorHorizontally();
        }

        // Track animation frames for walking
        walkingFrames = 0;
    }

    /**
     * Act - do whatever the Fox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move();
    } 

    public void move()
    {
        if(jeda==0)jeda=10;
        if(jeda==1){
            setImage(walkingRightImages[num]);
            num++;
            if(num>=walkingRightImages.length)num=0;
            setLocation(getX()+deltaX,getY());
            if(getX()>getWorld().getWidth()+100)setLocation(-100,getY());
        }
        
        if(jeda>0)jeda--;
       
    }

    /**
     * Animate walking
     */
    private void animateWalk(String direction)
    {
        // Track walking animation frames
        walkingFrames += 1;

        // Get current animation stage
        int stage = walkingFrames / WALK_ANIMATION_DELAY;

        // Animate
        if (stage < walkingRightImages.length)
        {
            // Set image for this stage of the animation
            if (direction == FACING_RIGHT)
            {
                setImage(walkingRightImages[stage]);
            }
            else
            {
                setImage(walkingLeftImages[stage]);
            }
        }
        else
        {
            // Start animation loop from beginning
            walkingFrames = 0;
        }
    }

    public boolean onGround()
    {
        // Get an reference to a solid object (subclass of Platform) below the hero, if one exists
        Actor directlyUnder = getOneObjectAtOffset(0, getImage().getHeight() / 2, Ground.class);
        Actor frontUnder = getOneObjectAtOffset(getImage().getWidth() / 3, getImage().getHeight() / 2, Ground.class);
        Actor rearUnder = getOneObjectAtOffset(0 - getImage().getWidth() / 3, getImage().getHeight() / 2, Ground.class);

        // If there is no solid object below (or slightly in front of or behind) the hero...
        if (directlyUnder == null && frontUnder == null && rearUnder == null)
        {
            return false;   // Not on a solid object
        }
        else
        {
            return true;
        }
    }

    /**
     * Move the fox to the right.
     */
    public void moveRight()
    {
        // Track direction
        horizontalDirection = FACING_RIGHT;

        // Set image 
        if (onGround())
        {
            animateWalk(horizontalDirection);
        }

    }

    public void moveLeft()
    {
        // Track direction
        horizontalDirection = FACING_LEFT;

        // Set image 
        if (onGround())
        {
            animateWalk(horizontalDirection);
        }
    }
}

