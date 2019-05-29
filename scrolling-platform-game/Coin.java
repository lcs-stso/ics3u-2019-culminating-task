import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Coin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Coin extends Decoration
{
    private int frames = 0; 
      Coin(int scrollableWorldX, int scrollableWorldY)
    {
        super(scrollableWorldX, scrollableWorldY);
        
    }
    /**
     * Act - do whatever the Coin wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        int x = getX();
        int y = getY();
        setLocation(x, y+4);
        
        
        //object disappear by itself 
        frames = frames + 1; 
        if((frames%240) == 0)
        {
            //Get an object reference to our world
            SideScrollingWorld world = (SideScrollingWorld) getWorld();
            world.removeObject(this);    
        }
    }    
}
