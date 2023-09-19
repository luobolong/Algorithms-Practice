package com.luobo.design;

interface Observer {
    void update(String message);
}

public class ChainObserver implements Observer {
    private Observer next;

    public ChainObserver(Observer next) {
        this.next = next;
    }

    @Override
    public void update(String message) {
        // Do something
        System.out.println("Received message: " + message);

        // Notify the next observer in the chain
        if (next != null) {
            next.update(message);
        }
    }
    public static void main(String[] args) {
        Observer observer3 = new ChainObserver(null);
        Observer observer2 = new ChainObserver(observer3);
        Observer observer1 = new ChainObserver(observer2);

        observer1.update("Trigger chain");
    }
}

