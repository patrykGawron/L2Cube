package patryk.gawron.l2cube2;

import android.opengl.GLES30;

import java.util.ArrayList;
import java.util.List;

public class VertexArray {
        private int[] mRendererID = new int[1];
        List<Integer> attribList = new ArrayList<Integer>();
        private  int mStride = 0;
        private boolean enabled = false;


        public VertexArray(){
            GLES30.glGenVertexArrays(1, mRendererID, 0);
        }

        public int getAttribLocation(int mProgram, String name){
            int location;
            location = GLES30.glGetAttribLocation(mProgram, name);
            return location;
        }

        public void push(int count){
            attribList.add(count);
            mStride += count * 4;
        }

        public void enableVertex(int program, int index){
            GLES30.glEnableVertexAttribArray(getAttribLocation(program, "vPosition"));
            GLES30.glVertexAttribPointer(getAttribLocation(program, "vPosition"),
                    attribList.get(index), GLES30.GL_FLOAT, false, mStride, 0);
        }


        public void bind(){
            GLES30.glBindVertexArray(mRendererID[0]);
        }

        public void unbind(){
            GLES30.glBindVertexArray(0);
        }

}
