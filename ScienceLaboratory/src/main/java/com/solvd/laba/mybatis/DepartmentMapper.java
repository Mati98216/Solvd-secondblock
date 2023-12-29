package com.solvd.laba.mybatis;
import com.solvd.laba.domain.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DepartmentMapper {

    @Insert("INSERT INTO departments (department_name) VALUES (#{departmentName})")
    @Options(useGeneratedKeys = true, keyProperty = "departmentId")
    void insert(Department department);

    @Select("SELECT * FROM departments WHERE department_id = #{id}")
    Department getById(int id);

    @Update("UPDATE departments SET department_name = #{departmentName} WHERE department_id = #{departmentId}")
    void update(Department department);

    @Delete("DELETE FROM departments WHERE department_id = #{id}")
    void delete(int id);

    @Select("SELECT * FROM departments")
    List<Department> findAll();
}
