package com.example.helloworld.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Singleton;

@Singleton
public class RentalRepository {
    ConcurrentHashMap<Integer, ArrayList<String>>  branch = new ConcurrentHashMap<>();
    ConcurrentHashMap<Integer, ArrayList<String>>  vehicle = new ConcurrentHashMap<>();
    ConcurrentHashMap<Integer, ArrayList<Integer>> vehicleByBranch = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, ArrayList<Integer>>  vehicleByType = new ConcurrentHashMap<>();
    AtomicInteger branchCounter = new AtomicInteger(0);
    AtomicInteger vehicleCounter = new AtomicInteger(0);
    String[]                                       types = {"TYPE1", "TYPE2", "TYPE3"};

    public List<List<String>> getAllBranches() {
        List<List<String>> branches = new LinkedList<>();
        branches.addAll(branch.values());
        return branches;
    }

    public List<String> getBranch(Integer id) {
        return branch.get(id);
    }

    public List<String> addBranch(String name) {
        ArrayList<String> branchData = new ArrayList<>();
        Integer id = Integer.valueOf(branchCounter.incrementAndGet());
        branchData.add(id.toString());
        branchData.add(name);
        branch.put(id, branchData);
        return branchData;
    }

    public List<String> addVehicleBranch(String branchId, String price, String type) {
        ArrayList<String> vehicleData = new ArrayList<>();
        Integer id = Integer.valueOf(vehicleCounter.incrementAndGet());
        vehicleData.add(id.toString());
        vehicleData.add(price);
        vehicleData.add(type);
        vehicleData.add(branchId);
        vehicleData.add("true");
        vehicleData.add("000000000000000000000000");
        vehicle.put(id, vehicleData);
        vehicleByBranch.computeIfAbsent(Integer.parseInt(branchId), k -> new ArrayList<>()).add(id);
        vehicleByType.computeIfAbsent(type, k -> new ArrayList<>()).add(id);
        return vehicleData;
    }

    public List<List<String>> getVehiclesBranch(Integer branchId) {
        List<List<String>> vehicles = new LinkedList<>();
        for (Integer id : vehicleByBranch.get(branchId)) {
            vehicles.add(vehicle.get(id));
        }
        return vehicles;
    }

    public PriorityQueue<ArrayList<String>> orderedVehicles(ArrayList<Integer> vehicles, Integer orderingKey) {
        PriorityQueue<ArrayList<String>> pq = new PriorityQueue<>((a,b) -> Integer.valueOf(a.get(orderingKey))
                                                                                  .compareTo(Integer.valueOf(b.get(orderingKey))));
        for (Integer id : vehicles) {
            ArrayList<String> data = vehicle.get(id);
            pq.offer(data);
        }
        return pq;
    }

    public List<String> rent(String type, Integer slot, Integer orderingKey) {
        List<String> rentedVehicle = new LinkedList<>();
        ArrayList<Integer> availableVehicles = vehicleByType.get(type);
        PriorityQueue<ArrayList<String>> prioritizedVehicles = orderedVehicles(availableVehicles, orderingKey);
        while(!prioritizedVehicles.isEmpty()) {
            ArrayList<String> data = prioritizedVehicles.poll();
            if (data.get(data.size() - 1).charAt(slot) != '1') {
                rentedVehicle.addAll(data);
                StringBuilder sb = new StringBuilder(data.get(data.size() - 1));
                sb.setCharAt(slot, '1');
                data.set(data.size() - 1, sb.toString());
                data.set(data.size() - 2, "false");
                break;
            }
        }
        return rentedVehicle;
    }


}
