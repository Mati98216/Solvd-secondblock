-- Inserting into departments table
INSERT INTO departments (department_name) VALUES 
('Physics'),
('Chemistry'),
('Biology'),
('Engineering'),
('Computer Science'),
('Geology'),
('Mathematics'),
('Biochemistry'),
('Robotics'),
('Environmental Science');

-- Inserting into research_areas table
INSERT INTO research_areas (area_name) VALUES 
('Quantum Mechanics'),
('Organic Chemistry'),
('Genetics'),
('Mechanical Engineering'),
('Artificial Intelligence'),
('Geophysics'),
('Number Theory'),
('Proteomics'),
('Humanoid Robotics'),
('Climate Science');

-- Inserting into scientists table
INSERT INTO scientists (name, email, department_id, area_id) VALUES 
('John Doe', 'john@example.com', 1, 1),
('Jane Smith', 'jane@example.com', 2, 2),
('Michael Johnson', 'michael@example.com', 3, 3),
('Emily Brown', 'emily@example.com', 4, 4),
('David Lee', 'david@example.com', 5, 5),
('Robert Johnson', 'robert@example.com', 6, 6),
('Alice Thompson', 'alice@example.com', 7, 7),
('Sophie Wilson', 'sophie@example.com', 8, 8),
('Daniel Brown', 'daniel@example.com', 9, 9),
('Oliver Garcia', 'oliver@example.com', 10, 10);
-- Inserting into laboratory_assistants table
INSERT INTO laboratory_assistants (name, email, department_id, area_id) VALUES 
('Sarah Williams', 'sarah@example.com', 1, 1),
('Alex Turner', 'alex@example.com', 2, 2),
('Olivia Martinez', 'olivia@example.com', 3, 3),
('Ethan Garcia', 'ethan@example.com', 4, 4),
('Sophia Nguyen', 'sophia@example.com', 5, 5),
('Grace Lee', 'grace@example.com', 6, 6),
('Liam Harris', 'liam@example.com', 7, 7),
('Emma Rodriguez', 'emma@example.com', 8, 8),
('Ava Nguyen', 'ava@example.com', 9, 9),
('Logan King', 'logan@example.com', 10, 10);

-- Inserting into experiments table
INSERT INTO experiments (experiment_name, scientist_id) VALUES 
('Quantum Entanglement Study', 1),
('Synthesis of Organic Molecules', 2),
('Gene Editing Techniques', 3),
('Mechanical Stress Analysis', 4),
('AI Image Recognition', 5),
('Geological Survey', 6),
('Number Theory Hypothesis', 7),
('Protein Interaction Analysis', 8),
('Humanoid Motion Studies', 9),
('Climate Change Impact Study', 10);

-- Inserting into analysis table
INSERT INTO analysis (analysis_name, scientist_id, assistant_id) VALUES 
('Entanglement Measurement', 1, 1),
('Chemical Analysis', 2, 2),
('Genetic Sequencing', 3, 3),
('Structural Analysis', 4, 4),
('Data Processing', 5, 5),
('Geological Data Analysis', 6, 2),
('Number Theory Proof Verification', 7, 3),
('Proteomics Profiling', 8, 4),
('Humanoid Behavior Analysis', 9, 2),
('Climate Model Simulation', 10, 1);

-- Inserting into experiment_results table
INSERT INTO experiment_results (experiment_id, analysis_id, result_details) VALUES 
(1, 1, 'Quantum entanglement observed.'),
(2, 2, 'Successful synthesis achieved.'),
(3, 3, 'Specific gene sequence identified.'),
(4, 4, 'Mechanical stress tolerance determined.'),
(5, 5, 'High accuracy in image recognition achieved.'),
(6, 5, 'Geological data analyzed.'),
(7, 4, 'Number theory hypothesis confirmed.'),
(8, 7, 'Protein interactions identified.'),
(9, 2, 'Humanoid motion patterns studied.'),
(10,3, 'Climate change impact assessed.');

-- Inserting into equipment table
INSERT INTO equipment (equipment_name, department_id) VALUES 
('Particle Accelerator', 1),
('Chemical Reactor', 2),
('DNA Sequencer', 3),
('Stress Test Machine', 4),
('GPU Servers', 5),
('Geological Survey Equipment', 6),
('Mathematical Modelling Software', 7),
('Biochemistry Analyzer', 8),
('Robotics Kits', 9),
('Environmental Sensors', 6);

-- Inserting into experiment_equipment table
INSERT INTO experiment_equipment (experiment_id, equipment_id) VALUES 
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 4),
(7, 2),
(8, 4),
(9, 5),
(10, 6);
-- Inserting into experiment_timeline table
INSERT INTO experiment_timeline (experiment_id, start_date, end_date) VALUES 
(1, '2023-01-01', '2023-02-01'),
(2, '2023-03-01', '2023-04-01'),
(3, '2023-05-01', '2023-06-01'),
(4, '2023-07-01', '2023-08-01'),
(5, '2023-09-01', '2023-10-01'),
(6, '2023-11-01', '2023-11-30'),
(7, '2023-12-01', '2023-12-31'),
(8, '2024-01-01', '2024-01-31'),
(9, '2024-02-01', '2024-02-29'),
(10, '2024-03-01', '2024-03-31');

-- Inserting into publications table
INSERT INTO publications (publication_title, publication_date, scientist_id) VALUES 
('Advancements in Quantum Physics', '2023-03-15', 1),
('New Organic Synthesis Methods', '2023-05-20', 2),
('Understanding Genetic Modifications', '2023-07-25', 3),
('Mechanical Stress Analysis Techniques', '2023-09-30', 4),
('AI in Image Recognition', '2023-11-05', 5),
('Geological Survey Findings', '2024-01-15', 6),
('Advancements in Number Theory', '2024-02-20', 7),
('Biochemical Analysis Report', '2024-03-25', 8),
('Robotics Motion Analysis Paper', '2024-04-30', 9),
('Environmental Impact Study Report', '2024-05-05', 10);

-- Inserting into experiment_publications table
INSERT INTO experiment_publications (experiment_id, publication_id) VALUES 
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);