package lesson_4;

import lesson_4.repository.PostRepository;

public class Main {
    public static void main(String[] args) {

        PostRepository repository = new PostRepository();
        repository.savePost("test");
    }
}
