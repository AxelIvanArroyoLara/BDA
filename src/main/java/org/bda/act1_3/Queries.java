package org.bda.act1_3;

import jakarta.persistence.EntityManager;
import java.util.List;

public class Queries {

    // Query 1:
    // Give the name of all customers living in Puebla whose individual garments
    // have a cost of more than $145.00.
    //
    // Significado:
    // Se buscan los nombres de los clientes cuya dirección sea "Puebla"
    // y que tengan al menos una prenda con precio mayor a 145.
    //
    // Lógica:
    // - c representa un Customer
    // - o representa una prenda dentro de c.orders
    // - se filtra por dirección y por precio de la prenda
    // - DISTINCT evita repetir nombres si un cliente tiene varias prendas que cumplen
    public static void query1(EntityManager em) {
        String q = """
            SELECT DISTINCT c.name
            FROM Customer c, IN(c.orders) o
            WHERE c.address = 'Puebla' AND o.price > 145.0
            """;

        List<String> result = em.createQuery(q, String.class).getResultList();

        System.out.println("Query 1:");
        for (String name : result) {
            System.out.println(name);
        }
    }

    // Query 2:
    // Give the identifier of the customers with the average price of all their
    // orders higher than $150.00.
    //
    // Significado:
    // Se buscan los identificadores de los clientes cuyo promedio del precio
    // de todas sus prendas pedidas sea mayor a 150.
    //
    // Lógica:
    // - para cada cliente c, se calcula el promedio AVG(o.price) de todas sus órdenes
    // - si ese promedio es mayor a 150, se devuelve su customerID
    public static void query2(EntityManager em) {
        String q = """
            SELECT c.customerID
            FROM Customer c
            WHERE (SELECT AVG(o.price) FROM IN(c.orders) o) > 150.0
            """;

        List<Long> result = em.createQuery(q, Long.class).getResultList();

        System.out.println("Query 2:");
        for (Long id : result) {
            System.out.println(id);
        }
    }

    // Query 3:
    // Give the name and address of customers who ordered at least 2 garments
    // of the same type.
    //
    // Significado:
    // Se buscan clientes que hayan pedido al menos 2 prendas del mismo tipo
    // (por ejemplo, 2 Hat, o 2 Sock, etc.).
    //
    // Lógica:
    // - se recorre cada cliente y sus órdenes
    // - se agrupa por cliente y por tipo de prenda
    // - getClass().getName() permite distinguir el tipo concreto de la prenda
    // - HAVING COUNT(o) >= 2 exige que haya al menos dos del mismo tipo
    // - se devuelven nombre y dirección
    public static void query3(EntityManager em) {
        String q = """
            SELECT DISTINCT c.name, c.address
            FROM Customer c, IN(c.orders) o
            GROUP BY c.customerID, c.name, c.address, o.getClass().getName()
            HAVING COUNT(o) >= 2
            """;

        List<Object[]> result = em.createQuery(q, Object[].class).getResultList();

        System.out.println("Query 3:");
        for (Object[] row : result) {
            System.out.println("Name: " + row[0] + ", Address: " + row[1]);
        }
    }

    // Query 4:
    // Give the name and orders of customers who ordered at least 2 garments
    // of the same type.
    //
    // Significado:
    // Se buscan los clientes que hayan pedido al menos 2 prendas del mismo tipo,
    // pero en vez de devolver dirección, se devuelve su nombre y toda su colección
    // de órdenes.
    //
    // Lógica:
    // - igual que en la consulta 3, se agrupa por cliente y tipo de prenda
    // - HAVING COUNT(o) >= 2 asegura que existan al menos dos del mismo tipo
    // - se devuelve el nombre del cliente y su lista completa de orders
    public static void query4(EntityManager em) {
        String q = """
            SELECT DISTINCT c.name, c.orders
            FROM Customer c, IN(c.orders) o
            GROUP BY c.customerID, c.name, c.orders, o.getClass().getName()
            HAVING COUNT(o) >= 2
            """;

        List<Object[]> result = em.createQuery(q, Object[].class).getResultList();

        System.out.println("Query 4:");
        for (Object[] row : result) {
            System.out.println("Customer: " + row[0]);
            System.out.println("Orders: " + row[1]);
        }
    }

    // Query 5:
    // Give the identifier of the customers with the average price of their
    // garments of the same type that is higher than $150.00 dollars.
    //
    // Significado:
    // Se buscan los identificadores de clientes que, para algún tipo de prenda,
    // tengan un promedio de precio mayor a 150.
    //
    // Ejemplo:
    // Si un cliente compró 3 Hat con promedio 180, entonces cumple,
    // aunque sus Sock o Shirt no cumplan.
    //
    // Lógica:
    // - se agrupa por cliente y por tipo de prenda
    // - se calcula AVG(o.price) dentro de cada grupo
    // - si el promedio de ese tipo supera 150, se devuelve el customerID
    // - DISTINCT evita repetir el mismo cliente si cumple con más de un tipo
    public static void query5(EntityManager em) {
        String q = """
            SELECT DISTINCT c.customerID
            FROM Customer c, IN(c.orders) o
            GROUP BY c.customerID, o.getClass().getName()
            HAVING AVG(o.price) > 150.0
            """;

        List<Long> result = em.createQuery(q, Long.class).getResultList();

        System.out.println("Query 5:");
        for (Long id : result) {
            System.out.println(id);
        }
    }
}