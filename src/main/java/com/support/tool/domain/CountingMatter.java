package com.support.tool.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter
@Builder
@ToString
public class CountingMatter {

    @Id
    @Column(length = 8)
    private String countedDate;

    @Column
    @Builder.Default
    private int dailyTotal = 0;

    @Column
    @Builder.Default
    private int completedAmount = 0;

    @Column
    @Builder.Default
    private int uncompletedAmount = 0;

    @Column
    @Builder.Default
    private int etcAmount = 0;

    public CountingMatter() {}

    public CountingMatter(String countedDate, int dailyTotal, int completedAmount, int uncompletedAmount, int etcAmount) {
        this.countedDate = countedDate;
        this.dailyTotal = dailyTotal;
        this.completedAmount = completedAmount;
        this.uncompletedAmount = uncompletedAmount;
        this.etcAmount = etcAmount;
    }
}
