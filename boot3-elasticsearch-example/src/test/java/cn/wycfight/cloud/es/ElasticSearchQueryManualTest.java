package cn.wycfight.cloud.es;


import cn.wycfight.cloud.es.config.MyClientConfig;
import cn.wycfight.cloud.es.entity.Article;
import cn.wycfight.cloud.es.entity.Author;
import cn.wycfight.cloud.es.repository.ArticleRepository;
import org.elasticsearch.client.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;

@SpringBootTest
@ContextConfiguration(classes = MyClientConfig.class)
public class ElasticSearchQueryManualTest {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private RestClient client;


    private final Author johnSmith = new Author("John Smith");
    private final Author johnDoe = new Author("John Doe");

    public void before() {
        Article article = new Article("Spring Data Elasticsearch");
        article.setAuthors(Arrays.asList(johnSmith, johnDoe));
        article.setTags(new String[]{"elasticsearch", "spring data"});
        articleRepository.save(article);

        article = new Article("Search engines");
        article.setAuthors(Arrays.asList(johnDoe));
        article.setTags(new String[]{"search engines", "tutorial"});
        articleRepository.save(article);

        article = new Article("Second Article About Elasticsearch");
        article.setAuthors(Arrays.asList(johnSmith));
        article.setTags(new String[]{"elasticsearch", "spring data"});
        articleRepository.save(article);

        article = new Article("Elasticsearch Tutorial");
        article.setAuthors(Arrays.asList(johnDoe));
        article.setTags(new String[]{"elasticsearch"});
        articleRepository.save(article);
    }

    public void after() {
        articleRepository.deleteAll();
    }


    @Test
    public void givenFullTitle_whenRunMatchQuery_thenDocIsFound() {
        final Query searchQuery = NativeQuery.builder()
                .withQuery(q -> q.match(v -> v.field("title").query("Search engines")))
                .withFields("title")
                .build();
        final SearchHits<Article> articles = elasticsearchOperations
                .search(searchQuery, Article.class, IndexCoordinates.of("blog"));
        Assertions.assertEquals(1, articles.getTotalHits());
    }

}
