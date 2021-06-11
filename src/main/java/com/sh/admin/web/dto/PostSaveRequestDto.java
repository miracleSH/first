package com.sh.admin.web.dto;

import com.sh.admin.web.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {
  private String title;
  private String content;
  private String author;

  @Builder
  public PostSaveRequestDto(String title, String content, String author){
    this.title = title;
    this.content = content;
    this.author = author;
  }

  public Posts toEntity(){
    return Posts.builder()
        .title(title)
        .author(author)
        .content(content)
        .build();
  }
}
