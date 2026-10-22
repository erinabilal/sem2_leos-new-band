package tile;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	public Tile[] tiles;
	public int mapTileNumber[][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tiles = new Tile[30];
		mapTileNumber = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap();
	}
	
	public void getTileImage() {
		try {
			tiles[0] = new Tile();
			tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tiles[1] = new Tile();
			tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path.png"));
			
			tiles[2] = new Tile();
			tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/shrub.png"));
			tiles[2].collision = true;
			
			tiles[3] = new Tile();
			tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/bush.png"));
			tiles[3].collision = true;
			
			tiles[4] = new Tile();
			tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/house_1.png"));
			tiles[4].collision = true;
			
			tiles[5] = new Tile();
			tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/house_2.png"));
			tiles[5].collision = true;
			
			tiles[6] = new Tile();
			tiles[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/house_3.png"));
			tiles[6].collision = true;
			
			tiles[7] = new Tile();
			tiles[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/house_4.png"));
			tiles[7].collision = true;
			
			tiles[8] = new Tile();
			tiles[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/house_5.png"));
			tiles[8].collision = true;
			
			tiles[9] = new Tile();
			tiles[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/house_6.png"));
			tiles[9].collision = true;
			
			tiles[10] = new Tile();
			tiles[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/house_7.png"));
			tiles[10].collision = true;
			
			tiles[11] = new Tile();
			tiles[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/house_8.png"));
			tiles[11].collision = true;
			
			tiles[12] = new Tile();
			tiles[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/house_9.png"));
			tiles[12].collision = true;
			
			tiles[13] = new Tile();
			tiles[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/house_10.png"));
			tiles[13].collision = true;
			
			tiles[14] = new Tile();
			tiles[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/house_11.png"));
			tiles[14].collision = true;
			
			tiles[15] = new Tile();
			tiles[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/house_12.png"));
			tiles[15].collision = true;
			
			tiles[16] = new Tile();
			tiles[16].image = ImageIO.read(getClass().getResourceAsStream("/tiles/house_13.png"));
			tiles[16].collision = true;
			
			tiles[17] = new Tile();
			tiles[17].image = ImageIO.read(getClass().getResourceAsStream("/tiles/house_14.png"));
			tiles[17].collision = true;
			
			tiles[18] = new Tile();
			tiles[18].image = ImageIO.read(getClass().getResourceAsStream("/tiles/house_15.png"));
			tiles[18].collision = true;
			
			tiles[19] = new Tile();
			tiles[19].image = ImageIO.read(getClass().getResourceAsStream("/tiles/house_16.png"));
			tiles[19].collision = true;
			
			tiles[20] = new Tile();
			tiles[20].image = ImageIO.read(getClass().getResourceAsStream("/tiles/fence0.png"));
			tiles[20].collision = true;
			
			tiles[21] = new Tile();
			tiles[21].image = ImageIO.read(getClass().getResourceAsStream("/tiles/fence1.png"));
			tiles[21].collision = true;
			
			tiles[22] = new Tile();
			tiles[22].image = ImageIO.read(getClass().getResourceAsStream("/tiles/fence2.png"));
			tiles[22].collision = true;
			
			tiles[23] = new Tile();
			tiles[23].image = ImageIO.read(getClass().getResourceAsStream("/tiles/fence3.png"));
			tiles[23].collision = true;
			
			tiles[24] = new Tile();
			tiles[24].image = ImageIO.read(getClass().getResourceAsStream("/tiles/fence4.png"));
			tiles[24].collision = true;
			
			tiles[25] = new Tile();
			tiles[25].image = ImageIO.read(getClass().getResourceAsStream("/tiles/fence5.png"));
			tiles[25].collision = true;
			
			tiles[26] = new Tile();
			tiles[26].image = ImageIO.read(getClass().getResourceAsStream("/tiles/fence6.png"));
			tiles[26].collision = true;
			
			tiles[27] = new Tile();
			tiles[27].image = ImageIO.read(getClass().getResourceAsStream("/tiles/fence7.png"));
			tiles[27].collision = true;
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void loadMap() {
	    try {
	        InputStream is = getClass().getResourceAsStream("/maps/world_map_40x40.txt");
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));

	        for (int row = 0; row < gp.maxWorldRow; row++) {
	            String line = br.readLine();
	            String[] numbers = line.split(" ");

	            for (int col = 0; col < gp.maxWorldCol; col++) {
	                mapTileNumber[col][row] = Integer.parseInt(numbers[col]);
	            }
	        }

	        br.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;

		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNumber[worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
					worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
					worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
					worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				
				g2.drawImage(tiles[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}
	
			worldCol++;

			if (worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
			
		}
		
		
		
	}
}
