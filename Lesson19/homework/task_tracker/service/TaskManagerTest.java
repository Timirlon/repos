package Lesson19.homework.task_tracker.service;

import Lesson19.homework.task_tracker.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public abstract class TaskManagerTest<T extends TaskManager> {
    private final T taskManager;

    public TaskManagerTest(T taskManager) {
        this.taskManager = taskManager;
    }

    @Test
    public void historyHasToBeEmptyIfGetTaskMethodsNotCalled() {
        assertTrue(taskManager.getHistory().isEmpty());
    }

    @Test
    public void historyHasToContainOneTaskIfGetTaskMethodsCalledOnce() {
        Task task = taskManager.createTask(new Task("test1", "test1"));

        taskManager.getTask(1);

        assertEquals(1, taskManager.getHistory().size());
        assertTrue(taskManager.getHistory().contains(task));
    }

    @Test
    public void historyHasToContainThreeTasksIfGetTaskMethodsCalledThrice() {
        Task taskOne = taskManager.createTask(new Task("test1", "test1"));
        Task taskTwo = taskManager.createTask(new Task("test2", "test2"));
        Task taskThree = taskManager.createTask(new Task("test3", "test3"));

        for (int i = 1; i < 4; i++) {
            taskManager.getTask(i);
        }

        assertEquals(3, taskManager.getHistory().size());
        assertTrue(taskManager.getHistory().contains(taskOne));
        assertTrue(taskManager.getHistory().contains(taskTwo));
        assertTrue(taskManager.getHistory().contains(taskThree));
    }

    @Test
    public void getTasksHasToReturnEmptyCollectionIfNoTasksCreated() {
        assertTrue(taskManager.getTasks().isEmpty());
    }

    @Test
    public void getTasksHasToReturnCollectionContainingAllCreatedTasks() {
        Task taskOne = taskManager.createTask(new Task("test1", "test1"));
        Task taskTwo = taskManager.createTask(new Task("test2", "test2"));
        Task taskThree = taskManager.createTask(new Task("test3", "test3"));


        assertEquals(3, taskManager.getTasks().size());
        assertTrue(taskManager.getTasks().contains(taskOne));
        assertTrue(taskManager.getTasks().contains(taskTwo));
        assertTrue(taskManager.getTasks().contains(taskThree));
    }

    @Test
    public void getEpicsHasToReturnEmptyCollectionIfNoEpicsCreated() {
        assertTrue(taskManager.getEpics().isEmpty());
    }

    @Test
    public void getEpicsHasToReturnCollectionContainingAllCreatedEpics() {
        Epic epicOne = taskManager.createEpic(new Epic("test1", "test1"));
        Epic epicTwo = taskManager.createEpic(new Epic("test2", "test2"));
        Epic epicThree = taskManager.createEpic(new Epic("test3", "test3"));


        assertEquals(3, taskManager.getEpics().size());
        assertTrue(taskManager.getEpics().contains(epicOne));
        assertTrue(taskManager.getEpics().contains(epicTwo));
        assertTrue(taskManager.getEpics().contains(epicThree));
    }

    @Test
    public void getSubtasksHasToReturnEmptyCollectionIfNoSubtasksCreated() {
        assertTrue(taskManager.getSubtasks().isEmpty());
    }

    @Test
    public void getSubtasksHasToReturnCollectionContainingAllCreatedSubtasks() {
        Epic epic = taskManager.createEpic(new Epic("test-epic", "test-epic"));

        Subtask subtaskOne = taskManager.createSubtask(new Subtask("test1", "test1", epic));
        Subtask subtaskTwo = taskManager.createSubtask(new Subtask("test2", "test2", epic));
        Subtask subtaskThree = taskManager.createSubtask(new Subtask("test3", "test3", epic));


        assertEquals(3, taskManager.getSubtasks().size());
        assertTrue(taskManager.getSubtasks().contains(subtaskOne));
        assertTrue(taskManager.getSubtasks().contains(subtaskTwo));
        assertTrue(taskManager.getSubtasks().contains(subtaskThree));
    }

    @Test
    public void removeAllMethodShouldLeaveHistoryTasksEpicsAndSubtasksEmpty() {
        Task taskOne = taskManager.createTask(new Task("task-test1", "task-test1"));
        Epic epicOne = taskManager.createEpic(new Epic("epic-test1", "epic-test1"));
        Subtask subtaskOne = taskManager.createSubtask(new Subtask("subtask-test1", "subtask-test1", epicOne));

        taskManager.getTask(1);


        assertFalse(taskManager.getHistory().isEmpty());
        assertFalse(taskManager.getTasks().isEmpty());
        assertFalse(taskManager.getEpics().isEmpty());
        assertFalse(taskManager.getSubtasks().isEmpty());

        taskManager.removeAll();


        assertTrue(taskManager.getHistory().isEmpty());
        assertTrue(taskManager.getTasks().isEmpty());
        assertTrue(taskManager.getEpics().isEmpty());
        assertTrue(taskManager.getSubtasks().isEmpty());
    }

    @Test
    public void getTaskMethodShouldReturnNullIfGivenInvalidIndex() {
        int invalidId = 1;
        assertNull(taskManager.getTask(invalidId));
    }

    @Test
    public void getTaskShouldReturnRequestedTaskIfAllConditionsAreMet() {
        Task task = taskManager.createTask(new Task("test", "test"));

        assertEquals(task, taskManager.getTask(task.getId()));
    }

    @Test
    public void getEpicMethodShouldReturnNullIfGivenInvalidIndex() {
        int invalidId = 1;
        assertNull(taskManager.getEpic(invalidId));
    }

    @Test
    public void getEpicShouldReturnRequestedEpicIfAllConditionsAreMet() {
        Epic epic = taskManager.createEpic(new Epic("test", "test"));

        assertEquals(epic, taskManager.getEpic(epic.getId()));
    }

    @Test
    public void getSubtaskMethodShouldReturnNullIfGivenInvalidIndex() {
        int invalidId = 1;
        assertNull(taskManager.getSubtask(invalidId));
    }

    @Test
    public void getSubtaskShouldReturnRequestedSubtaskIfAllConditionsAreMet() {
        Epic testEpic = taskManager.createEpic(new Epic("epic-test", "epic-test"));

        Subtask subtask = taskManager.createSubtask(new Subtask("test", "test", testEpic));

        assertEquals(subtask, taskManager.getSubtask(subtask.getId()));
    }

    @Test
    public void removeTaskMethodShouldReturnNullIfGivenInvalidIndex() {
        assertNull(taskManager.removeTask(2));
    }

    @Test
    public void someTest() {
        Task task = new Task("test", "test");

        taskManager.createTask(task);
        assertEquals(task, taskManager.removeTask(1));
    }
}
