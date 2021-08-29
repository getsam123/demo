package com.example.helloworld.resources;

import com.example.helloworld.api.Branch;
import com.example.helloworld.api.RentedVehicle;
import com.example.helloworld.api.Vehicle;
import com.example.helloworld.db.BranchDAO;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/branch")
@Produces(MediaType.APPLICATION_JSON)
public class BranchResource {

    @Inject
    private BranchDAO dao;

    @GET
    public List<Branch> getAllBranches() {
        List<Branch> branches = new LinkedList<>();
        for (List<String> branch : dao.getAllBranches()) {
            branches.add(new Branch(branch));
        }
        return branches;
    }

    @POST
    public Branch addBranch(@QueryParam("branchName") String branchName) {
        List<String> branch = dao.addBranch(branchName);
        return new Branch(branch);
    }

    @POST
    @Path("/vehicle")
    public Vehicle addVehicleBranch(Vehicle vehicle) {
        List<String> vehicleData = dao.addVehicleBranch(vehicle.getBranchId().toString(),
                                                        vehicle.getPrice().toString(),
                                                        vehicle.getVehicleType());
        return new Vehicle(vehicleData.get(3),vehicleData.get(1),vehicleData.get(2));
    }

    @GET
    @Path("/{branchId}/vehicle")
    public List<Vehicle> getBranchVehicles(@PathParam("branchId") Integer branchId) {
        List<Vehicle> vehicles = new LinkedList<>();
        for (List<String> vehicle : dao.getVehiclesBranch(branchId)) {
            vehicles.add(new Vehicle(vehicle.get(3), vehicle.get(1), vehicle.get(2)));
        }
        return vehicles;
    }

    @POST
    @Path("/rent")
    public RentedVehicle rent(@QueryParam("type") String type, @QueryParam("slot") Integer slot) {
        List<String> rentedVehicle = dao.rent(type, slot, 1);
        return new RentedVehicle(new Vehicle(rentedVehicle.get(3),
                                             rentedVehicle.get(1),
                                             rentedVehicle.get(2)), slot);
    }

}
