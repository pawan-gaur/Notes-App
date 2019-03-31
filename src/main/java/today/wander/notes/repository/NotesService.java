package today.wander.notes.repository;

import java.util.List;

import today.wander.notes.model.Notes;


public interface NotesService {
	
	void saveNotes(Notes notes);
	
	List<Notes> getNotes();
	
	Notes findById(long id);
	
	Notes updateNotes(Notes notes, long id);
	
	Object deleteNotes(long id);

}
