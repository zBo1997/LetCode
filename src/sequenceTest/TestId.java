package sequenceTest;

import java.net.InetAddress;
import java.net.NetworkInterface;

public class TestId {

    private static final long offSetLimit = 5;
    private static final long workerIdBits = 5L;
    private static final long datacenterIdBits = 5L;
    private static final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private static final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    public static void main(String[] args) {
        System.out.println("maxDatacenterId"+maxDatacenterId);
        System.out.println("result "+getDatacenterId(maxDatacenterId));
    }


    protected static long getDatacenterId(long maxDatacenterId) {
        System.err.println(maxDatacenterId);
        long id = 0L;
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            if (network == null) {
                id = 1L;
            } else {
                byte[] mac = network.getHardwareAddress();
                if (null != mac) {
                    id = ((0x000000FF & (long) mac[mac.length - 1]) | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
                    id = id % (maxDatacenterId + 1);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return id;
    }
}
