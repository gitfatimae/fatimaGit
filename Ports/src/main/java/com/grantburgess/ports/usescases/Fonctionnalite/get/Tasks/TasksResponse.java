package com.grantburgess.ports.usescases.Fonctionnalite.get.Tasks;

import com.grantburgess.ports.usescases.Fonctionnalite.get.TaskResponse;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class TasksResponse {
    @Singular("addTask") private List<TaskResponse> tasks;
}
