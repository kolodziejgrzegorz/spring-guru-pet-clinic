package guru.spring.petclinic.bootstrap;

import guru.spring.petclinic.model.Owner;
import guru.spring.petclinic.model.Pet;
import guru.spring.petclinic.model.PetType;
import guru.spring.petclinic.model.Vet;
import guru.spring.petclinic.services.OwnerService;
import guru.spring.petclinic.services.PetTypeService;
import guru.spring.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Dog");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Jan");
        owner1.setLastName("Kowalski");
        owner1.setAddress("ulica1 123");
        owner1.setTelephone("123456789");
        owner1.setCity("Miami");

        Pet jansPet = new Pet();
        jansPet.setPetType(savedDogPetType);
        jansPet.setBirthDate(LocalDate.now());
        jansPet.setOwner(owner1);
        jansPet.setName("Rosco");
        owner1.getPets().add(jansPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Pan");
        owner2.setLastName("Nowak");
        owner2.setCity("Warszawa");
        owner2.setTelephone("987654321");
        owner2.setAddress("ulica2 345");

        Pet nowaksPet = new Pet();
        nowaksPet.setPetType(savedCatPetType);
        nowaksPet.setBirthDate(LocalDate.now());
        nowaksPet.setOwner(owner2);
        nowaksPet.setName("cat");
        owner2.getPets().add(nowaksPet);

        ownerService.save(owner2);

        System.out.println("owners loaded");

        Vet vet1 = new Vet();
        vet1.setFirstName("Jessie");
        vet1.setLastName("James");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Walter");
        vet2.setLastName("White");
        vetService.save(vet2);

        System.out.println("vets loaded");
    }
}
