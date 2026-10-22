package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class NPC_Cyrus extends Entity{
	

	public int homeX, homeY;
	int roamRadius = 4;
	
	public NPC_Cyrus(GamePanel gp) {
		super(gp);
		direction = "down";
		speed = 1;
		homeX = gp.tileSize*16;
		homeY = gp.tileSize*15;
		
		getImage();
		setDialogue();
	}
	
	public void getImage() {
		try {
			//default position is 0, 1 and 2 are right and left foot stepping positions
			front0 = ImageIO.read(getClass().getResourceAsStream("/npc/cyrus_front_0.png"));
			front1 = ImageIO.read(getClass().getResourceAsStream("/npc/cyrus_front_1.png"));
			front2 = ImageIO.read(getClass().getResourceAsStream("/npc/cyrus_front_2.png"));
			
			up0 = ImageIO.read(getClass().getResourceAsStream("/npc/cyrus_up_0.png"));
			up1 = ImageIO.read(getClass().getResourceAsStream("/npc/cyrus_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/npc/cyrus_up_2.png"));
			
			left0 = ImageIO.read(getClass().getResourceAsStream("/npc/cyrus_left_0.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/npc/cyrus_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/npc/cyrus_left_2.png"));

			right0 = ImageIO.read(getClass().getResourceAsStream("/npc/cyrus_right_0.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/npc/cyrus_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/npc/cyrus_right_2.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setDialogue() {
		
		//before all the monsters are dead
		dialogues.add("CYRUS:\n...");
		dialogues.add("LEO:\n(Yikes! That glare!)\nU-Um, I-");
		dialogues.add("CYRUS:\nAnd who might you be?");
		dialogues.add("LEO:\n(Get it together, man!\nOkay... Here goes...)");
		dialogues.add("LEO:\nMy n-name is Leo Hart and\nI was wondering i-if you\nwanted to...");
		dialogues.add("LEO:\nIf you would like to be a part\nof my band...? ");
		dialogues.add("CYRUS:\n...");
		dialogues.add("CYRUS:\nYour band, you say?\n*giggle*");
		dialogues.add("LEO:\n(...He's laughing?)");
		dialogues.add("CYRUS:\nI'm afraid I don't have time\nto spare conversing with the\nlikes of you.");
		dialogues.add("CYRUS:\nNow, please leave before I am\nforced to contact the\nauthorities.");
		dialogues.add("LEO:\nNo, please, w-wait-\n*phone rings*");
		dialogues.add("LEO:\nCome on! Not now!\nWhy did he have to hear my\n\"Bunny Friends\" theme ringtone?!");
		dialogues.add("CYRUS:\n*gasps*");
		dialogues.add("LEO:\nUmm, sorry about tha-");
		dialogues.add("LEO:\n...");
		dialogues.add("LEO:\n(Huh? He's hiding behind\nthe bushes...?\nHe's shivering...)");
		dialogues.add("LEO:\nAre you.. okay?");
		dialogues.add("CYRUS:\nDon't tell me that was just...");
		dialogues.add("CYRUS:\n*clears throat* Nevermind.\nForget what you saw.");
		dialogues.add("LEO:\n(He's back in front of me)");
		dialogues.add("LEO:\nB-But...\n(Wait... is he scared of...)");
		dialogues.add("LEO:\nI'm sorry if this is rude but uh..\nare you perhaps scared\nof rabbits?");
		dialogues.add("CYRUS:\nRabbits?! PFFFT!\nWho said that!");
		dialogues.add("LEO:\n(He's back behind the bushes)");
		dialogues.add("LEO:\n...");
		dialogues.add("LEO:\nIt's alright, you know?");
		dialogues.add("CYRUS:\nW-What?");
		dialogues.add("LEO:\nI mean, it's okay to be scared.");
		dialogues.add("LEO:\nI'm scared of cats...\nThey give me the heeby jeebies...");
		dialogues.add("CYRUS:\n...");
		dialogues.add("CYRUS:\n*mumble*");
		dialogues.add("LEO:\nSorry, what was that?");
		dialogues.add("(Cyrus gets out out the bushes\nand brushes the leaves\noff of himself)");
		dialogues.add("CYRUS:\nI'll accept your request...\n");
		dialogues.add("LEO:\nOh my god! Really?!!\nThank you so mu-");
		dialogues.add("CYRUS:\nBut on one condition...");
		dialogues.add("LEO:\nOh. What is it?");
		dialogues.add("CYRUS:\nYou have to slay the..\nr-rabbit monsters\nthat have invaded my garden...");
		dialogues.add("LEO:\nOh no. That sounds terrible.\nLeave it to me!");
		dialogues.add("CYRUS:\nBut I haven't even told you\nwhere it is yet.");
		dialogues.add("LEO:\nO-Oh, right. Of course... haha");
		dialogues.add("CYRUS:\nHead SOUTH-WEST. Be careful...\nThey're absolutely t-t-terrifying...");
		dialogues.add("LEO:\nDon't you worry, Mr. Key,\nI'll be back in no time!");
		dialogues.add("CYRUS:\nHmm... Good luck, I suppose.\nTry not to die.");
		
		//after the rabbit monsters have been killed
		dialogues.add("CYRUS:\nI see you're back\nmore or less in one piece...");
		dialogues.add("LEO:\n*pants* I didn't know it was going\nto be that hard...");
		dialogues.add("CYRUS:\nSo what is it?\nAre the.. you-know-whats dead...?");
		dialogues.add("LEO:\nYep!");
		dialogues.add("CYRUS:\nI see...");
		dialogues.add("CYRUS:\nWell, a deal is a deal.\nThank you, Leon.\nYou've done me a great service.");
		dialogues.add("LEO:\n(Umm... my name is Leo.\nBut I'm too happy to point it out!)");
		dialogues.add("CYRUS:\nAs I said, I will join your band.\nPleasure doing business\nwith you.");
		dialogues.add("LEO:\nThank you!!! I'm so glad!!");
		dialogues.add("CYRUS:\nOh, I forgot to ask...\nWhere exactly are your other\nmembers?");
		dialogues.add("LEO:\nOh.. um.. well, you see...");
		dialogues.add("LEO:\nYou're the first!");
	
	}
	
	public void setAction() {
	    actionLockCounter++;

	    if (actionLockCounter == 120) {
	        
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
	
	private boolean allMonstersDead() {
	    for (int i = 0; i < gp.monster.length; i++) {
	        if (gp.monster[i] != null) return false;
	    }
	    return true;
	}
	
	
	@Override
	public void speak() {
	    // Face the player
	    switch (gp.player.direction) {
	        case "up"    -> direction = "down";
	        case "down"  -> direction = "up";
	        case "left"  -> direction = "right";
	        case "right" -> direction = "left";
	    }

	    // Switch from phase 0 to phase 1 exactly once, when rabbits are all dead
	    if (allMonstersDead() && phase == 0) {
	        phase = 1;
	        phaseIndex = PHASE1_START;
	    }

	    if (phase == 0) {
	        // Only show a line if we haven't walked off the end
	        if (phaseIndex <= PHASE0_END) {
	            gp.ui.currentDialogue = dialogues.get(phaseIndex);
	            phaseIndex++;
	        }
	        // If phaseIndex is now 45 and rabbits aren't dead yet,
	        // currentDialogue just stays as whatever it last was (line 44).
	        // Next time they talk to Cyrus with dead rabbits, the phase switch fires.
	        return;
	    }

	    if (phase == 1 && !allDialogueExhausted) {
	        gp.ui.currentDialogue = dialogues.get(phaseIndex);
	        if (phaseIndex < PHASE1_END) {
	            phaseIndex++;
	        } else {
	            allDialogueExhausted = true;
	        }
	    }
	}
	
	public void draw(Graphics2D g2, GamePanel gamePanel) {
		int screenX = getScreenX(gamePanel);
		int screenY = getScreenY(gamePanel);
		
	    // only draw if on screen
	    if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
	        worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
	        worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
	        worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {

	        BufferedImage image = null;
	        int[] phaseToSprite = {0, 1, 0, 2};
	        spriteNum = phaseToSprite[spritePhase];

	        switch (direction) {
	            case "up"    -> image = (spriteNum == 0) ? up0    : (spriteNum == 1) ? up1    : up2;
	            case "down"  -> image = (spriteNum == 0) ? front0 : (spriteNum == 1) ? front1 : front2;
	            case "left"  -> image = (spriteNum == 0) ? left0  : (spriteNum == 1) ? left1  : left2;
	            case "right" -> image = (spriteNum == 0) ? right0 : (spriteNum == 1) ? right1 : right2;
	        }

	        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
	    }
	}
	
	
	
}
