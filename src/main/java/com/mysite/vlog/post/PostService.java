package com.mysite.vlog.post;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void write(String title, String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCreateDate(LocalDateTime.now());

        postRepository.save(post);
    }

    public List<Post> getList() {
        return postRepository.findAll();
    }

    // ★★★ 여기 추가!! ★★★
    public Post getPost(Long id) {
        return postRepository.findById(id).orElse(null);
    }
    
    public void delete(Long id) {
    	postRepository.deleteById(id);
    }
    
    public void modify(Long id, String title, String content) {
    	Post post = getPost(id);
    	post.setTitle(title);
    	post.setContent(content);
    	post.setModifyDate(LocalDateTime.now());
    	postRepository.save(post);
    }
    
    public List<Post> search(String keyword){
    	return postRepository.findByTitleContainingOrContentContaining(keyword, keyword);
    }
    
}
