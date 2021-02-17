package de.dsms.rsscreator.domain.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public abstract class CustomApplicationEvent {
    private final LocalDateTime created = LocalDateTime.now();
}
