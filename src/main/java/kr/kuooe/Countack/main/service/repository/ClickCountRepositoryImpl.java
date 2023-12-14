package kr.kuooe.Countack.main.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.kuooe.Countack.main.service.dto.CtClickCount;

@Repository
public interface ClickCountRepositoryImpl extends JpaRepository<CtClickCount, String> {
}