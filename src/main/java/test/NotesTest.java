package test;
import controllers.NotesController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class NotesTest {
    private InputStream sysInBackup;
    private final NotesController notesController = new NotesController();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        sysInBackup = System.in;
    }
    @After
    public void tearDown() {
        System.setIn(sysInBackup);
    }
    @Test
    public void testValidInput() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextInt()).thenReturn(5);
        assertEquals(5, notesController.validIntInput(scanner));
    }
    @Test
    public void testNegativeValidInput() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextInt()).thenReturn(-5, 5);
        assertEquals(5, notesController.validIntInput(scanner));
    }
    @Test
    public void testNonNumericInput() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextInt()).thenThrow(new InputMismatchException()).thenReturn(5);
        assertEquals(5, notesController.validIntInput(scanner));
    }

    @Test
    public void testAddNote() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn("Title", "Milk", "end");
        assertEquals(0, notesController.getNotes().size());
        notesController.addNote(scanner);
        assertEquals(1, notesController.getNotes().size());
    }

    @Test
    public void testDeleteNote() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextInt()).thenReturn(1);
        when(scanner.nextLine()).thenReturn("Title", "Milk", "end");
        notesController.addNote(scanner);
        assertEquals(1, notesController.getNotes().size());
        notesController.deleteNote(scanner);
        assertEquals(0, notesController.getNotes().size());
    }

    @Test
    public void testValidEmptyText() {
        assertEquals(false, notesController.valid(""));
    }
    @Test
    public void testValidNotEmptyText(){
        assertEquals(true, notesController.valid("Title"));
    }
}