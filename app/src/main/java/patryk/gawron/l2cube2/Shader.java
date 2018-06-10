package patryk.gawron.l2cube2;

import android.opengl.GLES30;

public class Shader {

    private int mRendererID;

    private final String vertexShaderCode =
                    "uniform mat4 uMVPMatrix;"+
                    "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor =  vColor;" +
                    "}";
    Shader(){
        mRendererID = createShader();
    }

    private int compileShader(int type, String shaderCode){

        int shader = GLES30.glCreateShader(type);

        GLES30.glShaderSource(shader, shaderCode);
        GLES30.glCompileShader(shader);

        return shader;
    }

    public int createShader(){
        int program;
        int vertexShader = compileShader(GLES30.GL_VERTEX_SHADER,
                vertexShaderCode);
        int fragmentShader = compileShader(GLES30.GL_FRAGMENT_SHADER,
                fragmentShaderCode);

        program = GLES30.glCreateProgram();

        GLES30.glAttachShader(program, vertexShader);
        GLES30.glAttachShader(program, fragmentShader);

        GLES30.glLinkProgram(program);
        return program;
    }

    public void bind(){
        GLES30.glUseProgram(mRendererID);
    }

    public void unbind(){
        GLES30.glUseProgram(0);
    }

    private int getUniformLocation(String name){
        return GLES30.glGetUniformLocation(mRendererID, name);
    }

    public void setUniform4fv(String name, float[] data){
        GLES30.glUniform4fv(getUniformLocation(name), 1, data, 0);
    }

    public void setUniformMatrix4fv(String name, float[] data){
        GLES30.glUniformMatrix4fv(getUniformLocation(name), 1, false, data, 0);
    }

    public int getMRendererID(){
        return mRendererID;
    }
}
