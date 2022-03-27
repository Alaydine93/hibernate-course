package com.mycompany.tennis.core.dto;

import com.mycompany.tennis.core.entity.Tournoi;

import java.util.Set;

public class EpreuveFullDto {
    private Long id;
    private Short annee;
    private TournoiDto tournoiDto;
    private Character typeEpreuve;
    private Set<JoueurDto> participants;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getAnnee() {
        return annee;
    }

    public void setAnnee(Short annee) {
        this.annee = annee;
    }

    public TournoiDto getTournoiDto() {
        return tournoiDto;
    }

    public void setTournoiDto(TournoiDto tournoiDto) {
        this.tournoiDto = tournoiDto;
    }

    public Character getTypeEpreuve() {
        return typeEpreuve;
    }

    public void setTypeEpreuve(Character typeEpreuve) {
        this.typeEpreuve = typeEpreuve;
    }

    public Set<JoueurDto> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<JoueurDto> participants) {
        this.participants = participants;
    }
}
