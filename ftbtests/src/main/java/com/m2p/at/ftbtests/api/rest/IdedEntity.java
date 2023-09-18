package com.m2p.at.ftbtests.api.rest;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder(builderMethodName = "of")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class IdedEntity {

    protected long id;
}
