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
|/notes/{id}| GET |Retrieves a ResponseEntity\<NoteDTO\>|
|/notes/{id}| POST |Creates a new Note with selected id. Retrieves saved object ResponseEntity\<NoteDTO\>|
|/notes/{id}| PUT |Fully updates object with selected id|
|/notes/{id}| PATCH |Partially updates object with selected id |
|/notes/{id}| DELETE |Delete an note with selected id|

User object
| Endpoint | Type | Description |
| --- | --- | --- |
|/users| GET |Retrieves a Page<UserDTO> of all objects (UserDTO.class) from users table|
|/users/{id}| GET |Retrieves a ResponseEntity\<UserDTO\>|
|/users/{id}| POST |Creates a new User with selected id. Retrieves saved object ResponseEntity\<UserDTO\>|
|/users/{id}| PUT |Fully updates user with selected id|
|/users/{id}| PATCH |Partially updates user with selected id |
|/users/{id}| DELETE |Delete an user with selected id|


Click object - object that is responsible for keeping statistics of clicking on navigation button
| Endpoint | Type | Description |
| --- | --- | --- |
|/nav-button-stat| GET |Retrieves a Page<ClickDTO> of all objects (ClickDTO.class) from nav_button_clicks table|
|/nav-button-stat/{id}| GET |Retrieves a ResponseEntity\<ClickDTO\>|
|/nav-button-stat/{id}| POST |Creates a new Click object with selected id. Retrieves saved object ResponseEntity\<ClickDTO\>|
|/nav-button-stat/{id}| PUT |Fully updates object with selected id|
|/nav-button-stat/{id}| PATCH |Partially updates object with selected id |
|/nav-button-stat/{id}| DELETE |Delete an click-record with selected id|

Edit object - object that is responsible for keeping statistics of editing notes
| Endpoint | Type | Description |
| --- | --- | --- |
|/edit-stat| GET |Retrieves a Page<EditDTO> of all objects (EditDTO.class) from note_edits table|
|/edit-stat/{id}| GET |Retrieves a ResponseEntity\<EditDTO\>|
|/edit-stat/{id}| POST |Creates a new Edit with selected id. Retrieves saved object ResponseEntity\<EditDTO\>|
|/edit-stat/{id}| PUT |Fully updates object with selected id|
|/edit-stat/{id}| PATCH |Partially updates object with selected id |
|/notedit-states/{id}| DELETE |Delete an object with selected id|

View object - object that is responsible for keeping statistics of viewing the notes
| Endpoint | Type | Description |
| --- | --- | --- |
|/view-stat| GET |Retrieves a Page<ViewDTO> of all objects (ViewDTO.class) from note_views table|
|/view-stat/{id}| GET |Retrieves a ResponseEntity\<ViewDTO\>|
|/view-stat/{id}| POST |Creates a new Veiw with selected id. Retrieves saved object ResponseEntity\<ViewDTO\>|
|/view-stat/{id}| PUT |Fully updates object with selected id|
|/view-stat/{id}| PATCH |Partially updates object with selected id |
|/view-stat/{id}| DELETE |Delete a view with selected id|


# Getting data from database visually (as a table)
| Endpoint | Description |
| --- | --- |
|/display/notes|Shows all data from notes database, but with pagination|
|/display/views|Shows all data from note_views database, but with pagination and sorted by createdAt field|
|/display/edits|Shows all data from note_edits database, but with pagination and sorted by createdAt field|
|/display/clicks|Shows all data from nav_button_clicks database, but with pagination and sorted by createdAt field|
|/display/users|Shows all data from users database, but with pagination|
