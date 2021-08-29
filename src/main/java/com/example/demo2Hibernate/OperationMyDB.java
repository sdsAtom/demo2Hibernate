package com.example.demo2Hibernate;

import javax.persistence.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/operations")
public class OperationMyDB {

    @GET
    @Produces("text/plain")
    public String getFullSum() {
        String res;

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sds_data_source");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            /* 1
            Clients client = new Clients();
            client.setId(10);
            client.setName("fff");
            client.setEmail("fff@dsdsad");
            client.setPhone("+7(999)666-55-44");
            entityManager.persist(client);
             */

            /* 2
            TypedQuery<Clients> empByIdQuery = entityManager.createNamedQuery("Client.byID", Clients.class);
            empByIdQuery.setParameter(1, 1);

            for (Clients client : empByIdQuery.getResultList()) {
                System.out.println(client);
            }
            */

            Query countClients = entityManager.createNativeQuery("select sum(oper_sum) as sum from sds_t_operations");
            //countClients.setParameter("ident", 1);
            //System.out.println("cnt = " + countClients.getSingleResult() + "!");

            res = "sum=" + countClients.getSingleResult();

            transaction.commit();
        } finally {

            if (transaction.isActive()) {
                transaction.rollback();
            }

            entityManager.close();
            entityManagerFactory.close();
        }

        return res;
    }

}
