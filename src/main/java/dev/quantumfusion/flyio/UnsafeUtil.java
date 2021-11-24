package dev.quantumfusion.flyio;

import org.jetbrains.annotations.NotNull;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;

public final class UnsafeUtil {
	// The Unsafe:tm:
	public static final Unsafe UNSAFE = getUnsafeInstance();

	// Array Offset
	public static final int BOOLEAN_ARRAY_BASE = Unsafe.ARRAY_BOOLEAN_BASE_OFFSET;
	public static final int BYTE_ARRAY_BASE = Unsafe.ARRAY_BYTE_BASE_OFFSET;
	public static final int SHORT_ARRAY_BASE = Unsafe.ARRAY_SHORT_BASE_OFFSET;
	public static final int CHAR_ARRAY_BASE = Unsafe.ARRAY_CHAR_BASE_OFFSET;
	public static final int INT_ARRAY_BASE = Unsafe.ARRAY_INT_BASE_OFFSET;
	public static final int LONG_ARRAY_BASE = Unsafe.ARRAY_LONG_BASE_OFFSET;
	public static final int FLOAT_ARRAY_BASE = Unsafe.ARRAY_FLOAT_BASE_OFFSET;
	public static final int DOUBLE_ARRAY_BASE = Unsafe.ARRAY_DOUBLE_BASE_OFFSET;
	public static final int OBJECT_ARRAY_BASE = Unsafe.ARRAY_OBJECT_BASE_OFFSET;

	// Array Index Scale
	public static final int BOOLEAN_ARRAY_INDEX_SCALE = Unsafe.ARRAY_BOOLEAN_INDEX_SCALE;
	public static final int BYTE_ARRAY_INDEX_SCALE = Unsafe.ARRAY_BYTE_INDEX_SCALE;
	public static final int SHORT_ARRAY_INDEX_SCALE = Unsafe.ARRAY_SHORT_INDEX_SCALE;
	public static final int CHAR_ARRAY_INDEX_SCALE = Unsafe.ARRAY_CHAR_INDEX_SCALE;
	public static final int INT_ARRAY_INDEX_SCALE = Unsafe.ARRAY_INT_INDEX_SCALE;
	public static final int LONG_ARRAY_INDEX_SCALE = Unsafe.ARRAY_LONG_INDEX_SCALE;
	public static final int FLOAT_ARRAY_INDEX_SCALE = Unsafe.ARRAY_FLOAT_INDEX_SCALE;
	public static final int DOUBLE_ARRAY_INDEX_SCALE = Unsafe.ARRAY_DOUBLE_INDEX_SCALE;
	public static final int OBJECT_ARRAY_INDEX_SCALE = Unsafe.ARRAY_OBJECT_INDEX_SCALE;

	private static Unsafe getUnsafeInstance() {
		Class<Unsafe> clazz = Unsafe.class;
		for (Field field : clazz.getDeclaredFields()) {
			if (!field.getType().equals(clazz))
				continue;
			final int modifiers = field.getModifiers();
			if (!(Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers)))
				continue;
			try {
				field.setAccessible(true);
				return (Unsafe) field.get(null);
			} catch (Exception ignored) {
			}
			break;
		}

