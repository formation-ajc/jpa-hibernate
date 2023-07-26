package org.example.view;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import libraries.Scan;
import org.example.entity.User;
import org.example.repository.UserRepository;

public class ConnectionView {
    private final UserRepository userRepository;
    private final CustomerView viewCustomer;
    private final SupplierView viewSupplier;
    private boolean start;
    private User user;

    public ConnectionView() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db_config");
        this.userRepository = new UserRepository(emf);
        this.viewCustomer = new CustomerView(emf, this.userRepository);
        this.viewSupplier = new SupplierView(emf, this.userRepository);
        this.startView();
    }

    private void startView() {
        this.start = true;
        do {
            if (this.user == null){
                this.disconnectedView();
            }
            else {
                if (this.user.getCustomer() != null) {
                    this.viewCustomer.setCustomer(this.user.getCustomer());
                    this.viewCustomer.connectedView();
                    this.user = null;
                }
                else if (this.user.getSupplier() != null) {
                    this.viewSupplier.setSupplier(this.user.getSupplier());
                    this.viewSupplier.connectedView();
                    this.user = null;
                }
            }
        } while (this.start);
    }

    private void connect() {
        System.out.println("--- Connexion ---");
        System.out.println("Nom d'utilisateur");
        String username = Scan.getString();
        System.out.println("Mot de passe");
        String password = Scan.getString();

        User user = userRepository.getByUsernameAndPassword(username, password);

        if (user != null) {
            this.user = user;
            System.out.println("Connexion réussie !\n");
        }
    }

    private void disconnectedView() {
        int choice;
        do {
            System.out.println("\n--- Bienvenue ---\n");
            System.out.println("Quelle opération voulez-vous effectuer ?");
            System.out.println("1) Se connecter");
            System.out.println("2) S'inscrire en tant que client");
            System.out.println("3) S'inscrire en tant que fournisseur");
            System.out.println("4) Quitter");
            choice = Scan.getInt();

            switch (choice) {
                case 1 -> this.connect();
                case 2 -> this.viewCustomer.registerCustomer();
                case 3 -> this.viewSupplier.registerSupplier();
                case 4-> {
                    this.start = false;
                    System.out.println("--- Merci de votre visite ---");
                }
                default -> {
                }
            }
        } while (choice != 4 && this.user == null);
    }
}
