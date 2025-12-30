package com.insurance.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.insurance.www.model.Customer;
import com.insurance.www.service.CustomerService;
import com.insurance.www.util.FileStorageUtil;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = {"http://localhost:9505",
		"https://insurancefront.netlify.app"
	    }
	)
public class CustomerController {

    @Autowired
    private CustomerService service;

    /* ================= ADD ================= */
    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Customer addCustomer(
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
            @RequestPart(required = false) MultipartFile aadharFile,
            @RequestPart(required = false) MultipartFile panFile,
            @RequestPart(required = false) MultipartFile photo
    ) {

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

        if (aadharFile != null && !aadharFile.isEmpty())
            c.setAadhaarFilePath(FileStorageUtil.save(aadharFile));

        if (panFile != null && !panFile.isEmpty())
            c.setPanFilePath(FileStorageUtil.save(panFile));

        if (photo != null && !photo.isEmpty())
            c.setPhotoPath(FileStorageUtil.save(photo));

        return service.saveCustomer(c);
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

    /* ================= FILTER SEARCH ================= */
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
    public Customer updateCustomer(
            @PathVariable String custId,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam long number,
            @RequestParam String address,
            @RequestParam String bankName,
            @RequestParam String bankAccount,
            @RequestParam String ifsc,
            @RequestPart(required = false) MultipartFile aadharFile,
            @RequestPart(required = false) MultipartFile panFile,
            @RequestPart(required = false) MultipartFile photo
    ) {

        Customer c = new Customer();
        c.setName(name);
        c.setEmail(email);
        c.setNumber(number);
        c.setAddress(address);
        c.setBankName(bankName);
        c.setBankAccount(bankAccount);
        c.setIfsc(ifsc);

        if (aadharFile != null && !aadharFile.isEmpty())
            c.setAadhaarFilePath(FileStorageUtil.save(aadharFile));

        if (panFile != null && !panFile.isEmpty())
            c.setPanFilePath(FileStorageUtil.save(panFile));

        if (photo != null && !photo.isEmpty())
            c.setPhotoPath(FileStorageUtil.save(photo));

        return service.updateCustomer(custId, c);
    }

    /* ================= DELETE ================= */
    @DeleteMapping("/{custId}")
    public void delete(@PathVariable String custId) {
        service.deleteCustomer(custId);
    }
}
