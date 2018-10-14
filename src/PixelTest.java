import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PixelTest {
    private JFrame window;
    private BufferedImage img;
    private Canvas canvas;
    private Renderer renderer;


    public PixelTest(){
        window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(1200, 800);
        window.setLocationRelativeTo(null);
        window.setTitle("Úsečka a n-úhelník");
        //inicializace image, nastavení rozměrů
        img = new BufferedImage(1200,800, BufferedImage.TYPE_INT_RGB);

        // inicializtace plátna, do kterého budeme kreslit img
        canvas = new Canvas();


        window.add(canvas);
        window.setVisible(true);

        renderer = new Renderer(img, canvas);

        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Pressed");
                renderer.body.add(new Bod(e.getX(),e.getY()));
                renderer.drawPixel(e.getX(),e.getY(),0x00ffff);
              //  renderer.setX1(e.getX());
              //  renderer.setY1(e.getY());

                //points.add(e.getX());
                //points.add(e.getY());
                //renderer.drawPolygon(points);
            }

        });

        canvas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                renderer.clear();
                int b = renderer.body.size();
                Bod pomocnaZacatek = renderer.body.get(b - 1);
                renderer.drawDDA(pomocnaZacatek.getX1(), pomocnaZacatek.getY1(), e.getX(),e.getY(), 0x00ffff);
            }
        });

        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                renderer.body.add(new Bod(e.getX(),e.getY()));
                System.out.println("Clicked");
            }
        });

    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(PixelTest::new);
        // https://www.google.com/search?q=SwingUtilities.invokeLater
        // https://www.javamex.com/tutorials/threads/invokelater.shtml
        // https://www.google.com/search?q=java+double+colon
    }
}
