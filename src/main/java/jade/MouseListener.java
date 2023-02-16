package jade;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener {

    private static MouseListener instance; //
    private double scrollX, scrollY; //
    private double xPos, yPos, lastY, lastX; // dx dhy values
    private boolean mouseButtonPressed[] = new boolean[3]; //
    private boolean isDragging;

    public MouseListener() {
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.lastY = 0.0;
        this.lastX = 0.0;
    }
    public static MouseListener getMouseListener(){
        if (MouseListener.instance == null){
            MouseListener.instance = new MouseListener();
        }

        return MouseListener.instance;
    }
    // key input mouse input
    public static  void mousePosCallback(long window, double xpos, double ypos){
        getMouseListener().lastX = getMouseListener().xPos;
        getMouseListener().lastY = getMouseListener().yPos;
        getMouseListener().xPos = xpos;
        getMouseListener().yPos = ypos;
        getMouseListener().isDragging = getMouseListener().mouseButtonPressed[0] || getMouseListener().mouseButtonPressed[1] || getMouseListener().mouseButtonPressed[2];


    }
    // mouse button input
    public static void mouseButtonCallback(long window, int button, int action, int mods){
        if(action == GLFW_PRESS){
            if (button < getMouseListener().mouseButtonPressed.length) {
                getMouseListener().mouseButtonPressed[button] = true;
            }
            getMouseListener().mouseButtonPressed[button]= true;
        } else if (action == GLFW_RELEASE) {
            getMouseListener().mouseButtonPressed[button] = false;
            getMouseListener().isDragging = false;
        }
    }
    // mouse scroll
    public static void mouseScrollCallback(long window, double xOffset, double yOffset){
        getMouseListener().scrollX = xOffset;
        getMouseListener().scrollY = yOffset;
    }
    //end function
    public static void endFrame(){
        getMouseListener().scrollX = 0;
        getMouseListener().scrollY = 0;
        getMouseListener().lastX = getMouseListener().xPos;
        getMouseListener().lastY = getMouseListener().yPos;
    }

    public static float getX(){
        return (float) getMouseListener().xPos;
    }
    public static float getY(){
        return (float) getMouseListener().yPos;
    }
    public static float getEDx(){
        return (float) (getMouseListener().lastX - getMouseListener().xPos);
    }
    public static float getEDy(){
        return (float) (getMouseListener().lastY - getMouseListener().yPos);
    }
    public static float getScrollX(){
        return (float) getMouseListener().scrollX;
    }
    public static float getScrollY(){
        return (float) getMouseListener().scrollY;
    }
    public static boolean isDragging(){
        return getMouseListener().isDragging;
    }
    public static boolean mouseButtonDown(int button){
        if (button < getMouseListener().mouseButtonPressed.length){
            return getMouseListener().mouseButtonPressed[button];
        }else {
            return false;
        }
    }
}
