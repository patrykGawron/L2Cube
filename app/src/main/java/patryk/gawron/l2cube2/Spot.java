package patryk.gawron.l2cube2;

public class Spot {
    private float x;
    private float y;
    private float z;
    private int ID;
    private int boundCube;
    private static int countID = -1;

    Spot(float ax, float ay, float az){
        ID = ++countID % 27;
        x = ax;
        y = ay;
        z = az;
    }

    public int getID(){
        return  ID;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public float getZ(){
        return z;
    }

    public int getBoundCube(){
        return boundCube;
    }
    public void bindCube(int id){
        boundCube = id;
    }
}
