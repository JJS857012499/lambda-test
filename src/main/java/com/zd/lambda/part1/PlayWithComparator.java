package com.zd.lambda.part1;

import com.zd.lambda.part1.module.Person;

import java.util.Comparator;
import java.util.function.Function;

//import com.zd.lambda.part1.util.Comparator;

/**
 * Created by 江俊升 on 2019/3/11.
 */
public class PlayWithComparator {

    public static void main(String[] args) {

        Person michael = new Person("Jackson", "Michael", 51);
        Person michaelBis = new Person("Jackson", null, 51);
        Person rod = new Person("Rod", "Stewart", 71);
        Person paul = new Person("Paul", "McCartney", 74);
        Person mick = new Person("Mick", "Jagger", 73);
        Person jermaine = new Person("Jackson", "Jermaine", 61);


        Function<Person, String> getLastName = Person::getLastName;
        Function<Person, String> getFirstName = Person::getFirstName;
        Function<Person, Integer> getAge = Person::getAge;

        Comparator<Person> cmp = Comparator.comparing(getLastName)
                .thenComparing(getFirstName)
                .thenComparing(getAge);

//        System.out.println(cmp.compare(michael, michaelBis));

        Comparator<Person> cmpNull = Comparator.nullsLast(cmp);


//        List<Person> people = Arrays.asList(rod, null, michael);
//        people.sort(cmpNull);
//        people.forEach(System.out::println);

        Comparator<Person> cmpReversed = cmp.reversed();

        Comparator<String> cmp2 = Comparator.<String>nullsLast(Comparator.naturalOrder());
        int result = cmp2.compare("Hello", null);
        System.out.println("result = " + result);



    }

}
