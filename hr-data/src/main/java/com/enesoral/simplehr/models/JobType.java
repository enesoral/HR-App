package com.enesoral.simplehr.models;

public enum JobType {
    TYPE_FULL("Full-time"), TYPE_PART("Part-time"), TYPE_TEMP("Temporary"), TYPE_INTERN("Intern");

    private final String value;
    JobType(final String s) { value = s; }
    public String toString() { return value; }
}
