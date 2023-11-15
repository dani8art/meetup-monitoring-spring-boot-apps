package com.darteaga.demo.monitoring.model;

import static jakarta.persistence.FetchType.EAGER;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {

  @Id
  private UUID id;

  @ManyToMany(fetch = EAGER)
  @JoinTable(name = "purchase_ticket", joinColumns = @JoinColumn(name = "purchase_id"), inverseJoinColumns = @JoinColumn(name = "ticket_id"))
  private List<Ticket> tickets;

}
