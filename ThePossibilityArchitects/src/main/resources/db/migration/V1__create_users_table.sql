CREATE TABLE IF NOT EXISTS public.users (
    id BIGSERIAL PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    file_path VARCHAR(255) NOT NULL,
    activity BOOLEAN NOT NULL
    );

INSERT INTO users (user_name, file_path, activity)
VALUES ('Alyona', 'C:\Users\DELL\IdeaProjects\ThePossibilityArchitects\ThePossibilityArchitects\src\main\resources\static\task\Alyona.txt', true);

INSERT INTO users (user_name, file_path, activity)
VALUES ('Bohdan Tkachuk', 'C:\Users\DELL\IdeaProjects\ThePossibilityArchitects\ThePossibilityArchitects\src\main\resources\static\task\Bohdan Tkachuk.txt', true);

INSERT INTO users (user_name, file_path, activity)
VALUES ('Dmytro Shpak', 'C:\Users\DELL\IdeaProjects\ThePossibilityArchitects\ThePossibilityArchitects\src\main\resources\static\task\Dmytro Shpak.txt', true);

INSERT INTO users (user_name, file_path, activity)
VALUES ('Korniichyk Illia', 'C:\Users\DELL\IdeaProjects\ThePossibilityArchitects\ThePossibilityArchitects\src\main\resources\static\task\Korniichyk Illia.txt', true);

INSERT INTO users (user_name, file_path, activity)
VALUES ('Levi', 'C:\Users\DELL\IdeaProjects\ThePossibilityArchitects\ThePossibilityArchitects\src\main\resources\static\task\Levi.txt', true);

INSERT INTO users (user_name, file_path, activity)
VALUES ('Pavel Pavlik', 'C:\Users\DELL\IdeaProjects\ThePossibilityArchitects\ThePossibilityArchitects\src\main\resources\static\task\Pavel Pavlik.txt', true);

INSERT INTO users (user_name, file_path, activity)
VALUES ('Teract10s', 'C:\Users\DELL\IdeaProjects\ThePossibilityArchitects\ThePossibilityArchitects\src\main\resources\static\task\Teract10s.txt', true);
