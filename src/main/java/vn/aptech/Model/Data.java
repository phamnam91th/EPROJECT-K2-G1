package vn.aptech.Model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class Data {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    public Data() {
        emf = Persistence.createEntityManagerFactory("default");
        em=emf.createEntityManager();
    }
    public static EntityManager getEntityManage() {
        emf = Persistence.createEntityManagerFactory("default");
        em=emf.createEntityManager();
        return em;
    }
    public static void closeConnect() {
        em.close();
        emf.close();
    }

    public List<Employee> getEmployeeList() {
        List<Employee> employeeList = null;
        try{
            Query q = em.createNativeQuery("select * from employee", Employee.class);
            employeeList = q.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
            emf.close();
        }
        return employeeList;
    }

}
