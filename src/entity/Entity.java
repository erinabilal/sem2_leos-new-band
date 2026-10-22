package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;

public class Entity {
	
	public GamePanel gp;
	public int worldX , worldY;
	public int speed;
	public String direction;
	
	//character status
	public int maxLife;
	public int life;
	public boolean invincible = false;
	public int invincibleCounter = 0;
	
	//entity images
	public BufferedImage front1, front0, front2,
						 up1, up0, up2,
						 left1, left0, left2,
						 right1, right0, right2;
	
	public BufferedImage attack_front0, attack_front1,
						attack_up0, attack_up1,
						attack_left0, attack_left1,
						attack_right0, attack_right1;
	
	//sprite animation
	public int spriteCounter = 0;
	public int spriteNum = 0;
	public int spritePhase = 0;
	
	//entity type
	public int type; //0 is player, 1 is npc, 2 is monster
	
	//entity collision area
	public Rectangle solidArea = new Rectangle(15, 30, 21, 18);
	public int solidAreaDefaultX = solidArea.x;
	public int solidAreaDefaultY = solidArea.y;
	public boolean collisionOn = false;
	
	//entity ai
	public int actionLockCounter = 0;
	public boolean moving;
	
	//entity dialogues
	public ArrayList<String> dialogues = new ArrayList<>();
	public int dialogueIndex = 0;
	public static final int PHASE0_START = 0;
	public static final int PHASE0_END   = 44;
	public static final int PHASE1_START = 45;
	public static final int PHASE1_END   = 56;
	public int phase = 0;        // 0 = rabbits alive, 1 = rabbits dead
	public int phaseIndex = 0;   // which line within the current phase to show next
	public boolean allDialogueExhausted = false; // true only after phase 1 finishes
	
	//ATTACK VARIABLES
	boolean attacking = false;
	public Rectangle attackArea = new Rectangle (0,0,0,0);
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}

	public void draw(Graphics2D g2, GamePanel gamePanel) { }
	
	public void setAction( ) { }
	
	public void speak() { }
	
	public void update() {
	    setAction();
	    
	    if (moving) {
	        collisionOn = false;
	        gp.collisionChecker.checkTile(this);
	        gp.collisionChecker.checkEntity(this, gp.npc);     // Add this
	        gp.collisionChecker.checkEntity(this, gp.monster);
	        gp.collisionChecker.checkObject(this, false);
	        boolean contactPlayer = gp.collisionChecker.checkPlayer(this);
	        
	        if (this.type == 2 && contactPlayer == true) {
	        	if (gp.player.invincible == false) {
	        		gp.player.life -= 1;
	        		gp.player.invincible = true;
	        	}
	        }
	        
	        if (collisionOn == false) {
	            switch (direction) {
	            case "up"    : worldY -= speed; break;
	            case "down"  : worldY += speed; break;
	            case "left"  : worldX -= speed; break;
	            case "right" : worldX += speed; break;
	            }
	        }
	        
	        spriteCounter++;
	        if (spriteCounter > 12) {
	            spritePhase = (spritePhase + 1) % 4;
	            spriteCounter = 0;
	        }
	    } else {
	        spritePhase = 0; // reset to idle frame when standing still
	        spriteCounter = 0;
	    }
	    
	  //invincible
	  		if (invincible == true) {
	  			invincibleCounter++;
	  			if (invincibleCounter > 40) {
	  				invincible = false;
	  				invincibleCounter = 0;
	  			}
	  		}
	}

	
	public int getScreenX(GamePanel gp) {
	    return worldX - gp.player.worldX + gp.player.screenX;
	}

	public int getScreenY(GamePanel gp) {
	    return worldY - gp.player.worldY + gp.player.screenY;
	}

}
