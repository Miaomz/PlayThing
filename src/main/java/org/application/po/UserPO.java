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
public class UserPO implements PO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(unique = true, nullable = false, length = 32)
    private String name;

    private String password;

    private String location;

    private String phone;

    private String mail;

    /**
     * 用户头像图片的url
     */
    @Column(length = 64)
    private String display;

    private String role;

    private boolean isDeleted;

    private double balance;

    @Lob
    @Convert(converter = TagsConverter.class)
    private List<TagPO> tags;

}
