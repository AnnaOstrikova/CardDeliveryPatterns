package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    // TODO: добавить логику для объявления переменной date и задания её значения, для генерации строки с датой
    // Вы можете использовать класс LocalDate и его методы для получения и форматирования даты

    public static String generateCity() {
        var cities = new String[]{"Барнаул", "Белгород", "Брянск", "Волгоград", "Екатеринбург", "Иваново", "Курган", "Липецк", "Тюмень", "Челябинск"};

        // TODO: добавить логику для объявления переменной city и задания её значения, генерацию можно выполнить
        // с помощью Faker, либо используя массив валидных городов и класс Random
        return cities[new Random().nextInt(cities.length)];
    }

    public static String generateName() {
        Faker faker = new Faker(new Locale("ru"));
        String name = faker.name().fullName().replace("ё", "е");
        // TODO: добавить логику для объявления переменной name и задания её значения, для генерации можно
        // использовать Faker
        return name;
    }

    public static String generatePhone() {
        Faker faker = new Faker(new Locale("ru"));
        String phone = faker.phoneNumber().phoneNumber();
        // TODO: добавить логику для объявления переменной phone и задания её значения, для генерации можно
        // использовать Faker
        return phone;
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser() {
            Faker faker = new Faker(new Locale("ru"));
            // TODO: добавить логику для создания пользователя user с использованием методов generateCity(locale),
            // generateName(locale), generatePhone(locale)
            return new UserInfo(generateCity(), generateName(), generatePhone());
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}