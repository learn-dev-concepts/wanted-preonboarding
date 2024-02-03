package com.wanted.preonboarding.ticket.presentation;

import com.wanted.preonboarding.core.domain.response.ResponseHandler;
import com.wanted.preonboarding.ticket.application.TicketSeller;
import com.wanted.preonboarding.ticket.domain.dto.ReserveInfo;
import com.wanted.preonboarding.ticket.domain.dto.ReserveResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Slf4j
@RestController
@RequestMapping("/reserve")
@RequiredArgsConstructor
public class ReserveController {
  private final TicketSeller ticketSeller;

  @PostMapping("/")
  public ResponseEntity<ResponseHandler<ReserveResDto>> reservation(@RequestBody ReserveInfo info) {
    ReserveResDto result = ticketSeller.reserve(info);
    return ResponseEntity
            .ok()
            .body(ResponseHandler.<ReserveResDto>builder()
                    .message("Success")
                    .statusCode(HttpStatus.OK)
                    .data(result)
                    .build());
  }


  @DeleteMapping("/{id}")
  public void deleteReservation(@PathVariable UUID id) {
    ticketSeller.cancelReservation(id);
  }
}
