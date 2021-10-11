package repository

import (
	"database/sql"
	"log"

	_ "github.com/mattn/go-sqlite3"

	"algogrit.com/emp-server/entities"
)

type sqlRepo struct {
	*sql.DB
	// DB *sql.DB
}

func (repo sqlRepo) FindAll() ([]entities.Employee, error) {
	rows, err := repo.DB.Query("SELECT * FROM employees")

	if err != nil {
		return nil, err
	}

	var employees []entities.Employee
	for rows.Next() {
		var employee entities.Employee
		err := rows.Scan(&employee.ID, &employee.Name, &employee.Department, &employee.ProjectID)

		if err != nil {
			return nil, err
		}

		employees = append(employees, employee)
	}

	return employees, nil
}

func (repo sqlRepo) Save(newEmp entities.Employee) (*entities.Employee, error) {
	rows, err := repo.DB.Query("SELECT count(*) from employees")
	if err != nil {
		return nil, err
	}

	rows.Next()
	err = rows.Scan(&newEmp.ID)
	if err != nil {
		return nil, err
	}

	newEmp.ID++

	_, err = repo.DB.Exec("INSERT INTO employees (id, name, department, project_id) VALUES ($1, $2, $3, $4)", newEmp.ID, newEmp.Name, newEmp.Department, newEmp.ProjectID)

	if err != nil {
		return nil, err
	}

	return &newEmp, nil
}

func NewSQL(connectionString string) UserRepository {
	db, err := sql.Open("sqlite3", connectionString)

	if err != nil {
		log.Fatalln("Unable to connect:", err)
	}

	_, err = db.Exec("CREATE TABLE IF NOT EXISTS employees (id int primary key, name text, department text, project_id int)")

	if err != nil {
		log.Fatalln(err)
	}

	return &sqlRepo{db}
}
