package combatgame.naming;

/**
 * Represents the name of an entity. This class provides methods to refer to the
 * named entity in a variety of ways.
 */
public abstract class Name {
	/**
	 * The actual name being referred to.
	 */
	private final String name;

	/**
	 * The indefinite article used to refer to this name, if any.
	 */
	private final IndefiniteArticle indefiniteArticle;

	/**
	 * The definite article used to refer to this name, if any.
	 */
	private final DefiniteArticle definiteArticle;

	/**
	 * @param name              The actual name being referred to.
	 * @param indefiniteArticle The indefinite article used to refer to this name.
	 * @param definiteArticle   The definite article used to refer to this name.
	 */
	protected Name(String name, IndefiniteArticle indefiniteArticle, DefiniteArticle definiteArticle) {
		this.name = name;
		this.indefiniteArticle = indefiniteArticle;
		this.definiteArticle = definiteArticle;
	}

	/**
	 * @param capitalization How the resulting string should be capitalized.
	 * @return A string representing this name with its indefinite article, e.g. "a
	 *         mouse", "an apple", "Tom".
	 */
	public String getNameIndefiniteArticle(Capitalization capitalization) {
		String result;

		if (indefiniteArticle == IndefiniteArticle.NONE) {
			result = name;
		} else {
			result = getIndefiniteArticleString() + " " + name;
		}

		return performCapitalization(result, capitalization);
	}

	/**
	 * @param capitalization How the resulting string should be capitalized.
	 * @return A string representing this name with its definite article, e.g. "the
	 *         mouse", "the apple", "Tom".
	 */
	public String getNameDefiniteArticle(Capitalization capitalization) {
		String result;

		if (definiteArticle == DefiniteArticle.NONE) {
			result = name;
		} else {
			result = getDefiniteArticleString() + " " + name;
		}

		return performCapitalization(result, capitalization);
	}

	/**
	 * @return String form of the indefinite article used to refer to this name.
	 */
	private String getIndefiniteArticleString() {
		switch (indefiniteArticle) {
		case NONE:
			return "";
		case A:
			return "a";
		case AN:
			return "an";
		default:
			throw new EnumConstantNotPresentException(IndefiniteArticle.class, indefiniteArticle.toString());
		}
	}

	/**
	 * @return String form of the definite article used to refer to this name.
	 */
	private String getDefiniteArticleString() {
		switch (definiteArticle) {
		case NONE:
			return "";
		case THE:
			return "the";
		default:
			throw new EnumConstantNotPresentException(DefiniteArticle.class, definiteArticle.toString());
		}
	}

	/**
	 * Get a version of {@code strToCapitalize} that is capitalized according to
	 * {@code capitalization}.
	 *
	 * @param strToCapitalize The string to be capitalized.
	 * @param capitalization  The method of capitalization to use.
	 * @return The capitalized form of {@code strToCapitalize}.
	 */
	private String performCapitalization(String strToCapitalize, Capitalization capitalization) {
		switch (capitalization) {
		case NONE:
			return strToCapitalize;
		case FIRST_LETTER:
			return strToCapitalize.substring(0, 1).toUpperCase() + strToCapitalize.substring(1);
		case WHOLE_STRING:
			return strToCapitalize.toUpperCase();
		default:
			throw new EnumConstantNotPresentException(Capitalization.class, capitalization.toString());
		}
	}
}
