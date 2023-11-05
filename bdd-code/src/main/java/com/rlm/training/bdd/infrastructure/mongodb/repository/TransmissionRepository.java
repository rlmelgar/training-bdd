package com.rlm.training.bdd.infrastructure.mongodb.repository;

import com.rlm.training.bdd.infrastructure.mongodb.document.TransmissionDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransmissionRepository extends MongoRepository<TransmissionDocument, String> {

}
