package com.example.demo.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/middle")
@CrossOrigin(origins = "http://localhost:4200")
public class MiddleController {

    private static final String URL = "http://localhost:8082/api/form";

    @Autowired
    private RestTemplate restTemplate;

    private HttpHeaders getHeaderWithToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;

    }

    @GetMapping("/all")
    public ResponseEntity<List<Object>> getAllUsers(HttpServletRequest request) {
        HttpEntity<String> entity = new HttpEntity<>(getHeaderWithToken(request));
        return restTemplate.exchange(
                URL + "/all",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Object>>() {

                });

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id, HttpServletRequest request) {
        HttpEntity<String> entity = new HttpEntity<>(getHeaderWithToken(request));
        try {
            return restTemplate.exchange(
                    URL + "/get/" + id,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<Object>() {
                    });
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(e.getResponseBodyAsString());

        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@RequestBody Object userData, HttpServletRequest request) {
        HttpEntity<Object> entity = new HttpEntity<>(userData, getHeaderWithToken(request));
        restTemplate.exchange(URL + "/save", HttpMethod.POST, entity, String.class);
        return ResponseEntity.ok("User saved ");

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody Object userData,
            HttpServletRequest request) {
        HttpEntity<Object> entity = new HttpEntity<>(userData, getHeaderWithToken(request));
        try {
            return restTemplate.exchange(URL + "/update/" + id, HttpMethod.PUT, entity, Object.class);

        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(e.getResponseBodyAsString());

        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id, HttpServletRequest request) {
        HttpEntity<String> entity = new HttpEntity<>(getHeaderWithToken(request));
        restTemplate.exchange(URL + "/delete/" + id, HttpMethod.DELETE, entity, String.class);
        return ResponseEntity.ok("User deleted successfully");

    }

 
    @PostMapping("sign_in")
    public ResponseEntity<String> createUsers(@RequestBody Map<String, String> userData) {
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(userData);

        return restTemplate.exchange(
                URL + "/sign_in",
                HttpMethod.POST,
                entity,
                String.class);
    }

    @PostMapping("login_in")
    public ResponseEntity<Object> loginIn(@RequestBody Map<String, String> loginRequest) {
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(loginRequest);

        try {
            return restTemplate.exchange(
                    URL + "/login_in",
                    HttpMethod.POST,
                    entity,
                    Object.class);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(e.getResponseBodyAsString());
        }
    }


    @PostMapping("/saveStudent")
    public ResponseEntity<String> saveStudent(@RequestBody Object user, HttpServletRequest request) {
        HttpEntity<Object> entity = new HttpEntity<>(user, getHeaderWithToken(request));
        restTemplate.exchange(URL + "/saveStudent", HttpMethod.POST, entity, String.class);
        return ResponseEntity.ok("Student saved ");

    }

    @GetMapping("/allStudents")
    public ResponseEntity<List<Object>> getAllStudents(HttpServletRequest request) {
        HttpEntity<String> entity = new HttpEntity<>(getHeaderWithToken(request));
        return restTemplate.exchange(
                URL + "/allStudents",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Object>>() {

                });

    }

    @PostMapping("/saveProduct")
    public ResponseEntity<String> saveProduct(@RequestBody Object user, HttpServletRequest request) {
        HttpEntity<Object> entity = new HttpEntity<>(user, getHeaderWithToken(request));
        restTemplate.exchange(URL + "/saveProduct", HttpMethod.POST, entity, String.class);
        return ResponseEntity.ok("Product saved ");

    }

    @GetMapping("/allProducts")
    public ResponseEntity<List<Object>> getAllProducts(HttpServletRequest request) {
        HttpEntity<String> entity = new HttpEntity<>(getHeaderWithToken(request));
        return restTemplate.exchange(
                URL + "/allProducts",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Object>>() {

                });

    }

    @PostMapping("/saveCategory")
    public ResponseEntity<String> saveCategory(@RequestBody Object user, HttpServletRequest request) {
        HttpEntity<Object> entity = new HttpEntity<>(user, getHeaderWithToken(request));
        restTemplate.exchange(URL + "/saveCategory", HttpMethod.POST, entity, String.class);
        return ResponseEntity.ok("Category saved ");

    }

    @GetMapping("/allCategories")
    public ResponseEntity<List<Object>> getAllCategories(HttpServletRequest request) {
        HttpEntity<String> entity = new HttpEntity<>(getHeaderWithToken(request));
        return restTemplate.exchange(
                URL + "/allCategories",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Object>>() {

                });

    }

}
