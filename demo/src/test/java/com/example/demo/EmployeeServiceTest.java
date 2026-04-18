package com.example.demo.service;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Employee;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock private EmployeeRepository employeeRepository;
    @InjectMocks private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setId(1L);
        employee.setName("Vijay");
        employee.setEmail("vijay@example.com");
        employee.setDepartment("Engineering");
        employee.setSalary(new BigDecimal("75000"));
        employee.setActive(true);
    }

    @Test
    void getEmployeeById_found_returnsEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        Employee result = employeeService.getEmployeeById(1L);
        assertThat(result.getName()).isEqualTo("Vijay");
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void getEmployeeById_notFound_throwsException() {
        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> employeeService.getEmployeeById(99L))
                .isInstanceOf(EmployeeNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void saveEmployee_validDto_savesAndReturns() {
        EmployeeDto dto = new EmployeeDto();
        dto.setName("Ravi");
        dto.setEmail("ravi@example.com");
        dto.setDepartment("HR");
        dto.setSalary(new BigDecimal("60000"));

        when(employeeRepository.save(any(Employee.class))).thenAnswer(i -> i.getArgument(0));
        Employee saved = employeeService.saveEmployee(dto);
        assertThat(saved.getEmail()).isEqualTo("ravi@example.com");
    }

    @Test
    void deleteEmployee_softDeletes_doesNotRemoveFromDb() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any())).thenReturn(employee);
        employeeService.deleteEmployee(1L);
        assertThat(employee.isActive()).isFalse();
        verify(employeeRepository, never()).deleteById(any());
    }

    @Test
    void getAllEmployees_returnsOnlyActiveEmployees() {
        when(employeeRepository.findByIsActiveTrue()).thenReturn(List.of(employee));
        List<Employee> result = employeeService.getAllEmployees();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).isActive()).isTrue();
    }
}