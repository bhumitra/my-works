#include "cutscene.h"


	string cutscenes(int num){

		string scene;

		switch (num){
			case 1:
				scene = "It is a dark, stormy night. Dark clouds pass, and the Bat Signal is seen illuminated. A radio transmission from a nearby police car is heard.\n \nRadio : All units to Gotham City Hall. Batman has apprehended the Joker and he is taking him to Arkham Asylum.\n\n***You are the BATMAN****.\n\nYou reach the asylum where The Joker is strapped to an upright stretcher.\n\nSensing something is wrong because Joker seemed to give up easier than usual, \nyou accompany the security personnel and the Joker to the Holding Cells, where the Arkham staff refuse to let you go any further.\n\nAs the Joker is being registered into the Cell Block by Commissioner Gordon, he breaks free from a guard and escapes into the asylum.\nThe guards are knocked out on the floor.\n\nJoker : \"Welcome to the MADHOUSE, Batman! I set a trap and you sprang it gloriously! Now lets get this party started.\"";

				break;
			case 2:
				scene = "\nJoker: [on the asylum television sets] \"I'm in control of the asylum. You're not going anywhere I don't want you to. Understand?\n\nYou : If you think I'll let you run...\n\nJoker : Blah, blah, blah!Always with the hero speak!I'm getting bored of watching you. Why don't you just come find me ?" ;
				break;
			case 3:
				scene = "\nBatman comes to learn that the Joker is trying to create an army of Bane-like creatures to threaten Gotham City.\n\nWhen injected in a person, this serum transforms that person into a HUGE mindless MONSTER\nthat craves for one thing only..... DESTRUCTION!!\n\nYour Objective :\n\nDestroy the TITAN serum and THE JOKER.";
				break;
			case 4:
				scene = "\nBatman resists the change after taking the antidote...\n\nThe Joker becomes upset and takes an overdose of Titan, horribly mutating into a massive monster.\n\nNow fight the JOKER ! \n\nPress enter to begin the battle....";
				break;
			case 5:	
				scene = "\nYou defeat the TITAN affected Joker and his henchmen,\nknocking his nemesis unconscious with an explosive gel enhanced punch.\n\nIn the aftermath, those affected by Titan begin to revert to normal,\nincluding the Joker who is restrained and taken into custody as police officers retake the asylum.\nYou destroy the remaining TITAN serum containers.\nYou rescue gordon and team up with him to find the other escaped prisoners -- \n***Freeze, Poison Ivy, Bane , Killer Croc and Scarecrow****\nand put them back where they belong...\n\n\n\n************************THE END**********************\n\n\n\n---Created by BHUMITRA NAGAR\n\n----Storyline taken from BATMAN: ARKHAM ASYLYUM game.";
				break;
			}
		return scene;
		}

	string afterdeath(){

		int n = rand() % 6;
		string ad;

		switch (n){

			case 0:
				ad = "\n\nJoker : Ohh, isn't that cute? Little Bats asleepin'! (aside) Someone finish him off.";
				break;
			case 1:
				ad = "\n\nJoker : Too easy! Played you like a violin, then cut your strings. Nighty night, Bats..!";
				break;
			case 2:
				ad = "\n\nJoker : *tsk tsk tsk* Gotta say, I thought you'd last longer.";
				break;
			case 3:
				ad = "\n\nJoker : Is someone feeling a little down? Well, cheer up, Bats; it won't kill ya.... Oops! too late!";
				break;
			case 4:
				ad = "\n\nJoker : Ding dong!The bat is dead!Which old bat ? The dumb old bat!Ding dong, the dumb old bat is dead!";
				break;
			case 5:
				ad= "\n\nJoker : It's ok to die, Bats, I'll be here to protect Gotham! I'll do a real good job.";
				break;
			}
		return ad;
		}