package patryk.gawron.l2cube2;

import android.widget.BaseExpandableListAdapter;

import java.util.Arrays;
import static patryk.gawron.l2cube2.CubeColors.*;

public class Data {

        static int[] spotToCubeIDs;
        static void setSpotToCubeIDs(int[] array){
                spotToCubeIDs = array;
        }


    static float[][] translationVectors = {
            {0,0,0}, //mid 0

            {1.05f,0,0},// right mid 1
            {-1.05f,0,0}, //left mid 2
            {0,1.05f,0},// top mid 3
            {0,-1.05f,0},//bottom mid 4
            {0,0,1.05f},//front mid 5
            {0,0,-1.05f}, // back mid 6

            {1.05f, 1.05f, 0}, //right top mid 7
            {1.05f, -1.05f, 0}, //right bottom mid 8
            {-1.05f, 1.05f, 0}, //left top mid 9
            {-1.05f, -1.05f, 0}, //left bottom mid 10

            {0, 1.05f, 1.05f}, //top front mid 11
            {0, -1.05f, 1.05f},// bottom front mid 12
            {0, 1.05f, -1.05f}, // top back mid 13
            {0, -1.05f, -1.05f}, //bottom back mid 14

            {1.05f,0,1.05f}, //right front mid 15
            {1.05f,0,-1.05f},//right back mid 16
            {-1.05f,0,1.05f}, //left front mid 17
            {-1.05f,0,-1.05f}, //left back mid 18

            {1.05f, 1.05f, 1.05f},//right top front 19
            {1.05f, 1.05f, -1.05f},//right top back 20
            {1.05f, -1.05f, 1.05f},// right bottom front 21
            {1.05f, -1.05f, -1.05f},// right bottom back 22

            {-1.05f, 1.05f, 1.05f},//left top front 23
            {-1.05f, 1.05f, -1.05f},//left top back 24
            {-1.05f, -1.05f, 1.05f},//left bottom front 25
            {-1.05f, -1.05f, -1.05f}//left bottom back 26
    };



        static  int[][] spotsToChange = {
                {20, 26, 8, 2, 14, 11, 23, 17, 5},      //R     0
                {20, 2, 8, 26, 14, 11, 5, 17, 23},      //R'    1
                {18, 0, 6, 24, 12, 9, 3, 15, 21},       //L     2
                {18, 24, 6, 0, 12, 9, 21, 15, 3},       //L'    3
                {0, 2, 8, 6, 4, 1, 5, 7, 3},            //F     4
                {0, 6, 8, 2, 4, 1, 3, 7, 5},            //F'    5
                {18, 24, 26, 20, 22, 19, 21, 25, 23},   //B     6
                {18, 20, 26, 24, 22, 19, 23, 25, 21},   //B'    7
                {20, 2, 0, 18, 10, 19, 11, 1, 9},       //U     8
                {20, 18, 0, 2, 10, 19, 9, 1, 11},       //U'    9
                {24, 6, 8, 26, 16, 25, 15, 7, 17},      //D     10
                {24, 26, 8, 6, 16, 25, 17, 7, 15},      //D'    11
                {10, 4, 16, 22, 13, 1, 7, 25, 19},      //M     12
                {10, 22, 16, 4, 13, 1, 19, 25, 7},      //M'    13
                {21, 3, 5, 23, 13, 22, 12, 4, 14},      //E     14
                {21, 23, 5, 3, 13, 22, 14, 4, 12},      //E'    15
                {9, 11, 17, 15, 13, 10, 14, 16, 12},    //S     16
                {9, 15, 17, 11, 13, 10, 12, 16, 14},    //S'    17
        };

    static float[][] axisData = {
            {-1.0f, 0, 0},      //R     0
            {1.0f, 0, 0},       //R'    1
            {1.0f, 0, 0},       //L     2
            {-1.0f, 0, 0},      //L'    3
            {0, 0, -1.0f},      //F     4
            {0, 0, 1.0f},       //F'    5
            {0, 0, 1.0f},       //B     6
            {0, 0, -1.0f},      //B'    7
            {0, -1.0f, 0},      //U     8
            {0, 1.0f, 0},       //U'    9
            {0, 1.0f, 0},       //D     10
            {0, -1.0f, 0},      //D'    11
            {1.0f, 0, 0},       //M     12
            {-1.0f, 0, 0},      //M'    13
            {0, 1.0f, 0},       //E     14
            {0, -1.0f, 0},      //E'    15
            {0, 0, -1.0f},      //S     16
            {0, 0, 1.0f},       //S'    17
    };


