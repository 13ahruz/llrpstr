package com.example.magazine.service;

import com.example.magazine.Iservice.IMagazine;
import com.example.magazine.model.Magazine;
import com.example.magazine.repo.MagazineRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MagazineService implements IMagazine {
    private final MagazineRepo magazineRepo;

    public MagazineService(MagazineRepo magazineRepo) {
        this.magazineRepo = magazineRepo;
    }
    @Override
    public List<Magazine> getAllMagazines(){
        return magazineRepo.findAll();
    }

    @Override
    public Magazine saveMagazine(Magazine magazine){
        return magazineRepo.save(magazine);
    }
    @Override
    public Magazine getMagazineById(Long id){
        return magazineRepo.findById(id).get();
    }

    @Override
    public Magazine updateMagazine(Magazine magazine){
        return magazineRepo.save(magazine);
    }

    @Override
    public void deleteMagazineById(Long id){
        magazineRepo.deleteById(id);
    }
}
