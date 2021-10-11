package service

import (
	"algogrit.com/emp-server/entities"
	"algogrit.com/emp-server/users/repository"
)

type userSvcV1 struct {
	repo repository.UserRepository
}

func (svc *userSvcV1) Index() ([]entities.Employee, error) {
	return svc.repo.FindAll()
}

func (svc *userSvcV1) Create(newEmp entities.Employee) (*entities.Employee, error) {
	return svc.repo.Save(newEmp)
}

func NewV1(repo repository.UserRepository) UserService {
	return &userSvcV1{repo}
}
