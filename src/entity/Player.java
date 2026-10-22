package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import object.Obj_Sign;

public class Player extends Entity{
	public KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	public int attackCounter = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - gp.tileSize/2;
		screenY = gp.screenHeight/2 - gp.tileSize/2;
		
		attackArea.width = 36;
		attackArea.height = 36;
		
		
		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
	}
	
	public void setDefaultValues() {
		
		//player status
		maxLife = 6;
		life = maxLife;
		
		worldX = 33*gp.tileSize;
		worldY = 36*gp.tileSize;
		speed = 4;
		direction = "up";
	}
	
	public void getPlayerImage() {
		//default position is 0, 1 and 2 are right and left foot stepping positions
		front0 = setup("/player/leo_front_0.png", gp.tileSize, gp.tileSize);
		front1 = setup("/player/leo_front_1.png", gp.tileSize, gp.tileSize);
		front2 = setup("/player/leo_front_2.png", gp.tileSize, gp.tileSize);
		
		up0 = setup("/player/leo_up_0.png", gp.tileSize, gp.tileSize);
		up1 = setup("/player/leo_up_1.png", gp.tileSize, gp.tileSize);
		up2 = setup("/player/leo_up_2.png", gp.tileSize, gp.tileSize);
		
		left0 = setup("/player/leo_left_0.png", gp.tileSize, gp.tileSize);
		left1 = setup("/player/leo_left_1.png", gp.tileSize, gp.tileSize);
		left2 = setup("/player/leo_left_2.png", gp.tileSize, gp.tileSize);

		right0 = setup("/player/leo_right_0.png", gp.tileSize, gp.tileSize);
		right1 = setup("/player/leo_right_1.png", gp.tileSize, gp.tileSize);
		right2 = setup("/player/leo_right_2.png", gp.tileSize, gp.tileSize);	
	}
	
	public void getPlayerAttackImage() {
		attack_front0 = setup("/player/leo_front_attack_0.png", gp.tileSize, gp.tileSize*2);
		attack_front1 = setup("/player/leo_front_attack_1.png", gp.tileSize, gp.tileSize*2);
		
		attack_up0 = setup("/player/leo_up_attack_0.png", gp.tileSize, gp.tileSize*2);
		attack_up1 = setup("/player/leo_up_attack_1.png", gp.tileSize, gp.tileSize*2);
		
		attack_left0 = setup("/player/leo_left_attack_0.png", gp.tileSize*2, gp.tileSize);
		attack_left1 = setup("/player/leo_left_attack_1.png", gp.tileSize*2, gp.tileSize);
		
		attack_right0 = setup("/player/leo_right_attack_0.png", gp.tileSize*2, gp.tileSize);
		attack_right1 = setup("/player/leo_right_attack_1.png", gp.tileSize*2, gp.tileSize);
	}
	
	
	//HELPER FUNCTIONS FOR SETTING UP A SCALED IMAGE
	public BufferedImage setup(String imagePath, int width, int height) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath));
			image = scaleImage(image, width, height);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public BufferedImage scaleImage(BufferedImage original, int width, int height) {
	    BufferedImage scaled = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = scaled.createGraphics();
	    g2.drawImage(original, 0, 0, width, height, null);
	    g2.dispose();
	    return scaled;
	}
	//--------------------------------------------------
	
	
	
	public void update() {
		
		if (attacking) {
			attack();	
			if (invincible) {
	            invincibleCounter++;
	            if (invincibleCounter > 60) {
	                invincible = false;
	                invincibleCounter = 0;
	            }
	        }
	        return;
		}
		
		//check direction
		if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
			if (keyH.upPressed) {
				direction = "up";
			}
			else if (keyH.downPressed) {
				direction = "down";
			}
			else if (keyH.leftPressed) {
				direction = "left";
			}
			else if (keyH.rightPressed) {
				direction = "right";
			}
			
			
			//check tile collision
			collisionOn = false;
			gp.collisionChecker.checkTile(this);
			
			
			//check object collision
			int objIndex = gp.collisionChecker.checkObject(this, true);
			interactObject(objIndex);
			
			//check npc collision
			int npcIndex = gp.collisionChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			//check monster collision
			int monsterIndex = gp.collisionChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);
			
			//if collision false then player can move
			if (collisionOn == false) {
				switch (direction) {
				case "up" :
					worldY -= speed;
					break;
				case "down" :
					worldY += speed;
					break;
				case "left" :
					worldX -= speed;
					break;
				case "right" :
					worldX += speed;
					break;
				}
			}
			
			//animate only when moving
			spriteCounter++;
			if (spriteCounter > 12) {
				spritePhase = (spritePhase + 1) % 4;
				spriteCounter = 0;
			}
		} else {
			spritePhase = 0;
			spriteCounter = 0;
		}
		
		// attack input — checked independently of movement
		if (gp.keyH.attackPressed) {
		    attacking = true;
		    gp.keyH.attackPressed = false; // consume the press
		}
			
		//invincible
		if (invincible == true) {
			invincibleCounter++;
			if (invincibleCounter > 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		
		if (life <= 0) {
			gp.stopMusic();
			gp.gameState = gp.gameOverState;
			gp.playMusic(6);
		}
	}

	private void attack() {
	    attackCounter++;
	 
	    if (attackCounter <= 5) {
	        spriteNum = 0; // wind up frame
	    } else if (attackCounter <= 20) {
	        spriteNum = 1; // swing frame
	        
	        //save current worldx, worldy, solidarea
	        int currentWorldX = worldX;
	        int currentWorldY = worldY;
	        int solidAreaWidth = solidArea.width;
	        int solidAreaHeight = solidArea.height;
	        
	        //adjust players hitbox for attack
	        switch (direction) {
	        case "up" : worldY -= attackArea.height; break; 
	        case "down" : worldY += attackArea.height; break;
	        case "left" : worldX -= attackArea.width; break;
	        case "right" : worldX += attackArea.width; break;
	        }
	        
	        //attack area becomes solid area
	        solidArea.width = attackArea.width;
	        solidArea.height = attackArea.height;
	        
	        //check monster collision with hitbox
	        int monsterIndex = gp.collisionChecker.checkEntity(this, gp.monster);
	        damageMonster(monsterIndex);
	        
	        //reset players solid area
	        worldX = currentWorldX;
	        worldY = currentWorldY;
	        solidArea.width = solidAreaWidth;
	        solidArea.height = solidAreaHeight;
	        
	    } else {
	        // attack animation done
	        attacking = false;
	        attackCounter = 0;
	        spriteNum = 0;
	    }
	}

	public void damageMonster(int i) {
		if (i != 999) {
			if (gp.monster[i].invincible == false) {
				gp.playSFX(4);
				gp.monster[i].life -= 1;
				gp.monster[i].invincible = true;
				
				if (gp.monster[i].life <= 0) {
					gp.monster[i] = null;
				}
			}
		}		
	}

	public void contactMonster(int i) {
		if ( i != 999 ) {
			if (invincible == false) {
				gp.playSFX(5);
				life -= 1;	
				invincible = true;
			}

		}		
	}

	public void interactNPC(int i) {
	    if (i != 999 && gp.keyH.interactPressed) {
	        gp.gameState = gp.dialogueState;
	        gp.ui.dialogueFromNPC = true;
	        gp.keyH.interactPressed = false;
	        gp.npc[i].speak(); // show first line immediately when box opens
	    }
	}


	public void interactObject(int i) {
	    if (i != 999 && gp.keyH.interactPressed) {
	        if (gp.obj[i] instanceof Obj_Sign sign) {
	            gp.gameState = gp.dialogueState;
	            gp.ui.dialogueFromNPC = false;
	            gp.keyH.interactPressed = false;
	            sign.speak();
	        }
	    }
	}
	
	public void draw(Graphics2D g2, GamePanel gp) {
	    BufferedImage image = null;
	    int tempScreenX = screenX;
	    int tempScreenY = screenY;

	    if (attacking) {
	        switch (direction) {
	            case "up" -> {
	                image = (spriteNum == 0) ? attack_up0 : attack_up1;
	                tempScreenY = screenY - gp.tileSize; // shift up so feet stay in place
	            }
	            case "down" -> {
	                image = (spriteNum == 0) ? attack_front0 : attack_front1;
	            }
	            case "left" -> {
	                image = (spriteNum == 0) ? attack_left0 : attack_left1;
	                tempScreenX = screenX - gp.tileSize; // shift left so body stays in place
	            }
	            case "right" -> {
	                image = (spriteNum == 0) ? attack_right0 : attack_right1;
	            }
	        }
	    } else {
	        int[] phaseToSprite = {0, 1, 0, 2};
	        spriteNum = phaseToSprite[spritePhase];
	        switch (direction) {
	            case "up"    -> image = (spriteNum == 0) ? up0    : (spriteNum == 1) ? up1    : up2;
	            case "down"  -> image = (spriteNum == 0) ? front0 : (spriteNum == 1) ? front1 : front2;
	            case "left"  -> image = (spriteNum == 0) ? left0  : (spriteNum == 1) ? left1  : left2;
	            case "right" -> image = (spriteNum == 0) ? right0 : (spriteNum == 1) ? right1 : right2;
	        }
	    }

	    // invincible flash
	    if (invincible) {
	        if ((invincibleCounter / 10) % 2 == 0) {
	            float[] scales = {1.5f, 0.4f, 0.4f, 0.6f};
	            float[] offsets = {0, 0, 0, 0};
	            RescaleOp rop = new RescaleOp(scales, offsets, null);
	            BufferedImage scaled = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
	            Graphics2D g2temp = scaled.createGraphics();
	            g2temp.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
	            g2temp.dispose();
	            g2.drawImage(scaled, rop, tempScreenX, tempScreenY);
	        } else {
	            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
	            g2.drawImage(image, tempScreenX, tempScreenY, image.getWidth(), image.getHeight(), null);
	        }
	    } else {
	        g2.drawImage(image, tempScreenX, tempScreenY, image.getWidth(), image.getHeight(), null);
	    }

	    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
}
