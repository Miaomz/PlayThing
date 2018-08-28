package org.application.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @GeneratedValue
    private long rid;

    @Column(length = 2048)
    private String content;

    private boolean isDeleted;

    @OneToMany
    private List<TagPO> tags;

}
