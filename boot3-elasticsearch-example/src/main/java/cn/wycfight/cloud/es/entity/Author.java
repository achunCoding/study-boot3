package cn.wycfight.cloud.es.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 作者信息
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Field(type = FieldType.Text)
    private String name;
}
