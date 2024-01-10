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
@Table(name = "ORDER_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderDetail.findAll", query = "SELECT o FROM OrderDetail o"),
    @NamedQuery(name = "OrderDetail.findByDetailId", query = "SELECT o FROM OrderDetail o WHERE o.detailId = :detailId"),
    @NamedQuery(name = "OrderDetail.findByProductName", query = "SELECT o FROM OrderDetail o WHERE o.productName = :productName"),
    @NamedQuery(name = "OrderDetail.findByProductQuantity", query = "SELECT o FROM OrderDetail o WHERE o.productQuantity = :productQuantity"),
    @NamedQuery(name = "OrderDetail.findByProductPrice", query = "SELECT o FROM OrderDetail o WHERE o.productPrice = :productPrice"),
    @NamedQuery(name = "OrderDetail.findByPaymentMethod", query = "SELECT o FROM OrderDetail o WHERE o.paymentMethod = :paymentMethod"),
    @NamedQuery(name = "OrderDetail.findByDeliveryDate", query = "SELECT o FROM OrderDetail o WHERE o.deliveryDate = :deliveryDate"),
    @NamedQuery(name = "OrderDetail.findByOrderStatus", query = "SELECT o FROM OrderDetail o WHERE o.orderStatus = :orderStatus")})
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DETAIL_ID")
    private Integer detailId;
    @Size(max = 50)
    @Column(name = "PRODUCT_NAME")
    private String productName;
    @Column(name = "PRODUCT_QUANTITY")
    private Integer productQuantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRODUCT_PRICE")
    private Double productPrice;
    @Size(max = 50)
    @Column(name = "PAYMENT_METHOD")
    private String paymentMethod;
    @Column(name = "DELIVERY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDate;
    @Size(max = 30)
    @Column(name = "ORDER_STATUS")
    private String orderStatus;
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID")
    @ManyToOne(optional = false)
    private Orders orderId;
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
    @ManyToOne(optional = false)
    private Product productId;

    public OrderDetail() {
    }

    public OrderDetail(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
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

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detailId != null ? detailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderDetail)) {
            return false;
        }
        OrderDetail other = (OrderDetail) object;
        if ((this.detailId == null && other.detailId != null) || (this.detailId != null && !this.detailId.equals(other.detailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.OrderDetail[ detailId=" + detailId + " ]";
    }

}
