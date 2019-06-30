//Ruben Donovan
//Parallel Animator Class
//28.11.2017
package computer.graphics;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import javax.swing.*;
import static java.lang.Math.*;

public class ParallelAnimator extends Animator {

    private static final String[] files = {"./cube.dat", "./pyramid.dat"};

    public ParallelAnimator() throws FileNotFoundException {
        super();

        s = new Scene(files);
        setupCamera();
    }

    protected void setupCamera() {
        camera = new Camera(-5, 5, -5, 5);
    }

    protected void animate(Graphics g) {
        camera.setViewport(HEIGHT, WIDTH);

        if (g == null || s == null || camera == null) {
            return;
        }

        Matrix mX = new Matrix(), mY = new Matrix(), mZ = new Matrix();
        mX.setRotationX(rotateX);
        mY.setRotationY(rotateY);
        mZ.setRotationZ(rotateZ);
        s.transform(mZ.multiply(mY.multiply(mX)));
        s.draw(camera, g);
    }

}
