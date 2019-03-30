package today.wander.notes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import today.wander.notes.model.Notes;
import today.wander.notes.service.NotesService;

@Controller
public class NotesController {

	@Autowired
	private NotesService notesService;

	/*@RequestMapping(value = "/", method = RequestMethod.GET)
    public String listGifs(ModelMap modelMap){
        return "home";
    }*/
	
	@RequestMapping(value = "/newNote", method = RequestMethod.GET)
    public String newNote(){
        return "newNote";
    }
	
	@RequestMapping(value = "/saveNotes", method = RequestMethod.POST)
	public ResponseEntity<String> saveNotes(@RequestBody Notes notes) {
		notesService.saveNotes(notes);
		return new ResponseEntity<String>("Success", HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/allNotes", method = RequestMethod.GET)
	public String viewAllNotes(ModelMap modelMap) {
		List<Notes> notesList = notesService.getNotes();
		modelMap.put("notesList", notesList);
		return "allNotes";
	}
	
	@RequestMapping(value = "/note/{id}", method = RequestMethod.GET)
	public String viewNotes(@PathVariable long id, ModelMap modelMap) {
		Notes note = notesService.findById(id);
		modelMap.put("note", note);
		return "notes";
	}
	
	@RequestMapping(value = "/updateNotes", method = RequestMethod.PUT)
	public ResponseEntity<String> updateNotes(@RequestBody Notes currentNotes) {
		Notes note = notesService.findById(currentNotes.getId());
		if (note == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		notesService.updateNotes(currentNotes, currentNotes.getId());
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Notes> deleteNotes(@PathVariable long id) {
		Notes notes = notesService.findById(id);
		if (notes == null) {
			return new ResponseEntity<Notes>(HttpStatus.NOT_FOUND);
		}
		notesService.deleteNotes(id);
		return new ResponseEntity<Notes>(HttpStatus.OK);
	}

}
