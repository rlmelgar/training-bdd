package com.rlm.training.bdd.infrastructure.mongodb.adapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.rlm.training.bdd.domain.model.Transmission;
import com.rlm.training.bdd.domain.model.TransmissionBuilder;
import com.rlm.training.bdd.infrastructure.mongodb.document.TransmissionDocument;
import com.rlm.training.bdd.infrastructure.mongodb.document.TransmissionDocumentBuilder;
import com.rlm.training.bdd.infrastructure.mongodb.mapper.SpaceshipDocumentMapper;
import com.rlm.training.bdd.infrastructure.mongodb.mapper.TransmissionDocumentMapper;
import com.rlm.training.bdd.infrastructure.mongodb.mapper.TransmissionPetitionDocumentMapper;
import com.rlm.training.bdd.infrastructure.mongodb.repository.TransmissionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransmissionMongodbAdapterTest {

  SpaceshipDocumentMapper spaceshipDocumentMapper = spy(Mappers.getMapper(SpaceshipDocumentMapper.class));

  TransmissionPetitionDocumentMapper transmissionPetitionDocumentMapper = spy(Mappers.getMapper(TransmissionPetitionDocumentMapper.class));

  @InjectMocks
  TransmissionDocumentMapper personDocumentMapper = spy(Mappers.getMapper(TransmissionDocumentMapper.class));

  @Mock
  TransmissionRepository transmissionRepository;

  @InjectMocks
  TransmissionMongodbAdapter transmissionMongodbAdapter;

  @Test
  void whenGetActiveThenReturnsOnlyActiveTransmissions() {
    List<TransmissionDocument> transmissionDocuments = List.of(TransmissionDocumentBuilder.build(), TransmissionDocumentBuilder.build());
    List<Transmission> transmissionsExpected = List.of(TransmissionBuilder.buildFull(), TransmissionBuilder.buildFull());
    // GIVEN
    when(this.transmissionRepository.findAll()).thenReturn(transmissionDocuments);

    // WHEN
    Stream<Transmission> transmissionStream = transmissionMongodbAdapter.getAll();

    // THEN
    if (transmissionsExpected.isEmpty()) {
      assertThat(transmissionStream).isEmpty();
    } else {
      assertThat(transmissionStream).containsExactlyElementsOf(transmissionsExpected);
    }

    final InOrder inOrder = Mockito.inOrder(transmissionRepository);
    inOrder.verify(this.transmissionRepository, Mockito.times(1)).findAll();
  }

  @Test
  void whenFoundTransmissionThenReturnsIt() {
    // GIVEN
    when(this.transmissionRepository.findById(anyString())).thenReturn(Optional.of(TransmissionDocumentBuilder.build()));

    // WHEN
    Optional<Transmission> transmission = transmissionMongodbAdapter.getById(TransmissionBuilder.ID);

    // THEN
    assertThat(transmission).isPresent().get().isEqualTo(TransmissionBuilder.buildFull());

    final InOrder inOrder = Mockito.inOrder(transmissionRepository);
    inOrder.verify(this.transmissionRepository, Mockito.times(1)).findById(anyString());
  }

  @Test
  void whenCreateTransmissionThenReturnsIt() {
    // GIVEN
    when(this.transmissionRepository.insert(any(TransmissionDocument.class)))
        .thenAnswer(invocationOnMock -> ((TransmissionDocument) invocationOnMock.getArgument(0)).setId(TransmissionBuilder.ID));

    // WHEN
    Transmission transmission = transmissionMongodbAdapter.insert(TransmissionBuilder.buildNew());

    // THEN
    assertThat(transmission).usingRecursiveComparison()
        .isEqualTo(TransmissionBuilder.buildNew().setId(TransmissionBuilder.ID));

    final InOrder inOrder = Mockito.inOrder(transmissionRepository);
    inOrder.verify(this.transmissionRepository, Mockito.times(1)).insert(any(TransmissionDocument.class));
  }

}