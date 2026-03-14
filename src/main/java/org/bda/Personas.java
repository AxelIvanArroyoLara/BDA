package org.bda;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Personas {

    @Entity
    public static class Persona {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private String nombre;
        private int edad;

        public Persona() {
        }

        public Persona(String nombre, int edad) {
            this.nombre = nombre;
            this.edad = edad;
        }

        public Long getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        public int getEdad() {
            return edad;
        }

        @Override
        public String toString() {
            return "Persona{id=" + id + ", nombre='" + nombre + "', edad=" + edad + "}";
        }
    }

    public static void main(String[] args) {
        String dbPath = "objectdb:datos/miBD.odb";

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(dbPath);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(new Persona("Ana", 22));
            em.persist(new Persona("Bruno", 25));
            tx.commit();

            TypedQuery<Persona> query =
                    em.createQuery("SELECT p FROM Persona p ORDER BY p.id", Persona.class);

            List<Persona> personas = query.getResultList();

            System.out.println("=== Personas en la BD ===");
            for (Persona p : personas) {
                System.out.println(p);
            }

        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}