package com.mysite.vlog.post;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByTitleContainingOrContentContaining(String title, String content);
}
