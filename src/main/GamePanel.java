package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	//SCREEN SETTINGS
	final int originalTileSize = 16; //tile size 16x16
	final int scale = 3;
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; //768px
	public final int screenHeight = tileSize * maxScreenRow; //576px
	
	//WORLD SETTINGS
	public final int maxWorldCol = 40;
	public final int maxWorldRow = 40;

	
	//FPS
	final int FPS = 60;
	
	//SYSTEM
	TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	Thread gameThread;
	public CollisionChecker collisionChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public Sound sound = new Sound();
	public Sound sfx = new Sound();
	boolean musicPlaying = false;
	
	//PLAYER AND OBJECTS/ENTITY
	public Player player = new Player(this, keyH);
	public SuperObject obj[] = new SuperObject[10];
	public Entity npc[] = new Entity[10]; 
	public Entity monster[] = new Entity[20];
	
	//GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int gameOverState = 4;
	public final int gameFinishedState = 5;
	
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		gameState = titleState;
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while (gameThread != null) {
			
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			if (delta >= 1) {
				//UPDATE INFO: 
				update();
				
				//DRAW SCREEN WITH UPDATED INFO
				repaint();
				delta--;
			}
		}
	}
	
	public void update() { 
		
		if (gameState == titleState && ui.titleScreenState == 1 && !musicPlaying) {
			stopMusic();
			playMusic(1);
			musicPlaying = true;
		}
		
		if (gameState == playState && musicPlaying) {
			stopMusic();
			playMusic(2);
			musicPlaying = false;
		}
		
		//only update player/npc if game state is currently in playing mode
		if (gameState == playState) {
			
			//player
			player.update();
			
			//npc
			for (int i = 0; i<npc.length;i++) {
				if (npc[i] != null) {
					npc[i].update();
				}
			}
			
			//monster
			for (int i = 0; i< monster.length;i++) {
				if (monster[i] != null) {
					monster[i].update();
				}
			}
			
		}
		
		if (gameState == pauseState) {
			//do nothing
		}
		
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		//title screen
		if (gameState == titleState) {
			ui.draw(g2);
		}
		
		//others
		else {
			tileM.draw(g2);
			
			for( int i = 0; i < obj.length; i++) {
				if (obj[i] != null) {
					obj[i].draw(g2, this);
				}
			}
			
			// collect all entities to draw
			java.util.ArrayList<entity.Entity> entityList = new java.util.ArrayList<>();
			for (int i = 0; i < npc.length; i++) {
			    if (npc[i] != null) entityList.add(npc[i]);
			}
			for (int i = 0; i < monster.length; i++) {
			    if (monster[i] != null) entityList.add(monster[i]);
			}
			entityList.add(player);

			// sort by worldY so entities further south draw on top
			entityList.sort((a, b) -> Integer.compare(a.worldY, b.worldY));

			// draw them in order
			for (entity.Entity e : entityList) {
			    e.draw(g2, this);
			}
			
			ui.draw(g2);	
		}
		
		
		g2.dispose();
	}
	
	public void playMusic(int i) {
		sound.setFile(i);
		sound.play();
		sound.loop();
	}
	
	public void stopMusic() {
		sound.stop();
		musicPlaying = false;
	}
	
	public void playSFX(int i) {
		sfx.setFile(i);
		sfx.play();
	}
	
	
}
