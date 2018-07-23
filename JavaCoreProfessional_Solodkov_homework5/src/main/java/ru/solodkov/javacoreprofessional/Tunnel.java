package ru.solodkov.javacoreprofessional;

public class Tunnel extends Stage {
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car car) {
        try {
            try {
                System.out.println(car.getName() +
                        " готовится к этапу(ждет): " + description);

                // в тунель нельзя заехать
                App.TUNNEL.acquire();

                System.out.println(car.getName() +
                        " начал этап: " + description);
                Thread.sleep(length / car.getSpeed() * 1000);

                // в тунель можно заехать
                App.TUNNEL.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(car.getName() +
                        " закончил этап: " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}