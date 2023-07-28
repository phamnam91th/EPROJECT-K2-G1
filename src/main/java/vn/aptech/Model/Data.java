package vn.aptech.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.Object;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.DatatypeConverter;


public class Data {
    private EntityManagerFactory emf;
    private EntityManager em;

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public EntityManager getEm() {
        return em;
    }

    public Data() {
    }

    public void getConnect() {
        emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
    }

    public void closeConnect() {
        em.close();
        emf.close();
    }


    // Encode password
    public String getEncodePassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }


    // CRUD
    public boolean add(Object T) {
        boolean flag = false;
        getConnect();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(T);
            transaction.commit();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnect();
        }
        return flag;
    }

    public void update(Object T, int id) {
        getConnect();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            Object T1 = em.find(T.getClass(), id);
            em.merge(T1);
            transaction.commit();
            System.out.println("update success");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnect();
        }
    }

    //    convert method following to generic mothod :
    public void updateItem(Ticket T, int id, String content) {

        getConnect();

        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            Ticket ticket = em.find(T.getClass(), id);
            ticket.setCode(content);
            em.merge(ticket);
            transaction.commit();
            System.out.println("update success");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnect();
        }
    }

    public void delete(Object T, int id) {
        getConnect();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            Object T1 = em.find(T.getClass(), id);
            em.remove(T1);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnect();
        }
    }

//    public ObservableList<Object> getObservableList(String tableName) {
//        ObservableList<Object> ObservableList = null;
//        getConnect();
//        try {
//            Query q = em.createQuery("select s from " + tableName + " s");
//            ObservableList = FXCollections.observableArrayList(q.getResultList());
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            closeConnect();
//        }
//        return ObservableList;
//    }

    public <T> ObservableList<T> getObservableList(String sql) {
        getConnect();
        ObservableList<T> observableList = FXCollections.observableArrayList();
        try {
            Query q = em.createQuery(sql);
            observableList = FXCollections.observableArrayList(q.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnect();
        }
        return observableList;
    }

    public ObservableList<String> getListName(String tableName, String col) {
        ObservableList<String> ObservableList = null;
        getConnect();
        try {
            Query q = em.createQuery("select s." + col + " from " + tableName + " s");
            ObservableList = FXCollections.observableArrayList(q.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnect();
        }
        return ObservableList;
    }

    public <T> T getSignleResult(String code) {
        getConnect();
        T result = null;
        try {
            TypedQuery<T> q = (TypedQuery<T>) em.createQuery(code);
            result = q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnect();
        }
        return result;
    }

    public <T> T getListResult(String code) {
        getConnect();
        T result = null;
        try {
            TypedQuery<T> q = (TypedQuery<T>) em.createQuery(code);
            result = (T) q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnect();
        }
        return result;
    }



    // Employee

    // Positions

    // Branch

    // Role

    // User

    // ROuter list

    // Task list
    public static void main(String[] args) {
        Data data   = new Data();
        data.getConnect();
        try {
//            String str = "where b.id=1";
//            Query q = data.getEm().createQuery("select b from branch b "+ str);
//            List<Branch> branchObservableList = q.getResultList();
//            branchObservableList.forEach(System.out::println);
//            ObservableList<Branch> branchObservableList = data.getObservableList("branch");
//            for (Branch branch : branchObservableList) {
//                System.out.println(branch);
//            }
//            ObservableList<String> list;
//            list = data.getResult("select s.name from task_status s");
//            System.out.println(list);
//            TypedQuery<Long> q = data.em.createQuery("select count(s.status) from task_list s where s.status = 2", Long.class);
//            Long s = q.getSingleResult();
//            System.out.println(s);
//            Long s = data.getResult("select count(s.status) from task_list s where s.status = 2");
//            System.out.println(s);
            List<String> name = data.getListResult("select s.code from task_list s where s.status = 2");
            System.out.println(name);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            data.closeConnect();
        }
    }

}
