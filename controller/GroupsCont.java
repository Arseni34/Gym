package com.gym.controller;

import com.gym.controller.main.Attributes;
import com.gym.model.Groups;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/groups")
public class GroupsCont extends Attributes {
    @GetMapping
    public String groups(Model model) {
        AddAttributes(model);
        model.addAttribute("trainers", trainersRepo.findAll());
        model.addAttribute("groups", groupsRepo.findAll());
        return "groups";
    }

    @GetMapping("/{id}/delete")
    public String groupDelete(@PathVariable Long id) {
        groupsRepo.deleteById(id);
        return "redirect:/groups";
    }

    @PostMapping("/add")
    public String groupAdd(Model model, @RequestParam String name, @RequestParam String dateAndTime, @RequestParam Long trainerId, @RequestParam MultipartFile file) {
        String res = "";
        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            boolean createDir = true;
            try {
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) createDir = uploadDir.mkdir();
                if (createDir) {
                    res = "subs/" + uuidFile + "_" + file.getOriginalFilename();
                    file.transferTo(new File(uploadImg + "/" + res));
                }
            } catch (Exception e) {
                model.addAttribute("message", "Некорректный данные!");
                AddAttributes(model);
                model.addAttribute("trainers", trainersRepo.findAll());
                model.addAttribute("groups", groupsRepo.findAll());
                return "groups";
            }
        }

        groupsRepo.save(new Groups(dateAndTime, name, res, trainersRepo.getReferenceById(trainerId)));

        return "redirect:/groups";
    }
}