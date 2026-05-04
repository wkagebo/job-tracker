package com.wkagebo.jobtracker.messaging;

import com.wkagebo.jobtracker.model.ApplicationStatus;

public record StatusUpdateEvent(Long applicationId,
                                ApplicationStatus status) {}
