package dev.quantumfusion.flyio.impl;

import dev.quantumfusion.flyio.IOInterfrace;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@SuppressWarnings({"FinalMethodInFinalClass", "FinalStaticMethod"})
public final class InputStreamIO implements IOInterfrace {
	private final InputStream inputStream;
	private int pos = 0;

	private InputStreamIO(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public static final InputStreamIO wrap(InputStream inputStream) {
		return new InputStreamIO(inputStream);
	}


	@Override
	public final void rewind() {
		throw new UnsupportedOperationException("Cannot rewind an " + this.getClass().getSimpleName());
	}

	@Override
	public final int getPos() {
		return pos;
	}

	@Override
	public final void setPos(int pos) {
		if (this.pos > pos)
			throw new UnsupportedOperationException("Cannot pos back in an " + this.getClass().getSimpleName());

		try {
			inputStream.skipNBytes((pos - this.pos));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public final void close() {
		try {
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public final boolean getBoolean() {
		try {
			pos++;
			return inputStream.read() != 0;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public final byte getByte() {
		try {
			pos++;
			return (byte) inputStream.read();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public final char getChar() {
		return (char) (getByte() & 0xFF | (getByte() & 0xFF) << 8);
	}


	@Override
	public final short getShort() {
		return (short) (getByte() & 0xFF | (getByte() & 0xFF) << 8);
	}


	@Override
	public final int getInt() {
		return getByte() & 0xFF | (getByte() & 0xFF) << 8 | (getByte() & 0xFF) << 16 | (getByte() & 0xFF) << 24;
	}


	@Override
	public final long getLong() {
		return (getByte() & 0xFF | (getByte() & 0xFF) << 8) | (getByte() & 0xFF) << 16 | (long) (getByte() & 0xFF) << 24 | (long) (getByte() & 0xFF) << 32 | (long) (getByte() & 0xFF) << 40 | (long) (getByte() & 0xFF) << 48 | (long) (getByte() & 0xFF) << 56;
	}


	@Override
	public final float getFloat() {
		return Float.intBitsToFloat(getInt());
	}


	@Override
	public final double getDouble() {
		return Double.longBitsToDouble(getLong());
	}


	@Override
	public final String getString() {
		final int length = getInt();
		final byte[] byteArray = getByteArray(length);
		return new String(byteArray, 0, length, StandardCharsets.UTF_8);
	}

	@Override
	public final void putBoolean(final boolean value) {
		putByte((byte) (value ? 1 : 0));
	}


	@Override
	public final void putByte(final byte value) {
		throw new UnsupportedOperationException("Tried to write on an " + this.getClass().getSimpleName());
	}


	@Override
	public final void putChar(final char value) {
		throw new UnsupportedOperationException("Tried to write on an " + this.getClass().getSimpleName());
	}


	@Override
	public final void putShort(final short value) {
		throw new UnsupportedOperationException("Tried to write on an " + this.getClass().getSimpleName());
	}


	@Override
	public final void putInt(final int value) {
		throw new UnsupportedOperationException("Tried to write on an " + this.getClass().getSimpleName());
	}


	@Override
	public final void putLong(final long value) {
		throw new UnsupportedOperationException("Tried to write on an " + this.getClass().getSimpleName());
	}


	@Override
	public final void putFloat(final float value) {
		throw new UnsupportedOperationException("Tried to write on an " + this.getClass().getSimpleName());
	}


	@Override
	public final void putDouble(final double value) {
		throw new UnsupportedOperationException("Tried to write on an " + this.getClass().getSimpleName());
	}


	@Override
	public final void putString(final String value) {
		throw new UnsupportedOperationException("Tried to write on an " + this.getClass().getSimpleName());
	}

	@Override
	public final boolean[] getBooleanArray(final int length) {
		final boolean[] out = new boolean[length];
		for (int i = 0; i < length; i++)
			out[i] = getByte() == 1;
		return out;
	}


	@Override
	public final byte[] getByteArray(final int length) {
		final byte[] out = new byte[length];
		for (int i = 0; i < length; i++)
			out[i] = getByte();
		return out;
	}


	@Override
	public final char[] getCharArray(final int length) {
		final char[] out = new char[length];
		for (int i = 0; i < length; i++)
			out[i] = getChar();
		return out;
	}


	@Override
	public final short[] getShortArray(final int length) {
		final short[] out = new short[length];
		for (int i = 0; i < length; i++)
			out[i] = getShort();
		return out;
	}


	@Override
	public final int[] getIntArray(final int length) {
		final int[] out = new int[length];
		for (int i = 0; i < length; i++)
			out[i] = getInt();
		return out;
	}


	@Override
	public final long[] getLongArray(final int length) {
		final long[] out = new long[length];
		for (int i = 0; i < length; i++)
			out[i] = getLong();
		return out;
	}


	@Override
	public final float[] getFloatArray(final int length) {
		final float[] out = new float[length];
		for (int i = 0; i < length; i++)
			out[i] = getFloat();
		return out;
	}


	@Override
	public final double[] getDoubleArray(final int length) {
		final double[] out = new double[length];
		for (int i = 0; i < length; i++)
			out[i] = getDouble();
		return out;
	}


	@Override
	public final String[] getStringArray(final int length) {
		final String[] out = new String[length];
		for (int i = 0; i < length; i++)
			out[i] = getString();
		return out;
	}

	@Override
	public void putBooleanArray(boolean[] value, int length) {
		throw new UnsupportedOperationException("Tried to write on an " + this.getClass().getSimpleName());
	}

	@Override
	public void putByteArray(byte[] value, int length) {
		throw new UnsupportedOperationException("Tried to write on an " + this.getClass().getSimpleName());
	}

	@Override
	public void putCharArray(char[] value, int length) {
		throw new UnsupportedOperationException("Tried to write on an " + this.getClass().getSimpleName());
	}

	@Override
	public void putShortArray(short[] value, int length) {
		throw new UnsupportedOperationException("Tried to write on an " + this.getClass().getSimpleName());
	}

	@Override
	public void putIntArray(int[] value, int length) {
		throw new UnsupportedOperationException("Tried to write on an " + this.getClass().getSimpleName());
	}

	@Override
	public void putLongArray(long[] value, int length) {
		throw new UnsupportedOperationException("Tried to write on an " + this.getClass().getSimpleName());
	}

	@Override
	public void putFloatArray(float[] value, int length) {
		throw new UnsupportedOperationException("Tried to write on an " + this.getClass().getSimpleName());
	}

	@Override
	public void putDoubleArray(double[] value, int length) {
		throw new UnsupportedOperationException("Tried to write on an " + this.getClass().getSimpleName());
	}

	@Override
	public void putStringArray(String[] value, int length) {
		throw new UnsupportedOperationException("Tried to write on an " + this.getClass().getSimpleName());
	}
}
