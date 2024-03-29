package com.wanted.preonboarding.ticket.application;

import com.wanted.preonboarding.ticket.application.pay.DefaultPayMethod;
import com.wanted.preonboarding.ticket.application.pay.PayMethod;
import com.wanted.preonboarding.ticket.domain.dto.PerformanceInfo;
import com.wanted.preonboarding.ticket.domain.dto.ReserveInfo;
import com.wanted.preonboarding.ticket.domain.dto.ReserveResDto;
import com.wanted.preonboarding.ticket.domain.entity.Alarm;
import com.wanted.preonboarding.ticket.domain.entity.Performance;
import com.wanted.preonboarding.ticket.domain.entity.Reservation;
import com.wanted.preonboarding.ticket.infrastructure.repository.AlarmRepository;
import com.wanted.preonboarding.ticket.infrastructure.repository.PerformanceRepository;
import com.wanted.preonboarding.ticket.infrastructure.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TicketSeller {
    private final PerformanceRepository performanceRepository;
    private final ReservationRepository reservationRepository;
    private final AlarmRepository alarmRepository;

    private final DefaultPayMethod payMethod;
    private long totalAmount = 0L;

    public List<PerformanceInfo> getAllPerformanceInfoList(boolean isReserve) {
        String status = isReserve ? "enable" : "disable";
        return performanceRepository.findByIsReserve(status)
            .stream()
            .map(PerformanceInfo::of)
            .toList();
    }

    public PerformanceInfo getPerformanceInfoDetail(UUID id) {
        Performance info = performanceRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return PerformanceInfo.of(info);
    }


    // 문제1
    public ReserveResDto reserve(ReserveInfo reserveInfo) {
        log.info("reserveInfo ID => {}", reserveInfo.getPerformanceId());
        Performance info = performanceRepository.findById(reserveInfo.getPerformanceId())
            .orElseThrow(EntityNotFoundException::new);

        String enableReserve = info.getIsReserve();
        if (enableReserve.equalsIgnoreCase("enable")) {
            // 1. 결제

            int amount = payMethod.pay(reserveInfo.getAmount(), info.getPrice());
            reserveInfo.setAmount(amount);
            // 2. 예매 진행
            reservationRepository.save(Reservation.of(reserveInfo));
            return ReserveResDto.of(info, reserveInfo);

        } else {
            return null;
        }
    }



    // 문제2
    public ReserveResDto getPerformanceInfoByReservationName(String name, String contact) {
        Reservation reserveInfo = reservationRepository.findByNameAndPhoneNumber(name, contact);
        Performance info = performanceRepository.findById(reserveInfo.getPerformanceId())
                .orElseThrow(EntityNotFoundException::new);
        return ReserveResDto.of(info, reserveInfo);
    }

    @Transactional
    public void cancelReservation(UUID id) {
        reservationRepository.deleteByPerformanceId(id);
        List<Alarm> list = alarmRepository.findAllByPerformanceId(id);
        list.stream().forEach((a) -> sendMessage(a));

    }

    private void sendMessage(Alarm alarm) {
        log.info("send Message: >> ");
    }
}
