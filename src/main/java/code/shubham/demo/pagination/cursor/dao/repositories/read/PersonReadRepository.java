package code.shubham.demo.pagination.cursor.dao.repositories.read;

import code.shubham.demo.pagination.cursor.dao.entities.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PersonReadRepository extends JpaRepository<Person, Integer> {

    @Transactional(readOnly = true)
    @Query("SELECT p FROM Person p WHERE (p.createdAt > :createdAt) " +
            "OR (p.createdAt = :createdAt AND p.id > :id)")
    List<Person> findAllByCursor(@Param("createdAt") Timestamp createdAt, @Param("id") Long id, Pageable pageable);

    @Transactional(readOnly = true)
    @Query("SELECT p FROM Person p WHERE (p.createdAt < :createdAt) " +
            "OR (p.createdAt = :createdAt AND p.id < :id)")
    List<Person> findAllByCursorReverse(@Param("createdAt") Timestamp createdAt, @Param("id") Long id, Pageable pageable);

    Person findTopByOrderByCreatedAtDescIdDesc();
}
