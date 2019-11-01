package ai.widya.deafble;

public class IBeacon {

    private String uuid;
    private String major;
    private String minor;
    private double distance;
    private int rssi;
    private int txPower;
    private String mac;

    public IBeacon(String uuid, String major, String minor, double distance, int rssi, String mac, int txPower) {
        this.uuid = uuid;
        this.major = major;
        this.minor = minor;
        this.distance = distance;
        this.rssi = rssi;
        this.mac = mac;
        this.txPower = txPower;
    }

    public int getTxPower() {
        return txPower;
    }

    public void setTxPower(int txPower) {
        this.txPower = txPower;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
