/*
 * Program title: WOMstory.java
 * Author: CEH Emma Civello, CA Alicia Chen
 * Description: The text-based portion of Wrath of Manasses
 */

package wrathOfManasses;

import java.util.*;	// import statements

public class WOMstory {	// class header

	private Scanner carl;
	private String charName;	// the name the character chooses
	private String gender;	// gender the character chooses
	private int money;
	private ArrayList<String> inventory;
	private ArrayList<PartyMember> partyMembers;	// possibly change to a custom object (Character)
	// add sprite image name later
	
	public WOMstory() {	// constructor
		carl = new Scanner(System.in);
		charName = "";
		gender = "";
		money = 20;
		inventory = new ArrayList<>();
		partyMembers = new ArrayList<>();
	}
	
	public static void main(String[] args) {	// main method header
		
		WOMstory wom = new WOMstory();	// class instance
		wom.run();
		
	}

	private void run() {
		
		charSelection();
		castleScene();
		
	}
	
	private void charSelection() {	// name/gender selection
		
		/*
		while(!gender.toLowerCase().equals("girl") && !gender.toLowerCase().equals("boy")) {
			
			System.out.print("Would you like to look like a BOY or a GIRL? ");
			gender = carl.nextLine();
			
			if(!gender.toLowerCase().equals("girl") && !gender.toLowerCase().equals("boy")) {
				System.out.println("Please choose one of the above choices. \n");
			}
			
		}
		System.out.println("\n");
		
		System.out.println("The wizard Mercurion walks into his study and sees you standing there. \n");
		pauseText(2);
		System.out.println("MERCURION: *rubs eyes* My apologies, I didn't see you there. \n");
		pauseText(2);
		System.out.println("Mercurion walks over to his desk and picks up a stack of paper. \n");
		pauseText(2);
		System.out.println("MERCURION: You forgot to sign the report you gave me yesterday. Mind doing it now? \n");
		System.out.print("What is your name? ");
		charName = carl.nextLine();
		System.out.println("\nMERCURION: Thank you, " + charName + ". ");
		pauseText(2);
		*/
		
		charName = "Rick Astley";
		gender = "boy";
		
	}
	
