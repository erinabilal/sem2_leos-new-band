package main;

import entity.NPC_Cyrus;
import monster.Monster_Rabbit;
import object.Obj_Sign;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new Obj_Sign("Key Residence - NORTH\nBunny Pen - WEST", gp);
		gp.obj[0].worldX = 33*gp.tileSize;
		gp.obj[0].worldY = 29*gp.tileSize;
		
		gp.obj[1] = new Obj_Sign("WARNING: Rabbit Monsters...\nOnly go in if you're prepared!\n(press X to attack)", gp);
		gp.obj[1].worldX = 12*gp.tileSize;
		gp.obj[1].worldY = 30*gp.tileSize;
	}
	
	public void setNPC () {
		gp.npc[0] = new NPC_Cyrus(gp);
		gp.npc[0].worldX = gp.tileSize*16;
		gp.npc[0].worldY  = gp.tileSize*15;
	}
	
	public void setMonster() {
		gp.monster[0] = new Monster_Rabbit(gp);
		gp.monster[0].worldX = gp.tileSize*6;
		gp.monster[0].worldY = gp.tileSize*31;
		
		gp.monster[1] = new Monster_Rabbit(gp);
		gp.monster[1].worldX = gp.tileSize*6;
		gp.monster[1].worldY = gp.tileSize*33;
		
		gp.monster[2] = new Monster_Rabbit(gp);
		gp.monster[2].worldX = gp.tileSize*6;
		gp.monster[2].worldY = gp.tileSize*32;
		
		gp.monster[3] = new Monster_Rabbit(gp);
		gp.monster[3].worldX = gp.tileSize*8;
		gp.monster[3].worldY = gp.tileSize*33;
		
		gp.monster[4] = new Monster_Rabbit(gp);
		gp.monster[4].worldX = gp.tileSize*8;
		gp.monster[4].worldY = gp.tileSize*31;
	}
}
