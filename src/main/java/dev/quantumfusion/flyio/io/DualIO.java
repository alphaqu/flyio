package dev.quantumfusion.flyio.io;

/**
 * An IO implementation with both Read and Write Access
 */
public interface DualIO extends ReadIO, WriteIO, RawIO {
}
