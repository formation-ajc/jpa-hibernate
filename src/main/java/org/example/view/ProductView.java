package org.example.view;

import jakarta.persistence.EntityManagerFactory;
import libraries.Scan;
import org.example.entity.Product;
import org.example.entity.Supplier;
import org.example.repository.ProductRepository;

import java.util.List;

public class ProductView {
    private final EntityManagerFactory emf;
    private final ProductRepository productRepository;
    private final Supplier supplier;
    private List<Product> products;

    public ProductView(EntityManagerFactory emf, Supplier supplier) {
        this.emf = emf;
        this.supplier = supplier;
        this.productRepository = new ProductRepository(emf);
        this.startView();
    }

    private void startView() {
        int choice;
        do {
            System.out.println("\n--- Bonjour, " + this.supplier.getName() + " ---");
            System.out.println("--- Interface produit ---\n");
            System.out.println("Quelle opération voulez-vous effectuer ?");
            System.out.println("1) Liste des produits");
            System.out.println("2) Ajouter un produit");
            System.out.println("3) Mettre à jour un produit");
            System.out.println("4) Supprimer un produit");
            System.out.println("5) Quitter");
            choice = Scan.getInt();

            switch (choice) {
                case 1 -> this.listProduct();
                case 2 -> this.addProduct();
                case 3 -> this.updateProduct();
                case 4 -> this.deleteProduct();
                case 5-> {}
                default -> {
                }
            }

        } while (choice != 5);
    }

    private void listProduct() {
        System.out.println("--- Liste des produits ---\n");
        this.products = this.productRepository.getAllBySupplier(this.supplier);
        if (this.products != null && this.products.size() > 0 ) {
            int i = 0;
            for (Product product : products) {
                System.out.println(++i + ") " + product.toDisplay());
            }
        }
        else {
            System.out.println("!!! Aucun produits !!!");
        }
    }

    private void addProduct() {
        System.out.println("--- Ajouter un produit ---");
        System.out.println("Nom");
        String name = Scan.getString();
        System.out.println("Prix");
        Float price = Scan.getFloat();
        System.out.println("Quantité");
        Integer quantity = Scan.getInt();

        if (name.length() > 0) {

            Product product = this.productRepository.getByNamePriceAndSupplier(name, price, this.supplier);

            if (product == null) {
                this.productRepository.create(new Product(
                    name,
                    price,
                    quantity,
                    this.supplier
                ));
            }
            else {
                this.productRepository.update(new Product(
                    product.getId(),
                    name,
                    price,
                    (quantity + product.getQuantity()),
                    this.supplier
                ));
            }
        }
        else {
            System.out.println("!!! Les champs sont obligatoires !!!");
        }
    }

    private void updateProduct() {
        this.listProduct();
        if (this.products != null && this.products.size() > 0 ) {
            System.out.println("--- Modifier un produit ---");
            System.out.println("Numéro du produit dans la liste");
            int choice = Scan.getInt();
            System.out.println("Nom");
            String name = Scan.getString();
            System.out.println("Prix");
            Float price = Scan.getFloat();
            System.out.println("Quantité");
            Integer quantity = Scan.getInt();
            this.productRepository.update(new Product(
                    this.products.get(choice - 1).getId(),
                    name,
                    price,
                    quantity,
                    this.supplier
            ));
        }
    }

    private void deleteProduct() {
        this.listProduct();
        if (this.products != null && this.products.size() > 0 ) {
            System.out.println("--- Supprimer un produit ---");
            System.out.println("Numéro du produit dans la liste");
            int choice = Scan.getInt();
            this.productRepository.delete(this.products.get(choice - 1).getId());
        }
    }


}
