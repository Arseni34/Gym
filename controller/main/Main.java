package com.gym.controller.main;

import com.gym.model.Users;
import com.gym.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;

public class Main {
    @Autowired
    protected StaticsRepo staticsRepo;
    @Autowired
    protected SubsRepo subsRepo;
    @Autowired
    protected TrainersRepo trainersRepo;
    @Autowired
    protected UsersRepo usersRepo;
    @Autowired
    protected PurchasedRepo purchasedRepo;
    @Autowired
    protected GroupsRepo groupsRepo;
    @Autowired
    protected ChatRepo chatRepo;

    @Value("${upload.img}")
    protected String uploadImg;

    protected Users getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            return usersRepo.findByUsername(userDetail.getUsername());
        }
        return null;
    }

    protected String getRole() {
        Users users = getUser();
        if (users == null) return "NOT";
        return users.getRole().toString();
    }

    public static String getDate() {
        return LocalDateTime.now().toString().substring(0, 10);
    }

    public static String getDateAndTime() {
        String date = LocalDateTime.now().toString();
        return date.substring(0, 10) + ' ' + date.substring(11, 15);
    }
}