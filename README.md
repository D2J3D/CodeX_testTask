# CodeX_testTask
Data Warehouse implementation for a note keeping service.

# Usage cases
1. This API allows (using http requests) to save data not only about created notes and their authors, but also some probably important metrics for business(such as page loading time, navigation button clicks, time to edit note and note reading time). 
2. The analyst, in turn, can get the data either through created REST API (by http request) or by visiting website's \[link] page
3. And also there are graphs created [ coming soon ]

# Database structure
![image](https://github.com/D2J3D/CodeX_testTask/assets/120342275/a87f5199-eaca-417c-9a63-470696841558)

# API structure
Note object
| Endpoint | Type | Description |
| --- | --- | --- |
|/notes| GET |Retrieves a Page<NoteDTO> of all objects (NoteDTO.class) from note table|
|/notes/{id}| GET |Retrieves a ResponseEntity<NoteDTO>|
|/notes/{id}| POST |Creates a new Note with selected id. Retrieves saved object ResponseEntity<NoteDTO>|
|/notes/{id}| PUT |Fully updates object with selected id|
|/notes/{id}| PATCH |Partially updates object with selected id |
|/notes/{id}| DELETE |Delete an note with selected id|
