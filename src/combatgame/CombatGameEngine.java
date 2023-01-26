package combatgame;

import combatgame.entities.enemies.Enemy;
import combatgame.entities.player.Player;
import combatgame.naming.Capitalization;

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
		for (Enemy enemy : enemies) {
			// Introduce this enemy.
			System.out.println();
			System.out.println("You continue on your journey...");
			System.out.printf("You encounter %s!\n", enemy.NAME.getNameIndefiniteArticle(Capitalization.NONE));

			while (!player.isDead() && !enemy.isDead()) {
				// Display current health values.
				System.out.printf("You have %d health.\n", player.getHealth());
				System.out.printf("%s has %d health.\n", enemy.NAME.getNameDefiniteArticle(Capitalization.FIRST_LETTER),
						enemy.getHealth());

				// Player takes their turn.
				player.performCombatAction(enemy);

				if (enemy.isDead()) {
					// The player killed the enemy. Move on to the next one.
					System.out.printf("You defeat %s!\n", enemy.NAME.getNameDefiniteArticle(Capitalization.NONE));
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
			System.out.println("You are defeated in battle!");
		} else {
			System.out.println("Congratulations, you are victorious!");
		}
	}
}
