package com.wanted.preonboarding.ticket.infrastructure.repository;

import com.wanted.preonboarding.ticket.domain.entity.Alarm;
import com.wanted.preonboarding.ticket.domain.entity.Performance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AlarmRepository extends JpaRepository<Alarm, Integer>  {
  List<Alarm>  findAllByPerformanceId(UUID id);
}
