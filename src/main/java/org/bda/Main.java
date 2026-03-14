package org.bda;

import jakarta.persistence.*;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    @Entity
    public static class Persona {

        // Generación del ID automático
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private String nombre;
        private int edad;


        public Persona(){}


        public Persona(String nombre, int edad){
            this.nombre = nombre;
            this.edad = edad;
        }

        public Long getId(){
            return id;
        }

        public String getNombre(){
            return nombre;
        }

        public int getEdad(){
            return edad;
        }

        @Override
        public String toString() {
            return "Persona{id=" + id + ", nombre='" + nombre + "', edad=" + edad + "}";
        }
    }

    public static void main(String[] args){
        String dbPath = "objectdb:databases/testPersona.odb";

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(dbPath);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(new Persona("Ana", 22));
            tx.commit();

            TypedQuery<Persona> query =
                    (TypedQuery<Persona>) em.createQuery("SELECT p FROM Persona p ORDER BY p.id");

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