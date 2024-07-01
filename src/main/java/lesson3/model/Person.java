package lesson3.model;

import java.util.Objects;

public class Person {

    private Long id;
    private String name;
    private String lastName;
    private Integer age;
    private Long departmentId;

    public Person() {}

    public Person(String name, String lastName, Integer age, Long departmentId) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.departmentId = departmentId;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Person person = (Person) object;
        return Objects.equals(id, person.id) && Objects.equals(name, person.name) && Objects.equals(lastName, person.lastName) && Objects.equals(age, person.age) && Objects.equals(getDepartmentId(), person.getDepartmentId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, age, getDepartmentId());
    }

    @Override
    public String toString() {
        return "Person{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", lastName='" + lastName + '\'' +
               ", age=" + age +
               ", departmentId=" + departmentId +
               '}';
    }

    public static class Builder {
        private Long id;
        private String name;
        private String lastName;
        private Integer age;
        private Long departmentId;

        public Builder() {}

        public Builder(Person person) {
            this.id = person.id;
            this.name = person.name;
            this.lastName = person.lastName;
            this.age = person.age;
            this.departmentId = person.departmentId;
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

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }

        public Builder departmentId(Long departmentId) {
            this.departmentId = departmentId;
            return this;
        }

        public Person build() {
            Person person = new Person();
            person.id = this.id;
            person.name = this.name;
            person.lastName = this.lastName;
            person.age = this.age;
            person.departmentId = this.departmentId;
            return person;
        }
    }
}
