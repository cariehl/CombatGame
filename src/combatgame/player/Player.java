package combatgame.player;

import java.util.InputMismatchException;
import java.util.Scanner;

import combatgame.entities.Entity;
import combatgame.entities.enemies.Enemy;
import combatgame.items.Item;

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
	 * This Player's inventory.
	 */
	private Inventory inventory;

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
		this(name, startingHealth, attackPower, new Item[] {}, scanner);
	}

	/**
	 * Create a new Player with the given name and amount of starting health, and
	 * default amount of attack power.
	 *
	 * @param name           The name of this Player.
	 * @param startingHealth The amount of health this Player starts with.
	 * @param attackPower    The amount of attack power this Player starts with.
	 * @param startingItems  The items that should begin in the Player's inventory.
	 * @param scanner        The Scanner to use for retrieving user input.
	 */
	public Player(String name, int startingHealth, int attackPower, Item[] startingItems, Scanner scanner) {
		super(name, startingHealth);
		this.scanner = scanner;
		this.attackPower = attackPower;
		this.isBlocking = false;
		this.inventory = new Inventory(startingItems);
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

		// Prompt the player to choose an action to perform.
		CombatAction combatAction = promptUserForActionChoice(enemy);

		// Add a line of separation, so it's easier to tell what's happening when.
		System.out.println();

		// Resolve the player's chosen action.
		combatAction.resolve();
	}

	@Override
	public void restoreHealth(int healthAmount) {
		System.out.printf("You restore %d hitpoints.\n", healthAmount);
		super.restoreHealth(healthAmount);
	}

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
	 * @param enemy The enemy that the player is currently fighting.
	 * @return The action that the user selects.
	 */
	private CombatAction promptUserForActionChoice(Enemy enemy) {
		while (true) {
			// Add a line of separation, so it's easier to tell what's happening when.
			System.out.println();

			// Prompt the player to select an action.
			System.out.println("What would you like to do?");
			System.out.println("0 - Attack");
			System.out.println("1 - Defend");
			System.out.println("2 - Use an item");
			System.out.println("3 - Run away");
			System.out.print("> ");

			int userChoice = scanner.nextInt();
			if (userChoice < 0 || userChoice >= CombatActionChoice.values().length) {
				System.out.println("Unrecognized input. Please select one of the available actions by number.");
				continue;
			}

			CombatActionChoice actionChoice = CombatActionChoice.values()[userChoice];
			switch (actionChoice) {
			case ATTACK:
				return new AttackAction(enemy);

			case DEFEND:
				return new DefendAction();

			case VIEW_INVENTORY:
				if (inventory.size() == 0) {
					System.out.println("\nYour inventory is empty!");
					break;
				} else {
					int itemIndex = promptUserForInventoryChoice();
					if (itemIndex == -1) { // -1 is the "cancel" option when selecting an item.
						break;
					} else {
						return new UseItemAction(enemy, itemIndex);
					}
				}

			case RUN_AWAY:
				return new RunAwayAction();

			default:
				break;
			}
		}
	}

	/**
	 * Prompt the user to select an item from their inventory to use.
	 *
	 * @return The index of the selected item within the player's inventory, or -1
	 *         if the user selects the "cancel" option.
	 */
	private int promptUserForInventoryChoice() {
		while (true) {
			// Add a line of separation, so it's easier to tell what's happening when.
			System.out.println();

			// Prompt the player to select an item to use.
			System.out.println("Which item do you want to use? (-1 to cancel)");
			inventory.showItems();
			System.out.print("> ");

			int userChoice;
			try {
				userChoice = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Unrecognized action. Please select one of the available options.");
				scanner.nextLine();
				continue;
			}
      
			if (userChoice >= -1 && userChoice < inventory.size()) {
				return userChoice;
			} else {
				System.out.println("Unrecognized input. Please select one of the available items by number.");
			}
		}
	}

	// HERE BE DRAGONS

	/**
	 * The following nested classes are a (probably overengineered) way to resolve
	 * combat actions that the player might take. We keep these classes nested
	 * within the Player class so that they can access the Player's private
	 * variables. This is acceptable since these classes are a convenience feature
	 * used solely within the Player class, so the boundaries between these classes
	 * and the Player class can be fairly loose.
	 */

	/**
	 * Represents an action that the user can choose to take during combat.
	 */
	private enum CombatActionChoice {
		ATTACK, DEFEND, VIEW_INVENTORY, RUN_AWAY
	}

	/**
	 * Abstract class representing an action that the user can take in combat.
	 */
	private abstract class CombatAction {
		/**
		 * Construct a new {@link CombatAction}.
		 */
		public CombatAction() {
		}

		/**
		 * Resolve this action.
		 */
		public abstract void resolve();
	}

	/**
	 * Combat action class that is used when the player attacks their enemy.
	 */
	private final class AttackAction extends CombatAction {
		private Enemy enemy;

		/**
		 * Construct a new {@link AttackAction}.
		 *
		 * @param enemy The enemy that the player is currently in combat with.
		 */
		public AttackAction(Enemy enemy) {
			this.enemy = enemy;
		}

		@Override
		public void resolve() {
			System.out.println(String.format("You swing your sword at the %s!", enemy.name));
			enemy.takeDamage(attackPower);
		}
	}

	/**
	 * Combat action class that is used when the player defends against their enemy.
	 */
	private final class DefendAction extends CombatAction {
		/**
		 * Construct a new {@link DefendAction}.
		 */
		public DefendAction() {
		}

		@Override
		public void resolve() {
			System.out.println("You raise your shield...");
			isBlocking = true;
		}
	}

	/**
	 * Combat action class that is used when the player uses an item from their
	 * inventory.
	 */
	private final class UseItemAction extends CombatAction {
		private Enemy enemy;

		private int itemIndex;

		/**
		 * Construct a new {@link UseItemAction}.
		 *
		 * @param enemy     The enemy that the player is currently in combat with.
		 * @param itemIndex The index of the item in the player's inventory to use.
		 */
		public UseItemAction(Enemy enemy, int itemIndex) {
			this.enemy = enemy;
			this.itemIndex = itemIndex;
		}

		@Override
		public void resolve() {
			inventory.useItem(itemIndex, Player.this, enemy);
		}
	}

	/**
	 * Combat action class that is used when the player tries to run from combat.
	 */
	private final class RunAwayAction extends CombatAction {
		/**
		 * Construct a new {@link RunAwayAction}.
		 */
		public RunAwayAction() {
		}

		@Override
		public void resolve() {
			System.out.println("Heroes don't run from battle! (You lose your turn for being a coward...)");
		}
	}
}
