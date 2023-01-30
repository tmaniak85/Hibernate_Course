package pl.nullpointerexception.hibernate.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDateTime created;
    private LocalDateTime updated;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;

    @ElementCollection
    private List<Address> address;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL, optional = false)
    private CustomerDetails customerDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> addressList) {
        this.address = addressList;
    }

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
