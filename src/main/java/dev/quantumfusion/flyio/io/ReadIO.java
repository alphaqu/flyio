package dev.quantumfusion.flyio.io;

/**
 * An IO implementation with only read access
 */
public interface ReadIO extends RawIO {

	boolean getBoolean();

	byte getByte();

	char getChar();

	short getShort();

	int getInt();

	long getLong();

	float getFloat();

	double getDouble();

	String getString();

	boolean[] getBooleanArray(int length);

	byte[] getByteArray(int length);

	char[] getCharArray(int length);

	short[] getShortArray(int length);

	int[] getIntArray(int length);

	long[] getLongArray(int length);

	float[] getFloatArray(int length);

	double[] getDoubleArray(int length);

	String[] getStringArray(int length);


}
