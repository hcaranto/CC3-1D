package pacmanactivity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.geom.Area;

/**
 *
 * @author m304user
 */
public class PacmanActivity extends Panel implements KeyListener{
    int x = 0;
    int y = 0;
    static Color color1 = new Color(238, 244, 66);
    int startangle = 30;
    int endangle = 300;
    
    int foodY = 250;
    int foodX = 250;
    
    int count = 1;
    int score = 0;
    
    Random rand = new Random();
    Rectangle pacmanAreaShape, foodAreaShape;
    
    PacmanActivity(){
        addKeyListener(this);
    }
    
    public void createGUI(){
        PacmanActivity panel = new PacmanActivity();
        panel.setBackground(new Color(45, 45, 42));
        
        JFrame frame = new JFrame();
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
    }
    
    public boolean eatFood(Rectangle pacman, Rectangle food){
        Area pacmanArea = new Area(pacman);
        Area foodArea = new Area(food);
        
        pacmanArea.intersect(foodArea);
        return !pacmanArea.isEmpty();
    }
    
    @Override
    public void paint (Graphics g){
        super.paint(g);
        
        Graphics2D g2d = (Graphics2D) g;
        
        g.setColor(Color.blue);
        foodAreaShape = new Rectangle(foodX+5,foodY+5, 23, 23);
        
        g.setColor(Color.RED);
        g.fillOval(foodX, foodY, 30, 30);
        
        pacmanAreaShape = new Rectangle(65+x,60+y,70,75);
        
        g.setColor(color1);
        g.fillArc(50+x, 50+y, 100, 100, startangle, endangle);
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Helvetica", Font.BOLD, 30));
        g.drawString("SCORE: " + score, 320, 50);   
        
        
    }
    
    
    @Override
    public void keyTyped(KeyEvent e){
        
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_UP  || key == KeyEvent.VK_W){
            startangle = 140;
            endangle = 260;
            
            count++;
            
            if (count%2 == 0){
                startangle = 360;
                endangle = 360;
            }
            if (y > -100){
                y -= 5;
            } else {
                y = 450;
            }
        }else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S){
            startangle = 300;
            endangle = 300;
            
            count++;
            
            if (count%2 == 0){
                startangle = 360;
                endangle = 360;
            }
            if (y < 450){
                y += 5;
            } else {
                y = -50;
            }
        }else if (key == KeyEvent.VK_LEFT  || key == KeyEvent.VK_A){
            startangle = 225;
            endangle = 290;
            
            count++;
            
            if (count%2 == 0){
                startangle = 360;
                endangle = 360;
            }
            if (x > -42){
                x -= 5;
            } else {
                x = -42;
            }
        } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D){
            startangle = 30;
            endangle = 300;
            
            count++;
            
            if (count%2 == 0){
                startangle = 360;
                endangle = 360;
            }
            if (x < 340){
                x += 5;
            } else {
                x = 340;
            }
        }
        
        if (eatFood(pacmanAreaShape, foodAreaShape)){
            foodX = rand.nextInt(400) + 50;
            foodY = rand.nextInt(400) + 50;
            score++;
        }
        
        System.out.println(count);
        
        repaint();
        
        if (score == 10){
            JOptionPane.showMessageDialog(null, "Congratulations! You won!");
            System.exit(0);
        }
        
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        
    }
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new PacmanActivity().createGUI();
        
    }
    
}