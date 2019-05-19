import java.util.Random;
import java.util.Scanner;
public class TheForest {
	
	static int[][] arrMap = new int[5][5]; // 2 dimensional array to use as the map.
	static int iPlayerX = 2;
	static int iPlayerY = 4; // Player's starting coordinates.
	static Scanner reader = new Scanner(System.in); // For getting user input.
	static boolean bMoldarkDead = false;
	static int iLairX;
	static int iLairY = 1;
	static int iPlayerLevel = 1;
	static int iPlayerXP = 0;
	static int iPlayerGold = 0;
	static String sWeapon = "Wooden Sword"; // Amazing starter weapon, I know.
	static String sShield = "Hide Shield";
	static boolean bShielding = false;
	
	public static void main(String[] args) 
	{
		boolean bAcceptable = false;
		while (!bAcceptable) // https://www.youtube.com/watch?v=07So_lJQyqw
		{
			String sResponse = Intro();
			if (sResponse.intern() == "Yes")
			{
				System.out.println("You enter from the South with no idea what you will find inside...");
				PlayGame();
				bAcceptable = true;
			}
			
			else if (sResponse.intern() == "No")
			{
				System.out.println("Very well, but hurry back, the longer we wait the stronger "
						+ "he grows!");
				bAcceptable = true;
				reader.nextLine(); // Waits for enter before moving on.
			}
			
			else if (sResponse.intern() == "Help")
			{
				System.out.println(Help("Y/N"));
			}
			
			else
			{
				System.out.println("Enter a valid command, for a list of commands type 'Help'");
			}
		}
	}
	
	private static String Intro()
	{
		System.out.println("You are a poor townsperson searching for fame and riches. there has been talk amongst");
		System.out.println("the townspeople of a great reward from the king for any who can thwart the evil necromancer ");
		System.out.println("residing in the forest of Moldark, named for the elf who has made it his lair.");
		System.out.println("He has made the undead his guards and taking him down will be no small feat.");
		System.out.println("Do you accept this quest?");
		String sAnswer = reader.nextLine();
		return sAnswer;
	}
	
	private static void PlayGame()
	{
		SetMap();
		while (!bMoldarkDead)
		{
			System.out.println(ObserveSurroundings());
			String sAction = GetAction();
			
			if (sAction.intern() == "Move")
			{
				if (arrMap[iPlayerX][iPlayerY] == 0 || arrMap[iPlayerX][iPlayerY] == 4
						|| arrMap[iPlayerX][iPlayerY] == 5)
				{
					Move();
				}
				
				else
				{
					System.out.println("You can't move once you have been "
							+ "discovered by an enemy.");
				}
			}
			
			else if (sAction.intern() == "Fight")
			{
				Fight();
			}
			
			else if (sAction.intern() == "Flee")
			{
				Flee();
			}
			
			else if (sAction.intern() == "Open")
			{
				Open();
			}
			
			else if (sAction.intern() == "Help")
			{
				System.out.println(Help("GetAction"));
			}
			
			else
			{
				System.out.println("Enter a valid command, for a list of commands type 'Help'");
			}
		}
	}
	
	private static void SetMap()
	{
		for (int i = 0; i <= 4; i++)
		{
			for (int j = 0; j <= 4; j++)
			{
				Random rand = new Random();
				arrMap[i][j] = rand.nextInt(5);
			}
		}
		PlaceLair();
		arrMap[iLairX][iLairY] = 5;
		
	}
	
	private static String ObserveSurroundings()
	{
		if (arrMap[iPlayerX][iPlayerY] == 0)
		{
			return "You find yourself in a clearing.";
		}
		
		else if (arrMap[iPlayerX][iPlayerY] == 1)
		{
			return "You find yourself in front of a reanimated skeleton!";
		}
		
		else if (arrMap[iPlayerX][iPlayerY] == 2)
		{
			return "You find yourself in front of an undead soldier!";
		}
		
		else if (arrMap[iPlayerX][iPlayerY] == 3)
		{
			return "You find yourself in front of an undead giant!";
		}
		
		else if (arrMap[iPlayerX][iPlayerY] == 4)
		{
			return "You find yourself in front of a chest.";
		}
		
		else if (arrMap[iPlayerX][iPlayerY] == 5)
		{
			return "You have found Moldark's lair!";
		}
		
		else
		{
			return "ERROR1";
		}
		
	}
	
