package org.example.pimob.application.useCases.broker.getAll;

import org.example.pimob.communication.response.broker.BrokerAllResponse;

import java.util.List;

public interface IBrokerGetAllUseCase {
  List<BrokerAllResponse> execute();
}
