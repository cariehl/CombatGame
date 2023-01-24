package combatgame.entities;

/**
 * The {@link Entity} class represents an entity within the combat game.
 * Entities have names and some amount of health, and can be dealt damage by
 * game actions.
 */
public abstract class Entity
{
    /**
     * The name of this Entity.
     */
    public final String NAME;

    /**
     * The amount of health this Entity has remaining.
     */
    private int health;

    /**
     * Construct an Entity with the given name and amount of starting health.
     * @param name The name of this entity.
     * @param startingHealth The amount of health this Entity starts with.
     */
    protected Entity(String name, int startingHealth)
    {
        this.NAME = name;
        this.health = startingHealth;
    }

    /**
     * Cause this Entity to take some amount of damage.
     * @param damageAmount The amount of damage to deal to this Entity.
     */
    public void takeDamage(int damageAmount)
    {
        health -= damageAmount;
    }

    /**
     * Check if this Entity is dead.
     * @return True if this Entity is dead, false otherwise.
     */
    public boolean isDead() { return health <= 0; }

    /**
     * Get the amount of health this Entity has remaining.
     * @return The amount of health this Entity has remaining.
     */
    public int getHealth() { return health; }
}
