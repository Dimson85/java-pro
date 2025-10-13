package org.example.task8.entityes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_limits")
public class UserLimitEntity {
    @Id
    private Integer userId;

    @Column(nullable = false)
    private BigDecimal dailyLimit;

    @Column(nullable = false)
    private BigDecimal reserve;

    @Column(nullable = false)
    private BigDecimal defaultLimit;

    @Column(nullable = false)
    private LocalDateTime lastReset;
}
