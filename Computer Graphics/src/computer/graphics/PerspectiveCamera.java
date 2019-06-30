//Ruben Donovan
//Perspective Camera Class
//28.11.2017
package computer.graphics;

import static java.lang.Math.abs;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PerspectiveCamera extends Camera {

    private Point3D vrp = new Point3D(0, 0, 1); // view reference point / origin
    private Vector3D vpn = new Vector3D(0, 0, 1), vuv = new Vector3D(0, 1, 0); // vpn (n) and view up (v)
    // private Point3D cop=new Point3D(0,0,3); //centre of projection
    private Matrix m = new Matrix(); // matrix for transformations

    {
        m.setIdentity();
    }

    public Vector3D getVPN() {
        return vpn;
    }

    public void setupUVN(Point3D vrp_, Vector3D vpn_, Vector3D vuv_) {
        //initiate variables
        vrp = vrp_;
        vpn = vpn_;
        vuv = vuv_;
    }

    @Override
    protected Point3D cameraTransform(final Point3D p) {
        //find n = vpn.norm
        Vector3D n = new Vector3D(vpn.x, vpn.y, vpn.z);
        n.normalize();
        //find u = (vuv x vpn).norm
        Vector3D u = vuv.crossProduct(vpn);
        u.normalize();
        //find v = n x u
        Vector3D v = n.crossProduct(u);

        //create array for looping
        Vector3D[] uvn = new Vector3D[3];
        uvn[0] = u;
        uvn[1] = v;
        uvn[2] = n;

        //new transform matrix
        double[][] t = new double[4][4];
        for (int i = 0; i < 3; i++) {

            //loop through and create transform matrix t
            double x = uvn[i].x;
            double y = uvn[i].y;
            double z = uvn[i].z;
            t[i][0] = x;
            t[i][1] = y;
            t[i][2] = z;
            t[i][3] = -((x * vrp.x) + (y * vrp.y) + (z * vrp.z));
        }
        t[3][0] = 0;
        t[3][1] = 0;
        t[3][2] = 0;
        t[3][3] = 1;

        //set tranfomation matrix to t
        m.m = t;

        //transform point p by matrix m and return new point
        Point3D ret = new Point3D(p.x, p.y, p.z);
        ret = p.transform(m);
        return ret;
    }

    protected Point3D projectionTransform(final Point3D p) {
        //cop is the starting point. 
        //p(x,y,z) is a point to be projected onto viewplane
        //DISTANCE d is difference between COP and centre, assuming vp is on centre
        Point3D centre = new Point3D(0, 0, 0);
        double d = cop.distance(centre);
        //xs = distance*X / z distance
        double xs = (d * p.x) / abs(p.z - cop.z);
        double ys = (d * p.y) / abs(p.z - cop.z);
        //z = distance
        double zs = d;
        return new Point3D(xs, ys, zs);

    }

    public PerspectiveCamera(double xmin_, double xmax_, double ymin_, double ymax_) {
        //initiate camera variables
        super(xmin_, xmax_, ymin_, ymax_);
    }

    public void setupCOP(Point3D cop_) {
        //initiate cop
        super.cop = cop_;
    }
}
