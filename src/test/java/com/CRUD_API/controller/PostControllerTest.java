package com.CRUD_API.controller;

import com.CRUD_API.payload.PostDto;
import com.CRUD_API.service.PostService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PostControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    public PostControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void savePost() {
        // Prepare
        PostDto postDto = new PostDto();
        when(postService.savePost(any(PostDto.class))).thenReturn(postDto);

        // Perform
        ResponseEntity<PostDto> responseEntity = postController.savePost(postDto);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(postDto, responseEntity.getBody());
    }

    @Test
    void deletePostById() {
        // Prepare
        long id = 1L;

        // Perform
        ResponseEntity<String> responseEntity = postController.deletePostById(id);

        // Assert
        verify(postService).deletePostById(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Post is deleted", responseEntity.getBody());
    }

    @Test
    void updatePost() {
        // Prepare
        long id = 1L;
        PostDto postDto = new PostDto();
        when(postService.updatePost(eq(id), any(PostDto.class))).thenReturn(postDto);

        // Perform
        ResponseEntity<PostDto> responseEntity = postController.updatePost(id, postDto);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(postDto, responseEntity.getBody());
    }

    @Test
    void getPostById() {
        // Prepare
        long id = 1L;
        PostDto postDto = new PostDto();
        when(postService.getPostById(id)).thenReturn(postDto);

        // Perform
        ResponseEntity<PostDto> responseEntity = postController.getPostById(id);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(postDto, responseEntity.getBody());
    }
}

