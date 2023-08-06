package vn.aptech.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "report_by_branch")
@Table(name = "report_by_branch", schema = "digishop_project_k2_g1")
public class ReportByBranch {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "branch_id")
    private Integer branchId;
    @Basic
    @Column(name = "number_of_ticket")
    private Integer numberOfTicket;
    @Basic
    @Column(name = "number_of_ticket_done")
    private Integer numberOfTicketDone;
    @Basic
    @Column(name = "number_of_ticket_pending")
    private Integer numberOfTicketPending;
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

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
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

    public Integer getNumberOfTicketPending() {
        return numberOfTicketPending;
    }

    public void setNumberOfTicketPending(Integer numberOfTicketPending) {
        this.numberOfTicketPending = numberOfTicketPending;
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
        ReportByBranch that = (ReportByBranch) o;
        return id == that.id && Objects.equals(branchId, that.branchId) && Objects.equals(numberOfTicket, that.numberOfTicket) && Objects.equals(numberOfTicketDone, that.numberOfTicketDone) && Objects.equals(numberOfTicketPending, that.numberOfTicketPending) && Objects.equals(numberOfTicketCancel, that.numberOfTicketCancel) && Objects.equals(totalRevenue, that.totalRevenue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, branchId, numberOfTicket, numberOfTicketDone, numberOfTicketPending, numberOfTicketCancel, totalRevenue);
    }
}
