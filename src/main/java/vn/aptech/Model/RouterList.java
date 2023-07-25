package vn.aptech.Model;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

@Entity(name = "router_list")
@Table(name = "router_list", schema = "projectk2")
public class RouterList {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "code")
    private String code;
    @Basic
    @Column(name = "start_point")
    private int startPoint;
    @Basic
    @Column(name = "destination")
    private int destination;
    @Basic
    @Column(name = "start_time")
    private Time startTime;
    @Basic
    @Column(name = "end_time")
    private Time endTime;
    @Basic
    @Column(name = "price")
    private double price;
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

    public int getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(int startPoint) {
        this.startPoint = startPoint;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
        RouterList that = (RouterList) o;
        return id == that.id && startPoint == that.startPoint && destination == that.destination && Double.compare(that.price, price) == 0 && Objects.equals(code, that.code) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime) && Objects.equals(flag, that.flag) && Objects.equals(createAt, that.createAt) && Objects.equals(updateAt, that.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, startPoint, destination, startTime, endTime, price, flag, createAt, updateAt);
    }

    @Override
    public String toString() {
        return "RouterList{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", startPoint=" + startPoint +
                ", destination=" + destination +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", price=" + price +
                ", flag='" + flag + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
