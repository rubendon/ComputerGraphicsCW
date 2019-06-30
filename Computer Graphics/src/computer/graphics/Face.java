//Ruben Donovan
//Faces Class
//28.11.2017
package computer.graphics;

import java.awt.Color;

public class Face {

    public int[] index;
    /* list of indices of vertices of the face */

    public Color color;

    public Face(int[] i, Color c) {
        index = i;
        color = c;
    }

    public String toString() {
        String ret = "\nIndexes:";
        for (int i = 0; i < index.length; i++) {
            ret = ret + "" + index[i];
        }
        ret = ret + "\nColor: " + color.toString();
        return ret;
    }
}
