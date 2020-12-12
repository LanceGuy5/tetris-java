import java.awt.*;
import java.awt.image.BufferStrategy;

public class Tetris extends Canvas implements Runnable{

    Thread tetrisRunnable;
    boolean isRunning = false;

    public static final int WIDTH = 916;
    public static final int HEIGHT = 939;
    public static String name = "Tetris";

    public Tetris(){
        new Window(WIDTH, HEIGHT, name, this);
        tetrisRunnable = new Thread(this);
        start();
    }

    public synchronized void start(){
        if(isRunning) return;
        tetrisRunnable.start();
        isRunning = true;
    }

    public synchronized void stop(){
        if(!isRunning) return;
        try{
            tetrisRunnable.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        isRunning = false;
    }

    public void init(){

    }

    @Override
    public void run() {
        Thread init = new Thread(this::init);
        init.start();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                frames = 0;
                updates = 0;
            }
        }
    }

    public void tick(){

    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        //RENDER BETWEEN HERE

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        //AND HERE

        bs.show();
        g.dispose();
    }
}
