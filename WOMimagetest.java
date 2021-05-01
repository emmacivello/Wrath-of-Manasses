package wrathOfManasses;

import javax.swing.*;

import java.awt.*;

import java.io.*;
import javax.imageio.ImageIO;

public class WOMimagetest {

	private Image ruthard;
	private Image vertaine;
	
	private String pnRuth;
	private String pnVert;
	
	public WOMimagetest() {
		
		ruthard = null;
		vertaine = null;
		
		pnRuth = "Ruthard.png";
		pnVert = "Vertaine.png";
		
	}
	
	public static void main(String[] args) {
		
		WOMimagetest wit = new WOMimagetest();
		wit.run();
		
	}
	
	private void run() {
		
		getMyImage();
        layout();
        
	}
	
	public void getMyImage() {
        try {
            ruthard = ImageIO.read(new File(pnRuth));
            vertaine = ImageIO.read(new File(pnVert));
        }
        catch(IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }
    
    public void layout()
    {
        
        JFrame frame = new JFrame("Professor Malita, Please Give Us An A");
        
        frame.setSize(600, 400);                
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setLocation(50, 100);
        frame.setResizable(false);
        WOMtestpanel wtp = new WOMtestpanel();  
        frame.add(wtp);        

        frame.setVisible(true);    
        
    }
	
    
    class WOMtestpanel extends JPanel {
    	
    	public WOMtestpanel() {
            setBackground(Color.WHITE); 
        }
    	
    	public void paintComponent(Graphics g) {
    		
    		super.paintComponent(g);
    		
    		Font txtFont = new Font("Comic Sans MS", Font.PLAIN, 16);
    		
    		g.drawImage(vertaine, 180, 40, -150, 200, this);
            g.drawImage(ruthard, 370, 40, 180, 200, this);
            
            g.setColor(Color.DARK_GRAY);
            g.drawRoundRect(10, 240, 560, 110, 10, 10);
            
            g.setColor(new Color(121, 78, 168));
            g.setFont(txtFont);
            g.drawString("RUTHARD: Apple. ", 20, 260);
            g.setColor(new Color(135, 24, 56));
            g.drawString("VERTAINE: Orange. ", 20, 290);
            g.setColor(new Color(121, 78, 168));
            g.drawString("RUTHARD: Banana. ", 20, 320);
            
    	}
    	
    }
    
}

