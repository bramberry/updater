package by.vsu.bramberry.updateChecker.model.service.iservice;

import by.vsu.bramberry.updateChecker.model.entity.Request;

import java.util.List;

public interface RequestService {
    Request save(Request request);

    void delete(Long id);

    List<Request> findByUserId(Long id);

    Request update(Request request);

    List<Request> findAll();
}