	private void castleScene() {	// castle scene
		
		String ucname = charName.toUpperCase();	// for ease of formatting
		
		if(charName.equals("MERCURION")) {
			System.out.println("MERCURION: *laughs* The fact that we share the same name will never fail to amuse me. \n");
			pauseText(2);
			System.out.print(ucname + ": It's definitely funny. ");
		}
		else {
			System.out.print(ucname + ": It's no problem, Mercurion. ");
		}
		System.out.println("What task do you want me to do today? \n");
		pauseText(2);
		System.out.println("MERCURION: Could you please gather some Rust Weed? We need it for the next potion. There's \n"
						 + "a good patch of it near the Forest of Babbage. You know where that is- on the West edge of \n"
						 + "the castle grounds. Take some of these leather satchels, and remember to only choose the weeds \n"
						 + "with the reddest stalks. The others won't be ripe enough for our purposes. Actually, just take \n"
						 + "one bag. Filling this should be enough. \n");
		pauseText(4);
		System.out.println("Mercurion hands you a satchel. \n");
		pauseText(1);
		
		inventory.add("Leather satchel");	// add item to inventory ArrayList
		System.out.println("ITEM GET! Leather satchel x1 has been added to your inventory. \n");	// maybe add an option later to check the inventory whenever
		pauseText(1);
		
		int choice = -1;
		while(choice != 1 && choice != 2) {
			System.out.print("Choose something to say. \n"
							 + "\t1) Yes, of course. I will return shortly. \n"
							 + "\t2) Actually, I was planning on learning some spells today. \n"
							 + "Your choice? ");
			choice = carl.nextInt();
			System.out.println();
			
			switch(choice) {
			case 1:	// collection path
				System.out.println("MERCURION: Excellent. Do be careful in the forest, I hear that the poison ivy's gotten \n"
								 + "out of control lately. \n");
				pauseText(2);
				System.out.println("You leave the castle and head towards the Forest of Babbage, collecting all of the \n"
								 + "rust weed that you can find. ");
				
				inventory.add("Bundle of Rust Weed");
				pauseText(2);
				
				System.out.println("All of a sudden, you hear the noise of a loud explosion coming from the castle. You \n"
								 + "are suddenly thankful that you took this job. It might've been dull, but you are safe \n"
								 + "out here. The same cannot be said for the people inside. \n");
				pauseText(3);
				System.out.println(ucname + ": Oh no- I should probably go check on everyone. \n");
				pauseText(2);
				System.out.println("As you approach the castle, you can see that the whole structure is shaking. A wave of \n"
								 + "gray seems to be spreading - seeping up the oaken front doors and across the water of \n"
								 + "the moat. \n");
				pauseText(3);
				System.out.println("MERCURION: " + charName + "! Up here!");
				pauseText(2);
				System.out.println(ucname + ": Mercurion! What's going on? I don't understand- \n");
				pauseText(2);
				System.out.println("Mercurion's entire body shudders. The old man is trying his best to steady himself, \n"
								 + "but it seems to be getting harder for him to do so with each passing second. With a \n"
								 + "shaky voice, he shouts out the window to you. \n");
				pauseText(3);
				System.out.println("MERCURION: This- this is Manasses's doing. " + charName + ", go to the University of \n"
								 + "Meyerstern and find- \n");
				pauseText(2);
				System.out.println("There is a great pause. The man in the window seems to be fighting off a force- one \n"
								 + "that is invisible, but no less powerful than something that can be seen. \n");
				pauseText(3);
				if(charName.equals("Ruthard"))
					System.out.println("MERCURION: *gasps* Go to Meyerstern. Find my old friend, Ruthard. Yes, he has the \n"
									 + "same name as you. No, I will not... be taking any more questions. \n");
				else
					System.out.println("MERCURION: *gasps* Go to Meyerstern. Find my old friend, Ruthard. \n");
				pauseText(2);
				System.out.println("The gray wave makes its way towards you. You look down in horror as the nearby grass \n"
								 + "begins to turn into stone. \n");
				pauseText(3);
				System.out.println("MERCURION: Mey- Meyerstern. Ruthard. Go now. It'll be alright, " + charName + ". ");
				pauseText(2);
				System.out.println(ucname + ": I will. \n");
				pauseText(2);
				System.out.println("You turn around and run away from the gray boundary. You glance backwards, and see a \n"
								 + "perfect stone replica of Mercurion standing near the window where he once was. Thankfully, \n"
								 + "as you reach the edge of the castle grounds, the spell stops spreading. This wicked curse \n"
								 + "has chosen not to leave the castle grounds. \n");
				pauseText(4);
				System.out.println("You jog through the woods until you reach the main road, the one that stretches across \n"
								 + "Adalos and touches the seas on either side. You stop at the edge of the cobblestone, the \n"
								 + "name echoing in your head. \"Ruthard.\" The University of Meyerstern is not terribly far, \n"
								 + "and you are dressed adequately, in a leather vest, cotton tunic, and fine boots. \n");
				pauseText(4);
				System.out.println("However, you are slightly hungry, and having faster transportation would not hurt. You \n"
								 + "might be able to borrow a horse in the local town, Edenshel, since their stables owe \n"
								 + "the castle a number of favors. You could also pick up a simple set of armor. However, \n"
								 + "Mercurion was insistent: find Ruthard... \n");
				pauseText(4);
				
				choice = -1;
				while(choice != 1 && choice != 2) {
					System.out.print("Choose an action. \n"
							 + "\t1) Go to the town to stock up on supplies. \n"
							 + "\t2) Head straight to the University of Meyerstern. \n"
							 + "Your choice? ");
					choice = carl.nextInt();
					System.out.println();
					switch(choice) {
					case 1:
						townScene();	// method for town scene
						break;
						
					case 2:
						universityScene();	// method for university scene
						break;
					}
					

					if(choice != 1 && choice != 2)
						System.out.println("Please choose a valid answer. \n");
					
				}
				break;
				
			case 2:	// staying in the castle
				System.out.println("MERCURION: Remember that not all magic is waving wands and casting spells. You need \n"
						 		 + "to put in the hard work, and that involves some manual labor. Because you did well \n"
						 		 + "with potion-making yesterday, I will assign you something more \"interesting\" today, \n"
						 		 + "but in the future, I may not always let you off- \n");
				pauseText(4);
				System.out.println("A sudden explosion interrupts his sentence. \n");
				pauseText(3);
				System.out.println("MERCURION: Get down! \n");
				pauseText(2);
				System.out.println("Both of you shelter beneath an old oak-wood table as the castle shakes violently. \n");
				pauseText(3);
				System.out.println("MERCURION: I am going to cast a protection spell around us, but if what I think is \n"
								 + "happening is indeed happening, my spell will not hold for long. \n");
				pauseText(2);
				
				choice = -1;
				while(choice != 1 && choice != 2) {
					System.out.print("Choose an action. \n"
							 + "\t1) Panic and dash towards the door. \n"
							 + "\t2) Get closer to Mercurion, so his spell around you will be stronger. \n"
							 + "Your choice? ");
					choice = carl.nextInt();
					System.out.println();
					switch(choice) {
					case 1:	// flee
						System.out.println("Your leather boots pound loudly on stone as you crash towards the hallway. \n"
										 + "From everywhere yet nowhere, a crashing, harsh voice sounds one word with \n"
										 + "dreadfully happy glee: \"Free. Free. Free.\" The floor seems to tilt and the \n"
										 + "air thickens as this voice--this being--asserts its will on the castle. \n"
										 + "Slowly, you lose the ability to scream (which you barely realized you were \n"
										 + "doing anyhow). \n");
						pauseText(4);
						System.out.println("Once in the hallway, you also lose the ability to use your legs. Your body \n"
										 + "stiffens quickly until it matches the stone of the floor. As the voice continues \n"
										 + "its joyous chant, you realize that you may be here for a few minutes \n"
										 + "or for an eternity, watching the castle's lawn dissolve into chaos beyond the \n"
										 + "hallway windows. Either way, events are no longer in your control. \n");
						pauseText(4);
						System.out.println("\n-=~[GAME OVER]~=-");
						break;
						
					case 2:	// get closer to Mercurion
						System.out.println("Mercurion begins, while simultaneously moving his hands in the air so that a \n"
										 + "forcefield of sorts takes form around you. \n");
						pauseText(3);
						System.out.println("MERCURION: Many eons ago, there was a great battle between the alchemists Turin \n"
										 + "and Manasses, the founders of Adalos. Together, they fought to free this land \n"
										 + "from the control of an enemy lord, and were eventually successful. ");
						pauseText(4);
						System.out.println("However, as history has proven time and time again, men are easily corrupted by \n"
										 + "power. Such was the fate of Manasses, who became obsessed with spreading their \n"
										 + "revolutionary ideals to neighboring lands by any means necessary. Countless \n"
										 + "innocents died on the edge of his blade before his old friend realized what he had \n"
										 + "become.");
						pauseText(4);
						System.out.println("Turin swore to put a stop to this senseless bloodshed. He banished Manasses, locking \n"
										 + "him in a cell deep, deep beneath this castle. According to most prophecies, Manasses \n"
										 + "would eventually escape. However, these prophecies failed to mention that he would do \n"
										 + "so today. \n");
						pauseText(4);
						
						choice = -1;
						while(choice != 1 && choice != 2) {
							System.out.print("Choose something to say. \n"
									 + "\t1) How do you know that this curse is Manasses's doing? \n"
									 + "\t2) Will we be safe? \n"
									 + "Your choice? ");
							choice = carl.nextInt();
							System.out.println();
							switch(choice) {
							case 1:	// it is manasses because
								System.out.println("Mercurion glances towards you with a dreadful certainty. \n");
								pauseText(3);
								System.out.println("MERCURION: Very few alchemists have the power to shake this castle's foundations. \n"
												 + "I am one of them, which makes the other options even fewer. \n");
								pauseText(2);
								System.out.println("He pauses upon seeing the questioning glance on your face. A twinge of sadness shows on his face. \n");
								pauseText(3);
								System.out.println("MERCURION: But I am not powerful enough to make it stop shaking once it has started. \n");
								pauseText(2);
								System.out.println("You look up, wondering how long the shimmering protective spell will last, when \n"
												 + "you notice that the table over your head is no longer oak but stone. \n");
								pauseText(3);
								System.out.println("MERCURION: Ah, yes. It has already begun. Manasses was known for his power across spell \n"
												 + "disciplines, but his most famous ability was with stone-turning spells- he could turn \n"
												 + "objects to stone, bodies to stone, and most impressively, he came close to petrifying \n"
												 + "time and the soul itself. ");
								pauseText(4);
								System.out.println("That is one reason, among many, that Turin fought him. Manasses planned to do awful \n"
												 + "things with that power. He wanted to control the world - to remake it for \"the \n"
												 + "better\" - and he had a tyrant's definition of \"better.\"\n");
								pauseText(4);
								System.out.println("Then, Mercurion's protection gives way, and Manasses's spell engulfs the two of you. \n"
												 + "You realize that you may be here for a few minutes or for an eternity, mulling over the \n"
												 + "alchemist's last, grim lesson. Either way, it is no longer in your control. \n");
								pauseText(4);
								System.out.println("\n-=~[GAME OVER]~=-");
								break;
								
							case 2:	// "Heck no boi, we screwed." - Mercurion, 2021 :D
								System.out.println("Mercurion glances towards you with great sadness. \n");
								pauseText(2);
								System.out.println("MERCURION: This particular protection spell is the strongest, but against the strongest \n"
												 + "alchemist, I doubt that much will hold. \n");
								pauseText(2);
								System.out.println("You look up at the shimmering forcefield. Cracks are beginning to appear in it. \n"
												 + "Mercurion is visibly struggling, beads of sweat rolling down his face. \n");
								pauseText(3);
								System.out.println("MERCURION: By locking him up for so long, we may have inadvertently given him time to \n"
												 + "study his powers. When I release this spell, I suspect we will both turn into stone. The \n"
												 + "state may not be permanent, but that is out of our control. I am sorry. \n");
								pauseText(4);
								
								choice = -1;
								while(choice != 1 && choice != 2) {
									System.out.print("Choose an action. \n"
											 + "\t1) Ask Mercurion if there's anything that you can do. \n"
											 + "\t2) Allow the hopelessness of the situation to overtake you. \n"
											 + "Your choice? ");
									choice = carl.nextInt();
									System.out.println();
									switch(choice) {
									case 1:	// take action
										System.out.println(ucname + ": Is there anything I can do? \n");
										pauseText(2);
										System.out.println("Your question seems to shake the alchemist from his dark thoughts. His eyes \n"
														 + "brighten suddenly. \n");
										pauseText(3);
										if(charName.equals("Ruthard"))
											System.out.println("MERCURION: Go find my old friend, Ruthard. Yes, he has the same name as you. No, I never \n"
															 + "understood either.");
										else
											System.out.println("MERCURION: Go find my old friend, Ruthard. ");
										pauseText(2);
										System.out.println(ucname + ": What? ");
										pauseText(2);
										System.out.println("MERCURION: You'll be able to find him at the University of Meyerstern - he's \n"
														 + "a professor there. Just give me a moment. \n");
										pauseText(2);
										System.out.println("Mercurion begins to move his hands in a new pattern, and a faint golden sphere \n"
														 + "erupts around you. The man's hands are shaking so badly that you worry he will \n"
														 + "not be able to carry out whatever he plans to do. Yet somehow, they keep moving \n"
														 + "in patterns through the air, and the golden light grows stronger. He locks eyes with you. \n");
										pauseText(4);
										System.out.println("MERCURION: Go find Ruthard, " + charName + ". Don't worry, it'll be alright. \n");
										pauseText(2);
										System.out.println("With the last of his energy, he makes a grand gesture toward the hallway and the \n"
														 + "golden sphere, with you inside, shoots out of his office, through the window, \n"
														 + "and out of the castle. \n");
										pauseText(4);
										System.out.println("The golden orb deposits you beyond the castle boundary, and you fall to your \n"
														 + "knees on the grass. Thankfully, the stone seems to have stopped spreading. This \n"
														 + "wicked curse has chosen not to leave the castle grounds. \n");
										pauseText(4);
										System.out.println("You jog through the woods until you reach the main road, the one that stretches across \n"
														 + "Adalos and touches the seas on either side. You stop at the edge of the cobblestone, the \n"
														 + "name echoing in your head. \"Ruthard\" The University of Meyerstern is not terribly far, \n"
														 + "and you are dressed adequately, in a leather vest, cotton tunic, and fine boots. \n");
										pauseText(4);
										System.out.println("However, you are slightly hungry, and having faster transportation would not hurt. You \n"
														 + "might be able to borrow a horse in the local town, Edenshel, since their stables owe \n"
														 + "the castle a number of favors. You could also pick up a simple set of armor. However, \n"
														 + "Mercurion was insistent: find Ruthard... \n");
										pauseText(4);
										
										choice = -1;
										while(choice != 1 && choice != 2) {
											System.out.print("Choose an action. \n"
													 + "\t1) Go to the town to stock up on supplies. \n"
													 + "\t2) Head straight to the University of Meyerstern. \n"
													 + "Your choice? ");
											choice = carl.nextInt();
											System.out.println();
											switch(choice) {
											case 1:
												townScene();	// method for town scene
												break;
												
											case 2:
												universityScene();	// method for university scene
												break;
											}
											

											if(choice != 1 && choice != 2)
												System.out.println("Please choose a valid answer. \n");
											
										}
										break;
										
									case 2:	// surrender
										System.out.println("All too soon, the inevitable arrives. The forcefield's cracks expand with a sharp \n"
														 + "snapping sound, and your body starts turning to stone. You realize that you may be \n"
														 + "here for a few minutes or for an eternity, mulling over the alchemist's last, grim \n"
														 + "lesson. Either way, events are no longer in your control. \n");
										pauseText(4);
										System.out.println("\n-=~[GAME OVER]~=-");
										break;
									}
									

									if(choice != 1 && choice != 2)
										System.out.println("Please choose a valid answer. \n");
									
								}
								
								break;
							}
							

							if(choice != 1 && choice != 2)
								System.out.println("Please choose a valid answer. \n");
							
						}
						
						break;
					}
					

					if(choice != 1 && choice != 2)
						System.out.println("Please choose a valid answer. \n");
					
				}
				
				break;
			}
			
			if(choice != 1 && choice != 2)
				System.out.println("Please choose a valid answer. \n");
			
		}
		
	}

