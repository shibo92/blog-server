package thread.bank;

public class BankDemo {
    public static void main(String[] args) {
        Customer c = new Customer();
        Thread t1 = new Thread(c);
        Thread t2 = new Thread(c);
        t1.start();
        t2.start();
    }
}

class Bank {
    private int sum;

    public void add(int num) {
        this.sum += num;
        System.out.println(sum);
    }
}

class Customer implements Runnable {
    private Bank bank = new Bank();
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
            bank.add(100);
        }
    }
}

