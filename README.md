
## Deployment

HOW TO USE?

```bash
  use : 
  git clone https://github.com/ManuelAleman/CRUD_STUDENTS.git

  con = DriverManager.getConnection("JDBC_Connection", "user", "pwd");

    JDBC_Connection = JDBC connection URL

    user = database user

    pwd = database password in case you dont have one use ""; 


```


## DB STRUCTURE

CREATE TABLE Estudiante (

id int primary key,

nombre char(20),

apellido char(20),

edad int, 

telefono char(15),

carrera char(30)

)

