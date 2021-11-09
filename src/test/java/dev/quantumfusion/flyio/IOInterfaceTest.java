package dev.quantumfusion.flyio;

import org.junit.jupiter.api.Assertions;

public class IOInterfaceTest {
	private final IOInterfrace io;

	public IOInterfaceTest(IOInterfrace io) {
		this.io = io;
	}


	public void putValues(int depth) {
		for (int i = 0; i < depth; i++) {
			io.putBoolean(VariableGenerator.nextBoolean());
			io.putByte(VariableGenerator.nextByte());
			io.putChar(VariableGenerator.nextChar());
			io.putShort(VariableGenerator.nextShort());
			io.putInt(VariableGenerator.nextInt());
			io.putLong(VariableGenerator.nextLong());
			io.putFloat(VariableGenerator.nextFloat());
			io.putDouble(VariableGenerator.nextDouble());
			io.putString(VariableGenerator.nextString());
			io.putBooleanArray(VariableGenerator.BOOLEANS, VariableGenerator.BOOLEANS.length);
			io.putByteArray(VariableGenerator.BYTES, VariableGenerator.BYTES.length);
			io.putCharArray(VariableGenerator.CHARS, VariableGenerator.CHARS.length);
			io.putShortArray(VariableGenerator.SHORTS, VariableGenerator.SHORTS.length);
			io.putIntArray(VariableGenerator.INTS, VariableGenerator.INTS.length);
			io.putLongArray(VariableGenerator.LONGS, VariableGenerator.LONGS.length);
			io.putFloatArray(VariableGenerator.FLOATS, VariableGenerator.FLOATS.length);
			io.putDoubleArray(VariableGenerator.DOUBLES, VariableGenerator.DOUBLES.length);
			io.putStringArray(VariableGenerator.STRING, VariableGenerator.STRING.length);
		}
	}

	public void rewind() {
		io.rewind();
	}

	public void getValues(int depth) {
		for (int i = 0; i < depth; i++) {
			Assertions.assertEquals(VariableGenerator.nextBoolean(), io.getBoolean());
			Assertions.assertEquals(VariableGenerator.nextByte(), io.getByte());
			Assertions.assertEquals(VariableGenerator.nextChar(), io.getChar());
			Assertions.assertEquals(VariableGenerator.nextShort(), io.getShort());
			Assertions.assertEquals(VariableGenerator.nextInt(), io.getInt());
			Assertions.assertEquals(VariableGenerator.nextLong(), io.getLong());
			Assertions.assertEquals(VariableGenerator.nextFloat(), io.getFloat());
			Assertions.assertEquals(VariableGenerator.nextDouble(), io.getDouble());
			Assertions.assertEquals(VariableGenerator.nextString(), io.getString());
			Assertions.assertArrayEquals(io.getBooleanArray(VariableGenerator.BOOLEANS.length), VariableGenerator.BOOLEANS);
			Assertions.assertArrayEquals(io.getByteArray(VariableGenerator.BYTES.length), VariableGenerator.BYTES);
			Assertions.assertArrayEquals(io.getCharArray(VariableGenerator.CHARS.length), VariableGenerator.CHARS);
			Assertions.assertArrayEquals(io.getShortArray(VariableGenerator.SHORTS.length), VariableGenerator.SHORTS);
			Assertions.assertArrayEquals(io.getIntArray(VariableGenerator.INTS.length), VariableGenerator.INTS);
			Assertions.assertArrayEquals(io.getLongArray(VariableGenerator.LONGS.length), VariableGenerator.LONGS);
			Assertions.assertArrayEquals(io.getFloatArray(VariableGenerator.FLOATS.length), VariableGenerator.FLOATS);
			Assertions.assertArrayEquals(io.getDoubleArray(VariableGenerator.DOUBLES.length), VariableGenerator.DOUBLES);
			Assertions.assertArrayEquals(io.getStringArray(VariableGenerator.STRING.length), VariableGenerator.STRING);
		}
	}
}
