package pl.akademiakodu.noteApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.akademiakodu.noteApplication.excepion.ResourceNotFoundException;
import pl.akademiakodu.noteApplication.model.Note;
import pl.akademiakodu.noteApplication.repository.NoteRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
public class NoteController {


    @Autowired
    private NoteRepository noteRepository;

    //get all notes
    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    //create new note
    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note) {
        return noteRepository.save(note);
    }

    // get a single note
    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable(value = "id") Long noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }
    //update note by id
}
