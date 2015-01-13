#include <iostream> 
#include <list>
#include "Systemcontrol.h"
#include <stdio.h>
#include "cutscene.h"
#include <chrono>
#include <thread>
#include <ctime>
#include <conio.h>

using namespace std;

void fnfinalbattle();
int checkHelp(string);
int updateExpPts(int);
int updateHealth(int);
void level1();
void level1stage1();
void level1stage2();
void level1stage3();
void level2();
void level2stage1();
void level3();
void level3stage1();
void fnantidote();
void fnantipwd();
void fnbatarangAttack();
void fnbatclaw();
void fnbatmanMoves();
void fncheckCGS();
void fndefendbatman();
int fndisplayHealth();
void fnEnemyAttack();
void fnfistAttack();
void fnhelp();
void fnInvestigate();
void fnJokerAttack();
void fnOpenDoor();
void fntakedown();
void fnuseCGS();
int getjokerGrab();
int getjokerGun();
int getjokerTeeth();
int getjokerTitanCharge();

int health = 100; /// health of batman
int expPoints = 0; // experience points gained
int numberInmates = 3; // number of inmates you are fighting
bool gargoyle = true; //true means gargoyle exists, false if destroyed
int healthMax = 100; // max health of Batman
int healthMin = 0; // min health of Batman
int attackFistMin = 20; // min damage caused by fist
int attackFistMax = 40; // max damage caused by fist
int attackBatarang = 20; // damage cause by batarang
int enemyAttack = 0; // intializing damage caused by enemy
int enemyAttackMin = 1; // min damage caused by enemy
int enemyAttackMax = 6; // max damage caused by enemy
int deaths = 0; // keeps count of enemies that are alive. 3 means all 3 enemies are dead. 
int fistAttack; // damage caused by batmans fist/punches. increases as you gain experience
bool onGround = true; //player is on ground. 0 means not on ground
bool defencemode = false; // keeps track if defence is on or off
bool finalbattle = false; // true if final battle has started, else false.
int jokerhealth=300; //health of joker
bool isnear = false; // true if joker is too close the batman. False otherwise
int enemy[] = { 0, 1, 2 }; //declaring enemies 1,2 and 3
int enemyhealth[] = { 100, 100, 100 }; //declaring enemy health for enemy 1,2 and 3
string choice; //takes the value entered by the player.



// Purpose: a class to implement a timer.
class Timer {
private:
	unsigned long startTime; // takes the current time.
public:

	// start : null-> null
	// Purpose: When called starts the timer
	// Returns: null
	// EXAMPLES:
	// Timer t;
	// t.start()
	void start() {
		startTime = clock();
		}


	// elapsedTime : null->unsigned long
	// Purpose: When called returns the elapsed time since the timer was started
	// Returns: elapsed time
	// EXAMPLES:
	// Timer t;
	// t.elapsedTime()
	unsigned long elapsedTime() {
		return ((unsigned long)clock() - startTime) / CLOCKS_PER_SEC;
		}

	};



//******************************Common functions*****************************************************************************

// updateExpPts : int -> int 
// Purpose: When called This function updates the experience points gained by the player by adding/subtracting the supplied value to the previous exp points
// Returns: updated experience points (uep).
// Example: updateExpPts(1000);
int updateExpPts(int uep){
	expPoints += uep;
	return uep;
	}

// updateHealth : int -> int 
//Purpose: When called This function updates the health gained by the player by adding/subtracting the supplied value to the previous health points. It also takes into account the exp points, and adjusts health accordingly.
// Returns: updated health(h).
// Example: updateHealth(50);
//          updateHealth(-20);
int updateHealth(int h){
	if (expPoints > 1000 && expPoints < 2000)
		health = health + 50;
	if (expPoints >= 2000 && expPoints < 2500){
		healthMax = 120;
		health = health + 50;
		}
	if (expPoints >= 2500 && expPoints < 4000){
		healthMax = 150;
		health = health + 50;
		}
	if (expPoints >= 4000){
		healthMax = 250;
		health = health + 200;
		}
	health = health + h;
	return health;
	}



// checkHelp : string -> int
// Purpose: When called This function checks for the string passed on as argument. If sting is help, it returns 0, else it converts it into int and returns it.
// Returns: a number (int converted from string)
// Example: checkHelp(1);
//          checkHelp("help");
int checkHelp(string choice){
	int c = 0;
	try{
		if (choice == "HELP" || choice == "help" || choice == "hlp")
			{
			c = 0;
			}
		else {

			c = stoi(choice);
			//	throw 20;
			}

		}
	catch (std::invalid_argument& e){
		// if no conversion could be performed
		c = 9;
		}
	catch (std::out_of_range& e){
		// if the converted value would fall out of the range of the result type 
		// or if the underlying function (std::strtol or std::strtoull) sets errno 
		// to ERANGE.
		c = 9;
		}

	return c;
	}


