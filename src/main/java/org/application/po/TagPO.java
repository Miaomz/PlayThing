package org.application.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tag")
public class TagPO implements PO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tagId;

    private boolean isDeleted;

    @Column(unique = true, nullable = false, name = "content", length = 64)
    private String content;
}
