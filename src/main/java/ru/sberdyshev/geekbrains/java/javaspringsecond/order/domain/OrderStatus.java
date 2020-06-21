package ru.sberdyshev.geekbrains.java.javaspringsecond.order.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sberdyshev.geekbrains.java.javaspringsecond.general.config.ObjectJsonPrinterConfig;

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
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private UUID id;

    //    @OneToOne(fetch = FetchType.EAGER)
//    @OneToOne(mappedBy = "status", cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY, optional = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "status_id")
    private Status statusData;

    @Column(name = "time_changed")
    private Date timeChanged;

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
