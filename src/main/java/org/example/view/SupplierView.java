package org.example.view;

import jakarta.persistence.EntityManagerFactory;
import libraries.Scan;
import org.example.entity.Supplier;
import org.example.entity.User;
import org.example.repository.SupplierRepository;
import org.example.repository.UserRepository;

public class SupplierView {
    private final EntityManagerFactory emf;
    private final UserRepository userRepository;
    private final SupplierRepository supplierRepository;
    private Supplier supplier;

    public SupplierView(EntityManagerFactory emf, UserRepository userRepository) {
        this.emf = emf;
        this.userRepository = userRepository;
        this.supplierRepository = new SupplierRepository(emf);
    }

    public void registerSupplier() {
        System.out.println("--- Inscription fournisseur ---");
        System.out.println("Nom fournisseur");
        String username = Scan.getString();
        System.out.println("Mot de passe");
        String password = Scan.getString();
        System.out.println("Confirmation du mot de passe");
        String passwordConfirmation = Scan.getString();

        if (username.length() > 0 && password.length() > 0) {
            if (passwordConfirmation.equals(password)) {

                User user = this.userRepository.getByUsername(username);

                if (user == null) {
                    this.supplierRepository.create(new Supplier(
                            username,
                            this.userRepository.create(new User(username, password))
                    ));
                } else {
                    System.out.println("!!! Ce fournisseur existe déjà !!!");
                }
            } else {
                System.out.println("!!! Les mots de passe doivent être identiques !!!");
            }
        }
        else {
            System.out.println("!!! Les champs sont obligatoires !!!");
        }
    }

    public void connectedView() {
        int choice;
        do {
            System.out.println("\n--- Bonjour, " + this.supplier.getName() + " ---\n");
            System.out.println("Quelle opération voulez-vous effectuer ?");
            System.out.println("1) Quel fournisseur suis-je ?");
            System.out.println("2) Produits");
            System.out.println("3) Se déconnecter");
            choice = Scan.getInt();

            switch (choice) {
                case 1 -> System.out.println(this.supplier);
                case 2 -> new ProductView(this.emf, this.supplier);
                case 3 -> this.supplier = null;
                default -> {
                }
            }
        } while (choice != 3 && this.supplier != null);
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
