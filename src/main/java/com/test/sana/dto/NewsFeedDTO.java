package com.test.sana.dto;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewsFeedDTO {
    private Long id;
    @NotEmpty(message = "Title cannot be empty")
    private String title;
    @NotEmpty(message = "URL cannot be empty")
    private String url;
    private String summary;
    private String source;
}
