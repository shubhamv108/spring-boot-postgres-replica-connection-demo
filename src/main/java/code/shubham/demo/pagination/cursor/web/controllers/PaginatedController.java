package code.shubham.demo.pagination.cursor.web.controllers;

import code.shubham.demo.pagination.cursor.services.PersonService;
import code.shubham.demo.pagination.cursor.utils.PageInfoUtils;
import code.shubham.demo.pagination.cursor.dao.entities.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/list")
@RequiredArgsConstructor
public class PaginatedController {

    private final PersonService personService;
    private final PageInfoUtils<Person> pageInfoUtils;

    @GetMapping
    public ResponseEntity<?> getPersons(
            @RequestParam(required = false) String cursor,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "false") boolean isPrevious) {
        List<Person> persons = this.personService.fetchPage(cursor, pageSize, isPrevious);

        Person lastperson = personService.fetchTopByOrderByCreatedAtDescIdDesc();

        Map<String, Object> response = Map.of(
                "edges", persons,
                "pageInfo", pageInfoUtils.getPageInfo(pageSize, persons, lastperson));

        return ResponseEntity.ok(response);
    }

}