// fnhelp : null -> null
// Purpose: When called This function displays the help content to the user.
// Returns: null
// Example: fnhelp();
void fnhelp(){
	

	if (finalbattle){
		

		cout << "\n****************HELP*******************\n\nYou can attack with punch and evade to go away from joker.\nYou can throw batarangs at joker\nYou can jump on to gargoyle and stay there to gain some health.\nYou can evade if joker comes near you so that he won\'t grab and throw you.\nThrowing causes lot of damage to you. Joker can attack you with various gadgets like Joker gun, Joker explosive teeth etc.\nWatchout when Joker attacks you with TITAN CHARGE. He comes near you.\nYou deal 50 damage to joker if you take him down.But you risk going near to joker. He can grab you if you are near.\n\nPunch attack causes damage between 25 to 45.\nBatarangs cause 25 damage. Your attacks deal more damage now.! Also your heath might increase if you have gained enough experience points.\n\nPress enter to continue...";
		}
	else{
		clearscreen();
		cout << "\n****************HELP*******************\n\n********You are the BATMAN.*******\n\nYour aim is to go through the stages and find and defeat joker.\n\nYou will face some enemies and obstacles.\nYou can select the given choices by pressing its corresponding key.(number)\n\nAs you go through you gain experience points (Your game score).\nYour decision determine your experience points.\nMore experience you earn, you will get powerups like increase in health increase in attack, etc..\nYou begin with 100 health points and 0 experience points.If health falls below 0, you die!\n\nYou have the following gadgets which you will be able to use when you require them:\n1.Batarang(causes 20 damage to all enemies you are fighting) \n2.Batclaw(use it to jump on the gargoyle and gain health). Do not stay there for more than 30 sec. Gain 1 health each second.) \n3.Cryptographic sequencer(to open locked doors)\n\nIn the combat mode you can take down the enemies when their health falls below 30.\nIf you try to takedown an enemy when its health is not less than 30,\nit my counter and attack you.\nInmates die when thier health reaches 0.\n\nPress enter to continue...";
		getchar();
		}

	getchar();

	}



//**************************************************************************************************
//************FUNCTIONS FOR BATTLE start BELOW***********************************************///
//**************************************************************************************************


// fnbatarangAttack : null -> null
// Purpose: When called This function checks if its the final battle and displays the damage a batarang causes to joker or enemies. Reduces the enemy/joker health by defined value.
// Returns: null
// Example: fnbatarangAttack();
void fnbatarangAttack(){
	if (finalbattle){
		cout << "\n\nYou attack Joker with batarang and cause " << attackBatarang << " damage.\n";
		}
	else{
		for (int i = 0; i < 3; i++)
			{

			if (enemyhealth[i] > 0){
				cout << "\n\nYou attack enemy " << i + 1 << " with batarang and cause " << attackBatarang << " damage.\n";
				enemyhealth[i] = enemyhealth[i] - attackBatarang;
				}

			}

		}
	getchar();
	}


// fnbatclaw : null -> null
// Purpose: When called, this function checks loads the recovery mode of player. Player recovers health this time.
// Returns: null
// Example: fnbatclaw();
void fnbatclaw(){
	if (gargoyle != true){
		cout << "\nGargoyle is broken now. You cannot Jump on it again.!";
		getchar();
		}
	else{

		cout << "\nYou fired batclaw to jump on to the gargoyle.\nYour health INCREASES by 1 each passing second.\nDo not stay here for more than 30 sec or enemies will find you.\nEnemies cannot attack you this time.\n\nPress enter to jump off gargoyle....\n\n";
		onGround = false;
		unsigned long gargoyleBreak = 29;//in seconds
		Timer t;
		t.start();

		while (!_kbhit())
			{

			if (t.elapsedTime() > gargoyleBreak)

				{
				gargoyle = false;

				break;
				}

			}

		if (gargoyle) {
			health = health + int(t.elapsedTime()) - 1;

			cout << "You spent " << t.elapsedTime() - 1 << " sec on the gargoyle. Your health is increased by " << int(t.elapsedTime()) - 1;
			onGround = true;

			getchar();
			}
		else{
			if (finalbattle){
				cout << "\nYou stayed there for too long. Joker found you and grabs you.\nYou lose 20 health.!\n";
				health = health - 15;
				isnear = false;
				}
			else{
				cout << "\nYour enemies threw grenade at you. You stayed there for too long.\nYou lose 10 health.!\nYou fall and you are attacked\n";
				health = health - 10;
				//	getchar();

				}
			}
		}
	getchar();
	}


// fntakedown : null -> null
// Purpose: When called, this function takes down(defeats) the enemy or joker. and updates enemy health accordingly.
// Returns: null
// Example: fntakedown();
void fntakedown(){

	clearscreen();

	if (finalbattle){

		if (jokerhealth > 50){
		cout << "You try to Takedown joker. You dealt your maximum blow and caused 50 damage. You are near joker now";
		jokerhealth -= 50;
		isnear = true;
		//updateExpPts(1500);
		}
		else{
			cout << "You take down THE JOKER. You have defeated him. You gain 1500 exp points.";
			jokerhealth -= 50;
			updateExpPts(1500);
			isnear = false;
			}

		}
	else{

		while (1){
			string en;
			int enemyno;
			cout << "\n\nWhich enemy you want to take down? Enter 1,2 or 3: ";
			cin >> en;

			if (en == "1" || en == "2" || en == "3"){
				enemyno = stoi(en);

				if (enemyhealth[enemyno - 1] <= 0){
					cout << "\nYou cannot takedown a dead enemy. You wasted your turn! ";
					}

				else if (enemyhealth[enemyno - 1] < 30 && enemyhealth[enemyno - 1]>0)
					{
					cout << "\n\nYou took down enemy " << enemyno;
					enemyhealth[enemyno - 1] = 0;
					cout << "\nYou gain 300 exp points";
					updateExpPts(300);
					}
				else{
					int damage;
					cout << "\nYou cannot Take Down enemy " << enemyno << " now. Wait until his health falls below 30\n";
					damage = rand() % 25;
					cout << "\nEnemy " << enemyno << " countered you attack and dealt " << damage << " damage to you.";
					health -= damage;
					}

				break;
				}
			else{
				cout << "\nEnter either 1, 2 or 3";
				}
			}
		}
	getchar();

	}


