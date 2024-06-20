package lesson1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StreamDemo {

    private static <T> T getRandom(List<? extends T> items) {
        int randomIndex = ThreadLocalRandom.current().nextInt(0, items.size());
        return items.get(randomIndex);
    }

    static class Book {
        private String name;
        private String author;
        private LocalDate date;
        private int pages;
        private double price;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "Book{" +
                   "name='" + name + '\'' +
                   ", author='" + author + '\'' +
                   ", date=" + date +
                   ", pages=" + pages +
                   ", price=" + price +
                   '}';
        }
    }

    public static void main(String[] args) {
        List<String> authors = List.of("Tolstoy", "Bulgakov", "Desteevski", "Gogol", "Martin",
                "Hugo", "Duma");

        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Book book = new Book();
            book.setName("Book# " + i);
            book.setPages(ThreadLocalRandom.current().nextInt(100, 2500));
            book.setAuthor(getRandom(authors));
            book.setDate(LocalDate.now().minusYears(ThreadLocalRandom.current().nextLong(100)));
            book.setPrice(ThreadLocalRandom.current().nextInt(1_000, 100_000) * 1.0);

            books.add(book);
        }

//        System.out.println(MostExpensiveBook(books));
        printBooksWithLessPages(books);
    }

    // найти самую дорогую книгу
    static Book MostExpensiveBook(List<Book> books) {
        return books.stream()
                .max(Comparator.comparing(Book::getPrice)).get();

    }


    // топ 5 самых коротких книг
    static void printBooksWithLessPages(List<Book> books) {
        books.stream()
                .sorted(Comparator.comparing(Book::getPages))
                .limit(5)
                .forEach(System.out::println);
    }

}

//    public static void main(String[] args) {
//        List<String> lang = List.of("java", "c#", "c++", "python", "kotlin", "go",
//                "assembler", "pascal", "rust", "javascript", "ruby", "php", "delphi");
//
//        lang.stream()
//                .filter(s -> s.length() > 4)
//                .map(s -> s.toUpperCase())
//                .forEach(System.out::println);
//
//
//        String joining = lang.stream()
//                .reduce("START: ", (a, b) -> a + " " + b);
//        System.out.println(joining);
//
//        // START:  java c# c++ python kotlin go assembler pascal rust javascript ruby php delphi
//    }