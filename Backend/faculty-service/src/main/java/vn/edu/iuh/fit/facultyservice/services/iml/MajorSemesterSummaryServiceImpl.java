package vn.edu.iuh.fit.facultyservice.services.iml;


import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.facultyservice.models.MajorSemesterSummary;
import vn.edu.iuh.fit.facultyservice.repositories.MajorSemesterSummaryRepository;
import vn.edu.iuh.fit.facultyservice.services.MajorSemesterSummaryService;

import java.util.List;

@Service
public class MajorSemesterSummaryServiceImpl implements MajorSemesterSummaryService {
    private final MajorSemesterSummaryRepository majorSemesterSummaryRepository;

    public MajorSemesterSummaryServiceImpl(MajorSemesterSummaryRepository majorSemesterSummaryRepository) {
        this.majorSemesterSummaryRepository = majorSemesterSummaryRepository;
    }


    @Override
    public List<MajorSemesterSummary> findAllByMajorIdAndYear(int majorId, int year) {
        return majorSemesterSummaryRepository.findAllByMajorIdAndYearOrderBySemester(majorId, year);
    }
}
