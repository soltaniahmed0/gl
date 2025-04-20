package com.example.Backend.Services;

import com.example.Backend.Entity.Company;
import com.example.Backend.Entity.Employee;
import com.example.Backend.Repository.CompanyRepository;
import com.example.Backend.Repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import com.example.Backend.Entity.Role;
import com.example.Backend.Factory.EmployeeFactoryProvider;
import com.example.Backend.Factory.EmployeeFactory;
@Service
@Transactional
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    CompanyRepository companyRepository;
    public List<Employee> getAllEmps() {
        return employeeRepository.findAll();
    }


    public Optional<Employee> getEmp(String email){
        return employeeRepository.findByEmail(email);
    }
    public Employee updateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    public Employee createEmployeeByRole(Role role, String firstname, String lastname, 
                                   String email, String password, Company company) {
    EmployeeFactory factory = EmployeeFactoryProvider.getFactory(role);
    Employee employee = factory.createEmployee(
        email, 
        firstname, 
        lastname, 
        role.toString(), // On passe le nom du rôle comme position
        company
    );
    employee.setPassword(encodePassword(password));
    return employeeRepository.save(employee);
}

    public Optional<Employee> getEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }

    @Autowired
    private JavaMailSender javaMailSender;
    public void sendPasswordByEmail(String email) {
        Optional<Employee> employeeOptional  = employeeRepository.findByEmail(email);
        if (employeeOptional.isPresent()) {
            Employee employee=employeeOptional.get();
            String newPassword = generateNewPassword();
            employee.setPassword(encodePassword(newPassword));
            employeeRepository.save(employee);

            sendEmail(employee.getEmail(), "Your new password", "Your new password is: " + newPassword);
        }
    }
    public String sendEmailVerification(String email) throws Exception {
        Optional<Employee>employeeOptional=employeeRepository.findByEmail(email);
        if(employeeOptional.isPresent()){
            String code=generateCode();
            try {
                sendEmail(email, "Your verification code", "Code: " + code);
            } catch (Exception e) {
                throw new Exception("Failed to send verification email: " + e.getMessage());
            }
            return code;
        }
        else
        {

            throw new Exception("Employee not found for email: " + email);
        }
    }
    private String generateCode(){
        String allowedChars = "0123456789";
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(allowedChars.length());
            code.append(allowedChars.charAt(index));
        }
        return code.toString();

    }
    private String generateNewPassword() {
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789\\|";
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(allowedChars.length());
            password.append(allowedChars.charAt(index));
        }
        return password.toString();
    }

    private String encodePassword(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);

    }

    private void sendEmail(String toEmail, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
    public void savePhoto(Integer employeeId, byte[] photo) {
        employeeRepository.savePhoto(employeeId, photo);
    }
    public  void SetnewPassword(Integer id,String password){
        String newPassword=encodePassword(password);
        employeeRepository.changePassword(id,newPassword);
    }
    public List<Employee> EmployeefromsameCompany(String company){
        return employeeRepository.findByCompany(company);
    }
    public List<Employee> getEmployeesByCompanyId(int companyId) {
        Company company= companyRepository.findByCompanyid(companyId);
        return employeeRepository.findByCompany(company);
    }
    public List<String> getEmpDeviceToken() {
        return employeeRepository.findEmployeesWithDeviceToken();
    }
}
