package pl.akademiakodu.noteApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiakodu.noteApplication.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
