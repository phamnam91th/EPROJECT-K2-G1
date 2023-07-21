package vn.aptech;

import vn.aptech.Model.Data;
import vn.aptech.Model.Employee;

import javax.persistence.*;

public class Test {
    public static void main(String[] args) {
        EntityManager entityManager = Data.getEntityManage();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Query q = entityManager.createNativeQuery("select * from employee", Employee.class);
            for(Object test: q.getResultList()) {
                System.out.println(test);
            }
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            Data.closeConnect();
        }
    }
}