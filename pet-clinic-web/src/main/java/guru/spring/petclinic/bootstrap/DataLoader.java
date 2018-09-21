package guru.spring.petclinic.bootstrap;

import guru.spring.petclinic.model.Owner;
import guru.spring.petclinic.model.Vet;
import guru.spring.petclinic.services.OwnerService;
import guru.spring.petclinic.services.VetService;
import guru.spring.petclinic.services.map.OwnerServiceMap;
import guru.spring.petclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        this.ownerService = new OwnerServiceMap();
        this.vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Jan");
        owner1.setLastName("Kowalski");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Pan");
        owner2.setLastName("Nowak");
        ownerService.save(owner2);

        System.out.println("owners loaded");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Jessie");
        vet1.setLastName("James");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Walter");
        vet2.setLastName("White");
        vetService.save(vet2);

        System.out.println("vets loaded");
    }
}