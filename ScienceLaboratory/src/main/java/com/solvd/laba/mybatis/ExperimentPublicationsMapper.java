package com.solvd.laba.mybatis;
import com.solvd.laba.domain.ExperimentPublications;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ExperimentPublicationsMapper {

    @Insert("INSERT INTO experiment_publications (experiment_id, publication_id) " +
            "VALUES (#{experiment.experimentId}, #{publication.publicationId})")
    @Options(useGeneratedKeys = true, keyProperty = "experimentPublicationsId")
    void insert(ExperimentPublications experimentPublications);

    @Select("SELECT ep.*, e.experiment_name, p.publication_title " +
            "FROM experiment_publications ep " +
            "JOIN experiments e ON ep.experiment_id = e.experiment_id " +
            "JOIN publications p ON ep.publication_id = p.publication_id " +
            "WHERE experiment_publications_id = #{id}")
    @Results(value = {
            @Result(property = "experimentPublicationsId", column = "experiment_publications_id"),
            @Result(property = "experiment.experimentId", column = "experiment_id"),
            @Result(property = "experiment.name", column = "experiment_name"),
            @Result(property = "publication.publicationId", column = "publication_id"),
            @Result(property = "publication.title", column = "publication_title")
    })
    ExperimentPublications getById(int id);

    @Update("UPDATE experiment_publications SET experiment_id = #{experiment.experimentId}, " +
            "publication_id = #{publication.publicationId} WHERE experiment_publications_id = #{experimentPublicationsId}")
    void update(ExperimentPublications experimentPublications);

    @Delete("DELETE FROM experiment_publications WHERE experiment_publications_id = #{id}")
    void delete(int id);

    @Select("SELECT ep.*, e.experiment_name, p.publication_title " +
            "FROM experiment_publications ep " +
            "JOIN experiments e ON ep.experiment_id = e.experiment_id " +
            "JOIN publications p ON ep.publication_id = p.publication_id")
    @Results(value = {
            @Result(property = "experimentPublicationsId", column = "experiment_publications_id"),
            @Result(property = "experiment.experimentId", column = "experiment_id"),
            @Result(property = "experiment.name", column = "experiment_name"),
            @Result(property = "publication.publicationId", column = "publication_id"),
            @Result(property = "publication.title", column = "publication_title")
    })
    List<ExperimentPublications> findAll();
}
