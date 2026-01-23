package car.tp1.repositories;

import car.tp1.models.LigneCommande;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigneCommandeRepository extends CrudRepository<LigneCommande, String> {
}
