package ch.cern.todo.controller;

import ch.cern.todo.dto.TaskCategoryDTO;
import ch.cern.todo.mapper.CatalogMapper;
import ch.cern.todo.persistence.entity.TaskCategoryEntity;
import ch.cern.todo.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/rest/catalog")
@RestController
public class CatalogController {

    private CatalogService catalogService;

    @Autowired
    private CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @CrossOrigin
    @GetMapping("/categories")
    public ResponseEntity<List<TaskCategoryDTO>> getCategoriesCatalog() {
        List<TaskCategoryEntity> categories = catalogService.getTaskCategories();

        return ResponseEntity.ok().body(CatalogMapper.getCategories(categories));
    }
}
