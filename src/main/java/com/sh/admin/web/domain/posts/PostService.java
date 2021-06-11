package com.sh.admin.web.domain.posts;

import com.sh.admin.web.dto.PostResponseDto;
import com.sh.admin.web.dto.PostSaveRequestDto;
import com.sh.admin.web.dto.PostsListResponseDto;
import com.sh.admin.web.dto.PostsUpdateRequestDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {
  private final PostsRepository postsRepository;

  @Transactional
  public Long save(PostSaveRequestDto postSaveRequestDto){
     return postsRepository.save(postSaveRequestDto.toEntity()).getId();
  }

  @Transactional
    public Long update(Long id, PostsUpdateRequestDto postsUpdateRequestDto){
      Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다. id=" + id));

      posts.update(postsUpdateRequestDto.getTitle(), postsUpdateRequestDto.getContent()); //repository를 통해서 update하지 않은 이유: Post가 Entity객체이기 때문에 스프링의 Entity
                                                                                          //매니저에 의해 영속성이 유지된다. 따라서 Entity객체에 변화가 있을 경우 이를 감지해 자동으로 적용을 한다.

      return id;
  }

  @Transactional
    public PostResponseDto findById(Long id){
      Posts entity = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 글이 없습니다. id=" + id));

      return new PostResponseDto(entity);
  }

  @Transactional(readOnly = true)
  public List<PostsListResponseDto> findAllDesc(){
    return postsRepository.findAllDesc().stream()
        .map(PostsListResponseDto :: new)
        .collect(Collectors.toList());
  }

  @Transactional
  public void delete(Long id){
    Posts post = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("글이 존재하지 않습니다."));
    postsRepository.delete(post);
  }
}