// fndefendbatman : null -> null
// Purpose: When called, this function enables defence mode.. you defend yourself from enemy attack and take less damage.
// Returns: null
// Example: fndefendbatman();
void fndefendbatman(){
	clearscreen();
	if (finalbattle){
		cout << "\n\nYou evaded. You are now far away from joker.\n";
		isnear = false;
		}
	else{
		cout << "\n\nDefence mode enabled. Enemies cannot attack you now for 1 turn.\n";
		}
	defencemode = true;
	}

// fnJokerAttack : null -> null
// Purpose: When called, this function calculates the damage joker causes to player and reduces player health
// Returns: null
// Example: fnJokerAttack();
void fnJokerAttack(){

	clearscreen();
	int rndm = rand() % 4;
	int jokerattack;

	if (isnear){
		jokerattack = getjokerGrab();
		cout << "\nYou were grabbed by joker. He threw you on the wall. You took " << jokerattack << " damage.\n\n";
		health -= jokerattack;
		isnear = false;
		}
	else if (defencemode){
		jokerattack = 5;
		cout << "\nYou took only 5 damage from Joker as you evaded in previous turn.";
		health -= 5;
		isnear = false;
		defencemode = false;
		}
	else{

		switch (rndm){
			case 0:
				jokerattack = getjokerGun();
				cout << "\nJoker fired a gun at you. You took " << jokerattack << " damage.\n\n";
				health -= jokerattack;
				break;
			case 1:
				jokerattack = getjokerGun();
				cout << "\nJoker fired a gun at you. You took " << jokerattack << " damage.\n\n";
				health -= jokerattack;
				break;
			case 2:
				jokerattack = getjokerTeeth();
				cout << "\nJoker threw explosive teeth at you. You took " << jokerattack << " damage.\n\n";
				health -= jokerattack;
				break;
			case 3:
				jokerattack = getjokerTitanCharge();
				cout << "\nJoker ran into you and crushed you. You took " << jokerattack << " damage. Joker is NEAR you now.\n\nYou better evade.\n\n";
				health -= jokerattack;
				isnear = true;
				break;

			}
		}
	
	}


// fnEnemyAttack : null -> null
// Purpose: When called, this function calculates the damage enemies cause to player and reduces player health
// Returns: null
// Example: fnEnemyAttack();
void fnEnemyAttack(){

	clearscreen();

	for (int i = 0; i < 3; i++){
		if (enemyhealth[i] <= 0){
			//do nothing
			}
		else{

			if (defencemode){
				enemyAttack = 1;

				}
			else{
				enemyAttack = enemyAttackMin + rand() % (enemyAttackMax - enemyAttackMin);
				}

			cout << "\nYou were attacked by enemy " << i + 1 << ". You took " << enemyAttack << " damage.\n\n";
			health -= enemyAttack;


			}
		}
	defencemode = false;

	}


// fnfistAttack : null -> null
// Purpose: When called, this function calculates the damage you cause to joker/emenies with your punch and evade if attacking joker and reduce enemy/joker health accordingly
// Returns: null
// Example: fnfistAttack();
void fnfistAttack(){
	int random = rand() % 3;
	fistAttack = attackFistMin + rand() % (attackFistMax - attackFistMin);
	if (finalbattle){
		jokerhealth -= fistAttack;
		cout << "\n\nYou attacked Joker and caused " << fistAttack << " damage.";
		isnear = false;
		}
	else{
		if (enemyhealth[random] > 0){
			cout << "\n\nYou attacked enemy " << random + 1 << " and caused " << fistAttack << " damage.";
			enemyhealth[random] = enemyhealth[random] - fistAttack;
			}
		else
			{
			fnfistAttack();

			}
		}
	getchar();
	}



