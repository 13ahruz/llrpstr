package com.example.magazine.Iservice;

import com.example.magazine.model.Magazine;

import java.util.List;

public interface IMagazine {
    List<Magazine> getAllMagazines();

    Magazine saveMagazine(Magazine magazine);

    Magazine getMagazineById(Long id);

    Magazine updateMagazine(Magazine magazine);

    void deleteMagazineById(Long id);
}
