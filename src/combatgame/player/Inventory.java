package combatgame.player;

import java.util.ArrayList;
import java.util.Arrays;

import combatgame.entities.enemies.Enemy;
import combatgame.items.Item;

/**
 * An {@link Inventory} is a collection of {@link Item}s that can be used by the
 * {@link Player}.
 */
public class Inventory {
	/**
	 * The actual list of items in the inventory.
	 */
	private ArrayList<Item> items;

	/**
	 * Construct an empty inventory.
	 */
	public Inventory() {
		items = new ArrayList<>();
	}

	/**
	 * Construct an inventory that contains the given items.
	 *
	 * @param startingItems The items that start in the inventory.
	 */
	public Inventory(Item[] startingItems) {
		items = new ArrayList<>(Arrays.asList(startingItems));
	}

	/**
	 * Get the number of items in this inventory.
	 *
	 * @return The number of items in the inventory.
	 */
	public int size() {
		return items.size();
	}

	/**
	 * Display an indexed list of the items contained by this inventory.
	 */
	public void showItems() {
		int index = 0;
		for (Item item : items) {
			System.out.printf("%d - %s (%s)\n", index, item.name, item.description);
			++index;
		}
	}

	/**
	 * Add an item to this inventory.
	 *
	 * @param itemToAdd The item to add.
	 */
	public void addItem(Item itemToAdd) {
		items.add(itemToAdd);
	}

	/**
	 * Use the item at the given index.
	 *
	 * @param player The current player.
	 * @param enemy  The enemy currently being fought.
	 */
	public void useItem(int index, Player player, Enemy enemy) throws IndexOutOfBoundsException {
		if (index < 0 || index >= items.size()) {
			throw new IndexOutOfBoundsException(index);
		} else {
			items.get(index).use(player, enemy);
			items.remove(index);
		}
	}
}