// fnbatmanMoves : null -> null
// Purpose: When called, this function displays player the choices of how he wants to attack the enemy or joker and calls other functions according to selected choice.
// Returns: null
// Example: fnbatmanMoves();
void fnbatmanMoves(){
	int move;
	if (finalbattle){

		cout << "\n\nWhat do you want to do?\n\n1. Explosive-Punch him and evade.\n2. Throw Batarangs at joker\n3. Use batclaw to jump over to gargoyle(recover health)\n4. Takedown the Joker(you risk going near joker)\n5. Evade and defend against next attack if joker is near\n\nEnter Your Choice: ";
		}
	else{
		cout << "\n\nWhat do you want to do?\n\n1. Attack with your Punch\n2. Attack with multiple batarangs\n3. Use batclaw to jump over to gargoyle(recover health)\n4. Takedown an enemy\n5. Defend Yourself from next attack\n\nEnter Your Choice: ";
		}
	cin >> choice;

	move = checkHelp(choice);

	switch (move){
		case 0:
			fnhelp();
			fnbatmanMoves();
			break;
		case 1:
			//cout << "\n You attacked an enemy with your fist.";
			fnfistAttack();
			break;
		case 2:
			//	cout << "\n You attack the enemies with batarangs.";
			fnbatarangAttack();
			break;
		case 3:
			//	cout << "\nYou used batclaw to jump on to the gargoyle. You can stay there for max 30 sec or it will break and you wont be able to use it again then. Enemies cannot attack you this time.\n\nPress any key to jump off gargoyle....\n\n";
			fnbatclaw();
			break;
		case 4:
			//	cout << "\nTakedown an enemy";
			fntakedown();
			break;
		case 5:
			//	cout << "\nYou Defend Yourself from next attack";
			fndefendbatman();
			break;
		default:
			cout << "\nYou must choose from given choices...";
			fnbatmanMoves();


		}

	}


// fndisplayHealth : null -> int
// Purpose: When called, this function display health of Batman and his Enemies/joker.
// Returns: 3 if all enemies are defeated, or 1 if joker is defeated.
// Example: fndisplayHealth();
int fndisplayHealth(){

	if (health <= 0)
		{

		cout << afterdeath();

		cout << "\n\nYou are dead! Play again...";
		deaths = 0;
		//getchar();
		}
	else{

		if (health >= healthMax)
			health = healthMax
			;

		cout << "\n\n\nYour Health : " << health;
		cout << "\nYour Exp Points : " << expPoints;

		if (finalbattle){
			if (jokerhealth <= 0){
				cout << "\n\nYou defeated the JOKER";
				//enemyhealth[i] = 0;
				deaths = 1;
				}
			else{
				cout << "\nRemaining health of JOKER is :" <<jokerhealth;
				}
			
			}
		else{

			for (int i = 0; i < 3; i++)
				{
				//int death = 0;
				if (enemyhealth[i] <= 0){
					cout << "\nEnemy " << i + 1 << " is dead";
					//enemyhealth[i] = 0;
					}
				else{
					cout << "\nRemaining health of enemy " << i + 1 << " is " << enemyhealth[i];
					}
				if (enemyhealth[0] <= 0 && enemyhealth[1] <= 0 && enemyhealth[2] <= 0){
					deaths = 3;

					}
				}
			}
		cout << "\n\nPress enter to continue... ";
		}
	getchar();
	return deaths;
	}



// fninmatesBattle : null -> null
// Purpose: When called, this function just displays the current stats .. to give uer time to prepare for battle.
// Returns: null
// Example: fninmatesBattle();
void fninmatesBattle(){
	clearscreen();
	cout << "\nYou are about to start fight with 3 inmates.\n\nYou will attack First....\n\nStats:\n\n";
	fndisplayHealth();
	}


//**************************************************************************************************
//************FUNCTIONS FOR BATLLE end*******************************************************///
//**************************************************************************************************




//**************************************************************************************************
//************LEVEL 1*******************************************************///
//**************************************************************************************************



// level1 : null --> null
// Purpose: When called This function loads level1. Player stays in this room, until he crosses all rooms in this level.(room 1,2 and 3)
// Returns: null
// Example: level1();
void level1(){
	level1stage1();
	level1stage2();
	level1stage3();	
	}



//**************************************************************************************************
//************Level 1 STAGE1***********************************************///
//**************************************************************************************************

// level1stage1 : null -> null
// Purpose: When called This function displays choices to the player at that stage of game and acts according to the given choices. loads room 1. 
// Returns: null
// Example: level1stage1();
void level1stage1(){

	int myints[] = { 0, 1, 2 };
	list<int> mylist(myints, myints + 3);

	string choices[] = { "1. Break the window to get into the holding cells",
		"2. Help the knocked out guards to see they are fine",
		"3. Ask Gordon break glass to follow the joker" };


	while (1){

		clearscreen();

		cout << "\n\n\nYou are locked out. There is a glass window in front of you. What do you do ?";

		cout << "\n\nYour choices are:\n";
		for (list<int>::iterator it = mylist.begin(); it != mylist.end(); ++it)
			cout << "\n" << choices[*it];
		cout << '\n';

		cout << "\n\nenter your choice: ";
		cin >> choice;

		int c = checkHelp(choice);


		switch (c){
			case 0:
				fnhelp();
				break;
			case 1:
				// exit while loop. and move to next step in execution. load level1stage2();
				break;
			case 2:
				cout << "\n you earn 100 exp points, but you need to catch joker.";
				updateExpPts(100);
				mylist.remove(1);
				getchar();
				getchar();
				break;
			case 3:
				cout << "\nHe is not strong enough to break the glass. He breaks his fist. You lose 200 experience points!";
				updateExpPts(-200);
				mylist.remove(2);
				getchar();
				getchar();
				break;

			default:
				cout << "\nYou cannot do that. Please choose from the given choices.";
				getchar();
				getchar();
				level1stage1();
				break;
			}

		if (c == 1)
			break;
		}



	getchar();

	}

