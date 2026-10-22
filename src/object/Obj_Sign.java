package object;

import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import main.GamePanel;


public class Obj_Sign extends SuperObject {
public ArrayList<String> dialogues = new ArrayList<>();
public int dialogueIndex = 0;
public boolean dialogueExhausted = false;
	
	public Obj_Sign(String text, GamePanel gp) {
		this.gp = gp;
		name = "Sign";
		try { 
			image = ImageIO.read(getClass().getResourceAsStream("/objects/sign.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		collision = true;
		dialogues.add(text);
	}
	
	public void speak() {
		 if (dialogueIndex < dialogues.size()) {
			 gp.ui.currentDialogue = dialogues.get(dialogueIndex);
		     dialogueIndex++;
		     if (dialogueIndex == dialogues.size()) {
		    	 dialogueExhausted = true;
		     }
		 }
	}
	
	
	
	
}
