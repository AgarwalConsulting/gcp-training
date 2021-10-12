package service

import "algogrit.com/sql_demo/entities"

//go:generate mockgen -source=$GOFILE -package=service -destination=./mock_service.go

type UserService interface {
	Index() ([]entities.Employee, error)
	Create(newEmp entities.Employee) (*entities.Employee, error)
}
