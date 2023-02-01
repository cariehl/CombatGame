package combatgame.items;

import combatgame.entities.enemies.Enemy;
import combatgame.player.Player;

public class HealingPotion extends Item {
	private static final String DEFAULT_NAME = "healing potion";

	private static final String DEFAULT_DESCRIPTION = "A potion that restores some hitpoints.";

	private static final int DEFAULT_HEAL_AMOUNT = 5;

	public HealingPotion() {
		super(DEFAULT_NAME, DEFAULT_DESCRIPTION);
	}

	@Override
	public void use(Player player, Enemy enemy) {
		int healAmount = DEFAULT_HEAL_AMOUNT;
		System.out.printf("You chug a %s.\n", name);
		player.restoreHealth(healAmount);
	}

}
