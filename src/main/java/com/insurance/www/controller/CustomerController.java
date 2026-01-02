package com.insurance.www.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.insurance.www.model.Customer;
import com.insurance.www.service.CustomerService;
import com.insurance.www.util.FileStorageUtil;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(
    origins = {
        "http://localhost:9505",
        "https://insurancefront.netlify.app"
    }
)
public class CustomerController {

    @Autowired
    private CustomerService service;

    /* ================= ADD ================= */
    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addCustomer(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam long number,
            @RequestParam long aadhaar,
            @RequestParam String pan,
            @RequestParam String address,
            @RequestParam String state,
            @RequestParam String district,
            @RequestParam String mandal,
            @RequestParam String bankName,
            @RequestParam String bankAccount,
            @RequestParam String ifsc,
            @RequestParam String agentId,
            @RequestParam String date,
            @RequestPart(required = false) MultipartFile aadharFile,
            @RequestPart(required = false) MultipartFile panFile,
            @RequestPart(required = false) MultipartFile photo
    ) {
        try {
            Customer c = new Customer();
            c.setName(name);
            c.setEmail(email);
            c.setNumber(number);
            c.setAadhaar(aadhaar);
            c.setPan(pan);
            c.setAddress(address);
            c.setState(state);
            c.setDistrict(district);
            c.setMandal(mandal);
            c.setBankName(bankName);
            c.setBankAccount(bankAccount);
            c.setIfsc(ifsc);
            c.setAgentId(agentId);

            if (date != null && !date.isBlank()) {
                c.setDate(LocalDate.parse(date));
            }

            if (aadharFile != null && !aadharFile.isEmpty()) {
                c.setAadhaarFilePath(FileStorageUtil.save(aadharFile));
            }

            if (panFile != null && !panFile.isEmpty()) {
                c.setPanFilePath(FileStorageUtil.save(panFile));
            }

            if (photo != null && !photo.isEmpty()) {
                c.setPhotoPath(FileStorageUtil.save(photo));
            }

            Customer saved = service.saveCustomer(c);
            return ResponseEntity.ok(saved);

        } catch (DataIntegrityViolationException e) {
            // UNIQUE constraint violations
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", "Duplicate value already exists"));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(Map.of("message", "Server error while adding customer"));
        }
    }

    /* ================= GET ALL ================= */
    @GetMapping("/all")
    public List<Customer> getAll() {
        return service.getAllCustomers();
    }

    /* ================= GET BY ID ================= */
    @GetMapping("/{custId}")
    public Customer getById(@PathVariable String custId) {
        return service.getCustomerByCustId(custId);
    }

    /* ================= FILTER ================= */
    @GetMapping("/filter")
    public List<Customer> filter(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String district,
            @RequestParam(required = false) String mandal
    ) {
        return service.filterCustomers(name, state, district, mandal);
    }

    /* ================= UPDATE ================= */
    @PutMapping(value = "/{custId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateCustomer(
            @PathVariable String custId,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam long number,
            @RequestParam String address,
            @RequestParam String bankName,
            @RequestParam String bankAccount,
            @RequestParam String ifsc,
            @RequestParam String date,
            @RequestPart(required = false) MultipartFile aadharFile,
            @RequestPart(required = false) MultipartFile panFile,
            @RequestPart(required = false) MultipartFile photo
    ) {
        try {
            Customer c = new Customer();
            c.setName(name);
            c.setEmail(email);
            c.setNumber(number);
            c.setAddress(address);
            c.setBankName(bankName);
            c.setBankAccount(bankAccount);
            c.setIfsc(ifsc);
            c.setDate(LocalDate.parse(date));

            if (aadharFile != null && !aadharFile.isEmpty()) {
                c.setAadhaarFilePath(FileStorageUtil.save(aadharFile));
            }

            if (panFile != null && !panFile.isEmpty()) {
                c.setPanFilePath(FileStorageUtil.save(panFile));
            }

            if (photo != null && !photo.isEmpty()) {
                c.setPhotoPath(FileStorageUtil.save(photo));
            }

            return ResponseEntity.ok(service.updateCustomer(custId, c));

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", "Duplicate value already exists"));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(Map.of("message", "Error updating customer"));
        }
    }

    /* ================= DELETE ================= */
    @DeleteMapping("/{custId}")
    public ResponseEntity<?> delete(@PathVariable String custId) {
        service.deleteCustomer(custId);
        return ResponseEntity.ok().build();
    }
}
