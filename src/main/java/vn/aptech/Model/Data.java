package vn.aptech.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.Object;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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

    public Users getLoginResult(String userName, String password) {
        getConnect();
        Users users = null;

        try {
            Query q = em.createQuery("select s from users s where s.userName=:userName and s.password=:password_encode");
            q.setParameter("userName", userName);
            q.setParameter("password_encode", getEncodePassword(password));
            users = (Users) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public ObservableList<TaskList> getTaskSearchResult(String s_select_car_cb, String s_select_router_cb, String s_select_driver_cb, String s_select_day_dp) {
        ObservableList<TaskList> taskLists = null;
        Query query = null;
        getConnect();
        try {
            query = em.createQuery("select tl from task_list tl inner join list_car lc on tl.listCarId = lc.id inner join router_list rl on tl.routerListId = rl.id inner join users u on tl.userId = u.id  where lc.licensePlates like '" + s_select_car_cb + "' and rl.code like '" + s_select_router_cb + "' and u.userName like '" + s_select_driver_cb + "' and tl.dateApply like '" + s_select_day_dp + "' ");
            System.out.println(query.toString());
            taskLists = FXCollections.observableArrayList(query.getResultList());
            System.out.println(taskLists);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnect();
        }
        return taskLists;
    }

    public ObservableList<Ticket> getTicketSearchResult( String customerName, String customerPhone, String branch, String dateApply) {
        ObservableList<Ticket> tickets = null;
        Query query = null;
        getConnect();
        try {
            query = em.createQuery("select t from ticket t inner join task_list tl on t.taskListId = tl.id inner join router_list rl on tl.routerListId = rl.id inner join branch b on rl.startPoint = b.id  where t.customerName like '" + customerName + "' and t.customerPhone like '" + customerPhone + "' and b.name like '" + branch + "' and tl.dateApply like '" + dateApply + "' ");
            System.out.println(query.toString());
            tickets = FXCollections.observableArrayList(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnect();
        }
        return tickets;
    }

    public static void main(String[] args) {
        Data data = new Data();
        ObservableList<Ticket> taskLists;
        taskLists = data.getTicketSearchResult( "%", "%", "HA NOI", "%");
        for (Ticket task : taskLists) {
            System.out.println(task);
        }
    }

}
