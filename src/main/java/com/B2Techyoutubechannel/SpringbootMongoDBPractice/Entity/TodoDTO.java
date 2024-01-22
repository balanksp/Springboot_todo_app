package com.B2Techyoutubechannel.SpringbootMongoDBPractice.Entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "todos")
public class TodoDTO {

    @Id
    private String id; // this field is primary

    @NotNull(message = "todo cannot to be null")
    private String todo;

    @NotNull(message = "description cannot to be null")
    private String description;

    @NotNull(message = "completed cannot to be null")
    private Boolean completed;

    private Date createdAt;

    private Date updateAt;

}
