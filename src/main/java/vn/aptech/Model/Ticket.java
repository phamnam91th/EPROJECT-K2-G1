package vn.aptech.Model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity(name = "ticket")
@Table(name = "ticket", schema = "projectk2")
public class Ticket {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "code")
    private String code;
    @Basic
    @Column(name = "customer_name")
    private String customerName;
    @Basic
    @Column(name = "customer_phone")
    private String customerPhone;
    @Basic
    @Column(name = "number_of_ticket")
    private int numberOfTicket;
    @Basic
    @Column(name = "branch_id")
    private int branchId;
    @Basic
    @Column(name = "task_list_id")
    private int taskListId;
    @Basic
    @Column(name = "employee_id")
    private int employeeId;
    @Basic
    @Column(name = "status")
    private int status;
    @Basic
    @Column(name = "flag")
    private String flag;
    @Basic
    @Column(name = "create_at")
    private Timestamp createAt;
    @Basic
    @Column(name = "update_at")
    private Timestamp updateAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public int getNumberOfTicket() {
        return numberOfTicket;
    }

    public void setNumberOfTicket(int numberOfTicket) {
        this.numberOfTicket = numberOfTicket;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public int getTaskListId() {
        return taskListId;
    }

    public void setTaskListId(int taskListId) {
        this.taskListId = taskListId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && numberOfTicket == ticket.numberOfTicket && branchId == ticket.branchId && taskListId == ticket.taskListId && employeeId == ticket.employeeId && status == ticket.status && Objects.equals(code, ticket.code) && Objects.equals(customerName, ticket.customerName) && Objects.equals(customerPhone, ticket.customerPhone) && Objects.equals(flag, ticket.flag) && Objects.equals(createAt, ticket.createAt) && Objects.equals(updateAt, ticket.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, customerName, customerPhone, numberOfTicket, branchId, taskListId, employeeId, status, flag, createAt, updateAt);
    }
}