        static void y(){
            int[] intTemp = Arrays.copyOf(spotsToChange[0], 9);
            spotsToChange[0] = spotsToChange[6];
            spotsToChange[6] = spotsToChange[2];
            spotsToChange[2] = spotsToChange[4];
            spotsToChange[4] = Arrays.copyOf(intTemp, intTemp.length);
            intTemp = Arrays.copyOf(spotsToChange[1], spotsToChange[1].length);
            spotsToChange[1] = spotsToChange[7];
            spotsToChange[7] = spotsToChange[3];
            spotsToChange[3] = spotsToChange[5];
            spotsToChange[5] = Arrays.copyOf(intTemp, intTemp.length);
            intTemp = Arrays.copyOf(spotsToChange[12], spotsToChange[12].length);
            spotsToChange[12] = spotsToChange[16];
            spotsToChange[16] = spotsToChange[13];
            spotsToChange[13] = spotsToChange[17];
            spotsToChange[17] = Arrays.copyOf(intTemp, intTemp.length);

            float[] floatTemp = Arrays.copyOf(axisData[0], axisData[0].length);
            axisData[0] = axisData[6];
            axisData[6] = axisData[2];
            axisData[2] = axisData[4];
            axisData[4] = Arrays.copyOf(floatTemp, floatTemp.length);
            floatTemp = Arrays.copyOf(axisData[1], axisData[1].length);
            axisData[1] = axisData[7];
            axisData[7] = axisData[3];
            axisData[3] = axisData[5];
            axisData[5] = Arrays.copyOf(floatTemp, floatTemp.length);
            floatTemp = Arrays.copyOf(axisData[12], axisData[12].length);
            axisData[12] = axisData[16];
            axisData[16] = axisData[13];
            axisData[13] = axisData[17];
            axisData[17] = Arrays.copyOf(floatTemp, floatTemp.length);

        }

        static void yp(){
            int[] intTemp = Arrays.copyOf(spotsToChange[0], 9);
            spotsToChange[0] = spotsToChange[4];
            spotsToChange[4] = spotsToChange[2];
            spotsToChange[2] = spotsToChange[6];
            spotsToChange[6] = Arrays.copyOf(intTemp, intTemp.length);
            intTemp = Arrays.copyOf(spotsToChange[1], spotsToChange[1].length);
            spotsToChange[1] = spotsToChange[5];
            spotsToChange[5] = spotsToChange[3];
            spotsToChange[3] = spotsToChange[7];
            spotsToChange[7] = Arrays.copyOf(intTemp, intTemp.length);
            intTemp = Arrays.copyOf(spotsToChange[12], spotsToChange[12].length);
            spotsToChange[12] = spotsToChange[17];
            spotsToChange[17] = spotsToChange[13];
            spotsToChange[13] = spotsToChange[16];
            spotsToChange[16] = Arrays.copyOf(intTemp, intTemp.length);

            float[] floatTemp = Arrays.copyOf(axisData[0], axisData[0].length);
            axisData[0] = axisData[4];
            axisData[4] = axisData[2];
            axisData[2] = axisData[6];
            axisData[6] = Arrays.copyOf(floatTemp, floatTemp.length);
            floatTemp = Arrays.copyOf(axisData[1], axisData[1].length);
            axisData[1] = axisData[5];
            axisData[5] = axisData[3];
            axisData[3] = axisData[7];
            axisData[7] = Arrays.copyOf(floatTemp, floatTemp.length);
            floatTemp = Arrays.copyOf(axisData[12], axisData[12].length);
            axisData[12] = axisData[17];
            axisData[17] = axisData[13];
            axisData[13] = axisData[16];
            axisData[16] = Arrays.copyOf(floatTemp, floatTemp.length);

        }

