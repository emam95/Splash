package splash.model;

import java.awt.Image;
import java.awt.Point;

public abstract class Tool {

    private Image thumb;
<<<<<<< HEAD

=======
    protected String id;
    
>>>>>>> 3466df6f1fa30eff1a820daaded3f2cca3cade15
    /**
     *
     * @param startpoint
     */
    public abstract void initFunction(Point startpoint);
    
    public String getId()
    {
        return this.id;
    }


    public abstract void startDrawing(int x, int y);

    public abstract void mouseOffset(int x, int y);

    public abstract void finishDrawing();
}
