package http_test

import (
	"bytes"
	"encoding/json"
	"net/http"
	"net/http/httptest"
	"testing"

	"algogrit.com/emp-server/entities"
	empHTTP "algogrit.com/emp-server/users/http"
	"algogrit.com/emp-server/users/service"

	"github.com/golang/mock/gomock"
	"github.com/stretchr/testify/assert"
)

func TestCreate(t *testing.T) {
	ctrl := gomock.NewController(t)
	mockService := service.NewMockUserService(ctrl)

	h := empHTTP.NewHandler(mockService)

	resRec := httptest.NewRecorder()

	newEmpBytes := []byte(`{"name": "Gaurav"}`)
	bytesReader := bytes.NewReader(newEmpBytes)
	req := httptest.NewRequest("POST", "/v1/employees", bytesReader)

	emp := entities.Employee{Name: "Gaurav"}

	mockService.EXPECT().Create(emp).Return(&emp, nil)

	// h.Create(resRec, req)
	h.ServeHTTP(resRec, req)

	res := resRec.Result()
	var actualEmp entities.Employee

	json.NewDecoder(res.Body).Decode(&actualEmp)

	assert.Equal(t, "Gaurav", actualEmp.Name)
	assert.Equal(t, http.StatusOK, res.StatusCode)
}
