package module6.backend.controller;

import module6.backend.service.IAccountService;
import module6.backend.service.IImportService;
import module6.backend.service.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/import")
public class ImportController {
    @Autowired
    private IImportService importService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IMaterialService materialService;

}
