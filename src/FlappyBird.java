import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int boardwidth = 360;
    int boardheight = 640;

    //Images
    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    //Bird

    int birdX = boardwidth/8;
    int birdY = boardheight/2;
    int birdWidth = 34;
    int birdHeight = 24;

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }




    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -9;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    class Bird{
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image img;

        Bird(Image img){
            this.img = img;
        }
    }

    //logic
    Bird bird;
    int velocityY = 0;
    int gravity = 1;

    Timer gameLoop;




    FlappyBird() {
        setPreferredSize(new Dimension(boardwidth, boardheight));
        //setBackground(Color.blue);

        setFocusable(true);
        addKeyListener(this);


        backgroundImg = new ImageIcon(getClass().getResource("icons/flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("icons/flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("icons/toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("icons/bottompipe.png")).getImage();

        bird = new Bird(birdImg);

        //game timer
        gameLoop = new Timer(1000/60,this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g) {

        //bg
        g.drawImage(backgroundImg, 0, 0, boardwidth, boardheight, null);
        //bird
        g.drawImage(birdImg, bird.x, bird.y, bird.width, bird.height, null);
    }
    public void move() {
        //bird
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);
    }
}
