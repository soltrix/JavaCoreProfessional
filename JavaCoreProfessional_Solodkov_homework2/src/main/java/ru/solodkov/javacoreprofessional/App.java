package ru.solodkov.javacoreprofessional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private static PreparedStatement ps = null;
    private static Integer price = 0;
    private static List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC"); // загружаем драйвер в память
            conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db"); // подключаемся к БД
            stmt = conn.createStatement();
            System.out.println("Opening DB mydatabase.db successfully");

            // task 1
            createTable();
            // task 2
            //fillTable();

            System.out.println("Чтобы узнать цену товара напишите: «/цена товар545»");
            System.out.println("Чтобы изменить цену товара напишите: «/сменитьцену товар10 10000»");
            System.out.println("Чтобы вывести товары в заданном ценовом диапазоне напишите: «/товарыпоцене 100 600»");
            Scanner sc = new Scanner(System.in);
            String b = sc.nextLine();
            String[] fields = b.split(" ");

            if (fields.length > 0) {
                switch (fields[0]) {
                    case ("/цена"):
                        // task 3
                        getPriceByTitle(fields[1]);
                        break;
                    case ("/сменитьцену"):
                        // task 4
                        changePrice(fields[1], fields[2]);
                        break;
                    case ("/товарыпоцене"):
                        // task 5
                        getProductsByRange(fields[1], fields[2]);
                        break;
                }
            }
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable() {
        try {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Products\n" +
                    "(\n" +
                    "    id          INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                    "    prodid      TEXT                NOT NULL,\n" +
                    "    title       TEXT                NOT NULL,\n" +
                    "    cost        INTEGER             NOT NULL\n" +
                    ");");
            System.out.println("Table Products created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void fillTable() {
        try {
            stmt.executeUpdate("DELETE FROM Products");
            System.out.println("Table Products clear");

            ps = conn.prepareStatement("INSERT INTO Products (prodid, title, cost) VALUES(?, ?, ?);");
            for (int i = 1; i <= 1000; i++) {
                ps.setString(1, "id_товара " + i);
                ps.setString(2, "товар" + i);
                ps.setInt(3, i * 10);
                ps.executeUpdate();
            }
            System.out.println("Table Products is filled");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getPriceByTitle(String field) {
        try {
            ps = conn.prepareStatement("SELECT * FROM Products WHERE title=?;");
            ps.setString(1, field);
            rs = ps.executeQuery();
            while (rs.next()) {
                price = rs.getInt("cost");
            }
            System.out.println((price <= 0) ? "Такого товара нет" : price);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void changePrice(String title, String price) {
        try {
            ps = conn.prepareStatement("UPDATE Products SET cost=? WHERE title=?;");
            ps.setInt(1, Integer.parseInt(price));
            ps.setString(2, title);
            ps.executeUpdate();
            System.out.println("Prise on " + title + " changed to " + price);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getProductsByRange(String from, String to) {
        try {
            ps = conn.prepareStatement("SELECT * FROM Products WHERE cost>=? AND cost<=?");
            ps.setInt(1, Integer.parseInt(from));
            ps.setInt(2, Integer.parseInt(to));
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(
                        rs.getInt("id") + "\t" +
                                rs.getString("title") + "\t" +
                                rs.getInt("cost"));
            }
            for (String item : list) {
                System.out.println(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
