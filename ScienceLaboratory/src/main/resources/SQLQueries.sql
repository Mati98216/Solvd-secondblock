-- UPDATE

-- Updating departments table
UPDATE departments SET department_name = 'Biochemistry' WHERE department_id = 1;
UPDATE departments SET department_name = 'Neuroscience' WHERE department_id = 2;

-- Updating research_areas table
UPDATE research_areas SET area_name = 'Inorganic Chemistry' WHERE area_id = 1;
UPDATE research_areas SET area_name = 'Microbiology' WHERE area_id = 2;

-- Updating scientists table
UPDATE scientists SET email = 'john.doe@example.com' WHERE scientist_id = 1;
UPDATE scientists SET department_id = 2, area_id = 2 WHERE scientist_id = 2;

-- Updating laboratory_assistants table
UPDATE laboratory_assistants SET name = 'Emma Brown' WHERE assistant_id = 1;
UPDATE laboratory_assistants SET department_id = 3 WHERE assistant_id = 3;

-- Updating experiments table
UPDATE experiments SET experiment_name = 'Chemical Kinetics Study' WHERE experiment_id = 1;
UPDATE experiments SET scientist_id = 2 WHERE experiment_id = 3;

-- Updating analysis table
UPDATE analysis SET assistant_id = 2 WHERE analysis_id = 1;
UPDATE analysis SET scientist_id = 1 WHERE analysis_id = 3;

-- Updating experiment_results table
UPDATE experiment_results SET result_details = 'Chemical reaction kinetics analyzed' WHERE result_id = 1;
UPDATE experiment_results SET analysis_id = 2 WHERE result_id = 3;

-- Updating equipment table
UPDATE equipment SET equipment_name = 'Mass Spectrometer' WHERE equipment_id = 1;
UPDATE equipment SET department_id = 2 WHERE equipment_id = 3;

-- Updating experiment_equipment table
UPDATE experiment_equipment SET equipment_id = 2 WHERE experiment_equipment_id = 1;
UPDATE experiment_equipment SET experiment_id = 2 WHERE experiment_equipment_id = 3;

-- Updating experiment_timeline table
UPDATE experiment_timeline SET end_date = '2023-03-15' WHERE timeline_id = 1;
UPDATE experiment_timeline SET start_date = '2023-04-01' WHERE timeline_id = 3;

-- Updating publications table
UPDATE publications SET publication_title = 'Advances in Genetics Research' WHERE publication_id = 1;
UPDATE publications SET publication_date = '2023-06-20' WHERE publication_id = 3;

-- Updating experiment_publications table
UPDATE experiment_publications SET publication_id = 2 WHERE experiment_publications_id = 1;
UPDATE experiment_publications SET experiment_id = 3 WHERE experiment_publications_id = 3;

--  DELETE
-- Deleting entries from experiment_publications table
DELETE FROM experiment_publications WHERE experiment_publications_id = 8;

-- Deleting entries from publications table
DELETE FROM publications WHERE publication_id = 8;

-- Deleting entries from experiment_results table
DELETE FROM experiment_results WHERE result_id = 8;

-- Deleting entries from analysis table
DELETE FROM analysis WHERE analysis_id = 8;

-- Deleting entries from experiment_timeline table
DELETE FROM experiment_timeline WHERE timeline_id = 8;

-- Deleting entries from experiment_equipment table
DELETE FROM experiment_equipment WHERE experiment_equipment_id = 8;

-- Deleting entries from experiments table
DELETE FROM experiments WHERE experiment_id = 8;

-- Deleting entries from equipment table
DELETE FROM equipment WHERE equipment_id = 8;

-- Deleting entries from laboratory_assistants table
DELETE FROM laboratory_assistants WHERE assistant_id = 8;

-- Deleting entries from scientists table
DELETE FROM scientists WHERE scientist_id = 8;

-- Deleting entries from departments table
DELETE FROM departments WHERE department_id = 8;

-- Deleting entries from research_areas table
DELETE FROM research_areas WHERE area_id = 8;
--   ALTER TABLE
ALTER TABLE scientists
ADD COLUMN phone_number VARCHAR(20);

ALTER TABLE publications
MODIFY COLUMN publication_title VARCHAR(500) NOT NULL;

ALTER TABLE analysis
CHANGE COLUMN analysis_name analysis_description VARCHAR(150) NOT NULL;

ALTER TABLE experiment_publications
DROP FOREIGN KEY experiment_publications_ibfk_1;

ALTER TABLE laboratory_assistants
ADD CONSTRAINT unique_name UNIQUE (name);

SELECT *
FROM departments AS d
LEFT JOIN scientists AS s ON d.department_id = s.department_id
LEFT JOIN laboratory_assistants AS la ON d.department_id = la.department_id
LEFT JOIN research_areas AS ra ON s.area_id = ra.area_id
LEFT JOIN experiments AS e ON s.scientist_id = e.scientist_id
LEFT JOIN experiment_results AS er ON e.experiment_id = er.experiment_id
LEFT JOIN analysis AS an ON er.analysis_id = an.analysis_id
LEFT JOIN equipment AS eq ON d.department_id = eq.department_id
LEFT JOIN experiment_equipment AS ee ON e.experiment_id = ee.experiment_id
LEFT JOIN experiment_timeline AS et ON e.experiment_id = et.experiment_id
LEFT JOIN publications AS p ON s.scientist_id = p.scientist_id
LEFT JOIN experiment_publications AS ep ON e.experiment_id = ep.experiment_id;

