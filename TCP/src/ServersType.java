public enum ServersType {
    SERVER("165.22.94.126"),
    KALI_LINUX("192.168.58.102"),
    UBUNTU_LINUX("192.168.58.101"),
    DEFAULT("192.168.56.1"),
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
