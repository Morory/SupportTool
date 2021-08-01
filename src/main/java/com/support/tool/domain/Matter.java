package com.support.tool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@ToString
public class Matter extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 8)
    private int id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @Column(length = 20, nullable = false)
    private String username;

    @Column(length = 90, nullable = false)
    private String title;

    // 処理中 = 'N', 処理完了 = 'Y', その他 = 'E'
    @Column(nullable = false)
    private char completeStatus;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column
    private String content;

    @JsonIgnore
    @CreatedDate
    private LocalDateTime createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @JsonIgnore
    @Column(nullable = false)
    private char status = 'Y';
}
