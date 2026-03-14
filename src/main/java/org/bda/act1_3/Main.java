package org.bda.act1_3;
import jakarta.persistence.*;

public class Main {
    public static void main(String[] args) {
        String dbPath = "objectdb:databases/act1_3.odb";

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(dbPath);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            em.getTransaction().begin();

            Customer customer = new Customer("Axel", "Puebla");

            Hat hat = new Hat("Sombrero negro", 250.0f, 'B');
            Sock sock = new Sock("Calcetas verdes", 80.0f, 'G');
            Pant pant = new Pant("Pantalón casual", 500.0f, 32, 'M', 'B');
            Shirt shirt = new Shirt("Camisa formal", 400.0f, 'M', 'R');

            customer.addOrder(hat);
            customer.addOrder(sock);
            customer.addOrder(pant);
            customer.addOrder(shirt);

            em.persist(customer);

            em.getTransaction().commit();

            System.out.println("Cliente y prendas guardadas correctamente. \n");

            System.out.println("Cliente: ");
            customer.displayInformation();

            System.out.println("\nPrendas ordenadas:");
            for (Clothing clothing : customer.getOrders()){
                clothing.displayInformation();
            }
        } catch(Exception e){
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
