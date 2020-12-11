package org.example.service;

import org.example.model.Deal;
import org.example.repository.DealRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DealService extends AbstractService<Deal, Long, DealRepository> {

    public List<Deal> findAllByItem_Id(Long id) {
        return r.findAllByItem_Id(id);
    }

    public List<Deal> findAllBySeller_Id(Long id) {
        return r.findAllBySeller_Id(id);
    }

    public List<Deal> findAllByBuyer_Id(Long id) {
        return r.findAllByBuyer_Id(id);
    }

    List<Deal> findAllBySeller_IdOrBuyer_Id(Long id, Long iid) {
        return r.findAllBySeller_IdOrBuyer_Id(id, iid);
    }

}
