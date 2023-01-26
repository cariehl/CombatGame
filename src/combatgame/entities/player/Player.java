package combatgame.entities.player;

import java.util.Scanner;

import combatgame.entities.Entity;
import combatgame.entities.enemies.Enemy;
import combatgame.naming.SpecificName;

/**
 * The {@link Player} class represents the user's avatar within the game. This
 * class keeps track of statistics and information related to the player, i.e.
 * name, health, etc.
 *
 * This class also provides the interface for other classes to modify player
 * information, such as dealing damage to the player.
 */
public class Player extends Entity {
	/**
	 * Represents an action that the user can choose to take during combat.
	 */
	private enum CombatActionChoice {
		ATTACK, DEFEND, RUN_AWAY
	}

	/**
	 * The scanner to use to retrieve user input.
	 */
	private Scanner scanner;

	/**
	 * The amount of damage this Player deals per attack.
	 */
	private int attackPower;

	/**
	 * Is this Player blocking or not?
	 */
	private boolean isBlocking;

	/**
	 * Create a new Player with the given name and amount of starting health, and
	 * default amount of attack power.
	 *
	 * @param name           The name of this Player.
	 * @param startingHealth The amount of health this Player starts with.
	 * @param attackPower    The amount of attack power this Player starts with.
	 * @param scanner        The Scanner to use for retrieving user input.
	 */
	public Player(String name, int startingHealth, int attackPower, Scanner scanner) {
		super(new SpecificName(name), startingHealth);
		this.scanner = scanner;
		this.attackPower = attackPower;
		this.isBlocking = false;
	}

	/**
	 * Prompt the user to select which combat action they would like to perform,
	 * then perform it against the given Enemy.
	 *
	 * @param player The Enemy that this Player is in combat with.
	 */
	public void performCombatAction(Enemy enemy) {
		// Reset our blocking status.
		isBlocking = false;

		// Prompt the player for input.
		CombatActionChoice userChoice = promptUserForInput();

		// Add a line of separation, so it's easier to tell what's happening when.
		System.out.println();

		// Do something based on the player's choice.
		if (userChoice == CombatActionChoice.ATTACK) {
			System.out.printf("You swing your laser sword at the %s!\n", enemy.NAME);
			enemy.takeDamage(attackPower);
		} else if (userChoice == CombatActionChoice.DEFEND) {
			System.out.println("You raise your energy shield...");
			isBlocking = true;
		} else if (userChoice == CombatActionChoice.RUN_AWAY) {
			System.out.println("Heroes don't run from battle! (You lose your turn for being a coward...)");
		}
	}

	/**
	 * Deal damage to this Player. Damage may be modified if the Player is blocking.
	 *
	 * @param damageAmount The amount of incoming damage to deal.
	 */
	@Override
	public void takeDamage(int damageAmount) {
		// If we are blocking, reduce the incoming damage by 60%.
		int blockedDamageAmount = 0;
		if (isBlocking) {
			blockedDamageAmount = (int)((double)damageAmount * 0.6);
			damageAmount = damageAmount - blockedDamageAmount;
		}

		// Print a message describing the damage.
		String message = String.format("You take %d damage", damageAmount);
		if (blockedDamageAmount > 0) {
			message += String.format(" (blocked %d damage)", blockedDamageAmount);
		}
		message += ".";
		System.out.println(message);

		// Tell the parent class how much damage we took.
		super.takeDamage(damageAmount);
	}

	/**
	 * Prompt the user to choose which combat action they would like to perform.
	 *
	 * @return The action that the user selects.
	 */
	private CombatActionChoice promptUserForInput() {
		while (true) {
			// Add a line of separation, so it's easier to tell what's happening when.
			System.out.println();

			// Prompt the player to select an action.
			System.out.println("What would you like to do?");
			System.out.println("0 - Attack");
			System.out.println("1 - Defend");
			System.out.println("2 - Run away");
			System.out.print("> ");

			int userChoice = scanner.nextInt();
			if (userChoice >= 0 && userChoice < CombatActionChoice.values().length) {
				return CombatActionChoice.values()[userChoice];
			} else {
				System.out.println("Unrecognized action. Please select one of the available options.");
			}
		}
	}
}
