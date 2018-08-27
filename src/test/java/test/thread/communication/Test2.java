package test.thread.communication;

import sun.security.util.Resources_es;

import javax.sound.midi.Soundbank;

public class Test2 {
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

class Resource {
    private String name;
    private String sex;
    private boolean flag = false;

    public synchronized void set(String name,String sex){
        // 为true表示已写入，还未读
        if(this.flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.name = name;
        this.sex = sex;
        // 写入成功
        this.flag = true;
        // 唤醒读取线程
        this.notify();
    }

    public synchronized void out(){
        if(this.flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(this.name + "..." + this.sex);
        this.flag = false;
        this.notify();

    }
}

class Input2 implements Runnable {
    Resource person;

    Input2(Resource person) {
        this.person = person;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            if (i == 0) {
                person.set("mike","nan");
            } else {
                person.set("哈哈","我是女生");
            }
            i = (i + 1) % 2;
        }
    }
}

class Output2 implements Runnable {
    Resource person;

    Output2(Resource person) {
        this.person = person;
    }

    @Override
    public void run() {
        while (true) {
            person.out();
        }
    }
}