package combatgame.entities.enemies;

import combatgame.entities.Entity;
import combatgame.entities.player.Player;
import combatgame.naming.Capitalization;
import combatgame.naming.Name;

/**
 * The {@link Enemy} class represents an opponent that can be fought by the
 * player in the combat game. Each subclass determines its own action to take
 * when it is its turn.
 */
public abstract class Enemy extends Entity {
	/**
	 * @param name           The name of this Enemy.
	 * @param startingHealth The amount of health this Enemy starts with.
	 */
	protected Enemy(Name name, int startingHealth) {
		super(name, startingHealth);
	}

	/**
	 * Tell this Enemy to perform a combat action against the given Player. This
	 * could be the same action every time, or it could change depending on the
	 * circumstance.
	 *
	 * @param player The Player that this Enemy is in combat with.
	 */
	public abstract void performCombatAction(Player player);

	/**
	 * Overridden method that prints a description of the damage being taken.
	 */
	@Override
	public void takeDamage(int damageAmount) {
		System.out.printf("%s takes %d damage.\n", NAME.getNameDefiniteArticle(Capitalization.FIRST_LETTER),
				damageAmount);
		super.takeDamage(damageAmount);
	}
}
