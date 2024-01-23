package com.solvd.laba.mybatis;

import com.solvd.laba.domain.Experiment;
import com.solvd.laba.domain.Publication;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PublicationMapper {

    @Insert("INSERT INTO publications (publication_title, publication_date, scientist_id) " +
            "VALUES (#{publicationTitle}, #{publicationDate}, #{scientist.scientistId})")
    @Options(useGeneratedKeys = true, keyProperty = "publicationId")
    void insert(Publication publication);

    @Select("SELECT p.*, s.name as scientist_name " +
            "FROM publications p " +
            "JOIN scientists s ON p.scientist_id = s.scientist_id " +
            "WHERE publication_id = #{id}")
    @Results(value = {
            @Result(property = "publicationId", column = "publication_id"),
            @Result(property = "publicationTitle", column = "publication_title"),
            @Result(property = "publicationDate", column = "publication_date"),
            @Result(property = "scientist.scientistId", column = "scientist_id"),
            @Result(property = "scientist.name", column = "scientist_name")
    })
    Publication getById(int id);

    @Update("UPDATE publications SET publication_title = #{publicationTitle}, " +
            "publication_date = #{publicationDate}, scientist_id = #{scientist.scientistId} " +
            "WHERE publication_id = #{publicationId}")
    void update(Publication publication);

    @Delete("DELETE FROM publications WHERE publication_id = #{id}")
    void delete(int id);

    @Select("SELECT p.*, s.name as scientist_name " +
            "FROM publications p " +
            "JOIN scientists s ON p.scientist_id = s.scientist_id")
    @Results(value = {
            @Result(property = "publicationId", column = "publication_id"),
            @Result(property = "publicationTitle", column = "publication_title"),
            @Result(property = "publicationDate", column = "publication_date"),
            @Result(property = "scientist.scientistId", column = "scientist_id"),
            @Result(property = "scientist.name", column = "scientist_name")
    })
    List<Publication> findAll();

    @Insert("INSERT INTO experiment_publications (experiment_id,publication_id) VALUES (#{experimentId}, #{publicationId})")
    void addExperimentToPublication(@Param("experimentId") int experimentId, @Param("publicationId") int publicationId);

    @Select("SELECT e.* FROM experiments e " +
            "JOIN experiment_publications ep ON e.experiment_id = ep.experiment_id " +
            "WHERE ep.publication_id = #{publicationId}")
    List<Experiment> getExperimentsForPublication(int publicationId);

    @Delete("DELETE FROM experiment_publications WHERE publication_id = #{publicationId} AND experiment_id = #{experimentId}")
    void removeExperimentFromPublication(@Param("publicationId") int publicationId, @Param("experimentId") int experimentId);

    @Update("UPDATE experiment_publications SET experiment_id = #{newExperimentId} " +
            "WHERE publication_id = #{publicationId} AND experiment_id = #{oldExperimentId}")
    void updateExperimentForPublication(@Param("publicationId") int publicationId,
                                        @Param("oldExperimentId") int oldExperimentId,
                                        @Param("newExperimentId") int newExperimentId);


}