	private void townScene(){
		System.out.println("After walking a little ways down The King's Road, you arrive in Edenshel.");
		pauseText(2);
		System.out.println("On both sides of the road, merchants display their wares across broad tables.");
		pauseText(2);
		System.out.println("Some are muttering, heads together, and you wonder if the explosion at the castle\n" 
						+"was loud enough for them to hear.");
		pauseText(3);
		System.out.println("However, gossip often occupies the townspeople,\n"
						+"and other merchants continue to shout the merits of their products.\n");
		pauseText(3);
		System.out.println("You have only the 20 dollars that was on your person when you left the grounds.\n"
						+"If you buy food, your ability to buy other items will be limited.\n");
		pauseText(3);
		String[]foods = {"bread and strawberry preserve", "venison", "dragon tenders"};
		int[]prices = {5, 10, 15};
		System.out.println("What would you like to do?\n"
		+"\t1) Purchase "+foods[0]+" ($"+prices[0]+")\n"
		+"\t2) Purchase "+foods[1]+" ($"+prices[1]+")\n"
		+"\t3) Purchase "+foods[2]+" ($"+prices[2]+")\n"
		+"\t4) Save my money");
		int op;
		do{
			op = carl.nextInt();
			if(op==1 || op==2 || op==3){
				inventory.add(foods[op-1]);
				System.out.println("\nITEM GET! "+foods[op-1]+" x1 has been added to your inventory. \n");
				money = money - prices[op-1];
				System.out.println("Monetary balance: $"+money+"\n");
			}
			else if(op != 4){
				System.out.println("Please choose a valid answer.");
			}
		}while(!(op==1 || op==2 || op==3 || op==4));
		System.out.println("You hurry deeper into town and find the stable building.\n");
		pauseText(2);
		System.out.println("There is a small table inside the door where the Stable Master sits.\n"
						+"You have come here once before, seeking his advice on an illness affecting\n"
						+"the castle's steeds. He recognizes you now.\n");
		pauseText(4);
		System.out.println("STABLE MASTER: Hello, "+charName+". How can I help you today?");
		pauseText(2);
		System.out.println(charName.toUpperCase()+": Stable Master, can you please lend me a horse?");
		pauseText(2);
		System.out.println("STABLE MASTER: For what purpose?\n");
		pauseText(2);
		System.out.println("At that moment, bells begin to ring--not the church bells of the Tamali faith\n"
						+"(which ring in beautiful melodies)--but a deeper gong. An urgent warning.");
		pauseText(3);
		System.out.println("The Stable Master's face pales.\n");
		pauseText(1);
		System.out.println("STABLE MASTER: *whispering* Oh no. Have you been at the castle recenly?\n"
						+"Is something happening there? Please, please no . . . ");
		pauseText(3);
		System.out.println("STABLE MASTER: The bells ring in pairs. 'Trouble on the horizon, not yet arrived.'\n"
						+"We are safe for the moment. Have you seen anything suspicious?");
		pauseText(3);
		System.out.println(charName.toUpperCase()+": Manasses. Mercurion says he has escaped his banishment,\n" 
						+"and so Mercurion sent me here--well, technically to the University of Meyerstern,\n"
						+"but I stopped off for supplies. Please, sir, I need a horse. I will return it,\n"
						+"but I am supposed to meet Ruthard at the university.\n");
		pauseText(4);
		System.out.println("The Stable Master's face is snowy white at this point, but he is also good under pressure.\n");
		pauseText(2);
		System.out.println("STABLE MASTER: Of course. A horse.\n");
		pauseText(2);
		System.out.println("The man disappears down the hallway and quickly returns, leading a gray steed.\n");
		pauseText(2);
		System.out.println("STABLE MASTER: Go, make haste. Would you like my son to come with you? He is an\n"
							+"accomplished stable hand--he can lead this horse quickly.\n");
		pauseText(3);
		System.out.println("1) Yes, thank you for the offer. I could use his assistance.\n"
							+"2) I appreciate your offer, but I will brave the journey alone.\n"
							+"Your choice? ");
		do{
			op = carl.nextInt();
			if(op==1){
				System.out.println("\nA moment later, the Stable Master's son appears\n"
									+"and introduces himself as Ahkal.\n");
				System.out.println("Ahkal has joined the party! \n");
				PartyMember Ahkal = new PartyMember("Ahkal");
				partyMembers.add(Ahkal);
			}
			else{
				System.out.println("STABLE MASTER: Very well. I wish you luck.\n");
			}
		}while(!(op==1 || op==2));
		System.out.println("Finally, you visit the armory. The head armorer is just looking the doors when you arrive.\n");
		pauseText(2);
		System.out.println(charName.toUpperCase()+": Please, sir, can I purchase some armor? It is urgent, and I will be quick.\n");
		pauseText(2);
		System.out.println("He looks like he is about to resist when he notices the castle's crest on your sash.\n");
		pauseText(3);
		System.out.println("ARMORER: *gruffly* Fine. The prices--\n");
		pauseText(2);
		System.out.println("He points to a sheet of parchment hanging on the wall.\n");
		pauseText(2);
		String[]armor = {"Helmet", "Chestplate", "Shield", "Nothing"};
		int[]armor_prices = {10, 15, 20, 0};
		System.out.println("What would you like to buy?\n"
						+"\t1) "+armor[0]+" ($"+armor_prices[0]+")\n"
						+"\t2) "+armor[1]+" ($"+armor_prices[1]+")\n"
						+"\t3) "+armor[2]+" ($"+armor_prices[2]+")\n"
						+"\t4) "+armor[3]+"\n");
		boolean go = false;
		do{
			op = carl.nextInt();
			if(op==1){
				if(money>=armor_prices[0]){
					inventory.add(armor[0]);
					System.out.println("\nITEM GET! "+armor[0]+" x1 has been added to your inventory. \n");
					money = money - armor_prices[0];
					System.out.println("Monetary balance: $"+money+"\n");
					go = true;
				}
				else{
					System.out.println("ARMORER: *irritated* You do not have enough to buy "+armor[0]);
				}
			}
			else if(op==2){
				if(money>=armor_prices[1]){
					inventory.add(armor[1]);
					System.out.println("\nITEM GET! "+armor[1]+" x1 has been added to your inventory. \n");
					money = money - armor_prices[1];
					System.out.println("Monetary balance: $"+money+"\n");
					go = true;
				}
				else{
					System.out.println("ARMORER: *irritated* You do not have enough to buy "+armor[1]);
				}
			}
			else if(op==3){
				if(money>=armor_prices[2]){
					inventory.add(armor[2]);
					System.out.println("\nITEM GET! "+armor[2]+" x1 has been added to your inventory. \n");
					money = money - armor_prices[2];
					System.out.println("Monetary balance: $"+money+"\n");
					go = true;
				}
				else{
					System.out.println("ARMORER: *irritated* You do not have enough to buy "+armor[2]);
				}
			}
			else if(op==4){
				go = true;
			}
			else{
				System.out.println("Please choose a valid answer.");
			}
		}while(go==false);
		System.out.println("Now, equipped for your journey, you head toward the University of Meyerstern.");
		universityScene();
		//call to At the University method
	}//TownScene
	
