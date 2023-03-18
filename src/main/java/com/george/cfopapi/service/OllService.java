package com.george.cfopapi.service;

import com.george.cfopapi.dto.AlgorithmDTO;
import com.george.cfopapi.dto.NewAlgorithmDTO;
import com.george.cfopapi.dto.OllDTO;
import com.george.cfopapi.exception.CustomErrorHandler;
import com.george.cfopapi.model.AlgorithmModel;
import com.george.cfopapi.model.OllModel;
import com.george.cfopapi.repository.OllRepository;
import com.mongodb.MongoException;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class OllService {
    @Autowired
    private OllRepository ollRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<OllModel> allOlls() {
        return ollRepository.findAll();
    }

    public List<OllDTO> singleOll(String caseId){
        Optional<OllModel> singleOll = ollRepository.findByCaseId(caseId);
        if(singleOll.isPresent()){
            return List.of(convertOllToDTO(singleOll.get()));
        } else {
            throw new CustomErrorHandler("Oll not found");
        }
    }

    private OllDTO convertOllToDTO(OllModel ollModel){
        return new OllDTO(
                ollModel.getCaseId(),
                ollModel.getNames(),
                ollModel.getGroup(),
                ollModel.getAlgs()
        );
    }

    public AlgorithmDTO createAlg(AlgorithmDTO algorithmDTO, String caseId){
        Optional<OllModel> singleOll = ollRepository.findByCaseId(caseId);
        if(singleOll.isEmpty()){
            throw new CustomErrorHandler("Oll not found");
        }

        try {
            mongoTemplate.update(OllModel.class)
                    .matching(Criteria.where("caseId").is(caseId))
                    .apply(new Update().push("algs", new AlgorithmModel(algorithmDTO.getMoves())))
                    .first();
            return algorithmDTO;
        } catch (MongoException ex) {
            throw new CustomErrorHandler("Error while creating algorithm");
        }
    }

    public NewAlgorithmDTO updateAlg(@PathVariable String caseId, @RequestBody NewAlgorithmDTO newAlgorithmDTO) {
        OllModel ollModel = ollRepository.findByCaseId(caseId)
                .orElseThrow(() -> new CustomErrorHandler("Oll not found"));

        AlgorithmModel algorithmModel = ollModel.getAlgs().stream()
                .filter(algorithm -> algorithm.getMoves().equals(newAlgorithmDTO.getOldAlg()))
                .findFirst()
                .orElseThrow(() -> new CustomErrorHandler("Algorithm not found"));

        try{
            if (newAlgorithmDTO.getOldAlg().equals(newAlgorithmDTO.getNewAlg())) {
                throw new CustomErrorHandler("The new and old algorithms cannot be the same");
            }
        } catch (NullPointerException ex){
            throw new CustomErrorHandler("Error while updating algorithm");
        }

        try {
            mongoTemplate.update(OllModel.class)
                    .matching(Criteria.where("caseId").is(caseId).and("algs.moves").is(newAlgorithmDTO.getOldAlg()))
                    .apply(new Update().set("algs.$.moves", newAlgorithmDTO.getNewAlg()))
                    .first();
        } catch (MongoException ex) {
            throw new CustomErrorHandler("Error while updating algorithm");
        }

        return newAlgorithmDTO;
    }


}
