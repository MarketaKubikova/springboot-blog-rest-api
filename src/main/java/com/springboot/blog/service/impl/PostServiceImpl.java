package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post = mapToEntity(postDTO);
        Post newPost = postRepository.save(post);

        return mapToDTO(newPost);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostById(Long id) {
        Post post = getPost(id);

        return mapToDTO(post);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Long id) {
        Post post = getPost(id);

        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());

        Post updatedPost = postRepository.save(post);

        return mapToDTO(updatedPost);
    }

    @Override
    public void deletePostById(Long id) {
        Post post = getPost(id);
        postRepository.delete(post);
    }

    private PostDTO mapToDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setDescription(post.getDescription());
        postDTO.setContent(post.getContent());

        return postDTO;
    }

    private Post mapToEntity(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(post.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());

        return post;
    }

    private Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    }
}
