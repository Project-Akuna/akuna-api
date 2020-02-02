package ph.com.adcpp.core.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ph.com.adcpp.commons.constant.IncentiveType;
import ph.com.adcpp.models.entity.Incentive;
import ph.com.adcpp.models.service.IncentiveService;

import java.util.Arrays;

/**
 * @author Choy
 * @date 1/29/2020.
 */

@Slf4j
@Component("customTaskScheduler")
public class TaskScheduler {

    private IncentiveService incentiveService;

    public TaskScheduler(IncentiveService incentiveService) {
        this.incentiveService = incentiveService;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void createIncentiveForTheDay() {
        Arrays.asList(IncentiveType.values()).forEach(incentiveType -> {
            log.info("Creating [{}] incentive...", incentiveType.name());

            incentiveService.save(new Incentive(incentiveType));

            log.info("Successfully created [{}] incentive.", incentiveType);
        });
    }
}
