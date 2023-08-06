package vn.aptech.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "report_by_car")
@Table(name = "report_by_car", schema = "digishop_project_k2_g1")
public class ReportByCar {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "car_id")
    private Integer carId;
    @Basic
    @Column(name = "number_of_task")
    private Integer numberOfTask;
    @Basic
    @Column(name = "number_of_ticket")
    private Integer numberOfTicket;
    @Basic
    @Column(name = "number_of_ticket_done")
    private Integer numberOfTicketDone;
    @Basic
    @Column(name = "number_of_ticket_cancel")
    private Integer numberOfTicketCancel;
    @Basic
    @Column(name = "total_revenue")
    private Double totalRevenue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getNumberOfTask() {
        return numberOfTask;
    }

    public void setNumberOfTask(Integer numberOfTask) {
        this.numberOfTask = numberOfTask;
    }

    public Integer getNumberOfTicket() {
        return numberOfTicket;
    }

    public void setNumberOfTicket(Integer numberOfTicket) {
        this.numberOfTicket = numberOfTicket;
    }

    public Integer getNumberOfTicketDone() {
        return numberOfTicketDone;
    }

    public void setNumberOfTicketDone(Integer numberOfTicketDone) {
        this.numberOfTicketDone = numberOfTicketDone;
    }

    public Integer getNumberOfTicketCancel() {
        return numberOfTicketCancel;
    }

    public void setNumberOfTicketCancel(Integer numberOfTicketCancel) {
        this.numberOfTicketCancel = numberOfTicketCancel;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportByCar that = (ReportByCar) o;
        return id == that.id && Objects.equals(carId, that.carId) && Objects.equals(numberOfTask, that.numberOfTask) && Objects.equals(numberOfTicket, that.numberOfTicket) && Objects.equals(numberOfTicketDone, that.numberOfTicketDone) && Objects.equals(numberOfTicketCancel, that.numberOfTicketCancel) && Objects.equals(totalRevenue, that.totalRevenue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carId, numberOfTask, numberOfTicket, numberOfTicketDone, numberOfTicketCancel, totalRevenue);
    }
}
