package patryk.gawron.l2cube2;

import android.opengl.GLES30;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

public class IndexBuffer {

    private ShortBuffer indiciesBuffer;
    private int[] mRendererID = new int[1];

    public IndexBuffer(short[] data){
        ByteBuffer dlb = ByteBuffer.allocateDirect(data.length * 2);
        dlb.order(ByteOrder.nativeOrder());

        indiciesBuffer = dlb.asShortBuffer();
        indiciesBuffer.put(data);
        indiciesBuffer.position(0);

        GLES30.glGenBuffers(1, mRendererID, 0);
        GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, mRendererID[0]);
        GLES30.glBufferData(GLES30.GL_ELEMENT_ARRAY_BUFFER, data.length *2, indiciesBuffer, GLES30.GL_STATIC_DRAW);
    }

    public void bind(){
        GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, mRendererID[0]);
    }

    public void unbind(){
        GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, 0);
    }
}
