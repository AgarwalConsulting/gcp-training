package repository

import "algogrit.com/emp-server/entities"

//go:generate mockgen -source=$GOFILE -package=repository -destination=./mock_repository.go

type UserRepository interface {
	FindAll() ([]entities.Employee, error)
	Save(newEmp entities.Employee) (*entities.Employee, error)
}
