package com.rlm.training.bdd.infrastructure.mongodb.document;

import java.util.List;

import com.rlm.training.bdd.domain.model.Spaceship;
import com.rlm.training.bdd.domain.model.TransmissionPetition;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transmission")
@Data
@Builder
@Accessors(chain = true)
public class TransmissionDocument {

  @Id
  private String id;

  private SpaceshipDocument spaceship;

  private List<TransmissionPetitionDocument> petitions;

  private String clearanceCode;

  private Boolean active;

}
