package splash.model;

import java.awt.Image;
import java.awt.Point;

public abstract class Tool {

    private Image thumb;
    protected String id;
    
    /**
     *
     * @param startpoint
     */
    public abstract void initFunction(Point startpoint);
    
    public String getId()
    {
        return this.id;
    }


}
