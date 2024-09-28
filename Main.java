class AnimalThread extends Thread {
    private String name;
    private int priority;
    private int distance;

    public AnimalThread(String name, int priority) {
        this.name = name;
        this.priority = priority;
        this.distance = 0;
        setName(name);
        setPriority(priority);
    }

    @Override
    public void run() {
        while (distance < 100) {
            distance += (int) (Math.random() * 10); // Каждое движение на случайное расстояние
            System.out.printf("%s пробежал %d метров.%n", getName(), distance, distance);

            // Изменение приоритета отстающего животного
            if (getName().equals("Черепаха")) {
                Thread.currentThread().setPriority(Math.min(Thread.MAX_PRIORITY, getPriority() + 1));
            } else {
                Thread.currentThread().setPriority(Math.max(Thread.MIN_PRIORITY, getPriority() - 1));
            }

            try {
                Thread.sleep(100); // Задержка для симуляции времени
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("%s достиг финиша!%n", getName());
    }
}

class RabbitAndTurtle {
    public static void main(String[] args) {
        AnimalThread rabbit = new AnimalThread("Кролик", Thread.MAX_PRIORITY);
        AnimalThread turtle = new AnimalThread("Черепаха", Thread.MIN_PRIORITY);

        rabbit.start();
        turtle.start();

        try {
            rabbit.join();
            turtle.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Гонка завершена!");
    }
}
