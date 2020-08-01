package com.yxh.pojo;

import lombok.*;

/**
 * @author YXH
 * @date 2020/7/27 - 11:38
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CookOrder {
    private int table_id;
    private int ok;
    private String time;
    private String foods;
}
