package com.oa.ppmtool.exceptions;

public class ProjectIdentifierExceptionResponse {

    // "projectIdentifier": "Identifier is required"
    private String projectIdentifier;

    public ProjectIdentifierExceptionResponse(final String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(final String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }
}
