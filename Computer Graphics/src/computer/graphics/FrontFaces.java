//Ruben Donovan
//Front Faces Class
//28.11.2017
package computer.graphics;

import java.awt.Color;
import java.util.ArrayList;

public class FrontFaces {
    //make object of front faces for sorting
    //make available for drawing

    int[] xvalues; //xy list for all faces in obj
    int[] yvalues;
    Color color; //color for all faces in obj
    double distance;
    int faceLength;
    int xave;
    int yave;

    public FrontFaces(int[] xvals, int[] yvals, Color col, double dis, int len, int xav, int yav) {
        xvalues = xvals;
        yvalues = yvals;
        color = col;
        distance = dis;
        faceLength = len;
        xave = xav;
        yave = yav;
    }

    public Double getDistance() {
        return distance;
    }
}
