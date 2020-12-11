package org.example.service;

import org.example.model.Unit;
import org.example.repository.UnitRepository;
import org.springframework.stereotype.Service;

@Service
public class UnitService extends AbstractService<Unit, Long, UnitRepository> {
}
