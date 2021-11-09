package dev.quantumfusion.flyio.impl;

import dev.quantumfusion.flyio.IOInterface;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

/**
 * <h2>Useful for debug and/or when UnsafeIO is unavailable.</h2>
 */
@SuppressWarnings({"FinalMethodInFinalClass", "FinalStaticMethod", "unused"})
public final class ByteBufferIO implements IOInterface {
	private final ByteBuffer byteBuffer;

	private ByteBufferIO(final ByteBuffer buffer) {
		this.byteBuffer = buffer;
	}

	public static final ByteBufferIO wrap(final ByteBuffer byteBuffer) {
		return new ByteBufferIO(byteBuffer.order(ByteOrder.LITTLE_ENDIAN));
	}

	public static final ByteBufferIO create(final int size) {
		return wrap(ByteBuffer.allocate(size));
	}

	public static final ByteBufferIO createDirect(final int size) {
		return wrap(ByteBuffer.allocateDirect(size));
	}

	// ======================================= FUNC ======================================= //
	@Override
	public final void rewind() {
		byteBuffer.rewind();
	}


	@Override
	public final int getPos() {
		return byteBuffer.position();
	}

	@Override
	public void setPos(int pos) {
		byteBuffer.position(pos);
	}

	@Override
	public final void close() {
		byteBuffer.clear();
	}


	// ======================================== GET ======================================== //
	@Override
	public final boolean getBoolean() {
		return byteBuffer.get() == 1;
	}


	@Override
	public final byte getByte() {
		return byteBuffer.get();
	}


	@Override
	public final char getChar() {
		return byteBuffer.getChar();
	}


	@Override
	public final short getShort() {
		return byteBuffer.getShort();
	}


	@Override
	public final int getInt() {
		return byteBuffer.getInt();
	}


	@Override
	public final long getLong() {
		return byteBuffer.getLong();
	}


	@Override
	public final float getFloat() {
		return byteBuffer.getFloat();
	}


	@Override
	public final double getDouble() {
		return byteBuffer.getDouble();
	}


	@Override
	public final String getString() {
		final int length = getInt();
		final byte[] byteArray = getByteArray(length);
		return new String(byteArray, 0, length, StandardCharsets.UTF_8);
	}


	// ======================================== PUT ======================================== //
	@Override
	public final void putBoolean(final boolean value) {
		byteBuffer.put((byte) (value ? 1 : 0));
	}


	@Override
	public final void putByte(final byte value) {
		byteBuffer.put(value);
	}


	@Override
	public final void putChar(final char value) {
		byteBuffer.putChar(value);
	}


	@Override
	public final void putShort(final short value) {
		byteBuffer.putShort(value);
	}

	@Override
	public final void putInt(final int value) {
		byteBuffer.putInt(value);
	}


	@Override
	public final void putLong(final long value) {
		byteBuffer.putLong(value);
	}


	@Override
	public final void putFloat(final float value) {
		byteBuffer.putFloat(value);
	}


	@Override
	public final void putDouble(final double value) {
		byteBuffer.putDouble(value);
	}


	@Override
	public final void putString(final String value) {
		final byte[] array = value.getBytes(StandardCharsets.UTF_8);
		final int length = array.length;
		putInt(length);
		putByteArray(array, length);
	}


	// ====================================== GET_ARR ======================================== //
	@Override
	public final boolean[] getBooleanArray(final int length) {
		final boolean[] out = new boolean[length];
		for (int i = 0; i < length; i++)
			out[i] = byteBuffer.get() == 1;
		return out;
	}


	@Override
	public final byte[] getByteArray(final int length) {
		final byte[] out = new byte[length];
		byteBuffer.get(out, 0, length);
		return out;
	}


	@Override
	public final char[] getCharArray(final int length) {
		final char[] out = new char[length];
		for (int i = 0; i < length; i++)
			out[i] = byteBuffer.getChar();
		return out;
	}

	@Override
	public final short[] getShortArray(final int length) {
		final short[] out = new short[length];
		for (int i = 0; i < length; i++)
			out[i] = byteBuffer.getShort();
		return out;
	}

	@Override
	public final int[] getIntArray(final int length) {
		final int[] out = new int[length];
		for (int i = 0; i < length; i++)
			out[i] = byteBuffer.getInt();
		return out;
	}

	@Override
	public final long[] getLongArray(final int length) {
		final long[] out = new long[length];
		for (int i = 0; i < length; i++)
			out[i] = byteBuffer.getLong();
		return out;
	}

	@Override
	public final float[] getFloatArray(final int length) {
		final float[] out = new float[length];
		for (int i = 0; i < length; i++)
			out[i] = byteBuffer.getFloat();
		return out;
	}

	@Override
	public final double[] getDoubleArray(final int length) {
		final double[] out = new double[length];
		for (int i = 0; i < length; i++)
			out[i] = byteBuffer.getDouble();
		return out;
	}

	@Override
	public final String[] getStringArray(final int length) {
		final String[] out = new String[length];
		for (int i = 0; i < length; i++)
			out[i] = getString();
		return out;
	}


	// ====================================== PUT_ARR ======================================== //
	@Override
	public final void putBooleanArray(final boolean[] value, final int length) {
		for (int i = 0; i < length; i++) byteBuffer.put((byte) (value[i] ? 1 : 0));
	}


	@Override
	public final void putByteArray(final byte[] value, final int length) {
		for (int i = 0; i < length; i++) byteBuffer.put(value[i]);
	}


	@Override
	public final void putCharArray(final char[] value, final int length) {
		for (int i = 0; i < length; i++) byteBuffer.putChar(value[i]);
	}


	@Override
	public final void putShortArray(final short[] value, final int length) {
		for (int i = 0; i < length; i++) byteBuffer.putShort(value[i]);
	}


	@Override
	public final void putIntArray(final int[] value, final int length) {
		for (int i = 0; i < length; i++) byteBuffer.putInt(value[i]);
	}


	@Override
	public final void putLongArray(final long[] value, final int length) {
		for (int i = 0; i < length; i++) byteBuffer.putLong(value[i]);
	}


	@Override
	public final void putFloatArray(final float[] value, final int length) {
		for (int i = 0; i < length; i++) byteBuffer.putFloat(value[i]);
	}


	@Override
	public final void putDoubleArray(final double[] value, final int length) {
		for (int i = 0; i < length; i++) byteBuffer.putDouble(value[i]);
	}


	@Override
	public final void putStringArray(final String[] value, final int length) {
		for (int i = 0; i < length; i++) putString(value[i]);
	}
}
