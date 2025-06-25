package com.eaxmple.hello_project._3jdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
// @Getter @Setter @toString @hashCode @eqauls 포함
@Data
//모든 매개변수를 가지는 생성자
@AllArgsConstructor
//기본 생성자
@NoArgsConstructor
public class TodoDTO {
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}
