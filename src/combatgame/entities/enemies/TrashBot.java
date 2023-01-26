package combatgame.entities.enemies;

import combatgame.naming.Capitalization;
import combatgame.naming.GenericName;
import combatgame.naming.IndefiniteArticle;
import combatgame.naming.Name;
import combatgame.util.Random;

public class TrashBot extends SimpleEnemy {
	private static final Name DEFAULT_NAME = new GenericName("trash bot", IndefiniteArticle.A);

	private static final int STARTING_HEALTH = 5;

	private static final int ATTACK_POWER = 2;

	public TrashBot() {
		super(DEFAULT_NAME, STARTING_HEALTH, ATTACK_POWER);
	}

	@Override
	protected String getAttackMessage() {
		// "The trash bot"
		String name = NAME.getNameDefiniteArticle(Capitalization.FIRST_LETTER);

		int rand = Random.getInt(0, 4);
		if (rand >= 0 && rand <= 3) {
			return String.format("%s throws a steel bolt at you!", name);
		} else if (rand == 4) {
			return String.format("%s drives over your toes! Owch!", name);
		} else {
			throw new RuntimeException("Unexpected random value");
		}
	}

}
