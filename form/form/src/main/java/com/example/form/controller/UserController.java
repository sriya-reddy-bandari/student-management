package com.example.form.controller;

import java.util.HashMap;


import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.security.core.userdetails.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.form.Jwt.JwtUtils;
import com.example.form.model.Category;
import com.example.form.model.LoginRequest;
import com.example.form.model.LoginResponse;
import com.example.form.model.Products;
import com.example.form.model.Student;
import com.example.form.model.UserData;
import com.example.form.model.UserSignIn;
import com.example.form.services.CategoryService;
import com.example.form.services.ProductService;
import com.example.form.services.StudentService;
import com.example.form.services.UserService;

import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/form")
public class UserController {

    @Autowired 
    AuthenticationManager authenticationManager;

    @Autowired
    DataSource dataSource;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils JwtUtils;

    @Autowired
    UserService userService;

    @Autowired
    StudentService studentService;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<String> saveFormdata(@RequestBody UserData userdata) {
        userService.saveFormdata(userdata);
        return ResponseEntity.ok("User saved");
    }

    @GetMapping("/all")
    public List<UserData> getAllFormData() {
        return userService.getAllFormData();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserData> getByIdFormData(@PathVariable Long id) {
        UserData data = userService.getByID(id);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<UserData> updateUser(@PathVariable Long id, @RequestBody UserData userdata) {
        UserData data = userService.updateUserData(id, userdata);
        return new ResponseEntity<>(data, HttpStatus.OK);

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUserData(id);
        return ResponseEntity.ok("User deleted ");
    }

    @PostMapping("/login_in")
    public ResponseEntity<Object> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (AuthenticationException exception) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Bad Credentials");
            map.put("status", false);
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwtToken = JwtUtils.generateTokenFromUsername(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .toList();

        LoginResponse response = new LoginResponse(jwtToken, userDetails.getUsername(), roles);

        return ResponseEntity.ok(response);

    }

    @PostMapping("/sign_in")
    public String createUser(@RequestBody UserSignIn userSignin) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

        if (userDetailsManager.userExists(userSignin.getUsername())) {
            return "User already exists";
        }

        UserDetails user = User.withUsername(userSignin.getUsername())
                .password(passwordEncoder.encode(userSignin.getPassword()))
                .roles(userSignin.getRole())
                .build();

        userDetailsManager.createUser(user);
        return "User created Successfully";

    }

    @PostMapping("/saveStudent")
    public ResponseEntity<String> saveStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
        return ResponseEntity.ok("Student saved");
    }

    @GetMapping("/allStudents")
    public List<Student> getAllStudents() {
        return studentService.getStudents();
    }

    @PostMapping("/saveProduct")
    public ResponseEntity<String> saveProduct(@RequestBody Products products) {
        productService.saveProducts(products);
        return ResponseEntity.ok("Product saved");
    }

    @GetMapping("/allProducts")
    public List<Products> getAllProducts() {
        return productService.getProducts();
    }

    @PostMapping("/saveCategory")
    public ResponseEntity<String> saveCategory(@RequestBody Category category) {
        categoryService.saveCategory(category);
        return ResponseEntity.ok("Category saved");
    }

    @GetMapping("/allCategories")
    public List<Category> getAllCategories() {
        return categoryService.getCategories();
    }

}