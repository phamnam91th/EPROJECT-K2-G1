package vn.aptech.Model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity(name = "employee")
@Table(name = "employee", schema = "projectk2")
public class Employee {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "code")
    private String code;
    @Basic
    @Column(name = "f_name")
    private String fName;
    @Basic
    @Column(name = "m_name")
    private String mName;
    @Basic
    @Column(name = "l_name")
    private String lName;
    @Basic
    @Column(name = "dob")
    private Date dob;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "person_id")
    private String personId;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "positions_id")
    private Integer positionsId;
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

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPositionsId() {
        return positionsId;
    }

    public void setPositionsId(Integer positionsId) {
        this.positionsId = positionsId;
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
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(code, employee.code) && Objects.equals(fName, employee.fName) && Objects.equals(mName, employee.mName) && Objects.equals(lName, employee.lName) && Objects.equals(dob, employee.dob) && Objects.equals(address, employee.address) && Objects.equals(personId, employee.personId) && Objects.equals(phone, employee.phone) && Objects.equals(email, employee.email) && Objects.equals(positionsId, employee.positionsId) && Objects.equals(flag, employee.flag) && Objects.equals(createAt, employee.createAt) && Objects.equals(updateAt, employee.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, fName, mName, lName, dob, address, personId, phone, email, positionsId, flag, createAt, updateAt);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", fName='" + fName + '\'' +
                ", mName='" + mName + '\'' +
                ", lName='" + lName + '\'' +
                ", dob=" + dob +
                ", address='" + address + '\'' +
                ", personId='" + personId + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", positionsId=" + positionsId +
                ", flag='" + flag + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
