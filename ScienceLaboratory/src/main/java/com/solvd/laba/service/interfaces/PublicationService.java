package com.solvd.laba.service.interfaces;

import com.solvd.laba.domain.Publication;

import java.util.List;

public interface PublicationService {
    void addPublication(Publication publication);
    Publication getPublicationById(int id);
    List<Publication> getAllPublications();
    void updatePublication(Publication publication);
    void deletePublication(int id);
}
