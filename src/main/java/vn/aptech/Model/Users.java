package vn.aptech.Model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity(name = "users")
@Table(name = "users", schema = "projectk2")
public class Users {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "user_name")
    private String userName;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "role_id")
    private int roleId;
    @Basic
    @Column(name = "employee_id")
    private int employeeId;
    @Basic
    @Column(name = "employee_create")
    private int employeeCreate;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getEmployeeCreate() {
        return employeeCreate;
    }

    public void setEmployeeCreate(int employeeCreate) {
        this.employeeCreate = employeeCreate;
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
        Users users = (Users) o;
        return id == users.id && roleId == users.roleId && employeeId == users.employeeId && employeeCreate == users.employeeCreate && Objects.equals(userName, users.userName) && Objects.equals(password, users.password) && Objects.equals(flag, users.flag) && Objects.equals(createAt, users.createAt) && Objects.equals(updateAt, users.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, roleId, employeeId, employeeCreate, flag, createAt, updateAt);
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" + roleId +
                ", employeeId=" + employeeId +
                ", employeeCreate=" + employeeCreate +
                ", flag='" + flag + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
