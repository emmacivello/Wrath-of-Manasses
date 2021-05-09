/*
 * Program title: WOMstory.java
 * Author: CEH Emma Civello, CA Alicia Chen
 * Description: The text-based portion of Wrath of Manasses
 * 
 * 
 * note to self: copy of github's but without button and with the continue/finish later option
 */

//package wrathOfManasses;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import java.io.*;
import javax.imageio.ImageIO;

import java.util.*; // import statements

public class WOMstoryWgraphics {	// class header

	private Scanner carl;
	private String charName;	// the name the character chooses
	private String gender;	// gender the character chooses
	private String user_speaker; //name of png representing speaker (depends on gender), either Reyna or Reinhardt
	private int money;
	private ArrayList<String> inventory;
	private ArrayList<PartyMember> partyMembers;	// possibly change to a custom object (Character)
	private JFrame frame;
    private WOMpanel new_panel;
	private String savepoint;
    // add sprite image name later
	
	public WOMstoryWgraphics() {	// constructor
		carl = new Scanner(System.in);
		charName = "";
		gender = "";
		user_speaker = "";
		money = 20;
		inventory = new ArrayList<>();
		inventory.add(Integer.toString(money));
		partyMembers = new ArrayList<>();
        frame = new JFrame();
        new_panel = new WOMpanel(inventory, partyMembers);
		savepoint = "";
	}
	
	public static void main(String[] args) {	// main method header
		WOMstoryWgraphics wom = new WOMstoryWgraphics();	// class instance
		wom.menu();
		
	}

	private void menu(){
		int op;
		do{
            System.out.print("Menu\n1) Play\n2) Instructions\n3) Authors\n0) Exit\nOption? ");
            op = carl.nextInt(); carl.nextLine();
            switch(op){
                case 1: run(); break;
                case 2: 
                	System.out.println("Wrath of Manasses is a choose your own adventure game. In order to perform \n"
                					 + "actions, you must wait until you're presented with a list of options and then \n"
                					 + "type in the number of the option you want. \n");
                	break;
                case 3:
                	System.out.println("This game was coded by Emma Civello and Alicia Chen. \n");
                	break;
                case 0: System.out.println("Goodbye"); break;
                default: System.out.println("Sorry, bad input.");
            }
        }while(op!=0);
	}

	private void run() {	// either runs game from the beginning or opens up a previous save file

        frame.setSize(600, 400);                
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setLocation(50, 100);
        frame.setResizable(false);
		new_panel.setLayout(null); //allows use of coordinates (rather than grid)
									//discovered at: https://stackoverflow.com/questions/3195666/how-to-place-a-jbutton-at-a-desired-location-in-a-jframe-using-java
		
		// JButton b = new JButton("Leave Game");
		// Font font = new Font("Times New Roman", Font.PLAIN, 12);
		// b.setFont(font);
		// b.setBounds(20, 320, 100, 30);
		// // b.setVerticalAlignment(SwingConstants.CENTER);
		// // b.setHorizontalAlignment(SwingConstants.BOTTOM);
		
		// new_panel.add(b);

		// ActionListener actionListener = new ActionListener(){
        //     public void actionPerformed(ActionEvent actionEvent){
        //         //if action detected, set up next panel and button
		// 		leaveGame();
        //     }
        // };
		// b.addActionListener(actionListener);

        frame.add(new_panel);
        frame.setVisible(true);

		WOMfilereader fr = new WOMfilereader();
		ArrayList<String> data = fr.read();
		
		if(data.isEmpty()) {
			charSelection();
		}
		
		else {
			
			charName = (String)(data.get(0));
			gender = (String)(data.get(1));
			
			if(!data.get(2).equals("")){ //if there are party members (if there aren't and this if weren't here,
										//the program tries to create one w/blank name)
				ArrayList<String> temp2 = new ArrayList<String>(Arrays.asList(data.get(2).split(",")));
				for(int i=0; i<temp2.size(); i++) {
				
					String currMem = temp2.get(i);
					//if(!currMem.equals("")) //don't add blanks to party
						partyMembers.add(new PartyMember(currMem));
				
				}
			}
			
			System.out.println("SIZE:"+partyMembers.size());
			if(!data.get(3).equals("")){
				inventory = new ArrayList<String>(Arrays.asList(data.get(3).split(",")));
			}
			
			savepoint = (String)(data.get(4));

			new_panel.updateItems(frame, inventory);
			new_panel.updatePeople(frame, partyMembers);
			
			switch(savepoint) {
			case "castleScene":
				castleScene();
				break;
			case "townScene":
				townScene();
				break;
			case "universityScene":
				universityScene();
				break;
			case "goblinFight":
				goblinFight();
				break;
			case "atMihailsTower":
				atMihailsTower();
				break;
			case "burningTrees":
				burningTrees();
				break;
			case "bindingManasses":
				bindingManasses();
				break;
			case "backAtCastle":
				backAtCastle();
				break;
			}
			
		}
		
	}
	
	private void charSelection() {	// name/gender selection
		
		while(!gender.toLowerCase().equals("girl") && !gender.toLowerCase().equals("boy")) {
			
			System.out.print("Would you like to look like a BOY or a GIRL? ");
			gender = carl.nextLine();	
			if(!gender.toLowerCase().equals("girl") && !gender.toLowerCase().equals("boy")) {
				System.out.println("Please choose one of the above choices. \n");
			}
			
		}
		System.out.println("\n");
		
		new_panel.updateSpeaker(frame, "Mercurion");
		System.out.println("The wizard Mercurion walks into his study and sees you standing there. \n");
		pauseText(2);
		System.out.println("MERCURION: *rubs eyes* My apologies, I didn't see you there. \n");
		pauseText(2);
		System.out.println("Mercurion walks over to his desk and picks up a stack of paper. \n");
		pauseText(2);
		if(gender.toLowerCase().equals("girl")) {
			partyMembers.add(new PartyMember("Reyna"));
			user_speaker = "Reyna";
	        new_panel.updatePeople(frame, partyMembers);
		}
		else if(gender.toLowerCase().equals("boy")) {
			partyMembers.add(new PartyMember("Reinhardt"));
			user_speaker = "Reinhardt";
	        new_panel.updatePeople(frame, partyMembers);
		}
		System.out.println("MERCURION: You forgot to sign the report you gave me yesterday. Mind doing it now? \n");
		pauseText(2);
		System.out.print("What is your name? ");
		charName = carl.nextLine();
		System.out.println("\nMERCURION: Thank you, " + charName + ". ");
		pauseText(2);
		
		//charName = "Rick Astley";
		//gender = "boy";

		castleScene();
		
	}
	
