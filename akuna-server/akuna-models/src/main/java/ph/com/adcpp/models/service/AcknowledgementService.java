package ph.com.adcpp.models.service;

import org.springframework.stereotype.Service;
import ph.com.adcpp.models.repository.AcknowledgementRepo;

/**
 * @author Choy
 * @date 2/24/2020.
 */

@Service
public class AcknowledgementService {

    private AcknowledgementRepo acknowledgementRepo;

    public AcknowledgementService(AcknowledgementRepo acknowledgementRepo) {
        this.acknowledgementRepo = acknowledgementRepo;
    }
}
