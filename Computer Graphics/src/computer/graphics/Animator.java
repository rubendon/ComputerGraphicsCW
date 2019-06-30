//Ruben Donovan
//Animator Class
//28.11.2017
package computer.graphics;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyCode;

public class Animator extends JFrame implements KeyListener {

    public double rotateX = 0.1;
    public double rotateY = 0.1;
    public double rotateZ = 0.1;
    public double rotateXtemp = 0.1;
    public double rotateYtemp = 0.1;
    public double rotateZtemp = 0.1;
    public boolean stopped = false;

    public Animator() throws FileNotFoundException {
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.white);
        setVisible(true);
        addKeyListener(this);
        message();
    }

    public void message() {
        JOptionPane.showMessageDialog(this, "INSTRUCTIONS - \n\nRotations - \nspeed up x - f    ||    slow down x - c\nspeed up y - g    ||    slow down y - v\nspeed up z - h    ||    slow down z - b\n\n"
                + "Visuals -\nParrallel Camera - 1\nPerspective Camera - 2\nSkeleton Mode - s\nShow midpoints - m\n\n"
                + "Show Real World Position - w    ||    Show Plane Position - p\n\n"
                + "PAUSE - SPACE BAR\n\n"
                + "Show instructions - 3");
    }

    protected void animate(Graphics g) {

    }

    protected final void loop() {
        while (true) {
            image = new BufferedImage(HEIGHT, WIDTH, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = image.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.black);
            // g2.fillRect(0,200,this.getHeight()-HEIGHT,this.getWidth()-WIDTH);
            g2.fillRect(0, 200, 10, 10);

            animate(g2);

            ((Graphics2D) getGraphics()).drawImage(image, 0, 0, null);
            paint(getGraphics());
            try {
                Thread.sleep(INTERVAL);
            } catch (InterruptedException e) {
            }
        }
    }

    public final void paint(Graphics g) {
    }

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    private static final int INTERVAL = 100;
    private BufferedImage image;
    static Animator an;

    protected Camera camera;
    public Scene s;

    public static void main(String[] args) throws FileNotFoundException {
        an = new ParallelAnimator();
        an.loop();
        instructions();
    }

    public static void instructions() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyChar() == '1') {
            //use camera initializer
            camera = new Camera(-5, 5, -5, 5);
        }
        if (e.getKeyChar() == '2') {
            //use perspective camera 
            camera = new PerspectiveCamera(-5, 5, -5, 5);
            ((PerspectiveCamera) camera).setupUVN(new Point3D(0, 0, 1), new Vector3D(0, 0, 1), new Vector3D(0, 1, 0));
            ((PerspectiveCamera) camera).setupCOP(new Point3D(0, 0, 5));
        }
        if (e.getKeyChar() == 's') {
            s.skeleton = !s.skeleton;
        }
        if (e.getKeyChar() == 'm') {
            s.midpoints = !s.midpoints;
        }
        if (e.getKeyChar() == 'q') {
            camera.setCop(new Point3D(camera.cop.x, camera.cop.y, camera.cop.z + 4));
        }
        if (e.getKeyChar() == 'a') {
            camera.setCop(new Point3D(camera.cop.x, camera.cop.y, camera.cop.z - 4));
        }
        if (e.getKeyChar() == 'e') {
            camera.setCop(new Point3D(camera.cop.x + 4, camera.cop.y, camera.cop.z));
        }
        if (e.getKeyChar() == 'd') {
            camera.setCop(new Point3D(camera.cop.x - 4, camera.cop.y, camera.cop.z));
        }
        if (e.getKeyChar() == 'i') {
            camera.setCop(new Point3D(camera.cop.x, camera.cop.y + 4, camera.cop.z));
        }
        if (e.getKeyChar() == 'k') {
            camera.setCop(new Point3D(camera.cop.x, camera.cop.y - 4, camera.cop.z));
        }
        if (e.getKeyChar() == 'f') {
            rotateX = rotateX + 0.05;
        }
        if (e.getKeyChar() == 'g') {
            rotateX = rotateX + 0.05;
        }
        if (e.getKeyChar() == 'h') {
            rotateX = rotateX + 0.05;
        }
        if (e.getKeyChar() == 'c') {
            rotateX = rotateX - 0.05;
        }
        if (e.getKeyChar() == 'v') {
            rotateX = rotateX - 0.05;
        }
        if (e.getKeyChar() == 'b') {
            rotateX = rotateX - 0.05;
        }
        if (e.getKeyChar() == 'w') {
            if (s.showPlane != true) {
                s.showWorld = !s.showWorld;
            }
        }
        if (e.getKeyChar() == 'p') {
            if (s.showWorld != true) {
                s.showPlane = !s.showPlane;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            System.out.println("here");
            if (stopped == false) {
                rotateXtemp = rotateX;
                rotateYtemp = rotateY;
                rotateZtemp = rotateZ;
                rotateX = 0;
                rotateY = 0;
                rotateZ = 0;
                stopped = true;
            } else {
                rotateX = rotateXtemp;
                rotateY = rotateYtemp;
                rotateZ = rotateZtemp;
                stopped = false;
            }
        }
        if (e.getKeyChar() == '3') {
            message();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
