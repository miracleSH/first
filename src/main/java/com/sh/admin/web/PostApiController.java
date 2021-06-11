package com.sh.admin.web;

import com.sh.admin.web.domain.posts.PostService;
import com.sh.admin.web.domain.posts.Posts;
import com.sh.admin.web.dto.PostResponseDto;
import com.sh.admin.web.dto.PostSaveRequestDto;
import com.sh.admin.web.dto.PostsUpdateRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostApiController {

  private final PostService postService;

  @ResponseBody
  @PostMapping( value = "/api/v1/posts", consumes = {"application/json"})
  public PostResponseDto save(@RequestBody PostSaveRequestDto postSaveRequestDto){
    postService.save(postSaveRequestDto);
    System.out.println("저장 완료");

    PostResponseDto responseDto = new PostResponseDto(Posts.builder()
        .title(postSaveRequestDto.getTitle())
        .content(postSaveRequestDto.getContent())
        .author(postSaveRequestDto.getAuthor()).build());

    return responseDto;
  }

  @PutMapping("/api/v1/posts/{id}")
  public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto postsUpdateRequestDto){
    return postService.update(id, postsUpdateRequestDto);
  }

  @GetMapping("/api/v1/posts/{id}")
  public PostResponseDto findById(@PathVariable Long id){
    return postService.findById(id);
  }

  @DeleteMapping("/api/v1/posts/{id}")
  public Long delete(@PathVariable Long id){
    postService.delete(id);
    return id;
  }
}
