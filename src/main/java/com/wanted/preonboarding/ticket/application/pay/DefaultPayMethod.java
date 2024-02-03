package com.wanted.preonboarding.ticket.application.pay;

import org.springframework.stereotype.Component;

@Component
public class DefaultPayMethod implements PayMethod{

  @Override
  public int pay(long userPaid, int price) {
    return 0;
  }
}

