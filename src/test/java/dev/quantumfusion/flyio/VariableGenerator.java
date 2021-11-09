package dev.quantumfusion.flyio;

public class VariableGenerator {
	private static int CURRENT_VAR = 0;
	public static final boolean[] BOOLEANS = {true, false, true, true, false, false};
	public static final byte[] BYTES = {0, Byte.MIN_VALUE, Byte.MAX_VALUE};
	public static final short[] SHORTS = {0, Short.MIN_VALUE, Short.MAX_VALUE};
	public static final char[] CHARS = {0, Character.MIN_VALUE, Character.MAX_VALUE, '\uD83E', '\uDD80'};
	public static final int[] INTS = {0, Integer.MIN_VALUE, Integer.MAX_VALUE};
	public static final long[] LONGS = {0, Long.MIN_VALUE, Long.MAX_VALUE};
	public static final float[] FLOATS = {0, Float.MIN_VALUE, Float.MAX_VALUE, Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NaN, Float.MIN_NORMAL};
	public static final double[] DOUBLES = {0, Double.MIN_VALUE, Double.MAX_VALUE, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NaN, Double.MIN_NORMAL};
	public static final String[] STRING = {"", "glosco", "ඞ", "Ayo, Crashnite?"};

	public static boolean nextBoolean() {
		return BOOLEANS[CURRENT_VAR++ % BOOLEANS.length];
	}

	public static byte nextByte() {
		return BYTES[CURRENT_VAR++ % BYTES.length];
	}

	public static short nextShort() {
		return SHORTS[CURRENT_VAR++ % SHORTS.length];
	}

	public static char nextChar() {
		return CHARS[CURRENT_VAR++ % CHARS.length];
	}

	public static int nextInt() {
		return INTS[CURRENT_VAR++ % INTS.length];
	}

	public static long nextLong() {
		return LONGS[CURRENT_VAR++ % LONGS.length];
	}

	public static float nextFloat() {
		return FLOATS[CURRENT_VAR++ % FLOATS.length];
	}

	public static double nextDouble() {
		return DOUBLES[CURRENT_VAR++ % DOUBLES.length];
	}

	public static String nextString() {
		return STRING[CURRENT_VAR++ % STRING.length];
	}

	public static void reset() {
		CURRENT_VAR = 0;
	}

}