//**************************************************************************************************
//************Level 1 STAGE2***********************************************///
//**************************************************************************************************


// level1stage2 : null -> null
// Purpose: When called This function displays choices to the player at that stage of game and acts according to the given choices. load room 2. Player stays in this room, until satisfactory response is obtained.
// Returns: null
// Example: level1stage2();
void level1stage2() {
	clearscreen();
	cout << "\n\nNow you are inside the Holding Cells area, the Joker springs his first trap,\nunlocking the holding cells and allowing Black gate convicts to flood out to attack Batman. \n\nWhat do you do now?\n";


	while (1){
		cout << "\n\n1. Fight the escaped convits\n";//– load battle"
		cout << "\n2. Go back to find some more security guards\n\n\nEnter your choice : ";

		cin >> choice;

		int c = checkHelp(choice);

		//cout << choice;

		switch (c){
			case 0:
				fnhelp();
				break;
			case 1:
				cout << "\n\nGet ready to battle...You gain 200 exp points for your courage.\n\nPress enter to start....";
				updateExpPts(200);

				break;
			case 2:
				cout << "\nYou are BATMAN. You should not be afraid to fight. You go back but find no guards. The inmates have caused a chaos and the convicts have freed other inmates.\nSome have escaped. You loose 200 exp points because of your carelessness. Now FIGHT them. ";
				updateExpPts(-200);
				// load battle
				break;

			default:
				cout << "\nYou cannot do that. Please choose from the given choices.";
				break;
			}


		if (c == 1 || c == 2){
			break;
			}
		}
	getchar();
	getchar();


	}


// level1stage3 : null -> null
// Purpose: When called This function loads room 3. battle begins. Player stays in this room, until he defeats all enemies. If player dies, this stage is loaded again.
// Returns: null
// Example: level1stage3();
void level1stage3(){
	fninmatesBattle();
	while (1){
		if (deaths == 3 || health <= 0)
			break;
		clearscreen();
		//fndisplayHealth();
		fnbatmanMoves();
		//
		fndisplayHealth();
		if (deaths == 3 || health <= 0)
			break;
		clearscreen();
		fnEnemyAttack();
		fndisplayHealth();
		}
	if (deaths == 3)
		{
		updateExpPts(1000);
		updateHealth(0);
		cout << "\1n\nYou have defeated all the enemies...\nYou gain 1000 exp points.\nYour health is increased by 50 for every 1000 exp points you gain.\n\n";
		fndisplayHealth();
		deaths = 0;
		//break;
		}
	else{
		clearscreen();
		cout << "\nBegining the battle again...Press enter to continue..";
		health = 100;
		enemyhealth[0] = 100;
		enemyhealth[1] = 100;
		enemyhealth[2] = 100;
		deaths = 0;
		level1stage3();
		}
	}




//**************************************************************************************************
//************LEVEL 1 END*******************************************************///
//**************************************************************************************************







//**************************************************************************************************
//************ LEVEL 2 *******************************************************///
//**************************************************************************************************


// level2 : null -> null
// Purpose: This function just gives a call to a function to load room 4.
// Returns: null
// Example: level2();
void level2(){
	level2stage1();
	}



// fnInvestigate : null --> null
// Purpose: When called This function loads room 6. Player stays in this room, until he chooses from the choices provided.
//          Investigate area for clues
// Returns: null
// Example: fnInvestigate();
void fnInvestigate(){

	clearscreen();
	cout << "\nOn Investigation of the area, you find a box. What do you want to do?";

	cout << "\n\n1. Open box.It could be a trap!A bomb! You never know.\n2. Investigate the box using Xray vision.\n\nEnter your choice: ";


	int move;

	

	while (1){

		cin >> choice;

		move = checkHelp(choice);
		switch (move){
			case 0:
				fnhelp();
				break;
			case 1:
				cout << "\n\nYou opened the box without inspection. It was boobytrapped and bomb went off..!\n\n\nYOU ARE DEAD...\n\n";
				cout << afterdeath();
				break;
			case 2:
				cout << "\n You see that the box is boobytrapped. You diffuse the bomb and open the box.\nYou find a card inside on which Its written KRJEO. What might that mean! Might be some kind of key.\n\nYou gain 300 exp points.\n\nGo to the door.. Press Enter";
				updateExpPts(300);
				getchar();
				
				break;
			default:
				cout << "\nYou must choose from given choices...\n\nEnter your choice: ";
				break;
			}
		getchar();
		if (move == 1){
		//	cout<< afterdeath();
			getchar();
			level2stage1();
			}
		if (move == 2)
			{
			fnOpenDoor();
			break;
			}
		}
}



