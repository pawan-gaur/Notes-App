package today.wander.notes.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import today.wander.notes.model.Notes;
import today.wander.notes.repository.NotesRepository;
import today.wander.notes.repository.NotesService;

@Service
@Transactional
public class NotesServiceImpl implements NotesService {

	@Autowired
	private NotesRepository notesRepository;

	public List<Notes> getNotes() {
		return notesRepository.findAll();
	}

	public void saveNotes(Notes notes) {
		notesRepository.save(notes);
	}

	public Notes findById(long id) {
		return notesRepository.findByid(id);
	}

	public Notes updateNotes(Notes notes, long id) {
		return notesRepository.save(notes);
	}

	public Object deleteNotes(long id) {
		return notesRepository.deleteByid(id);
	}

	public List<Notes> findByTitleName(String name) {
		return notesRepository.findByTitleLike("%" + name + "%");
	}

}
