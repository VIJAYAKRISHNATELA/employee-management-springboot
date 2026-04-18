package com.example.demo.controller;

import com.example.demo.service.EmployeeService;
import com.example.demo.exception.EmployeeNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import com.example.demo.controller.EmployeeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @MockBean EmployeeService employeeService;

    @Test
    @WithMockUser(roles = "USER")
    void getAllEmployees_returnsOk() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(List.of());
        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getEmployeeById_notFound_returns404() throws Exception {
        when(employeeService.getEmployeeById(99L))
                .thenThrow(new EmployeeNotFoundException("Employee not found with id: 99"));
        mockMvc.perform(get("/api/employees/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteEmployee_asAdmin_returnsOk() throws Exception {
        doNothing().when(employeeService).deleteEmployee(1L);
        mockMvc.perform(delete("/api/employees/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void deleteEmployee_asUser_returns403() throws Exception {
        mockMvc.perform(delete("/api/employees/1"))
                .andExpect(status().isForbidden());
    }
}