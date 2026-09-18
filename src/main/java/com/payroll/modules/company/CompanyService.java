package com.payroll.modules.company;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    @PostConstruct
    public void seedDefaultCompany() {
        if (companyRepository.count() == 0) {
            Company defaultCompany = Company.builder()
                    .companyName("PT Danamas Insan Kreasi Andalan")
                    .companyCode("DIKA")
                    .packageType("ENTERPRISE")
                    .isActive(true)
                    .build();
            companyRepository.save(defaultCompany);
        }
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    @Transactional
    public Company create(Company company) {
        return companyRepository.save(company);
    }

    @Transactional
    public Company update(Long id, Company details) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Company not found with id: " + id));
        company.setCompanyName(details.getCompanyName());
        company.setCompanyCode(details.getCompanyCode());
        company.setPackageType(details.getPackageType());
        company.setIsActive(details.getIsActive());
        return companyRepository.save(company);
    }
}
