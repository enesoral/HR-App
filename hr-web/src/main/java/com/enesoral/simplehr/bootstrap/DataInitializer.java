package com.enesoral.simplehr.bootstrap;

import com.enesoral.simplehr.models.*;
import com.enesoral.simplehr.services.ApplicationService;
import com.enesoral.simplehr.services.DepartmentService;
import com.enesoral.simplehr.services.JobService;
import com.enesoral.simplehr.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@Component
public class DataInitializer implements CommandLineRunner {
    private final ApplicationService applicationService;
    private final JobService jobService;
    private final UserService userService;
    private final DepartmentService departmentService;

    public DataInitializer(ApplicationService applicationService, JobService jobService, UserService userService, DepartmentService departmentService) {
        this.applicationService = applicationService;
        this.jobService = jobService;
        this.userService = userService;
        this.departmentService = departmentService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = (int) jobService.findAll(PageRequest.of(0, Integer.MAX_VALUE)).getTotalElements();

        if (count == 0) loadData();
    }

    private void loadData() {
        Department marketing = new Department();
        marketing.setName("Sales & Marketing");
        departmentService.save(marketing);

        Department it = new Department();
        it.setName("Information Technology");
        departmentService.save(it);

        Department humanResources = new Department();
        humanResources.setName("Human Resources");
        departmentService.save(humanResources);

        Department finance = new Department();
        finance.setName("Accounting and Finance");
        departmentService.save(finance);

        Department production = new Department();
        production.setName("Production");
        departmentService.save(production);

        String descriptionMsg = "We are looking for employee who loves his job and doing tasks with passion!";
        Job softwareEngineerIntern = Job.builder().title("Software Engineer Intern").description(descriptionMsg)
                .numberOfHire(3).department(it).jobType(JobType.TYPE_INTERN)
                .publishDate(LocalDate.now().atTime(13, 0)).lastApplicationDate(LocalDate.now()).build();
        jobService.save(softwareEngineerIntern);

        Job humanResourcesTeamLead = Job.builder().title("Human Resources Team Lead").description(descriptionMsg)
                .numberOfHire(2).department(humanResources).jobType(JobType.TYPE_FULL)
                .publishDate(LocalDateTime.of(2019, Month.DECEMBER, 1, 12, 30))
                .lastApplicationDate(LocalDate.now()).build();
        jobService.save(humanResourcesTeamLead);

        Job marketingTeamLead = Job.builder().title("Marketing Team Lead").description(descriptionMsg)
                .numberOfHire(3).department(marketing).jobType(JobType.TYPE_FULL)
                .publishDate(LocalDate.now().atTime(11,0)).lastApplicationDate(LocalDate.now()).build();
        jobService.save(marketingTeamLead);

        User enes = User.builder().firstName("Muhammed Enes").lastName("Oral").address("İzmir").email("info@enesoral.com")
                .phone("5078713351").username("enesoral").password("123").isManager(false).build();
        enes.setResumeDirectory(System.getProperty("user.dir") + "/uploads/enesoral-resume");
        userService.save(enes);

        User ozlem = User.builder().firstName("Özlem").lastName("Çakmak").username("ozlemcakmak").address("İstanbul")
                .email("ozlemcakmak@gmail.com").phone("5553334444").password("123").isManager(false).build();
        userService.save(ozlem);

        User gulcin = User.builder().firstName("Gülçin").lastName("Nacak").username("gulcinnacak").address("İstanbul")
                .email("gulcinnacak@gmail.com").phone("5554443333").password("123").isManager(false).build();
        userService.save(gulcin);

        User destan = User.builder().firstName("Destan").lastName("Sarpkaya").username("destansarpkaya").address("İstanbul")
                .email("destan@gmail.com").phone("5554433334").password("123").isManager(true).build();
        userService.save(destan);

        String thoughtsMsg = "I'm exactly match for this job because I love my job and I complete tasks with passion!";
        Application application1 = new Application();
        application1.setUser(enes);
        application1.setJob(softwareEngineerIntern);
        application1.setApplicationDate(LocalDateTime.now());
        application1.setThoughtsOnJob(thoughtsMsg);
        applicationService.save(application1);
    }
}
