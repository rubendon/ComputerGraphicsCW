//Ruben Donovan
//Camera Class
//28.11.2017
package computer.graphics;

public class Camera {

    public Point3D cop = new Point3D(0, 0, 3);

    public Vector3D getVPN() {
        Vector3D v1 = new Vector3D(1, 0, 0);
        Vector3D v2 = new Vector3D(0, 1, 0);
        return v1.crossProduct(v2);
    }

    protected Point3D cameraTransform(final Point3D p) {
        return p;
    }

    protected Point3D projectionTransform(final Point3D p) {
        return new Point3D(p.x, p.y, p.z);
    }

    private final Point3D viewportTransform(final Point3D p) {
        double x, y, z;
        z = 0;
        x = ax + (bx * p.x);
        y = ay + (by * p.y);
        return new Point3D(x, y, z);
    }

    public final Point3D project(final Point3D p) {
        Point3D temp = cameraTransform(p);
        temp = projectionTransform(temp);
        return viewportTransform(temp);
    }

    public Camera(double xmin_, double xmax_, double ymin_, double ymax_) {
        xmin = xmin_;
        ymin = ymin_;
        xmax = xmax_;
        ymax = ymax_;
    }

    public void setViewport(int width, int height) {

        double umin, umax, vmin, vmax, dx, dy, du, dv;
        umin = 0;
        umax = width;
        vmin = 0;
        vmax = height;
        dx = xmax - xmin;
        dy = ymax - ymin;
        du = umax - umin;
        dv = vmax - vmin;
        bx = (du / dx);
        ax = umin - (bx * xmin);
        by = (dv / dy);
        ay = vmin - (by * ymin);

        /*calculate ax, bx, ay, by*/
    }

    public void setCop(Point3D _cop) {
        cop = _cop;
    }

    public String toString() {
        String ret = "(xmin, xmax, ymin, ymax) = (" + xmin + ", " + xmax + ", " + ymin + ", " + ymax + ")";
        ret = ret + "\n(ax, bx, ay, by) = (" + ax + ", " + bx + ", " + ay + ". " + by + ")";
        ret = ret + getVPN().x + " and " + getVPN().y;
        return ret;

    }

    private double xmin, xmax, ymin, ymax;
    private double fcp, bcp;  //NOT USED: front & back clippling planes
    private double ax, bx, ay, by;
}
