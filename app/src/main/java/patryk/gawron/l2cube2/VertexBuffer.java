package patryk.gawron.l2cube2;

import android.opengl.GLES30;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class VertexBuffer {

    private FloatBuffer vertexBuffer;
    private int[] mRendererID = new int[1];

    public VertexBuffer(float[] data){
        ByteBuffer bb = ByteBuffer.allocateDirect(data.length * 4);
        bb.order(ByteOrder.nativeOrder());

        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(data);
        vertexBuffer.position(0);

        GLES30.glGenBuffers(1,mRendererID, 0);
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, mRendererID[0]);
        GLES30.glBufferData(GLES30.GL_ARRAY_BUFFER, data.length*4, vertexBuffer, GLES30.GL_STATIC_DRAW);
    }

    public FloatBuffer getVertexBuffer(){
        return vertexBuffer;
    }

    public void bind(){
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, mRendererID[0]);
    }

    public void unbind(){
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, 0);
    }

    public int getMRendererID(){
        return mRendererID[0];
    }
}
