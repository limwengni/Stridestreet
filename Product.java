/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "PRODUCT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findByProductId", query = "SELECT p FROM Product p WHERE p.productId = :productId"),
    @NamedQuery(name = "Product.findByProductName", query = "SELECT p FROM Product p WHERE p.productName = :productName"),
    @NamedQuery(name = "Product.findByProductDesc", query = "SELECT p FROM Product p WHERE p.productDesc = :productDesc"),
    @NamedQuery(name = "Product.findBySku", query = "SELECT p FROM Product p WHERE p.sku = :sku"),
    @NamedQuery(name = "Product.findByCategory", query = "SELECT p FROM Product p WHERE p.category = :category"),
    @NamedQuery(name = "Product.findByProductSize", query = "SELECT p FROM Product p WHERE p.productSize = :productSize"),
    @NamedQuery(name = "Product.findByProductQuantity", query = "SELECT p FROM Product p WHERE p.productQuantity = :productQuantity"),
    @NamedQuery(name = "Product.findByProductSold", query = "SELECT p FROM Product p WHERE p.productSold = :productSold"),
    @NamedQuery(name = "Product.findByProductPrice", query = "SELECT p FROM Product p WHERE p.productPrice = :productPrice")})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PRODUCT_ID")
    private Integer productId;
    @Size(max = 50)
    @Column(name = "PRODUCT_NAME")
    private String productName;
    @Size(max = 3000)
    @Column(name = "PRODUCT_DESC")
    private String productDesc;
    @Size(max = 8)
    @Column(name = "SKU")
    private String sku;
    @Size(max = 50)
    @Column(name = "CATEGORY")
    private String category;
    @Size(max = 50)
    @Column(name = "PRODUCT_SIZE")
    private String productSize;
    @Column(name = "PRODUCT_QUANTITY")
    private Integer productQuantity;
    @Column(name = "PRODUCT_SOLD")
    private Integer productSold;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRODUCT_PRICE")
    private Double productPrice;
    @Lob
    @Column(name = "PRODUCT_IMAGE1")
    private Serializable productImage1;
    @Lob
    @Column(name = "PRODUCT_IMAGE2")
    private Serializable productImage2;
    @Lob
    @Column(name = "PRODUCT_IMAGE3")
    private Serializable productImage3;
    @Lob
    @Column(name = "PRODUCT_IMAGE4")
    private Serializable productImage4;
    @Lob
    @Column(name = "PRODUCT_IMAGE5")
    private Serializable productImage5;
    @Lob
    @Column(name = "PRODUCT_IMAGE6")
    private Serializable productImage6;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private List<OrderDetail> orderDetailList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private List<CartItem> cartItemList;

    public Product() {
    }

    public Product(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Integer getProductSold() {
        return productSold;
    }

    public void setProductSold(Integer productSold) {
        this.productSold = productSold;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Serializable getProductImage1() {
        return productImage1;
    }

    public void setProductImage1(Serializable productImage1) {
        this.productImage1 = productImage1;
    }

    public Serializable getProductImage2() {
        return productImage2;
    }

    public void setProductImage2(Serializable productImage2) {
        this.productImage2 = productImage2;
    }

    public Serializable getProductImage3() {
        return productImage3;
    }

    public void setProductImage3(Serializable productImage3) {
        this.productImage3 = productImage3;
    }

    public Serializable getProductImage4() {
        return productImage4;
    }

    public void setProductImage4(Serializable productImage4) {
        this.productImage4 = productImage4;
    }

    public Serializable getProductImage5() {
        return productImage5;
    }

    public void setProductImage5(Serializable productImage5) {
        this.productImage5 = productImage5;
    }

    public Serializable getProductImage6() {
        return productImage6;
    }

    public void setProductImage6(Serializable productImage6) {
        this.productImage6 = productImage6;
    }

    @XmlTransient
    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    @XmlTransient
    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Product[ productId=" + productId + " ]";
    }

}
