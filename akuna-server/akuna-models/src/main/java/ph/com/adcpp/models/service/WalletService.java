package ph.com.adcpp.models.service;

import org.springframework.stereotype.Service;
import ph.com.adcpp.models.entity.Wallet;
import ph.com.adcpp.models.repository.WalletRepository;

/**
 * @author Choy
 * @date 1/31/2020.
 */

@Service
public class WalletService {

    private WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet save(Wallet wallet) {
        return walletRepository.save(wallet);
    }
}