	private static void Move()
	{
		System.out.println("In which direction would you like to move?");
		String sDirection = reader.nextLine();
		
		if (sDirection.intern() == "North")
		{
			if (iPlayerY == 0) // it's 0 not 4 because I made it back to front and never changed it.
			{
				System.out.println("You exit the forest on the northern edge but quickly "
						+ "return to avoid ridicule from the townspeople.");
			}
			
			else
			{
				iPlayerY--;
			}
			
		}
		
		else if (sDirection.intern() == "South")
		{
			if (iPlayerY == 4)
			{
				System.out.println("You exit the forest from the direction you entered and wonder "
						+ "if you may be too simple for adventuring...");
			}
			
			else
			{
				iPlayerY++;
			}
		}
		
		else if (sDirection.intern() == "East")
		{
			if (iPlayerX == 4)
			{
				System.out.println("You exit the forest on the eastern edge but quickly "
						+ "return to avoid ridicule from the townspeople.");
			}
			
			else
			{
				iPlayerX++;
			}
		}
		
		else if (sDirection.intern() == "West")
		{
			if (iPlayerX == 0)
			{
				System.out.println("You exit the forest on the western edge but quickly "
						+ "return to avoid ridicule from the townspeople.");
			}
			
			else
			{
				iPlayerX--;
			}
		}
		
		else if (sDirection.intern() == "Help")
		{
			Help("Move");
		}
		
		else
		{
			System.out.println("Enter a valid command, for a list of commands type 'Help'");
		}
	}
	
	private static void PlaceLair()
	{
		Random rand = new Random();
		iLairX = rand.nextInt(3) + 1;
	}
	
	private static String GetAction()
	{
		System.out.println("What would you like to do?");
		String sAction = reader.nextLine();
		return sAction;
	}
	
	private static void Fight()
	{
		if (arrMap[iPlayerX][iPlayerY] == 0) // If tile has a clearing
		{
			System.out.println("You look around, sword drawn with nothing to fight and feel"
					+ " a simpleton.");
		}
		
		else if (arrMap[iPlayerX][iPlayerY] == 1) // If tile has a skeleton
		{
			Battle("Skeleton");
			iPlayerXP = iPlayerXP + 3;
			if (LevelUp())
			{
				System.out.println("You leveld up! You are now level " + iPlayerLevel + "!");
			}
		}
		
		else if (arrMap[iPlayerX][iPlayerY] == 2) // If tile has an undead soldier
		{
			Battle("Undead Soldier");
			iPlayerXP = iPlayerXP + 6;
			if (LevelUp())
			{
				System.out.println("You leveld up! You are now level " + iPlayerLevel + "!");
			}
		}
		
		else if (arrMap[iPlayerX][iPlayerY] == 3) // If tile has an undead giant
		{
			Battle("Undead Giant");
			iPlayerXP = iPlayerXP + 18;
			if (LevelUp())
			{
				System.out.println("You leveld up! You are now level " + iPlayerLevel + "!");
			}
		}
		
		else if (arrMap[iPlayerX][iPlayerY] == 4) // If tile has a chest
		{
			System.out.println("You hack and slash at the chest but to your dismay it doesn't turn "
					+ "into a mimick and you just look like an idiot.");
		}
		
		else if (arrMap[iPlayerX][iPlayerY] == 5) // If tile has Moldark's lair
		{
			MoldarkBattle();
		}
		
		else
		{
			System.out.println("ERROR2");
		}
		
	}
	
	private static void Flee()
	{
		if (arrMap[iPlayerX][iPlayerY] == 1)
		{
			Random rand = new Random();
			int iLuck = rand.nextInt(2);
			if (iLuck == 0)
			{
				System.out.println("You were unlucky and failed to flee.");
				Battle("Skeleton");
			}
			
			else
			{
				System.out.println("You fled successfully!");
				Move();
			}
		}
		
		else if (arrMap[iPlayerX][iPlayerY] == 2)
		{
			Random rand = new Random();
			int iLuck = rand.nextInt(3);
			if (iLuck == 0)
			{
				System.out.println("You were unlucky and failed to flee.");
				Battle("Undead Soldier");
			}
			
			else
			{
				System.out.println("You fled successfully!");
				Move();
			}
		}
		
		else if (arrMap[iPlayerX][iPlayerY] == 3)
		{
			Random rand = new Random();
			int iLuck = rand.nextInt(20);
			if (iLuck == 0)
			{
				System.out.println("You were unlucky and failed to flee.");
				Battle("Undead Giant");
			}
			
			else
			{
				System.out.println("You fled successfully!");
				Move();
			}
		}
		
		else if (arrMap[iPlayerX][iPlayerY] == 0)
		{
			System.out.println("Congratulations, you managed to flee from a clearing"
					+ " without tripping and falling.");
		}
		
		else if (arrMap[iPlayerX][iPlayerY] == 4)
		{
			System.out.println("You successfully ran away from a butt load of gold, "
					+ "each to their own I guess.");
		}
		
		else if (arrMap[iPlayerX][iPlayerY] == 5)
		{
			System.out.println("You realise just how scary Moldark is and run"
					+ " away, soiling your pants as you do so.");
		}
	}
	
