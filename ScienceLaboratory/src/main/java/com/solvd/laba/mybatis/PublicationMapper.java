package com.solvd.laba.mybatis;

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
}
