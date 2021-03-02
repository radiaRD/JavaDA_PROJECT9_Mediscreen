package com.patientData.patientInformation.repository;

        import com.patientData.patientInformation.domain.Patient;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

        import java.util.Date;
        import java.util.Optional;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Optional<Patient>findByLastNameAndFirstNameAndDateOfBirth(String lastName, String firstName, Date dateOfBirth);

}
