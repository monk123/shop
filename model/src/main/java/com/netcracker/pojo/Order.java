package com.netcracker.pojo;

import lombok.*;
import lombok.extern.java.Log;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple JavaBean object of representation an Order
 *
 * @author Ayupov Vadim
 * @version 1.0
 */

@NoArgsConstructor @Log
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    private static final Long serialVersionUID = 8L;

    @Column(name = "ORDER_DATE")
    @Getter @Setter
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "M/dd/yyyy hh:mm:ss a")
    private Date orderDate;

    @Column(name = "AMOUNT")
    @Getter @Setter
    private double amount;

    @Column(name = "ORDER_NUMBER")
    @Getter @Setter
    private int orderNumber;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @Getter @Setter
    private User user;

    @OneToMany(mappedBy = "order")
    @Getter @Setter
    private Set<OrderDetail> products = new HashSet<>();

    public Order(Date orderDate, double amount, int orderNumber) {
        this.orderDate = orderDate;
        this.amount = amount;
        this.orderNumber = orderNumber;
    }

    public Order(Date orderDate, double amount, int orderNumber, User user, Set<OrderDetail> products) {
        this.orderDate = orderDate;
        this.amount = amount;
        this.orderNumber = orderNumber;
        this.user = user;
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        if (!super.equals(o)) return false;

        Order order = (Order) o;

        if (Double.compare(order.amount, amount) != 0) return false;
        if (orderNumber != order.orderNumber) return false;
        return orderDate != null ? orderDate.equals(order.orderDate) : order.orderDate == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + orderNumber;
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderDate=" + orderDate +
                ", amount=" + amount +
                ", orderNumber=" + orderNumber +
                '}';
    }
}
