package guru.spring.petclinic.bootstrap;

import guru.spring.petclinic.model.*;
import guru.spring.petclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if( count == 0 ){
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadilogy = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        radiology.setDescription("Surgery");
        Speciality savedSurgery= specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        radiology.setDescription("Dentistry");
        Speciality saveDentistry = specialityService.save(dentistry);

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

        Visit catVisit = new Visit();
        catVisit.setPet(nowaksPet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Snezzy Kitty");
        visitService.save(catVisit);

        Vet vet1 = new Vet();
        vet1.setFirstName("Jessie");
        vet1.setLastName("James");
        vet1.getSpecialities().add(savedRadilogy);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Walter");
        vet2.setLastName("White");
        vet2.getSpecialities().add(savedSurgery);
        vetService.save(vet2);

        System.out.println("vets loaded");
    }
}
