package com.sh.admin.web;

import com.sh.admin.web.domain.posts.PostService;
import com.sh.admin.web.domain.posts.Posts;
import com.sh.admin.web.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostService postService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts", postService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("posts/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model){
        PostResponseDto responseDto = postService.findById(id);
        model.addAttribute("post", responseDto);

        return "post-update";
    }
}
