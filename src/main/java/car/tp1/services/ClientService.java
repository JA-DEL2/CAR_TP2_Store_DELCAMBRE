package car.tp1.services;

import car.tp1.models.Client;
import car.tp1.models.Commande;
import car.tp1.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client createClient(String email, String nom, String prenom, String mdp) {
        boolean clientFound = (this.clientRepository.findByEmailIgnoreCase(email) == null);
        if(clientFound)
            return this.clientRepository.save(new Client(email, nom, prenom, mdp));
        return null;
    }

    public Client loginClient(String email, String mdp) {
        Client clientFound = this.clientRepository.findByEmailAndMdp(email,mdp);
        return clientFound;
    }

    public Iterable<Client> getAllClients() {
        return this.clientRepository.findAll();
    }

    public void addCommande(Commande commande, String email) {
        Client client = this.clientRepository.findByEmailIgnoreCase(email);
        if(client != null) {
            client.addCommande(commande);
            this.clientRepository.save(client);
        }
    }

    public List<Commande> getCommandesByClientId(String email){
        Client client = this.clientRepository.findByEmailIgnoreCase(email);
        if(client != null) {
            return client.getCommandes();
        }
        return new ArrayList<>();
    }

}
