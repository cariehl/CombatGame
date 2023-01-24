package combatgame.entities.enemies;

import combatgame.entities.player.Player;

/**
 * A {@link Goblin} is a basic enemy. It attacks the player every turn until
 * either the player or the goblin dies.
 */
public class Goblin extends Enemy
{
    private static final String DEFAULT_NAME = "goblin";

    private static final int DEFAULT_STARTING_HEALTH = 4;

    private static final int DEFAULT_ATTACK_POWER = 2;

    /**
     * The amount of damage this goblin deals per attack.
     */
    private int attackPower;

    public Goblin()
    {
        super(DEFAULT_NAME, DEFAULT_STARTING_HEALTH);
        this.attackPower = DEFAULT_ATTACK_POWER;
    }

    @Override
    public void performCombatAction(Player player)
    {
        System.out.println(String.format("The %s swings its mace at you!", NAME));
        player.takeDamage(attackPower);
    }
}
