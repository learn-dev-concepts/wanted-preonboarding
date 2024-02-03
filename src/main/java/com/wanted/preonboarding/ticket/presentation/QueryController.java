package com.wanted.preonboarding.ticket.presentation;

import com.wanted.preonboarding.core.domain.response.ResponseHandler;
import com.wanted.preonboarding.ticket.application.TicketSeller;
import com.wanted.preonboarding.ticket.domain.dto.PerformanceInfo;
import com.wanted.preonboarding.ticket.domain.dto.ReserveResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("query")
@RequiredArgsConstructor
public class QueryController {
    private final TicketSeller ticketSeller;

    @GetMapping("/all/performance")
    public ResponseEntity<ResponseHandler<List<PerformanceInfo>>> getAllPerformanceInfoList(@RequestParam boolean isReserve) {
        List<PerformanceInfo> list = ticketSeller.getAllPerformanceInfoList(isReserve);

        return ResponseEntity
            .ok()
            .body(ResponseHandler.<List<PerformanceInfo>>builder()
                .message("Success")
                .statusCode(HttpStatus.OK)
                .data(list)
                .build()
            );
    }

    @GetMapping("/performance/{id}")
    public ResponseEntity<ResponseHandler<PerformanceInfo>> getPerformanceInfoDetail(@PathVariable UUID id) {
        PerformanceInfo info = ticketSeller.getPerformanceInfoDetail(id);

        return ResponseEntity
                .ok()
                .body(ResponseHandler.<PerformanceInfo>builder()
                        .message("Success")
                        .statusCode(HttpStatus.OK)
                        .data(info)
                        .build()
                );
    }

    @GetMapping("/performance")
    public ReserveResDto getPerformanceByUserData(@RequestParam String name, @RequestParam String contact) {
        return ticketSeller.getPerformanceInfoByReservationName(name, contact);
    }

}
