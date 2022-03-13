## Dependencies
- java 8+
- postgresql 10+
- maven 3.6
# Setup
_____

### 1. Download
Download the source code from the [git repository](https://github.com/Lotashinski/task1).
`````bash
$ git clone git@github.com:Lotashinski/task1.git
`````

### 2. Create and connect database
Log in to the sql console as an administrator (``postgres`` is used by default)
`````bash
$ sudo -u postgres psql
`````
Then you need to create a database for the project and user
For better security, you need to change the name of the database, username and password.
````sql
postgres=# create database task1;
postgres=# create user task1 with password 'secret';
````
And at the end assign rights
```sql
postgres=# grant ALL on DATABASE task1 to task1 ;
```
Then you need to Connect database in project setting

In the ```src/main/resources/hibernate.cfg.xml``` file, change ```connection.username```, ```connection.password``` and in ```connection.url``` specify instead of ```task1``` the name of the created database.
### 3. Change settings
You can make changes to the settings in the file ```src/main/resources/.env```.
### 4. Compile
To build the project, you need to use [maven](https://maven.apache.org/).

`````bash
$ mvn package
`````
The ```war``` file will be created in ```target/```.
### 5. Deploy
You can deploy an application in a servlet container

