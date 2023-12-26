package ru.vsu.cs.OOP2023.elfimov_a_m;

import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig.Fool36GameConfig;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig.GameConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        String[] types = new String[] {"SimpleBot", "SimpleBot"}; // Поменять на User, для ручной игры
        GameConfig gameConfig = new Fool36GameConfig(types);

        Map<String, Integer> statistic = new HashMap<>();

        System.out.println("Введите число игр: ");
        int n = new Scanner(System.in).nextInt();
        while(n-- > 0){
            Game game = new Game(gameConfig);
            String name = game.start();

            statistic.put(name, 1+statistic.getOrDefault(name,0));
        }

        System.out.println(statistic);
    }
}