	private void universityScene() {	// university scene
		
		System.out.println("After a long journey, you finally end up at the gates of the University of Meyerstern. ");
		pauseText(2);
		
		// partyMembers.add(new PartyMember("Ahkal"));	// for debugging
		// System.out.print(partyMembers.size());
		
		if(checkMember("Ahkal")) {	// debug this section, the method isn't working (problem with equals()?)
			System.out.print("\nAHKHAL: I'll wait outside with the horses. You go meet this Ruthard fellow. ");
			pauseText(2);
			System.out.println(charName.toUpperCase() + ": Thank you, Ahkal. I'll try to be quick. \n");
			pauseText(2);
		}
		else {
			System.out.print("You tie the horses to a nearby post and head inside. \n");
			pauseText(2);
		}
		
		System.out.println("You walk up to the front door, but they're locked. You groan in dismay. \n"
						 + "Fortunately, a woman in blue exits through a side door and lets you in. \n");
		pauseText(3);
		System.out.println("WOMAN IN BLUE: Welcome to the University of Meyerstern. Who are you, and how \n"
						 + "may I help you? ");
		pauseText(2);		
		System.out.println(charName.toUpperCase() + ": " + charName + ", a journeyman under the tutelage of the alchemist Mercurion. He sent \n"
						 + "me here to speak with Professor Ruthard. ");
		pauseText(2);

		if(charName.equals("Ruthard"))
			System.out.println("WOMAN IN BLUE: How fascinating, you two share a name. The professor has just returned "
							 + "from his break. You can find him in his office on the second floor. Please, come in. \n");
		else if(charName.equals("Melynas"))
			System.out.println("WOMAN IN BLUE: That's my name as well! Anyway, the professor has just returned from \n"
							 + "his break. You can find him in his office on the second floor. Please, come in. \n");
		else
			System.out.println("WOMAN IN BLUE: The professor has just returned from his break. You can find him \n"
						 	 + "in his office on the second floor. Please, come in. \n");
		
		pauseText(2);
		System.out.println("You follow the woman through the side door and up a grand staircase. She leads you to a closed door. \n");
		pauseText(2);
		System.out.println("WOMAN IN BLUE: His office is just through there. ");
		pauseText(2);
		System.out.println(charName.toUpperCase() + ": Thank you. \n");
		pauseText(2);
		System.out.println("The woman leaves, and you walk up to the door. You hesitate for a moment, but knock on it twice. \n");
		pauseText(2);
		System.out.println("RUTHARD: Just a minute, please. I'll be right with you! \n");
		pauseText(2);
		System.out.println("The door opens, and a middle-aged man with glasses smiles warmly at you. You feel a sense of relief, \n"
						 + "but also a twinge of uncertainty. The sense of relief comes from the office. It is warm, inviting, \n"
						 + "and quite organized - the professor obviously takes great pride in keeping everything tidy. However, \n"
						 + "you are unable to pinpoint why exactly you feel so uncomfortable. \n");
		pauseText(4);
		
		System.out.println("RUTHARD: Greetings, it's a pleasure to meet you! I apologize for the mess. Please, take a seat! What may I help you with? \n");
		if(charName.equals("Ruthard"))
			System.out.println("Melynas told me we share a name- how exciting! It's always a pleasure, meeting another Ruthard. \n");
		System.out.println();
		
		pauseText(2);
		System.out.println("You explain your quest to Ruthard. His smile disappears and is replaced by a frown. You are finally able to pinpoint \n"
						 + "where the feeling of uncertainty comes from - the professor seems kind, but there is something behind his eyes and \n"
						 + "his smile that seem to suggest that something less than helpful lies beneath the surface. However, you brush the feeling off. \n"
						 + "After all, it was Mercurion who told you to seek him out, and you trust the old alchemist as if he were your own father. \n");
		pauseText(3);
		System.out.println("RUTHARD: If it is as you say, and Manasses has returned, we are all in danger. ");
		pauseText(2);
		System.out.println(charName.toUpperCase() + "\n");
		pauseText(2);
		System.out.println("Ruthard briskly walks over to a bookshelf. He pulls out a chair and stands on it to reach the top shelf, which has lots \n"
						 + "of scrolls stacked on it. After finding the one he wants, he returns to the desk and places it on the table. It appears \n"
						 + "to be a rather detailed map of Adalos. \n");
		pauseText(3);
		System.out.println("RUTHARD: A few of my colleagues went on a quest. Many years ago. They went west to visit the Binary Trees \n"
						 + "of Utilio. These trees, it is said, hold great magical power. Apparently the person who destroys them is imbued with \n"
						 + "that power and becomes strong enough to face great foes. \n");
		pauseText(3);
		System.out.println("Ruthard points to a spot on the map with two trees drawn on it. It is labeled \"Binary Trees of Utilio\". \n");
		pauseText(2);
		System.out.println("RUTHARD: I believe that destroying these trees might be our best chance at stopping Manasses. But please be careful. \n"
						 + "One of my colleagues once travelled there to study them, but... let's just say that his quest wasn't successful. \n");
		pauseText(2);
	
		int choice = -1;
		while(choice != 1 && choice != 2) {
			System.out.print("Choose something to say. \n"
					 + "\t1) But your colleague... survived, didn't he? \n"
					 + "\t2) Are you sure that these trees are real? \n"
					 + "Your choice? ");
			choice = carl.nextInt();
			System.out.println();
			switch(choice) {
			case 1:	// did the colleague survive?
				System.out.println("RUTHARD: My colleague made it home safely, but... he wasn't in the best condition. Please, please believe me when I say \n"
								 + "that I wish there was a safer option, but I fear this is the only way. ");
				pauseText(2);
				break;
				
			case 2:	// are dem trees real
				System.out.println("RUTHARD: I am positive. They appear in almost every historical text about arborology - and herbology for that matter. \n"
								 + "Just because they are penciled into this map does not mean that they are any less real than the Forest of Babbage. \n");
				pauseText(2);
				System.out.println("Ruthard gestures to the location where the castle's forest is. Its label is written in beautiful ink calligraphy. \n");
				pauseText(2);
				System.out.println("RUTHARD: Please, please believe me when I say that I wish there was a safer option, but I fear this is the only way. ");
				pauseText(2);
				break;
			}

			if(choice != 1 && choice != 2)
				System.out.println("Please choose a valid answer. \n");
			
		}
		
		System.out.println(charName.toUpperCase() + ": Are you sure there isn't another way? ");
		pauseText(2);
		System.out.println("RUTHARD: The only other option I could think of is finding the Tome of Turin, but it has been lost for centuries. \n"
						 + "I hate to be a pessimist, but there is little hope of recovering it quickly enough to defeat Manasses. \n");
		pauseText(2);
		System.out.println("You are not entirely convinced that the quest is a safe or viable one. However, since Mercurion trusts Ruthard, you take \n"
						 + "his advice and agree to travel to the Binary Trees of Utilio. ");
		pauseText(3);
		System.out.println("RUTHARD: The world thanks you, " + charName + ". I wish you the best of luck on your journey. But before you go, let me give you \n"
						 + "something that might be of use. \n");

		pauseText(1);
		inventory.add("Flamethrower");	// add flamethrower to inventory ArrayList
		System.out.println("ITEM GET! Flamethrower x1 has been added to your inventory. \n");
		pauseText(1);
		
		System.out.println("RUTHARD: I'm calling this a \"flamethrower\". Has Mercurion ever mentioned that I take great interest in mechanical creations? \n");
		pauseText(2);
		
		choice = -1;
		while(choice != 1 && choice != 2) {
			System.out.print("Choose something to say. \n"
					 + "\t1) Uh... actually, he hasn't told me much about you. \n"
					 + "\t2) I don't think it ever came up in any of our conversations, no. \n"
					 + "Your choice? ");
			choice = carl.nextInt();
			System.out.println();

			if(choice != 1 && choice != 2)
				System.out.println("Please choose a valid answer. \n");
			
		}
		
		System.out.println("RUTHARD: Ah, well. I consider myself to have some skill in engineering, and I think that my latest invention might \n"
						 + "help you destroy the trees. \n");
		pauseText(2);
		System.out.println("You awkwardly take the device from Ruthard. It is made of metal, and quite heavy. \n");
		pauseText(2);
		System.out.println(charName.toUpperCase() + ": Thank you. This looks like a powerful weapon. ");
		pauseText(2);
		System.out.println("RUTHARD: It's still a prototype, but I'm working on developing a model that has more uses and requires less fuel. \n"
						 + "Feel free to take the map! Oh, and one last thing, would you like me to send one of my teaching assistants with \n"
						 + "you to aid you on your quest? \n");
		pauseText(3);
		
		choice = -1;
		while(choice != 1 && choice != 2) {
			System.out.print("Choose something to say. \n"
					 + "\t1) I would greatly appreciate that. \n"
					 + "\t2) I appreciate your kind offer, but I refuse. \n"
					 + "Your choice? ");
			choice = carl.nextInt();
			System.out.println();
			switch(choice) {
			case 1:	// Vertaine joins the party
				System.out.println("RUTHARD: Alright. Vertaine? \n");
				pauseText(2);
				System.out.println("Out of nowhere, a young woman pops her head out from behind a bookshelf. Upon seeing you, she smiles and \n"
								 + "waves. You return the gesture. \n");
				pauseText(2);
				System.out.println("VERTAINE: What may I help you with, Professor Ruthard? ");
				pauseText(2);
				System.out.println("RUTHARD: Could you please accompany " + charName + " here on their journey to the Binary Trees? Quickly, grab a \n"
								 + "cloak and a horse from the stable. There are evil things happening at the castle - I'm sure " + charName + "\n "
								 + "will explain to you on the way. Please be careful, my dear. ");
				
				pauseText(1);
				System.out.println("Vertaine has joined the party! \n");
				partyMembers.add(new PartyMember("Vertaine"));
				pauseText(1);
				
				break;
				
			case 2:	// Vertaine doesn't join the party
				// sucks for you
				break;
			}

			if(choice != 1 && choice != 2)
				System.out.println("Please choose a valid answer. \n");
			
		}
		
		System.out.println("RUTHARD: Very well. Good luck on your journey, you two. I look forward to your return. \n");
		goblinFight();
		
	}

