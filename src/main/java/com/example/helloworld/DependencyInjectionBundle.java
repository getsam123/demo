package com.example.helloworld;

import com.example.helloworld.core.RentalRepository;
import com.example.helloworld.db.BranchDAO;
import com.example.helloworld.resources.BranchResource;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

import io.dropwizard.ConfiguredBundle;
import io.dropwizard.setup.Environment;

public class DependencyInjectionBundle implements ConfiguredBundle<DependencyConfig> {

    @Override
    public void run(DependencyConfig configuration, Environment environment) throws Exception {
        environment.jersey().register(
                new AbstractBinder() {
                    @Override
                    protected void configure() {
                        bindAsContract(BranchDAO.class).in(Singleton.class);
                        bindAsContract(RentalRepository.class).in(Singleton.class);
                    }
                }
        );
    }
}
