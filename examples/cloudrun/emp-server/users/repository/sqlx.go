package repository

import (
	"log"

	"github.com/jmoiron/sqlx"
	_ "github.com/lib/pq"

	"algogrit.com/emp-server/entities"
)

type sqlxRepo struct {
	*sqlx.DB
}

func (repo sqlxRepo) FindAll() ([]entities.Employee, error) {
	// rows, err := repo.DB.Queryx("SELECT * FROM employees")

	// if err != nil {
	// 	return nil, err
	// }

	// var employees []entities.Employee
	// for rows.Next() {
	// 	var employee entities.Employee
	// 	err := rows.StructScan(&employee)

	// 	if err != nil {
	// 		return nil, err
	// 	}

	// 	employees = append(employees, employee)
	// }
	var employees []entities.Employee
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

	newEmp.ID++

	_, err = repo.DB.NamedExec("INSERT INTO employees (id, name, department, project_id) VALUES (:id, :name, :department, :project_id)", &newEmp)

	if err != nil {
		return nil, err
	}

	return &newEmp, nil
}

func NewSQLX(connectionString string) UserRepository {
	db, err := sqlx.Open("postgres", connectionString)

	if err != nil {
		log.Fatalln("Unable to connect:", err)
	}

	_, err = db.Exec("CREATE TABLE IF NOT EXISTS employees (id int primary key, name text, department text, project_id int)")

	if err != nil {
		log.Fatalln(err)
	}

	return &sqlxRepo{db}
}
