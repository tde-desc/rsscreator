package de.dsms.rsscreator.domain.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public abstract class GenericApplicationEvent<T> extends CustomApplicationEvent {
    private final T object;
}
