package com.wanted.preonboarding.ticket.presentation;

import com.wanted.preonboarding.core.domain.response.ResponseHandler;
import com.wanted.preonboarding.ticket.application.TicketSeller;
import com.wanted.preonboarding.ticket.domain.dto.PerformanceInfo;
import com.wanted.preonboarding.ticket.domain.dto.ReserveResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("query")
@RequiredArgsConstructor
public class QueryController {
    private final TicketSeller ticketSeller;

    @GetMapping("/all/performance")
    public ResponseEntity<ResponseHandler<List<PerformanceInfo>>> getAllPerformanceInfoList() {
        System.out.println("getAllPerformanceInfoList");
        return ResponseEntity
            .ok()
            .body(ResponseHandler.<List<PerformanceInfo>>builder()
                .message("Success")
                .statusCode(HttpStatus.OK)
                .data(ticketSeller.getAllPerformanceInfoList())
                .build()
            );
    }

    @GetMapping("/performance")
    public ReserveResDto getPerformanceByUserData(@RequestParam String name, @RequestParam String contact) {
        log.info("name: {}", name);
        log.info("contact: {}", contact);

        return ticketSeller.getPerformanceInfoByReservationName(name, contact);
    }
}
