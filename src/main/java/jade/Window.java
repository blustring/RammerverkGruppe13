package jade;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import util.Time;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class
Window {
    private int width, height;
    private String title;
    private long glfwWindow;

    public float r, g, b, a;
    private boolean fadeToBlack;
    private static Scene currentScene;



    private static Window window = null;

    private Window(){
            this.width = 1920;
            this.height = 1080;
            this.title = "2dGame";
            r = 1;
            g = 1;
            b = 1;
            a = 1;


    }

    public static void changeScene(int newScene){
            switch (newScene){
                case 0:
                    currentScene = new LevelEditorScene();
                    currentScene.init();
                    currentScene.start();
                    break;
                case 1:
                    currentScene = new LevelScene();
                    currentScene.init();
                    currentScene.start();
                    break;
                default:
                    assert false : "Unknown scene " + newScene + " ' ";
                    break;
            }
        }


        public static Window get() {
        if (Window.window == null) {
            Window.window = new Window();
        }
        return Window.window;
        }

        public static Scene getScene() {
        return get().currentScene;
        }
    public void run(){
        System.out.println("Hello opengl" + Version.getVersion() + "!");

        init();
        loop();

        // free the memory
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        //terminare GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();


    }
    public void init(){
        // setup an error callback
        GLFWErrorCallback.createPrint(System.err).set();

        // initialize glfw
        if(!glfwInit()){
            throw new IllegalStateException("GLFW not available.");

        }

        // config flgw
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE , GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        //create window
        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if(glfwWindow == NULL){
            throw new IllegalStateException("Failed to load the game");
        }

        glfwSetCursorPosCallback(glfwWindow, MouseListener :: mousePosCallback);
        glfwSetMouseButtonCallback(glfwWindow, MouseListener :: mouseButtonCallback);
        glfwSetScrollCallback(glfwWindow, MouseListener :: mouseScrollCallback);
        glfwSetKeyCallback(glfwWindow, KeyListener :: keyCallback);

        // make the opengl context current
        glfwMakeContextCurrent(glfwWindow);
        //enable v-sync no restriction, no wait time
        glfwSwapInterval(1);
        // make windown visible
        glfwShowWindow(glfwWindow);

        GL.createCapabilities(); //

        Window.changeScene(0);

    }

    public void loop(){
        float beginTime = Time.getTime();
        float endTime;
        float dt = -1.0f;

        while (!glfwWindowShouldClose(glfwWindow)){
            //poll event - key event, mouse listener
            glfwPollEvents();

            glClearColor(r, g, b, a);
            glClear(GL_COLOR_BUFFER_BIT);

            if (dt >= 0) {
                currentScene.update(dt);
            }
            glfwSwapBuffers(glfwWindow);

            endTime = Time.getTime();
            dt = endTime -beginTime;
            beginTime = endTime;

        }

    }


    public static Window getWindow() {
        if (Window.window == null ){
            Window.window = new Window();
        }
        return Window.window;
    }

}
