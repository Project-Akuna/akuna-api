package ph.com.adcpp.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import ph.com.adcpp.commons.constant.InventoryType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity = 0;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private InventoryType inventoryType;

    @OneToOne
    @JoinColumn(name = "OWNER_USER_ID")
    private User ownerUser;

    @OneToOne
    @JoinColumn(name = "OWNER_DEPOT_ID")
    private Depot ownerDepot;

    @OneToOne
    @JoinColumn(name = "OWNER_ADC_ID")
    private ADC ownerAdc;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventory")
    @JsonManagedReference
    private List<InventoryHistory> history = new ArrayList<>();

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "INVENTORY_ID"), inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    @JsonManagedReference
    private List<Product> products = new ArrayList<>();

    private LocalDateTime dtimeCreated;
    private LocalDateTime dtimeUpdated;

    public Inventory(User user) {
        this.ownerUser = user;
    }

    public Inventory(Product product) {
        this.products.add(product);
    }

    public Inventory(InventoryType inventoryType) {
        this.inventoryType = inventoryType;
    }

    public Inventory(Depot ownerDepot) {
        this.ownerDepot = ownerDepot;
    }

    public Inventory(ADC ownerAdc) {
        this.ownerAdc = ownerAdc;
    }

    public Inventory() {
    }

    @PrePersist
    protected void onCreate() {
        this.dtimeCreated = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.dtimeUpdated = LocalDateTime.now();
    }
}