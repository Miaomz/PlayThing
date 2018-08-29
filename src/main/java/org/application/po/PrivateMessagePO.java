package org.application.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PrivateMessagePO implements PO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pmId;

    private long senderId;

    private long receiverId;

    private boolean isDeleted;

    private LocalDateTime time;

    private boolean isChecked;

    @Column(length = 2048)
    private String content;
}
