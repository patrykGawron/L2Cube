package patryk.gawron.l2cube2;

import android.opengl.GLES20;
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
    public Shader(){
        mRendererID = createShader();
    }

    public int compileShader(int type, String shaderCode){

        int shader = GLES30.glCreateShader(type);

        GLES30.glShaderSource(shader, shaderCode);
        GLES30.glCompileShader(shader);

        return shader;
    }

    public int createShader(){
        int program;
        int vertexShader = compileShader(GLES20.GL_VERTEX_SHADER,
                vertexShaderCode);
        int fragmentShader = compileShader(GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode);

        program = GLES20.glCreateProgram();

        GLES20.glAttachShader(program, vertexShader);
        GLES20.glAttachShader(program, fragmentShader);

        GLES20.glLinkProgram(program);
        return program;
    }

    public void bind(){
        GLES30.glUseProgram(mRendererID);
    }

    public void unbind(){
        GLES30.glUseProgram(0);
    }

    private int getUniformLocation(String name){
        int location = GLES30.glGetUniformLocation(mRendererID, name);
        return location;
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