	private void goblinFight(){
		System.out.println("You have been travelling toward the trees for a little while when\n"
                        +"you come upon three goblins crouching over some sort of prey on the side\n"
                        +"of the road.");
        pauseText(3);
        System.out.println("The scene is not unusual near the Berg Mountains which are inhabited\n"
                        +"by a number of goblin and ogre clans. Amidst the low grunts, however,\n"
                        +"is another voice, and as you look closer, you recognize that their prey is a human.\n");
        pauseText(3);
        System.out.println("THE STRANGER: HELP!!\n");
        System.out.println("At his cry, the goblins notice you, and the closest one bears its teeth.\n");
        pauseText(2);
        System.out.println("Do you rescue the man from the goblins?\n"
                        +"\t1) Yes\n"
                        +"\t2) No\n");
        int op;
        do{
			op = carl.nextInt();
            if(op==2){
                System.out.println("You feel terrible about leaving the man on the ground, but you\n"
                                +"tell yourself that you will save lives by going on towards the trees.\n"
                                +"You focus on that above the guilt.");
                //method call toward the trees scene
            }
            else if(op!=1){
                System.out.println("Please choose a valid answer.");
            }
        }while(!(op==1 || op==2));
        System.out.print("You dismount your horse and suddenly realize that your only weapon is\n" 
                        +"the small dagger used for cutting Rust Weed. "); 
        System.out.println("There is also the flamethrower,\n"
                        +"but you are hesitant to use up the fuel.");
        pauseText(4);
        System.out.println("You decide to act as a distraction and hope that the man on the ground\n"
                        +"makes it away. Maybe you can even try reasoning with these beasts …");       
        pauseText(3);
        System.out.println("but no, the goblin nearest the road lunges toward you.\n");
        pauseText(2);
        System.out.println("How do you react to the goblin’s attack?\n"
                        +"\t1) Cross your arms before you in a defensive position\n"
                        +"\t2) Leap out of the way\n"
                        +"\t3) Yell loudly\n"
                        +"Your choice? ");
        do{
            op = carl.nextInt();
            if(!(op==1 || op==2 || op==3))
                System.out.println("Please choose a valid option.");
        }while(!(op==1 || op==2 || op==3));
        System.out.println("\nYou successfully avoid the goblin’s first attack. However, he readies himself again.\n"
                        +"You must make another decision:\n"
                        +"\t1) Use dagger\n"
                        +"\t2) Use basic attack/defense spell\n"
                        +"\t3) Use your fists");
        do{
            op = carl.nextInt();
            if(!(op==1 || op==2 || op==3))
                System.out.println("Please choose a valid option.");
        }while(!(op==1 || op==2 || op==3));
        System.out.println("\nUnfortunately, the goblin still falls on you, leaving a painful gash in your arm.\n"
                        +"You scream and writhe, attempting to throw the monster off.");
        //print health stats
        pauseText(2);
        System.out.println("Suddenly he does leave, rushing to help the second goblin free the third who the stranger\n"
                        +"has pinned to the ground.\n");
        pauseText(2);
        System.out.println("You have a brief moment to recuperate before you need to help.\n"
                        +"What do you do?\n"
                        +"\t1) Re-enter the fight immediately\n"
                        +"\t2) Don armor\n"
                        +"\t3) Take a bite of food\n"
                        +"\t4) Wait as your comrades fight the goblins\n"
                        +"\t5) Mount your horse and leave the scene\n"
                        +"Your choice? ");
        boolean go = false;
        do{
            op = carl.nextInt();
            if(op==1){
                go = true;
                System.out.println("The three goblins together topple the man again, and now one comes back toward you.\n"
                                +"The pain in your arm is overwhelming, and to make matters worse, another two goblins\n"
                                +"emerge from the trees.");
                pauseText(3);
                System.out.println("You realize that you will not make it to your destination in time. Pictures of Mercurion\n"
                                +"and the castle, encased in stone, flash before your vision as the goblin leaps on top of you.\n"
                                +"The situation is out of your control. \n"
                                +"\n-=~[GAME OVER]~=-");

            }
            else if(op==2){
                if(inventory.contains("helmet") || inventory.contains("chestplate") || inventory.contains("shield")){
                    go = true;
                    System.out.println("You take the armor out of your bag and immediately feel better protected.");
                    pauseText(2);
                    System.out.println("You are ready to fight again. What do you do?\n"
                                    +"\t1) Use dagger\n"
                                    +"\t2) Yell loudly\n"
                                    +"\t3) Use basic attack/defense spell\n");
                    op = carl.nextInt();
                    System.out.println("Your action successfully distracts the goblins, and together, you and this stranger\n"
                                +"fight them back into the woods.");
                }
                else{
                    System.out.println("You don't have any armor. Please choose another option.");
                }
            }
            else if(op==3){
                if(inventory.contains("bread and strawberry preserve") || inventory.contains("venison") || inventory.contains("dragon tenders")){
                    go = true;
                    System.out.println("You take the food from your bag, and immediately feel energized.\n"
                                    +"The pain in your arm diminishes, and you are ready to fight again.\n"
                                    +"What do you do?");
                    pauseText(2);
                    System.out.println("\t1) Use dagger\n"
                                    +"\t2) Yell loudly\n"
                                    +"\t3) Use basic attack/defense spell\n");
                    op = carl.nextInt();
                    System.out.println("Your action successfully distracts the goblins, and together, you and this stranger\n"
                                +"fight them back into the woods.");
                }
                else{
                    System.out.println("You don't have any food. Please choose another option.");
                }
            }
            else if(op==4){
                if(!partyMembers.isEmpty()){
                    if(partyMembers.size()==1)
                        System.out.println("You scramble backwards and watch as "+partyMembers.get(0).getName()+" joins the stranger\n"
                                            +"and successfully drives the goblins back into the woods.");
                    else if(partyMembers.size()==2)
                        System.out.println("You scramble backwards and watch as "+partyMembers.get(0).getName()+" and "+partyMembers.get(1).getName()
                                        +"\njoin the stranger and successfully drive the goblins back into the woods.");
                }
                else{
                    System.out.println("You don't have any party members. Please choose another option.");
                }
            }
            else if(op==5){
                System.out.println("You feel terrible about leaving the man on the ground, but if you had stayed to fight,"
                                +"you yourself would have perished.");
                pauseText(2);
                System.out.println("'I will save lives by going to the trees,' you tell yourself. You focus on that above the guilt.");
                //call to Toward the Trees scene
            }
            else{
                System.out.println("Please choose a valid option.");
            }
        }while(!(op==1 || op==2 || op==3 || op==4 || op==5) && go==false);
        if(op==2 || op==3 || op==4){
            System.out.println("Once the goblins have disappeared, you turn back towards the stranger.\n"
                            +"He wears a bandana over one eye, suggesting that this is not the first time\n"
                            +"he has been attacked like this.");
            pauseText(3);
            System.out.println("As he walks over, you notice that his other eye has a triangular rune beneath it.");
            pauseText(2);
            System.out.println("'Is he a soothsayer?' you wonder, knowing that practitioners of that magic\n"
                            +"often identify themselves with such tattoos. He speaks just then, answering your\n"
                            +"unspoken question.\n");
            pauseText(4);
            System.out.println("MIHAIL: Thank you one thousand times. My name is Mihail, Soothsayer of Sedes.\n"
                            +"You have just saved my life. Can I ask you to return to my tower with me?\n"
                            +"I can feed you and treat your wounds. Perhaps I can start to repay you.\n");
            pauseText(3);
            System.out.println("Would you like to go to Mihail’s tower?\n"
                            +"\t1) Yes, I will accept your kind offer.\n"
                            +"\t2) Thank you, but I must continue along the road. We are on a quest.");
            do{
                op = carl.nextInt();
                if(op==1){
                    atMihailsTower();
                }
                else if(op==2){
                    System.out.println("MIHAIL: Very well, I will not hold up your progress any more than I have.\n"
                                    +"But, may the angels of the Tamali faith guide your quest.\n");
                    //call to Toward the Trees
                }
            }while(!(op==1 || op==2));
        }
    }

