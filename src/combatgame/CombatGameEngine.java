package combatgame;

import combatgame.entities.enemies.Enemy;
import combatgame.entities.player.Player;

/**
 * The {@link CombatGameEngine} is what drives the entire combat game. You can
 * begin a combat game by creating an instance of this class and calling the
 * `run()` method.
 */
public class CombatGameEngine {
	/**
	 * The player of this combat game.
	 */
	private Player player;

	/**
	 * The list of enemies that the player will fight, in order of appearance.
	 */
	private Enemy[] enemies;

	/**
	 * Construct a new {@link CombatGameEngine}.
	 * 
	 * @param player  The player of the combat game.
	 * @param enemies A list of enemies for the player to fight, in order of
	 *                appearance.
	 */
	public CombatGameEngine(Player player, Enemy[] enemies) {
		this.player = player;
		this.enemies = enemies;
	}

	/**
	 * Run the combat game represented by this engine. This method will not return
	 * until the player either defeats all of the enemies, or dies.
	 */
	public void run() {
		System.out.println(String.format("Welcome, %s! Your journey begins!", player.NAME));

		for (Enemy enemy : enemies) {
			// Introduce this enemy.
			System.out.println();
			System.out.println("You continue on your journey...");
			System.out.println(String.format("You encounter a %s!", enemy.NAME));

			while (!player.isDead() && !enemy.isDead()) {
				// Display current health values.
				System.out.println(String.format("You have %d health.", player.getHealth()));
				System.out.println(String.format("The %s has %d health.", enemy.NAME, enemy.getHealth()));

				// Player takes their turn.
				player.performCombatAction(enemy);

				if (enemy.isDead()) {
					// The player killed the enemy. Move on to the next one.
					System.out.println(String.format("You defeat the %s!", enemy.NAME));
					break;
				} else {
					// The enemy is alive, so they take their turn.
					enemy.performCombatAction(player);
				}
			}
		}

		// When we get to this point, it means either the player has died or
		// they have defeated all of the enemies.
		if (player.isDead()) {
			System.out.println("You have been defeated in battle!");
		} else {
			System.out.println("Congratulations, you are victorious!");
		}
	}
}
