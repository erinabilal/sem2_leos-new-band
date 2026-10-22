package monster;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class Monster_Rabbit extends Entity{
	
	public int homeX, homeY;
	int roamRadius = 3;
	
	public Monster_Rabbit(GamePanel gp) {
		super(gp);
		
		type = 2;
		
		direction = "down";
		speed = 2;
		maxLife = 4;
		life = maxLife;
		
		homeX = gp.tileSize*7;
		homeY = gp.tileSize*32;
		
		solidArea.x = 12;
		solidArea.y = 27;
		solidArea.width = 27;
		solidArea.height = 21;
		
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;	
		
		getImage();
	}
	
	
	public void getImage() {
		try {
			front0 = ImageIO.read(getClass().getResourceAsStream("/monster/rabbit_down.png"));
			front1 = ImageIO.read(getClass().getResourceAsStream("/monster/rabbit_down.png"));
			front2 = ImageIO.read(getClass().getResourceAsStream("/monster/rabbit_down.png"));
			
			up0 = ImageIO.read(getClass().getResourceAsStream("/monster/rabbit_up.png"));
			up1 = ImageIO.read(getClass().getResourceAsStream("/monster/rabbit_up.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/monster/rabbit_up.png"));
			
			
			left0 = ImageIO.read(getClass().getResourceAsStream("/monster/rabbit_left.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/monster/rabbit_left.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/monster/rabbit_left.png"));
			
			
			right0 = ImageIO.read(getClass().getResourceAsStream("/monster/rabbit_right.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/monster/rabbit_right.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/monster/rabbit_right.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	
	public void setAction() {
		actionLockCounter++;

	    if (actionLockCounter == 90) {
	        
	        // check if outside roam radius
	        int distX = Math.abs(worldX - homeX) / gp.tileSize;
	        int distY = Math.abs(worldY - homeY) / gp.tileSize;
	        
	        if (distX > roamRadius || distY > roamRadius) {
	            // steer back toward home
	            if (worldX < homeX) { direction = "right"; moving = true; }
	            else if (worldX > homeX) { direction = "left"; moving = true; }
	            else if (worldY < homeY) { direction = "down"; moving = true; }
	            else if (worldY > homeY) { direction = "up"; moving = true; }
	        } else {
	            // normal random wander
	            Random rand = new Random();
	            int i = rand.nextInt(100) + 1;

	            if (i <= 20)      { direction = "up";    moving = true; }
	            else if (i <= 40) { direction = "down";  moving = true; }
	            else if (i <= 60) { direction = "left";  moving = true; }
	            else if (i <= 80) { direction = "right"; moving = true; }
	            else              { moving = false; }
	        }

	        actionLockCounter = 0;
	    }
	}
	
	public void draw(Graphics2D g2, GamePanel gamePanel) {
	    int screenX = getScreenX(gamePanel);
	    int screenY = getScreenY(gamePanel);

	    if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
	        worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
	        worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
	        worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {

	        BufferedImage image = null;
	        switch (direction) {
	            case "up"    -> image = up0;
	            case "down"  -> image = front0;
	            case "left"  -> image = left0;
	            case "right" -> image = right0;
	        }

	        if (invincible) {
	            if ((invincibleCounter / 10) % 2 == 0) {
	                float[] scales = {1.5f, 0.4f, 0.4f, 0.6f};
	                float[] offsets = {0, 0, 0, 0};
	                RescaleOp rop = new RescaleOp(scales, offsets, null);
	                BufferedImage scaled = new BufferedImage(gamePanel.tileSize, gamePanel.tileSize, BufferedImage.TYPE_INT_ARGB);
	                Graphics2D g2temp = scaled.createGraphics();
	                g2temp.drawImage(image, 0, 0, gamePanel.tileSize, gamePanel.tileSize, null);
	                g2temp.dispose();
	                g2.drawImage(scaled, rop, screenX, screenY);
	            } else {
	                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
	                g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
	            }
	        } else {
	            g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
	        }

	        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	    }
	}
}