package thread.communication;

public class Test {
    public static void main(String[] args) {
        Person p = new Person();
        Input input = new Input(p);
        Output output = new Output(p);

        Thread threadInput = new Thread(input);
        Thread threadOutput = new Thread(output);

        threadInput.start();
        threadOutput.start();
    }
}

class Person {
    String name;
    String sex;
    boolean flag = false;
}

class Input implements Runnable {
    Person person;

    Input(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            synchronized (person) {
                // 为true表示已写入，还未读
                if(person.flag){
                    try {
                        person.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (i == 0) {
                    this.person.name = "mike";
                    this.person.sex = "nan";
                } else {
                    this.person.name = "哈哈";
                    this.person.sex = "我是女生";
                }
                // 写入成功
                person.flag = true;
                // 唤醒读取线程
                person.notify();
            }
            i = (i + 1) % 2;
        }
    }
}

class Output implements Runnable {
    Person person;

    Output(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (person) {
                if(!person.flag){
                    try {
                        person.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(this.person.name + " == " + this.person.sex);
                // 读取完毕
                person.flag = false;
                // 唤醒写入线程
                person.notify();

            }
        }
    }
}