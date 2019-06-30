//Ruben Donovan
//GObject Class
//28.11.2017
package computer.graphics;

import java.util.Scanner;
import java.io.*;

import java.awt.Color;

public class GObject {

    public Point3D[] vertex;
    /* list of vertices of the model */

    public Face[] face;

    /* list of faces of the model */

    public GObject(Point3D[] v, Face[] f) {
        //initiate vertex and face
        vertex = v;
        face = f;
    }

    public GObject(String fileName) throws FileNotFoundException {
        //make scanner
        File file = new File("./src/computer/graphics/" + fileName);
        Scanner s = new Scanner(file);
        //loop for num of vertices
        int nv = s.nextInt();
        Point3D[] vlist = new Point3D[nv];
        for (int i = 0; i < nv; i++) {
            double d1, d2, d3;
            d1 = s.nextDouble();
            d2 = s.nextDouble();
            d3 = s.nextDouble();
            vlist[i] = new Point3D(d1, d2, d3);
        }
        s.nextLine();
        //loop for num of faces
        int nf = s.nextInt();
        Face[] flist = new Face[nf];
        for (int i = 0; i < nf; i++) {
            int ni = s.nextInt();
            int[] ilist = new int[ni];
            for (int j = 0; j < ni; j++) {
                ilist[j] = s.nextInt();
            }
            double[] clist = new double[3];
            for (int j = 0; j < 3; j++) {
                clist[j] = s.nextDouble();
            }
            float f1, f2, f3;
            f1 = (float) clist[0];
            f2 = (float) clist[1];
            f3 = (float) clist[2];
            Color col = new Color(f1, f2, f3);
            flist[i] = new Face(ilist, col);
        }
        vertex = vlist;
        face = flist;
    }

    public void transform(Matrix m) {
        //loop through vertex
        for (int i = 0; i < vertex.length; i++) {
            //transform each point
            vertex[i] = vertex[i].transform(m);
        }
    }

    public String toString() {
        //return all vertex and faces
        String ret = "";
        for (int i = 0; i < face.length; i++) {
            ret = ret + face[i].toString();
        }
        for (int i = 0; i < vertex.length; i++) {
            ret = ret + "\n" + vertex[i].toString();
        }
        return ret;
    }
}