    static void x(){
        int[] intTemp = Arrays.copyOf(spotsToChange[4], 9);
        spotsToChange[4] = spotsToChange[10];
        spotsToChange[10] = spotsToChange[6];
        spotsToChange[6] = spotsToChange[8];
        spotsToChange[8] = Arrays.copyOf(intTemp, intTemp.length);
        intTemp = Arrays.copyOf(spotsToChange[5], spotsToChange[5].length);
        spotsToChange[5] = spotsToChange[11];
        spotsToChange[11] = spotsToChange[7];
        spotsToChange[7] = spotsToChange[9];
        spotsToChange[9] = Arrays.copyOf(intTemp, intTemp.length);
        intTemp = Arrays.copyOf(spotsToChange[14], spotsToChange[14].length);
        spotsToChange[14] = spotsToChange[17];
        spotsToChange[17] = spotsToChange[15];
        spotsToChange[15] = spotsToChange[16];
        spotsToChange[16] = Arrays.copyOf(intTemp, intTemp.length);

        float[] floatTemp = Arrays.copyOf(axisData[4], axisData[4].length);
        axisData[4] = axisData[10];
        axisData[10] = axisData[6];
        axisData[6] = axisData[8];
        axisData[8] = Arrays.copyOf(floatTemp, floatTemp.length);
        floatTemp = Arrays.copyOf(axisData[5], axisData[5].length);
        axisData[5] = axisData[11];
        axisData[11] = axisData[7];
        axisData[7] = axisData[9];
        axisData[9] = Arrays.copyOf(floatTemp, floatTemp.length);
        floatTemp = Arrays.copyOf(axisData[14], axisData[14].length);
        axisData[14] = axisData[17];
        axisData[17] = axisData[15];
        axisData[15] = axisData[16];
        axisData[16] = Arrays.copyOf(floatTemp, floatTemp.length);
    }

    static void xp(){
        int[] intTemp = Arrays.copyOf(spotsToChange[4], 9);
        spotsToChange[4] = spotsToChange[8];
        spotsToChange[8] = spotsToChange[6];
        spotsToChange[6] = spotsToChange[10];
        spotsToChange[10] = Arrays.copyOf(intTemp, intTemp.length);
        intTemp = Arrays.copyOf(spotsToChange[5], spotsToChange[5].length);
        spotsToChange[5] = spotsToChange[9];
        spotsToChange[9] = spotsToChange[7];
        spotsToChange[7] = spotsToChange[11];
        spotsToChange[11] = Arrays.copyOf(intTemp, intTemp.length);
        intTemp = Arrays.copyOf(spotsToChange[14], spotsToChange[14].length);
        spotsToChange[14] = spotsToChange[16];
        spotsToChange[16] = spotsToChange[15];
        spotsToChange[15] = spotsToChange[17];
        spotsToChange[17] = Arrays.copyOf(intTemp, intTemp.length);

        float[] floatTemp = Arrays.copyOf(axisData[4], axisData[4].length);
        axisData[4] = axisData[8];
        axisData[8] = axisData[6];
        axisData[6] = axisData[10];
        axisData[10] = Arrays.copyOf(floatTemp, floatTemp.length);
        floatTemp = Arrays.copyOf(axisData[5], axisData[5].length);
        axisData[5] = axisData[9];
        axisData[9] = axisData[7];
        axisData[7] = axisData[11];
        axisData[11] = Arrays.copyOf(floatTemp, floatTemp.length);
        floatTemp = Arrays.copyOf(axisData[14], axisData[14].length);
        axisData[14] = axisData[16];
        axisData[16] = axisData[15];
        axisData[15] = axisData[17];
        axisData[17] = Arrays.copyOf(floatTemp, floatTemp.length);
    }

