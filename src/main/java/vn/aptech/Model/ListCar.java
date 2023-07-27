package vn.aptech.Model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity(name = "list_car")
@Table(name = "list_car", schema = "digishop_project_k2_g1")
public class ListCar {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "license_plates")
    private String licensePlates;
    @Basic
    @Column(name = "type_car_id")
    private int typeCarId;
    @Basic
    @Column(name = "date_buy")
    private Date dateBuy;
    @Basic
    @Column(name = "description")
    private String description;
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

    public String getLicensePlates() {
        return licensePlates;
    }

    public void setLicensePlates(String licensePlates) {
        this.licensePlates = licensePlates;
    }

    public int getTypeCarId() {
        return typeCarId;
    }

    public void setTypeCarId(int typeCarId) {
        this.typeCarId = typeCarId;
    }

    public Date getDateBuy() {
        return dateBuy;
    }

    public void setDateBuy(Date dateBuy) {
        this.dateBuy = dateBuy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        ListCar listCar = (ListCar) o;
        return id == listCar.id && typeCarId == listCar.typeCarId && Objects.equals(licensePlates, listCar.licensePlates) && Objects.equals(dateBuy, listCar.dateBuy) && Objects.equals(description, listCar.description) && Objects.equals(flag, listCar.flag) && Objects.equals(createAt, listCar.createAt) && Objects.equals(updateAt, listCar.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, licensePlates, typeCarId, dateBuy, description, flag, createAt, updateAt);
    }
}
