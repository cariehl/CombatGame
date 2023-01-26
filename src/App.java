import java.util.Scanner;

import combatgame.CombatGameEngine;
import combatgame.entities.enemies.Enemy;
import combatgame.entities.enemies.Goblin;
import combatgame.entities.enemies.RagingGoblin;
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
			// TODO: Add your custom Enemy subclasses here.
			new Goblin(), new RagingGoblin()
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

		// Print some lines of spacing, to make it easier to tell what is happening.
		for (int i = 0; i < 5; ++i) {
			System.out.println();
		}

		System.out.println("Hello, adventurer. What is your name?");
		System.out.print("> ");
		String playerName = scanner.nextLine();

		Player player = new Player(playerName, PLAYER_STARTING_HEALTH, PLAYER_STARTING_ATTACK_POWER, scanner);

		CombatGameEngine engine = new CombatGameEngine(player, ENEMY_LIST);
		engine.run();

		scanner.close();
	}
}
