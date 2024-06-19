package Lesson21_staticFinal.homework.task1_bookMover;

public class MoverFromBorrowed extends BookMover {
    @Override
    protected void moveToStatus (Book requestedBook, Status requestedStatus) {
        if (requestedStatus == Status.AVAILABLE || requestedStatus == Status.ARCHIVED || requestedStatus == Status.OVERDUED) {
            super.moveToStatus(requestedBook, requestedStatus);
        } else {
            System.out.println("Задан недействительный статус.");
        }
    }
}
