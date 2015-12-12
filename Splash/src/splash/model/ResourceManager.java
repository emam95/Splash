package splash.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ResourceManager {

    private static ArrayList<Tool> tools = new ArrayList<>();
    private static HashMap<String, Class<? extends Brush>> brushes = new HashMap<>();

    public static ArrayList<Tool> getTools() {
        return tools;
    }

    public static HashMap<String, Class<? extends Brush>> getBrushes() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param brushes
     */
    public static void setBrushes(int brushes) {
        throw new UnsupportedOperationException();
    }

    public static void loadDrawables(String path) {
        // Pre-installed
        DrawableFactory.Drawables.put("Rectangle", RectangleObject.class);
        DrawableFactory.Drawables.put("Triangle", TriangleObject.class);
        DrawableFactory.Drawables.put("Ellipse", Ellipse.class);
        DrawableFactory.Drawables.put("Polygon", PolygonObject.class);

        BrushFactory.Brushes.put("Round", RoundBrush.class);
        // External
        // TODO: Load external classes
    }

    /**
     *
     * @param path
     */
    public static void loadTools() {
        // Predefined tools
        tools.add(new PointerTool());
        tools.add(new MarqueTool());
        tools.add(new ResizeTool());
        tools.add(new EraserTool());
        // Shape based tools
        Iterator<String> it = DrawableFactory.Drawables.keySet().iterator();
        while (it.hasNext()) {
            tools.add(new DrawableTool(it.next()));
        }
        Iterator<String> itb = BrushFactory.Brushes.keySet().iterator();
        while (itb.hasNext()) {
            tools.add(new BrushTool(itb.next()));
        }
        // TODO: Include brushes as tools
    }

    /**
     *
     * @param path
     */
    public static void loadBrushes(String path) {
        //TODO: load brushes
    }

    /**
     *
     * @param path
     */
    public static void saveProject(String path) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param path
     */
    public static void loadProject(String path) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param path
     */
    public static void exportImage(String path) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param path
     * @return
     */
    public RawLayer loadImage(String path) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param Layer
     * @param path
     */
    public static void exportAnimation(int[] Layer, String path) {
        throw new UnsupportedOperationException();
    }

    private ResourceManager() {
        throw new UnsupportedOperationException();
    }

}
