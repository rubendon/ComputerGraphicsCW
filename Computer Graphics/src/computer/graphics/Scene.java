//Ruben Donovan
//Scene Class
//28.11.2017
package computer.graphics;

import java.awt.*;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Scene {

    private GObject[] obj;
    public boolean midpoints = false;
    public boolean skeleton = false;
    public boolean showWorld = false;
    public boolean showPlane = false;

    public Scene(String[] fileName) throws FileNotFoundException {
        //reads all of g objects
        GObject[] set = new GObject[fileName.length];
        for (int i = 0; i < fileName.length; i++) {
            set[i] = new GObject(fileName[i]);
        }
        obj = set;
    }

    public void transform(Matrix m) {
        for (GObject obj1 : obj) {
            obj1.transform(m);
        }
    }

    public void draw(Camera c, Graphics g) {
        //get vpn
        Vector3D vpn = c.getVPN();
        Point3D cop = c.project(c.cop);

        ArrayList<FrontFaces> ff = new ArrayList<FrontFaces>();
        DecimalFormat df = new DecimalFormat("###.####");
        //loop through objects
        for (GObject object : obj) {
            //convert points to viewport

            Point3D[] points1 = new Point3D[object.vertex.length];
            Point3D[] points = new Point3D[object.vertex.length];
            for (int j = 0; j < object.vertex.length; j++) {
                points1[j] = object.vertex[j];
                points[j] = c.project(object.vertex[j]);
                if (showWorld == true) {
                    g.setColor(Color.WHITE);
                    String x = df.format(points1[j].x), y = df.format(points1[j].y), z = df.format(points1[j].z);
                    g.drawString("(" + x + ", " + y + ", " + z + ") ", (int) points[j].x - 20, (int) points[j].y);
                }
                if (showPlane == true) {
                    g.setColor(Color.WHITE);
                    String x = df.format(points[j].x), y = df.format(points[j].y), z = df.format(points[j].z);
                    g.drawString("(" + (int) points[j].x + ", " + (int) points[j].y + ") ", (int) points[j].x - 20, (int) points[j].y);
                }
            }

            //draw each face of this object
            for (Face face : object.face) {
                int indexLength = face.index.length;
                //x and y vals for drawing
                int[] xval = new int[indexLength]; //x values for polygons
                int[] yval = new int[indexLength]; //y values for polygons
                int[] xline = new int[indexLength]; //x values for lines - not frontface only
                int[] yline = new int[indexLength]; //y values for lines - not frontface only
                if (skeleton == true) {
                    for (int k = 0; k < indexLength; k++) {
                        xline[k] = (int) points[face.index[k]].x;;
                        yline[k] = (int) points[face.index[k]].y;
                    }
                    g.setColor(Color.white);
                    for (int i = 1; i < indexLength; i++) {
                        g.drawLine(xline[i - 1], yline[i - 1], xline[i], yline[i]);
                    }
                } else //check for front face
                if (Point3D.isFrontFace(points[face.index[0]], points[face.index[1]], points[face.index[2]], vpn)) {
                    int xave = 0, yave = 0;
                    int pxave = 0, pyave = 0;
                    //store front face points
                    for (int k = 0; k < indexLength; k++) {
                        //store front face
                        xval[k] = (int) points[face.index[k]].x;
                        yval[k] = (int) points[face.index[k]].y;
                        //store x and y for lines
                        xline[k] = xval[k];
                        yline[k] = yval[k];
                        //add averages for midpoint
                        xave += xval[k];
                        yave += yval[k];
                        pxave += xval[k];
                        pyave += yval[k];
                    }

                    //works out averages
                    xave /= indexLength;
                    yave /= indexLength;
                    pxave /= indexLength;
                    pyave /= indexLength;
                    //works out distance to cop
                    double distance = new Point3D(pxave, pyave, 0).distance(c.cop);

                    FrontFaces f = new FrontFaces(xval, yval, face.color, distance, indexLength, xave, yave);
                    ff.add(f);
                }
            }
        }
        //USE COLLECTIONS SORT TO COMPARE ALL FRONT FACES BY DISTANCE
        Collections.sort(ff, (FrontFaces item1, FrontFaces item2) -> item1.getDistance().compareTo(item2.getDistance()));
        //loop through front faces
        for (int i = ff.size() - 1; i >= 0; i--) {
            //draw all faces
            FrontFaces f1 = ff.get(i);
            g.setColor(f1.color);
            g.fillPolygon(f1.xvalues, f1.yvalues, f1.faceLength);
//                        g.setColor(Color.white);
//                        g.drawPolygon(f1.xvalues, f1.yvalues, f1.faceLength);
            if (midpoints == true) {
                //draws midpoint
                g.setColor(Color.white);
                g.drawOval(f1.xave, f1.yave, 5, 5);
            }
        }
    }

    @Override
    public String toString() {
        String ret = "Objects: \n\n";
        for (GObject obj1 : obj) {
            ret = ret + "NEW OBJECT" + obj1.toString() + "\n\n";
        }
        return ret;
    }
}
