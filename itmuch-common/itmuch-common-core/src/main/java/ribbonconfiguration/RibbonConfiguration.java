package ribbonconfiguration;

import com.itmuch.core.config.ribbon.NacosSameClusterWeightedRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonConfiguration {
    @Bean
    public IRule ribbonRule() {
        //返回负载均衡算法的配置类。
        return new NacosSameClusterWeightedRule();
    }
}
