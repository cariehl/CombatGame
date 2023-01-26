package combatgame.naming;

/**
 * Represents the name of a specific entity, i.e. an entity that is referred to
 * by a specific title or name, e.g. "Ash Ketchum", "The Duke of Wellington"
 *
 * Specific names are not referred to generically by indefinite or definite
 * articles. Any definite articles are included in the name itself.
 */
public class SpecificName extends Name {
	/**
	 * @param name The name being referred to.
	 */
	public SpecificName(String name) {
		super(name, IndefiniteArticle.NONE, DefiniteArticle.NONE);
	}

}
