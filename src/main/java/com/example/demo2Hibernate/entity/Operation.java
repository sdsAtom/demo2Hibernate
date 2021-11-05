package com.example.demo2Hibernate.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@NamedQuery(name = "OperationList", query = "select a from Operation a")
public class Operation {

    private UUID id;
    private Date date;
    private String name;
    private String account;

    @Id
    @Column(name = "id", unique = true)
    public UUID getId() { return this.id; }
    public void setId(UUID id) { this.id = id; }

    @Basic
    @Column(name = "date")
    public Date getDate() { return this.date; }
    public void setDate(Date date) { this.date = date; }

    @Basic
    @Column(name = "name")
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    @Basic
    @Column(name = "account")
    public String getAccount() { return this.account; }
    public void setAccount(String account) { this.account = account; }


    public static List<Operation> getOperationList() {
        List<Operation> list;

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sds_data_source");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {

            entityTransaction.begin();

            TypedQuery<Operation> query = entityManager.createNamedQuery("OperationList", Operation.class);

            list = query.getResultList();

            entityTransaction.commit();
        }
        finally {

            if (entityTransaction.isActive())
                entityTransaction.rollback();

            entityManager.close();
            entityManagerFactory.close();
        }

        return list;
    }
}