	private void atMihailsTower(){
        //String ucname = charName.toUpperCase();	// for ease of formatting
        String ucname = "Hi";

        System.out.println("Your party soon arrives at a decrepit tower.\n");
        pauseText(2);
        System.out.println("MIHAIL: Denise! Where are you?\n");
        pauseText(2);
        System.out.println("A lady opens the tower door.\n");
        pauseText(2);
        System.out.println("DENISE: Here, Soothsayer.\n");
        pauseText(2);
        System.out.println("Mihail gives introductions. Denise, you learn, is his scribe.");
        pauseText(2);
        System.out.println("Then, they feed you. He and Denise are gracious hosts.\n"
                        +"After you have finished the thick soup and tough bread, conversation starts.\n");
        pauseText(2);
        System.out.println("MIHAIL: So, where were you off to when you came upon me?");
        System.out.println(ucname+": We are on our way to destroy the Binary Trees of Utilio. We need their\n"
                        +"power to defeat Manasses--he has come back from banishment and taken siege of the castle.");
        pauseText(3);
        System.out.println("MIHAIL: *aghast* You are going to cut down the trees? Do you even know the tales\n"
                        +"of them? They are the source of magic for all the land. Cutting them down will stop\n"
                        +"Manasses, surely, but it will also rob everyone of their powers. Why would you make\n"
                        +"that decision?");
        pauseText(3);
        System.out.println(ucname+": Professor Ruthard at the University of Meyerstern told me to.");
        pauseText(2);
        System.out.println("MIHAIL: That place, no no no. Have you not heard the rumors? There is blackmailing \n"
                        +"business afoot. Ruthard is at the center of it. He and the lady of the sea, Melynas.\n"
                        +"Something is not right with them. I have seen it in my visions.");
        pauseText(4);
        System.out.println(ucname+": What should I do then?\n");
        pauseText(2);
        System.out.println("Mihail stands up and begins to pace. After a long while, his eyes alight on a book\n"
                        +"on the desk.\n");
        pauseText(2);
        System.out.println("MIHAIL: I found this book not long ago in an abandoned castle. The spirits\n"
                        +"there told me to take it. They told me that it may be of some use in the future.\n"
                        +"I think that you should take it now. Return to the castle with it. I have a feeling\n"
                        +"that it will help.");
        pauseText(4);
        System.out.println("MIHAIL: *after your silence* Please trust me. I will even come with you--and I’ll\n" 
                        +"bring Denise. If nothing else, we know some spells to try against Manasses. Just please,\n"
                        +"please do not attack those sacred Binary Trees.");
        pauseText(3);
        System.out.println(ucname+": Can I look at the book?\n");
        pauseText(2);
        System.out.println("Mihail seems suddenly hesitant to say yes. There is a possessive glint in his eye and\n"
                        +"a tremor in his hand.");
        pauseText(4);
        System.out.println("After a protracted silence, he hands it to you.");
        pauseText(1);
        System.out.println("It takes you a moment to translate the Old Elvish on the cover, but once you do,\n"
                        +"you shiver. 'Tome of Turin,' it says.\n");
        System.out.println("What would you like to do?\n"
                        +"\t1) Agree to Mihail's plan.\n"
                        +"\t2) Continue toward the trees.");
        int op;
        do{
            op = carl.nextInt();
            if(op==1){
                //call to back at the castle method
            }
            else if(op==2){
                //call to toward the trees method
            }
            else{
                System.out.println("Please choose a valid option.");
            }
        }while(!(op==1 || op==2));
    }