// fnOpenDoor : null -> null
// Purpose: This function loads the stage where player is asked for the password. Player will stay at this stage in the game until correct password is given.  Loads Room 7
// Returns: null
// Example: fnOpenDoor();
void fnOpenDoor(){

	clearscreen();

	while (1){

		cout << "\nYou use the Cryptographic Sequencer to hack into the security mainframe.\n\nRe-arrange the letters written on the card to  \n\nENTER PASSWORD TO OPEN THE DOOR. _ _ _ _ _\n\n\n ***Type help to see what was written on card.***\n\n ENTER PASSWORD : ";

		string pwd;

		cin >> pwd;
		int c = checkHelp(pwd);

		if (c == 0){
			cout << "\n\nKRJEO was written on the card\n";
			getchar();
			}
		else {
			if (pwd == "JOKER" || pwd == "joker"){
				cout << "\n\nPASSWORD ACCEPTED... opening DOOR....\n";
				getchar();
				//getchar();
				break;
				}
			else
				{
				cout << "\n\nIncorrect password... Guess again...!\n\n\n";
				}
			}
		}
	//getchar();
	//level2end();
	}



// fnuseCGS : null -> null
// Purpose: This function prompts player that he needs to go back to investivate when cryptographic sequencer is used
// Returns: null
// Example: fnuseCGS();
// 
void fnuseCGS(){

	clearscreen();
	getchar();
	cout << "***************************************************************\n\nYou use cryptographic sequencer to hack into security\nsystem to open the door.Its password protected.\nYou need to decrypt the password to open the door\n\nSearch your surroundings for clues.\n\nPress enter to continue...";

	getchar();
	clearscreen();
		

	cout << "\n\n*************************************************************\n\nJoker : Come on, Bats, get crazy. It's the only way to beat me!....";
	getchar();
	fnInvestigate();

	
	}



// fncheckCGS : null -> null
// Purpose: This function provides choices to use a gadget. Check if Cryptographic sequencer is used , it moves to other room, else stays here. Load room 5. 
// Returns: null
// Example: fncheckCGS();

void fncheckCGS(){


	while (1){

			clearscreen();
		int move;
		cout << "\nYou reach a door which is locked. You need to use one of your gadgets.";
		cout << "\nYou have : \n1. Batarangs\n2. Batclaw\n3. Cryptographic sequencer\n4. Explosive Gel\n\n Enter your choice: ";
		cin >> choice;

		move = checkHelp(choice);

		//cout << choice;


		switch (move){
			case 0:
				fnhelp();
				break;
			case 1:
				cout << "\nYou Hit the door with Batarangs. It is still locked. Try other gadgets.";
				break;
			case 2:
				cout << "\n You pull the door with the Batclaw. \nYou are not strong enough to pull the door by yourself.\nYou loose 10 health.";
				updateHealth(-20);
				break;
			case 3:
				fnuseCGS();
				break;
			case 4:
				cout << "\n You try to blow up the door.It alarms the Joker and he send 3 inmates to fight you. Now fight them..! ";
				break;
			default:
				cout << "\nYou must choose from given choices...";
				fncheckCGS();
				break;

			}

		if (move == 1|| move==2)
			{
			getchar();
			getchar();
			}

		if (move == 3)
			break;

		if (move == 4)
			{
			getchar();
			getchar();
			enemyhealth[0] = 100;
			enemyhealth[1] = 100;
			enemyhealth[2] = 100;
			level1stage3();
			
			}
		
		}

	}



// level2stage1 : null -> null
// Purpose: This function loads room 4. Player provided with choices as he reaches a door. Choices lead to other rooms. Inappropriate choices leads the player to stay in this room only.
// Returns: null
// Example: level2stage1();
void level2stage1(){

	clearscreen();
	int myints[] = { 0, 1, 2, 3 };
	list<int> mylist(myints, myints + 4);


	string choices[] = { "1. Walk through the gate to find the joker.",
		"2. Investigate the area first",
		"3. Talk to Gordon.",
		"4. Call Alfred to track joker" };


	while (1){
		clearscreen();

		cout << "\n\nThe Joker deactivates a security gate in front of you.\n\nWhat do you do?";

		cout << "\n\nYour choices are:\n";
		for (list<int>::iterator it = mylist.begin(); it != mylist.end(); ++it)
			cout << "\n" << choices[*it];
		cout << '\n';

		cout << "\n\nEnter your choice: ";
		cin >> choice;

		int c = checkHelp(choice);

		switch (c){
			case 0:
				fnhelp();
				break;
			case 1:
				fncheckCGS();
				//fncheckcgs>>fnusecgs>>fninvestigate>>fnopendoor
				break;
				//move forward code;
			case 2:
				fnInvestigate();
				//investigate>>fnopendoor
				break;
			case 3:
				cout << "\n\nIt might be a trap batman. But you have to find out what the Joker is up to.\nFind a way to open the door!\n\n";
				mylist.remove(2);
				break;
			case 4:
				cout << "Alfred cannot track the joker. His satellite signals are being barred for some reason.\nHe tells you that the Joker is working with BANE on some TITAN serum.\nYou need to find out what it is.";
				mylist.remove(3);
				break;
			default:
				cout << "\nYou cannot do that. Please choose from the given choices.";
				level2stage1();
				break;
			}

		if (c == 1 || c == 2)
			break;
		if (c == 3 || c == 4){
			getchar();
			getchar();
			}
		}

	getchar();

	}


//**************************************************************************************************
//************ LEVEL 2 END *******************************************************///
//**************************************************************************************************



//**************************************************************************************************
//************ LEVEL 3 *******************************************************///
//**************************************************************************************************




