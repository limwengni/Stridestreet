/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "ORDER_STATUS_LOG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderStatusLog.findAll", query = "SELECT o FROM OrderStatusLog o"),
    @NamedQuery(name = "OrderStatusLog.findByLogId", query = "SELECT o FROM OrderStatusLog o WHERE o.logId = :logId"),
    @NamedQuery(name = "OrderStatusLog.findByDateModified", query = "SELECT o FROM OrderStatusLog o WHERE o.dateModified = :dateModified"),
    @NamedQuery(name = "OrderStatusLog.findByOrderStatus", query = "SELECT o FROM OrderStatusLog o WHERE o.orderStatus = :orderStatus")})
public class OrderStatusLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "LOG_ID")
    private Integer logId;
    @Column(name = "DATE_MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    @Size(max = 30)
    @Column(name = "ORDER_STATUS")
    private String orderStatus;
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID")
    @ManyToOne(optional = false)
    private Orders orderId;
    @JoinColumn(name = "STAFF_ID", referencedColumnName = "STAFF_ID")
    @ManyToOne
    private Staff staffId;

    public OrderStatusLog() {
    }

    public OrderStatusLog(Integer logId) {
        this.logId = logId;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Orders getOrderId() {
        return orderId;
    }

    public void setOrderId(Orders orderId) {
        this.orderId = orderId;
    }

    public Staff getStaffId() {
        return staffId;
    }

    public void setStaffId(Staff staffId) {
        this.staffId = staffId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logId != null ? logId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderStatusLog)) {
            return false;
        }
        OrderStatusLog other = (OrderStatusLog) object;
        if ((this.logId == null && other.logId != null) || (this.logId != null && !this.logId.equals(other.logId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.OrderStatusLog[ logId=" + logId + " ]";
    }

}