	private void castleScene() {	// castle scene
		
		String ucname = charName.toUpperCase();	// for ease of formatting
		
		if(charName.equals("Mercurion")) {
			System.out.println("MERCURION: *laughs* The fact that we share the same name will never fail to amuse me. \n");
			pauseText(2);
			new_panel.updateSpeaker(frame, user_speaker);
			System.out.print(ucname + ": It's definitely funny. ");
		}
		else {
			System.out.print(ucname + ": It's no problem, Mercurion. ");
		}
		
		new_panel.updateSpeaker(frame, user_speaker);
		System.out.println("What task do you want me to do today? \n");
		pauseText(2);
		new_panel.updateSpeaker(frame, "Mercurion");
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
		Collections.sort(inventory);
        new_panel.updateItems(frame, inventory);
        pauseText(1);
		new_panel.updateSpeaker(frame, user_speaker);
		
		int choice = -1;
		while(choice != 1 && choice != 2) {
			new_panel.updateSpeaker(frame, user_speaker);
			System.out.print("Choose something to say. \n"
							 + "\t1) Yes, of course. I will return shortly. \n"
							 + "\t2) Actually, I was planning on learning some spells today. \n"
							 + "Your choice? ");
			choice = carl.nextInt(); carl.nextLine();
			System.out.println();
			
			switch(choice) {
			case 1:	// collection path
				new_panel.updateSpeaker(frame, "Mercurion");
				System.out.println("MERCURION: Excellent. Do be careful in the forest, I hear that the poison ivy's gotten \n"
								 + "out of control lately. \n");
				pauseText(2);
				new_panel.updateSpeaker(frame, "");
				System.out.println("You leave the castle and head towards the Forest of Babbage, collecting all of the \n"
								 + "rust weed that you can find. ");
				
				pauseText(1);
                System.out.println("\nITEM GET! Bundle of Rust Weed x1 has been added to your inventory.\n");
				inventory.add("Bundle of Rust Weed");
                //update graphics screen:
                Collections.sort(inventory);
                new_panel.updateItems(frame, inventory);
				pauseText(1);
				
				System.out.println("All of a sudden, you hear the noise of a loud explosion coming from the castle. You \n"
								 + "are suddenly thankful that you took this job. It might've been dull, but you are safe \n"
								 + "out here. The same cannot be said for the people inside. \n");
				pauseText(3);
				new_panel.updateSpeaker(frame, user_speaker);
				System.out.println(ucname + ": Oh no- I should probably go check on everyone. \n");
				pauseText(2);
				new_panel.updateSpeaker(frame, "");
				System.out.println("As you approach the castle, you can see that the whole structure is shaking. A wave of \n"
								 + "gray seems to be spreading - seeping up the oaken front doors and across the water of \n"
								 + "the moat. \n");
				pauseText(3);
				new_panel.updateSpeaker(frame, "Mercurion");
				System.out.println("MERCURION: " + charName + "! Up here!");
				pauseText(2);
				new_panel.updateSpeaker(frame, user_speaker);
				System.out.println(ucname + ": Mercurion! What's going on? I don't understand- \n");
				pauseText(2);
				new_panel.updateSpeaker(frame, "Mercurion");
				System.out.println("Mercurion's entire body shudders. The old man is trying his best to steady himself, \n"
								 + "but it seems to be getting harder for him to do so with each passing second. With a \n"
								 + "shaky voice, he shouts out the window to you. \n");
				pauseText(3);
				new_panel.updateSpeaker(frame, "Mercurion");
				
				if(charName.equals("Manasses")) {
					System.out.println("MERCURION: This- this is Manasses's doing. " + charName + ". ");
					pauseText(2);
					new_panel.updateSpeaker(frame, user_speaker);
					System.out.println(ucname + ": That wasn't me! I didn't do anything! ");
					pauseText(2);
					new_panel.updateSpeaker(frame, "Mercurion");
					System.out.println("MERCURION: No, not you! Another Manasses. Manasses... go to the University of \n"
									 + "Meyerstern and find- \n");
				}
				else
					System.out.println("MERCURION: This- this is Manasses's doing. " + charName + ", go to the University of \n"
							 + "Meyerstern and find- \n");
				pauseText(2);
				
				System.out.println("There is a great pause. The man in the window seems to be fighting off a force- one \n"
								 + "that is invisible, but no less powerful than something that can be seen. \n");
				pauseText(3);
				new_panel.updateSpeaker(frame, "Mercurion");
				if(charName.equals("Ruthard"))
					System.out.println("MERCURION: *gasps* Go to Meyerstern. Find my old friend, Ruthard. Yes, he has the \n"
									 + "same name as you. No, I will not... be taking any more questions. \n");
				else
					System.out.println("MERCURION: *gasps* Go to Meyerstern. Find my old friend, Ruthard. \n");
				pauseText(2);
				new_panel.updateSpeaker(frame, "");
				System.out.println("The gray wave makes its way towards you. You look down in horror as the nearby grass \n"
								 + "begins to turn into stone. \n");
				pauseText(3);
				new_panel.updateSpeaker(frame, "Mercurion");
				System.out.println("MERCURION: Mey- Meyerstern. Ruthard. Go now. It'll be alright, " + charName + ". ");
				pauseText(2);
				new_panel.updateSpeaker(frame, user_speaker);
				System.out.println(ucname + ": I will. \n");
				pauseText(2);
				new_panel.updateSpeaker(frame, "");
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
					choice = carl.nextInt(); carl.nextLine();
					System.out.println();
					switch(choice) {
					case 1:
						System.out.println("\nYou've reached a checkpoint! Continue to next scene or finish later?\n"
										+"Type 'c' to continue or 'f' to finish later.");
						char op;
						do{
							op = Character.toLowerCase(carl.nextLine().charAt(0));
						}while(!(op=='c' || op=='f'));
						if(op=='c'){
							townScene();
						}
						else{
							savepoint = "townScene";
							leaveGame();
						}
						break;
						
					case 2:
						System.out.println("\nYou've reached a checkpoint! Continue to next scene or finish later?\n"
										+"Type 'c' to continue or 'f' to finish later.");
						do{
							op = Character.toLowerCase(carl.nextLine().charAt(0));
						}while(!(op=='c' || op=='f'));
						if(op=='c')
							universityScene();	// method for university scene
						else{
							savepoint = "universityScene";
							leaveGame();
						}
						break;
					}
					

					if(choice != 1 && choice != 2)
						System.out.println("Please choose a valid answer. \n");
					
				}
				break;
				
			case 2:	// staying in the castle
				new_panel.updateSpeaker(frame, "Mercurion");
				System.out.println("MERCURION: Remember that not all magic is waving wands and casting spells. You need \n"
						 		 + "to put in the hard work, and that involves some manual labor. Because you did well \n"
						 		 + "with potion-making yesterday, I will assign you something more \"interesting\" today, \n"
						 		 + "but in the future, I may not always let you off- \n");
				pauseText(4);
				new_panel.updateSpeaker(frame, "");
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
					choice = carl.nextInt(); carl.nextLine();
					System.out.println();
					switch(choice) {
					case 1:	// flee
						new_panel.updateSpeaker(frame, "");
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
						System.out.println("\n-=~[GAME OVER]~=-\n");
						break;
						
					case 2:	// get closer to Mercurion
						System.out.println("Mercurion begins, while simultaneously moving his hands in the air so that a \n"
										 + "forcefield of sorts takes form around you. \n");
						pauseText(3);
						new_panel.updateSpeaker(frame, "Mercurion");
						
						if(charName.equals("Turin")) {
							System.out.println("MERCURION: Many eons ago, there was a great battle between the alchemists Turin \n"
											 + "and Manasses, the founders of Adalos. Yes Turin, he has the same name as you. Your parents \n"
											 + "probably named you after him. Together, they fought to free this land from the control of \n"
											 + "an enemy lord, and were eventually successful. ");
						}
						else if(charName.equals("Manasses")) {
							System.out.println("MERCURION: Many eons ago, there was a great battle between the alchemists Turin \n"
									 		 + "and Manasses, the founders of Adalos. To be frank, I have no idea why your parents named \n"
									 		 + "you after that man. Together, they fought to free this land from the control of an enemy \n"
									 		 + "lord, and were eventually successful. ");
						}
						else {
							System.out.println("MERCURION: Many eons ago, there was a great battle between the alchemists Turin \n"
									 + "and Manasses, the founders of Adalos. Together, they fought to free this land \n"
									 + "from the control of an enemy lord, and were eventually successful. ");
						}
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
						new_panel.updateSpeaker(frame, user_speaker);
						
						choice = -1;
						while(choice != 1 && choice != 2) {
							new_panel.updateSpeaker(frame, user_speaker);
							System.out.print("Choose something to say. \n"
									 + "\t1) How do you know that this curse is Manasses's doing? \n"
									 + "\t2) Will we be safe? \n"
									 + "Your choice? ");
							choice = carl.nextInt(); carl.nextLine();
							System.out.println();
							switch(choice) {
							case 1:	// it is manasses because
								System.out.println("Mercurion glances towards you with a dreadful certainty. \n");
								pauseText(3);
								new_panel.updateSpeaker(frame, "Mercurion");
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
								System.out.println("\n-=~[GAME OVER]~=-\n");
								break;
								
							case 2:	// "Heck no boi, we screwed." - Mercurion, 2021 :D
								new_panel.updateSpeaker(frame, "Mercurion");
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
								new_panel.updateSpeaker(frame, user_speaker);
								
								choice = -1;
								while(choice != 1 && choice != 2) {
									new_panel.updateSpeaker(frame, user_speaker);
									System.out.print("Choose an action. \n"
											 + "\t1) Ask Mercurion if there's anything that you can do. \n"
											 + "\t2) Allow the hopelessness of the situation to overtake you. \n"
											 + "Your choice? ");
									choice = carl.nextInt(); carl.nextLine();
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
										new_panel.updateSpeaker(frame, "");
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
											choice = carl.nextInt(); carl.nextLine();
											System.out.println();
											switch(choice) {
											case 1:
												System.out.println("\nYou've reached a checkpoint! Continue to next scene or finish later?\n"
																+"Type 'c' to continue or 'f' to finish later.");
												char op;
												do{
													op = Character.toLowerCase(carl.nextLine().charAt(0));
												}while(!(op=='c' || op=='f'));
												if(op=='c'){
													townScene();
												}
												else{
													savepoint = "townScene";
													leaveGame();
												}
												break;
											case 2:
												System.out.println("\nYou've reached a checkpoint! Continue to next scene or finish later?\n"
																+"Type 'c' to continue or 'f' to finish later.");
												do{
													op = Character.toLowerCase(carl.nextLine().charAt(0));
												}while(!(op=='c' || op=='f'));
												if(op=='c')
													universityScene();	// method for university scene
												else{
													savepoint = "universityScene";
													leaveGame();
												}
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
										System.out.println("\n-=~[GAME OVER]~=-\n");
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

	private void townScene() {	// town scene
		String ucname = charName.toUpperCase();
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
		String[]foods = {"Bread and Strawberry Preserve", "Venison", "Dragon Tenders"};
		int[]prices = {5, 10, 15};
		System.out.println("What would you like to do?\n"
		+"\t1) Purchase "+foods[0]+" ($"+prices[0]+")\n"
		+"\t2) Purchase "+foods[1]+" ($"+prices[1]+")\n"
		+"\t3) Purchase "+foods[2]+" ($"+prices[2]+")\n"
		+"\t4) Save my money");
		int op;
		do{
			op = carl.nextInt(); carl.nextLine();
			if(op==1 || op==2 || op==3){
				pauseText(1);
				inventory.add(foods[op-1]);
				System.out.println("\nITEM GET! "+foods[op-1]+" x1 has been added to your inventory. \n");
                Collections.sort(inventory);
                new_panel.updateItems(frame, inventory);
                pauseText(1);

				money = money - prices[op-1];
				inventory.set(0, Integer.toString(money));
				Collections.sort(inventory);
                new_panel.updateItems(frame, inventory);
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
		new_panel.updateSpeaker(frame, "StableMaster");
		System.out.println("STABLE MASTER: Hello, "+charName+". How can I help you today?");
		pauseText(2);
		new_panel.updateSpeaker(frame, user_speaker);
		System.out.println(charName.toUpperCase()+": Stable Master, can you please lend me a horse?");
		pauseText(2);
		new_panel.updateSpeaker(frame, "StableMaster");
		System.out.println("STABLE MASTER: For what purpose?\n");
		pauseText(2);
		new_panel.updateSpeaker(frame, "");
		System.out.println("At that moment, bells begin to ring--not the church bells of the Tamali faith\n"
						+"(which ring in beautiful melodies)--but a deeper gong. An urgent warning.");
		pauseText(3);
		System.out.println("The Stable Master's face pales.\n");
		pauseText(1);
		new_panel.updateSpeaker(frame, "StableMaster");
		System.out.println("STABLE MASTER: *whispering* Oh no. Have you been at the castle recenly?\n"
						+"Is something happening there? Please, please no . . . ");
		pauseText(3);
		System.out.println("The bells ring in pairs. \"Trouble on the horizon, not yet arrived.\" We are \n"
						 + "safe for the moment. Have you seen anything suspicious?");
		pauseText(3);
		new_panel.updateSpeaker(frame, user_speaker);
		System.out.println(charName.toUpperCase()+": Manasses. Mercurion says he has escaped his banishment,\n" 
						+"and so Mercurion sent me here--well, technically to the University of Meyerstern,\n"
						+"but I stopped off for supplies. Please, sir, I need a horse. I will return it,\n"
						+"but I am supposed to meet Ruthard at the university.\n");
		pauseText(4);
		new_panel.updateSpeaker(frame, user_speaker);
		System.out.println("The Stable Master's face is snowy white at this point, but he is also good under pressure.\n");
		pauseText(2);
		new_panel.updateSpeaker(frame, "StableMaster");
		System.out.println("STABLE MASTER: Of course. A horse.\n");
		pauseText(2);
		System.out.println("The man disappears down the hallway and quickly returns, leading a gray steed.\n");
		pauseText(2);
		System.out.println("STABLE MASTER: Go, make haste. Would you like my son to come with you? He is an\n"
							+"accomplished stable hand--he can lead this horse quickly.\n");
		pauseText(3);
		new_panel.updateSpeaker(frame, user_speaker);
		System.out.println("1) Yes, thank you for the offer. I could use his assistance.\n"
							+"2) I appreciate your offer, but I will brave the journey alone.\n"
							+"Your choice? ");
		do{
			op = carl.nextInt(); carl.nextLine();
			if(op==1){
				System.out.println("\nA moment later, the Stable Master's son appears\n"
									+"and introduces himself as Ahkal.\n");
				System.out.println("Ahkal has joined the party! \n");
				pauseText(2);
				PartyMember Ahkal = new PartyMember("Ahkal");
				partyMembers.add(Ahkal);
                Collections.sort(partyMembers, PartyMember.nameComp);
                new_panel.updatePeople(frame, partyMembers);
				if(ucname.equals("AHKAL")){
					System.out.println("Ahkal laughs when you introduce yourself as another Ahkal.\n");
					pauseText(2);
					System.out.println("STABLE MASTER: Oh boy, this could get confusing. *chuckles* Anyhow, good\n"
									+"luck on your quest.");
				}
			}
			else{
				System.out.println("STABLE MASTER: Very well. I wish you luck.\n");
			}
		}while(!(op==1 || op==2));
		pauseText(3);
		System.out.println("Finally, you visit the armory. The head armorer is just looking the doors when you arrive.\n");
		pauseText(2);
		new_panel.updateSpeaker(frame, user_speaker);
		System.out.println(charName.toUpperCase()+": Please, sir, can I purchase some armor? It is urgent, and I will be quick.\n");
		pauseText(2);
		new_panel.updateSpeaker(frame, "");
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
			op = carl.nextInt(); carl.nextLine();
			if(op==1){
				if(money>=armor_prices[0]){
					pauseText(1);
					inventory.add(armor[0]);
					System.out.println("\nITEM GET! "+armor[0]+" x1 has been added to your inventory. \n");
                    Collections.sort(inventory);
                    new_panel.updateItems(frame, inventory);
                    pauseText(1);
                    
					money = money - armor_prices[0];
					inventory.set(0, Integer.toString(money));
					Collections.sort(inventory);
                	new_panel.updateItems(frame, inventory);
					System.out.println("Monetary balance: $"+money+"\n");
					go = true;
				}
				else{
					System.out.println("ARMORER: *irritated* You do not have enough to buy "+armor[0]);
				}
			}
			else if(op==2){
				if(money>=armor_prices[1]){
					pauseText(1);
					inventory.add(armor[1]);
					System.out.println("\nITEM GET! "+armor[1]+" x1 has been added to your inventory. \n");
                    Collections.sort(inventory);
                    new_panel.updateItems(frame, inventory);
                    pauseText(1);
                    
					money = money - armor_prices[1];
					inventory.set(0, Integer.toString(money));
					Collections.sort(inventory);
                	new_panel.updateItems(frame, inventory);
					System.out.println("Monetary balance: $"+money+"\n");
					go = true;
				}
				else{
					System.out.println("ARMORER: *irritated* You do not have enough to buy "+armor[1]);
				}
			}
			else if(op==3){
				if(money>=armor_prices[2]){
					pauseText(1);
					inventory.add(armor[2]);
					System.out.println("\nITEM GET! "+armor[2]+" x1 has been added to your inventory. \n");
                    Collections.sort(inventory);
                    new_panel.updateItems(frame, inventory);
                    pauseText(1);
                    
					money = money - armor_prices[2];
					inventory.set(0, Integer.toString(money));
					Collections.sort(inventory);
                	new_panel.updateItems(frame, inventory);
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
		new_panel.updateSpeaker(frame, "");
		System.out.println("\nYou've reached a checkpoint! Continue to next scene or finish later?\n"
						+"Type 'c' to continue or 'f' to finish later.");
		do{
			op = Character.toLowerCase(carl.nextLine().charAt(0));
		}while(!(op=='c' || op=='f'));
		if(op=='c')
			universityScene();	// method for university scene
		else{
			savepoint = "universityScene";
			leaveGame();
		}
			
	}//TownScene
	
	private void universityScene() {	// university scene

		System.out.println("After a long journey, you finally end up at the gates of the University of Meyerstern. ");
		pauseText(2);
		
		System.out.println("After a long journey, you finally end up at the gates of the University of Meyerstern. ");
		pauseText(2);
		
		if(checkMember("Ahkal")) {
			new_panel.updateSpeaker(frame, "Ahkal");
			System.out.print("\nAHKHAL: I'll wait outside with the horses. You go meet this Ruthard fellow. ");
			pauseText(2);
			new_panel.updateSpeaker(frame, user_speaker);
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
		new_panel.updateSpeaker(frame, "Melynas");
		System.out.println("WOMAN IN BLUE: Welcome to the University of Meyerstern. Who are you, and how \n"
						 + "may I help you? ");
		pauseText(2);
		new_panel.updateSpeaker(frame, user_speaker);		
		System.out.println(charName.toUpperCase() + ": " + charName + ", a journeyman under the tutelage of the alchemist Mercurion. He sent \n"
						 + "me here to speak with Professor Ruthard. ");
		pauseText(2);
		new_panel.updateSpeaker(frame, "Melynas");

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
		new_panel.updateSpeaker(frame, user_speaker);
		System.out.println(charName.toUpperCase() + ": Thank you. \n");
		pauseText(2);
		new_panel.updateSpeaker(frame, "");
		System.out.println("The woman leaves, and you walk up to the door. You hesitate for a moment, but knock on it twice. \n");
		pauseText(2);
		new_panel.updateSpeaker(frame, "Ruthard");
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
		new_panel.updateSpeaker(frame, user_speaker);
		System.out.println("You explain your quest to Ruthard. His smile disappears and is replaced by a frown. You are finally able to pinpoint \n"
						 + "where the feeling of uncertainty comes from - the professor seems kind, but there is something behind his eyes and \n"
						 + "his smile that seem to suggest that something less than helpful lies beneath the surface. However, you brush the feeling off. \n"
						 + "After all, it was Mercurion who told you to seek him out, and you trust the old alchemist as if he were your own father. \n");
		pauseText(3);
		new_panel.updateSpeaker(frame, "Ruthard");
		System.out.println("RUTHARD: If it is as you say, and Manasses has returned, we are all in danger. ");
		pauseText(2);
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
		new_panel.updateSpeaker(frame, user_speaker);
	
		int choice = -1;
		while(choice != 1 && choice != 2) {
			System.out.print("Choose something to say. \n"
					 + "\t1) But your colleague... survived, didn't he? \n"
					 + "\t2) Are you sure that these trees are real? \n"
					 + "Your choice? ");
			choice = carl.nextInt(); carl.nextLine();
			System.out.println();
			new_panel.updateSpeaker(frame, "Ruthard");
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

		new_panel.updateSpeaker(frame, user_speaker);
		System.out.println(charName.toUpperCase() + ": Are you sure there isn't another way? ");
		pauseText(2);
		new_panel.updateSpeaker(frame, "Ruthard");
		System.out.println("RUTHARD: The only other option I could think of is finding the Tome of Turin, but it has been lost for centuries. \n"
						 + "I hate to be a pessimist, but there is little hope of recovering it quickly enough to defeat Manasses. \n");
		pauseText(2);
		new_panel.updateSpeaker(frame, "");
		System.out.println("You are not entirely convinced that the quest is a safe or viable one. However, since Mercurion trusts Ruthard, you take \n"
						 + "his advice and agree to travel to the Binary Trees of Utilio. ");
		pauseText(3);
		new_panel.updateSpeaker(frame, "Ruthard");
		System.out.println("RUTHARD: The world thanks you, " + charName + ". I wish you the best of luck on your journey. But before you go, let me give you \n"
						 + "something that might be of use. \n");

		pauseText(1);
		inventory.add("Flamethrower");	// add flamethrower to inventory ArrayList
		System.out.println("ITEM GET! Flamethrower x1 has been added to your inventory. \n");
        Collections.sort(inventory);
        new_panel.updateItems(frame, inventory);
		pauseText(1);
		
		System.out.println("RUTHARD: I'm calling this a \"flamethrower\". Has Mercurion ever mentioned that I take great interest in mechanical creations? \n");
		pauseText(2);
		new_panel.updateSpeaker(frame, user_speaker);
		
		choice = -1;
		while(choice != 1 && choice != 2) {
			System.out.print("Choose something to say. \n"
					 + "\t1) Uh... actually, he hasn't told me much about you. \n"
					 + "\t2) I don't think it ever came up in any of our conversations, no. \n"
					 + "Your choice? ");
			choice = carl.nextInt(); carl.nextLine();
			System.out.println();

			if(choice != 1 && choice != 2)
				System.out.println("Please choose a valid answer. \n");
			
		}

		new_panel.updateSpeaker(frame, "Ruthard");
		System.out.println("RUTHARD: Ah, well. I consider myself to have some skill in engineering, and I think that my latest invention might \n"
						 + "help you destroy the trees. \n");
		pauseText(2);
		new_panel.updateSpeaker(frame, "");
		System.out.println("You awkwardly take the device from Ruthard. It is made of metal, and quite heavy. \n");
		pauseText(2);
		new_panel.updateSpeaker(frame, user_speaker);
		System.out.println(charName.toUpperCase() + ": Thank you. This looks like a powerful weapon. ");
		pauseText(2);
		new_panel.updateSpeaker(frame, "Ruthard");
		System.out.println("RUTHARD: It's still a prototype, but I'm working on developing a model that has more uses and requires less fuel. \n"
						 + "Feel free to take the map! Oh, and one last thing, would you like me to send one of my teaching assistants with \n"
						 + "you to aid you on your quest? \n");
		pauseText(3);
		new_panel.updateSpeaker(frame, user_speaker);
		
		choice = -1;
		while(choice != 1 && choice != 2) {
			System.out.print("Choose something to say. \n"
					 + "\t1) I would greatly appreciate that. \n"
					 + "\t2) I appreciate your kind offer, but I refuse. \n"
					 + "Your choice? ");
			choice = carl.nextInt(); carl.nextLine();
			System.out.println();
			switch(choice) {
			case 1:	// Vertaine joins the party
				new_panel.updateSpeaker(frame, "Ruthard");
				System.out.println("RUTHARD: Alright. Vertaine? \n");
				pauseText(2);
				new_panel.updateSpeaker(frame, "");
				System.out.println("Out of nowhere, a young woman pops her head out from behind a bookshelf. Upon seeing you, she smiles and \n"
								 + "waves. You return the gesture. \n");
				pauseText(2);
				new_panel.updateSpeaker(frame, "Vertaine");
				System.out.println("VERTAINE: What may I help you with, Professor Ruthard? ");
				pauseText(2);
				new_panel.updateSpeaker(frame, "Ruthard");
				System.out.println("RUTHARD: Could you please accompany " + charName + " here on their journey to the Binary Trees? Quickly, grab a \n"
								 + "cloak and a horse from the stable. There are evil things happening at the castle - I'm sure " + charName + "\n "
								 + "will explain to you on the way. Please be careful, my dear. ");
				
				if(charName.equals("Vertaine")) {
					new_panel.updateSpeaker(frame, "Vertaine");
					System.out.println("VERTAINE: *smiles* I've never met another Vertaine before. That's dope. ");
				}
				
				pauseText(1);
				System.out.println("Vertaine has joined the party! \n");
				partyMembers.add(new PartyMember("Vertaine"));
                Collections.sort(partyMembers, PartyMember.nameComp);
                new_panel.updatePeople(frame, partyMembers);
				pauseText(1);
				
				break;
				
			case 2:	// Vertaine doesn't join the party
				// sucks for you
				break;
			}

			if(choice != 1 && choice != 2)
				System.out.println("Please choose a valid answer. \n");
			
		}

		new_panel.updateSpeaker(frame, "Ruthard");
		System.out.println("RUTHARD: Very well. Good luck on your journey. I look forward to your return. \n"); //I took out the 'you two' part because it was reading even if Vertaine didn't join
		System.out.println("Now, equipped for your journey, you head toward the University of Meyerstern.");
		new_panel.updateSpeaker(frame, "");
		System.out.println("\nYou've reached a checkpoint! Continue to next scene or finish later?\n"
						+"Type 'c' to continue or 'f' to finish later.");
		char op;
		do{
			op = Character.toLowerCase(carl.nextLine().charAt(0));
		}while(!(op=='c' || op=='f'));
		if(op=='c'){
			goblinFight();
		}
		else{
			savepoint = "goblinFight";
			leaveGame();
		}

	}

	private void goblinFight() {	// goblin fight
		String ucname = charName.toUpperCase();
		System.out.println("You have been travelling toward the trees for a little while when\n"
                        +"you come upon three goblins crouching over some sort of prey on the side\n"
                        +"of the road.");
        pauseText(3);
        System.out.println("The scene is not unusual near the Berg Mountains which are inhabited\n"
                        +"by a number of goblin and ogre clans. Amidst the low grunts, however,\n"
                        +"is another voice, and as you look closer, you recognize that their prey is a human.\n");
        pauseText(3);
		new_panel.updateSpeaker(frame, "Mihail");
        System.out.println("THE STRANGER: HELP!!\n");
        pauseText(2);
		new_panel.updateSpeaker(frame, "");
        System.out.println("At his cry, the goblins notice you, and the closest one bears its teeth.\n");
        pauseText(2);
        System.out.println("Do you rescue the man from the goblins?\n"
                        +"\t1) Yes\n"
                        +"\t2) No\n"
                        + "Your choice? ");
        int op;
        do{
			op = carl.nextInt(); carl.nextLine();
            if(op==2){
                System.out.println("You feel terrible about leaving the man on the ground, but you\n"
                                +"tell yourself that you will save lives by going on towards the trees.\n"
                                +"You focus on that above the guilt.");
				System.out.println("\nYou've reached a checkpoint! Continue to next scene or finish later?\n"
								+"Type 'c' to continue or 'f' to finish later.");
				do{
					op = Character.toLowerCase(carl.nextLine().charAt(0));
				}while(!(op=='c' || op=='f'));
				if(op=='c'){
					burningTrees(); //method call to trees scene
				}
				else{
					savepoint = "burningTrees";
					leaveGame();
				}
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
                        +"makes it away. Maybe you can even try reasoning with these beasts... ");       
        pauseText(3);
        System.out.println("but no, the goblin nearest the road lunges toward you.\n");
        pauseText(2);
        System.out.println("How do you react to the goblin's attack?\n"
                        +"\t1) Cross your arms before you in a defensive position\n"
                        +"\t2) Leap out of the way\n"
                        +"\t3) Yell loudly\n"
                        +"Your choice? ");
        do{
            op = carl.nextInt(); carl.nextLine();
            if(!(op==1 || op==2 || op==3))
                System.out.println("Please choose a valid option.");
        }while(!(op==1 || op==2 || op==3));
        System.out.println("\nYou successfully avoid the goblin’s first attack. However, he readies himself again.\n"
                        +"You must make another decision:\n"
                        +"\t1) Use dagger\n"
                        +"\t2) Use basic attack/defense spell\n"
                        +"\t3) Use your fists\n"
                        + "Your choice? ");
        do{
            op = carl.nextInt(); carl.nextLine();
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
            op = carl.nextInt(); carl.nextLine();
            if(op==1){
                go = true;
                System.out.println("The three goblins together topple the man again, and now one comes back toward you.\n"
                                +"The pain in your arm is overwhelming, and to make matters worse, another two goblins\n"
                                +"emerge from the trees.");
                pauseText(3);
                System.out.println("You realize that you will not make it to your destination in time. Pictures of Mercurion\n"
                                +"and the castle, encased in stone, flash before your vision as the goblin leaps on top of you.\n"
                                +"The situation is out of your control. \n"
                                +"\n-=~[GAME OVER]~=-\n");

            }
            else if(op==2){
                if(inventory.contains("Helmet") || inventory.contains("Chestplate") || inventory.contains("Shield")){
                    go = true;
                    System.out.println("You take the armor out of your bag and immediately feel better protected.");
                    pauseText(2);
                    System.out.println("You are ready to fight again. What do you do?\n"
                                    +"\t1) Use dagger\n"
                                    +"\t2) Yell loudly\n"
                                    +"\t3) Use basic attack/defense spell\n"
                                    + "Your choice? ");
                    carl.nextLine(); //wait for user input
                    System.out.println("Your action successfully distracts the goblins, and together, you and this stranger\n"
                                +"fight them back into the woods.");
                }
                else{
                    System.out.println("You don't have any armor. Please choose another option.");
                }
            }
            else if(op==3){
                if(inventory.contains("Bread and Strawberry Preserve") || inventory.contains("Venison") || inventory.contains("Dragon Tenders")){
                    go = true;
					if(inventory.contains("Bread and Strawberry Preserve")){
                        inventory.remove("Bread and Strawberry Preserve");
                        Collections.sort(inventory);
                        new_panel.updateItems(frame, inventory);
                        frame.repaint(0);
                        frame.setVisible(true);
                    }
					else if(inventory.contains("Venison")){
                        inventory.remove("Venison");
                        Collections.sort(inventory);
                        new_panel.updateItems(frame, inventory);
                        frame.repaint(0);
                        frame.setVisible(true);
                    }
						
					else if(inventory.contains("Dragon Tenders")){
                        inventory.remove("Dragon Tenders");
                        Collections.sort(inventory);
                        new_panel.updateItems(frame, inventory);
                        frame.repaint(0);
                        frame.setVisible(true);
                    }
                    System.out.println("You take the food from your bag, and\n"
									+"immediately feel energized. The pain in your arm diminishes, and you\n"
                                    +"are ready to fight again.\n"
                                    +"What do you do?");
                    pauseText(2);
                    System.out.println("\t1) Use dagger\n"
                                    +"\t2) Yell loudly\n"
                                    +"\t3) Use basic attack/defense spell\n"
                                    + "Your choice? ");
					carl.nextLine();
                    System.out.println("Your action successfully distracts the goblins, and together, you and this stranger\n"
                                +"fight them back into the woods.");
                }
                else{
                    System.out.println("You don't have any food. Please choose another option.");
                }
            }
            else if(op==4){
                if(!partyMembers.isEmpty()){
					go = true;
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
				go = true;
                System.out.println("You feel terrible about leaving the man on the ground, but if you had stayed to fight,"
                                +"you yourself would have perished.");
                pauseText(2);
                System.out.println("'I will save lives by going to the trees,' you tell yourself. You focus on that above the guilt.");
				System.out.println("You feel terrible about leaving the man on the ground, but you\n"
                                +"tell yourself that you will save lives by going on towards the trees.\n"
                                +"You focus on that above the guilt.");
				System.out.println("\nYou've reached a checkpoint! Continue to next scene or finish later?\n"
								+"Type 'c' to continue or 'f' to finish later.");
				do{
					op = Character.toLowerCase(carl.nextLine().charAt(0));
				}while(!(op=='c' || op=='f'));
				if(op=='c'){
					burningTrees(); //method call to trees scene
				}
				else{
					savepoint = "burningTrees";
					leaveGame();
				}
            }
            else{
                System.out.println("Please choose a valid option.");
            }
        }while(!(op==1 || op==2 || op==3 || op==4 || op==5) || go==false);
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
    		new_panel.updateSpeaker(frame, "Mihail");
            System.out.println("SOOTHSAYER?: Thank you one thousand times. My name is Mihail, Soothsayer of Sedes.\n"
                            +"You have just saved my life. Can I ask you to return to my tower with me?\n"
                            +"I can feed you and treat your wounds. Perhaps I can start to repay you.\n");
            pauseText(3);
			if(ucname.equals("MIHAIL")){
				new_panel.updateSpeaker(frame, user_speaker);
				System.out.println(ucname+": No way, your name is Mihail? That's my name, too.");
				new_panel.updateSpeaker(frame, "");
			}
            System.out.println("Would you like to go to Mihail's tower?\n"
                            +"\t1) Yes, I will accept your kind offer.\n"
                            +"\t2) Thank you, but I must continue along the road. We are on a quest."
                            + "Your choice? ");
            do{
                op = carl.nextInt(); carl.nextLine();
                if(op==1){
					System.out.println("\nYou've reached a checkpoint! Continue to next scene or finish later?\n"
										+"Type 'c' to continue or 'f' to finish later.");
					do{
						op = Character.toLowerCase(carl.nextLine().charAt(0));
					}while(!(op=='c' || op=='f'));
					if(op=='c'){
						atMihailsTower(); //method call to trees scene
					}
					else{
						savepoint = "atMihailsTower";
						leaveGame();
					}
                }
                else if(op==2){
            		new_panel.updateSpeaker(frame, "Mihail");
                    System.out.println("MIHAIL: Very well, I will not hold up your progress any more than I have.\n"
                                    +"But, may the angels of the Tamali faith guide your quest.\n");
            		new_panel.updateSpeaker(frame, "");
					System.out.println("\nYou've reached a checkpoint! Continue to next scene or finish later?\n"
									+"Type 'c' to continue or 'f' to finish later.");
					do{
						op = Character.toLowerCase(carl.nextLine().charAt(0));
					}while(!(op=='c' || op=='f'));
					if(op=='c'){
						burningTrees(); //call to trees scene
					}
					else{
						savepoint = "burningTrees";
						leaveGame();
					}
                }
            }while(!(op==1 || op==2));
        }
    }

	private void atMihailsTower() {	// meeting Denise/getting to know Mihail
        String ucname = charName.toUpperCase();	// for ease of formatting

        System.out.println("Your party soon arrives at a decrepit tower.\n");
        pauseText(2);
        System.out.println("MIHAIL: Denise! Where are you?\n");
        pauseText(2);
        System.out.println("A lady opens the tower door.\n");
        pauseText(2);
        System.out.println("DENISE: Here, Soothsayer.\n");
        pauseText(2);
		if(ucname.equals("DENISE")){
			System.out.println("MIHAIL: Denise, you won't believe this. I got saved from a pack of goblins by\n"
								+"another person named Denise!\n");
		}
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
		inventory.add("Tome of Turin");
		System.out.println("ITEM GET! Tome of Turin x1 has been added to your inventory. \n");
		Collections.sort(inventory);
        new_panel.updateItems(frame, inventory);
        pauseText(1);
        
        System.out.println("It takes you a moment to translate the Old Elvish on the cover, but once you do,\n"
                        +"you shiver. 'Tome of Turin,' it says.\n");
        System.out.println("What would you like to do?\n"
                        +"\t1) Agree to Mihail's plan.\n"
                        +"\t2) Continue toward the trees.");
        int op;
        do{
            op = carl.nextInt(); carl.nextLine();
            if(op==1){
                partyMembers.add(new PartyMember("Mihail"));
                partyMembers.add(new PartyMember("Denise")); 
                System.out.println("Mihail has joined the party! \n");
                System.out.println("Denise has joined the party! \n");
                Collections.sort(partyMembers, PartyMember.nameComp);
                new_panel.updatePeople(frame, partyMembers);
				System.out.println("\nYou've reached a checkpoint! Continue to next scene or finish later?\n"
									+"Type 'c' to continue or 'f' to finish later.");
				do{
					op = Character.toLowerCase(carl.nextLine().charAt(0));
				}while(!(op=='c' || op=='f'));
				if(op=='c'){
					backAtCastle(); //call to back at the castle method
				}
				else{
					savepoint = "backAtCastle";
					leaveGame();
				}
				
            }
            else if(op==2){
				System.out.println("\nYou've reached a checkpoint! Continue to next scene or finish later?\n"
									+"Type 'c' to continue or 'f' to finish later.");
				do{
					op = Character.toLowerCase(carl.nextLine().charAt(0));
				}while(!(op=='c' || op=='f'));
				if(op=='c'){
					burningTrees(); //call to trees scene
				}
				else{
					savepoint = "burningTrees";
					leaveGame();
				}
            }
            else{
                System.out.println("Please choose a valid option.");
            }
        }while(!(op==1 || op==2));
    }

	private void burningTrees() {	// burning dem trees
		String ucname = charName.toUpperCase();
		
		System.out.println("A day passes. It is nearly nightfall before you notice that the trees \n"
						 + "on either side of the road are no longer dark oak or spruce, but a new \n"
						 + "species of silvery birch. ");
		pauseText(3);
		System.out.println("Checking Ruthard's map, you find your position on the edge of The Argent Woods. ");
		pauseText(2);
		System.out.println("You must decide where to go. The map shows the trees' general location, \n"
						 + "but the path you are on splits into three. \n");
		pauseText(2);
		
		int choice = -1;
		while(choice != 2) {
			System.out.print("Choose an action. \n"
					 + "\t1) Head left. \n"
					 + "\t2) Head straight. \n"
					 + "\t3) Head right. \n"
					 + "Your choice? ");
			choice = carl.nextInt(); carl.nextLine();
			System.out.println();
			switch(choice) {
			case 1:	// head left
				System.out.println("After traveling for a while, you realize that this is not the correct path. \n"
								 + "This path travels by a small pond and then veers off to the north, away from \n"
								 + "the area on the map. You backtrack to the fork. \n");
				pauseText(3);
				break;
				
			case 2:	// head straight
				System.out.println("It turns out the center path is the correct one, but it takes a long time for \n"
								 + "you to realize that. After traveling down the same path for what feels like \n"
								 + "the fourth time, you suddenly arrive at a clearing that was not there on your \n"
								 + "first three passes. ");
				pauseText(4);
				System.out.println("The Binary Trees - for what else could they be? - sit apart from the rest of \n"
								 + "the forest in a small clearing. You have never before seen trees with such pale \n"
								 + "bark or such golden leaves. Furthermore, they seem to shimmer, to emit a wave of \n"
								 + "energy that makes the surrounding grass greener and the surrounding air clearer. ");
				pauseText(4);
				System.out.println("Despite their beauty, you must bring them down. You didn't come all this way \n"
								 + "just to sightsee. You approach the tree on the left. \n");
				pauseText(3);
				
				choice = -1;
				while(choice != 1 && choice != 2 && choice != 3) {
					System.out.print("Which part would you like to burn first? \n"
							 + "\t1) The trunk. \n"
							 + "\t2) The branches. \n"
							 + "\t3) The leaves. \n"
							 + "Your choice? ");
					choice = carl.nextInt(); carl.nextLine();
					System.out.println();
					switch(choice) {
					case 1:
						System.out.println("The trunk begins to blaze a fierce yellow, but the fire quickly dims to orange, then \n"
										 + "red, then nothing. The bark seems to be flame resistant. You turn to the right tree, \n"
										 + "hoping that it is weaker. \n");
						pauseText(3);
						break;
						
					case 2:
						System.out.println("The branches begin to blaze a fierce yellow, but the fire quickly dims to orange, then \n"
										 + "red, then nothing. The branches seems to be flame resistant. You turn to the right tree, \n"
										 + "hoping that it is weaker. \n");
						pauseText(3);
						break;
						
					case 3:
						System.out.println("The leaves begin to blaze a fierce yellow, but the fire quickly dims to orange, then \n"
										 + "red, then nothing. The leaves seems to be flame resistant. You turn to the right tree, \n"
										 + "hoping that it is weaker. \n");
						pauseText(3);
						break;
					}
					
					if(choice != 1 && choice != 2 && choice != 3)
						System.out.println("Please choose a valid answer. \n");
					
				}
				
				choice = -1;
				while(choice != 1 && choice != 2 && choice != 3) {
					
					System.out.print("Which area do you light on fire? \n"
							 + "\t1) The trunk. \n"
							 + "\t2) The branches. \n"
							 + "\t3) The leaves. \n"
							 + "Your choice? ");
					choice = carl.nextInt(); carl.nextLine();
					System.out.println();
					
					System.out.println("Again, the flames start and stop. Your head begins to feel heavy, and the flamethrower \n"
									 + "slips in the sweat that has accumulated on your hands. You hear a voice, a low moaning, \n"
									 + "that rings in your ears but not in the clearing. It is as though the trees have a guardian, \n"
									 + "and it is invading your mind. You must concentrate, for you have one use of the flamethrower remaining. \n");
					pauseText(4);
					
					if(choice != 1 && choice != 2 && choice != 3)
						System.out.println("Please choose a valid answer. \n");
					
				}
				
				choice = -1;
				while(choice != 1 && choice != 2 && choice != 3) {
					
					System.out.print("Which area do you light on fire? \n"
							 + "\t1) The trunk. \n"
							 + "\t2) The branches. \n"
							 + "\t3) The leaves. \n"
							 + "Your choice? ");
					choice = carl.nextInt(); carl.nextLine();
					System.out.println();
					
					System.out.println("This time, the right tree catches fire and stays lit. The flames must have gotten \n"
									 + "through the bark and into the wood at the tree's core, for it starts to glow from the inside. ");
					pauseText(2);
					System.out.println("A moment later, as Ruthard had described, you absorb the full force of a magical \n"
									 + "wave that emanates from the tree as it crashes down.");
					pauseText(2);
					System.out.println("The left tree is now on fire since its branches are entwined with those of the right one. A \n"
									 + "moment later, you catch the left tree in one hand just before it crashes into your head. You \n"
									 + "don't even feel the heat. You lower it slowly to the ground as though it were a mere twig. \n");
					pauseText(3);
					
					choice = -1;
					while(choice != 1 && choice != 2 && choice != 3) {
						System.out.print("Eager to test out your new power, you: \n"
								 + "\t1) Pick up a nearby boulder. \n"
								 + "\t2) Cast a spell on a nearby ant. \n"
								 + "\t3) Cast a flight spell on yourself. \n"
								 + "Your choice? ");
						choice = carl.nextInt(); carl.nextLine();
						System.out.println();
						switch(choice) {
						case 1:	// pick up rock
							System.out.println("The boulder is light, like a pebble. ");
							pauseText(2);
							break;
							
						case 2:	// animal abuse
							System.out.println("The ant pauses. You are able to make it run, dance, and jump with your mind. ");
							pauseText(2);
							break;
							
						case 3:	// I believe I can flyyy
							System.out.println("You have always struggled with this spell, but now it is easy. You hover a \n"
											 + "few feet off the ground. ");
							pauseText(2);
							break;
						}
						
						if(choice != 1 && choice != 2 && choice != 3)
							System.out.println("Please choose a valid answer. \n");
							
					}
					
					if(partyMembers.size() == 1)
						System.out.println("Your companion is very impressed. \n");
					else if(partyMembers.size() >= 2)
						System.out.println("Your companions are very impressed. \n");
					
					if(checkMember("Ahkal")) {
						System.out.println("AHKAL: *whistles* Now that's what I'm talking about. ");
						pauseText(2);
					}
					if(checkMember("Mihail") && checkMember("Denise")) {
						System.out.println("MIHAIL: *claps* Nice! Very nice. ");
						pauseText(2);
						System.out.println("DENISE: I agree with Mihail. ");
						pauseText(2);
					}
					if(checkMember("Vertaine")) {
						System.out.println("VERTAINE: That is COOL. ");
						pauseText(2);
					}
					System.out.println();
						
					if(partyMembers.size() > 1) {
						System.out.println("It seems like you've all absorbed a little bit of the magical essence, for \n"
										 + partyMembers.get((int)(Math.random()*partyMembers.size())).getName() + " is now able to roll a boulder along the ground with ease. ");
						pauseText(2);
						System.out.println("You also realize that you have now gained the ability to run quickly. The \n"
										 + "group concedes to split up. You will run ahead, and they will follow on horseback. ");
						pauseText(2);
					}
					else {
						System.out.println("You also realize that you have now gained the ability to run quickly. With \n"
										 + "not a moment to waste, you begin running in the direction of the castle. ");
						pauseText(2);
					}
					
					System.out.println("It takes you less than an hour to return to the castle. You stop on the edge of the stone spell,\n"
									+"still unsure what will happen if you touch it. Even from this distance, however, you can see\n"
									+"Manasses lounging on the castle's front steps. His golden, horn-like crown reflects the sun, and he\n"
									+"has draped his long scarlet cape over the bannister.\n");
					pauseText(4);
					System.out.println("You decide to attempt a binding spell, and so you remove a bracelet from your wrist--\n"
									+"this will be the artifact in which you trap him. You start to perform the binding spell\n"
									+"when suddenly, your powers allow you to take control of his mind.\n");
					pauseText(4);
					System.out.println("What do you do next?\n"
									+"\t1) Immediately bind him to the bracelet\n"
									+"\t2) Bring him over to the edge of the stone so that you can talk.\n");
					int op;
					do{
						op = carl.nextInt(); carl.nextLine();
						if(op==1){
							System.out.println("With another half-effort, you compel this powerful being to stand at your bidding.\n"
											+"You watch him turn his hands over, attempting to cast a spell--probably one for protection.\n"
											+"Manasses seems at first confused and then terrified when it does not work.\n");
							pauseText(4);
							System.out.println("\nYou've reached a checkpoint! Continue to next scene or finish later?\n"
											+"Type 'c' to continue or 'f' to finish later.");
							do{
								op = Character.toLowerCase(carl.nextLine().charAt(0));
							}while(!(op=='c' || op=='f'));
							if(op=='c'){
								bindingManasses();
							}
							else{
								savepoint = "bindingManasses";
								leaveGame();
							}
						}
						else if(op==2){
							System.out.println("He speaks first.\n");
							pauseText(1);
							System.out.println("MANASSES: So you are the one who killed the trees. Never thought it'd be a student."
											+"Always thought it'd be myself. Opportunity lost I suppose, but nicely done.");
							pauseText(3);
							System.out.println("Being praised by such an evil being makes you feel queasy and so you turn the conversation.\n");
							pauseText(3);
							System.out.println(ucname+": How did you escape your banishment?");
							pauseText(2);
							System.out.println("MANASSES: Maybe because Turin, my dear old friend, trapped me in this dollar store trinket.\n");
							pauseText(2);
							System.out.println("He holds out a strand of plastic beads.\n");
							pauseText(2);
							System.out.println("MANASSES: I would've at least expected gold. Not because it's a fine metal, a tribute to our\n"
											+"fine friendship, but because it's hard to break out of as banishment curses go. I thought he would\n"
											+"have known this.\n");
							pauseText(4);
							System.out.println("As Manasses continues to describe the properties of metals, it strikes you that this accomplished\n"
											+"sorcerer may know a way to replant the trees. You feel immense guilt for what you did, and so you\n"
											+"decide to ask when he pauses for breath.\n");
							pauseText(3);
							System.out.println(ucname+": Is there a way to restore the trees?");
							pauseText(2);
							System.out.println("MANASSES: I know of one, but if I tell you, you must agree to a bargain. You cannot banish me,\n"
											+"and you must promise to restore the trees--to restore magic to the people of this world.");
							pauseText(2);
							System.out.println("'To yourself, you mean,' you add in your head.\n");
							pauseText(2);
							System.out.println("Option: Do you accept these terms?\n"
											+"\t1) Yes. Your guilt is overwhelming and besides, you figure that there will be a way to keep Manasses\n"
											+"\tfrom regaining power. It would be breaking this bargain, but that is a trick he has pulled on so many others\n"
											+"\tin the past. It would be fitting. You shake his hand.\n"
											+"\t2) No. If you did, you would be undoing all of your previous work, and besides someone else must know how to\n"
											+"replant the trees. You decide to hold onto the guilt a while longer.\n");
							do{
								if(op==1){
									System.out.println("As you shake his hand, you suddenly feel very heavy. You're not turning to rock--at least\n"
													+"not yet--but you realize that you no longer have the trees' power. It also becomes evident that Manasses\n"
													+"does . . . how?\n");
									pauseText(4);
									System.out.println("MANASSES: *as though reading your mind* Ha! Skin-to-skin contact. Specifically, palm-to-palm.\n"
													+"The easiest way to transfer a power if you know how.");
									pauseText(3);
									System.out.println("Of course, Spell-Sharing was a bit experimental in my day, but it's a good thing I broke the law to\n"
													+"study it. Thank you for the powers ... what is your name?\n");
									pauseText(4);
									System.out.println("He pulls it out of your mind before you can answer. "+ucname+". Thank you, "+ucname+". You know, I was\n"
													+"really hoping my colleague Professor Ruthard of Meyerstern was going to help me himself, but it seems that\n"
													+"you are a good proxy.\n");
									pauseText(4);
									System.out.println("Come into the castle. I won't turn you to stone, don't worry! I want to tell you about my plans for the\n"
													+"future, about how I will rescue countries beyond Adalos's borders, about how I will expand the boundaries of\n"
													+"what is possible with magic. Would you like to listen?\n");
									pauseText(4);
									System.out.println("It is a question, but you no longer have a choice of answers.");
									pauseText(2);
									System.out.println("Your fate--and the world's--is now out of your hands, and so you follow Manasses into the castle.");
									if(!partyMembers.isEmpty()){
										if(partyMembers.size()==1)
											System.out.println("Your last fleeting hope is that "+partyMembers.get(0).getName()+" absorbed enough of the trees'\n"
															+"magic to fight Manasses.\n");
										else
											System.out.println("Your last fleeting hope is that your traveling companions absorbed enough of the trees'\n"
															+"magic to fight Manasses.\n");
									}
									System.out.println("-=~[THE END]~=-");
								}//if
								else if(op==2){
									System.out.println("\nYou've reached a checkpoint! Continue to next scene or finish later?\n"
											+"Type 'c' to continue or 'f' to finish later.");
									do{
										op = Character.toLowerCase(carl.nextLine().charAt(0));
									}while(!(op=='c' || op=='f'));
									if(op=='c'){
										bindingManasses();
									}
									else{
										savepoint = "bindingManasses";
										leaveGame();
									}
								}//else if
								else{
									System.out.println("Please choose a valid option.");
								}
							}while(!(op==1 || op==2));
						}
						else{
							System.out.println("Please enter a valid choice.");
						}
					}while(!(op==1||op==2));
					
					if(choice != 1 && choice != 2 && choice != 3)
						System.out.println("Please choose a valid answer. \n");
					
				}
				
				break;
				
			case 3:	// head right
				System.out.println("After traveling for a while, you realize that this is not the correct path. \n"
								 + "This path turns south, away from the area on the map. You backtrack to the fork. \n");
				pauseText(3);
				break;
			}
			
			if(choice != 1 && choice != 2 && choice != 3)
				System.out.println("Please choose a valid answer. \n");
			
			choice = 2;
			
		}
		
	}
	
	private void bindingManasses() {	// binding Manasses with the String of Morcom
		String ucname = charName.toUpperCase();
		
		System.out.println("It takes a final, brief command to bind the man - his body zooms \n"
						 + "toward the bracelet that you hold, outstretched, in one hand. ");
		pauseText(2);
		System.out.println("When he comes close enough, he shrinks and then merges with the \n"
						 + "metal. After the process finishes, you take a glance at the bracelet. \n"
						 + "It looks completely unchanged. \n");
		pauseText(3);
		System.out.println(ucname + ": I think I'll call this... the String of Morcom. \n");
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

	private void backAtCastle(){ // the good ending
        String ucname = charName.toUpperCase();
        System.out.println("The party arrives back at the castle many hours later. The stone spell has spread\n"
                        +"a little further, but thankfully not much.\n");
        pauseText(3);
        System.out.println("MIHAIL: You are going to go up to the perimeter, just there. Then, begin to read\n"
                        +"from this last page. I believe it is a banishing spell. It looks promising.\n");
        pauseText(3);
        System.out.println("You do as instructed. The Ancient Elvish words echo off the stone, and then suddenly,\n"
                        +"there is a harsh, booming voice. Manasses speaks furiously from the castle steps.\n");
        pauseText(3);
        System.out.println("MANASSES: You dared to trap me in this--this dollar store trinket?\n");
        pauseText(2);
        System.out.println("Out of the corner of your eye, you see him waving a string of plastic beads.\n"
                        +"You cannot stop to ponder what he means, however, for you must continue to read.\n"
                        +"You get through another paragraph when something happens that you cannot ignore.\n");
        pauseText(5);
        System.out.println("NEW VOICE: It was all that I had on-hand. What did you expect? 24-karat gold?\n");
        pauseText(3);
        System.out.println("Mihail gasps from behind you, and you look into the air to see a ghost-like form.\n"
                        +"It is Turin himself.\n");
        pauseText(3);
        System.out.println("MIHAIL: So I guess that was not a banishing spell. Denise, make sure you're recording\n"
                        +"all of this.\n");
        pauseText(3);
        System.out.println("Turin flies toward Manasses.\n");
        pauseText(2);
        System.out.println("TURIN: How did you escape?");
        pauseText(2);
        System.out.println("MANASSES: Maybe it was this cheap trinket. The plastic wasn't too hard to overcome.\n"
                        +"*leering* Are you happy to see me?");
        pauseText(3);
        System.out.println("TURIN: Far from it. I was dining with some of Tamali's angels before I was summoned here--\n"
                        +"not that I blame YOU, of course.\n");
        pauseText(3);
        System.out.println("He turns to you for the first time. This brief pause gives Manasses the break he was\n"
                        +"looking for, and he swipes toward Turin with a massive hand. The ghostly figure trembles\n"
                        +"as Manasses's spell strikes.\n");
        pauseText(5);
        System.out.println("How do you help?\n"
                        +"\t1) Attack Manasses with Mihail's flame-thrower.\n"
                        +"\t2) Flip through the tome, searching for a helpful spell.\n"
                        +"\t3) Chuck the hefty tome toward Manasses.\n");
        int op;
        do{
            op = carl.nextInt(); carl.nextLine();
            if(op==1){
                System.out.println("As you draw out the hefty machine, Manasses looks over.");
            }
            else if(op==2){
                System.out.println("As you flip through the Tome of Turin, Manasses looks over.\n\n"
                                +"MANASSES: How dare you touch those pages!\n");
            }
            else if(op==3){
                System.out.println("As the book sails toward his chest, Manasses is forced to dodge.");
            }
            else{
                System.out.println("Please choose a valid option.");
            }
        }while(!(op==1 || op==2 || op==3));
        System.out.println("Your action distracts Manasses long enough for Turin to overcome the spell. Turin \n"
                        +"starts to mouth an incantation, and you realize that you must distract Manasses again--\n"
                        +"long enough for Turin's spell to take effect.\n");
        pauseText(4);
        System.out.println("What do you do?\n"
                        +"\t1) Throw your rust-weed-cutting dagger toward Manasses.\n"
                        +"\t2) Start dancing.\n"
                        +"\t3) Tell Manasses a story.\n");
        do{
            op = carl.nextInt(); carl.nextLine();
            if(op==1){
                System.out.println("The dagger misses Manasses but still succeeds in distracting him.\n");
            }
            else if(op==2){
                System.out.println("Despite the tense situation, Manasses seems almost ready to laugh when\n"
                                +"he catches sight of your moves.");
            }
            else if(op==3){
                System.out.println("He does not seem particularly interested in the history of your journey here, but you\n"
                                +"successfully distracted him a second time.");
            }
            else{
                System.out.println("Please enter a valid option.");
            }
        }while(!(op==1 || op==2 || op==3));
        System.out.println("You have successfully distracted him a second time.\n");
        pauseText(2);
        System.out.println("TURIN: Do you have a necklace? Bracelet?\n");
        pauseText(2);
        System.out.println("Denise hurries forward, offering a thin golden chain.\n");
        pauseText(1);
        System.out.println("TURIN: Perfect.\n");
        pauseText(2);
        System.out.println("He waves his hand one last time, and Manasses begins to shrink in size and to zoom forward.\n"
                        +"He has just enough time to yell \"STOP\" before his body melds into the gold of the bracelet.\n"
                        +"Turin drops it to the ground, and from this spot, a wave of color washes out toward the castle.\n"
                        +"The stone retracts until it is gone.\n");
        pauseText(4);
        System.out.println("TURIN: We will call this the String of Morcom. It is to be returned to the vault where the\n"
                        +"plastic beads once lay. I trust that this will hold him for a few centuries longer. Now, I must\n"
                        +"go and finish my brunch.");
        pauseText(3);
        System.out.println("Before anyone has time to thank him or ask any questions, Turin disappears.\n");
        pauseText(2);
        System.out.println("The group enters the castle. Mercurion and King Karalius rush down the grand staircase\n"
                        +"as you enter. Mercurion can barely speak, but this time it is out of pure joy. The stone spells are gone.\n");
        pauseText(2);
        System.out.println("KARALIUS: Thank you, "+ucname+". You have saved this world. Thank you.\n");
        pauseText(2);
        System.out.println("Karalius strokes his beard thoughtfully.\n");
        pauseText(2);
        System.out.println("KARALUIS: I believe this calls for a feast.\n");
        pauseText(2);
        System.out.println("A few hours later, everyone gathers around a large oak table.");
        pauseText(2);
        System.out.println("Karalius repeats his appreciation again.\n");
        pauseText(1);
        System.out.println("KARALIUS: Thank you Mihail, Denise, ");
        if(checkMember("Ahkal"))
            System.out.print("Ahkal, ");
        else if(checkMember("Vertaine, "))
            System.out.print("Vertaine, ");
        System.out.println("and finally, the person at the heart of Manasses's defeat, "+ucname+".\n"
                        +"Thank you all for returning the castle to safety and for ensuring that harm does not\n"
                        +"befall my dear kingdom Adalos.");
        pauseText(4);
        String[]foods = {"Hearty dragon soup", "Pumpkin bread with preserves", "Pomegranate and wild turkey"};
        System.out.println("King Karalius then offers you the first choice of the delicacies.\n"
                        +"What food do you choose?\n"
                        +"\t1) "+foods[0]+"\n"
                        +"\t2) "+foods[1]+"\n"
                        +"\t3) "+foods[2]+"\n");
        String food = "";
        do{
			op = carl.nextInt(); carl.nextLine();
            if(!(op==1 || op==2 || op==3))
                System.out.println("Please choose a valid option.");
            else
                food = foods[op-1];
        }while(!(op==1 || op==2 || op==3));
        String[]drinks = {"Golden honey cider", "Cocoa tea", "Green leaf brew"};
        System.out.println("What beverage sounds the best?\n"
                        +"\t1) "+drinks[0]+"\n"
                        +"\t2) "+drinks[1]+"\n"
                        +"\t3) "+drinks[2]+"\n");
        String drink = "";
        do{
			op = carl.nextInt(); carl.nextLine();
            if(!(op==1 || op==2 || op==3))
                System.out.println("Please choose a valid option.");
            else
                drink = drinks[op-1];
        }while(!(op==1 || op==2 || op==3));
        System.out.println("As you enjoy your "+food+" and "+drink+", the conversation turns to other matters.\n"
                        +"Mihail explains urgently that he had been riding out to warn King Karalius of conspiracies afoot\n"
                        +"at the University of Meyerstern when he was attacked by goblins. When the assembly learns that\n"
                        +"you saved his life, there is a round of applause.\n");
        pauseText(4);
        System.out.println("Finally, the conversation arrives at the matter of the Tome of Turin itself. Mihail agrees\n"
                        +"to leave it in the castle library.");
        pauseText(3);
        System.out.println("KARALIUS: Thank you, Mihail, Soothsayer of Sedes. Turin said that the String of Morcom should\n"
                        +"hold Manasses for another few centuries, but after that who knows? We may need the book again.\n");
        pauseText(2);
        System.out.println("The next few centuries are safe thanks to your bravery and intelligence.\n");
        pauseText(2);
        System.out.println("Congratulations!");
        pauseText(2);
        System.out.println("\n-=~[YOU WIN]~=-");
    }

	private void leaveGame(){
		System.out.println("The game can save your progress in a folder called: "+charName.replace(" ", "")+"Savefile.txt\n"
							+"If a file with this name already exists, it will be overwritten.\n"
							+"Would you like to save your progress (y/n)?");
		String op;
		do{
			op = carl.nextLine();
		}while(!(op.equalsIgnoreCase("Y") || op.equalsIgnoreCase("N")));
		if(op.equalsIgnoreCase("Y")){
			String filename = charName.replace(" ", "")+"Savefile.txt";
			PrintWriter fout = null;
			try{fout = new PrintWriter(new File(filename));}
			catch(IOException ex){System.out.print(ex);}
			fout.write(charName+"\n\n");
			fout.write(gender+"\n\n");
			for(PartyMember member:partyMembers){
				fout.write(member.getName()+",");
			}
			fout.write("\n\n");
			for(String item:inventory){
				fout.write(item+",");
			}
			fout.write("\n\n");
			fout.write(savepoint+"\n\n");
			fout.close();
		}
		frame.dispose();
		return; //return to menu
	}//leaveGame

	private boolean checkMember(String nameIn) {	// check if you have a particular party member
		for(PartyMember member: partyMembers) {
			if(member != null && member.getName().equals(nameIn)) {
				return true;
			}
		}
		return false;
	}
	
	private void fullHouse() {	// for debugging purposes, adds every possible party member to the party (as well as a few others)
		if(gender.equals("boy"))
			partyMembers.add(new PartyMember("Reyna"));
		else if(gender.equals("girl"))
			partyMembers.add(new PartyMember("Reinhardt"));
		partyMembers.add(new PartyMember("Ahkal"));
		partyMembers.add(new PartyMember("Mihail"));
		partyMembers.add(new PartyMember("Denise"));
		partyMembers.add(new PartyMember("Ruthard"));
		partyMembers.add(new PartyMember("Melynas"));
		partyMembers.add(new PartyMember("Vertaine"));
		partyMembers.add(new PartyMember("Mercurion"));
		partyMembers.add(new PartyMember("Karalius"));
		partyMembers.add(new PartyMember("Turin"));
		partyMembers.add(new PartyMember("Manasses"));
		new_panel.updatePeople(frame, partyMembers);
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
    public static Comparator <PartyMember> nameComp = new Comparator <PartyMember> (){ //for Collections.sort()
        public int compare(PartyMember p1, PartyMember p2){
            return p1.name.compareTo(p2.name);
        }//compare
    };//pointComp


}

class WOMpanel extends JPanel {
    Image image = null;
	String speaker = "";
    ArrayList <String> items = new ArrayList<>();
    ArrayList <String> people = new ArrayList<>(); 
    public WOMpanel(ArrayList <String> inventory, ArrayList <PartyMember> party) {
        setBackground(Color.WHITE);
        items = inventory;
        for(PartyMember m:party){
            people.add(m.getName());
        };
    }

    public void paint(Graphics g) { //called automatically when frame becomes visible (either in setVisible(true) or in repaint(0))
        super.paint(g);
        //g.drawImage(image, 370, 40, 180, 200, this);
        Font font = new Font("Times New Roman", Font.PLAIN, 12);
        g.setFont(font);
        g.drawString("INVENTORY (alphabetically):", 20, 20);
        g.drawString("PARTY (alphabetically): ", 200, 20);
		g.drawString("CURRENT SPEAKER: ", 450, 20);
        font = new Font("Times New Roman", Font.PLAIN, 12);
        g.setFont(font);
        
        //inventory
        int i=50;
        if(items.size()==0){
            g.drawString("Empty", 20, i);
        }
        else{
            for(int j=0; j<items.size(); j++){
				if(j==0){g.drawString("$"+items.get(j), 20, i);} //dollars is always sorted first in inventory (since it's a number)
																//add dollar sign when drawing for clarity of what the number is
                else{g.drawString(items.get(j), 20, i);}
                i+=20;
            }
        }
        
        //party images
        i=50; //reset i
		int horizontal = 200;
        if(people.size()==0){
            g.drawString("Empty", 200, i);
        }
        else{
            Image new_image;
			for(int p=0; p<people.size(); p++){
				try {
                    new_image = ImageIO.read(new File(people.get(p)+".png"));
					if(p%2==1){
						horizontal += 100;
						g.drawImage(new_image, horizontal, i, 90, 100, this);
						horizontal -= 100;
						i+=130;
					}
                    else{
						g.drawImage(new_image, horizontal, i, 90, 100, this);
					}
                }
                catch(IOException e) {
                    System.out.println("File not found");
                    e.printStackTrace();
                }
			}
        }

        horizontal=200;
		i = 160;
        if(people.size()!=0){
			for(int p=0; p<people.size(); p++){
				if(p%2==1){
					horizontal += 100;
					g.drawString(people.get(p), horizontal, i);
					horizontal -= 100;
					i+=130;
				}
                else{
					g.drawString(people.get(p), horizontal, i);
				}
			}
        }

		if(!speaker.equals("")){
			try {
				Image new_image = ImageIO.read(new File(speaker+".png"));
				g.drawImage(new_image, 450, 60, 90, 100, this);
				g.drawString(speaker, 450,50);
			}
			catch(IOException e) {
				System.out.println("File not found");
				e.printStackTrace();
			}
		}
    }

    public void updateItems(JFrame frame, ArrayList <String> inventory){
        this.items = inventory;
		frame.repaint(0);
        frame.setVisible(true);
    }

    public void updatePeople(JFrame frame, ArrayList <PartyMember> party){
        this.people = new ArrayList<>();
        for(PartyMember m:party){
            people.add(m.getName());
        }
		frame.repaint(0);
        frame.setVisible(true);
    }

	public void updateSpeaker(JFrame frame, String s){
		speaker = s;
		frame.repaint(0);
        frame.setVisible(true);
	}
}//WOMpanel class

class WOMfilereader {	// class header

	/*public static void main(String[] args) {	// main method header
		WOMfilereader fr = new WOMfilereader();
		fr.read();
	}*/
	
	public ArrayList<String> read() {	// reads savefile
		
		Scanner carl = new Scanner(System.in);	// new Scanner object
		Scanner fileReader = null;
		ArrayList<String> fileData = new ArrayList<>();
		
		String choice = "";
		String username = "";
		int count = 0;
		System.out.print("Do you have a preexisting save file? ");
		choice = carl.nextLine();
		
		if(choice.toLowerCase().equals("yes")) {
			
			System.out.print("What is your username (case sensitive)? ");
			username = carl.nextLine().replace(" ", "");
			
			try {
				String filename = username + "Savefile.txt";
				fileReader = new Scanner(new File(filename));	// read file
			}
			catch (FileNotFoundException ex) {System.out.print(ex);}
			
			while ( fileReader.hasNext() ){	//check if end of file
				String temp = fileReader.nextLine(); 
				if(count % 2 == 0)
					fileData.add(temp);
				count++;
			}
			
			// fileData.set(2, listSplitter((String)(fileData.get(2))));
			// fileData.set(3, listSplitter((String)(fileData.get(3))));

			//System.out.println(fileData);
			
		}
		System.out.println();
		
		return fileData;
		
	}
	
	private ArrayList<String> listSplitter (String dataIn){	// converts strings to list
		
		String[] partynames = dataIn.split("\t");
		return new ArrayList<>(Arrays.asList(partynames));
		
	}
	
}