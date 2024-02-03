package com.wanted.preonboarding.ticket.application.pay;

public interface PayMethod {
  int pay(long userPaid, int price);
}
