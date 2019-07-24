package com.hana.mao;

import java.util.concurrent.atomic.AtomicBoolean;

public class su {
    private AtomicBoolean execute = new AtomicBoolean();

    public void run(Runnable task) {
        if (execute.get()) return;
        if (execute.compareAndSet(false, true)) {
            task.run();
        }
    }
}