	private static void Open()
	{
		if (arrMap[iPlayerX][iPlayerY] == 4)
		{
			Random rand = new Random();
			int iGold = rand.nextInt(50);
			iPlayerGold = iPlayerGold + iGold;
			System.out.println("You found " + iGold + " gold inside the chest! You "
					+ "now have " + iPlayerGold + " gold!");
			arrMap[iPlayerX][iPlayerY] = 0;
		}
	}
	
	private static void Battle(String sEnemy) // I love the smell of napalm in the morning.
	{
		Random rand = new Random();
		int iHealth = 0;
		int iDamage = 0;
		int iPlayerHealth = 10 + iPlayerLevel * 10;
		int iPlayerDamage = 0;
		
		if (sEnemy.intern() == "Skeleton")
		{
			iHealth = rand.nextInt(5) + 11;
			iDamage = rand.nextInt(3) + 1;
		}
		
		else if (sEnemy.intern() == "Undead Soldier")
		{
			iHealth = rand.nextInt(5) + 15;
			iDamage = rand.nextInt(4) + 3;
		}
		
		else if (sEnemy.intern() == "Undead Giant")
		{
			iHealth = rand.nextInt(7) + 50;
			iDamage = rand.nextInt(5) + 10;
		}
		
		while (iHealth >= 1 && iPlayerHealth >= 1)
		{
			System.out.println("The " + sEnemy + " before you has " + iHealth + " HP "
					+ "and does " + iDamage + " damage. What do you wish to do?");
			String sAnswer = reader.nextLine();
			
			if (sAnswer.intern() == "Attack")
			{
				iPlayerDamage = Attack();
				iHealth = iHealth - iPlayerDamage;
				
				if (iHealth <= 0)
				{
					System.out.println("You did " + iPlayerDamage + " points of damage "
							+ "to the " + sEnemy + " and managed to kill it!");
					arrMap[iPlayerX][iPlayerY] = 0;
				}
				
				else
				{
					System.out.println("You did " + iPlayerDamage + " points of damage "
							+ "to the " + sEnemy + " it now has " + iHealth + " HP.");
					iPlayerHealth = iPlayerHealth - iDamage;
					
					if (iPlayerHealth <= 0)
					{
						System.out.println("The " + sEnemy + " struck you with a fatal "
								+ "blow cand you fall to the ground!");
						System.exit(0);
					}
					
					System.out.println("The " + sEnemy + " attacked you dealing "
							+ iDamage + " damage. You have " + iPlayerHealth + " HP "
									+ "remainig for this battle.");
					
				}
				
			}
			
			else if (sAnswer.intern() == "Raise Shield")
			{
				if (!bShielding)
				{
					bShielding = true;
					iPlayerHealth = iPlayerHealth + 10;
					System.out.println("Shielding yourself has increased your HP by 10 points.");
				}
				
				else
				{
					System.out.println("You are already shielding.");
				}
			}
			
			else if (sAnswer.intern() == "Lower Shield")
			{
				if (iPlayerHealth >= 11)
				{
					if (bShielding)
					{
						iPlayerHealth = iPlayerHealth - 10;
						bShielding = false;
						System.out.println("You lower your shield and forfeit the extra HP"
								+ " it afforded you.");
					}
					
					else 
					{
						System.out.println("You are not shielding.");
					}
				}
				
				else
				{
					System.out.println("Your HP is too low too lower your shield.");
				}
				
			}
			
			else if (sAnswer.intern() == "Help")
			{
				System.out.println(Help("Battle"));
			}
			
			else
			{
				System.out.println("Enter a valid command.");
			}
		}
		
		
		
		
	}
	
