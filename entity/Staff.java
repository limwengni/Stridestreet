/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package entity;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "STAFF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Staff.findAll", query = "SELECT s FROM Staff s"),
    @NamedQuery(name = "Staff.findByStaffId", query = "SELECT s FROM Staff s WHERE s.staffId = :staffId"),
    @NamedQuery(name = "Staff.findByStaffUsername", query = "SELECT s FROM Staff s WHERE s.staffUsername = :staffUsername"),
    @NamedQuery(name = "Staff.findByStaffPassword", query = "SELECT s FROM Staff s WHERE s.staffPassword = :staffPassword"),
    @NamedQuery(name = "Staff.findByStaffRole", query = "SELECT s FROM Staff s WHERE s.staffRole = :staffRole"),
    @NamedQuery(name = "Staff.findByStaffName", query = "SELECT s FROM Staff s WHERE s.staffName = :staffName")})
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "STAFF_ID")
    private Integer staffId;
    @Size(max = 50)
    @Column(name = "STAFF_USERNAME")
    private String staffUsername;
    @Size(max = 50)
    @Column(name = "STAFF_PASSWORD")
    private String staffPassword;
    @Size(max = 30)
    @Column(name = "STAFF_ROLE")
    private String staffRole;
    @Size(max = 50)
    @Column(name = "STAFF_NAME")
    private String staffName;
    @OneToMany(mappedBy = "staffId")
    private List<OrderStatusLog> orderStatusLogList;
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
    @ManyToOne(optional = false)
    private Role roleId;

    public Staff() {
    }

    public Staff(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffUsername() {
        return staffUsername;
    }

    public void setStaffUsername(String staffUsername) {
        this.staffUsername = staffUsername;
    }

    public String getStaffPassword() {
        return staffPassword;
    }

    public void setStaffPassword(String staffPassword) {
        this.staffPassword = staffPassword;
    }

    public String getStaffRole() {
        return staffRole;
    }

    public void setStaffRole(String staffRole) {
        this.staffRole = staffRole;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @XmlTransient
    public List<OrderStatusLog> getOrderStatusLogList() {
        return orderStatusLogList;
    }

    public void setOrderStatusLogList(List<OrderStatusLog> orderStatusLogList) {
        this.orderStatusLogList = orderStatusLogList;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (staffId != null ? staffId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Staff)) {
            return false;
        }
        Staff other = (Staff) object;
        if ((this.staffId == null && other.staffId != null) || (this.staffId != null && !this.staffId.equals(other.staffId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Staff[ staffId=" + staffId + " ]";
    }

}
