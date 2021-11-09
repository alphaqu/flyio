package dev.quantumfusion.flyio.impl;

import dev.quantumfusion.flyio.IOInterfrace;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@SuppressWarnings({"FinalMethodInFinalClass", "FinalStaticMethod"})
public final class OutputStreamIO implements IOInterfrace {
	private final OutputStream outputStream;
	private int pos = 0;

	private OutputStreamIO(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public static final OutputStreamIO wrap(OutputStream outputStream) {
		return new OutputStreamIO(outputStream);
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
			final int length = pos - this.pos;
			for (int i = 0; i < length; i++) {
				outputStream.write(0);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public final void close() {
		try {
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean getBoolean() {
		throw new UnsupportedOperationException("Tried to read on an " + this.getClass().getSimpleName());
	}

	@Override
	public byte getByte() {
		throw new UnsupportedOperationException("Tried to read on an " + this.getClass().getSimpleName());
	}

	@Override
	public char getChar() {
		throw new UnsupportedOperationException("Tried to read on an " + this.getClass().getSimpleName());
	}

	@Override
	public short getShort() {
		throw new UnsupportedOperationException("Tried to read on an " + this.getClass().getSimpleName());
	}

	@Override
	public int getInt() {
		throw new UnsupportedOperationException("Tried to read on an " + this.getClass().getSimpleName());
	}

	@Override
	public long getLong() {
		throw new UnsupportedOperationException("Tried to read on an " + this.getClass().getSimpleName());
	}

	@Override
	public float getFloat() {
		throw new UnsupportedOperationException("Tried to read on an " + this.getClass().getSimpleName());
	}

	@Override
	public double getDouble() {
		throw new UnsupportedOperationException("Tried to read on an " + this.getClass().getSimpleName());
	}

	@Override
	public String getString() {
		throw new UnsupportedOperationException("Tried to read on an " + this.getClass().getSimpleName());
	}

	@Override
	public final void putBoolean(boolean value) {
		try {
			outputStream.write(value ? 1 : 0);
			pos++;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public final void putByte(byte value) {
		try {
			outputStream.write(value);
			pos++;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public final void putChar(final char value) {
		putByte((byte) value);
		putByte((byte) (value >>> 8));
	}


	@Override
	public final void putShort(final short value) {
		putByte((byte) value);
		putByte((byte) (value >>> 8));
	}


	@Override
	public final void putInt(final int value) {
		putByte((byte) value);
		putByte((byte) (value >>> 8));
		putByte((byte) (value >>> 16));
		putByte((byte) (value >>> 24));
	}


	@Override
	public final void putLong(final long value) {
		final int low = (int) value;
		final int high = (int) (value >>> 32);
		putByte((byte) low);
		putByte((byte) (low >>> 8));
		putByte((byte) (low >>> 16));
		putByte((byte) (low >>> 24));
		putByte((byte) high);
		putByte((byte) (high >>> 8));
		putByte((byte) (high >>> 16));
		putByte((byte) (high >>> 24));
	}


	@Override
	public final void putFloat(final float value) {
		putInt(Float.floatToIntBits(value));
	}


	@Override
	public final void putDouble(final double value) {
		putLong(Double.doubleToLongBits(value));
	}


	@Override
	public final void putString(final String value) {
		final byte[] array = value.getBytes(StandardCharsets.UTF_8);
		final int length = array.length;
		putInt(length);
		putByteArray(array, length);
	}

	@Override
	public boolean[] getBooleanArray(int length) {
		throw new UnsupportedOperationException("Tried to read on an " + this.getClass().getSimpleName());
	}

	@Override
	public byte[] getByteArray(int length) {
		throw new UnsupportedOperationException("Tried to read on an " + this.getClass().getSimpleName());
	}

	@Override
	public char[] getCharArray(int length) {
		throw new UnsupportedOperationException("Tried to read on an " + this.getClass().getSimpleName());
	}

	@Override
	public short[] getShortArray(int length) {
		throw new UnsupportedOperationException("Tried to read on an " + this.getClass().getSimpleName());
	}

	@Override
	public int[] getIntArray(int length) {
		throw new UnsupportedOperationException("Tried to read on an " + this.getClass().getSimpleName());
	}

	@Override
	public long[] getLongArray(int length) {
		throw new UnsupportedOperationException("Tried to read on an " + this.getClass().getSimpleName());
	}

	@Override
	public float[] getFloatArray(int length) {
		throw new UnsupportedOperationException("Tried to read on an " + this.getClass().getSimpleName());
	}

	@Override
	public double[] getDoubleArray(int length) {
		throw new UnsupportedOperationException("Tried to read on an " + this.getClass().getSimpleName());
	}

	@Override
	public String[] getStringArray(int length) {
		throw new UnsupportedOperationException("Tried to read on an " + this.getClass().getSimpleName());
	}

	// ====================================== PUT_ARR ======================================== //
	@Override
	public final void putBooleanArray(final boolean[] value, final int length) {
		for (int i = 0; i < length; i++) putByte((byte) (value[i] ? 1 : 0));
	}


	@Override
	public final void putByteArray(final byte[] value, final int length) {
		for (int i = 0; i < length; i++) putByte(value[i]);
	}


	@Override
	public final void putCharArray(final char[] value, final int length) {
		for (int i = 0; i < length; i++) putChar(value[i]);
	}


	@Override
	public final void putShortArray(final short[] value, final int length) {
		for (int i = 0; i < length; i++) putShort(value[i]);
	}


	@Override
	public final void putIntArray(final int[] value, final int length) {
		for (int i = 0; i < length; i++) putInt(value[i]);
	}


	@Override
	public final void putLongArray(final long[] value, final int length) {
		for (int i = 0; i < length; i++) putLong(value[i]);
	}


	@Override
	public final void putFloatArray(final float[] value, final int length) {
		for (int i = 0; i < length; i++) putFloat(value[i]);
	}


	@Override
	public final void putDoubleArray(final double[] value, final int length) {
		for (int i = 0; i < length; i++) putDouble(value[i]);
	}


	@Override
	public final void putStringArray(final String[] value, final int length) {
		for (int i = 0; i < length; i++) putString(value[i]);
	}
}
