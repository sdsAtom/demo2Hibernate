package com.example.demo2Hibernate.entity;

import javax.persistence.*;
import javax.ws.rs.PathParam;
import java.util.*;

@Entity
@NamedQuery(name = "Client.byID", query = "select a from Clients a where a.id = ?1")
@NamedQuery(name = "Client.full", query = "select a from Clients a")
public class Clients {


    @Override
    public String toString() {
        return "Clients{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    private int id;
    private String name;
    private String email;
    private String phone;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clients clients = (Clients) o;

        if (id != clients.id) return false;
        if (!Objects.equals(name, clients.name)) return false;
        if (!Objects.equals(email, clients.email)) return false;
        return Objects.equals(phone, clients.phone);
    }

    @Override
    public int hashCode() {

        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

    public void save() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sds_data_source");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        if  (this.id == 0) {
            try {
                transaction.begin();

                Query countClients = entityManager.createNativeQuery("select max(a.id)+1 as cnt from clients  a");
                this.id = (int) countClients.getSingleResult();

                entityManager.merge(this);

                transaction.commit();
            } finally {

                if (transaction.isActive()) {
                    transaction.rollback();
                }

                entityManager.close();
                entityManagerFactory.close();
            }
        } else {

            try {
                transaction.begin();

                //entityManager.persist(this);
                entityManager.merge(this);

                transaction.commit();
            } finally {

                if (transaction.isActive()) {
                    transaction.rollback();
                }

                entityManager.close();
                entityManagerFactory.close();
            }
        }
    }

    public static Clients getClient(int id) {

        Clients res = null;

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sds_data_source");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            TypedQuery<Clients> empByIdQuery = entityManager.createNamedQuery("Client.byID", Clients.class);
            empByIdQuery.setParameter(1, id);

            //List<Clients> clients = empByIdQuery.getResultList();


            for (Clients client : empByIdQuery.getResultList()) {
                res = client;
            }

            transaction.commit();
        } finally {

            if (transaction.isActive()) {
                transaction.rollback();
            }

            entityManager.close();
            entityManagerFactory.close();
        }

        return  res;
    }

    public static List<Clients> getClients(int id) {

        List<Clients> res = null;

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sds_data_source");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            if (id <= 0) {
                TypedQuery<Clients> empByIdQuery = entityManager.createNamedQuery("Client.full", Clients.class);
                //empByIdQuery.setParameter(1, id);
                res = empByIdQuery.getResultList();
            }
            else {
                TypedQuery<Clients> empByIdQuery = entityManager.createNamedQuery("Client.byID", Clients.class);
                empByIdQuery.setParameter(1, id);

                for (Clients client : empByIdQuery.getResultList()) {
                    res = Collections.singletonList(client);
                }
            }


            transaction.commit();
        } finally {

            if (transaction.isActive()) {
                transaction.rollback();
            }

            entityManager.close();
            entityManagerFactory.close();
        }

        return  res;
    }
}
