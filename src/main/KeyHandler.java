package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.NPC_Cyrus;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, interactPressed, attackPressed;
	
	
	public KeyHandler (GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {	} 

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		//title state
		
		if(gp.gameState == gp.titleState) {
			
			//check if on title menu
			if (gp.ui.titleScreenState == 0) {
				
				if (code == KeyEvent.VK_UP) {
					gp.playSFX(3);
					gp.ui.commandNum--;
					if (gp.ui.commandNum < 0) {
						gp.ui.commandNum = 2;
					}
				}
				
				if (code == KeyEvent.VK_DOWN) {
					gp.playSFX(3);
					gp.ui.commandNum++;
					if (gp.ui.commandNum > 2) {
						gp.ui.commandNum = 0;
					}
				}
				
				if (code == KeyEvent.VK_ENTER) {
					if ( gp.ui.commandNum == 0) {
						gp.playSFX(0);
						gp.ui.titleScreenState = 1;
					}
					if (gp.ui.commandNum == 1) {
						gp.playSFX(0);
						//temporary bcs no save and load function yet
					}
					if (gp.ui.commandNum == 2) {
						gp.playSFX(0);
						System.exit(0);
					}
				}
			}
			
			else if (gp.ui.titleScreenState == 1) {
			    if (code == KeyEvent.VK_Z) {
			        // still lines left in this CG
			        if (gp.ui.cgLineIndex < gp.ui.cgCaptions[gp.ui.cgIndex].length - 1) {
			            gp.ui.cgLineIndex++;
			        } else {
			            // move to next CG
			            gp.ui.cgIndex++;
			            gp.ui.cgLineIndex = 0;
			            
			            if (gp.ui.cgIndex >= gp.ui.sceneCGs.length || gp.ui.sceneCGs[gp.ui.cgIndex] == null) {
			                // all CGs done, start game
			                gp.ui.cgIndex = 0;
			                gp.ui.cgLineIndex = 0;
			                gp.gameState = gp.playState;
			            }
			        }
			    }
			}
			
			
		}
		
		//PLAY STATE
		if(gp.gameState == gp.playState) {
			//PLAYER MOVEMENT
			if (code == KeyEvent.VK_UP) { upPressed = true; }
			if (code == KeyEvent.VK_DOWN) { downPressed = true; }
			if (code == KeyEvent.VK_LEFT) { leftPressed = true; }
			if (code == KeyEvent.VK_RIGHT) { rightPressed = true; }
			
			//PLAYER INTERACTION
			if (code == KeyEvent.VK_Z) { interactPressed = true; }
			
			//PLAYER ATTACK
			if (code == KeyEvent.VK_X) {attackPressed = true; }
			
			//PAUSE GAME
			if (code == KeyEvent.VK_P) { gp.gameState = gp.pauseState; }
		}
		
		//PAUSE STATE
		else if(gp.gameState == gp.pauseState) {
			
			//PLAY GAME
			if (code == KeyEvent.VK_P) { gp.gameState = gp.playState; }
		}
		
		//DIALOGUE STATE
		else if (gp.gameState == gp.dialogueState) {
		    if (code == KeyEvent.VK_Z) {

		        if (!gp.ui.dialogueFromNPC) {
		            // Look for the sign the player is currently touching
		            int objIndex = gp.collisionChecker.checkObject(gp.player, true);
		            
		            if (objIndex != 999 && gp.obj[objIndex] instanceof object.Obj_Sign sign) {
		                if (sign.dialogueExhausted) {
		                    // Reset sign for next time and close
		                    sign.dialogueIndex = 0;
		                    sign.dialogueExhausted = false;
		                    gp.gameState = gp.playState;
		                } else {
		                    // Advance to the next line of text
		                    sign.speak();
		                }
		            } else {
		                // Fallback if no sign is found
		                gp.gameState = gp.playState;
		            }

		        } else {
		            // Find Cyrus specifically
		            NPC_Cyrus cyrus = null;
		            for (int i = 0; i < gp.npc.length; i++) {
		                if (gp.npc[i] instanceof NPC_Cyrus) {
		                    cyrus = (NPC_Cyrus) gp.npc[i];
		                    break;
		                }
		            }

		            if (cyrus == null) {
		                gp.gameState = gp.playState;
		                return;
		            }

		            if (cyrus.allDialogueExhausted) {
		                // Phase 1 is fully done — trigger the ending
		                gp.gameState = gp.gameFinishedState;
		                gp.stopMusic();
		                gp.playSFX(7);
		            } else {
		                cyrus.speak();
		                // If phase 0 just ran out and rabbits aren't dead, close the box
		                if (cyrus.phase == 0 && cyrus.phaseIndex > cyrus.PHASE0_END) {
		                    gp.gameState = gp.playState;
		                }
		            }
		        }
		    }
		}
		
		//GAMEOVER STATE
		else if (gp.gameState == gp.gameOverState) {
		    if (code == KeyEvent.VK_ENTER) {
		        gp.gameState = gp.titleState;
		        gp.setupGame();         // reset objects/npcs/monsters
		        gp.player.setDefaultValues(); // reset player position and life
		        gp.stopMusic();
		        gp.ui.titleScreenState = 0;   // back to main menu
		    }
		}
		
		
		//GAME FINISHED STATE
		else if (gp.gameState == gp.gameFinishedState) {
		    if (code == KeyEvent.VK_ENTER) {
		        gp.gameState = gp.titleState;
		        gp.setupGame(); // Respawns NPCs and monsters
		        gp.player.setDefaultValues(); // Resets health and position
		        gp.stopMusic();
		        gp.ui.titleScreenState = 0;
		    }
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_UP) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_DOWN) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}
		if (code == KeyEvent.VK_Z) {
			interactPressed = false;
		}
		
		if (code == KeyEvent.VK_X) {
		    attackPressed = false;
		}
	}
		
	

}
