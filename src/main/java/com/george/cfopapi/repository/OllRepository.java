package com.george.cfopapi.repository;

import com.george.cfopapi.model.OllModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface OllRepository extends MongoRepository<OllModel, ObjectId> {
    Optional<OllModel> findByCaseId(String caseId);


}
