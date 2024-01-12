CREATE DATABASE IF NOT EXISTS science_laboratory;
USE science_laboratory;
-- Table: departments
CREATE TABLE IF NOT EXISTS departments (
    department_id INT PRIMARY KEY AUTO_INCREMENT,
    department_name VARCHAR(100) NOT NULL
);
CREATE TABLE IF NOT EXISTS research_areas (
    area_id INT PRIMARY KEY AUTO_INCREMENT,
    area_name VARCHAR(100) NOT NULL
);
-- Table: scientists
CREATE TABLE IF NOT EXISTS scientists (
    scientist_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    department_id INT,
    area_id INT,
    FOREIGN KEY (department_id) REFERENCES departments(department_id),
    FOREIGN KEY (area_id) REFERENCES research_areas(area_id)
);

-- Table: laboratory_assistants
CREATE TABLE IF NOT EXISTS laboratory_assistants (
    assistant_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    department_id INT,
    area_id INT,
    FOREIGN KEY (department_id) REFERENCES departments(department_id),
    FOREIGN KEY (area_id) REFERENCES research_areas(area_id)
);


-- Table: experiments
CREATE TABLE IF NOT EXISTS experiments (
    experiment_id INT PRIMARY KEY AUTO_INCREMENT,
    experiment_name VARCHAR(100) NOT NULL,
    scientist_id INT,
    FOREIGN KEY (scientist_id) REFERENCES scientists(scientist_id)
);

-- Table: analysis
CREATE TABLE IF NOT EXISTS analysis (
    analysis_id INT PRIMARY KEY AUTO_INCREMENT,
    analysis_name VARCHAR(100) NOT NULL,
    scientist_id INT,
    assistant_id INT,
    FOREIGN KEY (scientist_id) REFERENCES scientists(scientist_id),
    FOREIGN KEY (assistant_id) REFERENCES laboratory_assistants(assistant_id)
);

-- Table: experiment_results
CREATE TABLE IF NOT EXISTS experiment_results (
    result_id INT PRIMARY KEY AUTO_INCREMENT,
    experiment_id INT,
    analysis_id INT,
    result_details TEXT,
    FOREIGN KEY (experiment_id) REFERENCES experiments(experiment_id),
    FOREIGN KEY (analysis_id) REFERENCES analysis(analysis_id)
);

-- Table: equipment
CREATE TABLE IF NOT EXISTS equipment (
    equipment_id INT PRIMARY KEY AUTO_INCREMENT,
    equipment_name VARCHAR(100) NOT NULL,
    department_id INT,
    FOREIGN KEY (department_id) REFERENCES departments(department_id)
);

-- Table: experiment_equipment
CREATE TABLE IF NOT EXISTS experiment_equipment (
	experiment_equipment_id INT PRIMARY KEY AUTO_INCREMENT,
    experiment_id INT,
    equipment_id INT,
    FOREIGN KEY (experiment_id) REFERENCES experiments(experiment_id),
    FOREIGN KEY (equipment_id) REFERENCES equipment(equipment_id)
);

-- Table: experiment_timeline
CREATE TABLE IF NOT EXISTS experiment_timeline (
    timeline_id INT PRIMARY KEY AUTO_INCREMENT,
    experiment_id INT,
    start_date DATE,
    end_date DATE,
    FOREIGN KEY (experiment_id) REFERENCES experiments(experiment_id)
);

-- Table: publications
CREATE TABLE IF NOT EXISTS publications (
    publication_id INT PRIMARY KEY AUTO_INCREMENT,
    publication_title VARCHAR(255) NOT NULL,
    publication_date DATE,
    scientist_id INT,
    FOREIGN KEY (scientist_id) REFERENCES scientists(scientist_id)
);

-- Table: experiment_publications
CREATE TABLE IF NOT EXISTS experiment_publications (
	experiment_publications_id INT PRIMARY KEY AUTO_INCREMENT,
    experiment_id INT,
    publication_id INT,
    FOREIGN KEY (experiment_id) REFERENCES experiments(experiment_id),
    FOREIGN KEY (publication_id) REFERENCES publications(publication_id)
);