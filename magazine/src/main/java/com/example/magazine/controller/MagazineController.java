package com.example.magazine.controller;

import com.example.magazine.Iservice.IMagazine;
import com.example.magazine.model.Magazine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MagazineController {
    private final IMagazine iMagazine;
    public MagazineController(IMagazine iMagazine) {
        super();
        this.iMagazine = iMagazine;
    }

    @GetMapping("/magazines")
    public String listMagazines(Model model) {
        model.addAttribute("magazines", iMagazine.getAllMagazines());
        return "magazines";
    }

    @GetMapping("/magazines/new")
    public String createMagazine(Model model) {
        Magazine magazine = new Magazine();
        model.addAttribute("magazine", magazine);
        return "create_magazine";
    }

    @PostMapping("/magazines")
    public String addMagazine(@ModelAttribute("magazine") Magazine magazine) {
        iMagazine.saveMagazine(magazine);
        return "redirect:/magazines";
    }

    @GetMapping("/magazines/edit/{id}")
    public String editMagazine(@PathVariable Long id, Model model) {
        model.addAttribute("magazine", iMagazine.getMagazineById(id));
        return "edit_magazine";
    }

    @PostMapping("/magazines/{id}")
    public String updateMagazine(@PathVariable Long id, @ModelAttribute("magazine") Magazine magazine) {
        Magazine existingMagazine = iMagazine.getMagazineById(id);
        existingMagazine.setId(id);
        existingMagazine.setName(magazine.getName());
        existingMagazine.setDescription(magazine.getDescription());
        existingMagazine.setPrice(magazine.getPrice());

        iMagazine.updateMagazine(existingMagazine);
        return "redirect:/magazines";
    }

    @GetMapping("/magazines/{id}")
    public String deleteMagazine(@PathVariable Long id) {
        iMagazine.deleteMagazineById(id);
        return "redirect:/magazines";
    }
}