	private void bindingManasses() {	// binding Manasses with the String of Morcom
		
		System.out.println("It takes a final, brief command to bind the man - his body zooms \n"
						 + "toward the bracelet that you hold, outstretched, in one hand. ");
		pauseText(2);
		System.out.println("When he comes close enough, he shrinks and then merges with the \n"
						 + "metal. After the process finishes, you take a glance at the bracelet. \n"
						 + "It looks completely unchanged. \n");
		pauseText(3);
		System.out.println(charName.toUpperCase() + ": I think I'll call this... the String of Morcom. \n");
		pauseText(2);
		System.out.println("You move towards the castle. With each step you take, a wave of color \n"
						 + "spreads outward. The stone spell is retreating. \n");
		pauseText(2);
		System.out.println("You win... but at the cost of the world's magic. Was it worth it? \n");
		pauseText(3);
		System.out.println("You can almost hear these questions in the king's mind as he shakes \n"
						 + "your hand. Another woman in dark clothing stands next to him. \n");
		pauseText(2);
		System.out.println("KARALIUS: This woman here is Denise. She is a scribe for Mihail, the \n"
						 + "Soothsayer of Sedes. She is going to ask you some questions about the \n"
						 + "events that have just transpired, and we will send you a copy of the \n"
						 + "record. Thank you again for you service. \n");
		pauseText(4);
		System.out.println("\n-=~[THE END]~=-");
		
	}
	
	private boolean checkMember(String nameIn) {	// check if you have a particular party member
		for(PartyMember member: partyMembers) {
			if(member != null && member.getName().equals(nameIn)) {
				return true;
			}
		}
		return false;
	}
	
	private void pauseText(int dur) {	// pause time for a specified period
		
		if(dur == 1) {
			try {
				Thread.currentThread().sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if(dur == 2) {
			try {
				Thread.currentThread().sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if(dur == 3) {
			try {
				Thread.currentThread().sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if(dur == 4) {
			try {
				Thread.currentThread().sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(dur == 5) {
			try {
				Thread.currentThread().sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}

class PartyMember{
	
	private String name;
	private int health; //also add power?
	private int powerLevel;
	
	public PartyMember(String name){
		this.name = name;
		health = 20; //randomize this?
	}
	public String getName(){	// return name of party member
		return name;
	}
}