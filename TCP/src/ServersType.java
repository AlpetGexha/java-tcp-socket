public enum ServersType {
    KALI_LINUX("192.168.58.101"),
    UBUNTU_LINUX("192.168.58.102"),
    LOCALHOST("localhost");

    private final String ipAddress;

    // Constructor
    ServersType(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    // Getter method
    public String getIpAddress() {
        return ipAddress;
    }

    @Override
    public String toString() {
        return name() + " (" + ipAddress + ")";
    }
}
