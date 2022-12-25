package app.filerepository.rederview.controller;

import app.filerepository.rederview.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Reader {
    @Autowired
    private ReaderService service;

    @GetMapping("/files")
    public List<String> getListFiles() {
        return service.getAllFiles();
    }
}