package patryk.gawron.l2cube2;

import android.opengl.GLES30;

import java.util.ArrayList;
import java.util.List;

public class Cube {

    private static int idCount = -1;
    private int ID;
    private int mProgram;
    private VertexArray va;
    private VertexBuffer vb;
    private IndexBuffer ib;
    private Shader shader;
    private List<Float[]> rotation;
    private float[][] colors;
    private static float cubeCoords[] ={
            -0.5f,  0.5f,  0.5f,    //top near left     0
            -0.5f, -0.5f,  0.5f,    //bottom near left  1
             0.5f, -0.5f,  0.5f,    //bottom near right 2
             0.5f,  0.5f,  0.5f,    //top near right    3
            -0.5f,  0.5f, -0.5f,    //top far left      4
            -0.5f, -0.5f, -0.5f,    //bottom far left   5
             0.5f, -0.5f, -0.5f,    //bottom far right  6
             0.5f,  0.5f, -0.5f,    //top far right     7
    };
    private short drawOrder[] = {
            0, 1, 2,
            0, 3, 2,    //Front

            0, 4, 5,
            0, 1, 5,    //Left

            0, 4, 7,
            0, 3, 7,    //Top

            6, 7, 3,
            6, 2, 3,    //Right

            6, 7, 4,
            6, 5, 4,    //Back

            6, 5, 1,
            6, 2, 1     //Bottom
    };

    Cube(float[][] color) {
        rotation = new ArrayList<>();
        colors = color;
        ID = ++idCount % 27;
    }

    //Wcześniej wszystkie call'e z tej metody były w konstruktorze
    //ale ponieważ tworzę tablicę kostek poza thread'ami openGL'a
    //czyli poza przysłoniętymi metodami renderer'a (a tylko one działają
    //na trhead'ach openGL'a)
    public void setup(){
        va = new VertexArray();
        vb = new VertexBuffer(cubeCoords);
        ib = new IndexBuffer(drawOrder);
        shader = new Shader();

        mProgram = shader.createShader();
        va.push(3);
    }

    public void draw(float[] matrix) {
        GLES30.glEnable(GLES30.GL_DEPTH_TEST);
        shader.bind();
        va.enableVertex(mProgram, 0);
        shader.setUniformMatrix4fv("uMVPMatrix", matrix);
        int offset = 0;
        for(int i =0; i < drawOrder.length / 6; i++){
            shader.setUniform4fv("vColor", colors[i]);
            GLES30.glDrawElements(GLES30.GL_TRIANGLES, drawOrder.length/6, GLES30.GL_UNSIGNED_SHORT, offset);
            offset +=2*6;
        }
        shader.unbind();
    }

    public int getID(){
        return  ID;
    }

    public Float[] getRotations(int index){
        return rotation.get(index);
    }

    public void pushRotation(Float[] array){ //float angle, float xAxis, float yAxis, float zAxis
        if(!rotation.add(array)){
            System.out.println("Failed to add");
        }
    }
    public int getRotationsPointer(){
        return rotation.size();
    }
    public void clear(){
        rotation.clear();
    }
}
