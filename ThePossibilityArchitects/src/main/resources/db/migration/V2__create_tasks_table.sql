CREATE TABLE IF NOT EXISTS public.tasks
(
    id bigint NOT NULL DEFAULT nextval('tasks_id_seq'::regclass),
    author character varying(255) COLLATE pg_catalog."default" NOT NULL,
    file_path character varying(255) COLLATE pg_catalog."default" NOT NULL,
    activity boolean NOT NULL,
    random_number integer,
    CONSTRAINT tasks_pkey PRIMARY KEY (id)
    );

INSERT INTO tasks (author, file_path, random_number, activity)
VALUES ('Alyona', 'C:\Users\DELL\IdeaProjects\ThePossibilityArchitects\ThePossibilityArchitects\src\main\resources\static\task\Alyona.txt', 1,true);

INSERT INTO tasks (author, file_path, random_number, activity)
VALUES ('Bohdan Tkachuk', 'C:\Users\DELL\IdeaProjects\ThePossibilityArchitects\ThePossibilityArchitects\src\main\resources\static\task\Bohdan Tkachuk.txt', 2,true);

INSERT INTO tasks (author, file_path, random_number, activity)
VALUES ('Dmytro Shpak', 'C:\Users\DELL\IdeaProjects\ThePossibilityArchitects\ThePossibilityArchitects\src\main\resources\static\task\Dmytro Shpak.txt', 3,true);

INSERT INTO tasks (author, file_path, random_number, activity)
VALUES ('Korniichyk Illia', 'C:\Users\DELL\IdeaProjects\ThePossibilityArchitects\ThePossibilityArchitects\src\main\resources\static\task\Korniichyk Illia.txt', 4,true);

INSERT INTO tasks (author, file_path, random_number, activity)
VALUES ('Levi', 'C:\Users\DELL\IdeaProjects\ThePossibilityArchitects\ThePossibilityArchitects\src\main\resources\static\task\Levi.txt', 5,true);

INSERT INTO tasks (author, file_path, random_number, activity)
VALUES ('Pavel Pavlik', 'C:\Users\DELL\IdeaProjects\ThePossibilityArchitects\ThePossibilityArchitects\src\main\resources\static\task\Pavel Pavlik.txt', 6,true);

INSERT INTO tasks (author, file_path, random_number, activity)
VALUES ('Teract10s', 'C:\Users\DELL\IdeaProjects\ThePossibilityArchitects\ThePossibilityArchitects\src\main\resources\static\task\Teract10s.txt', 7,true);

