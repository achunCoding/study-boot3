package cn.wycfight.cloud.es.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@Document(indexName = "blog")
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {

    @Id
    private String id;

    @MultiField(mainField = @Field(type = FieldType.Text, fielddata = true),
            otherFields = { @InnerField(suffix = "verbatim", type = FieldType.Keyword)})
    private String title;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Author> authors;

    @Field(type = FieldType.Keyword)
    private String[] tags;

    public Article(String title) {
        this.title = title;
    }
}
