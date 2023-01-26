package combatgame.entities.enemies;

import combatgame.entities.player.Player;
import combatgame.naming.Name;

/**
 * A {@link SimpleEnemy} is an {@link Enemy} that attacks the player for the
 * same amount of damage each turn, and does nothing else.
 */
public abstract class SimpleEnemy extends Enemy {
	/**
	 * How much damage this enemy deals per attack.
	 */
	private int attackPower;

	/**
	 * @param name           The name of this enemy.
	 * @param startingHealth The amount of health this enemy starts with.
	 * @param attackPower    The amount of damage this enemy deals per attack.
	 */
	protected SimpleEnemy(Name name, int startingHealth, int attackPower) {
		super(name, startingHealth);
		this.attackPower = attackPower;
	}

	@Override
	public void performCombatAction(Player player) {
		String attackMessage = getAttackMessage();
		System.out.println(attackMessage);
		player.takeDamage(attackPower);
	}

	/**
	 * @return The string to be displayed whenever this enemy attacks the player.
	 */
	protected abstract String getAttackMessage();
}
