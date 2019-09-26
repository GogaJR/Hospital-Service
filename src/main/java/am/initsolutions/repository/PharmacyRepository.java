package am.initsolutions.repository;

import am.initsolutions.models.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
    Pharmacy findByNameAndAddress(String name, String address);
}
