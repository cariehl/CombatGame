package combatgame.util;

import java.util.random.RandomGenerator;

/**
 * Utility class to provide a consistent source of randomness across the game.
 * Can be seeded with a specific seed to guarantee future reproducability.
 */
public class Random {
	/**
	 * The single source of randomness.
	 */
	private static java.util.Random random = java.util.Random.from(RandomGenerator.getDefault());

	/**
	 * Set the random generator used to generate random values.
	 *
	 * @param generator Generator to use.
	 */
	public static void setGenerator(RandomGenerator generator) {
		random = java.util.Random.from(generator);
	}

	/**
	 * Get a random integer in the range [lowerBound, upperBound]
	 *
	 * @param lowerBound The lower bound (inclusive) of the range of available
	 *                   integers.
	 * @param upperBound The upper bound (inclusive) of the range of available
	 *                   integers.
	 * @return A random integer in the range [lowerBound, upperBound]
	 */
	public static int getInt(int lowerBound, int upperBound) {
		return random.nextInt(lowerBound, upperBound + 1);
	}
}
