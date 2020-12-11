package org.example.service;

import org.example.model.Entity;
import org.example.repository.EntityRepository;
import org.springframework.stereotype.Service;

@Service
public class EntityService extends AbstractService<Entity, Long, EntityRepository> {
}
