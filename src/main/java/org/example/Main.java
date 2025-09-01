package org.example;

import lombok.SneakyThrows;
import org.example.task3.CustomThreadPool;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {

        var threadPool = new CustomThreadPool(3);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            System.out.println("Добавление задачи  " + finalI);
            threadPool.execute(() -> {
                System.out.println("Выполняется task%d потоком ".formatted(finalI) + Thread.currentThread().getName());
                try {
                    Thread.sleep(3000); // Симуляция работы
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        threadPool.shutdown();

        try {
            threadPool.execute(() -> System.out.println("Не должно выводиться!?"));
        } catch (IllegalStateException e) {
            System.out.printf("Сообщение после закрытия пула: %s%n", e.getMessage());
        }
    }
}
