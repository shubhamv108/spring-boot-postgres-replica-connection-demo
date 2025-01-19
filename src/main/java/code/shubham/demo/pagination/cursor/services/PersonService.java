package code.shubham.demo.pagination.cursor.services;

import code.shubham.demo.pagination.cursor.utils.CursorUtil;
import code.shubham.demo.pagination.cursor.dao.entities.Person;
import code.shubham.demo.pagination.cursor.dao.repositories.read.PersonReadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonReadRepository repository;

    public List<Person> fetchPage(String cursor, int pageSize, boolean isPrevious) {
        Sort sort = Sort.by("createdAt").ascending().and(Sort.by("id").ascending());
        Pageable pageable = PageRequest.of(0, pageSize, sort);

        // Handle the first page (no cursor)
        if (cursor == null) {
            return repository.findAll(pageable).getContent();
        }

        // Decode the cursor
        String decodedCursor = CursorUtil.decode(cursor);
        String[] cursorParts = decodedCursor.split("\\|");

        Instant createdAtCursor = Instant.ofEpochSecond(Long.parseLong(cursorParts[0]), Long.parseLong(cursorParts[1]));
        Timestamp createdAt = Timestamp.from(createdAtCursor);
        Long idCursor = Long.parseLong(cursorParts[2]);

        if (isPrevious) {
            pageable = PageRequest.of(0, pageSize, Sort.by("createdAt").descending().and(Sort.by("id").descending()));
            return repository.findAllByCursorReverse(createdAt, idCursor, pageable);
        }

        return repository.findAllByCursor(createdAt, idCursor, pageable);
    }

    public Person fetchTopByOrderByCreatedAtDescIdDesc() {
        return this.repository.findTopByOrderByCreatedAtDescIdDesc();
    }
}
