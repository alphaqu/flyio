package dev.quantumfusion.flyio.io;

/**
 * An IO implementation with only write access
 */
public interface WriteIO extends RawIO {
	void putBoolean(boolean value);

	void putByte(byte value);

	void putChar(char value);

	void putShort(short value);

	void putInt(int value);

	void putLong(long value);

	void putFloat(float value);

	void putDouble(double value);

	void putString(String value);

	void putBooleanArray(boolean[] value, int length);

	void putByteArray(byte[] value, int length);

	void putCharArray(char[] value, int length);

	void putShortArray(short[] value, int length);

	void putIntArray(int[] value, int length);

	void putLongArray(long[] value, int length);

	void putFloatArray(float[] value, int length);

	void putDoubleArray(double[] value, int length);

	void putStringArray(String[] value, int length);
}
