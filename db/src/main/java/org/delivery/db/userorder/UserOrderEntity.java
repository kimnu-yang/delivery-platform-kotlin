package org.delivery.db.userorder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.userorder.enums.UserOrderStatus;
import org.delivery.db.userordermenu.UserOrderMenuEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "user_order")
public class UserOrderEntity extends BaseEntity {

    @Column(nullable = false)
    private Long userId;

    // 자동으로 이름을 변환하여 매칭을 하기 때문에 변수명을 변경하거나 joinColumn에 name값을 줘야함
    @JoinColumn(nullable = false)
    @ManyToOne
    private StoreEntity store;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private UserOrderStatus status;

    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal amount;

    private LocalDateTime orderedAt;

    private LocalDateTime acceptedAt;

    private LocalDateTime cookingStartedAt;

    private LocalDateTime deliveryStartedAt;

    private LocalDateTime receivedAt;

    @OneToMany(mappedBy = "userOrder")
    // UserOrderEntity를 사용할때 Entity에 대해 toSting 호출을 하는데 아래 매핑된 데이터들 또한 toString을 하게되며 반복적으로 호출을하며 에러를 발생시킨다.
    // 그러므로 이 Entity에 대해 toString 호출하지 않도록 설정을 해주어야 한다.
    @ToString.Exclude
    // 위와 비슷하게 UserOrderEntity를 호출할때 Json으로 파싱하는 과정에서 반복적인 호출이 에러를 발생시킨다
    // 아래 어노테이션으로 Json 파싱을 막아준다.
    @JsonIgnore
    private List<UserOrderMenuEntity> userOrderMenuList;
}
