package pl.gielerak.infrastructure.repository;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import pl.gielerak.domain.repository.exception.RepositoryNotFoundException;
import pl.gielerak.domain.repository.model.FullName;
import pl.gielerak.domain.repository.repository.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
@Primary
public class RedisRepository implements Repository {
    final private static String CACHE_KEY = "repository.%s";
    final private static int CACHE_EXPIRE = 10;


    private Environment env;
    private Repository repository;
    private JedisPool pool;

    private final Lock lock = new ReentrantLock();
    private static final Logger logger = LoggerFactory.getLogger(RedisRepository.class);

    @Autowired
    public void RedisRepositoryFinder(Repository repository, Environment env) {
        this.repository = repository;
        this.env = env;
        this.pool = new JedisPool();
    }

    public void RedisRepositoryFinder(Repository repository, JedisPool pool, Environment env) {
        this.repository = repository;
        this.pool = pool;
        this.env = env;
    }

    @Override
    public pl.gielerak.domain.repository.model.Repository findByFullName(FullName fullName) throws RepositoryNotFoundException {
        loadJedis();
        String key = getKey(fullName);

        try (Jedis jedis = pool.getResource()) {
            lock.lock();

            if (jedis.exists(key)) {
                logger.info(String.format("Key \"%s\" exists, loading from redis.", key));
                JSONObject data = new JSONObject(jedis.get(key).toString());

                lock.unlock();

                return pl.gielerak.domain.repository.model.Repository.fromJson(data);
            }

            logger.info(String.format("Key \"%s\" doesn't exists, save to object to redis.", key));

            pl.gielerak.domain.repository.model.Repository repositoryResult = repository.findByFullName(fullName);
            jedis.set(key, repositoryResult.toJson().toString());
            jedis.expire(key, CACHE_EXPIRE);

            lock.unlock();

            return repositoryResult;
        }

    }

    private void loadJedis() {
        // create config classes to handle configuration
        if (null == pool) {
            String host = env.getProperty("redis.host");
            Integer port = env.getProperty("redis.port", Integer.class);

            logger.info(String.format("Connecting to Redis on \"%s:%d\"..", host, port));

            pool = new JedisPool(new JedisPoolConfig(), host, port);
        }
    }

    private static String getKey(FullName fullName) {
        return String.format(CACHE_KEY, fullName.getFullName()).toLowerCase();
    }
}