	private static int Attack()
	{
		int iDamage = 0;
		if (sWeapon == "Wooden Sword")
		{
			if (bShielding)
			{
				iDamage = iPlayerLevel * 3;
			}
			
			else 
			{
				iDamage = iPlayerLevel * 6;
			}
		}
		
		else if (sWeapon == "Longsword")
		{
			if (bShielding)
			{
				iDamage = iPlayerLevel * 3 + 5;
			}
			
			else 
			{
				iDamage = iPlayerLevel * 6 + 5;
			}
		}
		
		else if (sWeapon == "Reallylongsword")
		{
			if (bShielding)
			{
				iDamage = iPlayerLevel * 3 + 8;
			}
			
			else 
			{
				iDamage = iPlayerLevel * 6 + 8;
			}
		}
		
		else if (sWeapon == "Golden Fork")
		{
			if (bShielding)
			{
				iDamage = iPlayerLevel * 3 - 2;
			}
			
			else 
			{
				iDamage = iPlayerLevel * 6 - 2;
			}
		}
		
		return iDamage;
	}
	
	private static void MoldarkBattle()
	{
		Vendor();
		
		int iHealth = 300;
		int iDamage = 25;
		int iPlayerHealth = 10 + iPlayerLevel * 10;
		int iPlayerDamage = 0;
		
		while (iHealth >= 1 && iPlayerHealth >= 1)
		{
			System.out.println("Moldark has " + iHealth + " HP "
					+ "and does " + iDamage + " damage. What do you wish to do?");
			String sAnswer = reader.nextLine();
			
			if (sAnswer.intern() == "Attack")
			{
				iPlayerDamage = Attack();
				iHealth = iHealth - iPlayerDamage;
				
				if (iHealth <= 0)
				{
					System.out.println("You did " + iPlayerDamage + " points of damage "
							+ "to Moldark and managed to kill him!");
					bMoldarkDead = true;
				}
				
				else
				{
					System.out.println("You did " + iPlayerDamage + " points of damage "
							+ "to Moldark, he now has " + iHealth + " HP.");
					iPlayerHealth = iPlayerHealth - iDamage;
					
					if (iPlayerHealth <= 0)
					{
						System.out.println("A burst of energy erupts from Moldark's hands and you"
								+ " fall to the ground, a squishy sack of failure.");
						System.exit(0);
					}
					
					System.out.println("Moldark attacked you dealing "
							+ iDamage + " damage. You have " + iPlayerHealth + " HP "
									+ "remainig for this battle.");
					
				}
				
			}
			
			else if (sAnswer.intern() == "Raise Shield")
			{
				if (!bShielding)
				{
					bShielding = true;
					
					if (sShield == "Hide Shield")
					{
						iPlayerHealth = iPlayerHealth + 10;
						System.out.println("Shielding yourself has increased your HP by 10 points.");
					}
					
					else if (sShield == "Iron Shield")
					{
						iPlayerHealth = iPlayerHealth + 22;
						System.out.println("Shielding yourself has increased your HP by 22 points.");
					}
					
					else if (sShield == "Steel Shield")
					{
						iPlayerHealth = iPlayerHealth + 25;
						System.out.println("Shielding yourself has increased your HP by 25 points.");
					}
				}
				
				else
				{
					System.out.println("You are already shielding.");
				}
			}
			
			else if (sAnswer.intern() == "Lower Shield")
			{
				if (iPlayerHealth >= 11)
				{
					if (bShielding)
					{
						bShielding = false;
						
						if (sShield == "Hide Shield")
						{
							iPlayerHealth = iPlayerHealth - 10;
							System.out.println("You lower your shield and forfeit the extra HP"
									+ " it afforded you.");
						}
						
						else if (sShield == "Iron Shield")
						{
							iPlayerHealth = iPlayerHealth - 22;
							System.out.println("You lower your shield and forfeit the extra HP"
									+ " it afforded you.");
						}
						
						else if (sShield == "Steel Shield")
						{
							iPlayerHealth = iPlayerHealth - 25;
							System.out.println("You lower your shield and forfeit the extra HP"
									+ " it afforded you.");
						}
					}
					
					else 
					{
						System.out.println("You are not shielding.");
					}
				}
				
				else
				{
					System.out.println("Your HP is too low too lower your shield.");
				}
				
			}
			
			else if (sAnswer.intern() == "Help")
			{
				System.out.println(Help("Battle"));
			}
			
			else
			{
				System.out.println("Enter a valid command.");
			}
		}
	}
	
