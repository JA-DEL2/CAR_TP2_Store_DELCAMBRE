package car.tp1.repositories;

import car.tp1.models.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, String> {

    public Client findByEmailIgnoreCase(String email);
    public Client findByEmailAndMdp(String email, String password);
}