// level3 : null -> null
// Purpose: This function calls the function to load rooms 8, 9 and 10. Player can reach here, only if cleared previous rooms. If a player dies in this room, This room is loaded again.
// Returns: null
// Example: level3();
void level3(){

	//getchar();
	clearscreen();
	cout << cutscenes(3);
	getchar();
	level3stage1();
	clearscreen();
	cout << cutscenes(4);
	getchar();
	clearscreen();
	//fnfinalbattle();
	while (1){
	
			
		fnfinalbattle();
		if (jokerhealth < 0){
			break;
			}
		clearscreen();
		cout << "\nBegining the battle again...Press enter to continue..";
		updateExpPts(1000);
		updateHealth(0);
		health = healthMax;
		jokerhealth = 300;
		getchar();
		//getchar();
		//getchar();
			
		
		}
	clearscreen();
	cout<<cutscenes(5);
	getchar();
	exit(0);
	}




// getters for joker attacks : null->int
// Purpose: When called int value specifying the damage joker causes.
// Returns: int value
// EXAMPLES:
// getjokerTitanCharge()
int getjokerTitanCharge(){
	return 10 + rand() % 5;
	}

// getters for joker attacks : null->int
// Purpose: When called int value specifying the damage joker causes.
// Returns: int value
// EXAMPLES:
// getjokerGun()
int  getjokerGun(){
	return 15 + rand() % 10;
	}

// getters for joker attacks : null->int
// Purpose: When called int value specifying the damage joker causes.
// Returns: int value
// EXAMPLES:
// getjokerTeeth()
int  getjokerTeeth(){
	return 7 + rand() % 5;
	}

// getters for joker attacks : null->int
// Purpose: When called int value specifying the damage joker causes.
// Returns: int value
// EXAMPLES:
// getjokerGrab()
int getjokerGrab(){
	return 40 + rand() % 25;
	}



// fnfinalbattle : null -> null
// Purpose: This function loads the final battle. Room 10. Last stage of the game. defeat the joker to end game.
// Returns: null
// Example: fnfinalbattle();
void fnfinalbattle(){

	clearscreen();
	cout << "\nYou are about to start fight with JOKER. You will attack First....\n\nStats:\n";

	gargoyle = true;
	updateHealth(0);
	deaths = 0;
	attackFistMin = 25; // min damage caused by fist increased
	attackFistMax = 45; // max damage caused by fist increased
	attackBatarang = 25; // Batarang attack increased
	
	finalbattle = true;
	fndisplayHealth();
	
	cout << "**********************************************************";
	fnhelp();
	


	while (1){
		if (deaths == 1 || health <= 0)
			break;
		clearscreen();
		//fndisplayHealth();
		fnbatmanMoves();
		//
		fndisplayHealth();
		if (deaths == 1 || health <= 0)
			break;
		clearscreen();
		fnJokerAttack();
		fndisplayHealth();
		}
	if (deaths == 1){

		updateExpPts(1000);
		//	updateHealth(0);
		cout << "\n\nYou have defeated the Joker...\nYou gain 1000 exp points.\n\n";
		fndisplayHealth();
		deaths = 0;


		}
	}



// level3stage1 : null -> null
// Purpose: This function loads the Room 8. You have to choose from given choices. calls other functions accordingly.
// Returns: null
// Example: level3stage1();
void level3stage1(){

	//clearscreen();

	while (1){
		clearscreen();
		cout << "\nYou go through the door and find that Joker has recaptured Gordon.\n\nHe tries to shoot him with a titan - filled gun. What do you do ?\n\n1. Destroy the titan containers that joker has created\n2. Attack the joker\n3. Jump in the way of the titan filled dart and take the shot yourself.";

		cout << "\n\nEnter your choice: ";
		cin >> choice;

		int c = checkHelp(choice);

		switch (c){
			case 0:
				fnhelp();
				break;
			case 1:
				cout << "\n\nYou can do that later. Atleast try to save Gordon...";
				getchar();
				//fncheckCGS();
				break;

			case 2:
				cout << "\n\nYou let Gordon take the shot. He mutates into a monster and he kills you. You die ! Try again ...\n\n";
				cout << afterdeath();
				//fnInvestigate();
				getchar();
				//			mylist.remove(1);
				break;
			case 3:
				//fnantidote();
			//	ret = 3;
				fnantidote();
				//call antidote function.
				break;

			default:
				cout << "\nYou cannot do that. Please choose from the given choices.\n";
				getchar();
				break;
			}
		getchar();
		if (c == 3)
			break;
		}
	//return ret;

	}



