package vn.aptech.Model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity(name = "type_car")
@Table(name = "type_car", schema = "projectk2")
public class TypeCar {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "brand")
    private String brand;
    @Basic
    @Column(name = "number_of_seats")
    private int numberOfSeats;
    @Basic
    @Column(name = "year_of_manufacture")
    private Integer yearOfManufacture;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Integer getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(Integer yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
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
        TypeCar typeCar = (TypeCar) o;
        return id == typeCar.id && numberOfSeats == typeCar.numberOfSeats && Objects.equals(name, typeCar.name) && Objects.equals(brand, typeCar.brand) && Objects.equals(yearOfManufacture, typeCar.yearOfManufacture) && Objects.equals(flag, typeCar.flag) && Objects.equals(createAt, typeCar.createAt) && Objects.equals(updateAt, typeCar.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand, numberOfSeats, yearOfManufacture, flag, createAt, updateAt);
    }
}
