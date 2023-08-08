package cn.wycfight.cloud.es.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @description elasticsearch配置
 * @author
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "cn.wycfight.cloud.es.repository")
public class MyClientConfig extends ElasticsearchConfiguration {


    @Override
    public ClientConfiguration clientConfiguration() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .withBasicAuth("elastic", "chun12138")
                .build();
        return clientConfiguration;
    }
}
