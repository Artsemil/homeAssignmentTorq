package uitest;

import org.testng.Assert;
import org.testng.annotations.Test;
import verifiers.TasksVerifier;

import java.util.List;

public class FunctionalSanityTest extends BaseTest{

    private static final String ADD_TASK = "Add task";
    private static final String DELETE_TASK = "Delete task";
    private static final String COMPLETED_TASK = "Completed task";
    private static final String CLEAR_COMPLETED_TASK = "Clear ompleted task";
    private static final String COUNTER_TASK = "Counter task";

    @Test
    public void verifyAddNewTask() {
        todoVMCPage.selectAllTasks();
        todoVMCPage.addNewTask(ADD_TASK);
        List<String> allTasks = todoVMCPage.getListOfTasks();
        TasksVerifier.verifyTaskExists(allTasks, ADD_TASK);
        todoVMCPage.selectActiveTasks();
        TasksVerifier.verifyTaskExists(todoVMCPage.getListOfTasks(), ADD_TASK);
        todoVMCPage.selectCompletedTasks();
        TasksVerifier.verifyTaskDoesntExist(todoVMCPage.getListOfTasks(), ADD_TASK);
    }

    @Test
    public void verifyDeletedTask() {
        todoVMCPage.addNewTask(DELETE_TASK);
        TasksVerifier.verifyTaskExists(todoVMCPage.getListOfTasks(), DELETE_TASK);
        todoVMCPage.deleteTask(DELETE_TASK);
        TasksVerifier.verifyTaskDoesntExist(todoVMCPage.getListOfTasks(), DELETE_TASK);
        todoVMCPage.selectActiveTasks();
        TasksVerifier.verifyTaskDoesntExist(todoVMCPage.getListOfTasks(), DELETE_TASK);
        todoVMCPage.selectCompletedTasks();
        TasksVerifier.verifyTaskDoesntExist(todoVMCPage.getListOfTasks(), DELETE_TASK);
    }

    @Test
    public void verifyCompletedTask() {
        todoVMCPage.addNewTask(COMPLETED_TASK);
        TasksVerifier.verifyTaskExists(todoVMCPage.getListOfTasks(), COMPLETED_TASK);
        todoVMCPage.makeTaskCompleted(COMPLETED_TASK);
        TasksVerifier.verifyTaskIsComplete(todoVMCPage, COMPLETED_TASK);
        todoVMCPage.selectActiveTasks();
        TasksVerifier.verifyTaskDoesntExist(todoVMCPage.getListOfTasks(), COMPLETED_TASK);
        todoVMCPage.selectCompletedTasks();
        TasksVerifier.verifyTaskExists(todoVMCPage.getListOfTasks(), COMPLETED_TASK);
    }

    @Test
    public void verifyClearCompletedTask() {
        todoVMCPage.addNewTask(CLEAR_COMPLETED_TASK);
        todoVMCPage.makeTaskCompleted(CLEAR_COMPLETED_TASK);
        todoVMCPage.selectCompletedTasks();
        TasksVerifier.verifyTaskExists(todoVMCPage.getListOfTasks(), CLEAR_COMPLETED_TASK);
        todoVMCPage.clearCompletedTasks();
        TasksVerifier.verifyTaskDoesntExist(todoVMCPage.getListOfTasks(), CLEAR_COMPLETED_TASK);
        todoVMCPage.selectAllTasks();
        TasksVerifier.verifyTaskDoesntExist(todoVMCPage.getListOfTasks(), CLEAR_COMPLETED_TASK);
        todoVMCPage.selectActiveTasks();
        TasksVerifier.verifyTaskDoesntExist(todoVMCPage.getListOfTasks(), CLEAR_COMPLETED_TASK);
    }

    @Test
    public void verifyTasksCounter() {
        int tasksBefore = todoVMCPage.getTasksAmount();
        todoVMCPage.addNewTask(COUNTER_TASK);
        TasksVerifier.verifyTaskExists(todoVMCPage.getListOfTasks(), COUNTER_TASK);
        Assert.assertEquals(todoVMCPage.getTasksAmount(), tasksBefore + 1, "Task counter wasn't increased");
    }
}
