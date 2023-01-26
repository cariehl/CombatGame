package combatgame.entities;

import combatgame.naming.Name;

/**
 * The {@link Entity} class represents an entity within the combat game.
 * Entities have names and some amount of health, and can be dealt damage by
 * game actions.
 */
public abstract class Entity {
	/**
	 * The name of this entity.
	 */
	public final Name NAME;

	/**
	 * The amount of health this entity has remaining.
	 */
	private int health;

	/**
	 * @param name           The name of this entity.
	 * @param startingHealth The amount of health this entity starts with.
	 */
	protected Entity(Name name, int startingHealth) {
		this.NAME = name;
		this.health = startingHealth;
	}

	/**
	 * Cause this entity to take some amount of damage.
	 *
	 * @param damageAmount The amount of damage to deal to this entity.
	 */
	public void takeDamage(int damageAmount) {
		health -= damageAmount;
	}

	/**
	 * Check if this entity is dead.
	 *
	 * @return True if this entity is dead, false otherwise.
	 */
	public boolean isDead() {
		return health <= 0;
	}

	/**
	 * Get the amount of health this entity has remaining.
	 *
	 * @return The amount of health this entity has remaining.
	 */
	public int getHealth() {
		return health;
	}
}
