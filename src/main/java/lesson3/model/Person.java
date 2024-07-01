package lesson3.model;

import java.util.Objects;

public class Person {

    private Long id;
    private String name;
    private String lastName;
    private Byte age;

    public Person() {}

    public Person(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Person person = (Person) object;
        return Objects.equals(getId(), person.getId()) && Objects.equals(getName(), person.getName()) && Objects.equals(getLastName(), person.getLastName()) && Objects.equals(getAge(), person.getAge());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLastName(), getAge());
    }

    @Override
    public String toString() {
        return "Person{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", lastName='" + lastName + '\'' +
               ", age=" + age +
               '}';
    }

    public static class Builder {
        private Long id;
        private String name;
        private String lastName;
        private Byte age;

        public Builder() {}

        public Builder(Person person) {
            this.id = person.id;
            this.name = person.name;
            this.lastName = person.lastName;
            this.age = person.age;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder age(Byte age) {
            this.age = age;
            return this;
        }

        public Person build() {
            Person person = new Person();
            person.id = this.id;
            person.name = this.name;
            person.lastName = this.lastName;
            person.age = this.age;
            return person;
        }
    }
}
