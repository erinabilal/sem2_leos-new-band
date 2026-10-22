package main;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import object.Obj_Heart;
import object.SuperObject;


public class UI {
	GamePanel gp;
	Graphics2D g2;
	Font pixeloidSans, pixeloidSansBold;
	
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public String currentDialogue = "";
	public boolean dialogueFromNPC = false;
	public int commandNum = 0;
	
	BufferedImage heart_full, heart_blank, heart_half;
	
	public int titleScreenState = 0; //beginning title screen, 1 is intro cutscene story
	
	public BufferedImage[] sceneCGs = new BufferedImage[15];
	public String[][] cgCaptions = new String[15][];
	public int cgIndex = 0;
	public int cgLineIndex = 0;
	

	
	public UI(GamePanel gp) {
		this.gp = gp;
	
		try {
			
			InputStream is = getClass().getResourceAsStream("/font/PixeloidSansBold-1jpBg.ttf");
			pixeloidSansBold = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/font/PixeloidSans-lxa3y.ttf");
			pixeloidSans = Font.createFont(Font.TRUETYPE_FONT, is);
			
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//create HUD OBJECT
		SuperObject heart = new Obj_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
		
		
		//load intro scene cg text/immage
		try {
			sceneCGs[0] = ImageIO.read(getClass().getResourceAsStream("/cgs/cg_01.png"));
			cgCaptions[0] = new String[] {
					" >> XXX Concert Hall Greenroom - A few months ago..."
			};
			
			sceneCGs[1] = ImageIO.read(getClass().getResourceAsStream("/cgs/cg_02.png"));
			cgCaptions[1] = new String[] {
					"GUITAR PLAYER:\nMan, I can't believe this is the\nfifth time we lost a contest!",
					"KEYBOARD PLAYER:\nI know, right?",
					"KEYBOARD PLAYER:\nMakes me wonder why we're still even trying..."
			};
			
			sceneCGs[2] = ImageIO.read(getClass().getResourceAsStream("/cgs/cg_03.png"));
			cgCaptions[2] = new String[] {
					"LEO:\nDon't say that!!!",
					"LEO:\nHow could you guys say that?",
					"LEO:\nWasn't it our dream to get this award?\nAnd you guys are just giving up?"
			};
					
			
			sceneCGs[3] = ImageIO.read(getClass().getResourceAsStream("/cgs/cg_04.png"));
			cgCaptions[3] = new String[] {
					"GUITAR PLAYER:\nFace it, Leo... we're not cut out for this.",
					"GUITAR PLAYER:\nThat new group is way better than us anyway.",
			};
			
			sceneCGs[4] = ImageIO.read(getClass().getResourceAsStream("/cgs/cg_05.png"));
			cgCaptions[4] = new String[] {
					"LEO:\nB-But we can try!",
					"LEO:\nWe can always try again!",
					"LEO:\nI already have some new ideas for this song\nthere's only a small thing we need to fi-"
			};
			
			sceneCGs[5] = ImageIO.read(getClass().getResourceAsStream("/cgs/cg_04.png"));
			cgCaptions[5] = new String[] {
					"GUITAR PLAYER:\nThis is the millionth time you've said this, man"
			};
			
			sceneCGs[6] = ImageIO.read(getClass().getResourceAsStream("/cgs/cg_06.png"));
			cgCaptions[6] = new String[] {
					"KEYBOARD PLAYER:\nLook, Leo, we've been thinking for a while now...",
					"KEYBOARD PLAYER:\nAnd I think we all need to go our separate ways."
			};
			
			sceneCGs[7] = ImageIO.read(getClass().getResourceAsStream("/cgs/cg_07.png"));
			cgCaptions[7] = new String[] {
					"LEO:\nWhat...?"
			};
			
			sceneCGs[8] = ImageIO.read(getClass().getResourceAsStream("/cgs/cg_06.png"));
			cgCaptions[8] = new String[] {
					"KEYBOARD PLAYER:\nYou've been a great leader...",
					"KEYBOARD PLAYER:\nBut you need to know when to stop.",
					"KEYBOARD PLAYER:\nIt's best we announce our disbandment now\nwhen we still have some dignity left."
			};
			
			sceneCGs[9] = ImageIO.read(getClass().getResourceAsStream("/cgs/cg_04.png"));
			cgCaptions[9] = new String[] {
					"GUITAR PLAYER:\nYeah...\nAt least this way our fans won't be disappointed\nwhen we have nothing left.",
					"GUITAR PLAYER:\nC'mon, dude. I'm outta here."
			};
			
			sceneCGs[10] = ImageIO.read(getClass().getResourceAsStream("/cgs/cg_08.png"));
			cgCaptions[10] = new String[] {
					"LEO:\nWait. No. Guys...",
					"LEO:\nDon't go-",
					"LEO:\nWe can still-"
			};
			
			sceneCGs[11] = ImageIO.read(getClass().getResourceAsStream("/cgs/cg_09.png"));
			cgCaptions[11] = new String[] {
					"LEO:\nWe could...",
					"LEO:\nNo...",
					"LEO:\n..."
			};
			
			sceneCGs[12] = ImageIO.read(getClass().getResourceAsStream("/cgs/cg_blank.png"));
			cgCaptions[12] = new String[] {
					"...",
					"LEO:\nWe announced our disbandment on TV the next day.",
					"LEO:\nThe people's reaction was as expected, of course.",
					"LEO:\nI was sad too, but I wasn't going to give up.",
					"LEO:\nIt took a couple months, but I managed to\ntrack down a certain musician\nwho I wanted to ask for help.",
					"LEO:\nHe went by the name Cyrus Key\nand from the looks of it...",
					"LEO:\nHe wasn't exactly the approachable type.",
					"LEO:\nCountless news articles stated he quit every single\njob he was given...",
					"LEO:\nAnd all because he never saw eye-to-eye with anyone.",
					"LEO:\n...",
					"LEO:\nThis was gonna be tough,\nbut if anyone could convince him...",
					"LEO:\nIt would have to be me!"
			};
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		g2.setFont(pixeloidSans);
		g2.setColor(Color.white);
		
		
		//title state
		if(gp.gameState == gp.titleState) {
			g2.setFont(pixeloidSansBold);
			drawTitleScreen();
		}
		
		//play state
		if(gp.gameState == gp.playState) {
			drawPlayerLife();
		}
		
		//pause state
		if (gp.gameState == gp.pauseState) {
			drawPlayerLife();
			g2.setFont(pixeloidSansBold);
			drawPauseScreen();
		}
		
		//dialogue state
		if (gp.gameState == gp.dialogueState) {
			drawPlayerLife();
			g2.setFont(pixeloidSans);
			drawDialogueScreen();
		}
		
		//game over
		if (gp.gameState == gp.gameOverState) {
		    g2.setFont(pixeloidSansBold);
		    drawGameOverScreen();
		}
		
		//game completed
		if (gp.gameState == gp.gameFinishedState) {
	        g2.setFont(pixeloidSansBold);
	        drawGameFinishedScreen();
	    }
	}
	
	public void drawGameOverScreen() {
	    g2.setColor(Color.black);
	    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
	    g2.setColor(Color.white);
	    String text = "GAME OVER";
	    int x = getXForCenteredText(text);
	    int y = gp.screenHeight / 2 - gp.tileSize;
	    g2.drawString(text, x, y);

	    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
	    g2.setColor(Color.lightGray);
	    text = "Press ENTER to return to title";
	    x = getXForCenteredText(text);
	    y += gp.tileSize * 2;
	    g2.drawString(text, x, y);
	}
	
	
	public void drawPlayerLife() {
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
		
		//draw blank heart (max life)
		while (i < gp.player.maxLife/2) {
			g2.drawImage(heart_blank, x, y, gp.tileSize, gp.tileSize,  null);
			i++;
			x += gp.tileSize;
		}
		
		//reset
		x = gp.tileSize/2;
		y = gp.tileSize/2;
		i = 0;
		
		//draw current life
		while (i < gp.player.life) {
			g2.drawImage(heart_half, x, y, gp.tileSize, gp.tileSize,  null);
			i++;
			if (i < gp.player.life) {
				g2.drawImage(heart_full, x, y, gp.tileSize, gp.tileSize,  null);
			}
			i++;
			x += gp.tileSize;
		}
	}

	public void drawTitleScreen() {
		
		//TITLE SCREEN
		if(titleScreenState == 0) {
			
			g2.setColor(new Color(0,0,0));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			
			//title name
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 67F));
			String text = "Leo's New Band";
			int x = getXForCenteredText(text);
			int y = gp.tileSize*3;
			
			//shadow
			g2.setColor(Color.gray);
			g2.drawString(text, x + 5, y + 5);
			
			//main color
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			//leo image
			x = gp.screenHeight/2 + (gp.tileSize*2)/2;
			y += gp.tileSize*2;
			g2.drawImage(gp.player.front1, x, y, gp.tileSize*2, gp.tileSize*2, null);
			
			//menu
			g2.setFont((g2.getFont().deriveFont(Font.PLAIN, 30F)));
			
			text = "NEW GAME";
			x = getXForCenteredText(text);
			y+= gp.tileSize*4;
			g2.drawString(text, x, y);
			if (commandNum == 0) {
				g2.drawString( ">", x - gp.tileSize, y);
			}
			
			text = "LOAD GAME";
			x = getXForCenteredText(text);
			y+= gp.tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 1) {
				g2.drawString( ">", x - gp.tileSize, y);
			}
			
			text = "QUIT";
			x = getXForCenteredText(text);
			y+= gp.tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 2) {
				g2.drawString( ">", x - gp.tileSize, y);
			}
		}
		
		//STORY LORE CUTSCENE
		else if (titleScreenState == 1) {
		    g2.setColor(Color.black);
		    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

		    int x = gp.tileSize / 2;
		    int y = gp.tileSize / 2;
		    g2.drawImage(sceneCGs[cgIndex], x, y, gp.tileSize * 15, gp.tileSize * 6, null);

		    g2.setFont(pixeloidSans);
		    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24F));
		    g2.setColor(Color.white);
		    int textY = y + gp.tileSize * 8;
		    for (String line : cgCaptions[cgIndex][cgLineIndex].split("\n")) {
		        int textX = getXForCenteredText(line);
		        g2.drawString(line, textX, textY);
		        textY += g2.getFontMetrics().getHeight() + 4;
		    }

		    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 16F));
		    g2.setColor(Color.gray);
		    g2.drawString("Z - Next", gp.screenWidth - gp.tileSize * 2 - 10, gp.screenHeight - 20);
		}
	}
	
	public void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,40F));
		String text = "PAUSED";
		int x = getXForCenteredText(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	
	public void drawGameFinishedScreen() {
	    g2.setColor(Color.black);
	    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 70F));
	    g2.setColor(Color.white);
	    String text = "Game Complete";
	    int x = getXForCenteredText(text);
	    int y = gp.screenHeight / 2 - gp.tileSize;
	    g2.drawString(text, x, y);

	    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
	    g2.setColor(Color.lightGray);
	    text = "Press ENTER to return to title";
	    x = getXForCenteredText(text);
	    y += gp.tileSize * 2;
	    g2.drawString(text, x, y);
	}
	
	public void drawDialogueScreen() {
	    //WINDOW
	    int x = gp.tileSize*2;
	    int y = gp.tileSize*7;
	    int width = gp.screenWidth - (gp.tileSize*4);
	    int height = gp.tileSize*4;

	    drawSubWindow(x,y,width,height);

	    g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28F));
	    x += gp.tileSize;
	    y += gp.tileSize;

	    String dialogueText = (currentDialogue == null) ? "" : currentDialogue;

	    for (String line : dialogueText.split("\n")) {
	        g2.drawString(line, x, y);
	        y += 40;
	    }
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		Color c = new Color(0,0,0,215); //black color
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 25, 25);
		
		c = new Color(255,255,255); //white color
		g2.setColor(c);
		g2.setStroke(new BasicStroke(4));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
		
	}
	
	public int getXForCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
	
}
