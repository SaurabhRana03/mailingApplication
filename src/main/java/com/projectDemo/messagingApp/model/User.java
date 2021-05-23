package com.projectDemo.messagingApp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class User {
    private String emailAddress;

}