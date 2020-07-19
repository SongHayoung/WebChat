package Config;

import Config.ThirdParty.ContextDatabase;
import Config.ThirdParty.ContextKafka;
import Config.ThirdParty.ContextMyBatis;
import Config.ThirdParty.ContextRedis;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ContextDatabase.class, ContextKafka.class, ContextMyBatis.class, ContextRedis.class})
@ComponentScan(basePackages = {"Chatting.*"})
public class RootConfig {

}