	private static void Vendor()
	{
		boolean bPurchasing = true;
		
		System.out.println("Before you stands a large iron and glass machine that"
				+ " seems to be an automated vendor with a slot for gold pieces.");
		while (bPurchasing)
		{
			System.out.println("You have " + iPlayerGold + " gold. What would you like to purchase?");
			System.out.println("A1: Longsword (+5 Damage) - 30 gold				A2: Reallylongsword (+8 Damage) - 50 gold");
			System.out.println("B1: Iron Shield (+12 Shielding HP) - 40 gold	B2: Steel Shield (+15 Shielding HP) - 60 gold");
			System.out.println("C1: Golden Fork (-2 Damage) -300 gold");
			String sPurchase = reader.nextLine();
			
			if (sPurchase.intern() == "A1")
			{
				sWeapon = "Longsword";
				System.out.println("You purchased the longsword.");
				iPlayerGold = iPlayerGold - 30;
			}
			
			if (sPurchase.intern() == "A2")
			{
				sWeapon = "Reallylongsword";
				System.out.println("You purchased the reallylongsword.");
				iPlayerGold = iPlayerGold - 50;
			}
			
			if (sPurchase.intern() == "B1")
			{
				sWeapon = "Iron Shield";
				System.out.println("You purchased the iron shield");
				iPlayerGold = iPlayerGold - 40;
			}
			
			if (sPurchase.intern() == "B2")
			{
				sWeapon = "Steel Shield";
				System.out.println("You purchased the steel shield.");
				iPlayerGold = iPlayerGold - 60;
			}
			
			if (sPurchase.intern() == "C1")
			{
				System.out.println("The golden fork? Are you sure? There really isn't anything special about it.");
				String sAnswer = reader.nextLine();
				
				if (sAnswer.intern() == "Yes")
				{
					sWeapon = "Golden Fork";
					System.out.println("Very well, you purchased the golden fork.");
					iPlayerGold = iPlayerGold - 300;
				}
				
				else if (sAnswer.intern() == "No")
				{
					System.out.println("Of course not, it's a fork.");
				}
				
				else if (sAnswer.intern() == "Help")
				{
					System.out.println(Help("Y/N"));
				}
				
				else
				{
					System.out.println("Enter a valid command, for a list of commands type 'Help'.");
				}
			}
			
			else if (sPurchase.intern() == "Help")
			{
				System.out.println(Help("Purchasing"));
			}
			
			else
			{
				System.out.println("Enter a valid command, for a list of commands type 'Help'.");
			}
			
			System.out.println("You have " + iPlayerGold + " remaining, would you like to continue purchasing?");
			String sResponse = reader.nextLine();
			
			if (sResponse.intern() == "Yes")
			{
				
			}
			
			else if (sResponse.intern() == "No")
			{
				bPurchasing = false;
			}
			
			else if (sResponse.intern() == "Help")
			{
				System.out.println(Help("Y/N"));
			}
			
			else
			{
				System.out.println("Enter a valid command, for a list of commands type 'Help'");
			}
		}
	}
	private static boolean LevelUp() //will get overpowered as hell when you get to level 5 or 6.
	{								 //Abuse to your hearts content.
		int iPointsRequired = iPlayerLevel * 3 + 2;
		if (iPlayerXP >= iPointsRequired)
		{
			iPlayerXP = iPlayerXP - iPointsRequired;
			iPlayerLevel++;
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	private static String Help(String sSituation)
	{
		String sHelp = "There is no help page for this situation."; //TODO finish Help.
		if (sSituation == "Y/N")
		{
			sHelp = "Respond with 'Yes' or 'No'";
		}
		
		else if (sSituation == "Battle")
		{
			sHelp = "Respond with 'Attack', 'Raise Shield or 'Lower Shield'";
		}
		
		else if (sSituation == "GetAction")
		{
			sHelp = "Respond with 'Fight', 'Flee', 'Open' or 'Move'";
		}
		
		else if (sSituation == "Purchasing")
		{
			sHelp = "Respond with 'A1', 'A2', 'B1', 'B2' or 'C1'";
		}
		
		return sHelp;
	}
}