    static void z(){
        int[] intTemp = Arrays.copyOf(spotsToChange[0], 9);
        spotsToChange[0] = spotsToChange[8];
        spotsToChange[8] = spotsToChange[2];
        spotsToChange[2] = spotsToChange[10];
        spotsToChange[10] = Arrays.copyOf(intTemp, intTemp.length);
        intTemp = Arrays.copyOf(spotsToChange[1], spotsToChange[1].length);
        spotsToChange[1] = spotsToChange[9];
        spotsToChange[9] = spotsToChange[3];
        spotsToChange[3] = spotsToChange[11];
        spotsToChange[11] = Arrays.copyOf(intTemp, intTemp.length);
        intTemp = Arrays.copyOf(spotsToChange[12], spotsToChange[12].length);
        spotsToChange[12] = spotsToChange[14];
        spotsToChange[14] = spotsToChange[13];
        spotsToChange[13] = spotsToChange[15];
        spotsToChange[15] = Arrays.copyOf(intTemp, intTemp.length);

        float[] floatTemp = Arrays.copyOf(axisData[0], axisData[0].length);
        axisData[0] = axisData[8];
        axisData[8] = axisData[2];
        axisData[2] = axisData[10];
        axisData[10] = Arrays.copyOf(floatTemp, floatTemp.length);
        floatTemp = Arrays.copyOf(axisData[1], axisData[1].length);
        axisData[1] = axisData[9];
        axisData[9] = axisData[3];
        axisData[3] = axisData[11];
        axisData[11] = Arrays.copyOf(floatTemp, floatTemp.length);
        floatTemp = Arrays.copyOf(axisData[12], axisData[12].length);
        axisData[12] = axisData[14];
        axisData[14] = axisData[13];
        axisData[13] = axisData[15];
        axisData[15] = Arrays.copyOf(floatTemp, floatTemp.length);
    }

    static void zp(){
        int[] intTemp = Arrays.copyOf(spotsToChange[0], 9);
        spotsToChange[0] = spotsToChange[10];
        spotsToChange[10] = spotsToChange[2];
        spotsToChange[2] = spotsToChange[8];
        spotsToChange[8] = Arrays.copyOf(intTemp, intTemp.length);
        intTemp = Arrays.copyOf(spotsToChange[1], spotsToChange[1].length);
        spotsToChange[1] = spotsToChange[11];
        spotsToChange[11] = spotsToChange[3];
        spotsToChange[3] = spotsToChange[9];
        spotsToChange[9] = Arrays.copyOf(intTemp, intTemp.length);
        intTemp = Arrays.copyOf(spotsToChange[12], spotsToChange[12].length);
        spotsToChange[12] = spotsToChange[15];
        spotsToChange[15] = spotsToChange[13];
        spotsToChange[13] = spotsToChange[14];
        spotsToChange[14] = Arrays.copyOf(intTemp, intTemp.length);

        float[] floatTemp = Arrays.copyOf(axisData[0], axisData[0].length);
        axisData[0] = axisData[10];
        axisData[10] = axisData[2];
        axisData[2] = axisData[8];
        axisData[8] = Arrays.copyOf(floatTemp, floatTemp.length);
        floatTemp = Arrays.copyOf(axisData[1], axisData[1].length);
        axisData[1] = axisData[11];
        axisData[11] = axisData[3];
        axisData[3] = axisData[9];
        axisData[9] = Arrays.copyOf(floatTemp, floatTemp.length);
        floatTemp = Arrays.copyOf(axisData[12], axisData[12].length);
        axisData[12] = axisData[15];
        axisData[15] = axisData[13];
        axisData[13] = axisData[14];
        axisData[14] = Arrays.copyOf(floatTemp, floatTemp.length);
    }

        static void reset(){
            for(int i = 0; i < axisData.length; i++){
                axisData[i] = Arrays.copyOf(originalaxisData[i], originalaxisData[i].length);
            }
            for(int i = 0; i < spotsToChange.length; i++){
                spotsToChange[i] = Arrays.copyOf(originalSpotsToChange[i], originalSpotsToChange[i].length);
            }
        }

    static  int[][] originalSpotsToChange = {
            {20, 26, 8, 2, 14, 11, 23, 17, 5},      //R     0
            {20, 2, 8, 26, 14, 11, 5, 17, 23},      //R'    1
            {18, 0, 6, 24, 12, 9, 3, 15, 21},       //L     2
            {18, 24, 6, 0, 12, 9, 21, 15, 3},       //L'    3
            {0, 2, 8, 6, 4, 1, 5, 7, 3},            //F     4
            {0, 6, 8, 2, 4, 1, 3, 7, 5},            //F'    5
            {18, 24, 26, 20, 22, 19, 21, 25, 23},   //B     6
            {18, 20, 26, 24, 22, 19, 23, 25, 21},   //B'    7
            {20, 2, 0, 18, 10, 19, 11, 1, 9},       //U     8
            {20, 18, 0, 2, 10, 19, 9, 1, 11},       //U'    9
            {24, 6, 8, 26, 16, 25, 15, 7, 17},      //D     10
            {24, 26, 8, 6, 16, 25, 17, 7, 15},      //D'    11
            {10, 4, 16, 22, 13, 1, 7, 25, 19},      //M     12
            {10, 22, 16, 4, 13, 1, 19, 25, 7},      //M'    13
            {21, 3, 5, 23, 13, 22, 12, 4, 14},      //E     14
            {21, 23, 5, 3, 13, 22, 14, 4, 12},      //E'    15
            {9, 11, 17, 15, 13, 10, 14, 16, 12},    //S     16
            {9, 15, 17, 11, 13, 10, 12, 16, 14},    //S'    17
    };

