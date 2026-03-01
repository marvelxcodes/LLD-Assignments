package com.example.tickets;

import java.util.ArrayList;
import java.util.List;

import com.example.tickets.IncidentTicket;

/**
 * Service layer that creates tickets.
 *
 * CURRENT STATE (BROKEN ON PURPOSE):
 * - creates partially valid objects
 * - mutates after creation (bad for auditability)
 * - validation is scattered & incomplete
 *
 * TODO (student):
 * - After introducing immutable IncidentTicket + Builder, refactor this to stop mutating.
 */
public class TicketService {

    public IncidentTicket createTicket(String id, String reporterEmail, String title) {
        IncidentTicket.Builder t = new IncidentTicket.Builder(id, reporterEmail, title);

        t.setPriority("MEDIUM");
        t.setSource("CLI");
        t.setCustomerVisible(false);

        List<String> tags = new ArrayList<>();
        tags.add("NEW");
        t.setTags(tags);

        return t.build();
    }

    public IncidentTicket escalateToCritical(IncidentTicket t) {

        var tags = new ArrayList<String>();
        for(String i: t.getTags()) {
          tags.add(i);
        }
        tags.add("ESCALATED");

      return new IncidentTicket.Builder(t.getId(), t.getReporterEmail(), t.getTitle())
        .setSource(t.getSource())
        .setSlaMinutes(t.getSlaMinutes())
        .setDescription(t.getDescription())
        .setAssigneeEmail(t.getAssigneeEmail())
        .setReporterEmail(t.getReporterEmail())
        .setCustomerVisible(t.isCustomerVisible())
        .setPriority("CRITICAL")
        .setTags(tags)
        .build();
    }

    public IncidentTicket assign(IncidentTicket t, String assigneeEmail) {
      var tags = new ArrayList<String>();
        for(String i: t.getTags()) {
          tags.add(i);
        }

      return new IncidentTicket.Builder(t.getId(), t.getReporterEmail(), t.getTitle())
        .setSource(t.getSource())
        .setSlaMinutes(t.getSlaMinutes())
        .setDescription(t.getDescription())
        .setAssigneeEmail(assigneeEmail)
        .setReporterEmail(t.getReporterEmail())
        .setCustomerVisible(t.isCustomerVisible())
        .setPriority(t.getPriority())
        .setTags(tags)
        .build();
    }
}
