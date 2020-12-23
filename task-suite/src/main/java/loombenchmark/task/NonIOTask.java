package loombenchmark.task;

import lombok.extern.slf4j.Slf4j;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

@Slf4j
public class NonIOTask implements Task {

    @Override
    public Runnable work() {
        return () -> {
            try {
                final KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
                final SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
                keyGen.initialize(1024, random);
                log.debug("Initialized key pair {}", keyGen.generateKeyPair());
            } catch (final NoSuchAlgorithmException e) {
                log.error("Could not determine key generation algorithm", e);
            } catch (final NoSuchProviderException e) {
                log.error("Could not determine provider", e);
            }
        };
    }
}
