package repository

import (
	"sync"

	"algogrit.com/emp-server/entities"
)

type userInMemRepo struct {
	emp []entities.Employee
	mut sync.RWMutex
}

func (repo *userInMemRepo) FindAll() ([]entities.Employee, error) {
	repo.mut.RLock()
	defer repo.mut.RUnlock()
	return repo.emp, nil
}

func (repo *userInMemRepo) Save(newEmp entities.Employee) (*entities.Employee, error) {
	repo.mut.Lock()
	defer repo.mut.Unlock()
	newEmp.ID = len(repo.emp) + 1
	repo.emp = append(repo.emp, newEmp)

	return &newEmp, nil
}

func NewInMemoUserRepository() UserRepository {
	var employees = []entities.Employee{
		{1, "Gaurav", "LnD", 1001},
		{2, "David", "DBA", 10002},
		{3, "Hila", "Cloud", 10004},
		{4, "Shahar", "Cloud", 10005},
	}

	return &userInMemRepo{emp: employees}
}
