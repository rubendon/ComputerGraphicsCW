//Ruben Donovan
//Point3D Class
//28.11.2017
package computer.graphics;

import static java.lang.Math.*;
//3D point

public class Point3D {

    public double x, y, z;

    public Point3D(double X, double Y, double Z) {
        //set x y and z
        x = X;
        y = Y;
        z = Z;
    }

    public Point3D transform(Matrix m1) {

        //multiply each row by x, y and z. then by the multiplier w.
        double[][] m = m1.m;
        double xw, yw, zw, w;
        xw = ((x * m[0][0]) + (y * m[0][1]) + (z * m[0][2])) / m[3][3];
        yw = ((x * m[1][0]) + (y * m[1][1]) + (z * m[1][2])) / m[3][3];
        zw = ((x * m[2][0]) + (y * m[2][1]) + (z * m[2][2])) / m[3][3];

        //return new point
        return new Point3D(xw, yw, zw);
    }

    public Vector3D vector(Point3D p) {

        //return new vector between the 2 points
        //dx = x2-x1 sy = y2-y1
        double dx = this.x - p.x, dy = this.y - p.y, dz = this.z - z;
        return new Vector3D(dx, dy, dz);
    }

    public static Vector3D faceNormal(Point3D p1, Point3D p2, Point3D p3) {
        //ai + bj + ck
        //get vector between p2 and p1
        Vector3D v1 = p2.vector(p1);

        //get vector between p3 and p1
        Vector3D v2 = p3.vector(p1);
        //find the cross product of the 2 and return
        Vector3D n = v1.crossProduct(v2);
        return n;
    }

    public static boolean isFrontFace(Point3D p1, Point3D p2, Point3D p3, Vector3D vpn) {
        //find face normal vector of p1, p2 and p3
        Vector3D n = faceNormal(p1, p2, p3);
        double dot = n.dotProduct(vpn);
        //find dot product, return true if greater than 0
        return dot >= 0;
    }

    public double distance(Point3D p) {
        //find cx, cy, cz
        double cx = (p.x - x);
        double cy = (p.y - y);
        double cz = (p.z - z);
        //square the numbers, then add them
        double sq = Math.pow(cx, 2) + Math.pow(cy, 2) + Math.pow(cz, 2);
        //return the square root of the result
        return Math.sqrt(sq);
    }

    @Override
    public String toString() {
        //return point
        String ret = "3D point is {" + x + ", " + y + ", " + z + "}";
        return ret;
    }
}
