package com.CRUD_API.service.impl;

import com.CRUD_API.entity.Post;
import com.CRUD_API.exception.ResourceNotFound;
import com.CRUD_API.payload.PostDto;
import com.CRUD_API.repository.PostRepository;
import com.CRUD_API.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepo;

    public PostServiceImpl(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public PostDto savePost(PostDto postDto) {
        Post post = mapToEntity(postDto);

        Post savedPost = postRepo.save(post);

        PostDto dto = mapToDto(savedPost);

        return dto;
    }

    @Override
    public void deletePostById(long id) {
        postRepo.deleteById(id);
    }

    @Override
    public PostDto updatePost(long id, PostDto postDto) {
        Post post = postRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFound("Post not found with id:"+id)
        );
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        PostDto dto = mapToDto(post);

        return dto;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFound("Post not found with id:"+id)
        );
        PostDto dto = mapToDto(post);

        return dto;
    }

    Post mapToEntity(PostDto postDto){
        Post post = new Post();

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(post.getContent());

        return post;
    }

    PostDto mapToDto(Post post){
        PostDto dto = new PostDto();

        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());

        return dto;
    }
}
