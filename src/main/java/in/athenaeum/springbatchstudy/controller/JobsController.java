package in.athenaeum.springbatchstudy.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/v1/jobs")
public class JobsController {
    private final Logger logger;
    private final Job job;
    private final JobLauncher jobLauncher;

    public JobsController(Job job, JobLauncher jobLauncher) {
        this.job = job;
        this.jobLauncher = jobLauncher;
        this.logger = Logger.getLogger(JobsController.class.getName());
    }

    @GetMapping
    public ResponseEntity<?> load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        Thread thread = new Thread(() -> {
            Map<String, JobParameter> maps = new HashMap<>();
            maps.put("current_millis", new JobParameter(System.currentTimeMillis()));

            JobParameters parameters = new JobParameters(maps);

            logger.info("Job launching");
            try {
                jobLauncher.run(job, parameters);
            } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
                e.printStackTrace();
            }
        });

        thread.start();

        return ResponseEntity.ok().build();
    }
}
