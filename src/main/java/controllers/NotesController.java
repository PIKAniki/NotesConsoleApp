package controllers;//package controllers;
//
//import managers.NotesManager;
//import model.Note;
//import views.NotesView;
//
//import java.util.List;
//
//import static views.NotesView.addNoteFromView;
//
//public class NotesController {
//    private final NotesManager notesManager;
//
//    public NotesController() {
//        this.notesManager = new NotesManager();
//    }
//
//    public void addNote() {
//        notesManager.addNewNote(addNoteFromView());
//    }
//
//    public void editNote(){
////        notesManager.editNote(index, newText);
//    }
//    public void getNotes() {
//        List<Note> notes = notesManager.getNotes();
//        NotesView.displayNotes(notes);
//    }
//
//    public void deleteNote() {
////        notesManager.deleteNote(ind);
//    }
//}
import model.Note;
import views.NotesView;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NotesController {
    private ArrayList<Note> notes;
    private NotesView view;
    private Scanner scanner;

    public NotesController() {
        notes = new ArrayList<>();
        view = new NotesView(this);
        scanner = new Scanner(System.in);
    }

    public void start() {
        view.displayMenu();
        while (true) {
            System.out.print("Выберите действие (введите номер): ");
            int choice = validIntInput(scanner);
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addNote(scanner);
                    break;
                case 2:
                    view.displayNotes();
                    break;
                case 3:
                    editNote();
                    break;
                case 4:
                    deleteNote(scanner);
                    break;
                case 5:
                    System.out.println("До свидания!");
                    return;
                default:
                    System.out.println("Некорректный ввод. Попробуйте еще раз.");
            }
        }
    }

    public Boolean valid(String text) {
        if (text.isEmpty()) {
            System.out.println("Не может быть пустым");
            return false;
        }
        return true;
    }
    public int validIntInput(Scanner scanner) {
        int number = -1;
        while (number < 0) {
            try {
                number = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Можно ввести только положительное число: ");
                scanner.next();
            }
        }
        return number;
    }

    public void addNote(Scanner scanner) {
        System.out.print("Введите заголовок заметки: ");
        String title = scanner.nextLine();
        while (!valid(title)) {
            System.out.print("Введите заголовок заметки: ");
            title = scanner.nextLine();
        }
        System.out.println("Введите текст заметки (для завершения введите \"end\"): ");
        StringBuilder text = new StringBuilder();
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("end")) {
                break;
            }
            text.append(line).append("\n");
        }
        notes.add(new Note(title, text.toString()));
        System.out.println("Заметка успешно добавлена.");
    }

    public Boolean checkEmptyNotes() {
        if (notes.isEmpty()) {
            System.out.println("Нет заметок для действия с ними");
            return true;
        }
        return false;
    }


    private void editNote() {
        if (checkEmptyNotes()){return;};
        view.displayEditNoteMenu();
        int index = validIntInput(scanner) - 1;
        scanner.nextLine();
        if (index < 0 || index >= notes.size()) {
            System.out.println("Некорректный номер заметки.");
            return;
        }
        System.out.println("Введите новый текст заметки (для завершения введите \"end\"): ");
        StringBuilder text = new StringBuilder();
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("end")) {
                break;
            }
            text.append(line).append("\n");
        }
        String title = notes.get(index).getTitle();
        notes.get(index).setText(text.toString());
        System.out.println("Заметка " + title + " успешно отредактирована.");
    }

    public void deleteNote(Scanner scanner) {
        if (checkEmptyNotes()){return;};
        view.displayDeleteNoteMenu();
        int index = validIntInput(scanner) - 1;
        scanner.nextLine();
        if (index < 0 || index >= notes.size()) {
            System.out.println("Некорректный номер заметки.");
            return;
        }
        String title = notes.get(index).getTitle();
        notes.remove(index);
        System.out.println("Заметка "+ title +" успешно удалена.");
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }
}
