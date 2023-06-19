package com.mongoDB.crud.model;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;


@Document
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private int userId;
    private String password;
    private String number;
    private String email;
    private String firstName;
    private String lastName;
}