// fnantipwd : null -> null
// Purpose: This function loads Room 9. Player has to enter a password for antidote capsule, or it self destructs in 30 sec.
// Returns: null
// Example: fnantipwd();
void fnantipwd(){

	unsigned long selfdestruct = 30;//in seconds
	Timer t1;
	string pwd;
	bool die = false;
	t1.start();

	cout << "\n\nTime remaining : " << selfdestruct - t1.elapsedTime() << " seconds";
	cout << "\n\n**type HELP if you dont know the answer***\n\n";
	cout << "\n\nEnter your(batman\'s) first name to stop timer: ";


	while (!_kbhit()){
		if (t1.elapsedTime() > selfdestruct)
			{
		//	cout << "YOU ARE DEAD....";
			die = true;
			break;
			}

		}
	
	while (1)
		{
		if (die){
			cout << "\n\nYou couldnot take the antidote in time...You become a monster! and explode! You are DEAD....Play again!!";
			cout << afterdeath();
			break;
			}
		else{
			cin >> pwd;
			//getchar();

			//cin >> pwd;

			int c = checkHelp(pwd);

			if (c == 0){
				cout << "\n\n******************************************************\n\n";
				cout << "\n\nYour last name is WAYNE. Your first name is a 5 letter word. It starts with B.\n\nYou share your first name with the lead actor of DIE HARD movies. Still dont know? google it!.\n\nTIME IS RUNNING OUT..";
				cout << "\n\n******************************************************\n\n";
				cout << "Enter your first name to stop timer: ";
				//getchar();
				}
			else if (pwd == "BRUCE" || pwd == "bruce"){
				cout << "\n\nPASSWORD ACCEPTED... Timer stopped...\n";
				cout << "You gain 1000 exp points.";
				updateExpPts(1000);
				die = false;
				break;
				}
			else
				{
				int timerem = selfdestruct - t1.elapsedTime();

				if (timerem <= 0){
					timerem = 0;
					}

				cout << "\n\nIncorrect password... Guess again...!";
				cout << "\n\nTime remaining : " << timerem <<" seconds";
				cout<<"\n\nEnter your first name to stop timer: ";
				
				}

			if (t1.elapsedTime() > selfdestruct)

				{
				cout << "\n\nYou couldnot take the antidote in time...You become a monster!....Play again!!";
				getchar();
				die = true;
				break;
				}

			}
	

		}
	if (die){
	//	getchar();
		getchar();
		fnantidote();
		}

	}



// fnantidote : null -> null
// Purpose: This function is called after player selects to jump in way of titan dart. Diplays instructions to open antidote capsule.
// Returns: null
// Example: fnantidote();
void fnantidote(){
	clearscreen();
	cout << "\n\nYou jump in the way of the titan filled dart and take the shot yourself. You begin to mutate. You need an antidote.";

	cout << "\n\nYou call Alfred for an Antidote. \n\nAlfred has sent the antidote capsule but he has encrypted it with a password. \n\nYou need to enter the password in 30 sec or the capsule will self destruct...and you will become a monster..!\n\nPress enter to start timer....\n\n";

	getchar();
	getchar();
	cout << "Your timer begins in :\n\n";
	for (int i = 5; i > 0; i--){
		cout << "\n" << i;
		std::this_thread::sleep_for(std::chrono::milliseconds(1000));
		}
	fnantipwd();
	getchar();	
	}


//**************************************************************************************************
//************ LEVEL 3 END*******************************************************///
//**************************************************************************************************



//**************************************************************************************************
//************ TESTS *******************************************************///
//**************************************************************************************************

//test cutScenes
string testCutscenes(int a){
	string s = cutscenes(a);
	string s1 = "";

	if (s != s1)
		return "\ntest testCutscenes passed";
	else
		return "\ntest testCutscenes failed";
	}

//test afterDeath
string testAfterdeath(){
	string s = afterdeath();
	string s1 = "";

	if (s != s1)
		return "\ntest testAfterdeath passed";
	else
		return "\ntest testAfterdeath failed";
	}


//test checkHelp
string testCheckHelp(string s){
	if (checkHelp(s)==0)
		return "\ntest CheckHelp passed";
	else
		return "\ntest CheckHelp failed";
	}

//test updatehealth
string testUpdateHealth(int h){

	int h1 = health + h;
	if (updateHealth(h) == h1)
		return "\ntest updatehealth passed";
	else
		return "\ntest updatehealth failed";
	}

//test updatehealth
string testUpdateHealth2(int h){

	expPoints = 1200;
	int h1 = health + h + 50;
	if (updateHealth(h) == h1)
		return "\ntest updatehealth with 1200exp passed";
	else
		return "\ntest updatehealth with 1200exp failed";
	}

//test updateexppoints
string testUpdateExpPts(int exp){
	
	int exp1 = expPoints + exp;
	if (updateExpPts(exp) == exp1)
		return "\ntest updateexppoints passed";
	else
		return "\ntest updateexppoints failed";
	}


// function to begin testing...
void tests(){
	cout<<testCutscenes(4);
	cout<<testAfterdeath();
	cout<<testCheckHelp("help");
	cout<<testUpdateHealth(20);
	cout<<testUpdateExpPts(20);
	cout<<testUpdateHealth2(20);
	cout << "\n\npress enter to continue...";
	}



//**************************************************************************************************
//************ MAIN *******************************************************///
//**************************************************************************************************


//main function. 
//Loads level 1, 2 and 3
//returns null
int main(int argc, char * const argv[]) {

	// uncommment below 3 lines if you want to run tests first.
    // tests();
    //getchar();
	//clearscreen();
	cout << cutscenes(1);
	cout << "\n\n****Type HELP anytime to see what all you can do in the game****\n\nPress enter to continue...";
	getchar();
	level1();
	clearscreen();
	cout << cutscenes(2);
	getchar();
	level2();
	level3();

	return 0;
	}


