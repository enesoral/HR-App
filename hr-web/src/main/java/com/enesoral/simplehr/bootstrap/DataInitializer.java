package com.enesoral.simplehr.bootstrap;

import com.enesoral.simplehr.models.Application;
import com.enesoral.simplehr.models.Department;
import com.enesoral.simplehr.models.Job;
import com.enesoral.simplehr.models.User;
import com.enesoral.simplehr.services.ApplicationService;
import com.enesoral.simplehr.services.DepartmentService;
import com.enesoral.simplehr.services.JobService;
import com.enesoral.simplehr.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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
        int count = jobService.findAll().size();

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

        Job softwareEngineerIntern = Job.builder().title("Software Engineer Intern").description("We are hiring!")
                .numberOfHire(3).department(it).lastApplicationDate(LocalDate.now()).build();
        jobService.save(softwareEngineerIntern);

        Job humanResourcesTeamLead = Job.builder().title("Human Resources Team Lead").description("We are hiring!")
                .numberOfHire(2).department(humanResources).lastApplicationDate(LocalDate.now()).build();
        jobService.save(humanResourcesTeamLead);

        Job marketingTeamLead = Job.builder().title("Marketing Team Lead").description("We are hiring!")
                .numberOfHire(3).department(marketing).lastApplicationDate(LocalDate.now()).build();
        jobService.save(marketingTeamLead);

        User enes = User.builder().firstName("Muhammed Enes").lastName("Oral").username("enesoral")
                .password("123").isManager(false).build();
        userService.save(enes);

        User ozlem = User.builder().firstName("Özlem").lastName("Çakmak").username("ozlemcakmak")
                .password("123").isManager(false).build();
        userService.save(ozlem);

        User gulcin = User.builder().firstName("Gülçin").lastName("Nacak").username("gulcinnacak")
                .password("123").isManager(false).build();
        userService.save(gulcin);

        User destan = User.builder().firstName("Destan").lastName("Sarpkaya").username("destansarpkaya")
                .password("123").isManager(true).build();
        userService.save(destan);

        Application application1 = new Application();
        application1.setUser(enes);
        application1.setJob(softwareEngineerIntern);
        application1.setApplicationDate(LocalDate.now());
        applicationService.save(application1);

        Application application2 = new Application();
        application2.setUser(ozlem);
        application2.setJob(humanResourcesTeamLead);
        application2.setApplicationDate(LocalDate.now());
        applicationService.save(application2);

        Application application3 = new Application();
        application3.setUser(gulcin);
        application3.setJob(marketingTeamLead);
        application3.setApplicationDate(LocalDate.now());
        applicationService.save(application3);
    }
}
