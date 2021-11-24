package dev.quantumfusion.flyio.io;

@SuppressWarnings("unused") // good morning intellij this is a library
public interface RawIO extends AutoCloseable {
	void rewind();

	int getPos();

	void setPos(int pos);

	void close();
}
