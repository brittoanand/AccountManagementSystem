package com.accountmanagementsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "operation_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OperationType {
    @Id
    @Column(name = "operation_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long operationTypeId;

    @Column(name = "description", nullable = false)
    private String description;
}