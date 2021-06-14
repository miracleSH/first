package com.sh.admin.web;

import com.sh.admin.config.auth.LoginUser;
import com.sh.admin.config.auth.dto.SessionUser;
import com.sh.admin.web.domain.posts.PostService;
import com.sh.admin.web.domain.posts.Posts;
import com.sh.admin.web.dto.PostResponseDto;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostService postService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postService.findAllDesc());

        if(user != null){
            model.addAttribute("userName", user.getName());
        }

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
