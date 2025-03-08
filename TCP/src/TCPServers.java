public class TCPServers {
    public static void main(String[] args) {
        Thread server1Thread = new Thread(() -> new Server1().start());
        Thread server2Thread = new Thread(() -> new Server2().start());
        Thread server3Thread = new Thread(() -> new Server3().start());

        server1Thread.start();
        server2Thread.start();
        server3Thread.start();
    }
}
