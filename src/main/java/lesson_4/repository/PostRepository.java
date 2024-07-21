package lesson_4.repository;

import lesson_4.model.Post;
import lesson_4.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PostRepository {

    final SessionFactory sessionFactory;

    public PostRepository() {
        this.sessionFactory = Util.getSessionFactory();
    }

    public void savePost(String title) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Post post = new Post();
            post.setTitle(title);
            session.persist(post);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Post> getAllPosts() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Post", Post.class).list();
        }
    }

    public Post getPostById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Post.class, id);
        }
    }

    public void deletePost(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Post post = session.get(Post.class, id);
            if (post != null) {
                session.delete(post);
            }
            transaction.commit();
        }
    }

}
