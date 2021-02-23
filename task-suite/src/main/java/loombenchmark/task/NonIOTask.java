package loombenchmark.task;

import lombok.extern.slf4j.Slf4j;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.concurrent.Callable;

@Slf4j
public class NonIOTask implements Task<KeyPair> {

    @Override
    public Callable<KeyPair> work() {
        return () -> {
            try {
                final KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
                final SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
                keyGen.initialize(1024, random);
                final KeyPair keyPair = keyGen.generateKeyPair();
                log.debug("Initialized key pair {}", keyPair);
                return keyPair;
            } catch (final NoSuchAlgorithmException e) {
                log.error("Could not determine key generation algorithm", e);
                throw e;
            } catch (final NoSuchProviderException e) {
                log.error("Could not determine provider", e);
                throw e;
            }
        };
    }
}
