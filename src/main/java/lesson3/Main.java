package lesson3;

import lesson3.dao.PersonDaoJdbc;

public class Main {
    public static void main(String[] args) {
        PersonDaoJdbc personDaoJdbc = new PersonDaoJdbc();
        personDaoJdbc.createUsersTable();
    }

}
