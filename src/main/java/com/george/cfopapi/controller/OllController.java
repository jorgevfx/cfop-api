package com.george.cfopapi.controller;

import com.george.cfopapi.dto.OllDTO;
import com.george.cfopapi.exception.OllResponse;
import com.george.cfopapi.model.OllModel;
import com.george.cfopapi.service.OllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/oll")
public class OllController {
    @Autowired
    private OllService ollService;
    @GetMapping
    public ResponseEntity<List<OllModel>> getAllOlls(){
        return new ResponseEntity<List<OllModel>>(ollService.allOlls(), HttpStatus.OK);
    }

    @GetMapping("{caseId}")
    public ResponseEntity<?> getSingleOll(
            @PathVariable String caseId
            ){
        Optional<OllModel> singleOll = ollService.singleOll(caseId.toUpperCase());
        if(singleOll.isPresent()){
            OllModel ollModel = singleOll.get();
            OllDTO ollDTO = new OllDTO(
                    ollModel.getCaseId(),
                    ollModel.getNames(),
                    ollModel.getGroup(),
                    ollModel.getAlgs()
            );
            return new ResponseEntity<>(ollDTO, HttpStatus.OK);
        } else {
            OllResponse ollResponse = new OllResponse();
            ollResponse.setStatus(HttpStatus.NOT_FOUND.value());
            ollResponse.setMessage("Oll not found");
            return new ResponseEntity<OllResponse>(ollResponse, HttpStatus.NOT_FOUND);
        }
    }
}
