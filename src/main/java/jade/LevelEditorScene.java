package jade;


import org.lwjgl.opengl.GL20;

import static org.lwjgl.opengl.GL20.*;

public class LevelEditorScene extends Scene {

    private String vertexSharderSrc = "#version 330 core\n" +
            "    layout (location=0) in vec3 aPos;\n" +
            "    layout (location=1) in vec4 aColor;\n" +
            "\n" +
            "    out vec4 fColor;\n" +
            "\n" +
            "    void main(){\n" +
            "        fColor = aColor;\n" +
            "        gl_Position = vec4(aPos, 1.0);\n" +
            "    }";
    private String fragmentsShaderSrc = "#version 330 core\n" +
            "\n" +
            "    in vec4 fColor;\n" +
            "\n" +
            "    out vec4 color;\n" +
            "\n" +
            "    void main(){\n" +
            "        color = fColor;\n" +
            "    }";

    private int vertexID, fragmentID, shaderProgram;


    public LevelEditorScene(){

    }
    @Override
    public void init(){
        // compile and link the shaders

        //first load and compile the vertex shader
        vertexID = glCreateShader(GL_VERTEX_SHADER);

        // PASS THE SHADER source code to the gpu
        glShaderSource(vertexID, vertexSharderSrc);
        glCompileShader(vertexID);

        //check for errors in compilation process
        int success = glGetShaderi(vertexID, GL_COMPILE_STATUS);
        if (success == GL_FALSE){
            int len = glGetShaderi(vertexID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: defaultShader.glsl'\n\tVertex shader compilation failed.");
            System.out.println(glGetShaderInfoLog(vertexID, len));
            assert false: "";
        }
        //Fragment shader
        //first load and compile the vertex shader
        fragmentID = glCreateShader(GL_FRAGMENT_SHADER);

        // PASS THE SHADER source code to the gpu
        glShaderSource(fragmentID, fragmentsShaderSrc);
        glCompileShader(fragmentID);

        //check for errors in compilation process
        success = glGetShaderi(fragmentID, GL_COMPILE_STATUS);
        if (success == GL_FALSE){
            int len = glGetShaderi(fragmentID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: defaultShader.glsl'\n\tFragment shader compilation failed.");
            System.out.println(glGetShaderInfoLog(fragmentID, len));
            assert false: "";}

        //Link shader and check for errors
        shaderProgram = glCreateProgram();
        glAttachShader(shaderProgram, vertexID);
        glAttachShader(shaderProgram, fragmentID);
        glLinkProgram(shaderProgram);

        //check for linking error
        success = glGetProgrami(shaderProgram, GL_LINK_STATUS);
        if(success == GL_FALSE){
            int len = glGetProgrami(shaderProgram, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: defaultShader.glsl'\n\tLinking of shader failed.");
            System.out.println(glGetProgramInfoLog(shaderProgram, len));
            assert false: "";
        }
    }
    @Override
    public void update(float dt) {

        /*
        //System.out.println(" " + (1.0f / dt) + "FPS"); how many frames per seconds we are running
        if(!changingScene && KeyListener.isKeyPressed(KeyEvent.VK_SPACE)){
            changingScene = true;
        }
        if (changingScene && timeToChangeScene > 0 )
        {
            timeToChangeScene -= dt;
            Window.getWindow().r -= dt * 5.0f;
            Window.getWindow().g -= dt * 5.0f;
            Window.getWindow().b -= dt * 5.0f;
        }
        else if (changingScene){
            Window.changeScene(1);
        }*/



    }

}
