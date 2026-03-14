package org.bda.act1_3;

import jakarta.persistence.*;

// @Entity
// Indica que esta clase es una entidad persistente.
// Es decir, ObjectDB/JPA la va a mapear a la base de datos.
//
// @Inheritance(strategy = InheritanceType.JOINED)
// Indica que Clothing es la superclase de una jerarquía de herencia.
// Sus subclases, como Hat, Sock, Pant y Shirt, también serán entidades.
//
// La estrategia JOINED significa que:
// - habrá una tabla base para Clothing con los atributos comunes
// - y una tabla adicional para cada subclase con sus atributos específicos
//
// Esto se usa porque en el UML Clothing actúa como clase padre
// y las demás prendas heredan de ella.
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Clothing {

    // @Id
    // Marca este atributo como la clave primaria de la entidad.
    // Es el identificador único de cada objeto Clothing en la base de datos.
    //
    // @GeneratedValue(strategy = GenerationType.AUTO)
    // Le indica a JPA/ObjectDB que el valor del id no lo asignará manualmente el programador,
    // sino que se generará automáticamente.
    //
    // AUTO significa que el proveedor de persistencia decide
    // cuál es la mejor estrategia de generación.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Atributos comunes de cualquier prenda.
    // description describe la prenda.
    // price guarda su precio.
    private String description;
    private Float price;

    // @ManyToOne
    // Indica una relación de muchas prendas hacia un solo cliente.
    //
    // Significado en el modelo:
    // - un Customer puede haber ordenado muchas prendas
    // - pero cada Clothing pertenece a un solo Customer
    //
    // En otras palabras, muchas instancias de Clothing
    // pueden apuntar al mismo cliente.
    //
    // @JoinColumn(name = "customer_id")
    // Indica el nombre de la columna que se usará como clave foránea
    // para relacionar esta prenda con su cliente en la base de datos.
    //
    // orderedBy representa el cliente que ordenó esta prenda.
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer orderedBy;

    // Constructor vacío obligatorio para JPA.
    //
    // JPA/ObjectDB necesita un constructor sin argumentos
    // para poder crear objetos automáticamente al recuperarlos
    // desde la base de datos.
    //
    // Aunque el programador casi siempre use el constructor con parámetros,
    // este constructor vacío debe existir.
    public Clothing(){}

    // Constructor con parámetros.
    // Sirve para crear una prenda inicializando sus datos básicos.
    public Clothing(String description, Float price) {
        this.description = description;
        this.price = price;
    }

    // Devuelve el identificador único de la prenda.
    public Long getId(){
        return id;
    }

    // Devuelve la descripción de la prenda.
    public String getDescription(){
        return description;
    }

    // Devuelve el precio de la prenda.
    public Float getPrice() {
        return price;
    }

    // Devuelve el cliente que ordenó esta prenda.
    public Customer getOrderedBy() {
        return orderedBy;
    }

    // Permite modificar la descripción.
    public void setDescription(String description) {
        this.description = description;
    }

    // Permite modificar el precio.
    public void setPrice(Float price) {
        this.price = price;
    }

    // Permite asignar o cambiar el cliente que ordenó la prenda.
    //
    // Este método es importante en relaciones bidireccionales,
    // por ejemplo cuando desde Customer se usa addOrder(...)
    // para añadir una prenda a la lista de órdenes y al mismo tiempo
    // dejar enlazada esta prenda con ese cliente.
    public void setOrderedBy(Customer orderedBy) {
        this.orderedBy = orderedBy;
    }

    // Método de apoyo para mostrar la información básica de la prenda.
    public void displayInformation() {
        System.out.println("ID: " + id + ", description: " + description + ", price: " + price);
    }
}