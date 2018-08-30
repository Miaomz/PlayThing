package org.application.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.application.util.TagsConverter;

import javax.persistence.*;
import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recommendation")
public class RecommendationPO implements PO{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rid;

    @Column(length = 2048)
    private String content;

    private boolean isDeleted;

    @Lob
    @Convert(converter = TagsConverter.class)
    private List<TagPO> tags;

}
