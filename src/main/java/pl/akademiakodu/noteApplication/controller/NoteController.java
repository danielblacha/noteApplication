package pl.akademiakodu.noteApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akademiakodu.noteApplication.excepion.ResourceNotFoundException;
import pl.akademiakodu.noteApplication.model.Note;
import pl.akademiakodu.noteApplication.repository.NoteRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
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
    @PutMapping("/notes/{id}")
    public Note updateNoteById(@PathVariable(value = "id") Long noteId, @Valid @RequestBody Note noteDetails) {

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());
        return noteRepository.save(note);
    }

    //delete note by id
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
        noteRepository.delete(note);
        return ResponseEntity.ok().build();
    }
}
