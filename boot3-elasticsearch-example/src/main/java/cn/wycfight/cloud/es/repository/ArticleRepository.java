package cn.wycfight.cloud.es.repository;

import cn.wycfight.cloud.es.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends ElasticsearchRepository<Article, String> {
    /**
     * 解析算法需要访问authors属性，然后搜素每个项目name属性
     * @param name
     * @param pageable
     * @return
     */
    Page<Article> findByAuthorsName(String name, Pageable pageable);

    /**
     * 自定义@Query查询方式 作者姓名严格匹配
     * @param name 作者名称
     * @param pageable
     * @return
     */
    @Query("{\"bool\": {\"must\": [{\"match\": {\"authors.name\": \"?0\"}}]}}")
    Page<Article> findByAuthorsNameUsingCustomQuery(String name, Pageable pageable);


}
