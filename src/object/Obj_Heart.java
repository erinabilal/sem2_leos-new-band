package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Obj_Heart extends SuperObject{

	public Obj_Heart(GamePanel gp) {
		this.gp = gp;
		name = "Heart";
		
		try { 
			image = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
			image2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
			image3 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
