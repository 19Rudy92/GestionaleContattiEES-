package it.epicode.be.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity
public class Ruolo{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ruoloGen")
    @SequenceGenerator(name = "ruoloGen", sequenceName = "ruolo_sequence", allocationSize = 1)
    private long id;
    
    @Enumerated(EnumType.STRING)
    private RuoloTipo ruoloTipo;
    
}