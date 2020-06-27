package ru.sberdyshev.geekbrains.java.javaspringsecond.order.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

//todo добавить toString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ord_order_status")
public class OrderStatus {

    @Id
    @Column(name = "ID", unique = true)
//    @GeneratedValue
//    @GeneratedValue(generator="foreign")
//    @GenericGenerator(name="foreign", strategy = "foreign", parameters={
//            @Parameter(name="property", value="order")
//    })
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "status_id")
    private Status statusData;

    @Column(name = "time_changed")
    private Date timeChanged;

//    @OneToOne(cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY,
//            optional = false,
//            orphanRemoval = true)
//    @PrimaryKeyJoinColumn

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false,
            orphanRemoval = true)
    @MapsId
    @JoinColumn(name = "id",
            foreignKey = @ForeignKey(name = "fk_ord_order_status_table_to_ord_order_table"))
    private Order order;

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String objectAsJson = mapper.writeValueAsString(this);
            return objectAsJson;
        } catch (JsonProcessingException jsonProcessingException) {
            return this.getClass().getName();
        }
    }
}
