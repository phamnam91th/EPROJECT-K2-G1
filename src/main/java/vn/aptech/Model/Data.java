package vn.aptech.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.*;
import java.util.List;

public class Data {
    // Employee
//    public Data() {
//        emf = Persistence.createEntityManagerFactory("default");
//        em=emf.createEntityManager();
//    }
    private  EntityManagerFactory emf;
    private  EntityManager em;

    public  EntityManagerFactory getEmf() {
        return emf;
    }

    public EntityManager getEm() {
        return em;
    }

    public void getConnect() {
        emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
    }
    public void closeConnect() {
        em.close();
        emf.close();
    }


    // Employee
    public ObservableList<Employee> getEmployeeList() {
        ObservableList<Employee> employeeList = null;
        getConnect();
        try{
            Query q = em.createNativeQuery("select * from employee", Employee.class);
            employeeList = FXCollections.observableArrayList(q.getResultList());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnect();
        }
        return employeeList;
    }


    public void addEmployee(Employee employee) {
        getConnect();
        try{
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(employee);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnect();
        }
    }

    public void updateEmployee(Employee employee) {
        getConnect();
        try{
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            Employee employee1 = em.find(Employee.class, employee.getId());


            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnect();
        }
    }

    // Positions
    public ObservableList<Positions> getPositionsList() {
        ObservableList<Positions> positionsList = null;
        getConnect();
        try{
            Query q = em.createNativeQuery("select * from positions", Positions.class);
            positionsList = FXCollections.observableArrayList(q.getResultList());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnect();
        }
        return positionsList;
    }



    // Branch
    public ObservableList<Branch> getBranchObservableList() {
        ObservableList<Branch> branchObservableList = null;
        getConnect();
        try{
            Query q = em.createNativeQuery("select * from branch", Branch.class);
            branchObservableList = FXCollections.observableArrayList(q.getResultList());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnect();
        }
        return branchObservableList;
    }

    // Role
    public ObservableList<Role> getRoleObservableList() {
        ObservableList<Role> roleObservableList  = null;
        getConnect();
        try{
            Query q = em.createNativeQuery("select * from role", Role.class);
            roleObservableList = FXCollections.observableArrayList(q.getResultList());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnect();
        }
        return roleObservableList;
    }


}
