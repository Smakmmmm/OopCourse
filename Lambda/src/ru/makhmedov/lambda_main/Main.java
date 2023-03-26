package ru.makhmedov.lambda_main;

import ru.makhmedov.person.Person;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Максим", 26));
        persons.add(new Person("Денис", 20));
        persons.add(new Person("Дмитрий", 33));
        persons.add(new Person("Виктория", 18));
        persons.add(new Person("Денис", 15));
        persons.add(new Person("Иван", 45));

        List<String> uniqueNames = persons.stream().map(Person::getName).distinct().toList();
        System.out.println(uniqueNames);

        String uniqueNamesString = persons.stream().map(Person::getName)
                .distinct().collect(Collectors.joining(", ", "Имена: ", "."));
        System.out.println(uniqueNamesString);

        List<Person> personsOver18 = persons.stream().filter(p -> (p.getAge() > 18)).toList();
        personsOver18.forEach(s -> System.out.print(s.getName() + " "));
        System.out.println();

        double averageAge = personsOver18.stream().mapToDouble(Person::getAge).summaryStatistics().getAverage();
        System.out.println("Средний возраст для людей старше 18: " + averageAge);

        Map<String, Double> personsByName = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));
        System.out.println(personsByName);

        System.out.println("Люди возрастом от 20 до 45:");
        persons.stream().
                filter(p -> (p.getAge() >= 20 && p.getAge() <= 45))
                .sorted(Comparator.comparingInt(Person::getAge)).forEach(s -> System.out.print(s.getName() + " "));
    }
}
