import java.util.Scanner;

import combatgame.CombatGameEngine;
import combatgame.entities.enemies.Enemy;
import combatgame.entities.enemies.TrashBot;
import combatgame.entities.player.Player;

/**
 * This class provides the entrypoint for the program via the `main` method.
 * This is also where we perform initialization and configuration of the
 * program.
 */
public class App {
	/**
	 * The list of enemies for the player to fight, in order of appearance.
	 */
	private static final Enemy[] ENEMY_LIST = new Enemy[] {
			new TrashBot()
	};

	/**
	 * The player's default health at the start of the game.
	 */
	private static final int PLAYER_STARTING_HEALTH = 10;

	/**
	 * The player's default attack power at the start of the game.
	 */
	private static final int PLAYER_STARTING_ATTACK_POWER = 3;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Print some lines of spacing, to "clear" the console.
		for (int i = 0; i < 8; ++i) {
			System.out.println();
		}

		// Introduction
		System.out.println("The year is 2050.");
		System.out.println("Machines powered by artifical intelligence have taken over the planet.");
		System.out.println("You are one of the last remaining human survivors.");
		System.out.println("Your choice is simple: fight, or die.\n\n\n");

		// Name prompt
		System.out.println("What is your name?");
		System.out.print("> ");
		String playerName = scanner.nextLine();

		// Finish introduction
		System.out.println(
				"\n\n\nYou have been sent on a mission to defeat a local AI leader known as \"Scarlet Steel\".");
		System.out.printf("Good luck, %s.\n\n\n\n", playerName);

		// Initialize and run engine.
		Player player = new Player(playerName, PLAYER_STARTING_HEALTH, PLAYER_STARTING_ATTACK_POWER, scanner);
		CombatGameEngine engine = new CombatGameEngine(player, ENEMY_LIST);
		engine.run();

		scanner.close();
	}
}
