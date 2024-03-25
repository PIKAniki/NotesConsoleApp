package views;

import controllers.NotesController;

public class NotesView {
    private NotesController controller;

    public NotesView(NotesController controller) {
        this.controller = controller;
    }

    public void displayMenu() {
        System.out.println("Добро пожаловать в приложение \"Заметки\"!");
        System.out.println("1. Добавить заметку");
        System.out.println("2. Просмотреть заметки");
        System.out.println("3. Редактировать заметку");
        System.out.println("4. Удалить заметку");
        System.out.println("5. Выход");
    }

    public void displayNotes() {
        if (controller.getNotes().isEmpty()) {
            System.out.println("Список заметок: (пусто)");
        } else {
            System.out.println("Список заметок:");
            for (int i = 0; i < controller.getNotes().size(); i++) {
                System.out.println((i + 1) + ". " + controller.getNotes().get(i));
            }
        }
    }

    public void displayEditNoteMenu() {
        displayNotes();
        System.out.print("Введите номер заметки для редактирования: ");
    }

    public void displayDeleteNoteMenu() {
        displayNotes();
        System.out.print("Введите номер заметки для удаления: ");
    }
}
