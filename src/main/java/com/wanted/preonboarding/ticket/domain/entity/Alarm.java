package com.wanted.preonboarding.ticket.domain.entity;

import com.wanted.preonboarding.ticket.domain.dto.ReserveInfo;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@ToString
@Entity
@Table
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Alarm {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(columnDefinition = "BINARY(16)", nullable = false, name = "performance_id")
  private UUID performanceId;
  @Column(nullable = false)
  private String name;
  @Column(nullable = false, name = "phone_number")
  private String phoneNumber;

  public static Alarm of(ReserveInfo info) {
    return Alarm.builder()
            .performanceId(info.getPerformanceId())
            .name(info.getReservationName())
            .phoneNumber(info.getReservationPhoneNumber())
            .build();
  }
}
