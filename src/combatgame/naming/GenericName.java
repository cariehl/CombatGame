package combatgame.naming;

/**
 * Represents the name of a generic entity, i.e. an entity without a more
 * specific title or name, e.g. "mouse king", "angry bird".
 *
 * Generic names are referred to by definite and indefinite articles, and the
 * indefinite article must be specified by the user.
 */
public class GenericName extends Name {
	/**
	 * @param name              The name being referred to.
	 * @param indefiniteArticle The indefinite article used to refer to this name.
	 */
	public GenericName(String name, IndefiniteArticle indefiniteArticle) {
		super(name, indefiniteArticle, DefiniteArticle.THE);
	}

}
