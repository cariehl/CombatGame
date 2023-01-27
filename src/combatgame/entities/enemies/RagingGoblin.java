package combatgame.entities.enemies;

import combatgame.player.Player;

/**
 * A {@link RagingGoblin} is a bit more complex than the regular {@link Goblin}.
 * If it can attack, it will fly into a frenzy and deal extra damage.
 * Afterwards, it becomes tired and has to take a turn to rest.
 */
public class RagingGoblin extends Enemy {
	private static final String DEFAULT_NAME = "raging goblin";

	private static final int DEFAULT_STARTING_HEALTH = 6;

	private static final int DEFAULT_ATTACK_POWER = 2;

	/**
	 * The base amount of damage this raging goblin deals per attack.
	 */
	private int attackPower;

	/**
	 * Is this raging goblin tired or not?
	 */
	private boolean isTired;

	/**
	 * Create a new RagingGoblin.
	 */
	public RagingGoblin() {
		super(DEFAULT_NAME, DEFAULT_STARTING_HEALTH);
		this.attackPower = DEFAULT_ATTACK_POWER;
		this.isTired = false;
	}

	@Override
	public void performCombatAction(Player player) {
		if (isTired) {
			System.out.println(String.format("The %s is too exhausted to attack.", name));
		} else {
			int damageAmount = attackPower * 2;
			System.out.println(String.format("The %s wildly attacks you with its claws!", name));
			player.takeDamage(damageAmount);
			isTired = true;
			System.out.println(String.format("The %s looks exhausted...", name));
		}
	}
}
