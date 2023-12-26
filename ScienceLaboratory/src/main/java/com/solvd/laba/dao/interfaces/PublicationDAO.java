package com.solvd.laba.dao.interfaces;

import com.solvd.laba.domain.Publication;

import java.sql.SQLException;
import java.util.List;

public interface PublicationDAO {
    void addPublication(Publication publication) throws SQLException;
    Publication getPublicationById(int id) throws SQLException;
    List<Publication> getAllPublications() throws SQLException;
    void updatePublication(Publication publication) throws SQLException;
    void deletePublication(int id) throws SQLException;
}
