package com.example.helloworld.db;

import com.example.helloworld.core.RentalRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BranchDAO {
    @Inject
    private RentalRepository repository;

    public List<List<String>> getAllBranches(){
        return repository.getAllBranches();
    }

    public List<String> getBranch(Integer id) {
        return repository.getBranch(id);
    }

    public List<String> addBranch(String branchName) {
        return repository.addBranch(branchName);
    }

    public List<String> addVehicleBranch(String branchId, String price, String type) {
        return repository.addVehicleBranch(branchId, price, type);
    }

    public List<List<String>> getVehiclesBranch(Integer branchId) {
        return repository.getVehiclesBranch(branchId);
    }

    public List<String> rent(String type, Integer slot, Integer orderingKey) {
        return repository.rent(type, slot, orderingKey);
    }
}
