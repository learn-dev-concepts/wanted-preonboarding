package com.wanted.preonboarding.ticket.domain.dto;

import com.wanted.preonboarding.ticket.domain.entity.Performance;
import com.wanted.preonboarding.ticket.domain.entity.Reservation;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Data
@Builder
public class ReserveResDto {

  private UUID performanceId;
  private String performanceName;
  private int round;
  private int seat;
  private String customerName;
  private String contact;

  public static ReserveResDto of(Performance entity, ReserveInfo reserveInfo) {
    return ReserveResDto.builder()
            .performanceId(entity.getId())
            .performanceName(entity.getName())
            .round(entity.getRound())
            .seat(reserveInfo.getSeat())
            .customerName(reserveInfo.getReservationName())
            .contact(reserveInfo.getReservationPhoneNumber())
            .build();
  }

  public static ReserveResDto of(Performance entity, Reservation reserveInfo) {
    return ReserveResDto.builder()
            .performanceId(entity.getId())
            .performanceName(entity.getName())
            .round(entity.getRound())
            .seat(reserveInfo.getSeat())
            .customerName(reserveInfo.getName())
            .contact(reserveInfo.getPhoneNumber())
            .build();
  }

}
