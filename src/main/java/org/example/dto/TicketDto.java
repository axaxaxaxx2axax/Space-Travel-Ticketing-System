package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Ticket;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "dtoBuilder")
public class TicketDto {
        private Integer id;
        private LocalDateTime createdAt;
        private String clientName;
        private String fromPlanetName;
        private String toPlanetName;

}
