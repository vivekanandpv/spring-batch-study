package in.athenaeum.springbatchstudy.config;

import in.athenaeum.springbatchstudy.repository.ICarRepository;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private final Logger logger;

    private final ICarRepository repository;

    public JobCompletionNotificationListener(ICarRepository repository) {
        this.repository = repository;
        logger = Logger.getLogger(JobCompletionNotificationListener.class.getName());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            long recordCount = repository.count();
            logger.info("Job completed: " + recordCount + " records added to the database");
        }
    }
}
