package com.itemis.salestax.controller;

import com.itemis.salestax.common.Response;
import com.itemis.salestax.model.ImportDuty;
import com.itemis.salestax.service.ImportDutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/import-duty")

public class ImportDutyController {

    @Autowired
    private ImportDutyService importDutyService;

    @GetMapping("/")
    public ResponseEntity<List<ImportDuty>> getImportDuty() {
        List<ImportDuty> body = importDutyService.listImportDuty();
        return new ResponseEntity<List<ImportDuty>>(body, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addImportDuty(@Valid @RequestBody ImportDuty importDuty) {

        if (importDutyService.readImportDuty(importDuty.getDutyName()) != null) {
            return new ResponseEntity<Response>(new Response(false, "import duty already exists"), HttpStatus.CONFLICT);
        }
        importDutyService.addImportDuty(importDuty);
        return new ResponseEntity<Response>(new Response(true, "created imported duty"), HttpStatus.CREATED);
    }

    @PostMapping("/update/{importDutyId}")
    public ResponseEntity<Response> updateImportDuty(@PathVariable("importDutyId") Integer importDutyId, @Valid @RequestBody ImportDuty duty) {
        // Check to see if the import duty exists.
        if (importDutyService.readImportDuty(importDutyId).isPresent()) {
            // If the import duty exists then update it.
            importDutyService.updateImportDuty(importDutyId, duty);
            return new ResponseEntity<Response>(new Response(true, "updated import duty"), HttpStatus.OK);
        }

        // If the import duty doesn't exist then return a response of unsuccessful.
        return new ResponseEntity<Response>(new Response(false, "import duty does not exist"), HttpStatus.NOT_FOUND);
    }
}
