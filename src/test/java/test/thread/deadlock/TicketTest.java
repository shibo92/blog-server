package test.thread.deadlock;

public class TicketTest {
    public static void main(String[] args) {
        Ticket ticket1 = new Ticket();
        Thread t1 = new Thread(ticket1);
        Thread t2 = new Thread(ticket1);
        Thread t3 = new Thread(ticket1);
        Thread t4 = new Thread(ticket1);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
