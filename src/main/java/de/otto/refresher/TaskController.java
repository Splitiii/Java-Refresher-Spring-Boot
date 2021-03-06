package de.otto.refresher;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.otto.refresher.buisness.Task;
import de.otto.refresher.buisness.TasksMap;

@Controller
@RequestMapping("/")
public class TaskController {

    @Value("${application.addTaskErrorMessage}")
    private String addTaskErrorMessage;
    private TasksMap tasks = new TasksMap();

    @RequestMapping(method = RequestMethod.GET)
    public String taskForm(Model model) {
        model.addAttribute("notDoneTasks", tasks.getUndone());
        model.addAttribute("doneTasks", tasks.getDone());
        model.addAttribute("newTask", new Task());
        return "tasks";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String taskSubmit(@ModelAttribute Task newTask, RedirectAttributes attr) {
        if (!newTask.getMessage().trim().isEmpty()) {
            newTask.setCreatedOn(new Date());
            //todo add task to TaskMap
        } else {
            attr.addFlashAttribute("addTaskError", addTaskErrorMessage);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/done", method = RequestMethod.POST)
    public String setTaskDone(@RequestParam("id") String stringTaskId) {
        //todo set task to done
        return "redirect:/";
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public String removeTask(@RequestParam("id") String stringTaskId) {
        //todo delete submited task
        return "redirect:/";
    }
}
