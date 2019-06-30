//Ruben Donovan
//Perspective Animator Class
//28.11.2017
package computer.graphics;

import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;

public class PerspectiveAnimator extends ParallelAnimator {

    public PerspectiveAnimator() throws FileNotFoundException {

    }

    @Override
    protected void setupCamera() {

    }

    public static void main(String[] args) throws FileNotFoundException {
        new PerspectiveAnimator().loop();
    }
}
