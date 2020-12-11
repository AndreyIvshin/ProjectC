package org.example.repository;

import org.example.model.Deal;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface DealRepository extends CrudRepository<Deal, Long> {

    List<Deal> findAllByItem_Id(Long id);

    List<Deal> findAllBySeller_Id(Long id);

    List<Deal> findAllByBuyer_Id(Long id);

    List<Deal> findAllBySeller_IdOrBuyer_Id(Long id, Long iid);
}
