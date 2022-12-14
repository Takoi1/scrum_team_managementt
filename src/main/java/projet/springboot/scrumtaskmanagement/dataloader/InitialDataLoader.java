package projet.springboot.scrumtaskmanagement.dataloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import projet.springboot.scrumtaskmanagement.model.Role;
import projet.springboot.scrumtaskmanagement.model.User;
import projet.springboot.scrumtaskmanagement.model.Task;
import projet.springboot.scrumtaskmanagement.service.RoleService;
import projet.springboot.scrumtaskmanagement.service.TaskService;
import projet.springboot.scrumtaskmanagement.service.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private UserService userService;
    private TaskService taskService;
    private RoleService roleService;
    private final Logger logger = LoggerFactory.getLogger(InitialDataLoader.class);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Value("${default.admin.mail}")
    private String defaultAdminMail;
    @Value("${default.admin.name}")
    private String defaultAdminName;
    @Value("${default.admin.password}")
    private String defaultAdminPassword;
    @Value("${default.admin.image}")
    private String defaultAdminImage;

    @Autowired
    public InitialDataLoader(UserService userService, TaskService taskService, RoleService roleService) {
        this.userService = userService;
        this.taskService = taskService;
        this.roleService = roleService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        //ROLES --------------------------------------------------------------------------------------------------------
        roleService.createRole(new Role("ADMIN"));
        roleService.createRole(new Role("USER"));
        roleService.findAll().stream().map(role -> "saved role: " + role.getRole()).forEach(logger::info);

        //USERS --------------------------------------------------------------------------------------------------------
        //1
        User admin = new User(
                defaultAdminMail,
                defaultAdminName,
                defaultAdminPassword,
                defaultAdminImage);
        userService.createUser(admin);
        userService.changeRoleToAdmin(admin);

        //2
        userService.createUser(new User(
                "takoi@mail.com",
                "takoi",
                "112233",
                "images/user.png"));

        //4

        userService.createUser(new User(
                "ahmed@mail.com",
                "ahmed",
                "112233",
                "images/user.png"));

        //5
        userService.createUser(new User(
                "houssem@mail.com",
                "houssem",
                "112233",
                "images/user.png"));


        userService.findAll().stream()
                .map(u -> "saved user: " + u.getName())
                .forEach(logger::info);


        //TASKS --------------------------------------------------------------------------------------------------------


        LocalDate today = LocalDate.now();

        //1
        taskService.createTask(new Task(
                " name test_01 ",
                "test_01",
                today.minusDays(40),
                true,
                userService.getUserByEmail("takoi@mail.com").getName(),
                userService.getUserByEmail("takoi@mail.com")
        ));

        //2
        taskService.createTask(new Task(
                "name test_02 ",
                "test_02 " ,
                today.minusDays(30),
                true,
                userService.getUserByEmail("ahmed@mail.com").getName(),
                userService.getUserByEmail("ahmed@mail.com")
        ));

        //3
        taskService.createTask(new Task(
                "name task_03",
                "task_03",
                today.minusDays(20),
                true,
                userService.getUserByEmail("houssem@mail.com").getName(),
                userService.getUserByEmail("houssem@mail.com")
        ));

        taskService.findAll().stream().map(t -> "saved task: '" + t.getName()
                + "' for owner: " + getOwnerNameOrNoOwner(t)).forEach(logger::info);
    }

    private String getOwnerNameOrNoOwner(Task task) {
        return task.getOwner() == null ? "no owner" : task.getOwner().getName();
    }
}
