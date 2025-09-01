package org.example.task3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import lombok.Getter;

@Getter
public class CustomThreadPool {
    private final WorkerThread[] workerThreads;
    private final Queue<Runnable> taskQueue;
    private final AtomicBoolean isShutdown;
    private final ReentrantLock rLock = new ReentrantLock();
    private final Condition condition = rLock.newCondition();

    public CustomThreadPool(int capacity) {
        this.workerThreads = new WorkerThread[capacity];
        this.taskQueue = new LinkedList<>();
        this.isShutdown = new AtomicBoolean(false);

        for (int i = 0; i < capacity; i++) {
            workerThreads[i] = new WorkerThread("Поток-" + i);
            workerThreads[i].start();
        }
    }

    public void execute(Runnable task) {
        if (isShutdown.get()) {
            throw new IllegalStateException("Нельзя добавить новые задачи, пул закрыт.");
        }
        rLock.lock();
        try {
            taskQueue.add(task);
            condition.signal();
        } finally {
            rLock.unlock();
        }
    }

    public void shutdown() {
        if (isShutdown.compareAndSet(false, true)) {
            rLock.lock();
            try {
                condition.signalAll(); // Разбудить все потоки
            } finally {
                rLock.unlock();
            }
        }
    }

    @Getter
    private class WorkerThread extends Thread {
        private final AtomicBoolean running = new AtomicBoolean(true);

        public WorkerThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (running.get()) {
                Runnable task = null;
                rLock.lock();
                try {
                    while (taskQueue.isEmpty() && !isShutdown.get()) {
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            System.out.println("Тут ошибка");
                        }
                    }

                    if (!taskQueue.isEmpty()) {
                        task = taskQueue.poll();
                    }
                } finally {
                    rLock.unlock();
                }

                if (task != null) {
                    task.run();
                }
                if (isShutdown.get() && taskQueue.isEmpty()) {
                    running.set(false); // Закончить работу потока когда не осталось задач и грохнули пул
                    return;
                }

            }
        }
    }
}

