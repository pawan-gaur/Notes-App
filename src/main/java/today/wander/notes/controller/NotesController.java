package today.wander.notes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import today.wander.notes.model.Notes;
import today.wander.notes.model.User;
import today.wander.notes.repository.NotesRepository;
import today.wander.notes.repository.NotesService;
import today.wander.notes.repository.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class NotesController {

    @Autowired
    private NotesService notesService;
    @Autowired
    private NotesRepository notesRepository;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/saveNote", method = RequestMethod.POST, consumes = {"application/x-www-form-urlencoded"})
    public String saveNotes(@ModelAttribute Notes notes, Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        notes.setUser(user);
        notesService.saveNotes(notes);
        model.addAttribute("user", user);
        model.addAttribute("noteSaved", true);
        return "redirect:/home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String viewAllNotes(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<Notes> notesList = notesRepository.findByUser(user);
        model.addAttribute("user", user);
        model.addAttribute("notesList", notesList);
        return "home";
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

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteNotes(@PathVariable long id, Model model, Principal principal) {
        Notes notes = notesService.findById(id);
        if (notes == null) {
            model.addAttribute("isDeleted", false);
        }
        notesService.deleteNotes(id);
        return "redirect:/home";
    }

    @RequestMapping(value = "/notesByTitle", method = RequestMethod.POST)
    public String listNotes(Model model,Principal principal, String title) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("notesList", notesRepository.findByTitleLike(title));
        return "home";
    }

}
