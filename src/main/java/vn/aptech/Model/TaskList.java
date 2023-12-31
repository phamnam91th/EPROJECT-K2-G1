package vn.aptech.Model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity(name = "task_list")
@Table(name = "task_list", schema = "digishop_project_k2_g1")
public class TaskList {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "code")
    private String code;
    @Basic
    @Column(name = "list_car_id")
    private int listCarId;
    @Basic
    @Column(name = "router_list_id")
    private int routerListId;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "date_apply")
    private Date dateApply;
    @Basic
    @Column(name = "seat_available")
    private int seatAvailable;
    @Basic
    @Column(name = "start_time")
    private Timestamp startTime;
    @Basic
    @Column(name = "end_time")
    private Timestamp endTime;
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

    public int getListCarId() {
        return listCarId;
    }

    public void setListCarId(int listCarId) {
        this.listCarId = listCarId;
    }

    public int getRouterListId() {
        return routerListId;
    }

    public void setRouterListId(int routerListId) {
        this.routerListId = routerListId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDateApply() {
        return dateApply;
    }

    public void setDateApply(Date dateApply) {
        this.dateApply = dateApply;
    }

    public int getSeatAvailable() {
        return seatAvailable;
    }

    public void setSeatAvailable(int seatAvailable) {
        this.seatAvailable = seatAvailable;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
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
        TaskList taskList = (TaskList) o;
        return id == taskList.id && listCarId == taskList.listCarId && routerListId == taskList.routerListId && userId == taskList.userId && Objects.equals(code, taskList.code) && Objects.equals(dateApply, taskList.dateApply) && Objects.equals(status, taskList.status) && Objects.equals(flag, taskList.flag) && Objects.equals(createAt, taskList.createAt) && Objects.equals(updateAt, taskList.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, listCarId, routerListId, userId, dateApply, status, flag, createAt, updateAt);
    }

    @Override
    public String toString() {
        return "TaskList{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", listCarId=" + listCarId +
                ", routerListId=" + routerListId +
                ", userId=" + userId +
                ", dateApply=" + dateApply +
                ", status=" + status +
                ", flag='" + flag + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
