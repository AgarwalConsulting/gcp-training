package repository

import (
	"log"

	"github.com/jmoiron/sqlx"
	// _ "github.com/lib/pq"
	_ "github.com/jackc/pgx/v4/stdlib"

	"algogrit.com/sql_demo/entities"
)

type sqlxRepo struct {
	*sqlx.DB
}

func (repo sqlxRepo) FindAll() ([]entities.Employee, error) {
	employees := []entities.Employee{}
	err := repo.DB.Select(&employees, "SELECT * FROM employees")

	return employees, err
}

func (repo sqlxRepo) Save(newEmp entities.Employee) (*entities.Employee, error) {
	rows, err := repo.DB.Query("SELECT count(*) from employees")
	if err != nil {
		return nil, err
	}

	rows.Next()
	err = rows.Scan(&newEmp.ID)
	if err != nil {
		return nil, err
	}

	log.Println("Current employees count:", newEmp.ID)

	newEmp.ID++

	log.Println("Inserting new employee...")
	_, err = repo.DB.NamedExec("INSERT INTO employees (id, name, department, project_id) VALUES (:id, :name, :department, :project_id)", &newEmp)

	if err != nil {
		return nil, err
	}

	return &newEmp, nil
}

func NewSQLX(connectionString string) UserRepository {
	// db, err := sqlx.Open("postgres", connectionString)
	db, err := sqlx.Open("pgx", connectionString)

	if err != nil {
		log.Fatalln("Unable to connect:", err)
	}

	_, err = db.Exec("CREATE TABLE IF NOT EXISTS employees (id int primary key, name text, department text, project_id int)")

	if err != nil {
		log.Fatalln(err)
	}

	// [START cloud_sql_postgres_databasesql_limit]
	// Set maximum number of open connections to the database.
	db.SetMaxOpenConns(1)
	// [END cloud_sql_postgres_databasesql_limit]

	// [START cloud_sql_postgres_databasesql_lifetime]
	// Set Maximum time (in seconds) that a connection can remain open.
	db.SetConnMaxLifetime(900)
	// [END cloud_sql_postgres_databasesql_lifetime]

	return &sqlxRepo{db}
}
