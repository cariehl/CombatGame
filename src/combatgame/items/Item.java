package combatgame.items;

import combatgame.entities.enemies.Enemy;
import combatgame.player.Player;

/**
 * An {@link Item} is something that the {@link Player} can use in combat.
 */
public abstract class Item {
	/**
	 * The name of this item.
	 */
	public final String name;

	/**
	 * A short description of this item.
	 */
	public final String description;

	/**
	 * @param name        The name of this item.
	 * @param description A short description of this item.
	 */
	protected Item(String name, String description) {
		this.name = name;
		this.description = description;
	}

	/**
	 * Use this item. Effects may vary.
	 *
	 * @param player The current player.
	 * @param enemy  The enemy currently being fought.
	 */
	public abstract void use(Player player, Enemy enemy);
}
