package dev.quantumfusion.flyio;

import dev.quantumfusion.flyio.io.DualIO;
import dev.quantumfusion.flyio.io.impl.*;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.List;
import java.util.function.IntFunction;

public class IOTests {
	public static int DEPTH = 100000;

	@TestFactory
	Collection<DynamicTest> ioTests() {

		return List.of(
				DynamicTest.dynamicTest("ArrayIO", () -> testIO(ArrayIO::create)),
				DynamicTest.dynamicTest("ByteBufferIO Heap", () -> testIO(ByteBufferIO::create)),
				DynamicTest.dynamicTest("ByteBufferIO Direct", () -> testIO(ByteBufferIO::createDirect)),
				DynamicTest.dynamicTest("UnsafeIO", () -> testIO(UnsafeIO::create))
				//DynamicTest.dynamicTest("Stream", () -> {
				//	final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				//	final IOInterfaceTest out = new IOInterfaceTest(OutputStreamIO.wrap(outputStream));
				//	out.putValues(DEPTH);
//
				//	VariableGenerator.reset();
				//	outputStream.flush();
//
				//	final IOInterfaceTest in = new IOInterfaceTest(InputStreamIO.wrap(new ByteArrayInputStream(outputStream.toByteArray())));
				//	in.getValues(DEPTH);
				//	VariableGenerator.reset();
				//})
		);
	}


	private static void testIO(IntFunction<DualIO> creator) {
		final IOInterfaceTest ioInterfaceTest = new IOInterfaceTest(creator.apply(1024 * DEPTH));
		ioInterfaceTest.putValues(DEPTH);

		VariableGenerator.reset();
		ioInterfaceTest.rewind();

		ioInterfaceTest.getValues(DEPTH);
		VariableGenerator.reset();
	}
}
