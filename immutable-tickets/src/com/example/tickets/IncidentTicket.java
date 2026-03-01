package com.example.tickets;

import java.util.ArrayList;
import java.util.List;

import com.example.tickets.Validation;

/**
 * INTENTION: A ticket should be an immutable record-like object.
 *
 * CURRENT STATE (BROKEN ON PURPOSE):
 * - mutable fields
 * - multiple constructors
 * - public setters
 * - tags list can be modified from outside
 * - validation is scattered elsewhere
 *
 * TODO (student): refactor to immutable + Builder.
 */
public class IncidentTicket {

    private final String id;
    private final String reporterEmail;
    private final String title;

    private final String description;
    private final String priority;       // LOW, MEDIUM, HIGH, CRITICAL
    private final List<String> tags;     // mutable leak
    private final String assigneeEmail;
    private final boolean customerVisible;
    private final Integer slaMinutes;    // optional
    private final String source;         // e.g. "CLI", "WEBHOOK", "EMAIL"

    public static class Builder {
      private String id;
      private String reporterEmail;
      private String title;

      private String description;
      private String priority;       // LOW, MEDIUM, HIGH, CRITICAL
      private List<String> tags;     // mutable leak
      private String assigneeEmail;
      private boolean customerVisible;
      private Integer slaMinutes;    // optional
      private String source;         // e.g. "CLI", "WEBHOOK", "EMAIL"

      public Builder(String id, String reporterEmail, String title) {
        this.id = id;
        this.reporterEmail = reporterEmail;
        this.title = title;
      }

      public Builder setId(String id) {
        this.id = id;
        return this;
      }
      public Builder setReporterEmail(String reporterEmail) {
        this.reporterEmail = reporterEmail;
        return this;
      }
      public Builder setTitle(String title) {
        this.title = title;
        return this;
      }
      public Builder setDescription(String description) {
        this.description = description;
        return this;
      }
      public Builder setPriority(String priority) {
        this.priority = priority;
        return this;
      }
      public Builder setTags(List<String> tags) {
        this.tags = tags;
        return this;
      }
      public Builder setAssigneeEmail(String assigneeEmail) {
        this.assigneeEmail = assigneeEmail;
        return this;
      }
      public Builder setCustomerVisible(boolean customerVisible) {
        this.customerVisible = customerVisible;
        return this;
      }
      public Builder setSlaMinutes(Integer slaMinutes) {
        this.slaMinutes = slaMinutes;
        return this;
      }
      public Builder setSource(String source) {
        this.source = source;
        return this;
      }

      public IncidentTicket build() {
        Validation.requireNonBlank(this.id, "id");
        Validation.requireNonBlank(this.title, "title");
        Validation.requireEmail(this.reporterEmail, "email");
        Validation.requireEmail(this.assigneeEmail, "assigneeEmail");
        return new IncidentTicket(this);
      }
    }

    private IncidentTicket(Builder builder) {
      this.id = builder.id;
      this.reporterEmail = builder.reporterEmail;
      this.title = builder.title;
      this.description = builder.description;
      this.priority = builder.priority;
      this.tags = builder.tags;
      this.assigneeEmail = builder.assigneeEmail;
      this.customerVisible = builder.customerVisible;
      this.slaMinutes = builder.slaMinutes;
      this.source = builder.source;
    }

    // Getters
    public String getId() { return id; }
    public String getReporterEmail() { return reporterEmail; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPriority() { return priority; }
    public List<String> getTags() { return tags; } // BROKEN: leaks internal list
    public String getAssigneeEmail() { return assigneeEmail; }
    public boolean isCustomerVisible() { return customerVisible; }
    public Integer getSlaMinutes() { return slaMinutes; }
    public String getSource() { return source; }

    
    @Override
    public String toString() {
        return "IncidentTicket{" +
                "id='" + id + '\'' +
                ", reporterEmail='" + reporterEmail + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", tags=" + tags +
                ", assigneeEmail='" + assigneeEmail + '\'' +
                ", customerVisible=" + customerVisible +
                ", slaMinutes=" + slaMinutes +
                ", source='" + source + '\'' +
                '}';
    }
}