		throw new IllegalStateException("Unsafe is unavailable.");
	}

	// Memory Copy
	public static void memCpy(Object srcObject, long srcOffset, Object destObject, long destOffset, long bytes) {
		UNSAFE.copyMemory(srcObject, srcOffset, destObject, destOffset, bytes);
	}

	public static void memCpy(Object srcObject, long srcOffset, long destAddress, long bytes) {
		UNSAFE.copyMemory(srcObject, srcOffset, null, destAddress, bytes);
	}

	public static void memCpy(long srcAddress, Object destObject, long destOffset, long bytes) {
		UNSAFE.copyMemory(null, srcAddress, destObject, destOffset, bytes);
	}

	public static void memCpy(long srcAddress, long destAddress, long bytes) {
		UNSAFE.copyMemory(null, srcAddress, null, destAddress, bytes);
	}

	// Field getters
	public static boolean getBooleanField(Object object, Field field) {
		if (Modifier.isVolatile(field.getModifiers())) {
			return UNSAFE.getBooleanVolatile(object, getFieldOffset(field));
		} else return UNSAFE.getBoolean(object, getFieldOffset(field));
	}

	public static byte getByteField(Object object, Field field) {
		if (Modifier.isVolatile(field.getModifiers())) {
			return UNSAFE.getByteVolatile(object, getFieldOffset(field));
		} else return UNSAFE.getByte(object, getFieldOffset(field));
	}

	public static char getCharField(Object object, Field field) {
		if (Modifier.isVolatile(field.getModifiers())) {
			return UNSAFE.getCharVolatile(object, getFieldOffset(field));
		} else return UNSAFE.getChar(object, getFieldOffset(field));
	}

	public static short getShortField(Object object, Field field) {
		if (Modifier.isVolatile(field.getModifiers())) {
			return UNSAFE.getShortVolatile(object, getFieldOffset(field));
		} else return UNSAFE.getShort(object, getFieldOffset(field));
	}

	public static int getIntField(Object object, Field field) {
		if (Modifier.isVolatile(field.getModifiers())) {
			return UNSAFE.getIntVolatile(object, getFieldOffset(field));
		} else return UNSAFE.getInt(object, getFieldOffset(field));
	}

	public static float getFloatField(Object object, Field field) {
		if (Modifier.isVolatile(field.getModifiers())) {
			return UNSAFE.getFloatVolatile(object, getFieldOffset(field));
		} else return UNSAFE.getFloat(object, getFieldOffset(field));
	}

	public static long getLongField(Object object, Field field) {
		if (Modifier.isVolatile(field.getModifiers())) {
			return UNSAFE.getLongVolatile(object, getFieldOffset(field));
		} else return UNSAFE.getLong(object, getFieldOffset(field));
	}

	public static double getDoubleField(Object object, Field field) {
		if (Modifier.isVolatile(field.getModifiers())) {
			return UNSAFE.getDoubleVolatile(object, getFieldOffset(field));
		} else return UNSAFE.getDouble(object, getFieldOffset(field));
	}

	public static Object getObjectField(Object object, Field field) {
		if (Modifier.isVolatile(field.getModifiers())) {
			return UNSAFE.getObjectVolatile(object, getFieldOffset(field));
		} else return UNSAFE.getObject(object, getFieldOffset(field));
	}

	// Field Acquire builtin
	public static boolean getBooleanField(Object object, String fieldName) {
		return getBooleanField(object, getFieldFromClass(object.getClass(), fieldName));
	}

	public static byte getByteField(Object object, String fieldName) {
		return getByteField(object, getFieldFromClass(object.getClass(), fieldName));
	}

	public static char getCharField(Object object, String fieldName) {
		return getCharField(object, getFieldFromClass(object.getClass(), fieldName));
	}

	public static short getShortField(Object object, String fieldName) {
		return getShortField(object, getFieldFromClass(object.getClass(), fieldName));
	}

	public static int getIntField(Object object, String fieldName) {
		return getIntField(object, getFieldFromClass(object.getClass(), fieldName));
	}

	public static float getFloatField(Object object, String fieldName) {
		return getFloatField(object, getFieldFromClass(object.getClass(), fieldName));
	}

	public static long getLongField(Object object, String fieldName) {
		return getLongField(object, getFieldFromClass(object.getClass(), fieldName));
	}

	public static double getDoubleField(Object object, String fieldName) {
		return getDoubleField(object, getFieldFromClass(object.getClass(), fieldName));
	}

	public static Object getObjectField(Object object, String fieldName) {
		return getObjectField(object, getFieldFromClass(object.getClass(), fieldName));
	}

	// Field setters
	public static void setBooleanField(Object object, Field field, boolean value) {
		if (Modifier.isVolatile(field.getModifiers())) {
			UNSAFE.putBooleanVolatile(object, getFieldOffset(field), value);
		} else UNSAFE.putBoolean(object, getFieldOffset(field), value);
	}

	public static void setByteField(Object object, Field field, byte value) {
		if (Modifier.isVolatile(field.getModifiers())) {
			UNSAFE.putByteVolatile(object, getFieldOffset(field), value);
		} else UNSAFE.putByte(object, getFieldOffset(field), value);
	}

	public static void setCharField(Object object, Field field, char value) {
		if (Modifier.isVolatile(field.getModifiers())) {
			UNSAFE.putCharVolatile(object, getFieldOffset(field), value);
		} else UNSAFE.putChar(object, getFieldOffset(field), value);
	}

	public static void setShortField(Object object, Field field, short value) {
		if (Modifier.isVolatile(field.getModifiers())) {
			UNSAFE.putShortVolatile(object, getFieldOffset(field), value);
		} else UNSAFE.putShort(object, getFieldOffset(field), value);
	}

	public static void setIntField(Object object, Field field, int value) {
		if (Modifier.isVolatile(field.getModifiers())) {
			UNSAFE.putIntVolatile(object, getFieldOffset(field), value);
		} else UNSAFE.putInt(object, getFieldOffset(field), value);
	}

	public static void setFloatField(Object object, Field field, float value) {
		if (Modifier.isVolatile(field.getModifiers())) {
			UNSAFE.putFloatVolatile(object, getFieldOffset(field), value);
		} else UNSAFE.putFloat(object, getFieldOffset(field), value);
	}

	public static void setLongField(Object object, Field field, long value) {
		if (Modifier.isVolatile(field.getModifiers())) {
			UNSAFE.putLongVolatile(object, getFieldOffset(field), value);
		} else UNSAFE.putLong(object, getFieldOffset(field), value);
	}

	public static void setDoubleField(Object object, Field field, double value) {
		if (Modifier.isVolatile(field.getModifiers())) {
			UNSAFE.putDoubleVolatile(object, getFieldOffset(field), value);
		} else UNSAFE.putDouble(object, getFieldOffset(field), value);
	}

	public static void setObjectField(Object object, Field field, Object value) {
		if (Modifier.isVolatile(field.getModifiers())) {
			UNSAFE.putObjectVolatile(object, getFieldOffset(field), value);
		} else UNSAFE.putObject(object, getFieldOffset(field), value);
	}

	// Field Acquire builtin
	public static void setBooleanField(Object object, String fieldName, boolean value) {
		setBooleanField(object, getFieldFromClass(object.getClass(), fieldName), value);
	}

	public static void setByteField(Object object, String fieldName, byte value) {
		setByteField(object, getFieldFromClass(object.getClass(), fieldName), value);
	}

	public static void setCharField(Object object, String fieldName, char value) {
		setCharField(object, getFieldFromClass(object.getClass(), fieldName), value);
	}

	public static void setShortField(Object object, String fieldName, short value) {
		setShortField(object, getFieldFromClass(object.getClass(), fieldName), value);
	}

	public static void setIntField(Object object, String fieldName, int value) {
		setIntField(object, getFieldFromClass(object.getClass(), fieldName), value);
	}

	public static void setFloatField(Object object, String fieldName, float value) {
		setFloatField(object, getFieldFromClass(object.getClass(), fieldName), value);
	}

	public static void setLongField(Object object, String fieldName, long value) {
		setLongField(object, getFieldFromClass(object.getClass(), fieldName), value);
	}

	public static void setDoubleField(Object object, String fieldName, double value) {
		setDoubleField(object, getFieldFromClass(object.getClass(), fieldName), value);
	}

	public static void setObjectField(Object object, String fieldName, Object value) {
		setObjectField(object, getFieldFromClass(object.getClass(), fieldName), value);
	}

	// Field offset
	public static long getDeclaredFieldOffset(Class<?> aClass, String fieldName) {
		try {
			return getFieldOffset(aClass.getDeclaredField(fieldName));
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
	}

	public static long getFieldOffset(Class<?> aClass, String fieldName) {
		try {
			return getFieldOffset(aClass.getField(fieldName));
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
	}

	public static long getFieldOffset(Field field) {
		return Modifier.isStatic(field.getModifiers()) ? UNSAFE.staticFieldOffset(field) : UNSAFE.objectFieldOffset(field);
	}

	private static Field getFieldFromClass(@NotNull Class<?> aClass, String name) {
		var currentClass = aClass;
		while (currentClass != null) {
			try {
				return currentClass.getDeclaredField(name);
			} catch (NoSuchFieldException ignored) {
			}
			currentClass = currentClass.getSuperclass();
		}
		throw new RuntimeException("Could not find field " + name + " in " + aClass.getSimpleName());
	}

	public static void main(String[] args) {
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(12);
		System.out.println(UnsafeUtil.getLongField(byteBuffer, "address"));
	}
}