-- JOINS
-- Retrieve experiment details along with associated scientist information using INNER JOIN
SELECT experiments.experiment_id, experiments.experiment_name, scientists.name AS scientist_name
FROM experiments
INNER JOIN scientists ON experiments.scientist_id = scientists.scientist_id;

-- Retrieve all scientists and their associated experiments (if any) using LEFT JOIN
SELECT scientists.name AS scientist_name, experiments.experiment_name
FROM scientists
LEFT JOIN experiments ON scientists.scientist_id = experiments.scientist_id;

-- Retrieve all scientists and their associated experiments (if any) using UNION of LEFT and RIGHT JOINs
SELECT scientists.name AS scientist_name, experiments.experiment_name
FROM scientists
LEFT JOIN experiments ON scientists.scientist_id = experiments.scientist_id
UNION ALL
SELECT scientists.name AS scientist_name, experiments.experiment_name
FROM scientists
RIGHT JOIN experiments ON scientists.scientist_id = experiments.scientist_id
WHERE scientists.scientist_id IS NULL;

-- Retrieve all combinations of scientists and research areas using CROSS JOIN
SELECT scientists.name AS scientist_name, research_areas.area_name
FROM scientists
CROSS JOIN research_areas;

-- Without Having

-- Count the number of experiments conducted by each scientist
SELECT scientist_id, COUNT(experiment_id) AS num_experiments
FROM experiments
GROUP BY scientist_id;

-- Calculate the total equipment count in each department
SELECT department_id, SUM(equipment_id) AS total_equipment_count
FROM equipment
GROUP BY department_id;
-- Calculate the sum of equipment items used per experiment
SELECT experiment_id, SUM(equipment_id) AS total_equipment_used
FROM experiment_equipment
GROUP BY experiment_id;

-- Calculate the average duration of experiments for each scientist
SELECT e.scientist_id, AVG(DATEDIFF(et.end_date, et.start_date)) AS avg_experiment_duration
FROM experiments e
JOIN experiment_timeline et ON e.experiment_id = et.experiment_id
GROUP BY e.scientist_id;
-- Count the total number of experiments conducted
SELECT COUNT(*) AS total_experiments
FROM experiments;

-- Calculate the total count of equipment available
SELECT SUM(equipment_id) AS total_equipment_count
FROM equipment;

-- Calculate the average duration of all experiments
SELECT AVG(DATEDIFF(end_date, start_date)) AS avg_experiment_duration
FROM experiment_timeline;

-- With HAVING

-- Count the number of experiments per scientist, but only show counts for scientists with more than 2 experiments
SELECT scientist_id, COUNT(experiment_id) AS num_experiments
FROM experiments
GROUP BY scientist_id
HAVING COUNT(experiment_id) > 1;

-- Calculate the total number of experiments conducted by each department and display only departments with more than X experiments
SELECT e.department_id, COUNT(ee.experiment_id) AS num_experiments
FROM equipment e
JOIN experiment_equipment ee ON e.equipment_id = ee.equipment_id
JOIN experiments ex ON ee.experiment_id = ex.experiment_id
GROUP BY e.department_id
HAVING COUNT(ex.experiment_id) > 2;

-- Calculate the total equipment count for each department and display only departments with more than 10 equipment items
SELECT department_id, SUM(equipment_id) AS total_equipment_count
FROM equipment
GROUP BY department_id
HAVING SUM(equipment_id) > 10;

-- Show the average duration of experiments for each scientist and only display scientists with an average duration greater than 30 days
SELECT e.scientist_id, AVG(DATEDIFF(et.end_date, et.start_date)) AS avg_experiment_duration
FROM experiments e
JOIN experiment_timeline et ON e.experiment_id = et.experiment_id
GROUP BY e.scientist_id
HAVING AVG(DATEDIFF(et.end_date, et.start_date)) > 30;

-- Count the number of analysis records per assistant and show only assistants with more than 4 analysis records
SELECT assistant_id, COUNT(analysis_id) AS num_analysis_records
FROM analysis
GROUP BY assistant_id
HAVING COUNT(analysis_id) > 2;

-- Calculate the sum of equipment items used per experiment and display only experiments where the total equipment count is greater than 3
SELECT experiment_id, SUM(equipment_id) AS total_equipment_used
FROM experiment_equipment
GROUP BY experiment_id
HAVING SUM(equipment_id) > 3;

-- Count the number of publications per scientist and show only scientists with more than 3 publications
SELECT scientist_id, COUNT(publication_id) AS num_publications
FROM publications
GROUP BY scientist_id
HAVING COUNT(publication_id) = 1;