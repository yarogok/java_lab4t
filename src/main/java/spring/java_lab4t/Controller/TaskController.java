package spring.java_lab4t.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.java_lab4t.Model.Task;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final List<Task> taskList = new ArrayList<>();

    public TaskController() {
        taskList.add(new Task(1, "Task 1", "Description 1"));
        taskList.add(new Task(2, "Task 2", "Description 2"));
        taskList.add(new Task(3, "Task 3", "Description 3"));
        taskList.add(new Task(4, "Task 4", "Description 4"));
    }

    @GetMapping
    public String showTasks(Model model) {
        model.addAttribute("tasks", taskList);
        return "tasks";
    }

    @RequestMapping(value = "/{taskId}", method = {RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String updateTaskStatus(@PathVariable int taskId) {
        for (Task task : taskList) {
            if (task.getId() == taskId) {
                task.setCompleted(!task.isCompleted());
                return "Status updated for Task " + taskId;
            }
        }
        return "Task not found";
    }
}
