package service_test

import (
	"testing"

	"algogrit.com/emp-server/entities"
	"algogrit.com/emp-server/users/repository"
	"algogrit.com/emp-server/users/service"

	gomock "github.com/golang/mock/gomock"
	"github.com/stretchr/testify/assert"
)

func TestIndex(t *testing.T) {
	ctrl := gomock.NewController(t)

	mockRepo := repository.NewMockUserRepository(ctrl)
	v1Svc := service.NewV1(mockRepo)

	expectedEmps := []entities.Employee{
		{1, "G A", "LnD", 1001},
	}

	mockRepo.EXPECT().FindAll().Return(expectedEmps, nil)

	emps, err := v1Svc.Index()

	assert.Nil(t, err)
	assert.NotNil(t, emps)
	assert.Equal(t, expectedEmps, emps)
}
