package org.example.view;

import jakarta.persistence.EntityManagerFactory;
import libraries.Scan;
import org.example.entity.Customer;
import org.example.entity.User;
import org.example.repository.CustomerRepository;
import org.example.repository.UserRepository;

public class CustomerView {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private Customer customer;

    public CustomerView(EntityManagerFactory emf, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.customerRepository = new CustomerRepository(emf);
    }

    public void registerCustomer() {
        System.out.println("--- Inscription client ---");
        System.out.println("Prénom");
        String firstname = Scan.getString();
        System.out.println("Nom");
        String lastname = Scan.getString();
        System.out.println("Mot de passe");
        String password = Scan.getString();
        System.out.println("Confirmation du mot de passe");
        String passwordConfirmation = Scan.getString();
        if (firstname.length() > 0 && lastname.length() > 0 && password.length() > 0) {
            if (passwordConfirmation.equals(password)) {

                String username = firstname.toLowerCase().charAt(0) + lastname.toLowerCase();
                User user = this.userRepository.getByUsername(username);

                if (user != null) {
                    username = username + (this.userRepository.getNumUsername(username) + 1);
                }
                this.customerRepository.create(new Customer(
                        firstname,
                        lastname,
                        this.userRepository.create(new User(username, password))
                ));
                System.out.println("Votre nom d'utilisateur est {" + username + "}");
            }
            else {
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
            System.out.println("\n--- Bonjour, " + this.customer.getFirstname() + " " + this.customer.getLastname() + " ---\n");
            System.out.println("Quelle opération voulez-vous effectuer ?");
            System.out.println("1) Quel client suis-je ?");
            System.out.println("2) Se déconnecter");
            choice = Scan.getInt();

            switch (choice) {
                case 1 -> System.out.println(this.customer);
                case 2 -> this.customer = null;
                default -> {
                }
            }
        } while (choice != 2 && this.customer != null);
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
