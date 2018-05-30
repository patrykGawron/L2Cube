package patryk.gawron.l2cube2;

import android.opengl.GLES30;

public class Square {


    private static float squareCoords[];
    private short drawOrder[];

    private final int mProgram;
    private VertexArray va;
    private VertexBuffer vb;
    private IndexBuffer ib;
    private Shader shader;

    public Square(float[] coords, short[] order, float ambientStrenght) {
        squareCoords = coords;
        drawOrder = order;

        va = new VertexArray();
        vb = new VertexBuffer(squareCoords);
        ib = new IndexBuffer(drawOrder);
        shader = new Shader();

        mProgram = shader.createShader();
        va.push(3);
    }

    public void draw(float[] mvpMatrix, float[][] colors) {
        GLES30.glEnable(GLES30.GL_DEPTH_TEST);
        shader.bind();
        va.enableVertex(mProgram, 0);
        shader.setUniformMatrix4fv("uMVPMatrix", mvpMatrix);
        int offset = 0;
        for(int i =0; i < drawOrder.length / 6; i++){
            shader.setUniform4fv("vColor", colors[i]);
            GLES30.glDrawElements(GLES30.GL_TRIANGLES, drawOrder.length/6, GLES30.GL_UNSIGNED_SHORT, offset);
            offset +=2*6;
        }
        shader.unbind();
    }
}