    static float[][] originalaxisData = {
            {-1.0f, 0, 0},      //R     0
            {1.0f, 0, 0},       //R'    1
            {1.0f, 0, 0},       //L     2
            {-1.0f, 0, 0},      //L'    3
            {0, 0, -1.0f},      //F     4
            {0, 0, 1.0f},       //F'    5
            {0, 0, 1.0f},       //B     6
            {0, 0, -1.0f},      //B'    7
            {0, -1.0f, 0},      //U     8
            {0, 1.0f, 0},       //U'    9
            {0, 1.0f, 0},       //D     10
            {0, -1.0f, 0},      //D'    11
            {1.0f, 0, 0},       //M     12
            {-1.0f, 0, 0},      //M'    13
            {0, 1.0f, 0},       //E     14
            {0, -1.0f, 0},      //E'    15
            {0, 0, 1.0f},       //S     16
            {0, 0, -1.0f},      //S'    17
    };

    static int[] clearCube = {
            //front wall
            23, 11, 19,
            17, 5, 15,
            25, 12 ,21,
            //mid wall
            9, 3, 7,
            2, 0, 1,
            10, 4, 8,
            //back wall
            24, 13, 20,
            18, 6, 16,
            26, 14, 22
    };

    static float[][][] colors ={
            {
                    BLACK, //front
                    BLACK, //left
                    BLACK, //top
                    BLACK, //right
                    BLACK, //back
                    BLACK, //bottom
            },

            //------------------------------

            {BLACK, BLACK, BLACK, GREEN, BLACK, BLACK,},
            {BLACK, BLUE, BLACK, BLACK, BLACK, BLACK,},
            {BLACK, BLACK, YELLOW, BLACK, BLACK, BLACK,},
            {BLACK, BLACK, BLACK, BLACK, BLACK, WHITE,},
            {RED, BLACK, BLACK, BLACK, BLACK, BLACK,},
            {BLACK, BLACK, BLACK, BLACK, ORANGE, BLACK,},

            //--------------------------------

            {BLACK, BLACK, YELLOW, GREEN, BLACK, BLACK,},
            {BLACK, BLACK, BLACK, GREEN, BLACK, WHITE,},
            {BLACK, BLUE, YELLOW, BLACK, BLACK, BLACK,},
            {BLACK, BLUE, BLACK, BLACK, BLACK, WHITE,},

            //--------------------------------

            {RED, BLACK, YELLOW, BLACK, BLACK, BLACK,},
            {RED, BLACK, BLACK, BLACK, BLACK, WHITE,},
            {BLACK, BLACK, YELLOW, BLACK, ORANGE, BLACK,},
            {BLACK, BLACK, BLACK, BLACK, ORANGE, WHITE,},

            //---------------------------------------

            {RED, BLACK, BLACK, GREEN, BLACK, BLACK,},
            {BLACK, BLACK, BLACK, GREEN, ORANGE, BLACK,},
            {RED, BLUE, BLACK, BLACK, BLACK, BLACK,},
            {BLACK, BLUE, BLACK, BLACK, ORANGE, BLACK,},

            //----------------------------------------

            {RED, BLACK, YELLOW, GREEN, BLACK, BLACK,},
            {BLACK, BLACK, YELLOW, GREEN, ORANGE, BLACK,},
            {RED, BLACK, BLACK, GREEN, BLACK, WHITE,},
            {BLACK, BLACK, BLACK, GREEN, ORANGE, WHITE,},

            //--------------------------------------

            {RED, BLUE, YELLOW, BLACK, BLACK, BLACK,},
            {BLACK, BLUE, YELLOW, BLACK, ORANGE, BLACK,},
            {RED, BLUE, BLACK, BLACK, BLACK, WHITE,},
            {BLACK, BLUE, BLACK, BLACK, ORANGE, WHITE,},

    };
}
