package splash.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.swing.ImageIcon;
import splash.controller.GUIMgr;

public class ResourceManager {

    private static ArrayList<Tool> tools = new ArrayList<>();
    private static HashMap<String, Class<? extends Brush>> brushes = new HashMap<>();

    public static ArrayList<Tool> getTools() {
        return tools;
    }

    public static HashMap<String, Class<? extends Brush>> getBrushes() {
        return BrushFactory.Brushes;
    }

    public static void loadDrawables(String path) {
        // Pre-installed
        DrawableFactory.Drawables.put("Rectangle", RectangleObject.class);
        DrawableFactory.Drawables.put("Triangle", TriangleObject.class);
        DrawableFactory.Drawables.put("Ellipse", Ellipse.class);
        DrawableFactory.Drawables.put("Polygon", PolygonObject.class);

        String jarpath = getJarPath();
        File dir = new File(jarpath + path + "/drawables");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                System.out.println("Found class: " + child.getAbsolutePath());
                try {
                    // Convert File to a URL
                    URL url = child.getParentFile().getParentFile().toURI().toURL();
                    ClassLoader loader = URLClassLoader.newInstance(
                            new URL[]{url},
                            Drawable.class.getClassLoader()
                    );
                    String cname = child.getName();
                    if (cname.endsWith(".class")) {
                        cname = cname.substring(0, cname.length() - 6);
                    }
                    Class<?> clazz = Class.forName("drawables." + cname, false, loader);
                    Class<? extends Drawable> drawable = clazz.asSubclass(Drawable.class);
                    DrawableFactory.Drawables.put(cname, drawable);
                    System.out.println("Loaded drawable: " + cname);
                } catch (Exception e) {
                    System.out.println("Failed to load class.");
                }
            }
        }
    }

    /**
     *
     * @param path
     */
    public static void loadTools() {
        // Predefined tools
        tools.add(new PointerTool());
        tools.add(new FillTool());
        tools.add(new ResizeTool());
        tools.add(new EraserTool());
        tools.add(new IntersectTool());
        tools.add(new MultipleSelectionTool());
        // Shape based tools
        Iterator<String> it = DrawableFactory.Drawables.keySet().iterator();
        while (it.hasNext()) {
            tools.add(new DrawableTool(it.next()));
        }
        Iterator<String> itb = BrushFactory.Brushes.keySet().iterator();
        while (itb.hasNext()) {
            tools.add(new BrushTool(itb.next()));
        }
    }

    /**
     *
     * @param path
     */
    public static void loadBrushes(String path) {
        BrushFactory.Brushes.put("Round", RoundBrush.class);
    }

    /**
     *
     * @param path
     */
    public static void exportImage(String path) {
        BufferedImage img = GUIMgr.getVisibleImage();
        //        ImageIO io = new ImageWriter();
    }

    private static String getJarPath() {
        return System.getProperty("user.dir");
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
