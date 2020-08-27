package Config;

import Config.ThirdParty.ContextDatabase;
import Config.ThirdParty.ContextKafka;
import Config.ThirdParty.ContextMyBatis;
import Config.ThirdParty.ContextRedis;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@Import({ContextDatabase.class, ContextKafka.class, ContextMyBatis.class, ContextRedis.class, WebSecurityConfig.class})
@ComponentScan(basePackages = {"Chatting.*"})
@EnableJpaRepositories("Chatting.Dao")
public class RootConfig {

}
