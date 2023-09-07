package cn.wycfight.cloud.es;


import cn.wycfight.cloud.es.config.MyClientConfig;
import cn.wycfight.cloud.es.entity.Article;
import cn.wycfight.cloud.es.entity.Author;
import cn.wycfight.cloud.es.repository.ArticleRepository;
import co.elastic.clients.elasticsearch._types.query_dsl.Operator;
import co.elastic.clients.elasticsearch._types.query_dsl.TextQueryType;
import org.elasticsearch.client.RestClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @BeforeEach
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

    @AfterEach
    public void after() {
        articleRepository.deleteAll();
    }


    @Test
    public void givenFullTitle_whenRunMatchQuery_thenDocIsFound() {
        final Query searchQuery = NativeQuery.builder()
                .withQuery(q -> q.match(v -> v.field("title").query("Engines Solutions")))
                .build();
        final SearchHits<Article> articles = elasticsearchOperations
                .search(searchQuery, Article.class, IndexCoordinates.of("blog"));
        Assertions.assertEquals(1, articles.getTotalHits());
    }


    @Test
    public void givenPersistedArticles_whenSearchByAuthorsName_thenRightFound() {
        Page<Article> articleByAuthorName = articleRepository.findByAuthorsName(johnSmith.getName(), PageRequest.of(0, 10));
        Assertions.assertEquals(2L, articleByAuthorName.getTotalElements());
    }

    @Test
    public void givenCustomQuery_whenSearchByAuthorsName_thenArticleIsFound() {
        Page<Article> articleByAuthorName = articleRepository.findByAuthorsNameUsingCustomQuery("Smith", PageRequest.of(0, 10));
        Assertions.assertEquals(2L, articleByAuthorName.getTotalElements());
    }


    @Test
    public void givenSavedDoc_whenTitleUpdated_thenCouldFindByUpdatedTitle() {
        final Query searchQuery = NativeQuery.builder().withQuery(q ->
                        q.fuzzy(v -> v.field("title").value("ABC")))
                .build();
        final SearchHits<Article> articles = elasticsearchOperations.search(searchQuery, Article.class, IndexCoordinates.of("blog"));

        Assertions.assertEquals(1, articles.getTotalHits());

        final Article article = articles.getSearchHit(0)
                .getContent();
        final String newTitle = "Getting started with Search Engines";
        article.setTitle(newTitle);
        articleRepository.save(article);

        Assertions.assertEquals(newTitle, articleRepository.findById(article.getId())
                .get()
                .getTitle());
    }


    @Test
    public void givenPartTitle_whenRunMatchQuery_thenDocIsFound() {
        final Query searchQuery = NativeQuery.builder()
                .withQuery(q -> q.match(v -> v.field("title").query("elasticsearch data")))
                .build();

        final SearchHits<Article> articles = elasticsearchOperations.search(searchQuery,
                Article.class, IndexCoordinates.of("blog"));

        Assertions.assertEquals(3, articles.getTotalHits());
    }

//    @Test
//    public void givenTagFilterQuery_whenSearchByAuthorsName_thenArticleIsFound() {
//        final Page<Article> articleByAuthorName = articleRepository.findByAuthorsNameAndFilteredTagQuery("Doe", "elasticsearch", PageRequest.of(0, 10));
//        Assertions.assertEquals(2L, articleByAuthorName.getTotalElements());
//    }


    @Test
    public void givenFullTitle_whenRunMatchQueryOnVerbatimField_thenDocIsFound() {
        Query searchQuery = NativeQuery.builder()
                .withQuery(q -> q.match(v -> v.field("title.verbatim")
                        .query("Second Article About Elasticsearch")))
                .build();

        SearchHits<Article> articles = elasticsearchOperations.search(searchQuery, Article.class, IndexCoordinates.of("blog"));

        Assertions.assertEquals(1, articles.getTotalHits());


        searchQuery = NativeQuery.builder()
                .withQuery(q -> q.match(v -> v.field("title.verbatim")
                        .query("Second Article About")))
                .build();

        articles = elasticsearchOperations.search(searchQuery, Article.class, IndexCoordinates.of("blog"));
        Assertions.assertEquals(0, articles.getTotalHits());
    }



    @Test
    public void givenPhraseWithType_whenUseFuzziness_thenQueryMatches() {
        Query searchQuery = NativeQuery.builder()
                .withQuery(q -> q.match(v -> v.field("title")
                        .query("spring date elasticserch")
                        .operator(Operator.And)
                        .fuzziness("1")
                        .prefixLength(3)))
                .build();
        final SearchHits<Article> articles = elasticsearchOperations.search(searchQuery, Article.class, IndexCoordinates.of("blog"));
        Assertions.assertEquals(1, articles.getTotalHits());
    }



    @Test
    public void givenNotExactPhrase_whenUseSlop_thenQueryMatches() {
        final NativeQuery searchQuery = NativeQuery.builder()
                .withQuery(q -> q.matchPhrase(v -> v.field("title")
                        .query("spring elasticsearch")
                        .slop(1)))
                .build();
        final SearchHits<Article> articles = elasticsearchOperations.search(searchQuery, Article.class, IndexCoordinates.of("blog"));

        Assertions.assertEquals(1, articles.getTotalHits());
    }


    @Test
    public void givenMultimatchQuery_whenDoSearch_thenAllProvidedFieldsMatch() {
        final NativeQuery searchQuery = NativeQuery.builder()
                .withQuery(q -> q.multiMatch(v -> v.query("tutorial")
                        .fields(Arrays.asList("title", "tags"))
                        .type(TextQueryType.BestFields)))
                .build();

        final SearchHits<Article> articles = elasticsearchOperations.search(searchQuery, Article.class, IndexCoordinates.of("blog"));

        Assertions.assertEquals(2, articles.getTotalHits());
    }



}
