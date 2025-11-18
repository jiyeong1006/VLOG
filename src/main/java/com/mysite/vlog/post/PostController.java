package com.mysite.vlog.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;



    @GetMapping("/post/write")
    public String write() {
        return "post_write";
    }

    @PostMapping("/post/write")
    public String writePost(String title, String content) {
        postService.write(title, content);
        return "redirect:/post/list";
    }

    @GetMapping("/post/detail")
    public String detail(Long id, Model model) {
    	Post post = postService.getPost(id);
    	model.addAttribute("post",post);
        return "post_detail";
    }
    
    @GetMapping("/post/delete")
    public String delete(Long id) {
    	postService.delete(id);
    	return "redirect:/post/list";
    }
    
    @GetMapping("/post/modify")
    public String modify(Long id,Model model) {
    	Post post = postService.getPost(id);
    	model.addAttribute("post",post);
    	return "post_modify";
    }
    
    @PostMapping("/post/modify")
    public String modifyPost(Long id, String title, String content) {
    	postService.modify(id, title, content);
    	return "redirect:/post/detail?id="+id;
    }
    
    @GetMapping("/post/list")
    public String list(@RequestParam(value="keyword",required = false) 
    String keyword, Model model) {
    	List<Post> postList;
    	if(keyword != null && !keyword.isEmpty()) {
    		postList = postService.search(keyword);
    	}else{
    		postList = postService.getList();
    	}
    	
    	model.addAttribute("postList",postList);
    	model.addAttribute("keyword",keyword);
    	return "post_list";
    }
}
