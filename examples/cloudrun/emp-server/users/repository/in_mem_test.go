package repository_test

import (
	"sync"
	"testing"

	"algogrit.com/emp-server/entities"
	"algogrit.com/emp-server/users/repository"

	"github.com/stretchr/testify/assert"
	"syreclabs.com/go/faker"
)

func TestConsistency(t *testing.T) {
	repo := repository.NewInMemoUserRepository()

	existingEmp, err := repo.FindAll()

	assert.Nil(t, err)

	existingEmpCount := len(existingEmp)

	var wg sync.WaitGroup

	for i := 0; i < 100; i++ {
		wg.Add(1)

		go func() {
			defer wg.Done()
			newEmp := entities.Employee{Name: faker.Name().Name(), Department: faker.Commerce().Department(), ProjectID: faker.Number().NumberInt(4)}

			_, err := repo.Save(newEmp)

			assert.Nil(t, err)
		}()
	}

	wg.Wait()

	allEmp, err := repo.FindAll()

	assert.Equal(t, existingEmpCount+100, len(allEmp))
	assert.Nil(t, err)
}
