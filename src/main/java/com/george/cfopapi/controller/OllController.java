package com.george.cfopapi.controller;

import com.george.cfopapi.dto.AlgorithmDTO;
import com.george.cfopapi.dto.NewAlgorithmDTO;
import com.george.cfopapi.dto.OllDTO;
import com.george.cfopapi.model.AlgorithmModel;
import com.george.cfopapi.model.OllModel;
import com.george.cfopapi.service.OllService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/oll")
public class OllController {
    @Autowired
    private OllService ollService;

    /*
     GET REQUESTS
   */
    @GetMapping
    public ResponseEntity<List<OllModel>> getAllOlls(){
        return new ResponseEntity<>(ollService.allOlls(), HttpStatus.OK);
    }

    @GetMapping("{caseId}")
    public ResponseEntity<List<OllDTO>> getSingleOll(
            @PathVariable String caseId
            ){
        return new ResponseEntity<>(ollService.singleOll(caseId.toUpperCase()), HttpStatus.OK);

    }

    /*
     POST REQUESTS
    */
    @PostMapping("{caseId}")
    public ResponseEntity<AlgorithmDTO> createAlg(
            @Valid @RequestBody AlgorithmDTO algorithmDTO,
            @PathVariable String caseId
    ){
        return new ResponseEntity<>(ollService.createAlg(
                algorithmDTO, caseId.toUpperCase()),
                HttpStatus.CREATED);
    }

    /*
     PUT REQUESTS
    */
    @PutMapping("{caseId}")
    public ResponseEntity<NewAlgorithmDTO> updateAlg(
            @Valid @RequestBody NewAlgorithmDTO newAlgorithmDTO,
            @PathVariable String caseId
    ){
        return new ResponseEntity<>(ollService.updateAlg(
                caseId.toUpperCase(), newAlgorithmDTO),
                HttpStatus.OK);
    }
}
