package today.wander.notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import today.wander.notes.model.Notes;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {

	Notes findByid(long id);

	Object deleteByid(long id);

}
