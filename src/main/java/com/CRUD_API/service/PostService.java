package com.CRUD_API.service;

import com.CRUD_API.payload.PostDto;

public interface PostService {
    PostDto savePost(PostDto postDto);

    void deletePostById(long id);

    PostDto updatePost(long id, PostDto postDto);

    PostDto getPostById(long id);
}
