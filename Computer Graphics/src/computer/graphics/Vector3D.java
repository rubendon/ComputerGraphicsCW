//Ruben Donovan
//Vector3D Class
//28.11.2017
package computer.graphics;

import static java.lang.Math.*;

/* Now you can use math functions without the Math. prefix */

public class Vector3D implements Cloneable {

    public double x, y, z;

    public Vector3D(double X, double Y, double Z) {
        //initiate x y z
        x = X;
        y = Y;
        z = Z;
    }

    @Override
    public String toString() {
        //return x y z 
        String ret = "Vector is {" + x + ", " + y + ", " + z + "}";
        return ret;
    }

    public Point3D point() {
        return new Point3D(x, y, z);
    }

    @Override
    public Vector3D clone() throws CloneNotSupportedException {
        //clone vector and return
        Vector3D ret = (Vector3D) super.clone();
        return ret;
    }

    public Vector3D transform(Matrix m) {
        //makes new point using vector position
        Point3D p = new Point3D(x, y, z);
        //transform point by matrix
        Point3D p1 = p.transform(m);
        //return new vector with transformed point
        return new Vector3D(p1.x, p1.y, p1.z);
    }

    public double L2norm() {
        //add square of x y z
        double sq = (x * x) + (y * y) + (z * z);
        //return square root
        double ret = sqrt(sq);
        return ret;
    }

    public double dotProduct(Vector3D v) {
        //returns product of v and vector
        double ret = (x * v.x) + (y * v.y) + (z * v.z);
        return ret;
    }

    public Vector3D crossProduct(Vector3D v) {
        double cx, cy, cz;
        //x = y1*z2 - z1*y2
        //y = z1*x2 - x1*z2
        //z = x1*y2 - y1*x2

        cx = (y * v.z) - (z * v.y);
        cy = (z * v.x) - (x * v.z);
        cz = (x * v.y) - (y * v.x);
        //return as new vector
        Vector3D ret = new Vector3D(cx, cy, cz);
        return ret;
    }

    public void normalize() {
        //find norm
        double norm = L2norm();
        if (norm == 0) {
            return;
        }
        //divide x y z by norm
        x = x / norm;
        y = y / norm;
        z = z / norm;
    }
}
