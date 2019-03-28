package today.wander.notes.service;

import java.util.List;

import today.wander.notes.model.Notes;


public interface NotesService {
	
	public void saveNotes(Notes notes);
	
	public List<Notes> getNotes();
	
	public Notes findById(long id);
	
	public Notes updateNotes(Notes notes, long id);
	
	public Object deleteNotes(long id);

}
