package com.zd.lambda.part1;

import com.zd.lambda.part1.module.Person;
import com.zd.lambda.part1.util.Predicate;
import com.zd.lambda.part1.util.Validator;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by 江俊升 on 2019/3/13.
 */
public class PlayWithValidator {

    public static void main(String[] args) {


        Predicate<Person> p1 = t -> t.getLastName() == null;
        Predicate<Person> p2 = t -> t.getFirstName() == "";
        Predicate<Person> p3 = t -> t.getAge() < 50;

        Validator<Person> validator = Validator.<Person>firstValidate(p1, "不能为空")
                .thenValidate(p2, "需要空串")
                .thenValidate(p3, "需要小于50");

        Person p = new Person(null,"",49);
        Person pp = new Person("Mic","",49);
//        assertThat(validator.validate(p).get()).isSameAs(p);
        assertThat(validator.validate(pp).get()).isSameAs(pp);

    }

}
