package lesson_4;

import lesson_4.repository.PostRepository;

public class Main {
    public static void main(String[] args) {

        PostRepository postRepository = new PostRepository();
        postRepository.savePost("test");
        postRepository.savePost("test_2");
        System.out.println(postRepository.getAllPosts());

    }
}
