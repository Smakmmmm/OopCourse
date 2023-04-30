package ru.makhmedov.lambda_main;

import ru.makhmedov.lambda_person.Person;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Максим", 26),
                new Person("Денис", 20),
                new Person("Дмитрий", 33),
                new Person("Виктория", 18),
                new Person("Денис", 15),
                new Person("Иван", 45)
        );

        List<String> uniqueNames = persons.stream()
                .map(Person::name)
                .distinct()
                .toList();
        System.out.println(uniqueNames);

        String uniqueNamesString = uniqueNames.stream()
                .collect(Collectors.joining(", ", "Имена: ", "."));
        System.out.println(uniqueNamesString);

        List<Person> personsUnder18 = persons.stream()
                .filter(p -> p.age() < 18)
                .toList();

        if (personsUnder18.isEmpty()) {
            System.out.println("Нет людей младше 18.");
        } else {
            System.out.println(personsUnder18.stream()
                    .map(Person::name)
                    .collect(Collectors.joining(", ", "Имена людей младше 18 лет: ", ".")));
        }

        OptionalDouble averageAge = personsUnder18.stream()
                .mapToInt(Person::age)
                .average();
        averageAge.ifPresentOrElse(personsUnder18AverageAge -> System.out.println("Средний возраст людей младше 18: " + personsUnder18AverageAge), () -> System.out.println("Нет людей младше 18."));

        Map<String, Double> averageAgesByNames = persons.stream()
                .collect(Collectors.groupingBy(Person::name, Collectors.averagingInt(Person::age)));
        System.out.println(averageAgesByNames);

        System.out.println("Люди возрастом от 20 до 45:");
        persons.stream()
                .filter(p -> p.age() >= 20 && p.age() <= 45)
                .sorted((p1, p2) -> p2.age() - p1.age())
                .forEach(s -> System.out.print(s.name() + " "));
    }
}
