package com.george.cfopapi.service;

import com.george.cfopapi.model.OllModel;
import com.george.cfopapi.repository.OllRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OllService {
    @Autowired
    private OllRepository ollRepository;
    public List<OllModel> allOlls() {
        return ollRepository.findAll();
    }

    public Optional<OllModel> singleOll(String caseId){
        return ollRepository.findByCaseId(caseId);
    }
}
