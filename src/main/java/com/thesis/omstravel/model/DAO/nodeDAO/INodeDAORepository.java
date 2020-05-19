package com.thesis.omstravel.model.DAO.nodeDAO;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface INodeDAORepository extends MongoRepository<NodeDAO, Long> {
    @Override
    <S extends NodeDAO> List<S> saveAll(Iterable<S> entities);

    NodeDAO findById(String id);

}
